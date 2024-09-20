package com.sparta.baseball;

import com.sparta.baseball.DataType.GuessResult;
import com.sparta.baseball.Exceptions.GuessException;
import com.sparta.baseball.Main.Baseball;

import java.util.Scanner;

/*
LV.2 & LV.3 을 구현한 발전된 숫자야구게임 메인 클래스입니다.
 */

public class ImprovedGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Baseball.printBatter();
        System.out.print("환영합니다! ");
        while(true) {
            System.out.println("원하시는 번호를 입력해주세요");
            System.out.println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");

            try {
                switch(scanner.nextInt()) {
                    case 1:
                        System.out.println("< 게임을 시작합니다 >");
                        gameProcess();
                        break;
                    case 2:
                        System.out.println("< 게임 기록 보기 >");
                        printStat();
                        break;
                    case 3:
                        System.out.println("< 숫자 야구 게임을 종료합니다 >");
                        System.exit(0);
                    default:
                        System.out.println("올바른 숫자를 입력해주세요!");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
            System.out.println();
        }
    }

    // 숫자 게임을 시작하고, 정답을 맞출 시 통계에 저장하도록 하고 종료합니다.
    public static void gameProcess() {
        Scanner sc = new Scanner(System.in);
        Baseball bGame = new Baseball();
        GuessResult bResult = new GuessResult();
        bGame.newGame();
        while(!bResult.isCleared()) {
            System.out.print("세 자리 추측 입력: ");
            String input = sc.nextLine();
            try {bResult = bGame.guess(input);}
            catch (GuessException e) {System.out.println(e.getMessage()); continue;}
            System.out.println(bResult);
        }
        if(bResult.isCleared()) {
            System.out.println("정답은 " + bGame.getAnswer() + " 였습니다.");
            bGame.saveResult();
        }
    }

    // 통계를 참조하여 적절히 콘솔에 출력합니다.
    public static void printStat() {
        int[] stat = Baseball.getStatArray();
        for (int i = 0; i < stat.length; i++) {
            System.out.println(i + 1 + "번째 게임 : 시도 횟수 - " + stat[i]);
        }
    }
}
