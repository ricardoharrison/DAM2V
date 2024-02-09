package TCP.EnviarPdf;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientPdf {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            // Read the length of the file first
            int fileSize = dataInputStream.readInt();
            // Read the PDF file into a byte array
            byte[] buffer = new byte[fileSize];
            dataInputStream.readFully(buffer, 0, fileSize);

            // Save the received PDF file in the same folder as the client
            String fileName = "received_file.pdf";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(buffer);
            fileOutputStream.close();

            System.out.println("File received and saved as: " + fileName);

            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
