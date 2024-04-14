package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
 
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=FinanFX;encrypt=true;trustServerCertificate=true";;
    private static final String DB_USERNAME = "UserDSIW1_0124";
    private static final String DB_PASSWORD = "User13*";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador JDBC", e);
        }

        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static boolean testConnection() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            closeConnection(connection);
            return true;
        } else {
            return false;
        }
    }
}
