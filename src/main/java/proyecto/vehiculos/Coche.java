package proyecto.vehiculos;

import proyecto.AStar;
import proyecto.Graph;
import proyecto.Node;
import proyecto.LogisticaGUI;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Coche extends Vehiculo implements Runnable {

    public Coche(int id, int origin) {
        super(id, origin);
        extraTime = 2;
        name = "Coche";
    }

}
