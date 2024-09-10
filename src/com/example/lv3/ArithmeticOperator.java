package com.example.lv3;

// Number로 제한된 제네릭 사칙연산 계산 클래스.
// 제네릭 타입 private 필드인 피연산자 2항과 연산자 1개를 가집니다.
// 결과 기록을 담당하는 콜렉션은 Integer, Float의 부모인 Number 타입으로 정의됩니다.
// setter로 T 타입의 피연산자를 지정합니다.
// setter로 내부에 정의된 열거형 OperaterType 타입의 연산자를 지정합니다.
// calculator 메서드는 오버플로우, 잘못된 나눗셈 등에 대한 예외를 던질 수 있습니다.

public class ArithmeticOperator<T extends Number> {
    public enum OperatorType {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private T operandLeft, operandRight;
    private OperatorType operator;
    //    private final List<Number> resultList = new LinkedList<>();

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
//    public int getResult() {
//        int result = 0;
//        if (!resultList.isEmpty()) {
//            result = (int) resultList.get(resultList.size() - 1);
//        }
//        return result;
//    }
//    public Stream<?> getResultStream() {
//        return resultList.stream();
//    }
//
//    public void removeResult() {
//        if (!resultList.isEmpty()) {
//            resultList.remove(0);
//        }
//    }

}