package com.easyflowchart.models.mermaidgenerator;

import com.easyflowchart.enums.MermaidItemType;
import com.easyflowchart.enums.NodeType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class MermaidElementsManager {

    @Getter
    private final ArrayList<NodeItem> nodes = new ArrayList<>();
    private final ArrayList<LinkItem> links = new ArrayList<>();
    @Getter
    private int idCount = 0;

    @Getter @Setter
    private NodeItem lastNode = null;

    private String generateNewId (MermaidItemType type) {
        idCount++;
        return type.name() + idCount;
    }

    public void clear(){
        idCount = 0;
        nodes.clear();
        links.clear();
    }

    private void removeNode(NodeItem item){
        item.getInputs().forEach(input -> {
            int index = input.getOutputs().indexOf(item);
            input.getOutputs().remove(item);
            input.getOutputs().addAll(index, item.getOutputs());
        });

        item.getOutputs().forEach(output -> {
            int index = output.getInputs().indexOf(item);
            output.getInputs().remove(item);
            output.getInputs().addAll(index, item.getInputs());
        });
        nodes.remove(item);
    }

    private void removeAllEmptyNodes(){
        ArrayList <NodeItem> copyList = new ArrayList<>(nodes);
        for (NodeItem node : copyList) {
            if (node.content == null || node.content.isEmpty()) {
                removeNode(node);
            }
        }
    }

    public NodeItem createSingleNode(String label, NodeType type){
        NodeItem node = new NodeItem(generateNewId(MermaidItemType.NODE), label);
        if(type != null){
            node.setNodeType(type);
        }

        lastNode = node;
        nodes.add(node);
        return node;
    }

    public NodeItem createSingleNode(String label){
        return createSingleNode(label, null);
    }

    private void createAllLinks(){
        nodes.forEach(node -> {
            for(int i = 0; i < node.getOutputs().size(); i++) {
                NodeItem output = node.getOutputs().get(i);
                LinkItem link = new LinkItem(generateNewId(MermaidItemType.LINK));
                link.setToId(output.getId());
                link.setFromId(node.getId());
                if(node.nodeType.equals(NodeType.DECISION)){
                    link.setContent(i == 0 ? "True" : "False");
                }
                links.add(link);
            }
        });
    }

    public void linkNodes(NodeItem fromNode, NodeItem toNode){
        toNode.getInputs().add(fromNode);
        if(fromNode != null){
            fromNode.getOutputs().add(toNode);
        }
    }

    public void createMultipleLinkedNodes(String[] labels){
        for (String label : labels) {
            createSingleNodeLinkedToLast(label, null);
        }
    }

    public NodeItem createSingleNodeLinkedToLast(String label, NodeType type){
        NodeItem previous = lastNode;
        createSingleNode(label, type);
        linkNodes(previous, lastNode);
        return lastNode;
    }

    public NodeItem createSingleNodeLinkedToLast(String label){
        return createSingleNodeLinkedToLast(label, null);
    }

    public NodeItem createDecisionNodeLinkedToLast(String condition, NodeItem nodeIfTrue, NodeItem nodeIfFalse){
        NodeItem conditionNode = createSingleNodeLinkedToLast(condition, NodeType.DECISION);
        linkNodes(conditionNode, nodeIfTrue);
        linkNodes(conditionNode, nodeIfFalse);
        return conditionNode;
    }

    public NodeItem createDecisionNodeLinkedToLast(String condition, String labelIfTrue, String labelIfFalse){
        NodeItem last = lastNode;
        NodeItem nodeIfTrue  = createSingleNode(labelIfTrue);
        NodeItem nodeIfFalse = createSingleNode(labelIfFalse);
        lastNode = last;
        return createDecisionNodeLinkedToLast(condition, nodeIfTrue, nodeIfFalse);
    }

    public NodeItem createDecisionNodeLinkedToLast(String condition){
        return createDecisionNodeLinkedToLast(condition, "", "");
    }

    public String getMermaidCode(){
        removeAllEmptyNodes();
        ArrayList<MermaidItem> items = new ArrayList<>(nodes);
        NodeItem firstNode = nodes.get(0);
        firstNode.setNodeType(NodeType.START_END);
        createAllLinks();
        items.addAll(links);

        StringBuilder codeBuilder = new StringBuilder();
        for (MermaidItem item : items) {
            codeBuilder.append(item.getMermaidCode());
        }
        log.info(codeBuilder.toString());
        return codeBuilder.toString();
    }
}