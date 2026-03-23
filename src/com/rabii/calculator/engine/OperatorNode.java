package com.rabii.calculator.engine;

public class OperatorNode extends Node {

    private final TokenType operator;
    private final Node leftNode;
    private final Node rightNode;

    public OperatorNode(TokenType operator, Node leftNode, Node rightNode) {
        this.operator = operator;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public TokenType getOperator() {
        return operator;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }
}
