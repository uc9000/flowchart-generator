package com.easyflowchart.controllers;

import com.easyflowchart.models.FlowchartParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private FlowchartParser flowchartParser;

    @GetMapping("/flowchart")
    public ModelAndView flowchart(@RequestParam(value = "type", defaultValue = "mermaid") String type, @RequestParam(value = "code", defaultValue = "A --> B") String code, ModelAndView mv){
        mv.setViewName("flowchart.html");
        flowchartParser.setType(type);
        String convertedCode = flowchartParser.code2flowchart(code);
        mv.addObject("flowchart", flowchartParser);
        mv.addObject("convertedCode", convertedCode);
        return mv;
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView mv){
        mv.setViewName("index.html");
        String type = flowchartParser.getSyntaxType().type;
        mv.addObject("flowchart", flowchartParser);
        mv.addObject("type", type);
        return mv;
    }
}