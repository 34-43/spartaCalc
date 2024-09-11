package com.example.lv3;

import com.example.lv3.DataType.Token;
import com.example.lv3.Exceptions.ParserException;
import com.example.lv3.Exceptions.TokenizerException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class CalculatorApp {
    private final Parser parser = new Parser();
    private final PostFixConverter postFixConverter = new PostFixConverter();
    private final ArithmeticOperator<Integer> intOpera = new ArithmeticOperator<>();
    private final ArithmeticOperator<Float> floOpera = new ArithmeticOperator<>();
    private final ArrayList<Number> resultList = new ArrayList<>();

    public Number evaluation(String input) throws ParserException, TokenizerException {
        ArrayList<Token<?>> tokens;
        ArrayList<Token<?>> posts;
        Stack<Number> evalStack = new Stack<>();

        parser.setFullInput(input);
        tokens = parser.parse();
        postFixConverter.setTokenList(tokens);
        System.out.println("Tokenized_Serial:"+tokens);
        posts = postFixConverter.convert();
        System.out.println("PostFixed_Serial:"+posts);

        for (Token<?> token : posts) {
            switch (token.getType()) {
                case INTEGER: case FLOAT:
                    evalStack.add((Number)token.getValue());
                    break;
                case ARITHMETIC:
                    Number right = evalStack.pop();
                    Number left = evalStack.pop();
                    if (left instanceof Integer && right instanceof Integer) {
                        intOpera.setRight(right.intValue());
                        intOpera.setLeft(left.intValue());
                        intOpera.setOperator((ArithmeticOperator.OperatorType) token.getValue());
                        evalStack.add(intOpera.calculate());
                    } else {
                        floOpera.setRight(right.floatValue());
                        floOpera.setLeft(left.floatValue());
                        floOpera.setOperator((ArithmeticOperator.OperatorType) token.getValue());
                        evalStack.add(floOpera.calculate());
                    }
            }
        }
        Number result = evalStack.pop();
        resultList.add(result);
        return result;
    }

    public Stream<Number> getResultStream() {
        return resultList.stream();
    }

    public void removeResult() {
        if (!resultList.isEmpty()) {
            resultList.remove(0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalculatorApp calculator = new CalculatorApp();
        while (true) {
            try {
                System.out.print("테스트 수식 입력: ");
                System.out.println(calculator.evaluation(sc.nextLine()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
