package com.easyflowchart.models;

import com.easyflowchart.enums.SyntaxType;
import lombok.Data;
import lombok.Setter;

@Data
public class FlowchartAttributes {
    private String originalCode;
    private String convertedCode;
    private SyntaxType syntaxType;

    // default values
    public FlowchartAttributes(){
        this.originalCode = "A --> B";
        this.syntaxType = SyntaxType.MERMAID;
    }
}