import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame {

    public Login() {

        setTitle("Casa de Cambio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 50, 100, 30);
        add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 50, 150, 30);
        add(txtUsuario);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(50, 100, 100, 30);
        add(lblContraseña);

        JPasswordField txtContraseña = new JPasswordField();
        txtContraseña.setBounds(150, 100, 150, 30);
        add(txtContraseña);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(120, 170, 120, 40);
        add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String usuario = txtUsuario.getText();
                String contraseña = new String(txtContraseña.getPassword());

                UsuarioDAO usuarioDAO = new UsuarioDAO();

                boolean acceso = usuarioDAO.login(usuario, contraseña);

                if (acceso) {

                    JOptionPane.showMessageDialog(null, "Bienvenido");

                    new MenuPrincipal();

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new Login();
    }
}