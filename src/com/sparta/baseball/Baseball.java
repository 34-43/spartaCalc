package com.sparta.baseball;

import com.sparta.baseball.DataType.GuessResult;
import com.sparta.baseball.Exceptions.GuessException;

import java.util.*;

/*
Baseball 클래스는 숫자 야구 게임의 핵심 연산과 게임 상태를 담당하는 클래스입니다.
Baseball 객체는 정수 배열과 Integer Set 의 두 필드를 이용하여 정답 수열을 생성하거나, 추측 시도와 비교합니다.
 */

public class Baseball {
    // 정답 수열을 저장하는 정수 배열과 Integer Set 입니다. 이 중 Set 은 ball 계산에 지속적으로 사용됩니다.
    private int[] answerArr;
    private final Set<Integer> answerSet;

    // 필드를 초기화하는 유일한 기본 생성자입니다.
    public Baseball() {
        this.answerArr = new int[3];
        answerSet = new HashSet<>();
    }

    // 새로운 게임을 시작하기 위해, 정답을 생성하는 메서드입니다.
    // 1~9의 난수를 생성하여 필드 Set 의 크기가 정해진 크기가 되도록 하고, 이것을 배열로 변환하여 순서성이 부여되도록 합니다.
    // 변환 결과가 오름차순이 되는 것을 막기 위해 결과를 한 번 뒤섞습니다.
    public void newGame() {
        answerSet.clear();
        while(answerSet.size() < 3) {
            int a = (int)(Math.random() * 10);
            if (a != 0) {answerSet.add(a);}
        }
        List<Integer> ls = new ArrayList<>(answerSet);
        Collections.shuffle(ls);
        answerArr = ls.stream().mapToInt(i -> i).toArray();
    }

    // 정답 배열을 연이은 3자리 숫자 형태의 문자열로 변환하여 반환합니다.
    public String getAnswer() {
        return Integer.toString(Arrays.stream(answerArr).reduce((a,b)->10*a+b).orElse(0));
    }

    // 전달된 입력값을 검사하고, 정답 필드와 비교하여 그 결과를 GuessResult 객체로 반환합니다.
    // 입력값이 조건을 만족하는 지 확인하고 필요 시 예외를 발생시키기 위해 InputTester 유틸 클래스를 사용합니다.
    public GuessResult guess(String input) throws GuessException {
        int strike = 0;
        int ball = 0;
        InputTester.testGuess(input);
        for (int i = 0; i < 3; i++) {
            if (answerArr[i] == input.charAt(i) - '0') {strike++;}
            if (answerSet.contains(input.charAt(i) - '0')) {ball++;}
        }
        return new GuessResult(strike, ball - strike);
    }

    // 콘솔에 아스키 아트 야구선수를 출력해 주는 정적 메서드입니다.
    public static void printBatter() {
        String batter = """
                                _
                              .//'    \s
                            ./@'    \s
                           //'    \s
                          //  ___    \s
                          /' /_  2)    \s
                    .- .-,= _ _c ___>    \s
                   \\_(_-< ` ( \\ _/           _      _    \s
                      \\__)   `-,' \\         (_'  -    __  -    \s
                        \\ '-._/   /    \s
                         \\_   '-./    \s
                        \\ '---" |    \s
                        ['.____,l    \s
                       /`------"\\    \s
                       , `` __``_'
                      /  -- _^_    \\    \s
                     ,    .'   '.   '    \s
                    /   _/       \\_  \\    \s
                   ,   -'          .__,    \s
                  ,'---'            \\  \\    \s
                 /  ,'               '._\\    \s
                ,_./                  l`s\\    \s
                |`s/                 , _>__)
                """;
        System.out.println(batter);
    }

    public static void main(String[] args) {
        Baseball b = new Baseball();
        GuessResult gr;
        b.newGame();
        try {
            System.out.println("추측:123");
            gr = b.guess("123");
            System.out.println(gr);
            if (gr.isCleared()) {
                System.out.println("성공!");
            }

            System.out.println("추측:" + b.getAnswer());
            gr = b.guess(b.getAnswer());
            System.out.println(gr);
            if (gr.isCleared()) {
                System.out.println("성공!");
            }

        } catch (GuessException e) {
            System.out.println(e.getMessage());
        }

    }
}
