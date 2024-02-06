import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tema7Practica1 {
    public static void main(String[] args) {
        // Datos de conexión
        String url = "jdbc:mysql://localhost:3306/employee";
        String usuario = "root";
        String contrasena = "";

        Connection conexion = null;
        Statement declaracion = null;
        ResultSet resultado = null;

        try {
            // Conexión a la base de datos
            conexion = DriverManager.getConnection(url, usuario, contrasena);

            // Consulta para devolver filas de tabla EMPLOYEE en formato XML
            String consulta = "SELECT * FROM EMPLOYEE";
            declaracion = conexion.createStatement();
            resultado = declaracion.executeQuery(consulta);

            // Imprimir resultados en formato XML
            System.out.println("<Employees>");
            while (resultado.next()) {
                System.out.println("  <Employee>");
                System.out.println("    <EMPLOYEE_ID>" + resultado.getInt("EMPLOYEE_ID") + "</EMPLOYEE_ID>");
                System.out.println("    <LAST_NAME>" + resultado.getString("LAST_NAME") + "</LAST_NAME>");
                System.out.println("    <FIRST_NAME>" + resultado.getString("FIRST_NAME") + "</FIRST_NAME>");
                System.out
                        .println("    <MIDDLE_INITIAL>" + resultado.getString("MIDDLE_INITIAL") + "</MIDDLE_INITIAL>");
                System.out.println("    <JOB_ID>" + resultado.getInt("JOB_ID") + "</JOB_ID>");
                System.out.println("    <MANAGER_ID>" + resultado.getInt("MANAGER_ID") + "</MANAGER_ID>");
                System.out.println("    <HIRE_DATE>" + resultado.getDate("HIRE_DATE") + "</HIRE_DATE>");
                System.out.println("    <SALARY>" + resultado.getDouble("SALARY") + "</SALARY>");
                System.out.println("    <COMMISSION>" + resultado.getDouble("COMMISSION") + "</COMMISSION>");
                System.out.println("    <DEPARTMENT_ID>" + resultado.getInt("DEPARTMENT_ID") + "</DEPARTMENT_ID>");
                System.out.println("  </Employee>");
            }
            System.out.println("</Employees>");

            // Cerrar recursos
            resultado.close();
            declaracion.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}