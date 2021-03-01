package com.easyflowchart.easyflowchart.models;

import org.springframework.stereotype.Service;

@Service
public class FlowchartParser {
    private String syntaxType;

    FlowchartParser(){
        syntaxType = "mermaid";
    }

    public String code2flowchart(String code){
        if(syntaxType.equals("mermaid")){
            return code;
        }
        else if(syntaxType.equals("C")){
            return "WIP";
        }
        else{
            throw new IllegalArgumentException("Error: wrong syntax type");
        }
    }

    public void setType(String syntaxType){
        this.syntaxType = syntaxType;
    }

    public String getType(){
        return this.syntaxType;
    }
}
