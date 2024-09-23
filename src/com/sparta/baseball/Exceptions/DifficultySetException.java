package com.sparta.baseball.Exceptions;

/*
입력받은 난이도가 3~5 사이의 숫자가 아닌 경우에 이를 고지하는 예외 클래스입니다.
 */

public class DifficultySetException extends RuntimeException {
    public DifficultySetException(String cause) {
        super("오류: " + cause + "은 부적절한 난이도 입력입니다. (3~5 사이의 수 입력)");
    }
}
