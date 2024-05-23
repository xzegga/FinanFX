package finanfx.dao;

import finanfx.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import finanfx.models.Nota;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class Notas {
    public static CallableStatement re;
    
    public static void saveNotas (Nota note) throws SQLException
    {
        Connection conn = null;
        CallableStatement stmt = null;
        
        try
        {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_CrearNotaTransaccion ?,?";
            stmt = conn.prepareCall(sql);
            
            stmt.setInt(1, note.getID_Transaccion());
            stmt.setString(2, note.getNota());
        }
        finally
        {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public static void updateNota(Nota note) throws SQLException
    {
        Connection conn = null;
        CallableStatement stmt = null;
        
        try
        {
            conn = DatabaseConnection.getConnection();
            String sql ="EXEC SP_ActualizarNotaTransaccion ?, ?";
            stmt = conn.prepareCall(sql);
            
            stmt.setInt(1, note.getID_Transaccion());
            stmt.setString(2, note.getNota());
            
            stmt.executeUpdate();
        }
        finally
        {
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
    }
    
    public static void deleteNota(Nota note) throws SQLException
    {
        Connection conn = null;
        CallableStatement stmt = null;
        
        try
        {
            conn = DatabaseConnection.getConnection();
            String sql ="EXEC SP_EliminarNotaTransaccion ?";
            stmt = conn.prepareCall(sql);
            
            stmt.setInt(1, note.getID_Nota());
            
            stmt.executeUpdate();
        }
        finally
        {
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
    }
    
    public static Nota[] searchNotes(int ID_Transaccion)throws SQLException{
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Nota> noteList = new ArrayList<>();
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_ListarNotasTransaccion ?";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, ID_Transaccion);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idNota = rs.getInt("ID_Nota");
                int idTransaction = rs.getInt("ID_Transaccion");
                String noteText = rs.getString("Nota");
                
                Nota note = new Nota(idTransaction, idNota, noteText);
                noteList.add(note);
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return noteList.toArray(new Nota[noteList.size()]);
    }
}
