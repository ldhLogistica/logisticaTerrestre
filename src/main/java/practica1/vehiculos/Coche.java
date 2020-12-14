package practica1.vehiculos;

public class Coche implements IVehiculo{

    private int id;
    private int origin;
    private int goal;
    private int position;

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
}
