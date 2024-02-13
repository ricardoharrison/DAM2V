import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;

public class Tema7Practica4 {
    public static void main(String[] args) {
        try {
            // Crear un DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse(new File(
                    "C:\\Users\\34634\\OneDrive\\Escritorio\\DAMsincMEGA\\DAM2V\\AD\\PracticasTema7\\Practica4Tema7\\empleados.xml"));

            // Crear un objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Ejercicio 1: Nombres de oficio que empiezan por P
            XPathExpression expr1 = xpath.compile("//oficio[starts-with(., 'P')]/text()");
            NodeList oficiosP = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
            System.out.println("Nombres de oficio que empiezan por P:");
            printNodeList(oficiosP);

            // Ejercicio 2: Nombres de oficio y los empleados de cada oficio
            XPathExpression expr2 = xpath.compile("//oficio");
            NodeList oficios = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < oficios.getLength(); i++) {
                String oficio = oficios.item(i).getTextContent();
                System.out.println("Oficio: " + oficio);
                XPathExpression exprEmpleados = xpath.compile("//empleado[oficio='" + oficio + "']/nombre/text()");
                NodeList empleados = (NodeList) exprEmpleados.evaluate(doc, XPathConstants.NODESET);
                System.out.println("Empleados:");
                printNodeList(empleados);
            }

            // Ejercicio 3: El número de empleados que tiene cada departamento y la media de
            // salario redondeada
            XPathExpression expr3 = xpath.compile("count(//departamento)");
            double numDepartamentos = (double) (double) expr3.evaluate(doc, XPathConstants.NUMBER);
            System.out.println("Número de departamentos: " + (int) numDepartamentos);

            for (int i = 1; i <= numDepartamentos; i++) {
                String departamento = (String) xpath.evaluate("(//departamento)[" + i + "]", doc);
                double numEmpleados = (double) xpath.evaluate("count(//empleado[departamento='" + departamento + "'])",
                        doc, XPathConstants.NUMBER);
                double salarioTotal = (double) xpath.evaluate(
                        "sum(//empleado[departamento='" + departamento + "']/salario)", doc, XPathConstants.NUMBER);
                double salarioPromedio = salarioTotal / numEmpleados;
                System.out.println("Departamento: " + departamento);
                System.out.println("Número de empleados: " + (int) numEmpleados);
                System.out.println("Salario promedio: " + Math.round(salarioPromedio));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para imprimir NodeList
    private static void printNodeList(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeValue());
        }
    }
}
