package prog_340_main;

import java.util.ArrayList;
/**AStar_Path
 * 
 * @author Paul Siebenaler
 * @date Oct 24th, 2023
 * @version 1.0
 * 
 * @note
 * This class is designed to hold a set of nodes in an ArrayList. This ArrayList list is then
 * held in a priority queue used for A* Search. This class will contain the path of nodes, the current distance
 * this path has traveled and the f_value. The compareTo Method is overrode to allow the f_value be the the
 * value used in the Queue order. The lower f_values will have higher priority and will be at the front of a Queue
 * 
 */
public class AStar_Path implements Comparable<AStar_Path>{
	int f_value;
	int currentDist;
	ArrayList<Node> path;
	/***Constructors
	 * 
	 **AStar__Path(node n)
	 *
	 * @param n- usually a new node
	 * @return A new path is created starting at Node n
	 */
	public AStar_Path(Node n) {
		path = new ArrayList<>();
		f_value = n.getHeur();
		currentDist = 0;
		path.add(n);
	}
	/**AStar_Path(AStar_Path p)
	 * 
	 * @param p - this is for an existing path to make a clone
	 * @return A new path is created as a copy of AStar_Path p
	 */
	public AStar_Path(AStar_Path p) {
		this.f_value = p.getF_value();
		this.currentDist = p.getCurrentDist();
		this.path = new ArrayList<Node>(p.getPath());
	}
	/**addNode(Node n, Edge e)
	 * 
	 * @param n -This is the next node in the path added to the end of the ArrayList
	 * @param e - This is the route to the current node
	 * @note
	 * A new node is added to the ArrayList and the f_value and currentDist are updated
	 */
	public void addNode(Node n, Edge e) {
		path.add(n);
		currentDist += e.getDist(); 
		f_value = n.getHeur() + currentDist;
	}
	/**addNode(Node n)
	 * 
	 * @param n - the new node added to the list
	 * @note
	 * This method is used for adding a raw node to the list, mainly for JUnit testing
	 */
	public void addNode(Node n) {
		path.add(n);
		currentDist = n.getHeur();
		f_value = n.getHeur();
	}
	/**compareTo()
	 * 
	 * @param AStar_Path p the path to be compared with
	 * @note
	 * This method overrides java's compareTo method used for priority queues. This allows
	 * This type of object to be sorted by f_value when stored in a priority Queue of
	 * AStar_Path objects. The lower the f_value the higher priority. If there is a tie, then
	 * there is one tie breaker. That is Whichever path has a shorter distance traveled gets a higher priority.
	 */
	@Override
	public int compareTo(AStar_Path p) {
	    if (this.getF_value() < p.getF_value()) {
	        return -1;
	    } else if (this.getF_value() > p.getF_value()) {
	        return 1;
	    } else {
	        if (this.getCurrentDist() < p.getCurrentDist()) {
	            return -1;
	        } else if (this.getCurrentDist() > p.getCurrentDist()) {
	            return 1;
	        } else {
	            return 0;
	        }
	    }
	}
	/***Get Methods
	 * 
	 **getF_value 
	 * @return the current paths f_value
	 */
	public int getF_value() {
		return f_value;
	}
	/**getCurrentDist
	 * 
	 * @return the currentDist traveled
	 */
	public int getCurrentDist() {
		return currentDist;
	}
	/**getCurrentNode()
	 * 
	 * @return the last node visited, which is the last node in the ArrayList
	 */
	public Node getCurrentNode() {
		return path.get(path.size()-1);
	}
	/**getPath
	 * 
	 * @return the current Path ArrayList
	 */
	public ArrayList<Node> getPath() {
		return path;
	}
	/**getCurrentPathAbbrev()
	 * 
	 * @return This will return the current path traveled in the form of "node.abbrev-node.abbrev-..."
	 */
	public String getCurrentPathAbbrev() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < path.size(); i++) {
			if (i == path.size() - 1) {
				sb.append(path.get(i).getAbbrev());
			} else {
				sb.append(path.get(i).getAbbrev() + "-");
			}
		}
		return sb.toString();
	}
	/**getCurrentPathName()
	 * 
	 * @return This will return the current path traveled in the form of "node.name-node.name-..."
	 */
	public String getCurrentPathName() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < path.size(); i++) {
			if (i == path.size() - 1) {
				sb.append(path.get(i).getName());
			} else {
				sb.append(path.get(i).getName() + "-");
			}
		}
		return sb.toString();
		
	}
}
