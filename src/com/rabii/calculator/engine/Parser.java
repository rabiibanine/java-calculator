package com.rabii.calculator.engine;

import com.rabii.calculator.engine.exceptions.SyntaxException;
import com.rabii.calculator.engine.nodes.Node;

import static com.rabii.calculator.engine.TokenType.*;

public class Parser {

    private int currentTokenIndex;
    private Token currentToken;
    private final Token[] tokenList;

    public Parser(Token[] tokenList) {
        this.tokenList = tokenList;
        currentTokenIndex = 0;
        currentToken = tokenList[currentTokenIndex];
    }

    public Node getAST() {
        return expression();
    }

    private Node expression() {
        Node leftNode = term();

        while (currentToken.getType() == ADD_OP || currentToken.getType() == SUBTRACT_OP) {

            TokenType operator = currentToken.getType();

            advanceToken();

            Node rightNode = term();

            leftNode = new OperatorNode(operator, leftNode, rightNode);
        }

        return leftNode;
    };

    private Node term() {
        Node leftNode = factor();

        while (currentToken.getType() == MULTIPLY_OP || currentToken.getType() == DIVIDE_OP || currentToken.getType() == L_PAREN || currentToken.getType() == NUMBER) {

            TokenType operator = currentToken.getType() == DIVIDE_OP ? DIVIDE_OP : MULTIPLY_OP;

            if (currentToken.getType() == MULTIPLY_OP || currentToken.getType() == DIVIDE_OP) advanceToken();

            Node rightNode = factor();

            leftNode = new OperatorNode(operator, leftNode, rightNode);
        }

        return leftNode;
    };

    private Node factor() {
        if (currentToken.getType() == NUMBER) {
            NumberNode numberNode = new NumberNode(currentToken.getValue());
            advanceToken();
            return numberNode;
        }
        else if (currentToken.getType() == L_PAREN) {
            advanceToken();
            Node insideParentheses = expression();
            if (currentToken.getType() != R_PAREN) throw new SyntaxException("Syntax Error: Non-closed parenthesis. Index: " + currentToken.getIndex());
            advanceToken();
            return insideParentheses;
        }
        else {
            throw new SyntaxException("Syntax Error. Index: ");
        }
    };

    private void advanceToken() {
        currentTokenIndex++;
        currentToken = tokenList[currentTokenIndex];
    };

}
