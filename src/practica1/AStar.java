package practica1;

import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class AStar {
	
	private Graph graph_;
	ArrayList<Node> minimun_walk = new ArrayList<Node>();
	
	AStar(Graph graph){
		this.graph_ = graph;
		run();
	}
	
	protected void run() {
		
	}
	
	private ArrayList<Node> generateSons(Node node) {
		ArrayList<Node> sonsList = new ArrayList<Node>();
		ArrayList<Node> neighbours_list = new ArrayList<Node>();
		Iterator<Node> it = this.graph_.getNodeList().keySet().iterator();
		Node aux = null;
		boolean encontrado = false;
		while(it.hasNext() && !encontrado) {
			aux = it.next();
			if(node.getNodeID()==aux.getNodeID()) {
				encontrado = true;
			}
		}
		Iterator<Double> it_distances = this.graph_.getNodeList().get(aux).iterator();
		Double aux1;
		while(it_distances.hasNext()) {
			aux1 = it_distances.next();
			if(this.graph_ != 0.0 && it_distances.next())
		}
	}
	
	public String toString() {
		
		return this.graph_.toString();
	}

}
