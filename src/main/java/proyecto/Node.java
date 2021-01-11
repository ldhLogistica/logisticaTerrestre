package proyecto;

import java.util.ArrayList;

/**
 * 
 * @author samue
 * 
 * Clase para almacenar un nodo individual con sus
 * datos y funciones para acceder a ellos
 *
 */

final public class Node{
	
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
	 * 
	 * @param nodeID identificador de cada nodo
	 */
	Node(int nodeID){
		this.nodeID_ = nodeID;
		this.g_ = 0.0;
		n_nodos++;
		//System.out.println(this.nodeID_);
	}
	
	public Node returnNode() {
		return this;
	}
	
	/**
	 * 
	 * @return identificador del nodo
	 */
	public int getNodeID() {
		return this.nodeID_;
	}
	
	/**
	 * 
	 * @return 
	 */
	public double getHeuristic() {
		return this.h_;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getDistance() {
		return this.g_;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getValue() {
		
		return this.f_;
	}
	
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
	 * 
	 * @param distance
	 */
	public void setDistance(double distance) {
		this.g_ = distance;
	}
	
	/**
	 * 
	 * @param heuristic
	 */
	public void setHeuristic(double heuristic) {
		this.h_ = heuristic;
	}
	
	protected void setObjetive() {
		this.objetive_ = true;
	}
	protected boolean getObjetive() {
		return this.objetive_;
	}
	
	protected void setOrigin() {
		this.origin_ = true;
	}
	
	public String toString() {
		String cad = "";
		return cad + this.getNodeID();
	}
	
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
	
	public void setFather(Node node) {
		this.father_node = node;
	}
	
	public Node getFather() {
		return this.father_node;
	}
	
	public void addSon(Node node, double distance) {
		this.sons_nodes.add(node);
		this.sons_distances.add(distance);
	}
	
	public ArrayList<Node> getSonsList(){
		return this.sons_nodes;
	}
	public ArrayList<Double> getSonsDistances(){
		return this.sons_distances;
	}


	
	
	

}
