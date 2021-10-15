package com.easyflowchart.models.mermaidgenerator;

import com.easyflowchart.enums.LinkType;
import com.easyflowchart.enums.MermaidItemType;
import lombok.Getter;
import lombok.Setter;

public class LinkItem extends MermaidItemAbstractClass {

    @Getter @Setter
    LinkType linkType;

    @Getter @Setter
    Integer fromId, toId;

    @Override
    protected void initClass() {
        this.itemType = MermaidItemType.LINK;
        linkType = LinkType.ARROW;
    }

    private String createLinkDeclaration(){
        if(fromId == null || toId == null){
            throw new IllegalStateException("fromId or toId not defined");
        }
        return "N" +  fromId + linkType.getLink(content) +  "N" +  toId + ";";
    }

    @Override
    public String getMermaidCode(){
        return createLinkDeclaration();
    }

    public LinkItem(int  id){
        super(id);
    }

    public LinkItem(int id, String content){
        super(id, content);
    }
}