package com.easyflowchart.easyflowchart.controllers;

import com.easyflowchart.easyflowchart.models.FlowchartAttributes;
import com.easyflowchart.easyflowchart.models.FlowchartParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    @Autowired
    private FlowchartParser flowchartParser;

    @GetMapping("/flowchart")
    public ModelAndView flowchart(@RequestParam(value = "type", defaultValue = "mermaid") String type, @RequestParam(value = "code", defaultValue = "") String code){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("flowchart.html");
        try{
            flowchartParser.setType(type);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ModelAndView("index.html");
        }        
        mv.addObject("convertedCode", flowchartParser.code2flowchart(code));
        mv.addObject("type", type);
        return mv;
    }

    @GetMapping("/")
    public String home(Model model){
        FlowchartAttributes flowchart = new FlowchartAttributes();
        model.addAttribute("flowchart", flowchart);
        return "index.html";
    }
}