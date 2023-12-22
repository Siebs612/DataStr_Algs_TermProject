package deliverables;

import java.io.*;
import java.util.ArrayList;

import prog_340_main.*;
/** DelivA v2.0
 * @author Paul Siebenaler
 * @Program A
 * @date 08/28/23
 * 
 * @note This class when it is initialized will take in a text file and read it.  This scans the graph and can 
 * find the longest and shortest edges. 
 * 
 * @version 2.0
 * When I first did the program I thought this class needed to read and import the graph, instead i created an abstract class that imports the graph.
 * It does this the same way prog340 class reads a graph. So all this class should do is the requirements of 
 * 	1. read the and display the number of nodes
 * 	2. read and display the number of edges
 * 	3. find and print the the largest edge
 * 	4. find and print the smallest edge
 *     
 */
// Class DelivA does the work for deliverable DelivA of the Prog340

public class DelivA {
	private int longest = -1;
	private int shortest = -1;
	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	/***Constructor
	 * 
	 **DelivA 
	 * @param in = the file to be read
	 * @param gr = the graph to be used
	 * @result After this object is created the file will be scanned for the longest and shortest edges
	 * and the number of nodes and edges will added to the graph. 
	 * 
	 */
	public DelivA( File in, Graph gr ) {
		 
		inputFile = in;
		g = gr;
		
		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 );
		String[] extras = baseFileName.split("\\\\");
		//to get the graph name i am taking all leading characters off which leave the file name
		String graphName = extras[extras.length-1];
		String outputFileName = baseFileName.concat( "_out.txt" );
		outputFile = new File( outputFileName );
		FileWriter fr = null;
		try {
			fr = new FileWriter(outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// if the file exist then it will be written over
		if ( outputFile.exists() ) {    // For retests
			outputFile.delete();
		}
		// a master string is created, this will return the results of the tests
		StringBuilder masterString = new StringBuilder();
		masterString.append("Graph " + graphName + ":\n");
		// if the graph doesn't have any nodes or graphs it is displayed
		if (g.getNodeList().size() == 0) {
			masterString.append("This graph does not contain any Nodes\n");
		} else { 
			masterString.append("There are " + g.getNodeList().size() + " nodes in the graph.\n");
		}
		// if the graph doesn't have any edges then the program states this and ends, writing to the console and output file
		if (g.getEdgeList().size() == 0) {
			masterString.append("This graph does not contain any Edges\n");
			System.out.print(masterString.toString() + "\n");
			try {
				fr.write(masterString.toString());
				fr.flush();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		} else {
			masterString.append("There are " + g.getEdgeList().size() + " Edges in the graph.\n");
		}
		// shortestEdges will contain all the shortest edge,which could be 1 or more if the same distance is found 
		ArrayList<Edge> shortestEdges = new ArrayList<>();
		for (int i=0; i<g.getEdgeList().size(); i++ ) {
			int val = g.getEdgeList().get(i).getDist();
			if (shortestEdges.size() != 0) {
				int ref = shortestEdges.get(0).getDist();
				if (ref == val) {
					shortestEdges.add(g.getEdgeList().get(i));
				}
				// if a smaller element is found then the list is emptied and new shortest amount is added.
				if (ref > val) {
					shortestEdges.clear();
					shortestEdges.add(g.getEdgeList().get(i));
				}
			} else {
				shortestEdges.add(g.getEdgeList().get(i));
			}
		}
		// this next loop is the same as the last except it finds the largest edges in the graph
		ArrayList<Edge> longestEdges = new ArrayList<>();
		for (int i=0; i<g.getEdgeList().size(); i++ ) {
			int val = g.getEdgeList().get(i).getDist();
			if (longestEdges.size() != 0) {
				int ref = longestEdges.get(0).getDist();
				if (ref == val) {
					longestEdges.add(g.getEdgeList().get(i));
				}
				if (ref < val) {
					longestEdges.clear();
					longestEdges.add(g.getEdgeList().get(i));
				}
				
			} else {
				longestEdges.add(g.getEdgeList().get(i));
			}
		}
		//these next two loops are created to add to the masterString the results of the longest/shortest searches 
		masterString.append("The longest edge"); 
		masterString.append((longestEdges.size() ==1)? " is ":"s are ");
		for (int i=0; i<longestEdges.size();i++) {
			masterString.append(longestEdges.get(i).getTail().getAbbrev() + longestEdges.get(i).getHead().getAbbrev());
			masterString.append( (i!=longestEdges.size()-1)? " and ": " (" + longestEdges.get(0).getDist() + ").\n" );
		}
		masterString.append("The shortest edge"); 
		masterString.append((longestEdges.size() ==1)? " is ":"s are ");
		for (int i=0; i<shortestEdges.size();i++) {
			masterString.append(shortestEdges.get(i).getTail().getAbbrev() + shortestEdges.get(i).getHead().getAbbrev());
			masterString.append( (i!=shortestEdges.size()-1)? " and ": " (" + shortestEdges.get(0).getDist() + ").\n"  );
		}
		//the longest/shortest are save for later retrieval
		longest = longestEdges.get(0).getDist();
		shortest = shortestEdges.get(0).getDist();
		//this prints the master string displaying all the results
		//since StringBuilder is used you can paste the exact same string to the output file as well
		System.out.print(masterString.toString() + "\n");
		try {
			fr.write(masterString.toString());
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	/***get/set methods
	 * 
	 ** getLongestEdge
	 *	@param none 
	 * @return the longest edge distance only. if -1 then DelivA was never initialized
	 */
	public int getLongestEdge() {
		return longest;
	}
	/**getShortestEdge
	 * 
	 * @param none
	 * @return the shortest edge distance only. if -1 then DelivA was never initialized
	 */
	public int getShortestEdge() {
			return shortest;
	}
}


