import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;

public class Tema7Practica3 {
    public static void main(String[] args) {
        try {
            // Crear un DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse(new File(
                    "C:\\Users\\34634\\OneDrive\\Escritorio\\DAMsincMEGA\\DAM2V\\AD\\PracticasTema7\\Practica3Tema7\\sucursales.xml"));

            // Crear un objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Consulta 1: Obtener los datos de las cuentas bancarias cuyo tipo sea AHORRO
            XPathExpression expr1 = xpath.compile("//cuenta[@tipo='AHORRO']");
            NodeList ahorrAccounts = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
            System.out.println("Cuentas de AHORRO:");
            printNodeList(ahorrAccounts);

            // Consulta 2: Obtener por cada sucursal el código y el número de cuentas del
            // tipo AHORRO que tiene
            XPathExpression expr2 = xpath.compile("//sucursal");
            NodeList sucursalNodes = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < sucursalNodes.getLength(); i++) {
                String codigo = sucursalNodes.item(i).getAttributes().getNamedItem("codigo").getNodeValue();
                XPathExpression exprCount = xpath.compile("count(cuenta[@tipo='AHORRO'])");
                Double count = (Double) exprCount.evaluate(sucursalNodes.item(i), XPathConstants.NUMBER);
                System.out.println(
                        "Código de Sucursal: " + codigo + ", Número de Cuentas de AHORRO: " + count.intValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para imprimir NodeList
    private static void printNodeList(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getTextContent());
        }
    }
}