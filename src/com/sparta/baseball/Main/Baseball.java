package com.sparta.baseball.Main;

import com.sparta.baseball.DataType.GuessResult;
import com.sparta.baseball.Exceptions.DifficultySetException;
import com.sparta.baseball.Exceptions.GuessException;

import java.util.*;

/*
Baseball 클래스는 숫자 야구 게임의 핵심 연산과 게임 상태를 담당하는 클래스입니다.
Baseball 객체는 정수 배열과 Integer Set 의 두 필드를 이용하여 정답 수열을 생성하거나, 추측 시도와 비교합니다.
 */

public class Baseball {
    private int[] answerArr;    // 정답 수열을 저장하는 정수 배열.
    private final Set<Integer> answerSet;   // 정답 수열의 생성 및 추측 계산에 필요한 Integer Set 콜렉션.
    private int guessCount; // 추측 시도 횟수를 가산하는 정수 변수.
    private static int difficulty = 3;  // 프로그램이 종료되기 전까지 게임의 난이도 설정을 보존하는 정적 정수 변수.
    private static final ArrayList<Integer> statList = new ArrayList<>();   // 프로그램이 종료되기 전까지 통계를 저장할 정적 리스트 입니다.

    // 필드를 초기화하는 유일한 기본 생성자입니다.
    public Baseball() {
        this.answerArr = new int[difficulty];
        answerSet = new HashSet<>();
        guessCount = 0;
    }

    // 새로운 게임을 시작하기 위해, 정답을 생성하는 메서드입니다.
    // 1~9의 난수를 생성하여 필드 Set 의 크기가 정해진 크기가 되도록 하고, 이것을 배열로 변환하여 순서성이 부여되도록 합니다.
    // 변환 결과가 오름차순이 되는 것을 막기 위해 결과를 한 번 뒤섞습니다. 시도 횟수 또한 초기화합니다.
    public void newGame() {
        answerSet.clear();
        while(answerSet.size() < difficulty) {
            int a = (int)(Math.random() * 10);
            if (a != 0) {answerSet.add(a);}
        }
        List<Integer> ls = new ArrayList<>(answerSet);
        Collections.shuffle(ls);
        answerArr = ls.stream().mapToInt(i -> i).toArray();
        guessCount = 0;
    }

    // 정답 배열을 연이은 숫자 형태의 문자열로 변환하여 반환합니다.
    public String getAnswer() {
        return Integer.toString(Arrays.stream(answerArr).reduce((a,b)->10*a+b).orElse(0));
    }

    // guessCount 의 getter 입니다.
    public int getGuessCount() {
        return guessCount;
    }

    // 추측 시도 횟수를 가산하는 private 메서드입니다.
    private void increaseGuessCount() {
        guessCount++;
    }

    // 전달된 입력값을 검사하고, 정답 필드와 비교하여 그 결과를 GuessResult 객체로 반환합니다.
    // 입력값이 조건을 만족하는 지 확인하고 필요 시 예외를 발생시키기 위해 InputTester 유틸 클래스를 사용합니다.
    public GuessResult guess(String input) throws GuessException {
        int strike = 0;
        int ball = 0;
        InputTester.testGuess(input);
        for (int i = 0; i < difficulty; i++) {
            if (answerArr[i] == input.charAt(i) - '0') {strike++;}
            if (answerSet.contains(input.charAt(i) - '0')) {ball++;}
        }
        increaseGuessCount();
        return new GuessResult(strike, ball - strike);
    }

    // 추측이 성공적인 경우 호출되어, 현재 추측 시도 횟수를 통계에 저장합니다.
    public void saveResult() {
        if (guessCount > 0) {
            statList.add(guessCount);
            guessCount = 0;
        }
    }

    // 게임의 난이도를 설정합니다. 프로그램이 종료되기 전까지 유지되며, 잘못된 입력에 대해 예외를 발생시킵니다.
    public static void setDifficulty(String input) throws DifficultySetException {
        InputTester.testDifficulty(input);
        Baseball.difficulty = Integer.parseInt(input);
    }

    // 현재 설정되어 있는 난이도를 정수로 반환합니다.
    public static int getDifficulty() {
        return Baseball.difficulty;
    }

    // 통계를 int Array 형태로 반환하는 getter 입니다.
    public static int[] getStatArray() {
        return statList.stream().mapToInt(i -> i).toArray();
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

    // 부분 테스팅 드라이버
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
