package proyecto;

import proyecto.vehiculos.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que define la estructura LogisticaGUI.
 * Implementa la funcionalidad y las variaciones en la rejilla
 * @class AgregarVehiculoGUI
 * @author Equipo 1
 * @see <a href="https://github.com/ldhLogistica/logisticaTerrestre/blob/master/src/main/java/proyecto/LogisticaGUI.java"> Repositorio Github - LogisticaGUI</a>
 */
public class LogisticaGUI extends JPanel{
    /**
     * @brief variables de la clase
     */
    private JTable tablaVehículos;
    private JButton botonAgregarVehículo;
    private JPanel panelLogisticaTerrestre;
    private JLabel logisticaTerrestreTxt;
    private JScrollPane scrollTablaVehículos;

    private static Map<Vehiculo,Thread> listaVehiculos = new HashMap<>();
    private static DefaultTableModel model = new DefaultTableModel();
    public static int contID;

    /**
     * @brief Constructor para agregar funciones a la rejilla
     */
    LogisticaGUI(){
        contID = 0;
        setHeaders();
        botonAgregarVehículo.addActionListener(e -> new AgregarVehiculoGUIFrame());
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

    /**
     * @brief método que asigna las cabeceras a la rejilla
     */
    private void setHeaders() {
        this.tablaVehículos = new JTable(model);
        scrollTablaVehículos.setViewportView(tablaVehículos);
        model.addColumn("ID");
        model.addColumn("Tipo");
        model.addColumn("Distancia Línea Recta");
        model.addColumn("Distancia Carretera");
        model.addColumn("Ubicación");

    }

    /**
     * @brief método que retorna la lista de vehiculos
     * @return
     */
    public static Map<Vehiculo,Thread> getListaVehiculos(){
        return listaVehiculos;
    }

    /**
     * @brief método que devuelve el modelo
     * @return
     */
    public static DefaultTableModel getModel(){
        return model;
    }

    /**
     * @brief método que devuelve el panel completo
     * @return
     */
    public JPanel getPanelLogisticaTerrestre(){
        return this.panelLogisticaTerrestre;
    }

}

/**
 * @class Clase que genera la vista del formulario
 */
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
