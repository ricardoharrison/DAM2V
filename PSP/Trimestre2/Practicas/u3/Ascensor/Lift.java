package Ascensor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lift {
    static final int TRAVELLING_TIME = 1500;
    static final int LAPSE = 100;
    static final int MAX_LENGTH = (int)(Math.pow(2, 14));
    String server;
    int port;
    int code;
    int floor = 0;
    LiftState direction = LiftState.STATIONARY;
    String infoMsg = "CURRENTLY AT FLOOR ";

    public Lift(String server, int code, int port) {
        this.code = code;
        this.port = port;
        start();
    }

    private void start() {
        JFrame frame = new JFrame("Lift No. " + code);
        JPanel panel = new JPanel();

        frame.setResizable(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel labelInfo = new JLabel(infoMsg + floor);
        JButton buttonUp = new JButton("Go up");
        JButton buttonDown = new JButton("Go down");
        
        buttonUp.addActionListener(li -> {
            floor++;
            direction = LiftState.GOING_UP;
            labelInfo.setText("Going up...");
            try{
                Thread.sleep(TRAVELLING_TIME);
            } catch (InterruptedException e) {}
            direction = LiftState.STATIONARY;
            labelInfo.setText(infoMsg + floor);
        });

        buttonDown.addActionListener(li -> {
            floor--;
            direction = LiftState.GOING_DOWN;
            labelInfo.setText("Going down...");
            try{
                Thread.sleep(1500);
            } catch (InterruptedException e) {}
            direction = LiftState.STATIONARY;
            labelInfo.setText(infoMsg + floor);
        });

        panel.add(buttonUp);
        panel.add(buttonDown);
        panel.add(labelInfo);

        frame.add(panel);

        while(true){

            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress ipAddress = InetAddress.getByName(server); // Dirección del servidor
                byte[] sentData = new byte[MAX_LENGTH];
                String sentence = code + ";" + floor + ";" + direction.toString(); // Mensaje a enviar
                
                sentData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);
                socket.send(sendPacket); // Envía el paquete al servidor
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(LAPSE);
            } catch (InterruptedException e) {}
        }
        
        
    }



}
