package com.easyflowchart.models.mermaidgenerator;

import com.easyflowchart.enums.MermaidItemType;
import lombok.Data;

@Data
public abstract class MermaidItemAbstractClass implements MermaidItem {
    protected Integer id;
    protected String content;
    protected MermaidItemType itemType;

    abstract protected void initClass();
    abstract public String getMermaidCode();

    public MermaidItemAbstractClass(int id){
        this.id = id;
        initClass();
    }

    public MermaidItemAbstractClass(int id , String content){
        this(id);
        this.content = content;
        initClass();
    }
}