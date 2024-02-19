//package u3.TCP.PruebaObserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    public static void main(String[] args) {
        ServerSubject server = new ServerSubject();
        ClienteQueObserva observer = new ClienteQueObserva();
        server.subscribe(observer); // Subscribe the observer

        server.startReceiving(); // Start receiving and notifying observers
    }

    public void startReceiving() {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            Socket socket = serverSocket.accept();
            while (true) {
                try {
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    int random = (int) (Math.random() * 1000);
                    out.writeUTF("" + random);

                    if (esPrimo(random)) {
                        notifyObservers(); // Notify all observers
                    }

                    Thread.sleep(1000); // Simulate delay
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    private boolean esPrimo(int random) {
        if (random <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(random); i++) {
            if (random % i == 0) {
                return false;
            }
        }
        return true;
    }
}
