import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PruebaJFrame {
    public static void main(String[] args) {
        // JFrame creation and visibility
        JFrame myFrame = new JFrame();
        myFrame.setSize(500, 500);
        myFrame.setVisible(true);
        myFrame.setResizable(true);
        //myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //muy importante

        // JPanel creation and linkage to JFrame
        JPanel myPanel = new JPanel();
        myFrame.add(myPanel);

        // Object creation and addition to JPanel
        JButton myButton = new JButton("Say hello");
        JButton myButton2 = new JButton("Change color");
        JButton myButton3 = new JButton("Open confirm dialog: ");
        myPanel.add(myButton);
        myPanel.add(myButton2);
        myPanel.add(myButton3);

        // ActionListeners
        myButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(myFrame, "HELLLOOOOOO!");
        });
        myButton2.addActionListener((ActionEvent e) -> {
            myPanel.setBackground(generateRandomColor());
        });
        myButton3.addActionListener((ActionEvent e) -> {
            int userResponse = JOptionPane.showConfirmDialog(myPanel, "INFO", "Do you want to continue?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (userResponse == JOptionPane.YES_OPTION) {
                System.out.println("User chose 'Yes'");
            } else {
                System.out.println("User chose 'No'");
            }
        });

        // JRadioButton & ButtonGroup example
        JRadioButton radioButton1 = new JRadioButton("Gandalf");
        JRadioButton radioButton2 = new JRadioButton("Aragorn");
        JRadioButton radioButton3 = new JRadioButton("Boromir");
        myPanel.add(radioButton1);
        myPanel.add(radioButton2);
        myPanel.add(radioButton3);

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);
    }

    // fancy random Color generatory
    public static Color generateRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

}