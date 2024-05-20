/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finanfx.dao;

import finanfx.data.DatabaseConfig;
import finanfx.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import finanfx.models.Nota;


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
    
    public static void searchNota(Nota note) throws SQLException
    {
        Connection conn = null;
        CallableStatement stmt = null;

        try
        {
            conn = DatabaseConnection.getConnection();
            String sql ="EXEC SP_ObtenerNotaTransaccion ?";
            stmt = conn.prepareCall(sql);
            
            stmt.setInt(1, note.getID_Nota());
            
            stmt.executeUpdate();
            
            re=stmt;
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
}
