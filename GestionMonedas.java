import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GestionMonedas extends JFrame {

    JTable tabla;

    JButton btnActualizar;

    public GestionMonedas() {

        setTitle("Gestión de Monedas");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        String[] columnas = {
            "ID",
            "Moneda",
            "Tasa"
        };

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(columnas);

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 20, 440, 220);

        add(scroll);

        btnActualizar = new JButton("Actualizar Tasa");
        btnActualizar.setBounds(140, 280, 200, 40);

        add(btnActualizar);

        cargarMonedas(modelo);

        btnActualizar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                actualizarMoneda();
            }
        });

        setVisible(true);
    }

    public void cargarMonedas(DefaultTableModel modelo) {

        try {

            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM Moneda";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                modelo.addRow(new Object[] {

                    rs.getInt("id_moneda"),
                    rs.getString("nombre"),
                    rs.getDouble("tasaCambio")
                });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al cargar monedas");
        }
    }

    public void actualizarMoneda() {

        try {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(null, "Seleccione una moneda");
                return;
            }

            int id = Integer.parseInt(
                tabla.getValueAt(fila, 0).toString()
            );

            String moneda = tabla.getValueAt(fila, 1).toString();

            String nuevaTasa = JOptionPane.showInputDialog(
                "Nueva tasa para " + moneda
            );

            double tasa = Double.parseDouble(nuevaTasa);

            Connection con = Conexion.conectar();

            String sql = "UPDATE Moneda SET tasaCambio=? WHERE id_moneda=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, tasa);
            ps.setInt(2, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Tasa actualizada");

            dispose();

            new GestionMonedas();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al actualizar");
        }
    }
}