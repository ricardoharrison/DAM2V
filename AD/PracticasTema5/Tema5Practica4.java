import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tema5Practica4 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bd_alumnos";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            // Llamar al método ListarTablas
            ListarTablas(connection);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ListarTablas(Connection connection) throws SQLException {
        String sql = "SHOW TABLES";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Tablas en la base de datos:");

            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                System.out.println(tableName);
            }
        }
    }
}
