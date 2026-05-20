import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean login(String usuario, String contraseña) {

        try {

            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM Usuario WHERE usuario = ? AND contraseña = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

            return false;
        }
    }
}