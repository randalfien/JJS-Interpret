package cz.fit.cvut.paskaond.jsruntime.test;
import static org.junit.Assert.assertEquals;

import org.dojo.jsl.parser.ASTProgram;
import org.dojo.jsl.parser.ParseException;
import org.junit.Test;

import cz.fit.cvut.paskaond.jsruntime.DefaultEnvironment;


public class ArithmeticTest extends BaseTest {
	
	@Test
	public void test1() throws ParseException {
		String code = "1+1";
		ASTProgram n = getProgram(code);
		assertEquals( 2L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void test2() throws ParseException {
		String code = "1 + 1 * 2 + 3.0 + 0xA";
		ASTProgram n = getProgram(code);
		assertEquals( 16.0, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test(expected=ParseException.class)
	public void test3() throws ParseException {
		String code = "1 + 2 +";
		getProgram(code);
	}
	
	@Test
	public void testParam() throws ParseException {
		String code = "1 + 2 * (1 * 2 + 3.0) + (0xA)"; // = 1 + 2*5 + 10 
		ASTProgram n = getProgram(code);
		assertEquals( 21.0, n.evalIn( new DefaultEnvironment() ) );
	}

}
