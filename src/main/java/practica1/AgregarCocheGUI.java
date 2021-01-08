package practica1;

import practica1.vehiculos.Coche;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgregarCocheGUI extends JPanel{
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private static ArrayList<String> optTypesList = new ArrayList<>();
    private static ArrayList<Integer> optOriginsList = new ArrayList<>();
    private JTextField textField1;
    private JPanel panelAgregarVehiculo;
    private JButton agregarVehículoButton;

    AgregarCocheGUI(){

        fillComboBoxes();
        agregarVehículoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean idAceptado = true;
                int cocheId = Integer.parseInt(textField1.getText());

                for(Coche c : LogisticaGUI.getListaCoches()){
                    if(cocheId == c.getId()){
                        idAceptado = false;
                        break;
                    }
                }
                if(!idAceptado){
                    JOptionPane.showMessageDialog(null,"Identificador del coche repetido");
                }else{
                    Graph.getNode_list().clear();
                    Coche coche = new Coche(cocheId,Integer.parseInt(comboBox2.getSelectedItem().toString()));

                    Object[] datosNuevoVehiculo = new Object[3];
                    datosNuevoVehiculo[0] = coche.getId();
                    datosNuevoVehiculo[1] = comboBox1.getSelectedItem().toString();
                    datosNuevoVehiculo[2] = coche.getOrigin();
                    LogisticaGUI.getListaCoches().add(coche);
                    LogisticaGUI.getModel().addRow(datosNuevoVehiculo);
                    Thread hiloCoche = new Thread(coche);
                    hiloCoche.start();
                }
            }
        });
    }

    private void fillComboBoxes(){

        fillOptTypeList();
        fillOptOriginsList();

        for(String type : optTypesList){
            comboBox1.addItem(type);
        }

        for(int id : optOriginsList){
            comboBox2.addItem(id);
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
        if(Graph.getNode_list().size()==0){
            for (int i=1 ; i<=15 ; i++){
                optOriginsList.add(i);
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

class AgregarCocheGUIFrame extends JFrame{
    AgregarCocheGUI agregarCocheGUI = new AgregarCocheGUI();
    AgregarCocheGUIFrame(){
        super("Agregar Vehículo");
        setContentPane(agregarCocheGUI.getPanelAgregarVehiculo());
        setSize(600,400);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
