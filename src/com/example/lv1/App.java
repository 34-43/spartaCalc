package com.example.lv1;

import java.util.Scanner;

public class App {
    static long global_result;

    public static void main(String[] args) {
        calculation_process();
    }

    public static void calculation_process() {
        Scanner sc = new Scanner(System.in);
        System.out.print("첫 번째 숫자를 입력하세요: ");
        int a = sc.nextInt();
        System.out.print("두 번째 숫자를 입력하세요: ");
        int b = sc.nextInt();
        System.out.print("사칙연산 기호를 입력하세요: ");
        char o = sc.next().charAt(0);
        if (calculate(a,b,o)) {
            System.out.println("결과: " + global_result);
        }
        sc.close();
    }

    public static boolean calculate(int a, int b, char operand) {
        boolean flag = true;
        if (a>=0 && b>=0) {
            switch (operand) {
                case '+':
                    global_result = a + b;
                    break;
                case '-':
                    global_result = a - b;
                    break;
                case '*':
                    global_result = (long) a * b;
                    break;
                case '/':
                    if (b != 0) {
                        global_result = a / b;
                    } else {
                        System.out.println("오류:0으로 나눌 수 없음");
                        flag = false;
                    }
                    break;
                default:
                    System.out.println("오류:잘못된 연산자");
                    flag = false;
                    break;
            }
        } else {
            System.out.println("오류:잘못된 피연산자");
            flag = false;
        }
        return flag;
    }
}
