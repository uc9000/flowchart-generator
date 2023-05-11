package com.easyflowchart.controllers;

import com.easyflowchart.dto.ConvertRequestDTO;
import com.easyflowchart.dto.ConvertResponseDTO;
import com.easyflowchart.enums.SyntaxType;
import com.easyflowchart.models.FlowchartParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "htps://easychart-production.up.railway.app"})
@RequestMapping(
        value = "/api/v1",
        produces = "application/json",
        consumes = "application/json")
@Slf4j
public class CodeConvertController {
    private final FlowchartParser flowchartParser;

    @Autowired
    CodeConvertController(FlowchartParser _flowchartParser){
        flowchartParser = _flowchartParser;
    }

    @PostMapping("/convert")
    public ConvertResponseDTO convert(@RequestBody ConvertRequestDTO request){
        log.info("convert request: \n" + request.toString());
        SyntaxType type = request.getType();
        type = (type == null) ? SyntaxType.C : type;
        flowchartParser.setSyntaxType(type);
        String converted = flowchartParser.code2flowchart(request.getOriginalCode());
        return new ConvertResponseDTO(converted, type);
    }
}
