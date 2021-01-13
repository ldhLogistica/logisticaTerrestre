package proyecto;

import java.util.ArrayList;

/**
 * @class Clase Node para almacenar un nodo individual con sus datos y funciones para acceder a ellos
 * @author Equipo 1
 * @see <a href="https://github.com/ldhLogistica/logisticaTerrestre/blob/master/src/main/java/proyecto/Node.java"> Repositorio Github - Node</a>
 */

final public class Node{
	/**
	 * @brief variables de la clase
	 */
	private int nodeID_;
	private double g_; //Representa el camino recorrido desde el nodo inicial hasta el actual
	private double h_; //Representa el valor heur�stico
	private double f_; //Representa la suma de h + g
	private ArrayList<Node> sons_nodes = new ArrayList<Node>();
	private ArrayList<Double> sons_distances = new ArrayList<Double>();
	private Node father_node;
	boolean analized = false;
	boolean objetive_ = false;
	boolean origin_ = false;
	static protected int n_nodos = 0;
	
	
	/**
	 * @brief Constructor de la clase Node que define el nodo
	 * @param nodeID identificador de cada nodo
	 */
	Node(int nodeID){
		this.nodeID_ = nodeID;
		this.g_ = 0.0;
		n_nodos++;
		//System.out.println(this.nodeID_);
	}
	
	/**
	 * @brief método que retorna el nodo
	 * @return identificador del nodo
	 */
	public int getNodeID() {
		return this.nodeID_;
	}
	
	/**
	 * @brief método que retorna la heuristica (distancia en línea recta al objetivo)
	 * @return  heuristica
	 */
	public double getHeuristic() {
		return this.h_;
	}
	
	/**
	 * @brief método que retorna la distancia recorrida
	 * @return distancia
	 */
	public double getDistance() {
		return this.g_;
	}
	
	/**
	 * @brief método que devuelve el valor del nodo
	 * @return la suma la distancia recorrida más la línea recta
	 */
	public double getValue() {
		
		return this.f_;
	}

	/**
	 * @brief método que devuelve si el nodo actual es el objetivo
	 * @return true or false
	 */
	public boolean isObjetive() {
		return this.objetive_;
	}
	
	protected boolean isOrigin() {
		return this.origin_;
	}
	
	public void setValue() {
		this.f_ = getHeuristic() + getDistance();
	}
	
	/**
	 * @brief método que asigna la distancia recorrida hasta el momento
	 * @param distance
	 */
	public void setDistance(double distance) {
		this.g_ = distance;
	}
	
	/**
	 * @brief método que asigna la heuristica
	 * @param heuristic
	 */
	public void setHeuristic(double heuristic) {
		this.h_ = heuristic;
	}

	/**
	 * @brief método que asigna a este nodo como objetivo
	 */
	protected void setObjetive() {
		this.objetive_ = true;
	}

	/**
	 * @brief método que declara el nodo actual como origen
	 */
	protected void setOrigin() {
		this.origin_ = true;
	}

	/**
	 * @brief método destinado a formatear la salida por pantalla
	 * @return
	 */
	public String toString() {
		String cad = "";
		return cad + this.getNodeID();
	}

	/**
	 * @brief método que se utiliza para clonar un nodo (Igual un nodo con otro)
	 * @return nodo
	 */
	public Node clone() {
		Node node = new Node(this.nodeID_);
		node.analized = this.analized;
		node.f_ = this.f_;
		node.g_ = this.g_;
		node.h_ = this.h_;
		node.objetive_ = this.objetive_;
		node.origin_ = this.origin_;
		node.sons_nodes = this.sons_nodes;
		
		return node;
	}

	/**
	 * @brief método que asigna el nodo como padre del nodo introducido por parámetro
	 * @param node
	 */
	public void setFather(Node node) {
		this.father_node = node;
	}

	/**
	 * @brief método que devuelve el padre del nodo actual
	 * @return padre
	 */
	public Node getFather() {
		return this.father_node;
	}

	/**
	 * @brief método que añade un hijo y su distancia al nodo actual
	 * @param node
	 * @param distance
	 */
	public void addSon(Node node, double distance) {
		this.sons_nodes.add(node);
		this.sons_distances.add(distance);
	}

	/**
	 * @brief método que devuelve la lista de hijos del nodo actual
	 * @return
	 */
	public ArrayList<Node> getSonsList(){
		return this.sons_nodes;
	}

	/**
	 * @brief método que devuelve la distancia a los nodos hijos
	 * @return distancias
	 */
	public ArrayList<Double> getSonsDistances(){
		return this.sons_distances;
	}

}
