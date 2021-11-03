package com.easyflowchart.models.mermaidgenerator;

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

   private NodeItem fromNode;


   private String replaceChars(String input){
      input = input.replaceAll("[;\"'`]", ""); // remove all
      input = input.replaceAll("_", " ");
      input = "\"" + input + "\"";
      return input;
   }

   private String wordWrap(String input){
      String [] words = input.split(" ");
      StringBuilder sb = new StringBuilder();
      int charCount = 0;
      for (String word : words){
         charCount += word.length();
         sb.append(word);
         if(charCount < 12){
            sb.append(" ");
         }else{
            charCount = 0;
            sb.append("<br/>");
         }
      }
      return sb.toString();
   }

   private final CParser parser = new CParser() {
      @Override
      public void onIfStatementEnter(String condition, String expressions){
         condition = replaceChars(condition);
         condition = wordWrap(condition);
         log.info("IF entered : " + condition + " than : " + expressions);
         manager.setLastNode(fromNode);
         NodeItem scopeNode = manager.createDecisionNodeLinkedToLast(condition);
         scopeNodes.push(scopeNode);
         fromNode = scopeNode.getOutputs().get(0);
      }

      @Override
      public void onIfStatementExit(){
         log.info("IF exited");
         manager.createSingleNode("#if_merge");
         manager.linkNodes(scopeNodes.peek().findLastInTree(0), manager.getLastNode());
         manager.linkNodes(scopeNodes.peek().findLastInTree(1), manager.getLastNode());
         scopeNodes.pop();
         fromNode = manager.getLastNode();
      }

      @Override
      public void onElseStatementEnter(String expressions){
         log.info("ELSE entered : " + expressions);
         fromNode = scopeNodes.peek().getOutputs().get(1);
      }

      @Override
      public void onWhileStatementEnter(String condition, String expressions){
         condition = replaceChars(condition);
         condition = wordWrap(condition);
         log.info("WHILE entered : " + condition + " than : " + expressions);
         manager.setLastNode(fromNode);
         NodeItem scopeNode = manager.createDecisionNodeLinkedToLast(condition);
         scopeNodes.push(scopeNode);
         fromNode = scopeNode.getOutputs().get(0);
      }

      @Override
      public void onWhileStatementExit(){
         log.info("WHILE exited : ");
         manager.linkNodes(fromNode, scopeNodes.peek());
         fromNode = scopeNodes.peek().findLastInTree(1);

         scopeNodes.pop();
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
            manager.linkNodes(fromNode, manager.getLastNode());
         }
         fromNode = manager.getLastNode();
      }
   };


   public String getMermaidCode(String CCode){
      parser.parseProgram(CCode);
      return manager.getMermaidCode();
   }

}