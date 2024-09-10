package com.example.lv3;

//전체 계산식 문자열을 토큰화한 후 스택을 이용하여 후위표기로 변환하고 계산까지 진행하는 클래스.

import com.example.lv3.DataType.Token;
import com.example.lv3.Exceptions.ParserException;
import com.example.lv3.Exceptions.TokenizerException;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private StringBuffer inputBuffer;
    private char ptr;
    private final ArrayList<Token<?>> tokenizedList = new ArrayList<>();

    public void setFullInput(String fullInput) {
        this.inputBuffer = new StringBuffer(fullInput);
    }

    public ArrayList<Token<?>> parse() throws ParserException, TokenizerException {
        StringBuilder tempBuffer = new StringBuilder();
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
