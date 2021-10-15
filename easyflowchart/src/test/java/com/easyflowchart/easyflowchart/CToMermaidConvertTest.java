package com.easyflowchart.easyflowchart;

import com.easyflowchart.models.recursiveDescentParserForC.CInstructionType;
import com.easyflowchart.models.recursiveDescentParserForC.CParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class CToMermaidConvertTest {
	CParser parser = new CParser(){

		@Override
		public void onEndOfScope(CInstructionType type) {

		}
	};
}