package PokemonDAO;

import java.util.ArrayList;

public interface PokemonInterface {
    // GETTERS
    public int getEntrada();

    public String getNombre();

    public String getTipo1();

    public String getTipo2();

    public String getHabilidad();

    public String getRegion();

    public float getAltura();

    public float getPeso();

    // SETTERS
    public void setNombre(String nombre);

    public void setTipo1(String tipo1);

    public void setTipo2(String tipo2);

    public void setHabilidad(String habilidad);

    public void setRegion(String region);

    public void setAltura(float altura);

    public void setPeso(float peso);

    // BUSQUEDAS
    public PokemonBean getPokemonPorNumeroEntrada(int entrada);

    public ArrayList<PokemonBean> getPokemonPorTipo(String tipo);

    public ArrayList<PokemonBean> getPokemonPorHabilidad(String habilidad);

    // BORRADO
    public void delete();

    // CONSTRUCTOR
    public PokemonInterface getNuevoPokemon(
            int entrada, String nombre, String habilidad, String tipo1, String tipo2, String region, float altura,
            float peso);
}