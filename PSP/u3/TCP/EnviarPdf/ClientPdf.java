//package TCP.EnviarPdf;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientPdf {
    static int DATABYTE = 1024;
    private static String file = "D:\\Perfiles\\at1DAM2\\Desktop\\DAM2V\\PSP\\u3\\TCP\\EnviarPdf\\resultado.pdf";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            byte[] pdf = new byte[DATABYTE];
            int bytesRead;
            DataInputStream in = new DataInputStream(socket.getInputStream());
            FileOutputStream outputStream = new FileOutputStream(file);

            while ((bytesRead = in.read(pdf)) != -1) {
                outputStream.write(pdf, 0, bytesRead);
            }

            in.close();
            socket.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
