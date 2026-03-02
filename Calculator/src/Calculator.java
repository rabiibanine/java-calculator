import javax.swing.*;
import java.awt.*;

public class Calculator {
    public Calculator() {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField();
        textField.setEditable(true);

        JPanel numpad = new JPanel(new GridLayout(4, 4, 5, 5), false);

        numpad.add(new Button("7"));
        numpad.add(new Button("8"));
        numpad.add(new Button("9"));
        numpad.add(new Button("/"));
        numpad.add(new Button("4"));
        numpad.add(new Button("5"));
        numpad.add(new Button("6"));
        numpad.add(new Button("x"));
        numpad.add(new Button("1"));
        numpad.add(new Button("2"));
        numpad.add(new Button("3"));
        numpad.add(new Button("-"));
        numpad.add(new Button("0"));
        numpad.add(new Button("."));
        numpad.add(new Button("="));
        numpad.add(new Button("+"));

        frame.add(textField, BorderLayout.NORTH);
        frame.add(numpad, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
