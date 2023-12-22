package deliverables;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import prog_340_main.AStar_Path;
import prog_340_main.Edge;
import prog_340_main.Graph;
import prog_340_main.Node;

// Class DelivC does the work for deliverable DelivC of the Prog340
/***
 * DelivC
 * 
 * @author Paul Siebenaler
 * @date Oct 24th, 2023
 * @Version 1.0
 * 
 * @note This is my implementation of an A* Search for Project C. My search will read in a normal
 * graph and a graph containing heuristic values. Then it will use A* Search to find the shortest
 * route between the starting node "S" to the end node "G". This algorithm uses a priority Queue
 * to hold a series of paths. The Queue holds a wrapper class called AStar_Path, which is an ArrayList
 * of nodes linked together as a path. The Search will grab the first path from the queue denoted with
 * the ">" P| D| H| F| "<" on the console. if a shorter route to a node is found it will update the 
 * queue. every new node you pull out of the queue will have the shortest path discovered to that node
 * so a memo table is used to keep track of already visited nodes. the first time the goal node is encountered 
 * ends the search and the path leading to that node is returned. If no path is returned the program returns a 
 * note to the console.
 * 
 * 
 */
public class DelivC {
	/*@note
	 * added class variables
	 * 
	 * static AStar_Path results
	 * 		This is the results from running new DelivC
	 * 
	 * Graph gh
	 * 		The heuristic graph
	 * 
	 * static StringBuilder sb
	 * 		The String builder that records the events
	 */
	static AStar_Path results;
	static StringBuilder sb;
	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g, gh;
	
	/**Constructor
	 * 
	 * @param in - The input file for the Graph g
	 * @param gr - Standard Graph
	 * @param gHeur - Heuristic Graph
	 * 
	 * @note this method will take in two graphs and find the shortest distance from node S to G. It will then
	 * save this as the variable result. This method uses A* Search which will be explained later in the code.
	 */
	public DelivC( File in, Graph gr, Graph gHeur ) {
		inputFile = in;
		g = gr;
		gh = gHeur;
		
		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 ); // Strip off ".txt"
		String outputFileName = baseFileName.concat( "_out.txt" );
		outputFile = new File( outputFileName );
		if ( outputFile.exists() ) {    // For retests
			outputFile.delete();
		}
		
