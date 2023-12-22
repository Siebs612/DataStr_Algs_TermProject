package deliv_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import deliverables.DelivD;
import deliverables.ReadGraph;
import prog_340_main.Graph;

public class DelivD_testing {
	
	@Test
	public void fourXfour() {
		String path = "src/resources/DelivD/f1d.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Test-1<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		new DelivD(scenerioFile.getAbsoluteFile(), g);
		int[][] res = DelivD.getMainMatrix();
		int[][] ans = {
			    {0, 0, 1, 2},
			    {0, 0, 1, 2},
			    {-1, -1, 0, 1},
			    {-2, -2, -1, 0}
			};
		
		for (int i = 0; i <ans.length;i++) {
			for (int j = 0; j <ans[i].length; j++) {
				assertEquals(res[i][j], ans[i][j]);
			}
		}
		assertFalse(DelivD.isNegCycle());
	}
	
	@Test
	public void eightXeight() {
		String path = "src/resources/DelivD/f2d.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Test-2<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		new DelivD(scenerioFile.getAbsoluteFile(), g);
		int[][] res = DelivD.getMainMatrix();
		// *see cite_Statement for how i got these results
		int[][] ans = {
			    {0,2,5,0,1,-1,2,4},
			    {-1,0,3,-2,-1,-3,0,2},
			    {-2,-1,0,-3,-2,-4,-1,-1},
			    {1,3,5,0,1,-1,2,4},
			    {2,3,4,1,0,0,3,3},
			    {3,5,7,2,3,0,3,6},
			    {0,2,4,-1,0,-2,0,3},
			    {2,4,6,1,2,0,2,0}
			};
		
		for (int i = 0; i <ans.length;i++) {
			for (int j = 0; j <ans[i].length; j++) {
				assertEquals(res[i][j], ans[i][j]);
			}
		}
		assertFalse(DelivD.isNegCycle());
	}
	
	@Test
	public void twelveXtwelve() {
		String path = "src/resources/DelivD/f3d.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Test-3<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		new DelivD(scenerioFile.getAbsoluteFile(), g);
		int[][] res = DelivD.getMainMatrix();
		// *see cite_Statement for how i got these results
		int[][] ans = {
				{0,6,12,16,4,9,10,6,10,4,6,8},
				{-2,0,6,10,1,3,4,0,4,-2,0,2},
				{-5,1,0,7,-2,4,1,-3,1,-5,-3,-1},
				{-12,-6,0,0,-9,-3,-2,-10,-6,-12,-10,-8},
				{6,12,18,22,0,15,16,8,16,2,8,10},
				{-5,1,3,7,-2,0,1,-3,1,-5,-3,-1},
				{-6,0,2,6,-3,3,0,-4,0,-6,-4,-2},
				{-2,4,10,14,1,7,8,0,8,-2,0,2},
				{-6,0,6,10,-3,3,4,-4,0,-6,-4,-2},
				{4,10,16,20,3,13,14,6,14,0,6,8},
				{2,8,14,18,5,11,12,4,12,2,0,6},
				{0,6,12,16,3,9,10,2,6,0,-2,0}
				};
		
		for (int i = 0; i <ans.length;i++) {
			for (int j = 0; j <ans[i].length; j++) {
				assertEquals(res[i][j], ans[i][j]);
			}
		}
		assertFalse(DelivD.isNegCycle());
	}
	
	@Test
	public void negtiveWeightCycleDectection() {
		String path = "src/resources/DelivD/f4d.txt";
		File scenerioFile = new File(path);
		System.out.println(">>Test-4<<");
		Graph g = ReadGraph.readGraphInfo(new Graph(), scenerioFile);
		new DelivD(scenerioFile.getAbsoluteFile(), g);
		assertTrue(DelivD.isNegCycle());
	}
	
}
