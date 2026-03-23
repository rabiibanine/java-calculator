package com.rabii.calculator.engine;

import com.rabii.calculator.engine.exceptions.LexicalException;
import static com.rabii.calculator.engine.TokenType.*;

public class Scanner {

    private final static int ARRAY_SIZE = 200;

    private Token[] tokenList = new Token[ARRAY_SIZE];
    private int currentListIndex = 0;
    private int currentStringIndex = 0;

    public Scanner(){
        tokenList = new Token[ARRAY_SIZE];
        currentListIndex = 0;
        currentStringIndex = 0;
    }

    public Token[] getTokenList(String inputString) {
        currentListIndex = 0;
        currentStringIndex = 0;
        int inputStringLength = inputString.length();

        while (true) {

            // Handling string end
            if (currentStringIndex >= inputStringLength) {
                Token EOLToken = new Token(EOL, null, currentStringIndex);
                tokenList[currentListIndex] = EOLToken;
                break;
            }

            // Handling array boundary
            if (currentListIndex >= ARRAY_SIZE) {
                throw new LexicalException("Memory Overload: The given string has generated more tokens then the internal memory could handle.");
            }

            // Handling spaces?
            if (inputString.charAt(currentStringIndex) == ' ') {
                currentStringIndex++;
                continue;
            }

            // Get the next token
            tokenList[currentListIndex] = getToken(inputString);

            currentListIndex++;
        }

        return tokenList;

    }

    private Token getToken(String inputString) {

        Token token;
        String inputWord;
        TokenType tokenType = getType(inputString);

        if (tokenType == NUMBER) {
            inputWord = getNumber(inputString);
            token = new Token(tokenType, inputWord, currentStringIndex);
            currentStringIndex += inputWord.length();
        } else {
            inputWord = inputString.substring(currentStringIndex, currentStringIndex + 1);
            token = new Token(tokenType, inputWord, currentStringIndex);
            currentStringIndex++;
        }

        return token;
    }

    private TokenType getType(String inputWord) {
        char c = inputWord.charAt(currentStringIndex);
        switch (c) {
            case '(' :
                return L_PAREN;
            case ')':
                return R_PAREN;
            case '+':
                return ADD_OP;
            case '-' :
                return SUBTRACT_OP;
            case 'x':
                return MULTIPLY_OP;
            case '/':
                return DIVIDE_OP;
            default:
                return NUMBER;
        }
    }

    private String getNumber(String inputString) {
        boolean hasDecimal = false;
        int numberLength = 1;
        while (true) {

            if (currentStringIndex + numberLength >= inputString.length()) break;

            char numberChar = inputString.charAt(currentStringIndex + numberLength);

            if (numberChar == '.' && hasDecimal) {
                throw new LexicalException("Invalid Format: More than 1 decimals are present in the number at index: " + currentStringIndex + ".");
            }

            if (numberChar == '.') hasDecimal = true;

            if (!(Character.isDigit(numberChar) || numberChar == '.')) break;

            numberLength++;

        }
        return inputString.substring(currentStringIndex, currentStringIndex + numberLength);
    }

}
