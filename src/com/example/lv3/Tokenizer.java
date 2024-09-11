package com.example.lv3;

import com.example.lv3.DataType.Token;
import com.example.lv3.Exceptions.TokenizerException;
import com.example.lv3.DataType.Token.TokenType;
import com.example.lv3.ArithmeticOperator.OperatorType;

/*
 정규표현과 제어문을 통해 입력된 문자열 토큰을 적절한 토큰 객체로 반환해 주는 유틸 클래스.
 입력되는 문자열 토큰은 정확해야 하며, 잘못되었을 경우 사용자 지정 예외를 던집니다.
 */

public class Tokenizer {
    private static final String INTEGER_REG ="^[0-9]*$";
    private static final String FLOAT_REG ="^[0-9]*\\.?[0-9]+$";
    private static final String OPERATION_REG = "[+\\-*/]";
    
    public static Token<?> parseToken(String input) throws TokenizerException {
        if (input.matches(INTEGER_REG)) {
            try { return new Token<>(TokenType.INTEGER,Integer.parseInt(input));
            } catch (NumberFormatException e) {throw new TokenizerException(input,"정수 자릿수 초과");}
        } else if (input.matches(FLOAT_REG)) {
            try { return new Token<>(TokenType.FLOAT,Float.parseFloat(input));
            } catch (NumberFormatException e) {throw new TokenizerException(input,"실수 자릿수 초과");}
        } else if (input.matches(OPERATION_REG)) {
            OperatorType ot = switch (input) {
                case "+" -> OperatorType.ADD;
                case "-" -> OperatorType.SUBTRACT;
                case "*" -> OperatorType.MULTIPLY;
                case "/" -> OperatorType.DIVIDE;
                default -> throw new TokenizerException(input, "연산자 종류 부적합");
            };
            return new Token<>(TokenType.ARITHMETIC,ot);
        } else if (input.equals("(")) {
            return new Token<>(TokenType.PARENTHESES_LEFT,TokenType.PARENTHESES_LEFT);
        } else if (input.equals(")")) {
            return new Token<>(TokenType.PARENTHESES_RIGHT,TokenType.PARENTHESES_RIGHT);
        } else {
            throw new TokenizerException(input, "토큰화 불가능한 값");
        }
    }

    public static void main(String[] args) {
        String[] testCases = {
                "1",
                "5.5",
                "+",
                "(",
                "g"
        };
        for (String testCase : testCases) {
            try {
                System.out.println(parseToken(testCase));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
