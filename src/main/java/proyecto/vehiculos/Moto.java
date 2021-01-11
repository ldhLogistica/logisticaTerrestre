package proyecto.vehiculos;

import proyecto.AStar;
import proyecto.Graph;
import proyecto.LogisticaGUI;
import proyecto.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Moto extends Vehiculo implements Runnable {

    public Moto(int id, int origin) {
        super(id, origin);
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

