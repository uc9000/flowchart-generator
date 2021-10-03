package com.easyflowchart.models;

import org.springframework.stereotype.Service;

@Service
public class FlowchartParser extends FlowchartAttributes{
    public final String GRAPH_DIRECTION = "graph TD;\n"; // TD = top>down , LR = left>right etc.;
    FlowchartParser(){
        super();
    }

    private String handleMermaidCode(String input){
        this.setMarmeidCode(input);
        return GRAPH_DIRECTION + input;
    }

    public String code2flowchart(String code){
        switch (getType()){
            case MARMEID:
                return handleMermaidCode(code);

            case C:
                return "WIP";

            default:
                throw new IllegalStateException(getType().name() + " not supported by FlowchartParser");
        }
    }
}