package Trimestre1.Examenes.Examen1.SolucionExamen1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Md5sumPipe {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java MD5Sum <palabra>");
            return;
        }

        String palabra = args[0];

        try {
            // echo -ne "hola" | md5sum
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "echo -ne " + palabra + " | md5sum");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String md5Sum = reader.readLine();

            System.out.println("MD5Sum de '" + palabra + "': " + md5Sum.split(" ")[0]);

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}