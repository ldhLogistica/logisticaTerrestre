package proyecto.vehiculos;

import proyecto.AStar;
import proyecto.Graph;
import proyecto.LogisticaGUI;
import proyecto.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Vehiculo {

    protected int id;
    protected int origin;
    protected int goal;
    protected int position;

    protected AStar tree;
    protected Graph map;
    protected ArrayList<Node> minimunRoad = new ArrayList<>();

    public Vehiculo (int id, int origin) {
        this.id = id;
        this.origin = origin;
        create(this.id);
        setOrigin(this.origin);
        try {
            this.map = new Graph(this.origin);
            this.tree = new AStar(map);
            this.minimunRoad = tree.minimun_road;
            System.out.println("Vehiculo creado");

        } catch (IOException e) {

        }
    }

    public void create(int id) {
        this.id = id;
    }

    public void delete(int id) {
        //Borrar del array delcarado en AStar
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getPosition() {
        return this.position;
    }

    public int getId() {
        return this.id;
    }

    public int getOrigin() {
        return this.origin;
    }

    public ArrayList<Node> getMinimunRoad(){
        return this.minimunRoad;
    }

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
}
