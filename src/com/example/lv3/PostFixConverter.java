package com.example.lv3;

import com.example.lv3.DataType.Token;
import com.example.lv3.Exceptions.ParenthesesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostFixConverter {
    private List<Token<?>> inputList = new ArrayList<>();
    private Stack<Token<?>> tempStack = new Stack<>();
    private ArrayList<Token<?>> outputList = new ArrayList<>();

    public void setTokenList(List<Token<?>> inputList) {
        this.inputList = inputList;
    }

    public ArrayList<Token<?>> convert() throws ParenthesesException {
        Token<?> temp;
        for (Token<?> token : inputList) {
            switch (token.getType()) {
                case INTEGER: case FLOAT:   //숫자가 들어오면, 바로 내보냅니다.
                    outputList.add(token);
                    break;
                case PARENTHESES_RIGHT: //닫는 괄호는 여는 괄호 이후부터 저장한 연산자들을 스택에서 모두 내보내야 합니다.
                    if (tempStack.isEmpty()) {throw new ParenthesesException();}
                    temp = tempStack.pop();
                    while(!temp.getType().equals(Token.TokenType.PARENTHESES_LEFT)) {
                        outputList.add(temp);
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
                            if (Token.getPriority((Token<ArithmeticOperator.OperatorType>) temp, (Token<ArithmeticOperator.OperatorType>) token) >= 0) {
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
