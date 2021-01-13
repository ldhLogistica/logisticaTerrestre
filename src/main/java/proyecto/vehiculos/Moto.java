package proyecto.vehiculos;

/**
 * Clase que define la estructura Moto que hereda de la clase Vehículo
 * @class Moto
 * @author Equipo 1
 * @see <a href="https://github.com/ldhLogistica/logisticaTerrestre/blob/master/src/main/java/proyecto/vehiculos/Moto.java"> Repositorio Github - Coche</a>
 */

public class Moto extends Vehiculo implements Runnable {

    /**
     * @brief Constructor para la creación de un objeto Moto
     * @param id
     * @param origin
     */
    public Moto(int id, int origin) {
        super(id, origin);
        extraTime = 1;
        name = "Moto";
    }

}

