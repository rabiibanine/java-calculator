import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Calculator extends JFrame {
    public Calculator() {
        Dimension defaultDimensions = new Dimension(300, 400);
        URL iconURL = getClass().getResource("/main/resources/images/icon/calculator-icon.png");

        if (!(iconURL == null)) {
            ImageIcon icon = new ImageIcon(iconURL);
            Image image = icon.getImage();
            setIconImage(image);
        }

        setTitle("Calculator");
        setSize(defaultDimensions);
        setMinimumSize(defaultDimensions);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField();
        textField.setEditable(true);

        JPanel numpad = new JPanel(new GridLayout(4, 4, 5, 5), false);

        char[] numpadCharacters = {'7', '8', '9', '/', '4', '5', '6', 'x', '1', '2', '3', '-', '0', '.', '=', '+'};

        for (char c: numpadCharacters) numpad.add(new Button(String.valueOf(c)));

        add(textField, BorderLayout.NORTH);
        add(numpad, BorderLayout.SOUTH);
        setVisible(true);
    }
}
