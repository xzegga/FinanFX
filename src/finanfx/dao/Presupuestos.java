package finanfx.dao;

import finanfx.data.DatabaseConnection;
import finanfx.models.Presupuesto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Presupuestos {

    private Connection connection = null;
    private CallableStatement statement = null;
    private ResultSet resultSet = null;

    public Presupuesto CreateBudget(ResultSet resultset) throws SQLException {
        return new Presupuesto(
            resultSet.getInt("ID_Presupuesto"),
            resultSet.getInt("ID_Usuario"),
            resultSet.getString("Periodo"),
            resultSet.getString("Inicio"),
            resultSet.getString("Categoria"),
            resultSet.getDouble("Monto_Presupuestado")
        );
    }

    // Método para crear un nuevo presupuesto en la base de datos
    public void createBudget(Presupuesto budget) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{call SP_CrearPresupuesto(?, ?, ?, ?, ?)}");
            statement.setInt(1, budget.getID_Usuario());
            statement.setString(2, budget.getPeriodo());
            statement.setString(3, budget.getInicio());
            statement.setString(4, budget.getCategoria());
            statement.setDouble(5, budget.getMonto_Presupuestado());
            statement.execute();
        } finally {
            closeResources();
        }
    }

    // Método para listar presupuestos por ID de usuario desde la base de datos
    public ArrayList<Presupuesto> listBudgetsByUserId(int userId) throws SQLException {
        ArrayList<Presupuesto> budgets = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{call SP_ListarPresupuestosByUserId(?)}");
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Presupuesto budget = CreateBudget(resultSet);
                budgets.add(budget);
            }
            return budgets;
        } finally {
            closeResources();
        }
    }

    // Método para listar todos los presupuestos desde la base de datos
    public ArrayList<Presupuesto> listBudgets() throws SQLException {
        ArrayList<Presupuesto> budgets = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{call SP_ListarPresupuestos()}");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Presupuesto budget = CreateBudget(resultSet);
                budgets.add(budget);
            }
            return budgets;
        } finally {
            closeResources();
        }
    }

    // Método para obtener un presupuesto por su ID desde la base de datos
    public Presupuesto getBudgetById(int budgetId) throws SQLException {
        Presupuesto budget = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{call SP_ObtenerPresupuestoById(?)}");
            statement.setInt(1, budgetId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                budget = CreateBudget(resultSet);
            }
            return budget;
        } finally {
            closeResources();
        }
    }

    // Método para actualizar un presupuesto en la base de datos
    public void updateBudget(Presupuesto budget) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{call SP_ActualizarPresupuesto(?, ?, ?, ?, ?)}");
            statement.setInt(1, budget.getID_Presupuesto());
            statement.setString(2, budget.getPeriodo());
            statement.setString(3, budget.getInicio());
            statement.setString(4, budget.getCategoria());
            statement.setDouble(5, budget.getMonto_Presupuestado());
            statement.execute();
        } finally {
            closeResources();
        }
    }

    // Método para eliminar un presupuesto por su ID desde la base de datos
    public void deleteBudget(int budgetId) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareCall("{call SP_EliminarPresupuesto(?)}");
            statement.setInt(1, budgetId);
            statement.execute();
        } finally {
            closeResources();
        }
    }

    // Método privado para cerrar la conexión y liberar recursos
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
        } catch (SQLException e) {
        }
    }
}
