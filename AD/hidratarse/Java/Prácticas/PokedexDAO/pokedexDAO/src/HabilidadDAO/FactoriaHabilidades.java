package HabilidadDAO;

public class FactoriaHabilidades {
    public static HabilidadInterface getHabilidadDAO() {
        return new HabilidadBean();
    }
}