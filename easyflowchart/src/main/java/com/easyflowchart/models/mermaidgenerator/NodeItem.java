package com.easyflowchart.models.mermaidgenerator;

import com.easyflowchart.enums.MermaidItemType;
import com.easyflowchart.enums.NodeType;
import lombok.Getter;
import lombok.Setter;

public class NodeItem extends MermaidItemAbstractClass {

    @Getter @Setter
    NodeType nodeType;

    @Override
    protected void initClass() {
        this.itemType = MermaidItemType.NODE;
        nodeType = NodeType.PROCESS;
    }

    private String createNodeDeclaration(){
        return id + nodeType.wrapContent(content) + ";";
    }

    @Override
    public String getMermaidCode(){
        return createNodeDeclaration();
    }

    public NodeItem(String  id){
        super(id);
    }

    public NodeItem(String id, String content){
        super(id, content);
    }
}