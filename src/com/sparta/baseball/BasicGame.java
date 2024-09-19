package com.sparta.baseball;

import com.sparta.baseball.DataType.GuessResult;
import com.sparta.baseball.Exceptions.GuessException;

import java.util.Scanner;

/*
LV.1 과제를 구현한 가장 기본적인 숫자야구게임 메인 클래스입니다.
 */

public class BasicGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Baseball b = new Baseball();
        GuessResult gr = new GuessResult();
        b.newGame();
        Baseball.printBatter();
        while(!gr.isCleared()) {
            System.out.print("추측:");
            try {gr = b.guess(sc.next());}
            catch (GuessException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println(gr);
            if (gr.isCleared()) {
                System.out.println("성공! (정답:" + b.getAnswer() + ")");
            }
        }
    }
}
