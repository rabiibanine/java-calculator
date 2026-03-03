import javax.swing.*;
import java.awt.*;

public class Calculator {
    public Calculator() {
        JFrame frame = new JFrame("Calculator");
        Dimension defaultDimensions = new Dimension(300, 400);

        frame.setSize(defaultDimensions);
        frame.setMinimumSize(defaultDimensions);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage();

        JTextField textField = new JTextField();
        textField.setEditable(true);

        JPanel numpad = new JPanel(new GridLayout(4, 4, 5, 5), false);

        char[] numpadCharacters = {'7', '8', '9', '/', '4', '5', '6', 'x', '1', '2', '3', '-', '0', '.', '=', '+'};

        for (char c: numpadCharacters) numpad.add(new Button(String.valueOf(c)));

        frame.add(textField, BorderLayout.NORTH);
        frame.add(numpad, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
