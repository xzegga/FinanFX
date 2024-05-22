package finanfx.dao;

import finanfx.data.DatabaseConfig;
import finanfx.data.DatabaseConnection;
import finanfx.models.Cuenta;
import java.sql.*;
import java.util.ArrayList;

public class Cuentas {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet resultSet = null;
    String patron = DatabaseConfig.DB_PATTERN;

    public ArrayList<Cuenta> getAccounntsByUserId(int idUsuario) throws SQLException {
        ArrayList<Cuenta> cuentas = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{CALL SP_ListarCuentasFinancieras(?, ?)}");
            statement.setInt(1, idUsuario);
            statement.setString(2, patron);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cuenta cuenta = new Cuenta(
                        resultSet.getInt("ID_Cuenta"),
                        resultSet.getInt("ID_Usuario"),
                        resultSet.getString("Tipo_Cuenta"),
                        resultSet.getString("Numero_Cuenta"),
                        resultSet.getString("Nombre_Banco"),
                        resultSet.getDouble("Saldo_Inicial")
                );
                cuentas.add(cuenta);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            DatabaseConnection.closeConnection(connection);
        }

        return cuentas;
    }

    public void createAccount(Cuenta cuenta, int idUsuario) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{CALL SP_CrearCuentaFinanciera(?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, idUsuario);
            statement.setString(2, cuenta.getTipoCuenta());
            statement.setString(3, cuenta.getNumeroCuenta());
            statement.setString(4, cuenta.getNombreBanco());
            statement.setDouble(5, cuenta.getSaldoInicial());
            statement.setString(6, patron);

            statement.execute();
        } finally {
            if (statement != null) {
                statement.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void updateCuenta(Cuenta cuenta, int idCuenta) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{CALL SP_ActualizarCuentaFinanciera(?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, idCuenta);
            statement.setString(2, cuenta.getTipoCuenta());
            statement.setString(3, cuenta.getNumeroCuenta());
            statement.setString(4, cuenta.getNombreBanco());
            statement.setDouble(5, cuenta.getSaldoInicial());
            statement.setString(6, patron);

            statement.execute();
        } finally {
            if (statement != null) {
                statement.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void deleteAccount(int idCuenta) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{CALL SP_EliminarCuentaFinanciera(?)}");
            statement.setInt(1, idCuenta);

            statement.execute();
        } finally {
            if (statement != null) {
                statement.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public Cuenta getCuentaById(int idCuenta) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{CALL SP_ObtenerCuentaFinanciera(?, ?)}");
            statement.setInt(1, idCuenta);
            statement.setString(2, patron);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                finanfx.models.Cuenta cuenta = new finanfx.models.Cuenta(
                        resultSet.getString("Tipo_Cuenta"),
                        resultSet.getString("Numero_Cuenta"),
                        resultSet.getString("Nombre_Banco"),
                        resultSet.getDouble("Saldo_Inicial")
                );
                cuenta.setIdCuenta(resultSet.getInt("ID_Cuenta"));
                cuenta.setIdUsuario(resultSet.getInt("ID_Usuario"));
                return cuenta;
            } else {
                return null;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }
}
