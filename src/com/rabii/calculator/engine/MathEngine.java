package com.rabii.calculator.engine;

import com.rabii.calculator.engine.nodes.Node;

public class MathEngine {
    public static void main(String[] args) {
        float result = calculate("5+3/3*3");
        System.out.println(result);
    }

    public static float calculate(String inputString) {
        Scanner scanner = new Scanner();
        Token[] tokenList = scanner.getTokenList(inputString);
        Parser parser = new Parser(tokenList);
        Node AST = parser.getAST();
        return AST.evaluate();
    }
}
