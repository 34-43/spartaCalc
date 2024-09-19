package com.sparta.baseball;

import com.sparta.baseball.Exceptions.GuessException;

/*
입력된 추측 문자열을 검사하여 예외를 발생시키거나 종료되는 유틸 클래스입니다.
하나의 정적 메서드만을 가집니다.
 */

public class InputTester {
    // 입력된 문자열이 세 가지 주요 조건을 만족하는지 검사합니다.
    // 불만족할 경우, 그 사유를 들어 예외를 발생시킵니다.
    public static void testGuess(String input) throws GuessException {
        if (input.length() != 3) {
            throw new GuessException("문제 길이와 다른 입력");
        }
        char[] arrCh = input.toCharArray();
        for (char ch : arrCh) {
            if (ch <= '0' || ch > '9') {
                throw new GuessException("1~9 의 정수 이외의 입력");
            }
        }
        if (arrCh[0] == arrCh[1] || arrCh[0] == arrCh[2] || arrCh[1] == arrCh[2]) {
            throw new GuessException("값이 중복되는 입력");
        }
    }
}
