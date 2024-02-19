//package u3.TCP.PruebaObserver;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClienteQueObserva implements Observer {

    public static void main(String[] args) {
        String msg;

        try {
            Socket socket = new Socket("localhost", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());

            while (true) {
                try {
                    msg = in.readUTF();
                    System.out.println(msg);
                } catch (EOFException e) {
                    System.err.println("Connection closed by server.");
                    break; // Exit the loop when connection is closed
                }
            }

            /*
             * in.close(); // Close the output stream
             * socket.close(); // Close the socket
             */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        System.out.println("Es primo!");
    }
}
