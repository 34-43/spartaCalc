package com.sparta.calculator.lv3.DataType;

import com.sparta.calculator.lv3.ArithmeticOperator;

/*
토큰은 객체로서 두 필드를 갖는 자료형입니다.
하나는 토큰의 종류를 나타내는 열거형 변수인데, 열거형 또한 토큰의 정적 멤버로 정의되어 있습니다.
나머지 하나는 토큰의 종류에 따라 다른 타입의 값을 저장할 수 있는 value 입니다.
타입이지만, 여러 편리한 메서드를 정의해 두었습니다.
toOperatorToken 메서드는 토큰 객체에서 호출할 시 타입을 고정해 줍니다.
getPriority 메서드는 정적 메서드로 두 연산자 타입 토큰을 전달하면 우선순위 비교 결과를 정수로 반환합니다.
toString 메서드는 오버라이드 메서드로 두 필드를 포함한 적절한 문자열을 출력할 수 있도록 오버라이드하였습니다.
 */

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

    public Token<ArithmeticOperator.OperatorType> toOperatorToken() {
        return new Token<>(TokenType.ARITHMETIC,(ArithmeticOperator.OperatorType) this.value);
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
