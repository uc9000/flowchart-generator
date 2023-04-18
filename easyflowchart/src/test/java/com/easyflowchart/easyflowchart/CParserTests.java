package com.easyflowchart.easyflowchart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.easyflowchart.models.recursiveDescentParserForC.CParser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

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