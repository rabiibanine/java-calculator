import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Calculator extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
    public static final int DISPLAY_FONT_SIZE = 24;
    public static final int BUTTON_FONT_SIZE = 20;
    public static final String FONT_NAME = "Inter";
    public static final String ICON_PATH = "/main/resources/images/icon/calculator-icon.png";
    public static final String TITLE = "Calculator";

    public static final String[] NUMPAD_CHARACTERS = {"(", ")", "AC", "Del", "7", "8", "9", "/", "4", "5", "6", "x", "1", "2", "3", "-", "0", ".", "=", "+"};

    public static final Dimension defaultDimensions = new Dimension(WIDTH, HEIGHT);
    public static final Font displayFont = new Font(FONT_NAME, Font.PLAIN, DISPLAY_FONT_SIZE);
    public static final Font numpadFont = new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE);

    private String inputString = "";

    private JTextField inputDisplay;
    private JTextField resultDisplay;

    // CONSTRUCTOR
    public Calculator() {

        this.setupParams();
        this.setupDisplays();
        this.setupNumpad();

    }

    // HELPER FUNCTIONS
    private void setupParams() {
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

    }

    private void setupDisplays() {

        this.inputDisplay = new JTextField();
        inputDisplay.setEditable(true);
        inputDisplay.setFont(displayFont);
        inputDisplay.setMargin(new Insets(20, 10, 20, 10));
        inputDisplay.setText("For testing");

        // Result Display
        this.resultDisplay = new JTextField();
        resultDisplay.setEditable(true);
        resultDisplay.setFont(displayFont);
        resultDisplay.setMargin(new Insets(20, 10, 20, 10));
        resultDisplay.setHorizontalAlignment(JTextField.RIGHT);

        // # GridBagLayout Constraints

        // ## Input Display Constraints
        GridBagConstraints inputDisplayConstraints = new GridBagConstraints();
        inputDisplayConstraints.fill = GridBagConstraints.HORIZONTAL;
        inputDisplayConstraints.weightx = 1.0;
        inputDisplayConstraints.gridy = 0;

        // ## Result Display Constraints
        GridBagConstraints resultDisplayConstraints = new GridBagConstraints();
        resultDisplayConstraints.fill = GridBagConstraints.HORIZONTAL;
        resultDisplayConstraints.weightx = 1.0;
        resultDisplayConstraints.gridy = 1;

        add(inputDisplay, inputDisplayConstraints);
        add(resultDisplay, resultDisplayConstraints);
    }

    private void setupNumpad() {

        // Numpad
        JPanel numPad = new JPanel(new GridLayout(5, 4, 5, 5), false);
        for (String c: NUMPAD_CHARACTERS) {
            JButton button = new JButton(c);
            button.setFont(numpadFont);
            numPad.add(button);
            button.addActionListener(e -> {
                JButton thisButton = (JButton) e.getSource();
                this.handleButton(thisButton);

            });
        }

        // ## Numpad Constraints
        GridBagConstraints numpadConstraints = new GridBagConstraints();
        numpadConstraints.fill = GridBagConstraints.BOTH;
        numpadConstraints.weightx = 1.0;
        numpadConstraints.weighty = 1.0;
        numpadConstraints.gridy = 2;

        add(numPad, numpadConstraints);
        setVisible(true);
    }

    // METHODS

    public void setInputDisplayText(String inputText) {
        this.inputDisplay.setText(inputText);
    }

    public void clearInputDisplay(){
        this.inputDisplay.setText("");
    }

    public void clearResultDisplay(){
        this.resultDisplay.setText("");
    }


    public void handleButton() {

    }

}
