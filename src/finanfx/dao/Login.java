package finanfx.dao;

import finanfx.data.DatabaseConfig;
import finanfx.data.DatabaseConnection;
import finanfx.models.User;
import finanfx.state.LoggedInUser;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class Login {
    Usuarios userService = new Usuarios();

    public int validateUserLogin(String email, String password) throws SQLException {
        int userID = 0;
        
        try (
                Connection connection = DatabaseConnection.getConnection(); CallableStatement statement = connection.prepareCall("{call SP_ValidarUsuario(?,?,?)}")) {

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, DatabaseConfig.DB_PATTERN);

            boolean hasResults = statement.execute();

            if (hasResults) {
                while (statement.getResultSet().next()) {
                                  
                    userID = statement.getResultSet().getInt("ID_Usuario");                
                    setUserInStore(userID);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al autenticar el usuario", e);
        }

        return userID;
    }
    
    public void setUserInStore(int id) throws SQLException {           
        User user = userService.getUserById(id);
        LoggedInUser.getInstance().setUser(user);             
    }
}
