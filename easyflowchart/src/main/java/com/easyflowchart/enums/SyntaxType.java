package com.easyflowchart.enums;

import lombok.Getter;

public enum SyntaxType {
    C("C"),
    MERMAID("mermaid");

    @Getter
    private String type;

    SyntaxType(String type){
        this.type = type;
    }
}