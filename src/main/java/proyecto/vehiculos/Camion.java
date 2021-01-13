package proyecto.vehiculos;

/**
 * Clase que define la estructura Camión que hereda de la clase Vehículo
 * @class Camion
 * @author Equipo 1
 * @see <a href="https://github.com/ldhLogistica/logisticaTerrestre/blob/master/src/main/java/proyecto/vehiculos/Camion.java"> Repositorio Github - Coche</a>
 */

public class Camion extends Vehiculo{

    /**
     * @brief Constructor para la creación de un objeto Camión
     * @param id
     * @param origin
     */
    public Camion(int id, int origin) {
        super(id, origin);
        extraTime = 3;
        name = "Camion";
    }


}
