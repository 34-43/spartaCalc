package com.sparta.baseball;

import com.sparta.baseball.DataType.GuessResult;
import com.sparta.baseball.Exceptions.GuessException;

import java.util.Scanner;

public class BasicGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Baseball b = new Baseball();
        GuessResult gr = new GuessResult();
        b.newGame();
        printBatter();
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
    
    public static void printBatter() {
        String sb = """
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
                       , `` 'x_``_'
                      /  -- _^_    \\    \s
                     ,    .'   '.   '    \s
                    /   _/       \\_  \\    \s
                   ,   -'          .__,    \s
                  ,'---'            \\  \\    \s
                 /  ,'               '._\\    \s
                ,_./                  l`s\\    \s
                |`s/                 , _>__)
                
                ~ play ball ~
                """;
        System.out.println(sb);
    }
}
