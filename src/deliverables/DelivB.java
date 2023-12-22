package deliverables;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

import prog_340_main.Edge;
import prog_340_main.Graph;
import prog_340_main.Node;

// Class DelivB does the work for deliverable DelivB of the Prog340
/** DelivB v1.5
 * @author Paul Siebenaler
 * @Program B
 * @date 09/25/2023
 * 
 * @version 1.5
 * 
 * @note
 * This class will take in a Directed Acyclic Graph and discover all the nodes
 * from a starting location to a target location. It will traverse a DAG
 * until all paths are found from each node to the target, not just the starting
 * node. This information is saved in a hash table which can be looked up
 * at a later time. This class uses dynamic programming to save the 
 * information to be used later, so another method call is not needed
 * to find the paths from any node in the graph to the target. This method uses
 * top down memorization because it stores the values as it traverses using recursion
 * throughout the graph. 
 * 
 * 
 * @note
 * DelivA also got an overhaul and is simplified and still works
 */
public class DelivB {
	// I added a hash table global variable so it can recalled at any time
	Hashtable<Node, ArrayList<String>> memo;
	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	
	public DelivB( File in, Graph gr ) {
		inputFile = in;
		g = gr;
		
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
		// After this point in the method will use dynamic programming
		// the target and start nodes and found on the graph and saved
		Node target = null;
		for (Node i: gr.getNodeList()) {
			if (i.getVal().equalsIgnoreCase("G")) {
				target = i;
			}
		}
		Node start = null;
		for (Node i: gr.getNodeList()) {
			if (i.getVal().equalsIgnoreCase("S")) {
				start = i;
			}
		} 
		// This ArrayList call will get all the paths from a starting point to a target and return the results as
		// 	a string. each path will be its own index in the ArrayList 
		ArrayList<String> results = getAllPaths(start, target, gr.getNodeList());
		// after the results are found they are formated and appended to a master string
		int numOfRoutes = results.size();
		StringBuilder sb = new StringBuilder();
		if (results.size()==0) {
			sb.append("There are no paths that lead from " + start.getName() + " to " + target.getName() + ".\n");
		} else {
			sb.append("There are " + numOfRoutes + " ways to go from " + start.getName() + " to " + target.getName() + ": ");
			// this will take each path, break it up and add an arrow in between. This is only designed for formatting and is a extra step
			// and unnecessary for anything else
			for (int i = 0; i < results.size(); i++) {
					String split[] = results.get(i).split("(?=[A-Z])");
					for (int k = 0; k < split.length; k++) {
						sb.append((k!=split.length-1)? split[k] + "->": split[k]); 
					}
					sb.append( (i!=results.size()-1)? ", ": ".\n");
			}
		}
		System.out.print(sb.toString());
		output.println(sb.toString());
	}
	/** getAllPaths(Node start, Node target, ArrayList<Nodes> nodes)
	 * 
	 * @param start -  this is the starting node
	 * @param target -  this is the target node
	 * @param nodes -  this is the list of all nodes on the graph gr
	 * @return
	 * this method is a initialization method for the program that creates the table.
	 * This method will create a hash table which contains the path as a string
	 * in the the form of: key = n node, value = list of paths from n to target
	 * 
	 * After this the start nodes string array is returned. It is first checked to make
	 * sure no path nodes are labeled correctly in the hash table
	 * 
	 * 
	 */
	public ArrayList<String> getAllPaths(Node start, Node target, ArrayList<Node> nodes) {
		// this memo table is used for top down memorization. It will contain the path from all nodes to target
		memo = new Hashtable<Node,ArrayList<String>>();
		// for loop to initialize the keys of the hash table
		for (Node n: nodes) {
			memo.put(n,new ArrayList<String>());
		}
		// this is the method beings the traversal of the graph. it will start at the target and work its way through the graph
		// the path is is initialized to "" because it is empty, null would not work, because i did not make it that way
		memo = getAllPaths(target,target,"",memo);
		for (Node key: memo.keySet()) {
			ArrayList<String> path = memo.get(key);
			if (path.size() == 1 && key.getAbbrev().equals(path.get(0))) {
				path = new ArrayList<String>();
				memo.replace(key, path);
			}
		}
		return memo.get(start);
	} 

/** getAllPaths(Node current, Node target, String path, Hash table memo)
 * 
 * @param current - this is the node the current call stack is at
 * @param target -  this is the target node
 * @param path - this is the path taken at each call stack
 * @param memo - this is the bottom up hash table used to store the results
 * @return memo - this will return the results of the traversal
 * 
 * @note This is the actual dynamic program. This is the method that will start from target and 
 * travel to every node in the graph. since it works its way backwards each new path is saved as
 * a new string in the hash table. This will step by step build the table with all the nodes that
 * connect to the target node. If a node does not connect to the target node its value in the
 * hash table will be an empty array. If the target node has no incoming edges the method returns
 * the empty table.
 * 
 */
	public Hashtable<Node, ArrayList<String>> getAllPaths(Node current, Node target, String path, Hashtable<Node, ArrayList<String>> memo) { 
		// each call will start by extracting the current nodes incoming edges
		ArrayList<Edge> paths = current.getIncomingEdges();
		if (paths.isEmpty() && current == target) {
			return memo;
		}
		// the abbreviation of this current node is added to the path
		path = current.getAbbrev().concat(path); 			
		// the path is update in the hash table and saved
		// since each path is stored in an ArrayList the list has to be cast to a new ArrayList p
		// then the path is added and the table is updated
		ArrayList<String> p = memo.get(current);
		p.add(path);
		memo.put(current, p);
		// this will check to go to the next node
		// it will randomly grab an edge and go to that next node until this edge does not have any
		for (Edge r: paths) {
			memo = getAllPaths(r.getTail(), target, path, memo);
		}
		// this returns the table
		return memo;
	}
	/** getMemo()
	 * 
	 * @return memo -  the table
	 * @note
	 * This method will return the memo after it has traveled across the  graph and found all the paths
	 * This can be used to find all the path to the target node from any n node in the graph gr without 
	 * have to call DelivB again and search the same graph again
	 */
	public Hashtable<Node, ArrayList<String>> getMemo() {
		return memo;
	}
}



