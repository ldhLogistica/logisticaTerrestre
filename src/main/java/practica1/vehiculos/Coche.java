package practica1.vehiculos;

/*import practica1.AStar;
import practica1.Graph;
import practica1.LogisticaGUI;*/
import practica1.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Coche implements IVehiculo{

    private int id;
    private int origin;
    private int goal;
    private int position;

    //private AStar tree;
    //private Graph map;
    private ArrayList<Node> minimunRoad = new ArrayList<>();

    public Coche(int id, int origin){
        this.id = id;
        this.origin = origin;
        //create(this.id);
        //setOrigin(this.origin);
       /* try {
            this.map = new Graph(this.origin);
            this.tree = new AStar(map);
            this.minimunRoad = tree.minimun_road;
            System.out.println("Cochde creado");

        } catch (IOException e) {
            e.printStackTrace();
        }*/
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


   /* @Override
    public void run() {
        HashMap<Integer,ArrayList<Double>> distances = this.map.getDistances();
        this.position = minimunRoad.get(0).getNodeID();

        for(int i=0 ; i<minimunRoad.size()-1 ; i++){
            try {
                Thread.sleep((int)Math.round(distances.get(this.minimunRoad.get(i).getNodeID()).get(this.minimunRoad.get(i+1).getNodeID()-1)*1000));
                this.position = minimunRoad.get(i+1).getNodeID();
                Object[] newData = new Object[3];
                newData[0] = this.getId();
                newData[1] = "coche";
                newData[2] = this.position;
                //LogisticaGUI.getModel().setValueAt(newData[2],0,2);
                for(int j=0 ; j<LogisticaGUI.getModel().getRowCount() ; j++){

                    if((int)LogisticaGUI.getModel().getValueAt(j,0)==this.getId()){
                        LogisticaGUI.getModel().setValueAt(newData[2],j,2);
                    }
                }
                System.out.println("Posicion -> " + this.position);
                System.out.println("hola");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
