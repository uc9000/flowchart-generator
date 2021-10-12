package com.easyflowchart.models;

import com.easyflowchart.models.mermaidgenerator.cToMermaidConverter;
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
        log.info("Set originalCode:\n" + input);
        return marmeidPrefix() + input;
    }

    private String ConvertCToMarmeidCode(String CCode) {
        cToMermaidConverter converter = new cToMermaidConverter();
        return converter.getMermaidCode(CCode);
    }

    private String handleCCode(String input){
        String marmeidCode = ConvertCToMarmeidCode(input);
        return handleMermaidCode(marmeidCode);
    }

    public String code2flowchart(String code){
        this.setOriginalCode(code);
        switch (getSyntaxType()){
            case MERMAID:
                this.setConvertedCode(handleMermaidCode(code));
                break;

            case C:
                this.setConvertedCode(handleCCode(code));
                break;

            default:
                throw new IllegalStateException(getSyntaxType().name() + " not supported by FlowchartParser");
        }
        return this.getConvertedCode();
    }

}