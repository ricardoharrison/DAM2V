package Practica7Tema7;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practica7Tema7 {
    public static void main(String[] args) {
        try {
            // Parsear los archivos XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document productosDoc = builder.parse(new File(
                    "D:\\\\Perfiles\\\\at1DAM2\\\\Desktop\\\\DAM2V\\\\AD\\\\PracticasTema7\\\\Practica7Tema7\\\\productos.xml"));
            Document zonasDoc = builder.parse(new File(
                    "D:\\\\Perfiles\\\\at1DAM2\\\\Desktop\\\\DAM2V\\\\AD\\\\PracticasTema7\\\\Practica7Tema7\\\\zonas.xml"));
            Document sucursalesDoc = builder.parse(new File(
                    "D:\\Perfiles\\at1DAM2\\Desktop\\DAM2V\\AD\\PracticasTema7\\Practica7Tema7\\sucursales.xml"));

            // Consulta 1: Datos de productos ordenados por nombre de zona
            Map<String, List<String>> productosPorZona = getProductosPorZona(productosDoc, zonasDoc);
            for (String zona : productosPorZona.keySet()) {
                System.out.println("Zona: " + zona);
                List<String> productos = productosPorZona.get(zona);
                for (String producto : productos) {
                    System.out.println("  - " + producto);
                }
            }

            // Consulta 2: Número de productos por zona
            Map<String, Integer> numProductosPorZona = getNumProductosPorZona(productosDoc, zonasDoc);
            for (String zona : numProductosPorZona.keySet()) {
                System.out.println("Zona: " + zona + ", Número de productos: " + numProductosPorZona.get(zona));
            }

            // Consulta 3: Producto con menos stock por zona
            Map<String, String> productoMenosStockPorZona = getProductoMenosStockPorZona(productosDoc, zonasDoc);
            for (String zona : productoMenosStockPorZona.keySet()) {
                System.out.println(
                        "Zona: " + zona + ", Producto con menos stock: " + productoMenosStockPorZona.get(zona));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener los productos por zona
    // Método para obtener los productos por zona
    private static Map<String, List<String>> getProductosPorZona(Document productosDoc, Document zonasDoc)
            throws Exception {
        Map<String, List<String>> productosPorZona = new HashMap<>();

        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xpath.compile("//producto");

        NodeList productos = (NodeList) expr.evaluate(productosDoc, XPathConstants.NODESET);
        for (int i = 0; i < productos.getLength(); i++) {
            String denominacion = xpath.evaluate("denominacion", productos.item(i));
            String precio = xpath.evaluate("precio", productos.item(i));
            String codZona = xpath.evaluate("cod_zona", productos.item(i));

            // Updated XPath expression to retrieve nombre_zona
            String nombreZona = xpath.evaluate("//zona[cod_zona='" + codZona + "']/nombre_zona/text()", zonasDoc);

            String productoInfo = denominacion + " - Precio: " + precio + " - Zona: " + nombreZona;
            if (!productosPorZona.containsKey(nombreZona)) {
                productosPorZona.put(nombreZona, new ArrayList<>());
            }
            productosPorZona.get(nombreZona).add(productoInfo);
            Collections.sort(productosPorZona.get(nombreZona)); // Ordenar por nombre de zona
        }

        return productosPorZona;
    }

    // Método para obtener el número de productos por zona
    private static Map<String, Integer> getNumProductosPorZona(Document productosDoc, Document zonasDoc)
            throws Exception {
        Map<String, Integer> numProductosPorZona = new HashMap<>();

        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xpath.compile("//producto");

        NodeList productos = (NodeList) expr.evaluate(productosDoc, XPathConstants.NODESET);
        for (int i = 0; i < productos.getLength(); i++) {
            String codZona = xpath.evaluate("cod_zona", productos.item(i));

            // Updated XPath expression to retrieve nombre_zona
            String nombreZona = xpath.evaluate("//zona[cod_zona='" + codZona + "']/nombre_zona/text()", zonasDoc);

            numProductosPorZona.put(nombreZona, numProductosPorZona.getOrDefault(nombreZona, 0) + 1);
        }

        return numProductosPorZona;
    }

    // Método para obtener el producto con menos stock por zona
    private static Map<String, String> getProductoMenosStockPorZona(Document productosDoc, Document zonasDoc)
            throws Exception {
        Map<String, String> productoMenosStockPorZona = new HashMap<>();

        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xpath.compile("//producto");

        NodeList productos = (NodeList) expr.evaluate(productosDoc, XPathConstants.NODESET);
        for (int i = 0; i < productos.getLength(); i++) {
            String denominacion = xpath.evaluate("denominacion", productos.item(i));
            int stockActual = Integer.parseInt(xpath.evaluate("stock_actual", productos.item(i)));
            String codZona = xpath.evaluate("cod_zona", productos.item(i));

            System.out.println("CodZona: " + codZona); // Print codZona
            // Updated XPath expression to retrieve nombre_zona
            String nombreZona = xpath.evaluate("//zona[cod_zona='" + codZona + "']/nombre_zona/text()", zonasDoc);

            System.out.println("NombreZona: " + nombreZona); // Print nombreZona

            if (!productoMenosStockPorZona.containsKey(nombreZona) || stockActual < Integer.parseInt(xpath.evaluate(
                    "//producto[denominacion='" + productoMenosStockPorZona.get(nombreZona) + "']/stock_actual",
                    productosDoc))) {
                productoMenosStockPorZona.put(nombreZona, denominacion);
            }
        }

        return productoMenosStockPorZona;
    }

}