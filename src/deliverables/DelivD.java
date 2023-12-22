package deliverables;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import prog_340_main.Edge;
import prog_340_main.Graph;
import prog_340_main.Node;

// Class DelivD does the work for deliverable DelivD of the Prog340
/***
 * DelivD
 * 
 * @author Paul Siebenaler
 * @date Nov. 28, 2023
 * @version 1.0
 * 
 * @apiNote This is my final program for prog_340. This class implements the 
 * Floyd_warshall Algorithm to find all shortest pairs in a graph. This program
 * creates a main matrix and a previous matrix from an input file and then performs
 * the algorithm on them. The main matrix will keep track of the shortest distance found
 * and the previous matrix records what the pairs previous node. This program will also
 * recognize negative weight cycles. If one is found the program stops and the matrix
 * and a note is returned to the user.
 * 
 */
public class DelivD {
	/*Class Variables
	 * static in[][] mainMatrix
	 * 		The 2D array that holds the graph information
	 * 
	 * static String[][] prevMatrix
	 * 		The 2D array that holds the previous nodes
	 * 
	 * StringBuilder sb
	 * 		The sb that holds formated data, at the end of the initialization the
	 * 		toString method is called and the data is returned to the console and
	 * 		output file.
	 * 
	 * static boolean failed
	 * 		After FW Alg. is performed if there was a negative weight cycle this variable
	 * 		will be true denoting a negative weight cycle was found
	 */
	
	static int[][] mainMatrix;
	String[][] prevMatrix;
	static int underline;
	StringBuilder sb;
	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	static boolean failed;
	/**
	 * DelivD
	 * 
	 * @param in - the input file that contained graph gr
	 * @param gr - the graph from file in
	 * 
	 * @note
	 * This constructor will take in the file in  and the graph gr and it
	 * will import this information into the two matrix. After this the 
	 * method will perform the Floyd-Warshall Algorithm on matrixes and then
	 * store this information. While this method is running the sb records each
	 * step and then returns this information to the console and an output file
	 */
	public DelivD( File in, Graph gr ) {
		sb = new StringBuilder();
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
		ArrayList<Node> nodeList = g.getNodeList();
		ArrayList<Edge> edgeList = g.getEdgeList();
		
		// both of the matrix are initialized to MAX_VALUE to denote infinity
		mainMatrix = new int[nodeList.size()][nodeList.size()];
		prevMatrix = new String[nodeList.size()][nodeList.size()];
		for (int i = 0; i < mainMatrix.length ; i++) {
			for (int j = 0; j < mainMatrix[i].length; j++) {
				mainMatrix[i][j] = (i == j)? 0:Integer.MAX_VALUE;
				prevMatrix[i][j] = "~";
		        }
			}
		
		// After the matrixes are initialized the graphs edges are imported into the main matrix
		for (int i = 0; i < mainMatrix.length; i++) {
			String rowName = nodeList.get(i).getName();
			for (int j = 0; j < mainMatrix[i].length; j++) {
				if (!edgeList.isEmpty()) {
					for(Edge e: new ArrayList<>(edgeList)) {
						if (e.getTail().getName().equals(rowName) && e.getHead().getName().equals(nodeList.get(j).getName())) {
							mainMatrix[i][j] = e.getDist();
							prevMatrix[i][j] = e.getTail().getAbbrev();
							edgeList.remove(e);
							break;
						}
					}
				}
			}
		}
		
		sb.append("Initial Main Matrix:\n");
		sb.append(printMatrix(mainMatrix,nodeList) + "\n");
		sb.append("Initial Previous Node Matrix\n");
		sb.append(printPrevMatrix(prevMatrix,nodeList) + "\n");
		sb.append(">");
		for (int i = 0; i < underline;i++) {
			sb.append("-");
		}
		sb.append("<\n\n");
		
		// This is the Floyd-Warshall Algorithm. It contains the 3 for loops to iterate through he mainMatrix k times
		try {
			for (int k = 0; k < mainMatrix.length; k++) {
				for (int i = 0; i < mainMatrix.length; i++) {
					for (int j = 0; j < mainMatrix[i].length; j++) {
						// If statement is to look for infinity, if one is found than mainMatrix[i][j] will not be updated
						if (mainMatrix[i][k] != Integer.MAX_VALUE && mainMatrix[k][j] != Integer.MAX_VALUE) {
							int prev = mainMatrix[i][j];
							mainMatrix[i][j] = Math.min(mainMatrix[i][j], mainMatrix[i][k] + mainMatrix[k][j]);
							prevMatrix[i][j] = prev != mainMatrix[i][j]? nodeList.get(k).getAbbrev():prevMatrix[i][j];
						}
						if (mainMatrix [k][k] != 0) {
							sb.append(String.format("Node %s has a Negative Weight Cycle\n", nodeList.get(k).getName()));
							throw new RuntimeException();
							
						}
					}
				}
				
				sb.append(String.format("Main Matrix Iteration: #%d thru Node %s", k+1, nodeList.get(k).getAbbrev()) + "\n");
				sb.append(printMatrix(mainMatrix,nodeList) + "\n");
				sb.append(String.format("Previous Node Matrix Iteration: #%d thru Node %s", k+1, nodeList.get(k).getAbbrev()) + "\n");
				sb.append(printPrevMatrix(prevMatrix,nodeList) + "\n");
				sb.append(">");
				for (int i = 0; i < underline;i++) {
					sb.append("-");
				}
				sb.append("<\n\n");
			}
			failed = false;
		} catch (Exception e) {
			sb.append("Unable to complete this Graph, printing the results until the error\n\n");
			failed = true;
		}
			
		
		sb.append("Final Main Matrix:\n" + printMatrix(mainMatrix, nodeList) + "\n");
		sb.append("Final Previous Node Matrix:\n" + printPrevMatrix(prevMatrix,nodeList));
		System.out.print(sb.toString());
		output.append(sb.toString());
		output.flush();
		sb.delete(0, sb.length());
	}
	
