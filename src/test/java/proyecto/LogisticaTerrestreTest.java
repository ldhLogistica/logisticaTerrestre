package proyecto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import proyecto.vehiculos.Coche;
import proyecto.vehiculos.Vehiculo;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Logistica Terrestre")
public class LogisticaTerrestreTest {

    @DisplayName("Testeando medios de transporte")
    @Nested
    class VehiculoTest{
        private Vehiculo coche;

        @BeforeEach
        void setUp(){
            coche = new Coche(1,1);

        }

        @Test
        @DisplayName("Testeando getter de ID de Vehículo")
        void testGetId(){
            assertEquals(1,coche.getId());
        }

        @Test
        @DisplayName("Testeando getter de origen de Vehiculo")
        void testGetOrigen(){
            assertEquals(1,coche.getOrigin());
        }

        @Test
        @DisplayName("Testeando getter de nombre de Vehiculo")
        void testGetName(){
            assertEquals("Coche",coche.getName());
        }

        @Test
        @DisplayName("Testeando getter de extraTime de Vehiculo")
        void testGetExtraTime(){
            assertEquals(2,coche.getExtraTime());
        }

    }
}
