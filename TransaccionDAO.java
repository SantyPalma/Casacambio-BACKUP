import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransaccionDAO {

    public void registrarTransaccion(
            String nombre,
            String documento,
            String telefono,
            double monto,
            int idOrigen,
            int idDestino,
            double total
    ) {

        try {

            Connection con = Conexion.conectar();

            String sql = "INSERT INTO Transaccion "
                    + "(nombre, documento, telefono, monto, id_moneda_origen, id_moneda_destino, total) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, documento);
            ps.setString(3, telefono);

            ps.setDouble(4, monto);

            ps.setInt(5, idOrigen);
            ps.setInt(6, idDestino);

            ps.setDouble(7, total);

            ps.executeUpdate();

            System.out.println("Transacción registrada correctamente");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}