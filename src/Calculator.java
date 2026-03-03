import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Calculator extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 400;
    public static final int DISPLAY_FONT_SIZE = 24;
    public static final int BUTTON_FONT_SIZE = 20;
    public static final String FONT_NAME = "Inter";
    public static final String ICON_PATH = "/main/resources/images/icon/calculator-icon.png";
    public static final String TITLE = "Calculator";

    public static final char[] NUMPAD_CHARACTERS = {'7', '8', '9', '/', '4', '5', '6', 'x', '1', '2', '3', '-', '0', '.', '=', '+'};

    public Calculator() {
        Dimension defaultDimensions = new Dimension(WIDTH, HEIGHT);
        Font defaultFont = new Font(FONT_NAME, Font.PLAIN, DISPLAY_FONT_SIZE);
        URL iconURL = getClass().getResource(ICON_PATH);

        if (!(iconURL == null)) {
            ImageIcon icon = new ImageIcon(iconURL);
            Image image = icon.getImage();
            setIconImage(image);
        }

        setTitle(TITLE);
        setSize(defaultDimensions);
        setMinimumSize(defaultDimensions);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField();
        textField.setEditable(true);
        textField.setFont(defaultFont);

        JPanel numpad = new JPanel(new GridLayout(4, 4, 5, 5), false);


        for (char c: NUMPAD_CHARACTERS) numpad.add(new Button(String.valueOf(c)));

        add(textField, BorderLayout.NORTH);
        add(numpad, BorderLayout.SOUTH);
        setVisible(true);
    }
}
