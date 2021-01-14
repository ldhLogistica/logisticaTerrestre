package proyecto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import proyecto.vehiculos.Coche;
import proyecto.vehiculos.Vehiculo;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

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

        @Test
        @DisplayName("Testeando setter de straightLIne")
        void testSetStraightLineDistance(){
            coche.setStraightLineDistance(2.0);
            assertEquals(2.0,coche.getStraightLineDistance());
        }

        @Test
        @DisplayName("Testeando setter de straightLIne")
        void testSetRoadDistance(){
            coche.setRoadDistance(2.0);
            assertEquals(2.0,coche.getRoadDistance());
        }

    }

    @DisplayName("Testeando clase Grapg")
    @Nested
    class GraphTest{
        private Graph graph;

        @BeforeEach
        void setUp(){
            try {
                graph = new Graph(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Test
        @DisplayName("Testeando getter de node_list")
        void testGetNodeList(){
            ArrayList<Node> list = new ArrayList<>();
            for (int i=1 ; i<=graph.getNodeList().size() ; i++){
                list.add(new Node(i));
            }
            for(int i=0 ; i<graph.getNodeList().size() ; i++){
                assertEquals(list.get(i).getNodeID(),graph.getNodeList().get(i).getNodeID());
            }
        }

        @Test
        @DisplayName("Testeando getter de node_list")
        void testGetNode_list(){
            ArrayList<Node> list = new ArrayList<>();
            for (int i=1 ; i<=Graph.getNode_list().size() ; i++){
                list.add(new Node(i));
            }
            for(int i=0 ; i<Graph.getNode_list().size() ; i++){
                assertEquals(list.get(i).getNodeID(),Graph.getNode_list().get(i).getNodeID());
            }
        }


    }

   /*@DisplayName("Testeando clase LogisticaGUI")
    @Nested
    class TestLogisticaGUI{

        JFrame ventana1;

        @BeforeEach
        void setUp(){
            ventana1 = new LogisticaGUIFrame();
        }

        @Test
        @DisplayName("Testeando constructor de LogisticaGUI")
        void testLogisticaGUI(){
            JFrame ventasna2 = ventana1;
            assertEquals(ventana1,ventasna2);
        }

    }

    @DisplayName("Testeando clase AgregarVehiculoGUI")
    @Nested
    class TestAgregarVehiculoGUI{

        JFrame ventana1;

        @BeforeEach
        void setUp(){
            ventana1 = new AgregarVehiculoGUIFrame();
        }

        @Test
        @DisplayName("Testeando constructor de AgregarVehiculoGUI")
        void testLogisticaGUI(){
            JFrame ventasna2 = ventana1;
            assertEquals(ventana1,ventasna2);
        }

    }

    @DisplayName("Testeando clase LogisticaGUI")
    @Nested
    class TestInfoVehicleGUI{

        JFrame ventana1;


        @BeforeEach
        void setUp(){
            ventana1 = new InfoVehicleFrame(new Coche(1,1));
        }

        @Test
        @DisplayName("Testeando constructor de LogisticaGUI")
        void testLogisticaGUI(){
            JFrame ventasna2 = ventana1;
            assertEquals(ventana1,ventasna2);
        }

    }*/


}
