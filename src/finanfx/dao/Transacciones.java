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
    public static void saveTransaction(Transaccion transactions)throws SQLException{
        Connection conn =  null;
        CallableStatement stmt = null;
        
        try{
            conn =  DatabaseConnection.getConnection();
            String sql = "EXEC SP_CrearTransaccion ?, ?, ?, ?, ?, ?, ?";
            stmt = conn.prepareCall(sql);
            
            //configuracion de parametros de stored procedure
            stmt.setInt(1, transactions.getID_Usuario());
            stmt.setString(2, transactions.getTipo_Transaccion());
            stmt.setDouble(3, transactions.getCantidad());
            stmt.setDate(4, transactions.getFecha());
            stmt.setString(5, transactions.getCategoria());
            stmt.setString(6, transactions.getDescripcion());
            stmt.setString(7, transactions.getForma_Pago());
            
            //ejecutamos consulta
        }finally{
            if(stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }
        }
    }
    
    public static void updateTransaction(Transaccion transaction) throws SQLException {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_ActualizarTransaccion ?, ?, ?, ?, ?, ?, ?";
            stmt = conn.prepareCall(sql);

            // Configuración de parámetros del stored procedure
            stmt.setInt(1, transaction.getID_Usuario());
            stmt.setString(2, transaction.getTipo_Transaccion());
            stmt.setDouble(3, transaction.getCantidad());
            stmt.setDate(4, transaction.getFecha());
            stmt.setString(5, transaction.getCategoria());
            stmt.setString(6, transaction.getDescripcion());
            stmt.setString(7, transaction.getForma_Pago());

            // Ejecutar consulta
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
    
    public static Transaccion[] listTransactions(int ID_Transaccion) throws SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transaccion> transactionList = new ArrayList<>();

        try
        {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_ListarTransacciones ?";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, ID_Transaccion);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                int idTransaction = rs.getInt("ID_Transaccion");
                int idUsuario = rs.getInt("ID_Usuario");
                String transactionType = rs.getString("Tipo_Transaccion");
                double amount = rs.getDouble("Cantidad");
                Date fecha = rs.getDate("Fecha");
                String categories = rs.getString("Categoria");
                String description = rs.getString("Descripcion");
                String paymentMethod = rs.getString("Forma_Pago");

                Transaccion transaction = new Transaccion(idTransaction,idUsuario, transactionType, amount, fecha, categories, description, paymentMethod);
                transactionList.add(transaction);
            }
        } finally
        {
            if (rs != null)
            {
                rs.close();
            }
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }

        return transactionList.toArray(new Transaccion[transactionList.size()]);
    }
    
    public static void deleteTransactions(int ID_Transaccion) throws SQLException{
        Connection conn = null;
        CallableStatement stmt = null;
        
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_EliminarTransaccion ?";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, ID_Transaccion);
            stmt.executeUpdate();
        }finally{
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
    }
}
