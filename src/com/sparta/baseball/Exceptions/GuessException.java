package com.sparta.baseball.Exceptions;

/*
추측 시도가 조건을 만족하지 못했을 경우, 해당 조건을 들어 부적절함을 고지하는 예외 클래스입니다.
 */

public class GuessException extends Exception {
    public GuessException(String reasonName) {
        super("오류: " + reasonName + "은(는) 부적절한 추측입니다.");
    }
}
