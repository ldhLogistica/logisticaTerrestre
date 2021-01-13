package proyecto;

import java.util.*;

/**
 * Clase que define la estructura A estrella. Un algoritmo de inteligencia artificial que calcula el camino mínimo entre dos puntos.
 * @class AStar
 * @author Equipo 1
 * @see <a href="https://github.com/ldhLogistica/logisticaTerrestre/blob/master/src/main/java/proyecto/AStar.java"> Repositorio Github - AStar</a>
 */

public class AStar {

	/**
	 * @brief variables de la clase
	 */
	private Graph graph_;
	public ArrayList<Node> minimun_road = new ArrayList<Node>();
	private ArrayList<Node> not_visited_nodes = new ArrayList<Node>();
	private ArrayList<Node> objetives_nodes = new ArrayList<Node>();
	private int cont;
	private int generated_nodes;
	private double distance;

	/**
	 * @brief Constructor para la creación del algoritmo.
	 * @param graph Grafo donde se ubica los puntos a recorrer
	 */
	public AStar(Graph graph){
		this.cont = 1;
		this.graph_ = graph;
		Iterator<Node> it_node = this.graph_.getNodeList().iterator();
		Node aux = null;
		boolean found = false;
		this.generated_nodes = 0;
		distance = 0.0;
		while(it_node.hasNext() && !found) {
			aux = it_node.next();
			if(aux.isOrigin()) {
				found = true;
			}
		}
		aux.setDistance(0.0);
		calculateMinimunRoad(aux);
		
	}

	/**
	 * @brief método que genera los hijos del punto actual
	 * @param node Nodo (Punto) actual
	 * @return listado de nodos (puntos) hijos del punto actual
	 */
	public ArrayList<Node> generateSons(Node node) {
		
		ArrayList<Node> sonsList = new ArrayList<Node>(); //Lista con los nodos vecinos que tienen distancias asignadas
		if(!isViableRoad(node)) {
			return sonsList;
		}
		
		if(node.isObjetive()) {
			return sonsList;
		}else {
			Node aux = null;
			Double auxd = null;
			Iterator<Node> it_neighbours = node.getSonsList().iterator();
			Iterator<Double> it_double = node.getSonsDistances().iterator();
			
			while(it_neighbours.hasNext()) {
				this.generated_nodes++;
				aux = it_neighbours.next();
				auxd = it_double.next();
				if(aux.getDistance()==0 || aux.getDistance()>node.getDistance()+auxd) {
					aux.setDistance(node.getDistance()+auxd);
					aux.setValue();
					
					sonsList.add(aux);
					aux.setFather(node);
				}

			}
			return sonsList;
		}
	}

	/**
	 * @brief método que calcula el camino mínimo a recorrer hasta un punto destino
	 * @param node Nodo actual
	 */
	public void calculateMinimunRoad(Node node) {
		
		cont++;
		
		// AÑADIR A LA LISTA DE NODOS NO VISITADOS, LOS NODOS HIJOS QUE SE ACABAN DE GENERAR
		ArrayList<Node> sons = generateSons(node);
		Iterator<Node> it_sons = sons.iterator();
		
		Node aux = null;
		while(it_sons.hasNext()) {
			aux = it_sons.next();
		}
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
		Node nodo = null;
		while(it_aux1.hasNext() && !found) {
			aux1 = it_aux1.next();
			if(nodo_min.getValue() == aux1.getValue()) {
				nodo = aux1.clone();
				found = true;
				this.not_visited_nodes.remove(aux1);
			}
			
			i++;
		}
		
		
		if(nodo_min.isObjetive()) {
			
			this.objetives_nodes.add(nodo_min);
			
			setMinimunRoad();
		}else { 
			
			calculateMinimunRoad(nodo_min);

		}	
	}

	/**
	 * @brief método que retorna el nodo siguiente al que hay que desplazarse desde el punto actual
	 * @return Nodo hijo destino
	 */
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

	/**
	 * @brief método que verifica si es viable seguir el camino actual
	 * @param node
	 * @return true or false en función del calculo
	 */
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

	/**
	 * @brief método que asigna el camino mínimo a la ruta a seguir
	 */
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
		this.distance = node_it.getDistance();
		while(!node_it.isOrigin()) {
			this.minimun_road.add(0, node_it);
			node_it = node_it.getFather();
		}	
		this.minimun_road.add(0, node_it);
	}

	public double  getDistance(){
		return this.distance;
	}
}