package proyecto;

import proyecto.vehiculos.Camion;
import proyecto.vehiculos.Coche;
import proyecto.vehiculos.Moto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgregarVehiculoGUI extends JPanel{
    private JComboBox cbType;
    private JComboBox cbInicio;
    private static ArrayList<String> optTypesList = new ArrayList<>();
    private static ArrayList<Integer> optOriginsList = new ArrayList<>();
    private JPanel panelAgregarVehiculo;
    private JButton agregarVehículoButton;

    AgregarVehiculoGUI(){

        fillComboBoxes();
        agregarVehículoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean idAceptado = true;
                Graph.getNode_list().clear();

                LogisticaGUI.contID += 1;
                Object[] datosNuevoVehiculo = new Object[5];

                if(cbType.getSelectedIndex() == 0) {
                    Coche coche = new Coche(LogisticaGUI.contID+1,Integer.parseInt(cbInicio.getSelectedItem().toString()));
                    datosNuevoVehiculo[0] = coche.getId();
                    datosNuevoVehiculo[1] = cbType.getSelectedItem().toString();
                    datosNuevoVehiculo[2] = "";
                    datosNuevoVehiculo[3] = "";
                    datosNuevoVehiculo[4] = coche.getOrigin();
                    LogisticaGUI.getModel().addRow(datosNuevoVehiculo);
                    Thread hiloVehiculo = new Thread(coche);
                    LogisticaGUI.getListaVehiculos().put(coche,hiloVehiculo);
                    hiloVehiculo.start();
                } else if (cbType.getSelectedIndex() == 1) {
                    Camion camion = new Camion(LogisticaGUI.contID+1,Integer.parseInt(cbInicio.getSelectedItem().toString()));
                    datosNuevoVehiculo[0] = camion.getId();
                    datosNuevoVehiculo[1] = cbType.getSelectedItem().toString();
                    datosNuevoVehiculo[2] = "";
                    datosNuevoVehiculo[3] = "";
                    datosNuevoVehiculo[4] = camion.getOrigin();
                    LogisticaGUI.getModel().addRow(datosNuevoVehiculo);
                    Thread hiloVehiculo = new Thread(camion);
                    LogisticaGUI.getListaVehiculos().put(camion,hiloVehiculo);
                    hiloVehiculo.start();
                } else {
                    Moto moto = new Moto(LogisticaGUI.contID+1,Integer.parseInt(cbInicio.getSelectedItem().toString()));
                    datosNuevoVehiculo[0] = moto.getId();
                    datosNuevoVehiculo[1] = cbType.getSelectedItem().toString();
                    datosNuevoVehiculo[2] = "";
                    datosNuevoVehiculo[3] = "";
                    datosNuevoVehiculo[4] = moto.getOrigin();
                    LogisticaGUI.getModel().addRow(datosNuevoVehiculo);
                    Thread hiloVehiculo = new Thread(moto);
                    LogisticaGUI.getListaVehiculos().put(moto,hiloVehiculo);
                    hiloVehiculo.start();
                }
            }
        });
    }

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

    private void fillOptTypeList(){
        optTypesList.clear();
        optTypesList.add("Coche");
        optTypesList.add("Camión");
        optTypesList.add("Moto");
    }

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
