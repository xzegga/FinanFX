package finanfx.dao;

import java.sql.Date;
import java.sql.ResultSet;
import finanfx.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import finanfx.models.Transaccion;
import java.util.ArrayList;

public class Transacciones {

    public static void saveTransaction(Transaccion transaction) throws SQLException {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_CrearTransaccion ?, ?, ?, ?, ?, ?";
            stmt = conn.prepareCall(sql);

            java.sql.Date dateT = new java.sql.Date(transaction.getFecha().getTime());
            
            //configuracion de parametros de stored procedure
            stmt.setInt(1, transaction.getID_Usuario());
            stmt.setDouble(2, transaction.getCantidad());
            stmt.setDate(3, dateT);
            stmt.setString(4, transaction.getCategoria());
            stmt.setString(5, transaction.getDescripcion());
            stmt.setString(6, transaction.getForma_Pago());
            stmt.execute();

            //ejecutamos consulta
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void updateTransaction(Transaccion transaction) throws SQLException {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_ActualizarTransaccion ?, ?, ?, ?, ?, ?";
            stmt = conn.prepareCall(sql);

            java.sql.Date dateT = new java.sql.Date(transaction.getFecha().getTime());
            // Configuración de parámetros del stored procedure
            stmt.setInt(1, transaction.getID_Transaccion());
            stmt.setDouble(2, transaction.getCantidad());
            stmt.setDate(3, dateT);
            stmt.setString(4, transaction.getCategoria());
            stmt.setString(5, transaction.getDescripcion());
            stmt.setString(6, transaction.getForma_Pago());

            stmt.execute();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static ArrayList<Transaccion> listTransactions(int userId) throws SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transaccion> transactionList = new ArrayList<>();

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_ListarTransacciones ?";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Transaccion transaction = new Transaccion(
                        rs.getInt("ID_Transaccion"),
                        rs.getInt("ID_Usuario"),
                        rs.getDouble("Cantidad"),
                        (Date) rs.getDate("Fecha"),
                        rs.getString("Categoria"),
                        rs.getString("Descripcion"),
                        rs.getString("Forma_Pago")
                );

                transactionList.add(transaction);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return transactionList;
    }

    public static Transaccion getTransactionById(int ID_Transaccion) throws SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Transaccion transaction = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_ObtenerTransaccionById ?";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, ID_Transaccion);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idTransaction = rs.getInt("ID_Transaccion");
                int idUsuario = rs.getInt("ID_Usuario");
                double amount = rs.getDouble("Cantidad");
                Date fecha = rs.getDate("Fecha");
                String categories = rs.getString("Categoria");
                String description = rs.getString("Descripcion");
                String paymentMethod = rs.getString("Forma_Pago");

                transaction = new Transaccion(idTransaction, idUsuario, amount, fecha, categories, description, paymentMethod);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return transaction;
    }

    public static void deleteTransactions(int ID_Transaccion) throws SQLException {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_EliminarTransaccion ?";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, ID_Transaccion);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
