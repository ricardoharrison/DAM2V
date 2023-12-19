package Tema5Practica2;

public interface CicloInterface {
    public CicloInterface getNuevoCiclo(String codciclo,String descripcion,String  grado);
    
    public String getCodCiclo();
    public String getDescripcion();
    public String getGrado();
    
    public void  setDescripcion(String descripcion);
    public void  setGrado(String grado);
}