import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HistorialTransacciones extends JFrame {

    JTable tabla;

    DefaultTableModel modelo;

    JButton btnEditar;
    JButton btnEliminar;
    JButton btnLimpiar;
    JButton btnRecargar;

    public HistorialTransacciones() {

        setTitle("Historial de Transacciones");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        String[] columnas = {
                "ID",
                "Nombre",
                "Documento",
                "Telefono",
                "Monto",
                "Origen",
                "Destino",
                "Total",
                "Fecha"
        };

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(columnas);

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 20, 940, 320);

        add(scroll);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(50, 370, 150, 40);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(240, 370, 150, 40);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(430, 370, 150, 40);
        add(btnLimpiar);

        btnRecargar = new JButton("Recargar");
        btnRecargar.setBounds(620, 370, 150, 40);
        add(btnRecargar);

        cargarDatos();

        btnEditar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                editarTransaccion();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                eliminarTransaccion();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                limpiarTabla();
            }
        });

        btnRecargar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                recargarTabla();
            }
        });

        setVisible(true);
    }

    public void cargarDatos() {

        try {

            Connection con = Conexion.conectar();

            String sql =
                    "SELECT t.id_transaccion, t.nombre, t.documento, t.telefono, " +
                    "t.monto, " +
                    "m1.nombre AS origen, " +
                    "m2.nombre AS destino, " +
                    "t.total, t.fecha " +
                    "FROM Transaccion t " +
                    "INNER JOIN Moneda m1 ON t.id_moneda_origen = m1.id_moneda " +
                    "INNER JOIN Moneda m2 ON t.id_moneda_destino = m2.id_moneda";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                modelo.addRow(new Object[]{

                        rs.getInt("id_transaccion"),
                        rs.getString("nombre"),
                        rs.getString("documento"),
                        rs.getString("telefono"),
                        rs.getDouble("monto"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDouble("total"),
                        rs.getString("fecha")
                });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al cargar historial");
            System.out.println(e.getMessage());
        }
    }

    public void editarTransaccion() {

        try {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(null, "Seleccione una fila");
                return;
            }

            int id = Integer.parseInt(
                    tabla.getValueAt(fila, 0).toString()
            );

            String nombre = JOptionPane.showInputDialog(
                    "Nuevo nombre:",
                    tabla.getValueAt(fila, 1).toString()
            );

            String documento = JOptionPane.showInputDialog(
                    "Nuevo documento:",
                    tabla.getValueAt(fila, 2).toString()
            );

            String telefono = JOptionPane.showInputDialog(
                    "Nuevo teléfono:",
                    tabla.getValueAt(fila, 3).toString()
            );

            Connection con = Conexion.conectar();

            String sql = "UPDATE Transaccion "
                    + "SET nombre=?, documento=?, telefono=? "
                    + "WHERE id_transaccion=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, documento);
            ps.setString(3, telefono);
            ps.setInt(4, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Transacción actualizada");

            recargarTabla();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar");
        }
    }

    public void eliminarTransaccion() {

        try {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(null, "Seleccione una fila");
                return;
            }

            int id = Integer.parseInt(
                    tabla.getValueAt(fila, 0).toString()
            );

            Connection con = Conexion.conectar();

            String sql = "DELETE FROM Transaccion WHERE id_transaccion = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Transacción eliminada");

            recargarTabla();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar");
        }
    }

    public void limpiarTabla() {

        modelo.setRowCount(0);
    }

    public void recargarTabla() {

        modelo.setRowCount(0);

        cargarDatos();
    }
}