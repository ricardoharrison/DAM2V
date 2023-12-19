package PokemonDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PokemonBean implements PokemonInterface {

    private int entrada;
    private String nombre;
    private String habilidad;
    private String tipo1;
    private String tipo2;
    private String region;
    private float altura;
    private float peso;
    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    public PokemonBean() {
    }

    public PokemonBean(int entrada, String nombre, String habilidad, String tipo1, String tipo2,
            String region, float altura, float peso) {
        this.entrada = entrada;
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.region = region;
        this.altura = altura;
        this.peso = peso;
    }

    private Connection getConexionPokemon() {
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

    public int getEntrada() {
        return this.entrada;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute("UPDATE POKEMON SET NOMBRE = '" +
                    nombre.toUpperCase() + "' WHERE ENTRADA = " + this.entrada);
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.nombre = nombre;
    }

    public String getHabilidad() {
        return this.habilidad;
    }

    public void setHabilidad(String habilidad) {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute("UPDATE POKEMON SET HABILIDAD = '" +
                    habilidad.toUpperCase() + "' WHERE ENTRADA = " + this.entrada);
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.habilidad = habilidad;
    }

    public String getTipo1() {
        return this.tipo1;
    }

    public void setTipo1(String tipo1) {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute("UPDATE POKEMON SET TIPO_PRINCIPAL = '" +
                    tipo1.toUpperCase() + "' WHERE ENTRADA = " + this.entrada);
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return this.tipo2;
    }

    public void setTipo2(String tipo2) {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute("UPDATE POKEMON SET TIPO_SECUNDARIO = '" +
                    tipo2.toUpperCase() + "' WHERE ENTRADA = " + this.entrada);
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.tipo2 = tipo2;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute("UPDATE POKEMON SET REGION = '" +
                    region.toUpperCase() + "' WHERE ENTRADA = " + this.entrada);
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.region = region;
    }

    public float getAltura() {
        return this.altura;
    }

    public void setAltura(float altura) {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute("UPDATE POKEMON SET ALTURA = " +
                    String.valueOf(altura) + " WHERE ENTRADA = " + this.entrada);
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.altura = altura;
    }

    public float getPeso() {
        return this.peso;
    }

    public void setPeso(float peso) {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute("UPDATE POKEMON SET PESO = " +
                    String.valueOf(peso) + " WHERE ENTRADA = " + this.entrada);
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.peso = peso;
    }

    @Override
    public PokemonBean getPokemonPorNumeroEntrada(int entradaB) {
        conexion = getConexionPokemon();
        PokemonBean pokemon = new PokemonBean();
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM POKEMON WHERE ENTRADA = " + entradaB);
            while (rs.next()) {
                pokemon.entrada = rs.getInt("ENTRADA");
                pokemon.nombre = rs.getString("NOMBRE");
                pokemon.habilidad = rs.getString("HABILIDAD");
                pokemon.tipo1 = rs.getString("TIPO_PRINCIPAL");
                pokemon.tipo2 = rs.getString("TIPO_SECUNDARIO");
                pokemon.region = rs.getString("REGION");
                pokemon.altura = rs.getFloat("ALTURA");
                pokemon.peso = rs.getFloat("PESO");
            }
            rs.close();
            st.close();
            conexion.close();
            return pokemon;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<PokemonBean> getPokemonPorTipo(String tipoB) {
        conexion = getConexionPokemon();
        PokemonBean pokemon;
        ArrayList<PokemonBean> pokemons = new ArrayList<>();
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM POKEMON WHERE TIPO_PRINCIPAL LIKE '" + tipoB.toUpperCase() +
                    "' OR TIPO_SECUNDARIO LIKE '" + tipoB.toUpperCase() + "' ");
            while (rs.next()) {
                pokemon = new PokemonBean();
                pokemon.entrada = rs.getInt("ENTRADA");
                pokemon.nombre = rs.getString("NOMBRE");
                pokemon.habilidad = rs.getString("HABILIDAD");
                pokemon.tipo1 = rs.getString("TIPO_PRINCIPAL");
                pokemon.tipo2 = rs.getString("TIPO_SECUNDARIO");
                pokemon.region = rs.getString("REGION");
                pokemon.altura = rs.getFloat("ALTURA");
                pokemon.peso = rs.getFloat("PESO");
                pokemons.add(pokemon);
            }
            rs.close();
            st.close();
            conexion.close();
            return pokemons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<PokemonBean> getPokemonPorHabilidad(String habilidad) {
        conexion = getConexionPokemon();
        PokemonBean pokemon;
        ArrayList<PokemonBean> pokemons = new ArrayList<>();
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM POKEMON WHERE HABILIDAD LIKE '" + habilidad.toUpperCase() + "' ;");
            while (rs.next()) {
                pokemon = new PokemonBean();
                pokemon.entrada = rs.getInt("ENTRADA");
                pokemon.nombre = rs.getString("NOMBRE");
                pokemon.habilidad = rs.getString("HABILIDAD");
                pokemon.tipo1 = rs.getString("TIPO_PRINCIPAL");
                pokemon.tipo2 = rs.getString("TIPO_SECUNDARIO");
                pokemon.region = rs.getString("REGION");
                pokemon.altura = rs.getFloat("ALTURA");
                pokemon.peso = rs.getFloat("PESO");
                pokemons.add(pokemon);
            }
            rs.close();
            st.close();
            conexion.close();
            return pokemons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete() {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute("DELETE FROM POKEMON WHERE ENTRADA =" + this.entrada);
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonInterface getNuevoPokemon(int entrada, String nombre, String habilidad, String tipo1, String tipo2,
            String region, float altura, float peso) {
        conexion = getConexionPokemon();
        try {
            st = conexion.createStatement();
            st.execute(
                    "INSERT INTO POKEMON VALUES (" + entrada + ", '" + nombre.toUpperCase() + "', '"
                            + tipo1.toUpperCase() + "', '" + tipo2.toUpperCase() +
                            "', '" + habilidad.toUpperCase() + "', '" + region.toUpperCase() + "', " + altura + ", "
                            + peso + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new PokemonBean(entrada, nombre, habilidad, tipo1, tipo2, region, altura, peso);
    }
}