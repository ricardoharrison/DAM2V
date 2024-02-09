package Practica2Tema7;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class App {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(
                    "D:\\\\Perfiles\\\\at1DAM2\\\\Desktop\\\\DAM2V\\\\AD\\\\PracticasTema7\\\\Practica2Tema7\\\\productos.xml");

            XPath xpath = XPathFactory.newInstance().newXPath();

            // Obtener los nodos denominación y precio de todos los productos
            NodeList productos = (NodeList) xpath.evaluate("//producto", doc, XPathConstants.NODESET);

            // Inicializar variables para almacenar los valores mínimos y máximos
            double minPrecio = Double.MAX_VALUE;
            double maxPrecio = Double.MIN_VALUE;
            String productoMasBaratoZona20Nombre = "";
            String productoMasCaroNombre = "";

            // Iterar sobre los productos para encontrar los valores mínimos y máximos
            for (int i = 0; i < productos.getLength(); i++) {
                double precio = Double.parseDouble(xpath.evaluate("precio", productos.item(i)));
                String codZona = xpath.evaluate("cod_zona", productos.item(i));
                String denominacion = xpath.evaluate("denominacion", productos.item(i));

                // Comprobar si el producto pertenece a la zona 20
                if (codZona.equals("20")) {
                    // Actualizar el producto más barato de la zona 20
                    if (precio < minPrecio) {
                        minPrecio = precio;
                        productoMasBaratoZona20Nombre = denominacion;
                    }
                }

                // Actualizar el producto más caro
                if (precio > maxPrecio) {
                    maxPrecio = precio;
                    productoMasCaroNombre = denominacion;
                }
            }

            // Imprimir resultados
            System.out.println("Producto más caro: " + productoMasCaroNombre + " - Precio: " + maxPrecio);
            System.out.println(
                    "Producto más barato de la zona 20: " + productoMasBaratoZona20Nombre + " - Precio: " + minPrecio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
