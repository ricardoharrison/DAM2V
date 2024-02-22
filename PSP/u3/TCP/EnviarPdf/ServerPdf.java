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
                Socket socket = server.accept();

                new Thread(() -> {

                    try {
                        OutputStream outputStream = socket.getOutputStream();
                        FileInputStream fileInputStream = new FileInputStream(
                                "example.pdf");

                        int byteRead;
                        while ((byteRead = fileInputStream.read()) != -1) {
                            outputStream.write(byteRead);
                        }

                        fileInputStream.close();
                        outputStream.close();
                        socket.close();

                        System.out.println("File sent successfully.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
