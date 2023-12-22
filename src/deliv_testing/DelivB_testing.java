package deliv_testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import deliverables.DelivB;
import deliverables.ReadGraph;
import prog_340_main.Graph;
import prog_340_main.Node;

class DelivB_testing {

	@Test
	@DisplayName("Test File 1")
	public void testFile1() {
		String path = "src/resources/DelivB/F1b.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Test File 1<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		DelivB b = new DelivB(scenerioFile,g);
		Hashtable <Node,ArrayList<String>> memo = b.getMemo();
		Node a = g.getNodeList().get(0);
		ArrayList<String> startAtA = memo.get(a);
		assertEquals(2, startAtA.size());
	}
	
	@Test
	@DisplayName("Test File 2")
	public void testFile21() {
		String path = "src/resources/DelivB/F2b.txt";
		File scenerioFile = new File(path);
		System.out.println("\n>>Test File 2-1<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		DelivB b = new DelivB(scenerioFile,g);
		Hashtable<Node,ArrayList<String>> memo = b.getMemo();
		Node richfield = g.getNodeList().get(1);
		Node bloomington = g.getNodeList().get(2);
		ArrayList<String> startAtRichfield = memo.get(richfield);
		ArrayList<String> startAtBloomington = memo.get(bloomington);
		assertAll("Minnesota test", ()-> assertEquals(2, startAtRichfield.size()),
				()-> assertEquals(3, startAtBloomington.size())
			);
	}
	

	@Test
	@DisplayName("Test File 3")
	public void testFile3() {
		String path = "src/resources/DelivB/F3b.txt";
		File scenerioFile = new File(path);
		System.out.println("\n>>Test File 3<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		DelivB b = new DelivB(scenerioFile,g);
		Hashtable<Node, ArrayList<String>> memo = b.getMemo();
		Node a = g.getNodeList().get(0);
		Node c = g.getNodeList().get(2);
		Node j = g.getNodeList().get(g.getNodeList().size()-1);
		ArrayList<String> startA = memo.get(a);
		ArrayList<String> startC = memo.get(c);
		ArrayList<String> startJ = memo.get(j);
		assertAll("large test", ()-> assertEquals(8, startA.size()),
				()-> assertEquals(12,startC.size()),
				()-> assertEquals(25, startJ.size())
			);
		
	}
	@Test
	@DisplayName("Test File 4")
	public void testFile4() {
		String path = "src/resources/DelivB/F4b.txt";
		File scenerioFile = new File(path);
		System.out.println("\n>>Test File 4<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		DelivB b = new DelivB(scenerioFile,g);
		Hashtable<Node, ArrayList<String>> memo = b.getMemo();
		Node a = g.getNodeList().get(0);
		ArrayList<String> startA = memo.get(a);
		assertEquals(3, startA.size());		
	}
	@Test
	@DisplayName("Test File 5")
	public void testFile5() {
		String path = "src/resources/DelivB/F5b.txt";
		File scenerioFile = new File(path);
		System.out.println("\n>>Test File 5<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		DelivB b = new DelivB(scenerioFile,g);
		Hashtable<Node, ArrayList<String>> memo = b.getMemo();
		Node a = g.getNodeList().get(0);
		ArrayList<String> startA = memo.get(a);
		assertEquals(6, startA.size());
	
		
	}
	
	@Test
	@DisplayName("Test File 6")
	public void testFile6() {
		String path = "src/resources/DelivB/F6b.txt";
		File scenerioFile = new File(path);
		System.out.println("\n>>Test File 6<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		Node f = g.getNodeList().get(g.getNodeList().size() - 1);
		DelivB b = new DelivB(scenerioFile,g);
		Hashtable<Node, ArrayList<String>> memo = b.getMemo();
		ArrayList<String> startF = memo.get(f);
		assertEquals(0, startF.size());
	}
	
	

}
