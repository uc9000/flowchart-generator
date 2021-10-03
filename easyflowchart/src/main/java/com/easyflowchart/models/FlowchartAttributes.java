package com.easyflowchart.models;

import com.easyflowchart.enums.SyntaxType;
import lombok.Data;

@Data
public class FlowchartAttributes {
    private String marmeidCode;
    private SyntaxType type;

    // default values
    public FlowchartAttributes(){
        this.marmeidCode = "";
        this.type = SyntaxType.MARMEID;
    }

    public void setType(String type) {
        for(SyntaxType t : SyntaxType.values()){
            if (t.type.equals(type)){
                this.type = t;
                return;
            }
        }
    }
}