package AlumnoDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AlumnoBean implements AlumnoInterface{
    private String numexpdte;
    private String nombre;
    private String ciclo;
    private String dni;
    private java.sql.Connection conexionA = null;

    public AlumnoBean() {

    }

    public String getNumexpdte() {
        return this.numexpdte;
    }

    public String getNombre() {
        return nombre; // no es obligatorio poner this
    }

    public String getCiclo() {
        return ciclo;
    }

    public String getDni() {
        return dni;
    }

    // METODO PARA ESTABLECER LA CONEXIÓN
    private java.sql.Connection getConexionAlumno() {
        // Cargar el driver
        try {
            // Class.forName("oracle.jdbc.driver.OracleDriver"); //Obtener el driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Obtener el driver
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase del Driver");
            return null; // devuelve null si va mal
        }

        Connection conexion = null;
        try {
            // conexionA=DriverManager.getConnection("jdbc:oracle:thin:@192.168.33.228:1521:orcl","blanca","blanca");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/AD", "root", "shiav1");
        } catch (SQLException e) {
            System.out.println("No se puede obtener la conexión");
            return null;
        }
        return conexion;
    }

    // METODOS SET
    public void setNombre(String nombre) {
        conexionA = getConexionAlumno();
        java.sql.Statement sentencia = null;

        try {
            sentencia = conexionA.createStatement();
            sentencia.execute("UPDATE ALUMNO SET NOMBRE='" + nombre +
                    "'WHERE NUMEXPDTE='" + this.numexpdte + "'");
            sentencia.close();
            conexionA.close();
        } catch (SQLException e) {
            System.out.println("Error en UPDATE de nombre sobre ALUMNO");
            return;
        }
        this.nombre = nombre;
    }

    public void setCiclo(String ciclo) {

        conexionA = getConexionAlumno();
        java.sql.Statement sentencia = null;
        try {
            sentencia = conexionA.createStatement(); // se crea la sentencia

            sentencia.execute("UPDATE ALUMNO SET CICLO='" + ciclo +
                    "'WHERE NUMEXPDTE='" + this.numexpdte + "'");
            // modifico el nombre, con el nombre que nos dan de aquel cuyo expdiente
            // coincida con el objeto actual
            sentencia.close();
            conexionA.close();
        } catch (SQLException e) {
            System.out.println("Error en UPDATE de  ciclo sobre ALUMNO");
            return;
        }

        this.ciclo = ciclo;
    }

    public void setDni(String dni) {
        conexionA = getConexionAlumno();
        java.sql.Statement sentencia = null;
        try {
            sentencia = conexionA.createStatement();

            sentencia.execute("UPDATE ALUMNO SET DNI='" + dni +
                    "'WHERE NUMEXPDTE='" + this.numexpdte + "'");
            sentencia.close();
            conexionA.close();
        } catch (SQLException e) {
            System.out.println("Error en UPDATE de dni sobre ALUMNO");
            return;
        } catch (Exception e) {
            System.out.println(e.getClass());
            return;
        }

        this.dni = dni;
    }

    // METODOS FIND

    public AlumnoInterface getAlumnoPorNUMEXPDTE(String NUMEXPDTE) {
        conexionA = getConexionAlumno();
        java.sql.Statement sentencia = null;
        AlumnoBean alumno = null;

        try {
            sentencia = conexionA.createStatement(); // se crea la sentencia

            alumno = new AlumnoBean();
            // se le da valor ejecutandola
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery(
                    "SELECT * FROM ALUMNO" +
                            "WHERE NUMEXPDTE='" + NUMEXPDTE + "'");

            while (resultado.next()) {
                alumno.numexpdte = resultado.getString("NUMEXPDTE");
                alumno.ciclo = resultado.getString("CICLO");
                alumno.dni = resultado.getString("DNI");
                alumno.nombre = resultado.getString("NOMBRE");

            }
            resultado.close();
            sentencia.close();
            conexionA.close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de Alumno por NUMEXPDTE sobre ALUMNO");
            return null;
        }
        return alumno;

    }

    public java.util.Collection getAlumnoPorCiclo(String ciclo) {
        conexionA = getConexionAlumno();
        java.sql.Statement sentencia = null;

        AlumnoBean alumno = null;
        java.util.Collection coleccion = null;

        try {
            sentencia = conexionA.createStatement();
            coleccion = new java.util.Vector();
            java.sql.ResultSet resultado;

            resultado = sentencia.executeQuery(
                    "SELECT * FROM ALUMNO" +
                            "WHERE CICLO='" + ciclo + "'");

            while (resultado.next()) {
                alumno = new AlumnoBean();
                alumno.numexpdte = resultado.getString("NUMEXPDTE");
                alumno.ciclo = resultado.getString("CICLO");
                alumno.dni = resultado.getString("DNI");
                alumno.nombre = resultado.getString("NOMBRE");
                coleccion.add(alumno);
            }
            resultado.close();
            sentencia.close();
            conexionA.close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de Alumno por ciclo sobre ALUMNO");
            return null;
        }
        return coleccion; // Devuelve una colección de alumnos.
    }

    public java.util.Collection getAlumnoPorNombre(String nombre) {
        conexionA = getConexionAlumno();
        java.sql.Statement sentencia = null;
        AlumnoBean alumno = null;
        java.util.Collection coleccion = null;

        try {
            sentencia = conexionA.createStatement();
            coleccion = new java.util.Vector();

            java.sql.ResultSet resultado;

            resultado = sentencia.executeQuery(
                    "SELECT * FROM ALUMNO" +
                            "WHERE NOMBRE='" + nombre + "'");

            while (resultado.next()) {
                alumno = new AlumnoBean();
                alumno.numexpdte = resultado.getString("NUMEXPDTE");
                alumno.ciclo = resultado.getString("CICLO");
                alumno.dni = resultado.getString("DNI");
                alumno.nombre = resultado.getString("NOMBRE");
                coleccion.add(alumno);
            }
            resultado.close();
            sentencia.close();
            conexionA.close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de Alumno por nombre sobre ALUMNO");
            return null;
        }
        return coleccion;
    }

    // METODO DE BORRADO
    public void delete() {
        conexionA = getConexionAlumno();
        java.sql.Statement sentencia = null;
        try {
            sentencia = conexionA.createStatement();

            sentencia.execute("DELETE FROM ALUMNO  WHERE NUMEXPDTE='" + this.numexpdte + "'");
            sentencia.close();
            conexionA.close();
        } catch (SQLException e) {
            System.out.println("Error  DELETE   sobre ALUMNO");
            return;
        }
        ;
        this.ciclo = null;
        this.dni = null;
        this.numexpdte = null;
        this.conexionA = null;
        this.nombre = null;

    }

    // METODO INSERT
    public AlumnoInterface getNuevoAlumno(String NUMEXPDTE, String nombre, String ciclo, String dni) {

        conexionA = getConexionAlumno();
        java.sql.Statement sentencia = null;

        try {
            sentencia = conexionA.createStatement();
            sentencia.execute(
                    "INSERT INTO alumno(numexpdte,nombre,dni,ciclo) VALUES ('" + NUMEXPDTE + "','" +
                            nombre + "','" + dni + "','" + ciclo + "')");

            // "INSERT INTO ciclo(codciclo,denciclo,grado) VALUES ('"+codciclo+"','"+
            // descripcion+"','"+grado+" ')");

        } catch (SQLException e) {
            System.out.println("Error SQL al insertar Alumno" + e.getMessage());
            return null;
        }
        // Instanciamos alumno nuevo y le damos los valores a sus atributos
        AlumnoBean alumno = new AlumnoBean();

        alumno.numexpdte = NUMEXPDTE;
        alumno.dni = dni;
        alumno.nombre = nombre;
        alumno.ciclo = ciclo;
        // devuelvo el objeto alumno
        return alumno;
    }
}
