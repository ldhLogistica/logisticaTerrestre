package proyecto.vehiculos;

import proyecto.AStar;
import proyecto.Graph;
import proyecto.LogisticaGUI;
import proyecto.Node;


import java.io.IOException;
import java.util.ArrayList;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que define la estructura Vehículo que implementa Runnable (Libería para crear hilos).
 * Esta clase será la que herederan todos los vehículos usados en la aplicación.
 * @class Vehiculo
 * @author Equipo 1
 * @see <a href="https://github.com/ldhLogistica/logisticaTerrestre/blob/master/src/main/java/proyecto/vehiculos.java"> Repositorio Github - Coche</a>
 */

public abstract class Vehiculo implements Runnable{

    /**
     * @brief variables de la clase
     */
    protected int id;
    protected int origin;
    protected int goal;
    protected int position;
    protected double straightLineDistance;
    protected double roadDistance;

    private static final Logger LOGGER = Logger.getLogger(Vehiculo.class.getName());

    protected AStar tree;
    protected Graph map;
    protected ArrayList<Node> minimunRoad = new ArrayList<>();

    protected int extraTime;
    protected String name;

    /**
     * @brief Constructor para la creación de un objeto Vehiculo
     * @param id del vehículo
     * @param origin punto de origen
     */
    public Vehiculo (int id, int origin) {
        this.id = id;
        this.origin = origin;
        create(this.id);
        setOrigin(this.origin);
        try {
            this.map = new Graph(this.origin);
            this.tree = new AStar(map);
            this.minimunRoad = tree.minimun_road;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Error al crear coche " + e);
        }
    }

    /**
     * @brief método create destinado a crear el vehículo con la id introducida por parámetro
     * @param id identificador de vehiculo
     */
    public void create(int id) {
        this.id = id;
    }

    /**
     * @brief método setOrigen encargado de asignar un punto de origen
     * @param origin
     */
    public void setOrigin(int origin) {
        this.origin = origin;
    }

    /**
     * @brief método getId que devuelve la id del vehículo
     * @return id del vehículo
     */
    public int getId() {
        return this.id;
    }

    /**
     * @brief método getOrigin que devuelve el origen del vehículo
     * @return origen del vehículo
     */
    public int getOrigin() {
        return this.origin;
    }

    /**
     * @brief método setStraightLineDistance que asigna distancia en línea recta
     * @param sld
     */
    public void setStraightLineDistance(double sld){
        this.straightLineDistance = sld;
    }

    /**
     * @brief método setStraightLineDistance que retorna la distancia en línea recta
     * @return distancia en línea recta
     */
    public double getStraightLineDistance(){
        return this.straightLineDistance;
    }

    /**
     * @brief método setRoadDistance que asigna la distancia en carretera
     * @param rd
     */
    public void setRoadDistance(double rd){
        this.roadDistance = rd;
    }

    /**
     * @brief método getRoadDistance que retorna la distancia en carretera
     * @return distancia en carretera
     */
    public double getRoadDistance(){
        return this.roadDistance;
    }

    /**
     * @brief método getName que retorna el nombre del vehículo
     * @return nombre del vehículo
     */
    public String getName(){
        return this.name;
    }

    /**
     * @brief método que retorna el tiempo extra que tarda un vehículo concreto con respecto al resto
     * @return valor extra de tiempo
     */
    public int getExtraTime(){
        return this.extraTime;
    }

    /**
     * @brief método newPosition que genera una actualización de la posición del vehículo
     * @param newPosition
     */
    public void updateData(Object[] newPosition){
        for(int fila = 0; fila< LogisticaGUI.getModel().getRowCount() ; fila++){

            if((int)LogisticaGUI.getModel().getValueAt(fila,0)==this.getId()){
                LogisticaGUI.getModel().setValueAt(newPosition[0],fila,2);
                LogisticaGUI.getModel().setValueAt(newPosition[1],fila,3);
                LogisticaGUI.getModel().setValueAt(newPosition[2],fila,4);
                break;
            }
        }
    }

    /**
     * @brief método run que en este caso implementa la ejecución de un hilo en el que se calculan los datos de cada vehículo
     */
    @Override
    public void run() {
        Map<Integer,ArrayList<Double>> distances = this.map.getDistances();
        this.position = minimunRoad.get(0).getNodeID();

        for(int i=0 ; i<minimunRoad.size()-1 ; i++){
            try {
                Thread.sleep((int)Math.round(distances.get(this.minimunRoad.get(i).getNodeID()).get(this.minimunRoad.get(i+1).getNodeID()-1)*1000*extraTime));

                setStraightLineDistance(minimunRoad.get(i+1).getHeuristic());
                setRoadDistance(this.tree.getDistance() - minimunRoad.get(i+1).getDistance());
                this.position = minimunRoad.get(i+1).getNodeID();

                Object[] newData = new Object[3];
                newData[0] = straightLineDistance;
                newData[1] = roadDistance;
                if(!minimunRoad.get(i+1).isObjetive()){
                    newData[2] = this.position;
                }else{
                    newData[2] = "FINALIZADO";
                }

                updateData(newData);

            } catch (InterruptedException e) {
                // Restore interrupted state...
                Thread.currentThread().interrupt();
            }
        }
    }
}
