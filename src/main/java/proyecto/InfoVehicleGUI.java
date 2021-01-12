package proyecto;

import javax.swing.*;

public class InfoVehicleGUI extends JPanel{
    private JButton botonEliminarVehiculo;
    private JPanel panelInfoVehiculo;
    private JLabel infoVehiculoTitle;
    private JLabel idTxt;
    private JLabel tipoTxt;
    private JLabel lineaRectaTxt;
    private JLabel carreteraTxt;


    InfoVehicleGUI(){

    }

    public JPanel getPanelInfoVehiculo(){
        return this.panelInfoVehiculo;
    }
}

class InfoVehicleFrame extends JFrame{
    private InfoVehicleGUI infoVehicleGUI = new InfoVehicleGUI();
    InfoVehicleFrame(){
        super("Info Vehículo");
        setContentPane(infoVehicleGUI.getPanelInfoVehiculo());
        setSize(450,250);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