		try {
			output = new PrintWriter(outputFile);			
		}
		catch (Exception x ) { 
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		sb = new StringBuilder();
		Node start = null;
		Node goal = null;
		int targetIndex = -1;
		ArrayList<Node> holdList = g.getNodeList();
		for (int i = 0; i <holdList.size(); i++) {
			if (holdList.get(i).getVal().equalsIgnoreCase("g")) {
				goal = holdList.get(i);
				targetIndex = i;
			}
			if (holdList.get(i).getVal().equalsIgnoreCase("s")) {
				start = holdList.get(i);
			}
		}
		
		importHeur(gHeur, g, targetIndex);
		sb.append(">>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<\n");
		sb.append("|Shortest Path from " + start.getName() + " to " + goal.getName() + "|\n");
		sb.append(">>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<\n");
		AStar_Path calcPath = AStar_Search(g,start,goal);
		
		results = new AStar_Path(calcPath);
		
		if (!results.getCurrentNode().getAbbrev().equals("RETURN")) {
			sb.append("The Shortest Path from " + start.getName() + " to " + goal.getName() + ":\n");
			sb.append(">> " + calcPath.getCurrentPathAbbrev() + " << | Total Distance: " + calcPath.getCurrentDist() + "\n\n");
		}
		System.out.print(sb.toString());
		output.append(sb.toString());
		output.flush();
		sb.delete(0,sb.length()-1);
	}
	/** AStar_Search()
	 * 
	 * @param graph - Standard Graph
	 * @param start - the Starting Node
	 * @param goal - The Goal Node
	 * @return an AStar_Path containing the shortest path from start to goal using A* Search
	 * 
	 * @note  This method starts by creating a memo Hashtable to remember discovered nodes to prevent loops.
	 * The first path is added to the pathList priority queue and removed. From there every edge from this node 
	 * is added as a new path. The paths are arranged in the queue based on the f_value. This is the Heuristic of the current
	 * node in the path and the total distance to that node. the lowest f_value is at the front of the queue. When the goal
	 * node is found the program terminates and returns the path it took to get to that node. along the way the sb records
	 * each step of the Queue and will return it to be displayed later.
	 */
	public static AStar_Path AStar_Search(Graph graph, Node start, Node goal) {
		PriorityQueue<AStar_Path> pathList = new PriorityQueue<>();
		Hashtable<String,Integer> memo = new Hashtable<>();
		for (Node n:graph.getNodeList()) {                                      
			memo.put(n.getName(), -1);
		}
		AStar_Path startingPath = new AStar_Path(start);
		pathList.add(startingPath);
		while(!pathList.isEmpty()) {
			// lines 142 -162 are designed for formatting the sb record to be printed later
			sb.append("P|PATH\tD|DIST\tH|HEUR\tF|F-VALUE\n");
			int count = 0;
			for (AStar_Path p: pathList) {										
				if (count != 0) {
					sb.append(" P|" + p.getCurrentPathAbbrev() + 
					"\tD|" + p.getCurrentDist() + "\tH|" +
					p.getCurrentNode().getHeur() + "\tF|" + 
					p.getF_value() + "\n"
						); 
				} else {
					sb.append(">P|" + p.getCurrentPathAbbrev() + 
					"\tD|" + p.getCurrentDist() + "\tH|" +
					p.getCurrentNode().getHeur() + "\tF|" + 
					p.getF_value() + "<\n"
					);
				}
				count++;
			}
			count = 0;
			sb.append("------------------------------------------\n");
			// The first path is taken off the Queue and the edges are examined and eventually added the the pathList as a new path
			AStar_Path curPath = new AStar_Path(pathList.poll());
			// This records that the shortest path to the current node is found, this stops loops
			memo.replace(curPath.getCurrentNode().getName(), 1);
			ArrayList<Edge> edges = curPath.getCurrentNode().getOutgoingEdges();
			for (Edge e: edges) { 												
				AStar_Path path = new AStar_Path(curPath);
				path.addNode(e.getHead(), e);
				// If the edge e contains the goal node the method records this and returns the path
				if (e.getHead().equals(goal)) {
					return path;
				}
				// This is a memo lookup to stop repeat paths
				if (memo.get(e.getHead().getName()) == -1) {
					boolean addPath = true;
					// this loop double checks the current paths to see if there is a shorter route to the current node.
					// this will also update the Queue if there is a shorter distance to a node
					for (AStar_Path p: pathList) {    				
						if (p.getCurrentNode().equals(path.getCurrentNode())) {
							// If the path to the current node in already in the the queue it will not be added
							addPath = false;
							// This checks to see if the there is a shorter path to the current node
							// I chose to compare by f_value because thats what they are sorted by in the Queue
							if (p.getF_value() > path.getF_value()) {
								pathList.remove(p);
								pathList.add(path);
								break;
							}
						}
					}
					if (addPath) {
						pathList.add(path);
					}
				}	
			}
		}
		//if no routes are found the and there are no more paths to take, the method returns a fake node and list
		sb.append("No route was found from: " + start.getName() + " to " + goal.getName()+ "\n\n");
		Node ret = new Node("RETURN");
		ret.setHeur(-1);
		return new AStar_Path(ret);
	}
	/**importHeur()
	 * 
	 * @param gHeur - the Heuristic Graph
	 * @param gr - the Standard Graph
	 * @param target - the index location of the target node.
	 * 
	 * @note This method will take in the two different graphs and update the node list for the standard graph.
	 * Each node has a heuristic so each node is updated with the  under estimate to the target node. This is used as 
	 * a helper program for A* Search
	 */
	public static void importHeur(Graph gHeur, Graph gr, int target) {
		ArrayList<Node> nodeList = gr.getNodeList();
		ArrayList<Node> hNodeList = gHeur.getNodeList();
		ArrayList<Edge> targetInEdges = new ArrayList<Edge>();
		targetInEdges = hNodeList.get(target).getIncomingEdges();
		ArrayList<Node> updatedList = new ArrayList<Node>();
		for (int i =0; i < nodeList.size(); i++) {
			Node nNode = nodeList.get(i);
			nNode.setHeur(targetInEdges.get(i).getDist());
			updatedList.add(nNode);
		}
		gr.updateNodeList(updatedList);
	}
	/**getResults
	 * 
	 * @return - the shortest path from the latest initialization of new DelivC()
	 * @note - Method is used for JUnit testing
	 */
	public static AStar_Path getResults() {
		return results;
	}
}


