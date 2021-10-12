package com.easyflowchart.easyflowchart;

import com.easyflowchart.models.mermaidgenerator.LinkItem;
import com.easyflowchart.models.mermaidgenerator.MermaidElementsManager;
import com.easyflowchart.models.mermaidgenerator.NodeItem;
import com.easyflowchart.models.recursiveDescentParserForC.CParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class CParserTests {
	CParser parser = new CParser(){};

	@Test
	void innerExtractionTest(){
		String input = "if(condition){some statements}";
		String inner1 = "condition";
		String inner2 = "some statements";
		assertEquals(inner1, parser.getStringInOuterParenthesis(input));
		assertEquals(inner2, parser.getStringInOuterCurly(input));
	}

	@Test
	void parserRecursionTest(){
		String program = "start; if (x > 2){x greater than 2; do smth;} else {x less than 2;}";
		parser.parseProgram(program);
	}
}