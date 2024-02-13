import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;

public class Practica6Tema7 {
    public static void main(String[] args) {
        try {
            // Crear un DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML de empleados
            Document docEmpleados = builder.parse(new File(
                    "C:\\Users\\34634\\OneDrive\\Escritorio\\DAMsincMEGA\\DAM2V\\AD\\PracticasTema7\\Practica6Tema7\\empleados.xml"));
            // Parsear el archivo XML de departamentos
            Document docDepartamentos = builder.parse(new File(
                    "C:\\Users\\34634\\OneDrive\\Escritorio\\DAMsincMEGA\\DAM2V\\AD\\PracticasTema7\\Practica6Tema7\\departamentos.xml"));

            // Consulta 1: Visualizar por cada empleado su apellido, su número de
            // departamento y nombre del departamento
            NodeList empleados = docEmpleados.getElementsByTagName("empleado");
            NodeList departamentos = docDepartamentos.getElementsByTagName("departamento");

            for (int i = 0; i < empleados.getLength(); i++) {
                Node empleado = empleados.item(i);
                String apellido = empleado.getChildNodes().item(1).getTextContent();
                String numDepartamento = empleado.getChildNodes().item(3).getTextContent();

                for (int j = 0; j < departamentos.getLength(); j++) {
                    Node departamento = departamentos.item(j);
                    String numDep = departamento.getChildNodes().item(1).getTextContent();
                    String nombreDep = departamento.getChildNodes().item(3).getTextContent();

                    if (numDepartamento.equals(numDep)) {
                        System.out.println("Empleado: " + apellido + ", Número de departamento: " + numDep
                                + ", Nombre del departamento: " + nombreDep);
                        break; // Una vez encontrado el departamento, salimos del bucle
                    }
                }
            }

            // Consulta 2: Obtener por cada departamento el nombre, el número de empleados y
            // la media del salario
            for (int i = 0; i < departamentos.getLength(); i++) {
                Node departamento = departamentos.item(i);
                String nombreDep = departamento.getChildNodes().item(3).getTextContent();
                String numEmpleados = "0";
                double totalSalarios = 0.0;

                // Calcular el número de empleados y la suma de los salarios para el
                // departamento actual
                NodeList empleadosEnDepartamento = docEmpleados.getElementsByTagName("empleado");
                for (int k = 0; k < empleadosEnDepartamento.getLength(); k++) {
                    Node empleado = empleadosEnDepartamento.item(k);
                    String numDep = empleado.getChildNodes().item(3).getTextContent();
                    if (numDep.equals(departamento.getChildNodes().item(1).getTextContent())) {
                        numEmpleados = String.valueOf(Integer.parseInt(numEmpleados) + 1);
                        totalSalarios += Double.parseDouble(empleado.getChildNodes().item(5).getTextContent());
                    }
                }

                // Calcular la media del salario para el departamento actual
                double mediaSalario = (numEmpleados.equals("0")) ? 0.0 : totalSalarios / Integer.parseInt(numEmpleados);

                System.out.println("Departamento: " + nombreDep + ", Número de empleados: " + numEmpleados
                        + ", Media del salario: " + mediaSalario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// El último ejercicio sobre cómo resolver la consulta a) del apartado anterior,
// joins de documentos,
// también se podría haber resuelto con dos for y where:

// Para resolver esta consulta con dos for y where, necesitaríamos recorrer los
// nodos de empleados y departamentos
// y comparar el número de departamento de cada empleado con el número de
// departamento de cada departamento,
// seleccionando aquellos empleados cuyo número de departamento coincida con el
// número de departamento de un departamento específico.
// Luego, podríamos obtener los atributos de interés de esos empleados y
// departamentos para construir la salida deseada.
// Este enfoque con dos for y where sería más eficiente que usar join de
// documentos en XPath.
