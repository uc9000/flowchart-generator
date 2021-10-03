package com.easyflowchart.enums;

import lombok.Getter;

public enum SyntaxType {
    C("C"),
    MARMEID("mermaid");

    @Getter
    public String type;

    SyntaxType(String type){
        this.type = type;
    }
}