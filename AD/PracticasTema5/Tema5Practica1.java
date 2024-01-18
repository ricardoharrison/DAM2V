import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tema5Practica1 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            // Dar de alta un nuevo alumno
            altaNuevoAlumno(connection, "105", "ENRIQUETA", "ASI", "3434343");

            // Modificar los datos del alumno
            modificarAlumno(connection, "105", "BLANCA", "55555555", "DAM");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void altaNuevoAlumno(Connection connection, String numExpdte, String nombre, String ciclo, String dni)
            throws SQLException {
        String sql = "INSERT INTO alumno VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, numExpdte);
            pstmt.setString(2, nombre);
            pstmt.setString(3, dni);
            pstmt.setString(4, ciclo);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Alumno añadido con éxito.");
            }
        }
    }

    public static void modificarAlumno(Connection connection, String numExpdte, String nuevoNombre, String nuevoDni,
            String nuevoCiclo) throws SQLException {
        String sql = "UPDATE alumno SET nombre = ?, dni = ?, ciclo = ? WHERE numexpdte = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevoDni);
            pstmt.setString(3, nuevoCiclo);
            pstmt.setString(4, numExpdte);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Datos del alumno modificados con éxito.");
            }
        }
    }
}
