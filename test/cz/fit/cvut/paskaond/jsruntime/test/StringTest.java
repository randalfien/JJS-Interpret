package cz.fit.cvut.paskaond.jsruntime.test;
import static org.junit.Assert.assertEquals;

import org.dojo.jsl.parser.ASTProgram;
import org.dojo.jsl.parser.ParseException;
import org.junit.Test;

import cz.fit.cvut.paskaond.jsruntime.DefaultEnvironment;


public class StringTest extends BaseTest{
	
	@Test
	public void testConcat1() throws ParseException {
		String code = "\"Hello\"+\" \"+\"World\"";
		ASTProgram n = getProgram(code);
		assertEquals( "Hello World", n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testConcat2() throws ParseException {
		String code = "\"Number \"+9.0";
		ASTProgram n = getProgram(code);
		assertEquals( "Number 9.0", n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void test3() throws ParseException {
		String code = "\"Number \" * 9.0";
		ASTProgram n = getProgram(code);
		assertEquals( "NaN", n.evalIn( new DefaultEnvironment() ).toString() );
	}
	
	@Test
	public void testLength() throws ParseException {
		String code = "var txt = \"ABCD\"; \n"
					+ "txt.length";
		ASTProgram n = getProgram(code);
		assertEquals( 4, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testCharAt() throws ParseException {
		String code = "var txt = \"ABCD\"; \n"
					+ "txt.charAt(1)";
		ASTProgram n = getProgram(code);
		assertEquals( "B", n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testSplit() throws ParseException {
		String code = "var txt = \"1,2,3,4,5\"; \n"
					+ "txt.split(\",\")[1]";
		ASTProgram n = getProgram(code);
		assertEquals( "2", n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testSplitJoin() throws ParseException {
		String code = "var a = [1,2,3]; \n"
					+ "a.join(\",\").split(\",\")[1]";
		ASTProgram n = getProgram(code);
		assertEquals( "2", n.evalIn( new DefaultEnvironment() ) );

		code = "var txt = \"1,2,3\"; \n"
				+ "txt.split(\",\").join(\"a\")";
		n = getProgram(code);
		assertEquals( "1a2a3", n.evalIn( new DefaultEnvironment() ) );
	}

}
