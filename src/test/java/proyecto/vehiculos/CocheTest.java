package proyecto.vehiculos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CocheTest {

    private Coche coche;

    @BeforeEach
    void setUp(){
        coche = new Coche(1,1);
    }

    @Test
    public void testGetId(){
        assertEquals(1,coche.getId());
    }

    @Test
    public void testGetOrigin(){
        assertEquals(1,coche.getOrigin());
    }
}