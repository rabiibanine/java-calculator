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
        GridBagLayout mainLayout = new GridBagLayout();

        if (!(iconURL == null)) {
            ImageIcon icon = new ImageIcon(iconURL);
            Image image = icon.getImage();
            setIconImage(image);
        }

        setTitle(TITLE);
        setSize(defaultDimensions);
        setMinimumSize(defaultDimensions);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(mainLayout);

        JTextField display = new JTextField();
        display.setEditable(true);
        display.setFont(defaultFont);
        display.setMargin(new Insets(20, 20, 20, 20));

        JPanel numpad = new JPanel(new GridLayout(4, 4, 5, 5), false);

        for (char c: NUMPAD_CHARACTERS) numpad.add(new Button(String.valueOf(c)));

        // GridBagLayout Constraints
        GridBagConstraints displayConstraints = new GridBagConstraints();
        displayConstraints.fill = GridBagConstraints.HORIZONTAL;
        displayConstraints.weightx = 1.0;

        GridBagConstraints numpadConstraints = new GridBagConstraints();
        numpadConstraints.fill = GridBagConstraints.BOTH;
        numpadConstraints.weightx = 1.0;
        numpadConstraints.weighty = 1.0;
        numpadConstraints.gridy = 1;

        add(display, displayConstraints);
        add(numpad, numpadConstraints);
        setVisible(true);
    }
}
