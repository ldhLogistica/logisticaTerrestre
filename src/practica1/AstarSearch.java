package practica1;

import java.io.IOException;
import java.util.*;

public class AstarSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length!=3) {
			System.err.println("Error: invalid numbers of files to read\n"
					+ "Usage: java AstarSearch [distances_filename.txt] [heuristics_filename.txt]");
		}else {
			String distances_filename = args[0];
			String heuristics_filename = args[1];
			int origin = Integer.parseInt(args[2]);
			
			try {
				Graph graph = new Graph(distances_filename,heuristics_filename,origin);
				System.out.println(graph.toString());
				AStar aStar = new AStar(graph);
				System.out.println(aStar.toString());
				
				//System.out.println(Astar.toString());
				System.out.println("FIN");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
