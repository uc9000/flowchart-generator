package com.easyflowchart.models;

public class FlowchartAttributes {
    private String code;
    private String type;

    public FlowchartAttributes(){
        this.code = new String("");
        this.type = new String("mermaid");     
    }

    public FlowchartAttributes(String code, String type){
        this.code = code;
        this.type = type; 
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setCode(String code){
        this.code = code;
    }
}