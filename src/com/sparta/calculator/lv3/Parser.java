package com.sparta.calculator.lv3;

//전체 계산식 문자열을 토큰화한 후 스택을 이용하여 후위표기로 변환하고 계산까지 진행하는 클래스.

import com.sparta.calculator.lv3.DataType.Token;
import com.sparta.calculator.lv3.Exceptions.ParserException;
import com.sparta.calculator.lv3.Exceptions.TokenizerException;

import java.util.ArrayList;

/*
입력된 수식을 숫자와 기호로 구분하여 순서대로 토큰화합니다.
조건에 따라 가변 길이의 buffer 를 이용해 숫자와 기호를 앞에서부터 분리합니다.
Tokenizer 유틸 클래스의 parseToken 메서드를 사용해, 분리한 문자열을 토큰화합니다.
토큰들을 리스트에 저장하여 반환합니다.
입력된 수식을 순차적으로 처리하기 위한 내부 멤버 메서드를 2개 가집니다.
 */

public class Parser {
    private StringBuffer inputBuffer;
    private char ptr;
    private final ArrayList<Token<?>> tokenizedList = new ArrayList<>();

    public void setFullInput(String fullInput) {
        this.inputBuffer = new StringBuffer(fullInput);
    }

    public ArrayList<Token<?>> parse() throws ParserException, TokenizerException {
        StringBuilder tempBuffer = new StringBuilder();
        tokenizedList.clear();
        if (inputBuffer.isEmpty()) {
            throw new ParserException("입력된 수식이 없음");
        }
        pullFirst();
        while(!inputBuffer.isEmpty()) {
            if (Character.isDigit(ptr) || ptr == '.') {
                tempBuffer.append(ptr);
            } else {
                if (!tempBuffer.isEmpty()) {
                    tokenizedList.add(Tokenizer.parseToken(tempBuffer.toString()));
                }
                tokenizedList.add(Tokenizer.parseToken(Character.toString(ptr)));
                tempBuffer.setLength(0);
            }
            if (!pullNext()) {break;}
        }
        if (!tempBuffer.isEmpty()) {
            tokenizedList.add(Tokenizer.parseToken(tempBuffer.toString()));
        }
        return tokenizedList;
    }

    private void pullFirst() {
        if (!inputBuffer.isEmpty()) {
            ptr = inputBuffer.charAt(0);
        }
    }

    private boolean pullNext() {
        if (!inputBuffer.isEmpty()) {
            inputBuffer.deleteCharAt(0);
            if (!inputBuffer.isEmpty()) {
                ptr = inputBuffer.charAt(0);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Parser p = new Parser();
        p.setFullInput("2*(9.5-3.21)-1/9");
        try {
            for (Token<?> token : p.parse()) {
                System.out.println(token);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
