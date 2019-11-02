package practica1;

import java.util.*;
import java.io.*;

public class AStar {
	
	private Graph graph_;
	private ArrayList<Node> minimun_road = new ArrayList<Node>();
	private ArrayList<Node> not_visited_nodes = new ArrayList<Node>();
	private ArrayList<Node> objetives_nodes = new ArrayList<Node>();
	private int cont;
	//private ArrayList<ArrayList<Node>> roads_list = new ArrayList<ArrayList<Node>>();
	//private ArrayList<LinkedHashMap<Node, ArrayList<Node>>> roads_list = new ArrayList<LinkedHashMap<Node, ArrayList<Node>>>(); 	
	//private Iterator<ArrayList<Node>> walks_list_it = walks_list.iterator();
	
	
	AStar(Graph graph){
		this.cont = 0;
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
		System.out.println("Vamos a calcular los hijos de: " + node.getNodeID());
		ArrayList<Node> sonsList = new ArrayList<Node>(); //Lista con los nodos vecinos que tienen distancias asignadas
		if(!isViableRoad(node)) {
			return sonsList;
		}
		System.out.println("Este nodo es un nodo viable");
		if(node.isObjetive()) {
			return sonsList;
		}else {
			System.out.println("Este nodo no es un nodo objetivo");
			Iterator<Double> it_neighbours = this.graph_.getNodeList().get(node).iterator(); //Recorrerá las distancias del nodo pasado por parametro
			System.out.println("Numero de vecinos: " + it_neighbours);
			Iterator<Node> it_nodes = this.graph_.getNodeList().keySet().iterator(); //Recorrerá los nodos de la tabla hash
			double g;
			Node aux = null;
			while(it_neighbours.hasNext()) { //Mientras existan vecinos...
				g = it_neighbours.next();
				//System.out.println("Distancia con nodo vecino: " + g);
				if(g>0.0) { // Si se cumple esta condicion significa que es un vecino válido
					aux = it_nodes.next();
					System.out.println("El nodo " + aux.getNodeID() + " es vecino de " + node.getNodeID());
					aux.setFather(node);
					//System.out.println("Distancia nodo actual: " + node.getDistance());
					//System.out.println("Distancia en g: " + g);
					aux.setDistance(node.getDistance()+g);
					//System.out.println("Vecino distancia: "+aux.getDistance());
					aux.setValue();
					sonsList.add(aux); //Añado el nodo vecino válido
				}
				if(it_nodes.hasNext()) {
					it_nodes.next();
				}
				
			}
			Iterator<Node> it = sonsList.iterator();
			String cad = "";
			
			/*while(it.hasNext()) {
				
				//System.out.println(it.next().getDistance() + ", ");
			}*/
			Iterator<Node> it_sons = sonsList.iterator();
			while(it_sons.hasNext()) {
				System.out.println("ID vecino: " + it_sons.next().getNodeID());
			}
			return sonsList; //Devuelvo la lista de nodos vecinos
		}
	}
	
	public void calculateMinimunRoad(Node node) {
		System.out.println("\n-----------------------------------------------------------------------------\n");
		cont++;
		System.out.println("Veces ejecutado: " + cont);
		
		// AÑADIR A LA LISTA DE NODOS NO VISITADOS, LOS NODOS HIJOS QUE SE ACABAN DE GENERAR
		ArrayList<Node> sons = generateSons(node);
		Iterator<Node> it_sons = sons.iterator();
		System.out.println("Hijos de " + node.getNodeID());
		while(it_sons.hasNext()) {
			System.out.print(it_sons.next() +  ", ");
		}
		Iterator<Node> it_aux = sons.iterator();
		while(it_aux.hasNext()) {
			this.not_visited_nodes.add(it_aux.next());
		}
		
		
		Node nodo_min = getMinimunFNode();
		//System.out.println("Nodo minimo final: " + nodo_min.getNodeID() + " con valor " + nodo_min.getValue());
		
		// SACAR EL NODO MINIMO DE LA LISTA DE NO VISITADOS
		Iterator<Node> it_aux1 = this.not_visited_nodes.iterator();
		Node aux1 = null;
		boolean found = false;
		int i = 0;
		Node nodo = null;
		while(it_aux1.hasNext() && !found) {
			aux1 = it_aux1.next();
			if(nodo_min.getValue() == aux1.getValue()) {
				nodo = aux1.clone();
				found = true;
				this.not_visited_nodes.remove(aux1);
				
			}
			//System.out.println("Tamanio: " + this.not_visited_nodes.size());
			i++;
		}
		
		if(nodo_min.isObjetive()) {
			
			this.objetives_nodes.add(nodo_min);
			System.out.println("El nodo " + nodo_min + " es objetivo");
			if(!this.not_visited_nodes.isEmpty()) {
				System.out.println("La lista está vacía");
				calculateMinimunRoad(getMinimunFNode());
			}
		}else if (!this.not_visited_nodes.isEmpty()) {
			System.out.println("Soy el nodo " + nodo_min + ", no soy objetivo y la lista de no visitados tiene tamanio " + this.not_visited_nodes.size());
			calculateMinimunRoad(nodo_min);
		}else {
			setMinimunRoad();
		}
			
	}
	
	private Node getMinimunFNode() {
		// LOCALIZAR EL NODO CUYO VALOR F() SEA EL MINIMO
		//System.out.println("Se ha llamado a la funcion getMinimunNode " + cont + " veces");
		cont++;
		double min = Double.MAX_VALUE;
		Iterator<Node> it = this.not_visited_nodes.iterator();
		Node aux = null;
		Node nodo_min = null;
		while(it.hasNext()) {
			aux = it.next();
			/*System.out.println("Valor F del nodo " + aux.getNodeID() + " = " + aux.getValue());
			System.out.println("Comprobando que " + min + " > " + aux.getValue());*/
			if(min>aux.getValue()) {
				//System.out.println("Condicion pasada");
				min = aux.getValue();
				nodo_min = aux;	
				/*System.out.println("Valor F del nodo minimo actual: " + nodo_min.getValue());
				System.out.println("----------------------\n");*/
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
