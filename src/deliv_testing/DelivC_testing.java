package deliv_testing;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import deliverables.DelivC;
import deliverables.ReadGraph;
import prog_340_main.AStar_Path;
import prog_340_main.Edge;
import prog_340_main.Graph;
import prog_340_main.Node;

public class DelivC_testing {
	
	@Test
	@DisplayName("import heuristic on H12 - 1")
	public void importHeur1_12() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		Node atlanta = hgr.getNodeList().get(0);
		DelivC.importHeur(hgr, g, 0);
		ArrayList<Edge> edges = atlanta.getIncomingEdges();
		for (int i = 0; i < edges.size(); i++) {
			assertEquals(edges.get(i).getDist(), g.getNodeList().get(i).getHeur());
		}
	}

	@Test
	@DisplayName("import heuristic on H49 - 1")
	public void importHeur1_49() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D49dist.txt";
		String path2 = "src/resources/DelivC/H49dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		Node albertLea = hgr.getNodeList().get(0);
		DelivC.importHeur(hgr, g, 0);
		ArrayList<Edge> ansKey = albertLea.getIncomingEdges();
		for (int i = 0; i < ansKey.size(); i++) {
			assertEquals(ansKey.get(i).getDist(), g.getNodeList().get(i).getHeur());
		}
	}
	
	@Test
	@DisplayName("import heuristic on H12 - 2")
	public void importHeur2_12() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		Node denver = hgr.getNodeList().get(4);
		DelivC.importHeur(hgr, g, 4);
		ArrayList<Edge> ansKey = denver.getIncomingEdges();
		for (int i = 0; i < ansKey.size(); i++) {
			assertEquals(ansKey.get(i).getDist(), g.getNodeList().get(i).getHeur());
		}
	}
	
	@Test
	@DisplayName("import heuristic on H49 - 2")
	public void importHeur2_49() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D49dist.txt";
		String path2 = "src/resources/DelivC/H49dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		Node shakopee = hgr.getNodeList().get(4);
		DelivC.importHeur(hgr, g, 4);
		ArrayList<Edge> ansKey = shakopee.getIncomingEdges();
		for (int i = 0; i < ansKey.size(); i++) {
			assertEquals(ansKey.get(i).getDist(), g.getNodeList().get(i).getHeur());
		}	
	}
	
	@Test
	@DisplayName("import heuristic on H49 - 3")
	public void importHeur3_49() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D49dist.txt";
		String path2 = "src/resources/DelivC/H49dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		Node worthington = hgr.getNodeList().get(48);
		DelivC.importHeur(hgr, g, 48);
		ArrayList<Edge> ansKey = worthington.getIncomingEdges();
		for (int i = 0; i < ansKey.size(); i++) {
			assertEquals(ansKey.get(i).getDist(), g.getNodeList().get(i).getHeur());
		}	
	}
	
	@Test
	@DisplayName("import heuristic on H12 - 3")
	public void importHeur3_12() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		Node washington = hgr.getNodeList().get(11);
		DelivC.importHeur(hgr, g, 11);
		ArrayList<Edge> ansKey = washington.getIncomingEdges();
		for (int i = 0; i < ansKey.size(); i++) {
			assertEquals(ansKey.get(i).getDist(), g.getNodeList().get(i).getHeur());
		}
	}
	
	@Test
	@DisplayName("Create a Path")
	public void createNewPath() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		int atlanta = 0;
		Node boston = g.getNodeList().get(1);
		DelivC.importHeur(hgr, g, atlanta);
		AStar_Path newP = new AStar_Path(boston);
		assertAll("Path generation", ()-> assertEquals(500, newP.getF_value()),
				()-> assertEquals("Bos", newP.getCurrentPathAbbrev())
				);
	}
	
	@Test
	@DisplayName("create and add to Path")
	public void createNewPathLong1() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		int atlanta = 0;
		Node boston = g.getNodeList().get(1);
		Node chicago = g.getNodeList().get(2);
		Node minneapolis = g.getNodeList().get(7);
		DelivC.importHeur(hgr, g, atlanta);
		AStar_Path newP = new AStar_Path(boston);
		newP.addNode(chicago);
		Edge chi_mps = null;
		for (Edge e:minneapolis.getIncomingEdges()) {
			if ((e.getTail().equals(chicago))) {
				chi_mps = e;
				break;
			}
		}
		newP.addNode(minneapolis, chi_mps);
		assertAll("path test", ()->assertEquals("Bos-Chi-Mpl", newP.getCurrentPathAbbrev()));
	}
	
	@Test
	@DisplayName("pathQue order test")
	public void pathQueue1() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		int atlanta = 0;
		Node boston = g.getNodeList().get(1);
		Node chicago = g.getNodeList().get(2);
		Node minneapolis = g.getNodeList().get(7);
		Node ny = g.getNodeList().get(8);
		Node wash = g.getNodeList().get(11);
		DelivC.importHeur(hgr, g, atlanta);
		AStar_Path newP = new AStar_Path(boston);
		newP.addNode(chicago);
		Edge chi_mps = null;
		for (Edge e:minneapolis.getIncomingEdges()) {
			if ((e.getTail().equals(chicago))) {
				chi_mps = e;
				break;
			}
		}
		newP.addNode(minneapolis, chi_mps);
		AStar_Path newP2 = new AStar_Path(boston);
		newP2.addNode(ny);
		Edge ny_wash = null;
		for (Edge e:wash.getIncomingEdges()) {
			if ((e.getTail().equals(ny))) {
				ny_wash = e;
				break;
			}
		}
		newP2.addNode(wash,ny_wash);
		Queue<AStar_Path> testQ = new PriorityQueue<>();
		testQ.add(newP);
		testQ.add(newP2);
		assertAll("orderTest", ()->assertEquals("Bos-NY-Was", testQ.poll().getCurrentPathAbbrev()),
				()-> assertEquals("Bos-Chi-Mpl", testQ.poll().getCurrentPathAbbrev())
				);
	}
	
	@Test
	@DisplayName("Shortest Path 1")
	public void shortPath_1() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist_shortPath_1.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("Mpl-->Chi", ()-> assertEquals("Mpl-Chi", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(354, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("Shortest Path 2")
	public void shortPath_2() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist_shortPath_2.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("SF-->LA", ()-> assertEquals("SF-LA", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(313, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("No Path 1 - Removed Edges")
	public void noPath_1() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist_noPath_1.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("Mpl-> removed Edges -> Bos", ()-> assertEquals("RETURN", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(0, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("No Path 2 - Removed Edges")
	public void noPath_2() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist_noPath_2.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("Dal-> removed Edges -> Was", ()-> assertEquals("RETURN", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(0, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("Bos->LA D12_1 test")
	public void delivC_mainTest_12_1() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist_a_Bos-LA.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("Bos->LA", ()-> assertEquals("Bos-Chi-Den-LA", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(2599, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("SEA->NY D12_2 test")
	public void delivC_mainTest_12_2() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist_b_SEA-NY.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("SEA->NY", ()-> assertEquals("Sea-Mpl-Chi-NY", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(2487, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("Mia->Mpl D12_3 test")
	public void delivC_mainTest_12_3() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D12dist_c_Mia-Mpl.txt";
		String path2 = "src/resources/DelivC/H12dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("Mia->Mpl", ()-> assertEquals("Mia-Atl-Chi-Mpl", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(1543, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("Pipestone->Grand Portage D49_a test")
	public void delivC_mainTest_49_1() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D49dist_a_Pipestone-Grand Portage.txt";
		String path2 = "src/resources/DelivC/H49dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("Pipestone->Grand Portage", ()-> assertEquals("P-m-w-y-C-d-G", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(471, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("ThiefRiverFalls->Winona D49_b test")
	public void delivC_mainTest_49_2() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D49dist_b_ThiefRiverFalls-Winona.txt";
		String path2 = "src/resources/DelivC/H49dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("ThiefRiverFalls->Winona", ()-> assertEquals("T-D-b-Z-y-q-z-R-p", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(446, DelivC.getResults().getCurrentDist())
						);
	}
	
	@Test
	@DisplayName("Shakopee->EastGrandForks D49_3 test")
	public void delivC_mainTest_49_3() {
		Graph g = new Graph();
		Graph hgr = new Graph();
		String path1 = "src/resources/DelivC/Test_Cases/D49dist_c_Shakopee-EastGrandForks.txt";
		String path2 = "src/resources/DelivC/H49dist.txt";
		g = ReadGraph.readGraphInfo(g, new File(path1));
		hgr = ReadGraph.readGraphInfo(hgr, new File(path2));
		new DelivC(new File(path1), g, hgr);
		assertAll("Shakopee->EastGrandForks", ()-> assertEquals("K-y-X-U-o-E", DelivC.getResults().getCurrentPathAbbrev()),
				()-> assertEquals(332, DelivC.getResults().getCurrentDist())
						);
	}
}
