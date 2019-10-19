package practica1;

import java.util.*;
import java.io.*;

final public class Graph {
	
	//Lista en la que cada posición representa un par en el que el primer elemento es un nodo y el segundo elemento las distancias con los vecinos
	private LinkedHashMap<Node,ArrayList<Double>> node_list_ = new LinkedHashMap<Node,ArrayList<Double>>(); 
	
	private Node origin_node_;
	private int n_nodes_; //Numero de nodos
	
	Graph(String distances_file, String heuristics_file, int origin_node) throws NumberFormatException, IOException{
		try {
			
			
			FileReader f = new FileReader(distances_file);
			BufferedReader b = new BufferedReader(f);
			
			this.n_nodes_ = Integer.parseInt(b.readLine());
			int index;
			Node aux2 = null;
			for(index = 1 ; index <=this.n_nodes_ ; index ++) {
				Node node1 = new Node(index);
				if(index==origin_node)
					node1.setOrigin();
				this.node_list_.put(node1, new ArrayList<Double>());
				
			}
			
			
			double[][] distances_matrix = new double[this.getNNodes()][this.getNNodes()];
			
			for(int i=0 ; i<this.getNNodes() ; i++) {
				for(int j=0 ; j<this.getNNodes() ; j++) {
					if(i==j) {
						distances_matrix[i][j] = 0.0;
					}else if(i>j) {
						distances_matrix[i][j] = distances_matrix[j][i];
					}else {
						distances_matrix[i][j] = Double.parseDouble(b.readLine());
					}
					
					System.out.print(distances_matrix[i][j] + " ");
						
				}
				System.out.println();
			}
			
			b.close();
			
			Iterator<Node> it = this.node_list_.keySet().iterator();
			Node aux = null;
			int i=0;
			while(it.hasNext()) {
				aux = it.next();
				for(int j=0 ; j<this.getNNodes() ; j++) {
						this.node_list_.get(aux).add(distances_matrix[i][j]);
				}
				i++;
			}
			
			
			//LECTURA DEL FICHERO DE HEURISTICAS
			FileReader fr = new FileReader(heuristics_file);
			BufferedReader br = new BufferedReader(fr);
			int n_nodos = Integer.parseInt(br.readLine());
			
			Iterator<Node> it_node_list = this.node_list_.keySet().iterator();
			Node aux1 = null;
			while(it_node_list.hasNext()) {
				aux1 = it_node_list.next();
				aux1.setHeuristic(Double.parseDouble(br.readLine()));
				if(aux1.getHeuristic()==0.0)
					aux1.setObjetive();
			}
			br.close();
			
			
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String res = "";
		Iterator<Node> it = this.node_list_.keySet().iterator();
		Iterator<Double> it_a = new ArrayList<Double>().iterator();
		
		Node aux = null;
		while(it.hasNext()) {
			aux = it.next();
			res += "H = " + aux.getHeuristic() + "\n";
			res += "NodoID: " + aux.getNodeID() + "\n";
			//System.out.println(res);
			it_a = this.node_list_.get(aux).iterator();
			//System.out.println(this.node_list_.get(aux).size());
			while(it_a.hasNext()) {
				//System.out.println("Die Die Die!!");
				res += it_a.next() + "\n";
			}
			res += "\n--------------------------\n";
		}
		return res;
		
	}
	
	protected int getNNodes() {
		return this.n_nodes_;
	}
	protected LinkedHashMap<Node,ArrayList<Double>> getNodeList(){
		return this.node_list_;
	}
	
	/*protected Node getOrigin() {
		return ;
	}*/
	

}

/*NOTA: para calcular cuantas distancias hay que poner a cada nodo, calculamos n_nodos - ID del nodo*/
