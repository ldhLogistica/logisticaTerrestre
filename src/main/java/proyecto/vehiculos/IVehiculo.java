package proyecto.vehiculos;

public interface IVehiculo {

    void create(int id);
    void delete(int id);
    void setOrigin(int origin);
    void setGoal(int goal);
    int getPosition();
    int getId();
    int getOrigin();


}
