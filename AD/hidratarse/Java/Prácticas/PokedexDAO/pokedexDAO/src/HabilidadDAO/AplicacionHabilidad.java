package HabilidadDAO;

public class AplicacionHabilidad {
    public static void main(String[] args) {
        HabilidadInterface objetoDAO = FactoriaHabilidades.getHabilidadDAO();

        HabilidadInterface hab1 = objetoDAO.getNuevaHabilidad("Poder Solar", " ");

        System.out.println("INSERTADO SIN DESCRIPCIÓN\n"+hab1.getNombre()+"\n"+hab1.getDescripcion());

        hab1.setDescripcion("1.5x de Atk bajo el sol, 1/8 de HP menos por turno");

        System.out.println("Descripcion modificada: "+hab1.getDescripcion());
    }
}