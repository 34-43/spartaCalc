package com.example.lv2;

import java.util.LinkedList;
import java.util.List;

public class Calculator {
    private int operandLeft, operandRight;
    private char operator;
    private final List<Integer> resultList;

    public Calculator() {
        resultList = new LinkedList<>();
    }

    public void setLeft(int n) {
        operandLeft = n;
    }
    public void setRight(int n) {
        operandRight = n;
    }
    public boolean setOperator(char ch) {
        boolean flag;
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            operator = ch;
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean calculate() {
        boolean flag = true;
        try {
            switch (operator) {
                case '+':
                    resultList.add(Math.addExact(operandLeft, operandRight));
                    break;
                case '-':
                    resultList.add(Math.subtractExact(operandLeft, operandRight));
                    break;
                case '*':
                    resultList.add(Math.multiplyExact(operandLeft, operandRight));
                    break;
                case '/':
                    resultList.add(operandLeft/operandRight);
                    break;
                default:
                    flag = false;
                    break;
            }
        } catch (Exception e) { //ArithmeticException(Overflow, divide 0)
            flag = false;
        }
        return flag;
    }
    public int getResult() {
        int result = 0;
        if (!resultList.isEmpty()) {
            result = resultList.get(resultList.size()-1);
        }
        return result;
    }
    public void removeResult() {
        if (!resultList.isEmpty()) {
        resultList.remove(0);
        }
    }

}
