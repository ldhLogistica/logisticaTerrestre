package proyecto.vehiculos;

import proyecto.AStar;
import proyecto.Graph;
import proyecto.LogisticaGUI;
import proyecto.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Camion extends Vehiculo{

    public Camion(int id, int origin) {
        super(id, origin);
        extraTime = 3;
    }


}
