package com.rabii.calculator.engine;

public class OperatorNode extends Node {

    private final TokenType operator;
    private Node leftNode;
    private Node rightNode;

    public OperatorNode(TokenType operator, Node leftNode) {
        this.operator = operator;
        this.leftNode = leftNode;
        this.rightNode = null;
    }

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

    public Node getRightNode() { return rightNode; }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
