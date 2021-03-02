package com.easyflowchart.models;

import org.springframework.stereotype.Service;

@Service
public class FlowchartParser extends FlowchartAttributes{

    FlowchartParser(){
        setType("mermaid");
    }

    public String code2flowchart(String code){
        if(getType().equals("mermaid")){
            return code;
        }
        else if(getType().equals("C")){
            return "WIP";
        }
        else{
            return "Wrong type error";
        }
    }
}
