package proyecto;

import proyecto.vehiculos.Vehiculo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoVehicleGUI extends JPanel{
    private JButton botonEliminarVehiculo;
    private JPanel panelInfoVehiculo;
    private JLabel infoVehiculoTitle;
    private JLabel idTxt;
    private JLabel tipoTxt;
    private JLabel lineaRectaTxt;
    private JLabel carreteraTxt;
    private JLabel idInfoTxt;
    private JLabel typeInfoTxt;
    private JLabel straightLineInfoTxt;
    private JLabel roadInfoTxt;

    private Vehiculo vehiculo;
    private JFrame frame;


    InfoVehicleGUI(Vehiculo v, JFrame frame){
        this.frame = frame;
        this.vehiculo = v;
        idInfoTxt.setText(String.valueOf(v.getId()));
        typeInfoTxt.setText(v.getClass().getName());
        //Queda imprimir distancias actuales al objetivo
        straightLineInfoTxt.setText(String.valueOf(v.getStraightLineDistance()));
        roadInfoTxt.setText(String.valueOf(v.getRoadDistance()));

        botonEliminarVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int fila = 0; fila< LogisticaGUI.getModel().getRowCount() ; fila++){

                    if((int)LogisticaGUI.getModel().getValueAt(fila,0)== vehiculo.getId()){
                        LogisticaGUI.getModel().removeRow(fila);
                        LogisticaGUI.getListaVehiculos().get(vehiculo).stop();
                        LogisticaGUI.getListaVehiculos().remove(vehiculo);
                        frame.dispose();
                    }
                }
            }
        });
    }

    public JPanel getPanelInfoVehiculo(){
        return this.panelInfoVehiculo;
    }
}

class InfoVehicleFrame extends JFrame{
    private InfoVehicleGUI infoVehicleGUI;
    InfoVehicleFrame(Vehiculo v){
        super("Info Vehículo");
        infoVehicleGUI = new InfoVehicleGUI(v,this);
        setContentPane(infoVehicleGUI.getPanelInfoVehiculo());
        setSize(450,250);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
