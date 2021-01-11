package proyecto;

import proyecto.vehiculos.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogisticaGUI extends JPanel{
    private JTable tablaVehículos;
    private JButton botonAgregarVehículo;
    private JPanel panelLogisticaTerrestre;
    private JLabel logisticaTerrestreTxt;
    private JScrollPane scrollTablaVehículos;

    private static ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private static DefaultTableModel model = new DefaultTableModel();
    public static int contID;

    LogisticaGUI(){
        contID = 0;
        setHeaders();
        botonAgregarVehículo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarVehiculoGUIFrame();
            }
        });
    }
    private void setHeaders() {
        this.tablaVehículos = new JTable(model);
        scrollTablaVehículos.setViewportView(tablaVehículos);
        model.addColumn("ID");
        model.addColumn("Tipo");
        model.addColumn("Distancia Línea Recta");
        model.addColumn("Distancia Carretera");
        model.addColumn("Ubicación");

    }

    public static ArrayList<Vehiculo> getListaVehiculos(){
        return listaVehiculos;
    }

    public static DefaultTableModel getModel(){
        return model;
    }

    public static int getContId(){
        return contID;
    }

    public JPanel getPanelLogisticaTerrestre(){
        return this.panelLogisticaTerrestre;
    }

}

class LogisticaGUIFrame extends JFrame{
    LogisticaGUI logisticaGUI = new LogisticaGUI();
    LogisticaGUIFrame(){
        super("Logística Terrestre");
        setContentPane(logisticaGUI.getPanelLogisticaTerrestre());
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
