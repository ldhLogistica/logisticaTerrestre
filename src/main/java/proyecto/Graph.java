package proyecto;

import java.util.*;
import java.io.*;


final public class Graph {
	
	private static ArrayList<Node> node_list_ = new ArrayList<Node>();
	protected int originNodeID = 0;
	protected int finalNodeID = 0;
	protected int n_nodes_;
	private HashMap<Integer,ArrayList<Double>> distances = new HashMap<>();

	private ArrayList<Double> distancesList = new ArrayList<>();

	private static final String distancesFile = "./resources/Grafo1.txt";
	private static final String heuristicsFile = "./resources/Grafo1Heuristica1.txt";
	
	/**
	 * @param origin_node nodo de origen proporcionado en la linea de comandos
	 * @throws NumberFormatException se asegura que se esta leyendo en el formato correcto
	 * @throws IOException se asegura que no hay ningun error a la hora de leer los ficheros
	 */
	public Graph(int origin_node) throws NumberFormatException, IOException{
		System.out.println("Origen: " + origin_node);
		this.originNodeID = origin_node;
		buildDistances(distancesFile,origin_node);
		buildHeuristics(heuristicsFile);
			
	}
	
	/**
	 * Imprimir el grafo por pantalla (metodo para hacer pruebas, lo mas probable es que se acabe eliminando)
	 */
	public String toString() {
		String res = "";
		Iterator<Node> it_node = this.node_list_.iterator();
		
		Node aux = null;
		while(it_node.hasNext()) {
			aux = it_node.next();
			res += "H = " + aux.getHeuristic() + "\n";
			res += "NodoID: " + aux.getNodeID() + "\n";
			res += "Vecinos: ";
			Iterator<Node> it_neighbours = aux.getSonsList().iterator();
			Iterator<Double> it_double = aux.getSonsDistances().iterator();
			Node aux1 = null;
			while(it_neighbours.hasNext()) {
				aux1 = it_neighbours.next();
				res += aux1.getNodeID() + ": " + it_double.next() + ", ";
			}
			res+="\n\n";
			
		}
		return res;
		
	}
	
	/**
	 * 
	 * @return numero de nodos del grafo
	 */
	protected int getNNodes() {
		return this.n_nodes_;
	}

	/**
	 * 
	 * @return devuelve una tabla Hash cuyas claves son los nodos y los valores las distancias a los vecinos
	 */
	protected ArrayList<Node> getNodeList(){
		return this.node_list_;
	}
	
	/**
	 * Almacena las distancias de cada nodo con sus vecinos
	 * @param distances_file fichero que recogio el constructor por la linea de comandos
	 * @param origin_node entero que recogio el constructor desde la linea de comandos
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private void buildDistances(String distances_file, int origin_node) throws NumberFormatException, IOException {
		//System.out.println(distances_file);
		FileReader f = new FileReader(distances_file);
		BufferedReader b = new BufferedReader(f);
		this.n_nodes_ = Integer.parseInt(b.readLine());
		int index;
		for(index = 1 ; index <=this.n_nodes_ ; index ++) {
			Node node = new Node(index);
			if(index==origin_node)
				node.setOrigin();
			this.node_list_.add(node);	
		}
		double[][] distances_matrix = buildDistancesMatrix(b);
		insertDistances(distances_matrix);
		
	}
	
	/**
	 * 
	 * @param br bufer que se comunica con el fichero de distancias
	 * @return matriz de distancias
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private double[][] buildDistancesMatrix(BufferedReader br) throws NumberFormatException, IOException{
		double[][] distances_matrix = new double[getNNodes()][getNNodes()];
		
		for(int i=0 ; i<getNNodes() ; i++) {
			for(int j=0 ; j<getNNodes() ; j++) {
				if(i==j) {
					distances_matrix[i][j] = 0.0;
				}else if(i>j) {
					distances_matrix[i][j] = distances_matrix[j][i];
				}else {
					distances_matrix[i][j] = Double.parseDouble(br.readLine());
				}
				//////System.out.print(distances_matrix[i][j] + " ");
			}
			////System.out.println();
		}
		br.close();
		for(int i=0 ; i<getNNodes() ; i++){
			for(int j=0 ; j<getNNodes() ; j++){
				System.out.print(distances_matrix[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();
		ArrayList<Double> distancesAux = new ArrayList<>();
		for(int i=0 ; i<getNNodes() ; i++){
			distancesAux.clear();
			//System.out.println(distancesAux);
			for(int j=0 ; j<getNNodes() ; j++){
				distancesAux.add(distances_matrix[i][j]);
				//distances.put(i+1,distances_matrix[i][j]);
			}
			//System.out.println(distancesAux);
			this.distances.put(i+1,new ArrayList<>(distancesAux));
			//System.out.println(i + " -> " + distances);

		}

		System.out.println(distances);

		return distances_matrix;
	}
	
	/**
	 * Lee la matriz de distancias para almacenarlas en los nodos correspondientes
	 * @param distances_matrix matriz de distancias
	 */
	private void insertDistances(double[][] distances_matrix) {
		Iterator<Node> it_nodes = this.node_list_.iterator();
		Node aux = null;
		Node aux1 = null;
		
		while(it_nodes.hasNext()) {
			aux = it_nodes.next();
			int i = aux.getNodeID()-1;
			Iterator<Node> it_nodes_aux = this.node_list_.iterator();
			while(it_nodes_aux.hasNext()) {
				aux1 = it_nodes_aux.next();
				int j= aux1.getNodeID()-1;
				if(distances_matrix[i][j] > 0.0) {
					aux.addSon(aux1,distances_matrix[i][j]);
				}
				j++;
			}
			i++;
		}
	}
	
	/**
	 * Inserta a cada nodo su valor heuristico
	 * @param heuristics_file fichero que recogio el constructor desde la linea de comandos
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private void buildHeuristics(String heuristics_file) throws NumberFormatException, IOException {
		FileReader fr = new FileReader(heuristics_file);
		BufferedReader br = new BufferedReader(fr);
		int n_nodos = Integer.parseInt(br.readLine());
		Iterator<Node> it_node = this.node_list_.iterator();
		Node aux = null;
		while(it_node.hasNext()) {
			aux = it_node.next();
			System.out.println(aux);
			aux.setHeuristic(Double.parseDouble(br.readLine()));
			if(aux.getHeuristic()==0.0) {
				aux.setObjetive();
				this.finalNodeID = aux.getNodeID();
			}
		}

		br.close();
	}

	private void setDistancesList(){

	}

	public static ArrayList<Node> getNode_list(){
		return node_list_;
	}

	public HashMap<Integer,ArrayList<Double>> getDistances(){
		return this.distances;
	}
	
	
}

