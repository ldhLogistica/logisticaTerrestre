package proyecto.vehiculos;

import proyecto.AStar;
import proyecto.Graph;
import proyecto.Node;
import proyecto.LogisticaGUI;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Coche implements IVehiculo, Runnable{

    private int id;
    private int origin;
    private int goal;
    private int position;

    private AStar tree;
    private Graph map;
    private ArrayList<Node> minimunRoad = new ArrayList<>();

    public Coche(int id, int origin){
        this.id = id;
        this.origin = origin;
        create(this.id);
        setOrigin(this.origin);
        try {
            this.map = new Graph(this.origin);
            this.tree = new AStar(map);
            this.minimunRoad = tree.minimun_road;
            System.out.println("Cochde creado");

        } catch (IOException e) {
            
        }
    }

    @Override
    public void create(int id) {
        this.id = id;
    }

    @Override
    public void delete(int id) {
        //Borrar del array delcarado en AStar
    }

    @Override
    public void setOrigin(int origin) {
        this.origin = origin;
    }

    @Override
    public void setGoal(int goal) {
        this.goal = goal;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getOrigin() {
        return this.origin;
    }

    public ArrayList<Node> getMinimunRoad(){
        return this.minimunRoad;
    }

    public void updateData(Object[] newPosition){
        for(int fila=0 ; fila<LogisticaGUI.getModel().getRowCount() ; fila++){

            if((int)LogisticaGUI.getModel().getValueAt(fila,0)==this.getId()){
                LogisticaGUI.getModel().setValueAt(newPosition[0],fila,2);
                LogisticaGUI.getModel().setValueAt(newPosition[1],fila,3);
                LogisticaGUI.getModel().setValueAt(newPosition[2],fila,4);
                break;
            }
        }
    }


    @Override
    public void run() {
        HashMap<Integer,ArrayList<Double>> distances = this.map.getDistances();
        this.position = minimunRoad.get(0).getNodeID();

        for(int i=0 ; i<minimunRoad.size()-1 ; i++){
            try {
                Thread.sleep((int)Math.round(distances.get(this.minimunRoad.get(i).getNodeID()).get(this.minimunRoad.get(i+1).getNodeID()-1)*1000));

                double straightLineDistance = minimunRoad.get(i+1).getHeuristic();
                double roadDistance = this.tree.getDistance() - minimunRoad.get(i+1).getDistance();
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
