package com.easyflowchart.models.recursiveDescentParserForC;

public interface CParserConstants {
    String ELSE_PATTERN = "\\}else\\{.*";
    String IF_PATTERN = "if\\(.*";
    String WHILE_PATTERN = "while\\(.*";
    String EXPRESSION_PATTERN = "([\\w\\+\\-=\\(\\)\\.,]+?;).*";
    String END_SCOPE_PATTERN = "\\}.*";
}