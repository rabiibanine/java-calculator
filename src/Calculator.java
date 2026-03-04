import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;
import com.formdev.flatlaf.FlatDarkLaf;

public class Calculator extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
    public static final int DISPLAY_FONT_SIZE = 24;
    public static final int BUTTON_FONT_SIZE = 20;
    public static final String FONT_NAME = "Inter";
    public static final String ICON_PATH = "/main/resources/images/icon/calculator-icon.png";
    public static final String TITLE = "Calculator";

    public static final String[] NUMPAD_CHARACTERS = {"(", ")", "AC", "D", "7", "8", "9", "/", "4", "5", "6", "x", "1", "2", "3", "-", "0", ".", "=", "+"};

    public static final Dimension defaultDimensions = new Dimension(WIDTH, HEIGHT);
    public static final Font displayFont = new Font(FONT_NAME, Font.PLAIN, DISPLAY_FONT_SIZE);
    public static final Font numpadFont = new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE);

    private JPanel ContentPane;
    private JTextField inputDisplay;
    private JTextField resultDisplay;

    // CONSTRUCTOR
    public Calculator() {

        setupParams();
        setupDisplays();
        setupNumpad();
        setupStyling();

    }

    // HELPER FUNCTIONS
    private void setupParams() {
        ContentPane = (JPanel) getContentPane();
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

        inputDisplay = new JTextField();
        inputDisplay.setEditable(false);
        inputDisplay.setFont(displayFont);
        inputDisplay.setMargin(new Insets(20, 10, 20, 10));
        inputDisplay.setText("For testing");

        // Result Display
        resultDisplay = new JTextField();
        resultDisplay.setEditable(false);
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
                handleButton(thisButton);
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

    private void setupStyling(){

        Border noBorder = BorderFactory.createEmptyBorder();
        Border externalPadding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

        inputDisplay.setBorder(noBorder);
        resultDisplay.setBorder(noBorder);

        ContentPane.setBorder(externalPadding);
    }

    // METHODS

    public void setInputDisplayText(String inputText) {
        inputDisplay.setText(inputText);
    }

    public void clearInputDisplay(){
        inputDisplay.setText("");
    }

    public void appendInputDisplayText(String inputText) {
        String currentText = inputDisplay.getText();
        inputDisplay.setText(currentText += inputText);
    }

    public void chopInputDisplayText() {
        String currentText = inputDisplay.getText();
        int currentTextLength = currentText.length();
        if (currentTextLength == 0) return;
        String choppedText = currentText.substring(0 , currentTextLength - 1);
        inputDisplay.setText(choppedText);
    }

    public void setResultDisplayText(String inputText) {
        resultDisplay.setText(inputText);
    }

    public void clearResultDisplay(){
        resultDisplay.setText("");
    }


    public void handleButton(JButton button) {
        String buttonText = button.getText();
        char buttonChar = buttonText.charAt(0);
        if (Character.isDigit(buttonChar) || buttonText.equals(".") || buttonText.equals("(") || buttonText.equals(")") || buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("/") || buttonText.equals("x"))
            appendInputDisplayText(buttonText);
        else if (buttonText.equals("D")) chopInputDisplayText();
        else if (buttonText.equals("AC")) clearInputDisplay();
    }
}
