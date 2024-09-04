package com.example.lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    private int operandLeft, operandRight;
    private char operator;
    private final Queue<Integer> resultQueue;

    public Calculator() {
        resultQueue = new LinkedList<Integer>();
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
        switch (operator) {
            case '+':
                resultQueue.add(operandLeft+operandRight);
                break;
            case '-':
                resultQueue.add(operandLeft-operandRight);
                break;
            case '*':
                resultQueue.add(operandLeft*operandRight);
                break;
            case '/':
                try {
                    resultQueue.add(operandLeft/operandRight);
                } catch (ArithmeticException e) {
                    flag = false;
                }
                break;
            default:
                flag = false;
                break;
        }
        return flag;
    }
    public int getResult() {
        int result = 0;
        if (!resultQueue.isEmpty()) {
            result = resultQueue.peek();
        }
        return result;
    }
    public void removeResult() {
        resultQueue.poll();
    }

}
