package com.easyflowchart.models.recursiveDescentParserForC;

import com.easyflowchart.models.recursiveDescentParserForC.CParserConstants;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CInstructionType implements CParserConstants {
    EXPRESSION    (EXPRESSION_PATTERN),
    ELSE_STATEMENT(ELSE_PATTERN),
    IF_STATEMENT  (IF_PATTERN),
    WHILE         (WHILE_PATTERN),
    END_OF_SCOPE  (END_SCOPE_PATTERN),
    UNKNOWN       ("");

    @Getter
    private final Pattern PATTERN;

    CInstructionType(String regexPattern){
        this.PATTERN = Pattern.compile(regexPattern);
    }

    private boolean isGroupBalanced(String code){
        int curlyOpen = 0, parenthesisOpen = 0;
        int curlyCount = 0, parenthesisCount = 0;
        for (char c : code.toCharArray()){
            switch (c){
                case '(':
                    parenthesisOpen++;
                    parenthesisCount++;
                    break;
                case ')':
                    parenthesisOpen--;
                    parenthesisCount++;
                    break;
                case '{':
                    curlyOpen++;
                    curlyCount++;
                    break;
                case '}':
                    curlyOpen--;
                    curlyCount++;
                    break;
            }
        }
        switch (this){
            case EXPRESSION:
            case UNKNOWN:
            case END_OF_SCOPE:
                return true;
            case ELSE_STATEMENT:
                return curlyOpen < 0 && parenthesisOpen == 0 && curlyCount > 0;
            case IF_STATEMENT:
            case WHILE:
                return curlyOpen <= 0 && parenthesisOpen == 0 && curlyCount > 0 && parenthesisCount > 0;

        }
        return false;
    }

    public boolean isMatching(String code){
        Matcher matcher = PATTERN.matcher(code);
        return matcher.matches() && isGroupBalanced(code);
    }

    public String getFirstExpression(String code){
        if(this != EXPRESSION){
            throw new IllegalStateException("Not supported for type: " +  this.name());
        }
        Matcher matcher = PATTERN.matcher(code);
        if(matcher.find()){
            return matcher.group(1);
        }else{
            throw new IllegalStateException("Group not found");
        }
    }
}