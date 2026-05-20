import javax.swing.*;
import java.awt.event.*;

public class VentanaTransaccion extends JFrame {

    JComboBox<String> cbOrigen;
    JComboBox<String> cbDestino;

    JTextField txtMonto;
    JTextField txtNombre;
    JTextField txtDocumento;
    JTextField txtTelefono;

    JLabel lblResultado;

    public VentanaTransaccion() {

        setTitle("Nueva Transacción");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblMonto = new JLabel("Monto:");
        lblMonto.setBounds(50, 30, 100, 30);
        add(lblMonto);

        txtMonto = new JTextField();
        txtMonto.setBounds(180, 30, 200, 30);
        add(txtMonto);

        JLabel lblOrigen = new JLabel("Moneda origen:");
        lblOrigen.setBounds(50, 80, 120, 30);
        add(lblOrigen);

        cbOrigen = new JComboBox<>();
        cbOrigen.addItem("COP");
        cbOrigen.addItem("USD");
        cbOrigen.addItem("EUR");
        cbOrigen.setBounds(180, 80, 200, 30);
        add(cbOrigen);

        JLabel lblDestino = new JLabel("Moneda destino:");
        lblDestino.setBounds(50, 130, 120, 30);
        add(lblDestino);

        cbDestino = new JComboBox<>();
        cbDestino.addItem("COP");
        cbDestino.addItem("USD");
        cbDestino.addItem("EUR");
        cbDestino.setBounds(180, 130, 200, 30);
        add(cbDestino);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 180, 100, 30);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 180, 200, 30);
        add(txtNombre);

        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setBounds(50, 230, 100, 30);
        add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(180, 230, 200, 30);
        add(txtDocumento);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 280, 100, 30);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(180, 280, 200, 30);
        add(txtTelefono);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(50, 340, 150, 40);
        add(btnCalcular);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(230, 340, 150, 40);
        add(btnGuardar);

        lblResultado = new JLabel("");
        lblResultado.setBounds(50, 400, 400, 30);
        add(lblResultado);

        btnCalcular.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                calcularConversion();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                guardarTransaccion();
            }
        });

        setVisible(true);
    }

    public void calcularConversion() {

        try {

            double monto = Double.parseDouble(txtMonto.getText());

            String origen = cbOrigen.getSelectedItem().toString();
            String destino = cbDestino.getSelectedItem().toString();

            double tasaOrigen = obtenerTasa(origen);
            double tasaDestino = obtenerTasa(destino);

            double total = monto * tasaOrigen / tasaDestino;

            lblResultado.setText(
                String.format("%.2f", monto) + " " + origen +
                " = " +
                String.format("%.2f", total) + " " + destino
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al calcular");
        }
    }

    public double obtenerTasa(String moneda) {

        if (moneda.equals("COP")) {
            return 1;
        }

        if (moneda.equals("USD")) {
            return 3750;
        }

        if (moneda.equals("EUR")) {
            return 4200;
        }

        return 1;
    }

    public int obtenerIdMoneda(String moneda) {

        if (moneda.equals("COP")) {
            return 1;
        }

        if (moneda.equals("USD")) {
            return 2;
        }

        if (moneda.equals("EUR")) {
            return 3;
        }

        return 1;
    }

    public void guardarTransaccion() {

        try {

            double monto = Double.parseDouble(txtMonto.getText());

            String monedaOrigen = cbOrigen.getSelectedItem().toString();
            String monedaDestino = cbDestino.getSelectedItem().toString();

            int idOrigen = obtenerIdMoneda(monedaOrigen);
            int idDestino = obtenerIdMoneda(monedaDestino);

            double tasaOrigen = obtenerTasa(monedaOrigen);
            double tasaDestino = obtenerTasa(monedaDestino);

            double total = monto * tasaOrigen / tasaDestino;

            String nombre = txtNombre.getText();
            String documento = txtDocumento.getText();
            String telefono = txtTelefono.getText();

            TransaccionDAO dao = new TransaccionDAO();

            dao.registrarTransaccion(
                    nombre,
                    documento,
                    telefono,
                    monto,
                    idOrigen,
                    idDestino,
                    total
            );

            JOptionPane.showMessageDialog(null, "Transacción guardada");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al guardar");
        }
    }
}