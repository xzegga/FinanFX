/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finanfx.dao;

/**
 *
 * @author Ander
 */

import finanfx.data.DatabaseConfig;
import finanfx.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import finanfx.models.User;

public class Usuarios {
    public static CallableStatement re;
    
    public static void saveUsuarios(User users) throws SQLException
    {
        Connection conn = null;
        CallableStatement stmt = null;
        
        try
        {
            conn = DatabaseConnection.getConnection();
            String sql = "EXEC SP_CrearUsuario ?, ?, ?, ?, ?, ?, ?, ?";
            stmt = conn.prepareCall(sql);
            
            stmt.setString(1, users.getNombre());
            stmt.setString(2, users.getApellido());
            stmt.setDate(3, users.getFechaNacimiento());
            stmt.setString(4, users.getEmail());
            stmt.setString(5, users.getPassword());
            stmt.setString(6, users.getTelefono());
            stmt.setString(7, users.getEstado());
            stmt.setString(8, DatabaseConfig.DB_PATTERN);
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
}
