import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/casa_cambio";
            String user = "root";
            String password = "santy";

            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado correctamente");
            return con;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}