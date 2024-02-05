import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEnviaConsonantes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = "";
        try {
            Socket socket = new Socket("localhost", 1234);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            msg = sc.nextLine();

            out.writeUTF(msg);

            out.close(); // Close the output stream
            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}