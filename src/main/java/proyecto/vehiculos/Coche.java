package proyecto.vehiculos;

/**
 * Clase que define la estructura Coche que hereda de la clase Vehículo
 * @class Coche
 * @author Equipo 1
 * @see <a href="https://github.com/ldhLogistica/logisticaTerrestre/blob/master/src/main/java/proyecto/vehiculos/Coche.java"> Repositorio Github - Coche</a>
 */

public class Coche extends Vehiculo implements Runnable {

    /**
     * @brief Constructor para la creación de un objeto Coche
     * @param id
     * @param origin
     */

    public Coche(int id, int origin) {
        super(id, origin);
        extraTime = 2;
        name = "Coche";
    }

}
