package finanfx.dao;

import finanfx.data.DatabaseConfig;
import finanfx.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import finanfx.models.Transacciones;

public class Transaccion {
    public static void saveTransaction(Transacciones transactions)throws SQLException{
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
}
