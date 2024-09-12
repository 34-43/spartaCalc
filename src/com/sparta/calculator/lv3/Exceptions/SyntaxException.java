package com.sparta.calculator.lv3.Exceptions;

public class SyntaxException extends RuntimeException {
    public SyntaxException(String message) {
        super("오류: 잘못된 수식(__"+ message +"__)입니다.");
    }
}
