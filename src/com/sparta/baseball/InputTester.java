package com.sparta.baseball;

import com.sparta.baseball.Exceptions.GuessException;

public class InputTester {
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
