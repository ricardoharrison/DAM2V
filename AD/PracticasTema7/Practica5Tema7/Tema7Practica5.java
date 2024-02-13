import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;
import javax.xml.xpath.*;

public class Tema7Practica5 {
    public static void main(String[] args) {
        try {
            // Crear un DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse(new File(
                    "C:\\Users\\34634\\OneDrive\\Escritorio\\DAMsincMEGA\\DAM2V\\AD\\PracticasTema7\\Practica5Tema7\\productos.xml"));

            // Crear un objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Consulta 1: Obtener por cada zona el número de productos que tiene
            for (int i = 10; i <= 40; i += 10) {
                XPathExpression exprCountProductos = xpath.compile("count(//producto[cod_zona='" + i + "'])");
                System.out.println("Número de productos en la zona " + i + ": "
                        + exprCountProductos.evaluate(doc, XPathConstants.NUMBER));
            }

            // Consulta 2: Obtener la denominación de los productos según la zona
            for (int i = 10; i <= 40; i += 10) {
                XPathExpression exprDenominacion = xpath
                        .compile("//producto[cod_zona='" + i + "']/denominacion/text()");
                System.out.println(
                        "Productos en la zona " + i + ": " + exprDenominacion.evaluate(doc, XPathConstants.STRING));
            }

            // Consulta 3: Obtener la denominación del o de los productos más caros por zona
            for (int i = 10; i <= 40; i += 10) {
                XPathExpression exprPrecioMasCaro = xpath
                        .compile("//producto[cod_zona='" + i + "' and precio = //producto[cod_zona='" + i
                                + "']/precio[not(. < //producto[cod_zona='" + i + "']/precio)]]/denominacion/text()");
                System.out.println("Producto(s) más caro(s) en la zona " + i + ": "
                        + exprPrecioMasCaro.evaluate(doc, XPathConstants.STRING));
            }

            // Consulta 4: Obtener la categoría de los productos según su denominación
            XPathExpression exprCategorias = xpath.compile(
                    "//producto[denominacion[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'placa base')]]/denominacion/text() | //producto[denominacion[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'memoria')]]/denominacion/text() | //producto[denominacion[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'micro')]]/denominacion/text() | //producto[not(denominacion[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'placa base')]) and not(denominacion[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'memoria')]) and not(denominacion[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'micro')])]/denominacion/text()");
            System.out.println("Productos de tipo Placa Base, Memoria, Micro y Otros: "
                    + exprCategorias.evaluate(doc, XPathConstants.STRING));

            // Consulta 5: Inventa tus propias consultas XQuery
            // Ejemplo 1: Obtener el precio del producto con código 003
            XPathExpression exprPrecioProducto003 = xpath.compile("//producto[cod_prod='003']/precio/text()");
            System.out.println("Precio del producto con código 003: "
                    + exprPrecioProducto003.evaluate(doc, XPathConstants.STRING));

            // Ejemplo 2: Obtener el stock mínimo de todos los productos
            XPathExpression exprStockMinimo = xpath.compile("sum(//producto/stock_minimo)");
            System.out.println("Stock mínimo total: " + exprStockMinimo.evaluate(doc, XPathConstants.NUMBER));

            // Ejemplo 3: Obtener el promedio de precios de los productos en la zona 20
            XPathExpression exprPromedioPreciosZona20 = xpath
                    .compile("sum(//producto[cod_zona='20']/precio) div count(//producto[cod_zona='20'])");
            System.out.println(
                    "Precio promedio en la zona 20: " + exprPromedioPreciosZona20.evaluate(doc, XPathConstants.NUMBER));

            // Ejemplo 4: Obtener la denominación del producto más barato
            XPathExpression exprMinPrecio = xpath
                    .compile("//producto[precio = //producto/precio[not(. > //producto/precio)]]/denominacion/text()");
            System.out.println("Producto más barato: " + exprMinPrecio.evaluate(doc, XPathConstants.STRING));

            // Ejemplo 5: Obtener la suma del stock actual de todos los productos
            XPathExpression exprSumaStockActual = xpath.compile("sum(//producto/stock_actual)");
            System.out.println("Suma del stock actual: " + exprSumaStockActual.evaluate(doc, XPathConstants.NUMBER));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
