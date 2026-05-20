import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {

        setTitle("Casa de Cambio");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("CASA DE CAMBIO 💱");
        titulo.setBounds(110, 30, 200, 30);
        add(titulo);

        JButton btnTransaccion = new JButton("Nueva Transacción");
        btnTransaccion.setBounds(100, 80, 180, 40);
        add(btnTransaccion);

        JButton btnHistorial = new JButton("Ver Historial");
        btnHistorial.setBounds(100, 140, 180, 40);
        add(btnHistorial);

        JButton btnMonedas = new JButton("Gestionar Monedas");
        btnMonedas.setBounds(100, 200, 180, 40);
        add(btnMonedas);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(100, 260, 180, 40);
        add(btnSalir);

        btnTransaccion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new VentanaTransaccion();
            }
        });

        btnHistorial.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new HistorialTransacciones();
            }
        });

        btnMonedas.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new GestionMonedas();
            }
        });

        btnSalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        setVisible(true);
    }
}