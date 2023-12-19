package AlumnoDAO;

public class AplicacionAlumno {
    public static void main(String[] args) {
        AlumnoInterface objetoDAO = FactoriaAlumnos.getAlumnoDao();

        AlumnoInterface alumno1 = objetoDAO.getNuevoAlumno("1111", "Sonia Rodríguez Martín", "DAM", "1234");
    }
}
