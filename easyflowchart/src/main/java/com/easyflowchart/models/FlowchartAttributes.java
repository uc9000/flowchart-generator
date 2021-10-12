package com.easyflowchart.models;

import com.easyflowchart.enums.SyntaxType;
import lombok.Data;

@Data
public class FlowchartAttributes {
    private String originalCode;
    private String convertedCode;
    private SyntaxType syntaxType;
    private String type;

    // default values
    public FlowchartAttributes(){
        this.originalCode = "A --> B";
        this.syntaxType = SyntaxType.MERMAID;
    }

    public void setType(String type) {
        this.type = type;
        for(SyntaxType t : SyntaxType.values()){
            if (t.type.equals(type)){
                this.syntaxType = t;
                return;
            }
        }
    }
}