package cz.fit.cvut.paskaond.jsruntime.test;
import static org.junit.Assert.assertEquals;

import org.dojo.jsl.parser.ASTProgram;
import org.dojo.jsl.parser.ParseException;
import org.junit.Test;

import cz.fit.cvut.paskaond.jsruntime.DefaultEnvironment;


public class FlowControlTest extends BaseTest {

	@Test
	public void testIF1() throws ParseException {
		String code = "if( true ){ \n"
				+ " 2.0        \n"
				+ "}";
		ASTProgram n = getProgram(code);
		assertEquals( 2.0, n.evalIn( new DefaultEnvironment() ) );

		code = "if( 1 - 1 ){\n" // = 0 which equals false
				+ " 2.0      \n"
				+ "}else{  \n"
				+ "	13.25     \n"
				+ "}";
		n = getProgram(code);
		assertEquals( 13.25, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testIF2() throws ParseException {
		String code = "var a = false;	\n"
					+ "if( a == false ){\n"
					+ " true        	\n"
					+ "}";
		ASTProgram n = getProgram(code);
		assertEquals( "true", n.evalIn( new DefaultEnvironment() ).toString() );

		code = "if( 3*4 == 4*3 ){\n" 
				+ " true      	\n"
				+ "}else{  		\n"
				+ "	false     	\n"
				+ "}";
		n = getProgram(code);
		assertEquals( "true", n.evalIn( new DefaultEnvironment() ).toString() );
		
		code = "if( \"false\" == \"true\" ){\n" 
				+ " true      	\n"
				+ "}else{  		\n"
				+ "	false     	\n"
				+ "}";
		n = getProgram(code);
		assertEquals( "false", n.evalIn( new DefaultEnvironment() ).toString() );
	}
	
	@Test
	public void testIF3() throws ParseException {
		String code = "if( true && false ){ \n"
					+ " true        \n"
					+ "}else{\n"
					+ " false\n"
					+ "}";
		ASTProgram n = getProgram(code);
		assertEquals( "false", n.evalIn( new DefaultEnvironment() ).toString() );

		code = "var a = [true,false];\n"
				+ "if( 0 || false || a[0] ){ \n"
				+ " true        \n"
				+ "}else{ 		\n"
				+ " false 		\n"
				+ "}";
		n = getProgram(code);
		assertEquals( "true", n.evalIn( new DefaultEnvironment() ).toString() );
		

		code = "var a = [-1,-2,-3];\n"
				+ "var j = 1; 	\n"
				+ "if( a[j] < 0 ){ \n"
				+ " true        \n"
				+ "}else{ 		\n"
				+ " false 		\n"
				+ "}";
		n = getProgram(code);
		assertEquals( "true", n.evalIn( new DefaultEnvironment() ).toString() );
		
		
	}

	@Test
	public void testWhile() throws ParseException {
		String code = "var i = 1  \n"
				+ "var a = 1  \n"
				+ "while( i < 4 ){ 	\n"
				+ " a = a * i; 		\n"
				+ " i = i + 1;      \n"
				//	+ " writeln(\"a:\"+a) \n"
				+ "} \n"
				+ "a"; // 1*1*2*3
		ASTProgram n = getProgram(code);
		assertEquals( 6L, n.evalIn( new DefaultEnvironment() ) );
	}

	@Test
	public void testFor() throws ParseException {
		String code = "var a = 1  \n"
					+ "var i = 1 \n"
					+ "for( i = 1; i < 4; i++ ){ 	\n"
					+ " a = a * i; 		\n"
					//	+ " writeln(\"a:\"+a) \n"
					+ "} \n"
					+ "a"; // 1*1*2*3
		ASTProgram n = getProgram(code);
		assertEquals( 6L, n.evalIn( new DefaultEnvironment() ) );

		code = "var a = 1  \n"
				+ "for( var i = 1; i < 4; i++ ){ 	\n"
				+ " a = a * i; 		\n"
			//	+ " writeln(\"a:\"+a) \n"
				+ "} \n"
				+ "a"; // 1*1*2*3
		n = getProgram(code);
		assertEquals( 6L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testBreak() throws ParseException {
		String code = "var a = 1  \n"
					+ "var i = 1 \n"
					+ "for( i = 1; i < 10; i++ ){ 	\n"
					+ " a = a * i; 		\n"
					+ " if( i == 3 ) break; \n"
					+ "} \n"
					+ "a"; // 1*1*2*3
		ASTProgram n = getProgram(code);
		assertEquals( 6L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testBreak2() throws ParseException {
		String code="var a = 0;\n"+
					"for( var i = 0; i < 5; i++ ){\n"+
					"	for( var j = 1; j < 100; j++ ){\n"+
					" 		a = a + j;\n"+
					"		break;\n"+
					"	}\n"+
					"}"+
					"a"; // 5*1
		ASTProgram n = getProgram(code);
		assertEquals( 5L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testReturn() throws ParseException {
		String code="var b = 1;			\n"+
					"function a() {		\n"+
					"	b = 5;			\n"+
					"	return;			\n"+
					"	b = 6;			\n"+
					"}					\n"+
					"a();				\n"+
					"b;";
		ASTProgram n = getProgram(code);
		assertEquals( "5", n.evalIn( new DefaultEnvironment() ).toString() );
	}

	@Test
	public void testReturn2() throws ParseException {
		String code="var b = 1;			\n"+
					"function outer(){	\n"+
					"	function inner(){\n"+
					"		return 100;\n"+
					"		b = 5;		\n"+
					"	}\n"+
					"	var a = inner();\n"+
					"	b = 2;			\n"+
					"	return 200;			\n"+
					"	b = 5;			\n"+
					"}					\n"+
					"outer() + b";
		ASTProgram n = getProgram(code);
		assertEquals( 202L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testReturn3() throws ParseException {
		String code="function a(){\n"+
					"	for( var i = 0; i < 6; i++ ){\n"+
					"		if( i == 1 ){\n"+
					"			return 5;\n"+
					"		}\n"+
					"	}\n"+
					"	return 3;\n"+
					"}\n"+
					"a();";
		ASTProgram n = getProgram(code);
		assertEquals( "5", n.evalIn( new DefaultEnvironment() ).toString() );
	}

}
