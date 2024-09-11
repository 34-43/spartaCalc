package com.example.lv3;

import com.example.lv3.DataType.Token;
import com.example.lv3.Exceptions.ParserException;
import com.example.lv3.Exceptions.TokenizerException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

/*
계산기의 기능을 외부에서 활용하기 위해 각 모듈을 종속하는 중간 조작 클래스.
구현된 Parser, PostFixConverter, ArithmeticOperator 등을 객체화하여 사용합니다.
evaluation 메서드는 리스트로 만든 후위 연산 식과 사칙연산 단위연산기를 통해 실제 값을 계산하고 반환합니다.
결과를 저장하는 콜렉션에서 가장 오래된 값을 지우거나 Stream 형태로 반환하는 public 메서드 등을 가집니다.
 */

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
