package practica1;

import practica1.vehiculos.Coche;

import java.io.IOException;
import java.util.*;

public class AstarSearch {

	public static void main(String[] args) {

		int origin = Integer.parseInt(args[0]);

		Coche coche1 = new Coche(1, origin);
		ArrayList<Node> camino = coche1.getMinimunRoad();
		System.out.println(camino);
			/*Graph graph = new Graph(origin);
			//System.out.println(graph.toString());
			double TInicio, TFin, tiempo;
			 TInicio = System.currentTimeMillis();
			AStar aStar = new AStar(graph);
			TFin = System.currentTimeMillis();
			tiempo = (TFin-TInicio)/1000;
			System.out.println(aStar.toString());
			System.out.println("Tiempo ejecucion: " + tiempo + " segundos\n");*/

	}
}