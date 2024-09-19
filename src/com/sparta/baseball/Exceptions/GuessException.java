package com.sparta.baseball.Exceptions;

public class GuessException extends Exception {
    public GuessException(String message) {
        super("오류: " + message + "은(는) 부적절한 추측입니다.");
    }
}
