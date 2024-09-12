package com.sparta.calculator.lv3;

import com.sparta.calculator.lv3.DataType.Token;
import com.sparta.calculator.lv3.Exceptions.SyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
토큰 형태로 저장된 중위 연산식의 리스트를 Stack 과 알고리즘을 통해 괄호가 제거된 후위 연산식 리스트로 처리합니다.
연산자의 우선 순위에 따라 스택에 바로 넣을 지, 스택 top 원소를 내보낸 후에 넣을 지 결정하게 됩니다.
비교는 Token 자료형 클래스에 구현된 정적 메서드 getPriority 를 통해 간편하게 비교하며, 해당 메서드는 열거형 index 를 활용하여 비교합니다.
 */

public class PostFixConverter {
    private List<Token<?>> inputList = new ArrayList<>();
    private final Stack<Token<?>> tempStack = new Stack<>();
    private final ArrayList<Token<?>> outputList = new ArrayList<>();

    public void setTokenList(List<Token<?>> inputList) {
        this.inputList = inputList;
    }

    public ArrayList<Token<?>> convert() throws SyntaxException {
        Token<?> temp;
        outputList.clear();
        for (Token<?> token : inputList) {
            switch (token.getType()) {
                case INTEGER: case FLOAT:   //숫자가 들어오면, 바로 내보냅니다.
                    outputList.add(token);
                    break;
                case PARENTHESES_RIGHT: //닫는 괄호는 여는 괄호 이후부터 저장한 연산자들을 스택에서 모두 내보내야 합니다.
                    if (tempStack.isEmpty()) {throw new SyntaxException("(가 없음");}
                    temp = tempStack.pop();
                    while(!temp.getType().equals(Token.TokenType.PARENTHESES_LEFT)) {
                        outputList.add(temp);
                        if (tempStack.isEmpty()) {throw new SyntaxException("(가 없음");}
                        temp = tempStack.pop();
                    }
                default:    //그 외 기호들에 대해서는 우선 순위에 따라 작업합니다.
                    if (tempStack.isEmpty()) {
                        tempStack.push(token);
                    } else if (token.getType().equals(Token.TokenType.PARENTHESES_LEFT)) {
                        tempStack.push(token);
                    } else if (token.getType().equals(Token.TokenType.ARITHMETIC)) {
                        temp = tempStack.peek();
                        if (temp.getType().equals(Token.TokenType.ARITHMETIC)) {
                            if (Token.getPriority((temp.toOperatorToken()), token.toOperatorToken()) >= 0) {
                                tempStack.pop();    //peek 한 값이 pop 되도록 합니다.
                                outputList.add(temp);
                            }
                            tempStack.push(token);
                        } else {
                            tempStack.push(token);
                        }
                    }
                    break;
            }
        }
        while (!tempStack.isEmpty()) {  //마지막에 스택에 남은 연산자들을 모두 붙여줍니다.
            outputList.add(tempStack.pop());
        }
        return outputList;
    }
}
