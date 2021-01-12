package proyecto;

import proyecto.vehiculos.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogisticaGUI extends JPanel{
    private JTable tablaVehículos;
    private JButton botonAgregarVehículo;
    private JPanel panelLogisticaTerrestre;
    private JLabel logisticaTerrestreTxt;
    private JScrollPane scrollTablaVehículos;

    private static Map<Vehiculo,Thread> listaVehiculos = new HashMap<>();
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
        tablaVehículos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int rowPoint = tablaVehículos.rowAtPoint(e.getPoint());
                int columnPoint = 0;
                if(rowPoint > -1){
                    int idVehiculo = (int)model.getValueAt(rowPoint,columnPoint);
                    for(Map.Entry<Vehiculo,Thread> entry : listaVehiculos.entrySet()){
                        if(entry.getKey().getId()==idVehiculo){
                            new InfoVehicleFrame(entry.getKey());
                            break;
                        }
                    }
                }
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

    public static Map<Vehiculo,Thread> getListaVehiculos(){
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
