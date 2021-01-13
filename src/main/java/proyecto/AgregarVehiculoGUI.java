package proyecto;

import proyecto.vehiculos.Camion;
import proyecto.vehiculos.Coche;
import proyecto.vehiculos.Moto;
import proyecto.vehiculos.Vehiculo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clase que define la estructura AgregarVehiculoGUI.
 * Implementa la funcionalidad del formulario destinado para agregar vehículos
 * @class AgregarVehiculoGUI
 * @author Equipo 1
 * @see <a href="https://github.com/ldhLogistica/logisticaTerrestre/blob/master/src/main/java/proyecto/AgregarVehiculoGUI.java"> Repositorio Github - AgregarVehiculoGUI</a>
 */
public class AgregarVehiculoGUI extends JPanel{
    /**
     * @brief variables de la clase
     */
    private JComboBox cbType;
    private JComboBox cbInicio;
    private static ArrayList<String> optTypesList = new ArrayList<>();
    private static ArrayList<Integer> optOriginsList = new ArrayList<>();
    private JPanel panelAgregarVehiculo;
    private JButton agregarVehículoButton;

    /**
     * @brief Constructor para agregar vehículo
     */
    AgregarVehiculoGUI(){

        fillComboBoxes();
        agregarVehículoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graph.getNode_list().clear();
                LogisticaGUI.contID += 1;
                if(cbType.getSelectedIndex() == 0) {
                    agregarVehiculo(new Coche(LogisticaGUI.contID+1,Integer.parseInt(cbInicio.getSelectedItem().toString())));
                } else if (cbType.getSelectedIndex() == 1) {
                    agregarVehiculo(new Camion(LogisticaGUI.contID+1,Integer.parseInt(cbInicio.getSelectedItem().toString())));
                } else {
                    agregarVehiculo(new Moto(LogisticaGUI.contID+1,Integer.parseInt(cbInicio.getSelectedItem().toString())));
                }
            }
        });
    }

    /**
     * @brief método que agrega un vehículo concreto a la rejilla
     * @param v vehículo
     */
    private void agregarVehiculo(Vehiculo v){
        Object[] datosNuevoVehiculo = new Object[5];
        datosNuevoVehiculo[0] = v.getId();
        datosNuevoVehiculo[1] = cbType.getSelectedItem().toString();
        datosNuevoVehiculo[2] = "";
        datosNuevoVehiculo[3] = "";
        datosNuevoVehiculo[4] = v.getOrigin();
        LogisticaGUI.getModel().addRow(datosNuevoVehiculo);
        Thread hiloVehiculo = new Thread(v);
        LogisticaGUI.getListaVehiculos().put(v,hiloVehiculo);
        hiloVehiculo.start();
    }

    /**
     * @brief método que rellena los combos con la información de los nodos de la lista
     */
    private void fillComboBoxes(){

        fillOptTypeList();
        fillOptOriginsList();
        System.out.println(optOriginsList);
        for(String type : optTypesList){
            this.cbType.addItem(type);
        }

        for(int id : optOriginsList){
            cbInicio.addItem(id);
        }

    }

    /**
     * @brief método que rellena los tipos de vehículos a elegir
     */
    private void fillOptTypeList(){
        optTypesList.clear();
        optTypesList.add("Coche");
        optTypesList.add("Camión");
        optTypesList.add("Moto");
    }

    /**
     * @brief método que rellena la lista de nodos
     */
    private void fillOptOriginsList(){
        optOriginsList.clear();
        if(Graph.getNode_list().size() == 0 ){
            for (int node = 1 ; node <= 15 ; node++) {
                if (node != 8) {
                    optOriginsList.add(node);
                }
            }
        }
        for(int i=0 ; i< Graph.getNode_list().size() ; i++){
            optOriginsList.add(Graph.getNode_list().get(i).getNodeID());
        }
    }

    public JPanel getPanelAgregarVehiculo(){
        return this.panelAgregarVehiculo;
    }
}

/**
 * @class Clase que da forma a la vista del formulario
 */
class AgregarVehiculoGUIFrame extends JFrame{
    AgregarVehiculoGUI agregarVehiculoGUI = new AgregarVehiculoGUI();
    AgregarVehiculoGUIFrame(){
        super("Agregar Vehículo");
        setContentPane(agregarVehiculoGUI.getPanelAgregarVehiculo());
        setSize(450,250);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
