package com.easyflowchart.models;

import org.springframework.stereotype.Service;

@Service
public class FlowchartParser extends FlowchartAttributes{
    public final String GRAPH_DIRECTION;
    FlowchartParser(){
        setType("mermaid");
        GRAPH_DIRECTION = new String("graph TD;\n"); // TD = top>down , LR = ledt>right etc.
    }

    private String handleMermaidCode(String input){
        String output = new String(GRAPH_DIRECTION + input);
        return output;
    }

    public String code2flowchart(String code){
        if(getType().equals("mermaid")){
            return handleMermaidCode(code);
        }
        else if(getType().equals("C")){
            return "WIP";
        }
        else{
            return "Wrong type error";
        }
    }
}
