package com.example.lv3.Exceptions;

public class TokenizerException extends Exception {
    public TokenizerException(String erroredType) {
        super("에러:파싱된 __" + erroredType + "__의 토큰화 과정에서 오류 발생.");
    }
}
