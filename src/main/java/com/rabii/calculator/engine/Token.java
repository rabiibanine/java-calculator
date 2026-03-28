package com.rabii.calculator.engine;

public class Token {

    private final TokenType type;
    private final String value;
    private final int index;

    public Token (TokenType type, String value, int index) {
        this.type = type;
        this.value = value;
        this.index = index;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %d]", this.type, this.value, this.index);
    }
}
