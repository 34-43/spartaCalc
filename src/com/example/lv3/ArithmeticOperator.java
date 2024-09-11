package com.example.lv3;

// Number 로 제한된 제네릭 사칙연산 계산 클래스.
// 제네릭 타입 피연산자 2항과 열거형 연산자 1개를 private 필드로 가집니다.
// setter 로 필드를 지정합니다.
// calculator 메서드는 잘못된 나눗셈 등에 대한 예외를 던질 수 있습니다.

public class ArithmeticOperator<T extends Number> {
    public enum OperatorType {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private T operandLeft, operandRight;
    private OperatorType operator;

    public void setLeft(T n) {
        operandLeft = n;
    }
    public void setRight(T n) {
        operandRight = n;
    }
    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }

    public Number calculate() throws ArithmeticException {
        Number result;
        if (operandLeft instanceof Integer && operandRight instanceof Integer) {
            result = switch (operator) {
                case ADD -> operandLeft.intValue() + operandRight.intValue();
                case SUBTRACT -> operandLeft.intValue() - operandRight.intValue();
                case MULTIPLY -> operandLeft.intValue() * operandRight.intValue();
                case DIVIDE -> operandLeft.intValue() / operandRight.intValue();
            };
        } else {
            result = switch (operator) {
                case ADD -> operandLeft.floatValue() + operandRight.floatValue();
                case SUBTRACT -> operandLeft.floatValue() - operandRight.floatValue();
                case MULTIPLY -> operandLeft.floatValue() * operandRight.floatValue();
                case DIVIDE -> operandLeft.floatValue() / operandRight.floatValue();
            };
        }
        return result;
    }

}