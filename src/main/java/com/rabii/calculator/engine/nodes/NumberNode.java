package com.rabii.calculator.engine.nodes;

public class NumberNode extends Node {

    private final String number;

    public NumberNode(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public float evaluate() {
        return Float.parseFloat(this.number);
    }

}
