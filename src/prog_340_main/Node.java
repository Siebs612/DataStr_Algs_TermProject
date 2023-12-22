package prog_340_main;

import java.util.ArrayList;

//A node of a graph for the Spring 2018 ICS 340 program
/**Node
 * 
 * @version 1.1
 * @note
 * This class was updated for DelivC implementation. Both DelivA and DelivB still use this class as well.
 * This class has added variables and corresponding get and set methods. I also added a .equals() method
 * to this class to help identify nodes based on name or abbreviation.
 * 
 */

public class Node {
/**Class Variables
 * 
 * New Variables as of 1.1
 * int heur - The heuristic value
 * 
 */
	String name;
	String val;  // The value of the Node
	String abbrev; // The abbreviation for the Node
	int heur;
	ArrayList<Edge> outgoingEdges;  
	ArrayList<Edge> incomingEdges;
	
	public Node( String theAbbrev ){
		setAbbrev( theAbbrev );
		val = null;
		name = null;
		heur = 0;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
	}
	
	public String getAbbrev() {
		return abbrev;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVal() {
		return val;
	}
	
	public ArrayList<Edge> getOutgoingEdges() {
		return outgoingEdges;
	}
	
	public ArrayList<Edge> getIncomingEdges() {
		return incomingEdges;
	}
	public int getHeur() {
		return heur;
	}
	/**setHeur
	 * 
	 * New as of V_1.1
	 * 
	 * @param h the new Heuristic
	 * 
	 */
	public void setHeur(int h) {
		heur = h;
	}
	public void setAbbrev( String theAbbrev ) {
		abbrev = theAbbrev;
	}
	
	public void setName( String theName ) {
		name = theName;
	}
	
	public void setVal( String theVal ) {
		val = theVal;
	}
	public void addOutgoingEdge( Edge e ) {
		outgoingEdges.add( e );
	}
	
	public void addIncomingEdge( Edge e ) {
		incomingEdges.add( e );
	}
	
	/**equals()
	 * 
	 * @param n - node to be compared with
	 * @return either true or false 
	 * 
	 * @note
	 * This method is used to compare nodes based on their name or abbreviation
	 */
	public boolean equals(Node n) {
		if (n.getName().equals(this.name) || n.getAbbrev().equals(this.getAbbrev())) {
			return true;
		} else {
			return false;
		}
		
	}
}



	


