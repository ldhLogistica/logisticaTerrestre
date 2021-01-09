package practica1;

import practica1.vehiculos.Coche;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class LogisticaGUI extends JPanel{
    private JTable tablaVehículos;
    private JButton botonAgregarVehículo;
    private JPanel panelLogisticaTerrestre;
    private JLabel logisticaTerrestreTxt;
    private JScrollPane scrollTablaVehículos;

    private static ArrayList<Coche> listaCoches = new ArrayList<>();
    private static DefaultTableModel model = new DefaultTableModel();
    public static int contID;

    LogisticaGUI(){
        contID = 0;
        setHeaders();
        botonAgregarVehículo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarCocheGUIFrame();
            }
        });
    }
    private void setHeaders() {
        this.tablaVehículos = new JTable(model);
        scrollTablaVehículos.setViewportView(tablaVehículos);
        model.addColumn("ID");
        model.addColumn("Tipo");
        model.addColumn("Ubicación");
    }

    public static ArrayList<Coche> getListaCoches(){
        return listaCoches;
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
