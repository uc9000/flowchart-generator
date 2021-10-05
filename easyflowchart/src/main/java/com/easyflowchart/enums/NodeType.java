package com.easyflowchart.enums;

public enum NodeType {
    PROCESS,
    DECISION,
    START_END,
    DATA;

    public String wrapContent(String content){
        switch (this){
            case PROCESS:
                return "[" + content + "]";

            case DECISION:
                return "{" + content + "}";

            case START_END:
                return "([" + content + "])";

            case DATA:
                return "[/" + content + "/]";

            default:
                throw new IllegalStateException("Unsupported NodeType: " + this.name());
        }
    }
}
