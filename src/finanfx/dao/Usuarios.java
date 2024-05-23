package finanfx.dao;

/**
 * @author Anderson ^Raúl Escamilla
 */
import finanfx.data.DatabaseConfig;
import finanfx.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import finanfx.models.User;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuarios {

    private Connection connection = null;
    private CallableStatement statement = null;
    private ResultSet resultSet = null;

    public void createUser(User user) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{CALL SP_CrearUsuario(?, ?, ?, ?, ?, ?, ?, ?)}");
            
            java.sql.Date fechaNac = new java.sql.Date(user.getFechaNacimiento().getTime());
            
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setDate(3, fechaNac);
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getTelefono());
            statement.setString(7,"a");
            statement.setString(8, DatabaseConfig.DB_PATTERN);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger("saveUsuarios").log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    public void resetPassword(String email, String oldPassword, String newPassword, String confirmNewPassword) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{CALL SP_ResetearContrasena(?, ?, ?, ?, ?)}");

            statement.setString(1, email);
            statement.setString(2, oldPassword);
            statement.setString(3, newPassword);
            statement.setString(4, confirmNewPassword);
            statement.setString(5, DatabaseConfig.DB_PATTERN);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger("saveUsuarios").log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    public void updateUsuarios(User user) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{CALL SP_ActualizarUsuario(?, ?, ?, ?, ?, ?, ?)}");
            
            java.sql.Date fechaNac = new java.sql.Date(user.getFechaNacimiento().getTime());
            
            statement.setInt(1, user.getID());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getApellido());
            statement.setDate(4, fechaNac);
            statement.setString(5, user.getTelefono());
            statement.setString(6, user.getEstado());
            statement.setString(7, DatabaseConfig.DB_PATTERN);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger("saveUsuarios").log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    // Método para obtener un presupuesto por su ID desde la base de datos
    public User getUserById(int userId) throws SQLException {
        User user = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{call SP_LeerUsuario(?, ?)}");

            statement.setInt(1, userId);
            statement.setString(2, DatabaseConfig.DB_PATTERN);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("ID_Usuario"),
                        resultSet.getString("Nombre"),
                        resultSet.getString("Apellido"),
                        resultSet.getDate("Fecha_Nacimiento"),
                        resultSet.getString("Email"),
                        resultSet.getString("Telefono"),
                        resultSet.getString("Estado")
                );
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger("saveUsuarios").log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }

        return user;
    }

    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

            DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
    }
}
