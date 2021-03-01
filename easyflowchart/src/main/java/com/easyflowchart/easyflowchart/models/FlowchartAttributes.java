package com.easyflowchart.easyflowchart.models;

public class FlowchartAttributes {
    private String code, type;

    public FlowchartAttributes(){
        this.code = new String();
        this.type = new String();     
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
}