package com.sparta.calculator.lv3.Exceptions;

public class TokenizerException extends Exception {
    public TokenizerException(String erroredType, String cause) {
        super("에러:파싱된 __" + erroredType + "__의 토큰화 과정에서 __"+ cause +"__로 인한 오류 발생.");
    }
}
