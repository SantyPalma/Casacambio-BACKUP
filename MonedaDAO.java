import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MonedaDAO {

    public void mostrarMonedas() {
        try {
            Connection con = Conexion.conectar();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM Moneda";
            ResultSet rs = st.executeQuery(sql);

            System.out.println("Monedas disponibles:");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_moneda") + ". " +
                    rs.getString("nombre")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}