package com.rabii.calculator.engine;

import static com.rabii.calculator.engine.TokenType.*;

public class Scanner {

    private Token[] tokenList = new Token[200];
    private TokenType currentTokenType;
    private String currentValue;
    private int currentListIndex = 0;
    private int currentStringIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        Token[] testTokenList = scanner.getTokenList("5.3+3()123123()123.123.123");
        System.out.println(java.util.Arrays.toString(testTokenList));
    }

    public Token[] getTokenList(String inputString) {
        int inputStringLength = inputString.length();

        while (true) {
            tokenList[currentListIndex] = getToken(inputString);
            if (currentStringIndex >= inputStringLength) {
               break;
            }
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
        int numberLength = 1;
        while (true) {

            if (currentStringIndex + numberLength >= inputString.length()) break;

            char numberChar = inputString.charAt(currentStringIndex + numberLength);

            if (!(Character.isDigit(numberChar) || numberChar == '.')) break;

            numberLength++;

        }
        return inputString.substring(currentStringIndex, currentStringIndex + numberLength);
    }

}
