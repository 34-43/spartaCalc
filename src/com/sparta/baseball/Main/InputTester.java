package com.sparta.baseball.Main;

import com.sparta.baseball.Exceptions.DifficultySetException;
import com.sparta.baseball.Exceptions.GuessException;

import java.util.HashSet;
import java.util.Set;

/*
입력된 문자열을 검사하여 필요 시에만 예외를 발생시키는 목적으로 만들어진 유틸 클래스입니다.
난이도 입력 및 추측 입력에 대한 검사용 유틸 메서드를 각각 가집니다.
 */

public class InputTester {
    // 입력된 문자열이 세 가지 주요 조건을 만족하는지 검사하고 필요 시 예외만을 발생시킵니다.
    public static void testGuess(String input) throws GuessException {
        if (input.length() != Baseball.getDifficulty()) {
            throw new GuessException("문제 길이와 다른 입력");
        }
        char[] arrCh = input.toCharArray();
        Set<Integer> dupTestSet = new HashSet<>();
        for (char ch : arrCh) {
            if (ch <= '0' || ch > '9') {
                throw new GuessException("1~9 의 정수 이외의 입력");
            } else {
                dupTestSet.add(ch - '0');
            }
        }
        if (dupTestSet.size() != Baseball.getDifficulty()) {
            throw new GuessException("값이 중복되는 입력");
        }
    }

    // 입력된 문자열이 범위 내 숫자로 변환 가능한 지 검사하고 필요 시 예외만을 발생시킵니다.
    public static void testDifficulty(String input) throws DifficultySetException {
        try {
            int iInput = Integer.parseInt(input);
            if (!(iInput >= 3 && iInput <= 5)) {
                throw new DifficultySetException("잘못된 범위의 값");
            }
        } catch (NumberFormatException e) {
            throw new DifficultySetException("숫자타입이 아닌 입력");
        }
    }
}
