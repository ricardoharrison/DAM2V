package HabilidadDAO;

public interface HabilidadInterface {
    public HabilidadInterface getNuevaHabilidad(String nombre, String descripcion);

    public String getNombre();

    public String getDescripcion();

    public void setDescripcion(String descripcion);
}