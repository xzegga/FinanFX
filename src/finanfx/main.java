package finanfx;
import finanfx.frm.frmLogin;
//import java.sql.SQLException;
//import finanfx.data.DatabaseConnection;

public class main {

    public static void main(String[] args) {
       /*try {
            if (DatabaseConnection.testConnection()) {
                System.out.println("Connection to database successful!");
            } else {
                System.err.println("Failed to connect to database!");
            }
        } catch (SQLException e) {
            System.err.println("Error while testing connection: " + e.getMessage());
        }*/
       frmLogin login = new frmLogin();
       login.setVisible(true);
    }
}
