package com.easyflowchart.models.mermaidgenerator;

import com.easyflowchart.enums.NodeType;
import com.easyflowchart.models.recursiveDescentParserForC.CInstructionType;
import com.easyflowchart.models.recursiveDescentParserForC.CParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

@Slf4j
public class cToMermaidConverter {

   @Getter
   private final MermaidElementsManager manager = new MermaidElementsManager();

   private final Stack <NodeItem> scopeNodes = new Stack<>();
   private final Stack <Integer> outputIndexes = new Stack<>();
   Integer outputIndex = 0;


   private String replaceChars(String input){
      input = input.replaceAll("[;\"'`]", ""); // remove all
      input = input.replaceAll("_", " ");
      input = "\"" + input + "\"";
      return input;
   }

   private final CParser parser = new CParser() {
      @Override
      public void onIfStatementEnter(String condition, String expressions){
         condition = replaceChars(condition);
         log.info("IF entered : " + condition + " than : " + expressions);
         NodeItem scopeNode = manager.createDecisionNodeLinkedToLast(condition);
         scopeNodes.push(scopeNode);
         outputIndexes.push(0);
         outputIndex = outputIndexes.peek();
      }

      @Override
      public void onIfStatementExit(){
         log.info("IF exited");
         manager.createSingleNode("if_merge_node");
         manager.linkNodes(scopeNodes.peek().findLastInTree(0), manager.getLastNode());
         manager.linkNodes(scopeNodes.peek().findLastInTree(1), manager.getLastNode());
         scopeNodes.pop();
         outputIndexes.pop();
      }

      @Override
      public void onElseStatementEnter(String expressions){
         log.info("ELSE entered : " + expressions);
         outputIndex++;
      }

      @Override
      public void onWhileStatementEnter(String condition, String expressions){
         condition = replaceChars(condition);
         log.info("WHILE entered : " + condition + " than : " + expressions);
         NodeItem scopeNode = manager.createDecisionNodeLinkedToLast(condition);
         scopeNodes.push(scopeNode);
         outputIndexes.push(0);
         outputIndex = outputIndexes.peek();
      }

      @Override
      public void onWhileStatementExit(){
         log.info("WHILE exited : ");
         NodeItem loopNode = manager.createSingleNode("while_merge_node");
         manager.linkNodes(scopeNodes.peek().findLastInTree(0), manager.getLastNode());
         manager.linkNodes(scopeNodes.peek().findLastInTree(1), manager.getLastNode());
         NodeItem decisionNode = scopeNodes.pop();
         manager.createSingleNode("");
         manager.linkNodes(decisionNode, manager.getLastNode());
         scopeNodes.push(manager.getLastNode());
         manager.linkNodes(loopNode, decisionNode);
         outputIndexes.pop();
      }

      @Override
      public void onEndOfScope(CInstructionType type){
      }

      @Override
      public void onExpressionEnter(String expression){
         expression = replaceChars(expression);
         log.info("expression entered: " + expression);
         manager.createSingleNode(expression);
         if(scopeNodes.empty()){
            scopeNodes.push(manager.getLastNode()); // add root node
         }
         else {
            manager.linkNodes(scopeNodes.peek().findLastInTree(outputIndex), manager.getLastNode());
         }
      }
   };


   public String getMermaidCode(String CCode){
      parser.parseProgram(CCode);
      return manager.getMermaidCode();
   }

}