package com.example.lv3.Exceptions;

public class ParenthesesException extends RuntimeException {
    public ParenthesesException() {
        super("오류: 괄호 짝이 안맞습니다.");
    }
}
