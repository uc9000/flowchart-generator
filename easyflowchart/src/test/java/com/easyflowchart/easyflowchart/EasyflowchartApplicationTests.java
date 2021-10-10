package com.easyflowchart.easyflowchart;

import com.easyflowchart.models.mermaidgenerator.LinkItem;
import com.easyflowchart.models.mermaidgenerator.MermaidElementsManager;
import com.easyflowchart.models.mermaidgenerator.NodeItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class EasyflowchartApplicationTests{
	MermaidElementsManager mManager = new MermaidElementsManager();

	@BeforeEach
	void beforeEach(){
		mManager.clear();
	}

	@Test
	void nodesWithLinkGenerationTest() {
		NodeItem node1 = new NodeItem("node1", "Start");
		NodeItem node2 = new NodeItem("node2", "End");
		LinkItem link1 = new LinkItem("link1");

		link1.setFromId(node1.getId());
		link1.setToId(node2.getId());

		String sb = node1.getMermaidCode() +
				node2.getMermaidCode() +
				link1.getMermaidCode();

		log.info("nodesWithLinkGenerationTest result: \n" + sb);
		assertEquals("node1[Start];node2[End];node1-->node2", sb);
	}

	@Test
	void NodesWithLinksChainTest(){
		mManager.createMultipleLinkedNodes(new String[]{"Start", "Action", "End"});
		mManager.getMermaidCode();
	}

	@Test
	void DecisionNodeCreationTest(){
		mManager.createSingleNodeLinkedToLast("Start");
		NodeItem decisionNode = mManager.createDecisionNodeLinkedToLast("x > 2 ?", "write: x is greater than 2", "write: x is less or equal to 2");
		NodeItem lastNode = mManager.createSingleNode("Save the result");
		decisionNode.getOutputs().forEach(output -> {
			mManager.linkNodes(output, lastNode);
		});
		mManager.getMermaidCode();
	}
}