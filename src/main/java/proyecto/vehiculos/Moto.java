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
        extraTime = 1;
        name = "Moto";
    }

}

