package com.easyflowchart.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlowchartParser extends FlowchartAttributes{
    public final static String GRAPH_DIRECTION = "graph TD;\n"; // TD = top>down , LR = left>right etc.;

    public FlowchartParser (){
        super();
    }

    private static String marmeidPrefix() {
        return GRAPH_DIRECTION;
    }

    private String handleMermaidCode(String input){
        this.setCode(input);
        log.info("Set code:\n" + input);
        return marmeidPrefix() + input;
    }

    private String ConvertCToMarmeidCode(String CCode) {
        throw new IllegalStateException("C not yet supported");
        //return "WIP"; // TODO: create C parser
    }

    private String handleCCode(String input){
        String marmeidCode = ConvertCToMarmeidCode(input);
        return handleMermaidCode(marmeidCode);
    }

    public String code2flowchart(String code){
        switch (getSyntaxType()){
            case MERMAID:
                return handleMermaidCode(code);

            case C:
                return handleCCode(code);

            default:
                throw new IllegalStateException(getSyntaxType().name() + " not supported by FlowchartParser");
        }
    }

}