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
    public ModelAndView flowchart(@RequestParam(value = "type", defaultValue = "mermaid") String type, @RequestParam(value = "originalCode", defaultValue = "A --> B") String code, ModelAndView mv){
        try {
            mv.setViewName("flowchart.html");
            flowchartParser.setType(type);
            flowchartParser.code2flowchart(code);
            mv.addObject("flowchart", flowchartParser);
            return mv;
        } catch (Exception e){
            mv.setViewName("errorpage.html");
            mv.addObject("message", e.getMessage());
            return mv;
        }
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView mv){
        mv.setViewName("index.html");
        mv.addObject("flowchart", flowchartParser);
        return mv;
    }
}