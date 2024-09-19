package com.sparta.baseball;

import com.sparta.baseball.DataType.GuessResult;
import com.sparta.baseball.Exceptions.GuessException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Baseball {
    private int[] answerArr;
    private final Set<Integer> answerSet;

    public Baseball() {
        this.answerArr = new int[3];
        answerSet = new HashSet<>();
    }

    public void newGame() {
        answerSet.clear();
        while(answerSet.size() < 3) {
            int a = (int)(Math.random() * 10);
            if (a != 0) {answerSet.add(a);}
        }
        answerArr = answerSet.stream().mapToInt(i -> i).toArray();
    }

    public String getAnswer() {
        return Integer.toString(Arrays.stream(answerArr).reduce((a,b)->10*a+b).orElse(0));
    }

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
