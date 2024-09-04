package com.example.lv2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();

        while (true) {
            try {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int a = sc.nextInt();
                calc.setLeft(a);
                System.out.print("두 번째 숫자를 입력하세요: ");
                int b = sc.nextInt();
                calc.setRight(b);
            } catch (InputMismatchException e) {
                System.out.println("오류:int 범위 내의 정수를 입력하세요");
                sc.next();
                continue;
            }
            System.out.print("사칙연산 기호를 입력하세요: ");
            char o = sc.next().charAt(0);
            calc.setOperator(o);
            if (calc.calculate()) {
                System.out.println("결과: " + calc.getResult());
            }
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            String input_str = sc.next();
            if (input_str.equals("exit")) {
                sc.close();
                System.exit(0);
            }
        }
    }
}
