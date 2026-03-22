package com.rabii.calculator.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;
import com.formdev.flatlaf.FlatDarkLaf;

public class Calculator extends JFrame {

    public static final int WIDTH = 450;
    public static final int HEIGHT = 600;
    public static final int DISPLAY_FONT_SIZE = 24;
    public static final int BUTTON_FONT_SIZE = 20;
    public static final String FONT_NAME = "Inter";
    public static final String ICON_PATH = "/main/resources/images/icon/calculator-icon.png";
    public static final String TITLE = "com.rabii.calculator.gui.Calculator";

    public static final String[] NUMPAD_CHARACTERS = {"←", "→", "...", "...", "(", ")", "AC", "Del", "7", "8", "9", "/", "4", "5", "6", "x", "1", "2", "3", "-", "0", ".", "=", "+"};

    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(WIDTH, HEIGHT);
    public static final Font DISPLAY_FONT = new Font(FONT_NAME, Font.PLAIN, DISPLAY_FONT_SIZE);
    public static final Font NUMPAD_FONT = new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE);
    public static final Color BACKGROUND_COLOR = new Color(83, 90, 97);
    public static final Color DISPLAY_COLOR = new Color(47, 51, 59);
    public static final Color BUTTON_COLOR_1 = new Color(103, 110, 117);
    public static final Color BUTTON_COLOR_2 = new Color(121, 189,160);
    public static final Color TEXT_COLOR_1 = new Color(245, 243, 240);
    public static final Color TEXT_COLOR_2 = new Color(47, 51, 59);

    private JPanel contentPane;
    private JPanel numPad;
    private JPanel displayScreen;
    private JTextField inputDisplay;
    private JTextField resultDisplay;

    // CONSTRUCTOR
    public Calculator() {

        FlatDarkLaf.setup();

        SwingUtilities.invokeLater(() -> {
            setupParams();
            setupDisplays();
            setupNumpad();
            setupStyling();
            SwingUtilities.updateComponentTreeUI(this);
            setVisible(true);
        });

    }

    // HELPER FUNCTIONS
    private void setupParams() {
        contentPane = (JPanel) getContentPane();
        URL iconURL = getClass().getResource(ICON_PATH);
        GridBagLayout mainLayout = new GridBagLayout();

        UIManager.put("Button.arc", 999);
        UIManager.put("Button.background", BUTTON_COLOR_1);
        UIManager.put("Button.foreground", TEXT_COLOR_1);
        UIManager.put("Button.borderWidth", 0);
        UIManager.put("Button.focusWidth", 0);;

        if (!(iconURL == null)) {
            ImageIcon icon = new ImageIcon(iconURL);
            Image image = icon.getImage();
            setIconImage(image);
        }

        setTitle(TITLE);
        setSize(DEFAULT_DIMENSIONS);
        setMinimumSize(DEFAULT_DIMENSIONS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(mainLayout);

    }

    private void setupDisplays() {

        displayScreen = new JPanel();
        displayScreen.setLayout(new GridLayout(2, 1));

        inputDisplay = new JTextField();
        inputDisplay.setEditable(false);
        inputDisplay.setFont(DISPLAY_FONT);
        inputDisplay.setText("For testing");

        // Result Display
        resultDisplay = new JTextField();
        resultDisplay.setEditable(false);
        resultDisplay.setFont(DISPLAY_FONT);
        resultDisplay.setHorizontalAlignment(JTextField.RIGHT);
        resultDisplay.setText("For testing");

        // # GridBagLayout Constraints

        // ## Display Screen Constraints
        GridBagConstraints displayScreenConstraints = new GridBagConstraints();
        displayScreenConstraints.fill = GridBagConstraints.HORIZONTAL;
        displayScreenConstraints.weightx = 1.0;
        displayScreenConstraints.gridy = 0;


        displayScreen.add(inputDisplay);
        displayScreen.add(resultDisplay);

        add(displayScreen, displayScreenConstraints);
    }

    private void setupNumpad() {

        // Numpad
        numPad = new JPanel(new GridLayout(6, 4, 5, 5), false);
        for (String c: NUMPAD_CHARACTERS) {
            JButton button = new JButton(c);
            button.setFont(NUMPAD_FONT);
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
        numpadConstraints.gridy = 1;
        numpadConstraints.insets = new Insets(15, 0, 0, 0);

        add(numPad, numpadConstraints);
    }

    private void setupStyling(){

        Border noBorder = BorderFactory.createEmptyBorder();
        Border externalPadding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

        contentPane.setBorder(externalPadding);
        contentPane.setBackground(BACKGROUND_COLOR);

        displayScreen.setBackground(DISPLAY_COLOR);
        displayScreen.putClientProperty("FlatLaf.style", "arc: 15");

        inputDisplay.setBorder(externalPadding);
        inputDisplay.setOpaque(false);
        inputDisplay.setForeground(TEXT_COLOR_1);

        resultDisplay.setBorder(externalPadding);
        resultDisplay.setOpaque(false);
        resultDisplay.setForeground(TEXT_COLOR_1);

        numPad.setBackground(BACKGROUND_COLOR);


        for(Component c: numPad.getComponents()) {
            JButton button = (JButton) c;
            if (button.getText().equals("AC") ||
                button.getText().equals("Del") ||
                button.getText().equals("-") ||
                button.getText().equals("+") ||
                button.getText().equals("x") ||
                button.getText().equals("/") ||
                button.getText().equals("=")) button.setBackground(BUTTON_COLOR_2);
            button.setBorderPainted(false);
        }


    }

    // METHODS

    public void setInputDisplayText(String inputText) {
        inputDisplay.setText(inputText);
    }

    public void setInputDisplayCaretPosition(int caretPosition) {
        inputDisplay.requestFocus();
        try {
            inputDisplay.setCaretPosition(caretPosition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void moveInputDisplayCaretPositionLeft() {
        int currentPosition = inputDisplay.getCaretPosition();
        setInputDisplayCaretPosition(currentPosition - 1);
    }

    public void moveInputDisplayCaretPositionRight() {
        int currentPosition = inputDisplay.getCaretPosition();
        setInputDisplayCaretPosition(currentPosition + 1);
    }

    public void clearInputDisplay(){
        inputDisplay.setText("");
    }

    public void appendInputDisplayText(String inputText) {
        String currentText = inputDisplay.getText();
        inputDisplay.setText(currentText += inputText);
    }

    public void insertInputDisplayText(String inputText) {
        int currentPosition = inputDisplay.getCaretPosition();
        String currentText = inputDisplay.getText();
        String resultInputText = currentText.substring(0, currentPosition) + inputText + currentText.substring(currentPosition);
        inputDisplay.setText(resultInputText);
        inputDisplay.setCaretPosition(currentPosition + 1);
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
            insertInputDisplayText(buttonText);
        else if (buttonText.equals("Del")) chopInputDisplayText();
        else if (buttonText.equals("AC")) clearInputDisplay();
        else if (buttonText.equals("←")) moveInputDisplayCaretPositionLeft();
        else if (buttonText.equals("→")) moveInputDisplayCaretPositionRight();
    }
}
