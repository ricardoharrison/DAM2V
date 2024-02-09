//package TcpConThreads;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConThreads {

    public static void main(String[] args) {

        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                RunnableServer runnable = new RunnableServer(socket);
                Thread thread = new Thread(runnable);

                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
