package HabilidadDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HabilidadBean implements HabilidadInterface {
    private String nombre;
    private String descripcion;
    private Connection conexion;

    public HabilidadBean() {
    }

    public HabilidadBean(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public HabilidadInterface getNuevaHabilidad(String nombre, String descripcion) {

        conexion = getConexion();
        try (Statement st = conexion.createStatement()) {
            st.execute(
                    "INSERT INTO HABILIDAD (NOMBRE, DESCRIPCION) VALUES('" + nombre.toUpperCase() + "','" +
                            descripcion.toUpperCase() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HabilidadBean(nombre, descripcion);
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

    @Override
    public void setDescripcion(String descripcion) {
        conexion = getConexion();
        try (Statement st = conexion.createStatement()) {
            st.execute(
                    "UPDATE HABILIDAD SET DESCRIPCION = '" + descripcion +
                            "' WHERE NOMBRE = '" + this.nombre + "'");
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.descripcion = descripcion;
    }

    private Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/Pokedex", "root", "shiav1");
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}