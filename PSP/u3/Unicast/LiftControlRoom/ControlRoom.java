package LiftControlRoom;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlRoom {

    private static final int MAX_LENGTH = (int) (Math.pow(2, 14));

    public static void main(String[] args) {

        int port = 8000;
        String server = "localhost";

        JFrame frame = new JFrame("Control room");

        JPanel panel = new JPanel();

        frame.setResizable(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel label1 = new JLabel();
        JLabel separator = new JLabel("|\n");
        JLabel label2 = new JLabel();

        panel.add(label1);
        panel.add(separator);
        panel.add(label2);

        frame.add(panel);

        Lift lift1 = new Lift(1, port, server);
        Lift lift2 = new Lift(2, port, server);

        try {
            DatagramSocket socket = new DatagramSocket(port);
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] splitMsg = msg.split(";");
                switch (splitMsg[0]) {
                    case "1":
                        label1.setText("Lift No. " + splitMsg[0] + " is currently " + splitMsg[2] + " at/to floor "
                                + splitMsg[1]);
                        break;
                    case "2":
                        label2.setText("Lift No. " + splitMsg[0] + " is currently " + splitMsg[2] + " at/to floor "
                                + splitMsg[1]);
                        break;
                    default:
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
