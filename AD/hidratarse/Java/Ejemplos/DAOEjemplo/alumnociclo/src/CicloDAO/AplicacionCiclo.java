package CicloDAO;

public class AplicacionCiclo {
    public static void main(String[] args) {

        CicloInterface objetoDAO = FactoriaCiclos.getCicloDao();

        CicloInterface ciclo1 = objetoDAO.getNuevoCiclo("ARI", "Administración de Recursos Integrales", "SUPERIOR");
    }
}
