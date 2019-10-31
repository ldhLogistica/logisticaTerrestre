package practica1;

import java.util.*;
import java.io.*;

public class AStar {
	
	private Graph graph_;
	private ArrayList<Node> minimun_road = new ArrayList<Node>();
	private ArrayList<Node> not_visited_nodes = new ArrayList<Node>();
	private ArrayList<Node> objetives_nodes = new ArrayList<Node>();
	//private ArrayList<ArrayList<Node>> roads_list = new ArrayList<ArrayList<Node>>();
	//private ArrayList<LinkedHashMap<Node, ArrayList<Node>>> roads_list = new ArrayList<LinkedHashMap<Node, ArrayList<Node>>>(); 	
	//private Iterator<ArrayList<Node>> walks_list_it = walks_list.iterator();
	
	
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
		calculateMinimunRoad(aux);
		
	}
	
	public ArrayList<Node> generateSons(Node node) {
		ArrayList<Node> sonsList = new ArrayList<Node>(); //Lista con los nodos vecinos que tienen distancias asignadas
		if(isViableRoad(node)) {
			return sonsList;
		}
		if(node.isObjetive()) {
			return sonsList;
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
					aux = it_nodes.next().clone();
					aux.setFather(node);
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
			
			return sonsList; //Devuelvo la lista de nodos vecinos
		}
	}
	
	public void calculateMinimunRoad(Node node) {
		
		// AÑADIR A LA LISTA DE NODOS NO VISITADOS, LOS NODOS HIJOS QUE SE ACABAN DE GENERAR
		ArrayList<Node> sons = generateSons(node);
		Iterator<Node> it_aux = sons.iterator();
		while(it_aux.hasNext()) {
			this.not_visited_nodes.add(it_aux.next());
		}
		
		
		Node nodo_min = getMinimunFNode();
		
		
		// SACAR EL NODO MINIMO DE LA LISTA DE NO VISITADOS
		Iterator<Node> it_aux1 = this.not_visited_nodes.iterator();
		Node aux1 = null;
		boolean found = false;
		int i = 0;
		while(it_aux1.hasNext() && !found) {
			aux1 = it_aux1.next();
			if(nodo_min.getValue() == aux1.getValue()) {
				found = true;
				this.not_visited_nodes.remove(i);
			}
			i++;
		}
		
		if(nodo_min.isObjetive()) {
			this.objetives_nodes.add(nodo_min);
			if(!this.not_visited_nodes.isEmpty()) {
				calculateMinimunRoad(getMinimunFNode());
			}
		}else if (!this.not_visited_nodes.isEmpty()) {
			calculateMinimunRoad(getMinimunFNode());
		}else {
			setMinimunRoad();
		}
			
	}
	
	private Node getMinimunFNode() {
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
				return nodo_min;
	}
	
	//VERIFICAR SI ES VIABLE SEGUIR CON UN CAMINO
	private boolean isViableRoad(Node node) {
		Iterator<Node> it = this.objetives_nodes.iterator();
		boolean viable = true;
		while(it.hasNext() && viable) {
			if(it.next().getValue() <= node.getValue()) {
				viable = false;
			}
		}
		
		return viable;
	}
	
	private void setMinimunRoad(){
		Iterator<Node> it = this.objetives_nodes.iterator();
		Node optimal_node = null;
		Node aux = null;
		double minimunF = Double.MAX_VALUE;
		while(it.hasNext()) {
			aux = it.next();
			if(minimunF > aux.getValue()) {
				minimunF = aux.getValue();
				optimal_node = aux;
			}
		}
		Node node_it = optimal_node;
		while(!node_it.isOrigin()) {
			this.minimun_road.add(0, node_it);
			node_it = node_it.getFather();
		}	
	}

	public String toString() {
		
		String res = "";
		Iterator<Node> it = this.minimun_road.iterator();
		while(it.hasNext()) {
			res+=it.next().getNodeID();
			if(it.hasNext())
				res +="->";
		}
		
		return res;
	}
	

}
