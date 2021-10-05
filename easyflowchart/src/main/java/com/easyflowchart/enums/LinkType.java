package com.easyflowchart.enums;

public enum LinkType {
    ARROW,
    DOTTED,
    OPEN,
    THICK_ARROW;

    public String getLink(String textOnLink){
        textOnLink = (textOnLink != null && !textOnLink.isEmpty())
                ? "|" + textOnLink + "|"
                : "";

        switch (this){
            case ARROW:
                return "-->" + textOnLink;

            case DOTTED:
                return "-.->" + textOnLink;

            case OPEN:
                return "---" + textOnLink;

            case THICK_ARROW:
                return "==>" + textOnLink;


            default:
                throw new IllegalStateException("Not supported LinkType: " + this.name());
        }
    }

    public String getLink(){
        return getLink(null);
    }
}
