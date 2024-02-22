import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientPdf {
    private static String file = "resultado.pdf";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            FileOutputStream outputStream = new FileOutputStream(file);

            int byteRead;
            while ((byteRead = in.read()) != -1) {
                outputStream.write(byteRead);
            }

            in.close();
            socket.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
