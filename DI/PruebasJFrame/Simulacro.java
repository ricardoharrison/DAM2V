import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class Simulacro {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Título de la ventana");
        JPanel myPanel = new JPanel();        

        JLabel labelTitulo = new JLabel("Escribe tu título:");
        JTextField textTitulo = new JTextField(20);
        JButton buttonCambia = new JButton("Cambia!");
        JButton buttonMinimizar = new JButton("Minimizar");
        JButton buttonCerrar = new JButton("Cerrar");
        JLabel labelColor = new JLabel("Selecciona el color de fondo");
        JComboBox<String> comboColores = new JComboBox<>();
        comboColores.addItem("rojo");
        comboColores.addItem("azul");
        comboColores.addItem("verde");
        comboColores.addItem("amarillo");
        JButton buttonMensaje = new JButton("Mensaje");

        myFrame.setResizable(true);
        myFrame.setSize(500, 500);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

        myPanel.add(labelTitulo);
        myPanel.add(textTitulo);
        myPanel.add(buttonCambia);
        myPanel.add(buttonMinimizar);
        myPanel.add(buttonCerrar);
        myPanel.add(labelColor);
        myPanel.add(comboColores);
        myPanel.add(buttonMensaje);
        
        myFrame.add(myPanel);
        comboColores.setSelectedItem(comboColores.getItemAt(3));

        buttonCambia.addActionListener(e -> {
            if(!textTitulo.getText().trim().isEmpty()){
                String nuevoTitulo = textTitulo.getText();
                myFrame.setTitle(nuevoTitulo);
            } else {
                JOptionPane.showMessageDialog(myFrame, "No has introducido ningún titulo");
            }
        });

        buttonMinimizar.addActionListener( e -> {
            myFrame.setExtendedState(JFrame.ICONIFIED);
            buttonCambia.setEnabled(false);
        });

        comboColores.addActionListener(e -> {
            String colorSeleccionado = comboColores.getSelectedItem().toString();
            switch(colorSeleccionado){
                case "rojo":
                    myPanel.setBackground(Color.RED);
                    break;
                case "azul":
                    myPanel.setBackground(Color.BLUE);
                    break;
                case "amarillo":
                    myPanel.setBackground(Color.YELLOW);
                    break;
                case "verde":
                    myPanel.setBackground(Color.GREEN);
                    break;
                default:
                    break;
            }
        });

        buttonMensaje.addActionListener(e -> {
            JOptionPane.showConfirmDialog(myFrame, "Este examen es demasiado sencillo");
        });
    }
}