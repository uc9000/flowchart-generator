package com.easyflowchart.models.recursiveDescentParserForC;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CParser {

    private final StringBuilder programBuilder = new StringBuilder(200);

    @Getter @Setter
    private String program;

    private boolean isSpecialChar(char c){
        String specialCharacters = "{}();";
        for (char special : specialCharacters.toCharArray()){
            if(c == special){
                return true;
            }
        }
        return false;
    }

    private String getStringInOuterSigns(String code, char opening, char closing, boolean specialCharCheck){
        code = code.substring(code.indexOf(opening) + 1);
        StringBuilder innerString = new StringBuilder();
        int open = 1;
        for (char c : code.toCharArray()){
                if(opening == c) {
                    open++;
                }
                else if(closing == c) {
                    open--;
                }
                else {
                    if(specialCharCheck && isSpecialChar(c)){
                        throw new IllegalStateException("Can't this special character here: " + c);
                    }
                    innerString.append(c);
                }

            if (open == 0) {
                return innerString.toString();
            }
        }
        return innerString.toString();
    }

    public String getStringInOuterParenthesis(String code){
        return getStringInOuterSigns(code, '(', ')', true);
    }

    public String getStringInOuterCurly(String code){
        return getStringInOuterSigns(code, '{', '}', false);
    }

    public void onExpressionEnter(String expression){
        log.info("expression entered: " + expression);
    }

    private void handleExpression(){
        String expression = CInstructionType.EXPRESSION.getFirstExpression(programBuilder.toString());
        onExpressionEnter(expression);
        programBuilder.delete(0, expression.length());
        parseProgramInstruction();
    }

    public void onIfStatementEnter(String condition, String expressions){
        log.info("IF entered : " + condition + " than : " + expressions);
    }

    public void onIfStatementExit(String condition, String expressions){
        log.info("IF exited : " + condition);
    }

    private void handleIfStatement(){
        String statement = programBuilder.toString();
        String condition = getStringInOuterParenthesis(statement);
        String expressions = getStringInOuterCurly(statement);
        programBuilder.delete(0, condition.length() + 5);
        onIfStatementEnter(condition, expressions);
        parseProgramInstruction();
        onIfStatementExit(condition, expressions);
    }

    public void onElseStatementEnter(String expressions){
        log.info("ELSE entered : " + expressions);
    }

    public void onElseStatementExit(String expressions){
        log.info("ELSE exited");
    }

    private void handleElseStatement(){
        String statement = programBuilder.toString();
        String expressions = getStringInOuterCurly(statement);
        programBuilder.delete(0, 6);
        onElseStatementEnter(expressions);
        parseProgramInstruction();
        onElseStatementExit(expressions);
    }

    public void onEndOfScope(){}

    private void handleEndOfScope(){
        onEndOfScope();
        programBuilder.delete(0, 1);
    }

    private void parseProgramInstruction(){
        if(programBuilder.length() == 0){
            log.info("program exit");
            return;
        }
        CInstructionType currentType = CInstructionType.UNKNOWN;
        String code = programBuilder.toString();
        for(CInstructionType type : CInstructionType.values()){
            if(type.isMatching(code)){
                currentType = type;
                break;
            }
        }
        log.info("program instruction of type: " + currentType + " code: " + code);
        switch (currentType){
            case END_OF_SCOPE:
                handleEndOfScope();
                return;

            case EXPRESSION:
                handleExpression();
                return;

            case ELSE_STATEMENT:
                handleElseStatement();
                return;

            case IF_STATEMENT:
                handleIfStatement();
                return;

            case WHILE:
                //todo

            case UNKNOWN:
            default:
                throw new IllegalStateException("Unsupported syntax : " + programBuilder);
        }
    }


    public void parseProgram (String _program){

        //removing all whitespaces and new lines chars
        this.program = _program.replaceAll("[\\s\\n]", "");
        programBuilder.append(program);
        while ( ! programBuilder.toString().isEmpty() ){
            parseProgramInstruction();
        }
    }
}