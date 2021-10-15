package com.easyflowchart.models.mermaidgenerator;

import com.easyflowchart.enums.MermaidItemType;
import com.easyflowchart.enums.NodeType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class NodeItem extends MermaidItemAbstractClass {

    @Getter @Setter
    NodeType nodeType;

    @Getter @Setter
    private ArrayList<NodeItem> inputs  = new ArrayList<>();
    @Getter @Setter
    private ArrayList<NodeItem> outputs = new ArrayList<>();

    @Override
    protected void initClass() {
        this.itemType = MermaidItemType.NODE;
        nodeType = NodeType.PROCESS;
    }

    private String createNodeDeclaration(){
        if(outputs.isEmpty()){
            nodeType = NodeType.START_END;
        }
        return "N" + id + nodeType.wrapContent(content) + ";";
    }

    @Override
    public String getMermaidCode(){
        return createNodeDeclaration();
    }

    public NodeItem(int  id){
        super(id);
    }

    public NodeItem(int id, String content){
        super(id, content);
    }

    public NodeItem findLastInTree(int firstIndex){
        NodeItem last = this;
        if(last.outputs.size() > firstIndex){
            last = last.outputs.get(firstIndex);
        }
        ArrayList<NodeItem> outputs = last.outputs;
        while (!outputs.isEmpty() && outputs.get(0).getId() > last.getId()){
            last = outputs.get(0);
        }
        return last;
    }
}