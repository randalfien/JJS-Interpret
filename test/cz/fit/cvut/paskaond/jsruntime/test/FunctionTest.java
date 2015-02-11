package cz.fit.cvut.paskaond.jsruntime.test;
import static org.junit.Assert.assertEquals;

import org.dojo.jsl.parser.ASTProgram;
import org.dojo.jsl.parser.ParseException;
import org.junit.Test;

import cz.fit.cvut.paskaond.jsruntime.DefaultEnvironment;
import cz.fit.cvut.paskaond.jsruntime.exception.NotDefinedException;
import cz.fit.cvut.paskaond.jsruntime.exception.TooManyArgumentsException;


public class FunctionTest extends BaseTest {

	@Test
	public void test1() throws ParseException {
		String code = "function a(arg1){ return arg1 + \"World\";} \n"
					+ "a(\"Hello \");";
		ASTProgram n = getProgram(code);
		assertEquals( "Hello World", n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void test2() throws ParseException {
		String code = "function a(arg1){ c(); return arg1 + \"World \";} \n"
					+ "var b = 5; \n"
					+ "function c(){ b = 999; } \n"
					+ "a(\"Hello \") + b;";
		ASTProgram n = getProgram(code);
		assertEquals( "Hello World 999", n.evalIn( new DefaultEnvironment() ) );
	}

	@Test
	public void testLocalScope() throws ParseException {
		String code = "var b = 5 \n"
					+ "function a(){ "
					+ "	var b = 10;	\n"
					+ "	return b;	\n"
					+ "} \n"
					+ "a() + b;";
		ASTProgram n = getProgram(code);
		assertEquals( 15L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testForwardCall() throws ParseException {
		String code = "var b = a()+a()+a(); \n"
					+ "function a(){ "
					+ "	var b = 10;	\n"
					+ "	return b;	\n"
					+ "} \n"
					+ "b;";
		ASTProgram n = getProgram(code);
		assertEquals( 30L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testMissingArgs() throws ParseException {
		String code = "function a( arg1, arg2, arg3 ){ "
					+ "	return arg1+\" \"+arg2+\" \"+arg3;	\n"
					+ "} \n"
					+ "a(1,2);";
		ASTProgram n = getProgram(code);
		assertEquals( "1 2 undefined", n.evalIn( new DefaultEnvironment() ) );	
	}

	@Test(expected=TooManyArgumentsException.class)
	public void testTooManyArgs() throws ParseException {
		String code = "function a( arg1, arg2 ){ "
					+ "	return arg1;	\n"
					+ "} \n"
					+ "a(1,2,3);";
		ASTProgram n = getProgram(code);
		n.evalIn( new DefaultEnvironment() );	
	}
	
	@Test
	public void testInnerFunctions() throws ParseException {
		String code = "function a( arg1, arg2 ){ "
					+ " function inner(arg1){ return arg1 + 5;}\n"
					+ "	return inner(arg1)+arg2;	\n"
					+ "} \n"
					+ "a(1,2);";
		ASTProgram n = getProgram(code);
		assertEquals( 8L, n.evalIn( new DefaultEnvironment() ) );	
	}
	
	@Test(expected=NotDefinedException.class)
	public void testInnerFunctions2() throws ParseException {
		String code = "function a( arg1, arg2 ){ "
					+ " function inner(arg1){ return arg1 + 5;}\n"
					+ "	return inner(arg1)+arg2;	\n"
					+ "} \n"
					+ "inner();";
		ASTProgram n = getProgram(code);
		n.evalIn( new DefaultEnvironment() );	
	}
	
	@Test
	public void testFunctionObjects() throws ParseException {
		String code = "var add1 = function (arg) { return arg+1 }\n"
					+"add1(5);";
		ASTProgram n = getProgram(code);
		assertEquals( 6L, n.evalIn( new DefaultEnvironment() ) );

		code = "function getAdd1(){"
				+"	return function (arg) { return arg+1 };"
				+"}\n"
				+"getAdd1()(5);";
		n = getProgram(code);
		assertEquals( 6L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testClosure1() throws ParseException {
		String code="function f(x) {  \n"+
				    "	return function(y) { return 10*y+x; } \n"+
					"}  \n"+
					"f(1)(2);";
		ASTProgram n = getProgram(code);
		assertEquals( 21L, n.evalIn( new DefaultEnvironment() ) );	
	}


	@Test
	public void testClosure2() throws ParseException {
		String code="var add = (function () {\n"+
					" var counter = 0;\n"+
					" return function () { return counter+=1;}\n"+
					"})();\n"+
					"add();							"+
					"add();							"+
					"add();							";
		ASTProgram n = getProgram(code);
		assertEquals( 3L, n.evalIn( new DefaultEnvironment() ) );	
	}
	
	@Test
	public void testComplex() throws ParseException {
		String code="var a = true;			\n"+
				"var result = \"\";			\n"+
				"oneCall();					\n"+
				"oneCall();					\n"+
				"function oneCall(){		\n"+
				"  call( 1 , 				\n"+
				"    function() { return a },\n"+
				"     function(msg){ result += \"Success\";},\n"+
				"     function(msg){ result += \"Failure\";}\n"+
				" ); 						\n"+
				"}							\n"+
				"function call( ind, rest, success, failure ){\n"+
				"  if( rest() ){			\n"+
				"    success(ind);			\n"+
				"  }else{					\n"+
				"    failure(ind);			\n"+
				"  }						\n"+
				"  a = false;    			\n"+
				"}\n"+
				"result;";
		ASTProgram n = getProgram(code);
		assertEquals( "SuccessFailure", n.evalIn( new DefaultEnvironment() ).toString() );	
	}
	
	@Test
	public void testRecursion() throws ParseException {
		String code="var num = 0;			\n"+
					"function rec(arg){		\n"+
					"	if( arg > 0 ){		\n"+
					"		rec(arg-1);		\n"+
					"	}					\n"+
					"	num++;				\n"+
					"}						\n"+
					"rec(4);";
		ASTProgram n = getProgram(code);
		assertEquals( 5L, n.evalIn( new DefaultEnvironment() ) );
		
		code="var num = 0;"+
			"var acc = 0;			\n"+
			"function rec(arg){		\n"+
			"	if( arg > 0 ){		\n"+
			"		rec(arg-1);		\n"+
			"		acc += arg;		\n"+
			"	}					\n"+
			"	num++;				\n"+
			"}						\n"+
			"rec(4);				\n"+
			"acc";
		n = getProgram(code);
		assertEquals( 10L, n.evalIn( new DefaultEnvironment() ) );
	}
}
