package deliverables;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import prog_340_main.Edge;
import prog_340_main.Graph;
import prog_340_main.Node;
/*** ReadGraph
 * 
 * @author Paul Siebenaler
 * @note this class is created for testing the graphs, this is the same read method as in prog340, but this can be
 * called outside that class to import a graph for testing.
 */
public abstract class ReadGraph {
	public static Graph readGraphInfo(Graph g, File in) {
		try {
			Scanner sc = new Scanner( in );
			// First line special:  It contains "~", and "val", and the nodes with the edges.
			String firstLine = sc.nextLine();
			String[] splitString = firstLine.split( " +" );
			// Ignore first two fields of first line,  Every other field is a node.
			for ( int i = 2; i < splitString.length; i++ ) {
				Node n = new Node( splitString[i] );
				g.addNode( n );
			}
			// Every other line gives the name and value of the Node, and any edges.
			int nodeIndex = 0;
			ArrayList<Node> nodeList = g.getNodeList();
			
			while ( sc.hasNextLine() ) {
				String nextLine = sc.nextLine();
				splitString = nextLine.split(" +");
				Node n = nodeList.get( nodeIndex );
				n.setName( splitString[0] );
				n.setVal( splitString[1] );

				for ( int i = 2; i < splitString.length; i++ ) {
					if ( !splitString[i].equals("~") ) {
						Node head = nodeList.get(i-2);
						int dist = Integer.parseInt( splitString[i] );
						Edge e = new Edge( n, head, dist );
						g.addEdge( e );
						n.addOutgoingEdge( e );
						head.addIncomingEdge( e );
					}
				}
				nodeIndex++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			return g;
			
		}
}
		
