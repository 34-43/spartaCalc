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
            System.out.println("0. 자리수 설정 1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");

            try {
                switch(scanner.nextInt()) {
                    case 0:
                        System.out.println("< 난이도를 설정합니다 >");
                        setDifficultyProcess();
                        // break 구문 없이, 이어서 게임을 시작합니다.
                    case 1:
                        System.out.println("< 게임을 시작합니다 >");
                        gameProcess();
                        break;
                    case 2:
                        System.out.println("< 게임 기록 보기 >");
                        printStatProcess();
                        break;
                    case 3:
                        System.out.println("< 숫자 야구 게임을 종료합니다 >");
                        exitProcess();
                    default:
                        System.out.println("오류: 올바른 숫자를 입력해주세요!");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
            System.out.println();
        }
    }

    // 종료되기 전까지 유지될 난이도를 입력받고 저장합니다.
    public static void setDifficultyProcess() {
        Scanner sc = new Scanner(System.in);
        System.out.print("설정하고자 하는 자리수를 입력하세요. (3~5 사이의 숫자): ");
        while(true) {
            try {
                Baseball.setDifficulty(sc.next());
                System.out.println(Baseball.getDifficulty() + "자리수 난이도로 설정되었습니다.");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 숫자 게임을 시작하고, 정답을 맞출 시 통계에 저장하도록 하고 종료합니다.
    public static void gameProcess() {
        Scanner sc = new Scanner(System.in);
        Baseball bGame = new Baseball();
        GuessResult bResult = new GuessResult();
        bGame.newGame();
        int diff = Baseball.getDifficulty();
        while(!bResult.isCleared()) {
            System.out.print(diff + "자리 수 입력: ");
            String input = sc.nextLine();
            try {bResult = bGame.guess(input);}
            catch (GuessException e) {System.out.println(e.getMessage()); continue;}
            System.out.println(bResult);
        }
        if(bResult.isCleared()) {
            System.out.println("정답: " + bGame.getAnswer() + ", 시도 횟수: " + bGame.getGuessCount());
            bGame.saveResult();
        }
    }

    // 통계를 참조하여 적절히 콘솔에 출력합니다.
    public static void printStatProcess() {
        int[] stat = Baseball.getStatArray();
        for (int i = 0; i < stat.length; i++) {
            System.out.println(i + 1 + "번째 게임 : 시도 횟수 - " + stat[i]);
        }
    }

    // 현재 프로그램을 종료시킵니다.
    public static void exitProcess() {
        System.exit(0);
    }
}
