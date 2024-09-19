package com.sparta.baseball.DataType;

public class GuessResult {
    private final int strike;
    private final int ball;

    private enum COLOR {BLACK,WHITE,YELLOW,GREEN,RED}
    private static final String black = "\u001B[30m";
    private static final String white = "\u001B[37m";
    private static final String yellow = "\u001B[33m";
    private static final String green = "\u001B[32m";
    private static final String red = "\u001B[31m";
    private static final String exit = "\u001B[0m";

    public GuessResult() {
        this(0,0);
    }
    public GuessResult(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public boolean isCleared() {
        return strike == 3;
    }

    public boolean isOut() {
        return strike == 0 && ball == 0;
    }

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
