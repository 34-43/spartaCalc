package com.example.lv3;

import com.example.lv3.DataType.Token;
import com.example.lv3.Exceptions.ParserException;
import com.example.lv3.Exceptions.TokenizerException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class CalculatorApp {
    private final Parser parser = new Parser();
    private final PostFixConverter postFixConverter = new PostFixConverter();
    private final ArithmeticOperator<Integer> intOper = new ArithmeticOperator<>();
    private final ArithmeticOperator<Float> floOper = new ArithmeticOperator<>();

    public Number evaluation(String input) throws ParserException, TokenizerException {
        ArrayList<Token<?>> tokens;
        ArrayList<Token<?>> posts;
        Stack<Number> evalStack = new Stack<>();

        parser.setFullInput(input);
        tokens = parser.parse();
        postFixConverter.setTokenList(tokens);
        posts = postFixConverter.convert();

        for (Token<?> token : posts) {
            switch (token.getType()) {
                case INTEGER: case FLOAT:
                    evalStack.add((Number)token.getValue());
                    break;
                case ARITHMETIC:
                    Number left = evalStack.pop();
                    Number right = evalStack.pop();
                    if (left instanceof Integer && right instanceof Integer) {
                        intOper.setRight(right.intValue());
                        intOper.setLeft(left.intValue());
                        intOper.setOperator((ArithmeticOperator.OperatorType) token.getValue());
                        evalStack.add(intOper.calculate());
                    } else {
                        floOper.setRight(right.floatValue());
                        floOper.setLeft(left.floatValue());
                        floOper.setOperator((ArithmeticOperator.OperatorType) token.getValue());
                        evalStack.add(floOper.calculate());
                    }
            }
        }
        return evalStack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalculatorApp calculator = new CalculatorApp();
        try {
            System.out.print("테스트 수식 입력: ");
            System.out.println(calculator.evaluation(sc.nextLine()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
