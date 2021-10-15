package com.easyflowchart.models.mermaidgenerator;

import com.easyflowchart.enums.MermaidItemType;

public interface MermaidItem {
    void setContent(String content);
    String getContent();

    void setId(Integer id);
    Integer getId();

    void setItemType(MermaidItemType itemType);
    MermaidItemType getItemType();

    String getMermaidCode();
}