package practica1.vehiculos;

import practica1.AStar;
import practica1.Graph;
import practica1.Node;

import java.io.IOException;
import java.util.ArrayList;

public class Coche implements IVehiculo{

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

        } catch (IOException e) {
            e.printStackTrace();
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

    public ArrayList<Node> getMinimunRoad(){
        return this.minimunRoad;
    }


}