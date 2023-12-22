package deliv_testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import deliverables.DelivA;
import deliverables.ReadGraph;
import prog_340_main.*;

class DelivA_testing {

	@Test
	@DisplayName("Zero Test-1")
	public void testZero1() {
		String path = "src/resources/DelivA/F5a.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Zero Test-1<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		new DelivA(scenerioFile.getAbsoluteFile(), g);
		assertEquals(0, g.getEdgeList().size());

	}
	@Test
	@DisplayName("Zero Test-2")
	public void testZero2() {
		String path = "src/resources/DelivA/F8a.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Zero Test-2<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		new DelivA(scenerioFile.getAbsoluteFile(), g);
		assertEquals(0, g.getNodeList().size());
	}
	
	@Test
	@DisplayName("One Test")
	public void oneTest() {
		String path = "src/resources/DelivA/F3a.txt";
		File scenerioFile = new File(path);
		System.out.println(">>One Test<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		new DelivA(scenerioFile.getAbsoluteFile(), g);
		assertEquals(4, g.getNodeList().size());
		assertEquals(2, g.getEdgeList().size());
	}
	@Test
	@DisplayName("Many Test")
	public void manyTest() {
		String path = "src/resources/DelivA/F10a.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Many Test<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		DelivA a = new DelivA(scenerioFile.getAbsoluteFile(), g);
		assertAll("many Test",()-> assertEquals(26, g.getNodeList().size()),
			()-> assertEquals(236, g.getEdgeList().size()),
			()-> assertEquals(996, a.getLongestEdge()),
			()-> assertEquals(6, a.getShortestEdge())
		);
	}
	
	@Test
	@DisplayName("Scenerios given")
	public void givenScenerios() {
		String path = "src/resources/DelivA/F0a.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Given Scenerios<<\n>>F0a.txt<<");
		Graph g1 = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		DelivA a = new DelivA(scenerioFile.getAbsoluteFile(), g1);
		path = "src/resources/DelivA/F1a.txt";
		scenerioFile = new File(path);
		Graph g2 = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		System.out.println(">>F1a.txt<<");
		DelivA b = new DelivA(scenerioFile.getAbsoluteFile(), g2);
		assertAll("Scenerios",()-> assertEquals(12, g1.getNodeList().size()),
				()-> assertEquals(46, g1.getEdgeList().size()),
				()-> assertEquals(1415, a.getLongestEdge()),
				()-> assertEquals(207, a.getShortestEdge()),
				()-> assertEquals(5, g2.getNodeList().size()),
				()-> assertEquals(5, g2.getNodeList().size()),
				()-> assertEquals(9, b.getLongestEdge()),
				()-> assertEquals(1, b.getShortestEdge())
			);
	}
	@Test
	@DisplayName("Many Node, No Edges")
	public void manyNNE() {
	String path = "src/resources/DelivA/F7a.txt";
	File scenerioFile = new File(path);
	Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
	System.out.println(">>No Edge Test<<");
	new DelivA(scenerioFile.getAbsoluteFile(), g);
	assertAll("many Test",()-> assertEquals(8, g.getNodeList().size()),
			()-> assertEquals(0, g.getEdgeList().size())
		);
	}
	
	@Test
	@DisplayName("Many Node, All Same Edges")
	public void manyAE() {
	String path = "src/resources/DelivA/F4a.txt";
	File scenerioFile = new File(path);
	Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
	System.out.println(">>All Same Edge Test<<");
	DelivA a = new DelivA(scenerioFile.getAbsoluteFile(), g);
	assertAll("oops all Edges",()-> assertEquals(5, g.getNodeList().size()),
			()-> assertEquals(25, g.getEdgeList().size()),
			()-> assertEquals(1, a.getLongestEdge()),
			()-> assertEquals(1, a.getShortestEdge())
		);
	}
	
	@Test
	@DisplayName("Large Test-1")
	public void largeTest1() {
	String path = "src/resources/DelivA/F6a.txt";
	File scenerioFile = new File(path);
	Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
	System.out.println(">>Large Grapgh Test1<<");
	DelivA a = new DelivA(scenerioFile.getAbsoluteFile(), g);
	assertAll("oops all Edges",()-> assertEquals(16, g.getNodeList().size()),
			()-> assertEquals(55, g.getEdgeList().size()),
			()-> assertEquals(491, a.getLongestEdge()),
			()-> assertEquals(1, a.getShortestEdge())
		);
	}
	
	@Test
	@DisplayName("Large Test-2")
	public void largeTest2() {
	String path = "src/resources/DelivA/F9a.txt";
	File scenerioFile = new File(path);
	Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
	System.out.println(">>Large Grapgh Test2<<");
	DelivA a = new DelivA(scenerioFile.getAbsoluteFile(), g);
	assertAll("oops all Edges",()-> assertEquals(100, g.getNodeList().size()),
			()-> assertEquals(4973, g.getEdgeList().size()),
			()-> assertEquals(9999, a.getLongestEdge()),
			()-> assertEquals(1, a.getShortestEdge())
		);
	}
}
