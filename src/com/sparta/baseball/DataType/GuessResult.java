package com.sparta.baseball.DataType;

/*
GuessResult 객체는 숫자 야구 게임에서 각 추측 시도에 대한 Strike 와 Ball 횟수를 저장하기 위한 구조화 데이터 클래스입니다.
생성자를 통해 계산된 두 값이 전달되며, 멤버 메서드로 두 값을 활용한 유용한 기능을 지원합니다.
 */

public class GuessResult {
    private final int strike;   // 추측 시도의 strike 결과값.
    private final int ball; //추측 시도의 ball 결과값.

    // 콘솔에 출력될 문자열에 색상을 입히기 위해 작성된 colorStr 메서드에서 사용하는 열거형 및 상수입니다.
    private enum COLOR {BLACK,WHITE,YELLOW,GREEN,RED}
    private static final String black = "\u001B[30m";
    private static final String white = "\u001B[37m";
    private static final String yellow = "\u001B[33m";
    private static final String green = "\u001B[32m";
    private static final String red = "\u001B[31m";
    private static final String exit = "\u001B[0m";

    // 기본 생성자입니다. this 메서드와 인자로 두 값을 0과 0으로 초기화합니다.
    public GuessResult() {
        this(0,0);
    }

    // 두 필드를 지정하는 생성자입니다. 별도의 setter 가 없는 상태에서 유일하게 상태를 지정하는 요인입니다.
    public GuessResult(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    // 추측 시도가 성공적이었는지, strike 값을 확인하여 반환합니다.
    public boolean isCleared() {
        return strike == 3;
    }

    // 추측 시도가 Out 인지, 두 필드를 확인하여 반환합니다.
    public boolean isOut() {
        return strike == 0 && ball == 0;
    }

    // 문자열과 색상 열거형을 입력받아, 색상이 적용된 문자열을 반환합니다.
    // 색상이 적용된 문자열은 콘솔에서 해당 색상으로 나타나게 됩니다.
    private static String colorStr(String str, COLOR color) {
        String colorEnter = switch (color) {
            case BLACK -> black;
            case WHITE -> white;
            case YELLOW -> yellow;
            case GREEN -> green;
            case RED -> red;
        };
        return colorEnter + str + exit;
    }

    // 현재 상태를 나타내기에 적합한 동적 문자열을 반환하는 toString 메서드를 오버라이딩하였습니다.
    // 두 필드 값과 colorStr 멤버 메서드를 활용하여 추측 시도의 결과를 특수기호와 색상의 조합으로 보기 좋게 출력합니다.
    @Override
    public String toString() {
        if (isCleared()) {return colorStr("GAME SET!", COLOR.RED);}
        else if (isOut()) {return colorStr("OUT!", COLOR.RED);}
        StringBuilder sb = new StringBuilder();
        sb.append(colorStr("⌌————————————————————⌍\n∣", COLOR.WHITE));
        sb.append(colorStr("\tSTRIKE\t",COLOR.WHITE));
        for (int i = 0; i < 3; i++) {
            sb.append(colorStr("⬤ ",strike > i ? COLOR.YELLOW : COLOR.BLACK));
        }
        sb.append(colorStr(" ∣\n∣", COLOR.WHITE));
        sb.append(colorStr("\tBALL  \t",COLOR.WHITE));
        for (int i = 0; i < 3; i++) {
            sb.append(colorStr("⬤ ",ball > i ? COLOR.GREEN : COLOR.BLACK));
        }
        sb.append(colorStr(" ∣\n⌎————————————————————⌏", COLOR.WHITE));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new GuessResult(3,0));
        System.out.println(new GuessResult(0,0));
        System.out.println(new GuessResult(1,2));
    }
}
