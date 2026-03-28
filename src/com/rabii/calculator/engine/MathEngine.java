package com.rabii.calculator.engine;

import java.util.Arrays;

public class MathEngine {
    public static void main(String[] args) {
        String result = calculate("5");
        System.out.println(result);
    }
    public static String calculate(String inputString) {
        Scanner scanner = new Scanner();
        Token[] tokenList = scanner.getTokenList(inputString);
        return Arrays.toString(tokenList);
    }
}
