package Tema5Practica2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CicloBean implements CicloInterface {
    private String codciclo;
    private String descripcion;
    private String grado;
    private java.sql.Connection conexion = null;

    /** Creates a new instance of CicloBean */
    public CicloBean() {
    }

    public CicloInterface getNuevoCiclo(String codciclo, String descripcion, String grado) {
        conexion = getConexion(); // desde aqui
        java.sql.Statement sentencia = null;

        try {
            sentencia = conexion.createStatement();
            sentencia.execute(
                    "INSERT INTO ciclo(codciclo,denciclo,grado) VALUES ('" + codciclo + "','" +
                            descripcion + "','" + grado + " ')");

        } catch (SQLException e) {
            System.out.println("Error SQL al insertar Alumno" + e.getMessage());
            return null;
        }
        // Instanciamos ciclo nuevo y le damos los valores a sus atributos
        CicloBean ciclo = new CicloBean();

        ciclo.codciclo = codciclo;
        ciclo.descripcion = descripcion;
        ciclo.grado = grado;

        // devuelvo el objeto ciclo
        return ciclo; // hasta aqui --> Esto lo podría poner en un constructor parametrizado
                      // para eso devuelvo un nuevo objeto:

    }

    public String getCodCiclo() {
        return this.codciclo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGrado() {
        return grado;
    }

    public void setDescripcion(String descripcion) {

        conexion = getConexion();
        java.sql.Statement sentencia = null;

        try {
            sentencia = conexion.createStatement(); // se crea la sentencia

            // se le da valor ejecutandola
            sentencia.execute(
                    "UPDATE CICLO SET denciclo'" + descripcion +
                            "'WHERE CODCICLO='" + this.codciclo + "'");

            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error en UPDATE de descripcion del ciclo");
            return;
        }

        this.descripcion = descripcion;

    }

    private java.sql.Connection getConexion() {
        // Cargar el driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Obtener el driver
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase del Driver");
            return null; // devuelve null si va mal
        }

        Connection conexion = null;

        try {

            // obtener una conexion
            // DriverManager clase que tiene un objeto que devuelve la conexión
            // jdbc:gestor:maquina:puerto/bbdd , usuario, pwd
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/alumno", "root", "");

        } catch (SQLException e) {
            System.out.println("No se puede obtener la conexión");
            return null;
        }
        return conexion;
    }

    public void setGrado(String grado) {
        conexion = getConexion();
        java.sql.Statement sentencia = null;
        try {
            sentencia = conexion.createStatement();

            sentencia.execute(
                    "UPDATE CICLO SET GRADO='" + grado +
                            "'WHERE CODCICLO='" + this.codciclo + "'");
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error en UPDATE de grado del ciclo");
            return;
        } catch (Exception e) {
            System.out.println(e.getClass());
            return;
        }

        this.grado = grado;
    }
}
