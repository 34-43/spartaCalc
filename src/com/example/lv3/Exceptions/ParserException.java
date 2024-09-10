package com.example.lv3.Exceptions;

public class ParserException extends Exception {
    public ParserException(String cause) {
        super("오류:파싱 과정에서 __" + cause + "__로 인해 중단되었습니다.");
    }
}
