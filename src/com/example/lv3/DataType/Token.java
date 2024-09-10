package com.example.lv3.DataType;

import com.example.lv3.ArithmeticOperator;

public class Token<T> {
    public enum TokenType {
        INTEGER, FLOAT, ARITHMETIC, PARENTHESES_LEFT, PARENTHESES_RIGHT,
    }
    private final TokenType type;
    private final T value;

    public Token(TokenType type, T value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public static int getPriority(Token<ArithmeticOperator.OperatorType> mightBigger, Token<ArithmeticOperator.OperatorType> mightSmaller) {
        return mightBigger.getValue().compareTo(mightSmaller.getValue());
    }

    @Override
    public String toString() {
        return "[타입:" + type + "," + "값:" + value + "]";
    }

    public static void main(String[] args) {
        Token<ArithmeticOperator.OperatorType> A = new Token<>(TokenType.ARITHMETIC, ArithmeticOperator.OperatorType.ADD);
        Token<ArithmeticOperator.OperatorType> B = new Token<>(TokenType.ARITHMETIC, ArithmeticOperator.OperatorType.MULTIPLY);
        System.out.println(getPriority(A,A));
        System.out.println(getPriority(A,B));
        System.out.println(getPriority(B,A));
    }
}
