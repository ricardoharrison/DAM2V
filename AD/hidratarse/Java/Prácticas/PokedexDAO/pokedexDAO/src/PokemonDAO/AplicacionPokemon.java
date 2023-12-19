package PokemonDAO;

import java.util.ArrayList;

public class AplicacionPokemon {
    public static void main(String[] args) {
        PokemonInterface objetoDAO = FactoriaPokemons.getPokemonDAO();

        PokemonInterface poke1 = objetoDAO.getNuevoPokemon(
                6, "CHARIZARD", "PODER SOLAR", "FUEGO", " ", "KANTO", 1.70f, 95.00f);

        System.out.println(poke1.getNombre() + " insertado");

        poke1.setTipo2("volador");

        System.out.println("Añadido el tipo "+poke1.getTipo2()+" a "+poke1.getNombre());

        ArrayList<PokemonBean> pokes = objetoDAO.getPokemonPorTipo("acero");

        for (PokemonBean pokemonBean : pokes) {
            System.out.println(pokemonBean.getNombre());
        }

        poke1.delete();
        
        //Busco la entrada 6 que es la que acabo de borrar
        System.out.println(objetoDAO.getPokemonPorNumeroEntrada(6).getNombre());
    }
}