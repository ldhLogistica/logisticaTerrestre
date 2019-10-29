package practica1;

import java.util.*;
import java.io.*;

public class AStar {
	
	private Graph graph_;
	private ArrayList<Node> minimun_road = new ArrayList<Node>();
	private ArrayList<Node> not_visited_nodes = new ArrayList<Node>();
	private LinkedHashMap<Double,ArrayList<Node>> roads_list = new LinkedHashMap<Double, ArrayList<Node>>();
	/*private ArrayList<ArrayList<Node>> walks_list = new ArrayList<ArrayList<Node>>();
	private Iterator<ArrayList<Node>> walks_list_it = walks_list.iterator();*/
	
	
	AStar(Graph graph){
		this.graph_ = graph;
		Iterator<Node> it = this.graph_.getNodeList().keySet().iterator();
		Node aux = null;
		boolean found = false;
		while(it.hasNext() && !found) {
			aux = it.next();
			if(aux.isOrigin()) {
				found = true;
			}
		}
		//calculateMinimunRoad(aux);
		generateSons(aux);
	}
	
	/*private void run(Node node) {
		ArrayList<Node> sonsList = new ArrayList<Node>();
		if(this.not_visited_nodes.isEmpty()) {
			sonsList = generateSons(node);
		}else {
			double min;
			
		}
	}*/
	
	public void generateSons(Node node) {
		ArrayList<Node> sonsList = new ArrayList<Node>(); //Lista con los nodos vecinos que tienen distancias asignadas
		if(node.isObjetive()) {
			//return sonsList;
		}else {
			Iterator<Double> it_neighbours = this.graph_.getNodeList().get(node).iterator(); //Recorrerá las distancias del nodo pasado por parametro
			Iterator<Node> it_nodes = this.graph_.getNodeList().keySet().iterator(); //Recorrerá los nodos de la tabla hash
			double g;
			int i=1;
			Node aux = null;
			while(it_neighbours.hasNext()) { //Mientras existan vecinos...
				g = it_neighbours.next();
				
				
				if(g>0.0) { // Si se cumple esta condicion significa que es un vecino válido
					aux = null;
					aux = it_nodes.next();
					
					System.out.println("Distancia nodo actual: " + node.getDistance());
					System.out.println("Distancia en g: " + g);
					aux.setDistance(node.getDistance()+g);
					System.out.println("Vecino distancia: "+aux.getDistance());
					aux.setValue();
					sonsList.add(aux); //Añado el nodo vecino válido
				}else {
					it_nodes.next();
				}
			}
			Iterator<Node> it = sonsList.iterator();
			String cad = "";
			
			while(it.hasNext()) {
				
				System.out.println(it.next().getDistance() + ", ");
			}
			
			//return sonsList; //Devuelvo la lista de nodos vecinos
		}
	}
	
	/*public ArrayList<Node> calculateMinimunRoad(Node node) {
		
		// AÑADIR A LA LISTA DE NODOS NO VISITADOS, LOS NODOS HIJOS QUE SE ACABAN DE GENERAR
		ArrayList<Node> sons = generateSons(node);
		Iterator<Node> it_aux = sons.iterator();
		while(it_aux.hasNext()) {
			this.not_visited_nodes.add(it_aux.next());
		}
		
		// LOCALIZAR EL NODO CUYO VALOR F() SEA EL MINIMO
		double min = Double.MAX_VALUE;
		Iterator<Node> it = this.not_visited_nodes.iterator();
		Node aux = null;
		Node nodo_min = null;
		while(it.hasNext()) {
			aux = it.next();
			if(min>aux.getValue()) {
				min = aux.getValue();
				nodo_min = aux;
			}
		}
		
		// SACAR EL NODO MINIMO DE LA LISTA DE NO VISITADOS
		Iterator<Node> it_aux1 = this.not_visited_nodes.iterator();
		Node aux1 = null;
		boolean found = false;
		int i = 0;
		while(it_aux1.hasNext() && !found) {
			aux1 = it_aux1.next();
			if(min == aux1.getValue()) {
				found = true;
				this.not_visited_nodes.remove(i);
			}
			i++;
		}
		if(!this.roads_list.isEmpty()) {
			calculateMinimunRoad(nodo_min);	
		}
		return this.minimun_road;
			
	}*/
	
	
	
	
	
	
	public String toString() {
		
		return this.graph_.toString();
	}

}
