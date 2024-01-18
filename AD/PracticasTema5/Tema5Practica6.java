import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tema5Practica6 {
    private static Connection conexion;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/bd_alumnos"; // Cambia la URL según tu configuración
                String usuario = "root";
                String contraseña = "";

                conexion = DriverManager.getConnection(url, usuario, contraseña);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void listarTablas() {
        try {
            DatabaseMetaData metaData = getConexion().getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", null);

            System.out.println("Tablas disponibles:");

            while (resultSet.next()) {
                String tableName = resultSet.getString(3);
                System.out.println(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarTabla(String tabla) {
        try {
            Statement statement = getConexion().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tabla);

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getConexion(); // Establecer conexión al principio

        System.out.println("Bienvenido al programa BD_Alumnos_Menu");
        listarTablas();

        int opcion = Leer
                .leerEntero("Seleccione una tabla (1 para alumnos, 2 para alumnos_asignaturas, 3 para asignaturas): ");

        switch (opcion) {
            case 1:
                mostrarTabla("alumnos");
                break;
            case 2:
                mostrarTabla("alumnos_asignaturas");
                break;
            case 3:
                mostrarTabla("asignaturas");
                break;
            default:
                System.out.println("Opción no válida");
        }

        cerrarConexion(); // Cerrar conexión al final
    }
}
