//package TcpConThreads;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RunnableServer implements Runnable {

    Socket socket;
    int num;
    static int totalThreads = 0;

    public RunnableServer(Socket socket) {
        this.socket = socket;
        this.num = totalThreads + 1;
        totalThreads++;
    }

    @Override
    public void run() {
        DataInputStream in;
        try {
            in = new DataInputStream(socket.getInputStream());
            String msg = in.readUTF();

            System.out.println("Mensaje leído por Thread nº " + num + ": '" + msg + "'");

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}