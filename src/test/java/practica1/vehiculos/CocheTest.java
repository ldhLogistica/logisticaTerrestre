package practica1.vehiculos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import proyecto.vehiculos.Coche;

import static org.junit.jupiter.api.Assertions.*;

class CocheTest {

    private Coche coche;

    @BeforeEach
    void setUp(){
        coche = new Coche(1,1);
    }
    @Test
    public void testIdCoche(){
        assertEquals(1,coche.getId());
    }
}

