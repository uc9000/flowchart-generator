package com.easyflowchart.controllers;

import com.easyflowchart.models.FlowchartAttributes;
import com.easyflowchart.models.FlowchartParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private FlowchartParser flowchartParser = new FlowchartParser();

    @GetMapping("/flowchart")
    public ModelAndView flowchart(@RequestParam(value = "type", defaultValue = "mermaid") String type, @RequestParam(value = "code", defaultValue = "A --> B") String code, ModelAndView mv){
        mv.setViewName("flowchart.html");
        flowchartParser.setType(type);
        String convertedCode = flowchartParser.code2flowchart(code);
        mv.addObject("flowchart", flowchartParser);
        mv.addObject("convertedCode", convertedCode);
        mv.addObject("type", type);
        return mv;
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView mv){
        mv.setViewName("index.html");
        String type = flowchartParser.getType().type;
        mv.addObject("flowchart", flowchartParser);
        mv.addObject("type", type);
        return mv;
    }
}