package prog_340_main;

import javax.swing.*;

import deliverables.*;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/** ProgramA simply reads a file containing rows of space-separated Strings, 
 ** your assignment is to print out those strings interpreted as a graph.
 **
 ** @author
 **    Mike Stein
**/

public class Prog340 extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;  // Keep Eclipse happy.
	File inputFile;
	File outputFile;
	PrintWriter output;
	JFileChooser fileChooser;
	Graph g, gh;
	JMenuBar menuBar;
	JMenu mainMenu, runMenu;
	JMenuItem readFile, readHeuristicFile, exit, read_H_File;
	JMenuItem delivA, delivB, delivC, delivD;
	

/** The main method instantiates a Prog340 class, which includes giving you a choice of things to do.
 ** The only one active now will be reading the graph file and having you parse it.
 **
 ** @param args
 **    - Not used
 **
 ** @throws FileNotFoundException
 **    - Thrown if the file selected is not found.  Shouldn't happen with a FileChooser.
**/

  public static void main(String[] args) throws FileNotFoundException {
	  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		  public void run() {
			  createAndShowGUI();
		  }
	  });
  }
  
  /** Create and show the GUI.
   ** For thread safety, this method should be invoked from the event-dispatching thread.
   **/
  private static void createAndShowGUI() {
	  // Create and set up the window
	  JFrame frame = new JFrame("Prog340");
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  // Create and set up the content pane.
	  JComponent newContentPane = new Prog340();
	  newContentPane.setOpaque(true);;  // content panes must be opaque
	  frame.setContentPane(newContentPane);;
	  
	  // Display the window.
	  frame.setBounds(new Rectangle(250,100));
	  // @note i updated this so i can grab the window better - ps - 08/28
	  //frame.pack();
	  frame.setVisible(true); 
  }
   
 
/** The constructor creates a new ProgramA object, and sets up the input and output files.
**/
  public Prog340() {
	  
	  super( new BorderLayout() );

	try {
		// I like the colorful FileChooser.
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		System.out.println( "Look and feel set.");
	}
	catch (Exception e) { // exit on exception. 
		System.err.format("Exception: %s%n", e);
		System.exit(0);
	}

    fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose a file");
	
	// Start looking for files at the current directory, not home.
    fileChooser.setCurrentDirectory(new File("."));
	inputFile = null;
	// Create the menus
	menuBar = new JMenuBar();
	
	mainMenu = new JMenu("Main Menu");
	menuBar.add(mainMenu);
	readFile = new JMenuItem("Read File");
	// @note --> I added a menu button to get a hGraph
	read_H_File = new JMenuItem("Read Heuristic File");
	mainMenu.add(readFile);
	mainMenu.add(read_H_File);
	runMenu = new JMenu("Run Code");
	mainMenu.add(runMenu);
	exit = new JMenuItem("Exit");
	mainMenu.add(exit);
	
	delivA = new JMenuItem("Run Deliv A");
	runMenu.add(delivA);
	delivB = new JMenuItem("Run Deliv B");
	runMenu.add(delivB);
	
	delivC = new JMenuItem("Run Deliv C");
	runMenu.add(delivC);
	delivD = new JMenuItem("Run Deliv D");
	runMenu.add(delivD);
	
	// Here are the Action Listeners for the Menu Items
	
	readFile.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			g = new Graph();
			readGraphInfo(g);
		}
	});
	// @note --> Added to get a hGrapg file
	read_H_File.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			gh = new Graph();
			// to keep the output file as d%%dist_out i save file path before getting the hgraph and then return it
			File tmp = inputFile;
			readGraphInfo(gh);
			inputFile = tmp;
		}
	});
	
	exit.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			System.out.println("Goodbye");
			System.exit(0);
		}
	});
	
	delivA.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			if(g == null){
				System.out.println("Please select a Graph First\n");
			}
			else{
				new DelivA( inputFile, g);
			}
		}
	});
	
	delivB.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			if(g == null){
				System.out.println("Please select a Graph First\n");
			}
			else{
				new DelivB( inputFile, g);
			}
			
		}
	});
	
	delivC.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			if (g == null || gh == null) {
				System.out.println("Please select a Graph First\n");
			} else {
			  
				new DelivC( inputFile, g, gh);
			}
		}
	});

	delivD.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			if (g == null) {
				System.out.println("Please select a Graph First\n");
			} else {
				new DelivD( inputFile, g);
			}
		}
	});
	
	this.add(menuBar);
  }

  
  /** Read the file containing the Strings, line by line, then process each line as it is read.
  **/
    public void readGraphInfo( Graph graph ) {

  	try {
  		
  	   	if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

  	   		// Instantiate the selected input and output files.
  	   		inputFile = fileChooser.getSelectedFile();
  	   		System.out.println( "Chosen file = " + inputFile + "\n");
  	   	}
  	   	
  		// read text file
  		Scanner sc = new Scanner( inputFile );
  		// First line special:  It contains "~", and "val", and the nodes with the edges.
  		String firstLine = sc.nextLine();
  		String[] splitString = firstLine.split( " +" );
  		
  		// Ignore first two fields of first line,  Every other field is a node.
  		for ( int i = 2; i < splitString.length; i++ ) {
  			Node n = new Node( splitString[i] );
  			graph.addNode( n );
  		}
  		
  		// Every other line gives the name and value of the Node, and any edges.
  		int nodeIndex = 0;
  		ArrayList<Node> nodeList = graph.getNodeList();
  		
  		while ( sc.hasNextLine() ) {
  			String nextLine = sc.nextLine();
  			splitString = nextLine.split(" +");

  			Node n = nodeList.get( nodeIndex );
  			n.setName( splitString[0] );
  			n.setVal( splitString[1] );

  			for ( int i = 2; i < splitString.length; i++ ) {
  				if ( !splitString[i].equals("~") ) {
  					Node head = nodeList.get(i-2);
  					int dist = Integer.parseInt( splitString[i] );
  					Edge e = new Edge( n, head, dist );
  					graph.addEdge( e );
  					n.addOutgoingEdge( e );
  					head.addIncomingEdge( e );
  				}
  			}
  			nodeIndex++;

  		}
  		sc.close();

  	}
  	catch (Exception x) {
  		System.err.format("ExceptionOuter: %s%n", x);
  	}
  }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}		

}

