package finanfx;

import db.DatabaseConnection;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) {
        try {
            if (DatabaseConnection.testConnection()) {
                System.out.println("Connection to database successful!");
            } else {
                System.err.println("Failed to connect to database!");
            }
        } catch (SQLException e) {
            System.err.println("Error while testing connection: " + e.getMessage());
        }
    }
}
