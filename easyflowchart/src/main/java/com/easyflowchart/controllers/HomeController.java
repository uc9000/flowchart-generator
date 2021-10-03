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
    @Autowired
    private FlowchartParser flowchartParser;

    @GetMapping("/flowchart")
    public ModelAndView flowchart(@RequestParam(value = "type", defaultValue = "mermaid") String type, @RequestParam(value = "marmeidCode", defaultValue = "A --> B") String code){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("flowchart.html");
        flowchartParser.setType(type);
        mv.addObject("originalCode", code);
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