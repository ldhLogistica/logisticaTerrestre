package practica1;

import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class AStar {
	
	private Graph graph_;
	private ArrayList<Node> minimun_walk = new ArrayList<Node>();
	private ArrayList<Node> not_visited_nodes = new ArrayList<Node>();
	
	AStar(Graph graph){
		this.graph_ = graph;
		Iterator<Node> it = this.graph_.getNodeList().keySet().iterator();
		Node aux = null;
		boolean found = false;
		while(it.hasNext()) {
			aux = it.next();
			if(aux.isObjetive())
				run(aux);
		}
	}
	
	private void run(Node node) {
		ArrayList<Node> sonsList = new ArrayList<Node>();
		if(this.not_visited_nodes.isEmpty()) {
			sonsList = generateSons(node);
		}
	}
	
	private ArrayList<Node> generateSons(Node node) {
		ArrayList<Node> sonsList = new ArrayList<Node>(); //Lista con los nodos vecinos que tienen distancias asignadas
		Iterator<Double> it_neighbours = this.graph_.getNodeList().get(node).iterator(); //Recorrerá las distancias del nodo pasado por parametro
		int n=0; //Tendrá las posiciones en la tabla hash de los nodos vecinos al pasado por parametro
		double g;
		while(it_neighbours.hasNext()) { //Mientras existan vecinos...
			g = it_neighbours.next();
			if(g>0.0) { // Si se cumple esta condicion significa que es un vecino válido
				Iterator<Node> it_nodes = this.graph_.getNodeList().keySet().iterator(); //Recorrerá los nodos de la tabla hash
				Node aux = null;
				for(int i=0 ; i<n ; i++) { //Recorro la tabla buscando la posicion donde está el nodo vecino 
					it_nodes.next();
				}
				aux = it_nodes.next();
				aux.setDistance(node.getDistance()+g);
				aux.setValue();
				sonsList.add(aux); //Añado el nodo vecino válido
			}
			n++;
		}
		return sonsList; //Devuelvo la lista de nodos vecinos
	}
	
	
	
	public String toString() {
		
		return this.graph_.toString();
	}

}
