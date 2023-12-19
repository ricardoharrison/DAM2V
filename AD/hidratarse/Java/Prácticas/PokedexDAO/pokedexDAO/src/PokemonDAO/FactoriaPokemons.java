package PokemonDAO;

public class FactoriaPokemons {
    public static PokemonInterface getPokemonDAO(){
        return new PokemonBean();
    }
}