	/**
	 * getMainMatrix
	 * @param none
	 * @return int[][] mainMatrix
	 * @apiNote 
	 * after DelivD is initialized this is used to retrieve the results
	 * for JUnit testing.
	 */
	public static int[][] getMainMatrix(){
		return mainMatrix;
	}
	
	/**
	 * printMatrix
	 * @param mm -  The matrix to be printed
	 * @param nodes - The list of the nodes on the original graph 
	 * @return - the printed matrix as a string
	 * 
	 */
	public static String printMatrix(int[][] mm, ArrayList<Node> nodes) {
		StringBuilder sb_1 = new StringBuilder();
		sb_1.append(" *** |");
		for (int i = 0; i < nodes.size(); i++) {
			sb_1.append(String.format("%5s",nodes.get(i).getAbbrev()));
		}
		underline = sb_1.length();
		sb_1.append("\n");
		for (int i = 0; i < underline; i++) {
			sb_1.append("-");
		}
		sb_1.append("\n");
		for (int i = 0; i < mm.length; i++) {
			sb_1.append(String.format("%5s|", nodes.get(i).getName()));
			for (int j = 0; j< mm[i].length; j++) {
				sb_1.append(mm[i][j] == Integer.MAX_VALUE? "    ~":String.format("%5d", mm[i][j]));
			}
			sb_1.append("\n");
		}
		return sb_1.toString();
	}
	
	/**
	 * printPrevMatrix
	 * 
	 * @param mm - the prev String[][] Matrix
	 * @param nodes - The list of nodes from the original graph
	 * @return - the printed matrix as a string
	 */
	public static String printPrevMatrix(String[][]mm, ArrayList<Node> nodes) {
		StringBuilder sb_2 = new StringBuilder();
		sb_2.append(" *** |");
		for (int i = 0; i < nodes.size(); i++) {
			sb_2.append(String.format("%5s",nodes.get(i).getAbbrev()));
		}
		underline = sb_2.length();
		sb_2.append("\n");
		for (int i = 0; i < underline; i++) {
			sb_2.append("-");
		}
		sb_2.append("\n");
		for (int i = 0; i < mm.length; i++) {
			sb_2.append(String.format("%5s|", nodes.get(i).getName()));
			for (int j = 0; j< mm[i].length; j++) {
				sb_2.append(mm[i][j].equals("~")? "    ~":String.format("%5s", mm[i][j]));
			}
			sb_2.append("\n");
		}
		return sb_2.toString();
	}
	
	/**
	 * isNegCycle
	 * 
	 * @param none
	 * @return
	 * - true if a negative weight cycle is found
	 * - false if a negative weight cycle IS NOT found
	 */
	public static boolean isNegCycle() {
		return failed;
	}
}



