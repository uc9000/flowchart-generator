package com.easyflowchart.models.mermaidgenerator;

import com.easyflowchart.enums.MermaidItemType;
import lombok.Data;

@Data
public abstract class MermaidItemAbstractClass implements MermaidItem {
    protected String id;
    protected String content;
    protected MermaidItemType itemType;

    abstract protected void initClass();
    abstract public String getMermaidCode();

    public MermaidItemAbstractClass(String id){
        this.id = id;
        initClass();
    }

    public MermaidItemAbstractClass(String  id , String content){
        this(id);
        this.content = content;
        initClass();
    }
}