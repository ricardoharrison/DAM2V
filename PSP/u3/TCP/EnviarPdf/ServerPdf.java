package TCP.EnviarPdf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPdf {

    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                new Thread(() -> {

                    try {
                        OutputStream outputStream = socket.getOutputStream();
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                        // Read the PDF file into a byte array
                        File file = new File(
                                "D:\\\\Perfiles\\\\at1DAM2\\\\Desktop\\\\DAM2V\\\\PSP\\\\u3\\\\TCP\\\\EnviarPdf\\\\example.pdf");
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] buffer = new byte[(int) file.length()];
                        fileInputStream.read(buffer);
                        fileInputStream.close();

                        // Send the length of the file first
                        dataOutputStream.writeInt(buffer.length);
                        // Send the PDF file
                        dataOutputStream.write(buffer, 0, buffer.length);
                        System.out.println("File sent successfully.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
                ;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
