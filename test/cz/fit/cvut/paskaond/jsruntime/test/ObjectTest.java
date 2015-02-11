package cz.fit.cvut.paskaond.jsruntime.test;
import static org.junit.Assert.assertEquals;

import org.dojo.jsl.parser.ASTProgram;
import org.dojo.jsl.parser.ParseException;
import org.junit.Test;

import cz.fit.cvut.paskaond.jsruntime.DefaultEnvironment;
import cz.fit.cvut.paskaond.jsruntime.exception.NullObjectReference;


public class ObjectTest extends BaseTest {

	@Test
	public void test1() throws ParseException {
		String code = "var a = 5;\n"
					+ "a+1";
		ASTProgram n = getProgram(code);
		assertEquals( 6L, n.evalIn( new DefaultEnvironment() ) );
	}

	@Test
	public void test2() throws ParseException {
		String code = "var a = {attr1:5, attr2:5};\n"
					+ "a.attr2 = 6;\n"
					+ "a[\"attr1\"]+a.attr2+1";
		ASTProgram n = getProgram(code);
		assertEquals( 12L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void test3() throws ParseException {
		String code = "var a = {attr1:5, attr2:0};\n"
					+ "var b = {ref1:a, attr3:100+100};\n"
					+ "a.attr2 = b.attr3;\n"
					+ "a[\"attr1\"]+b.ref1.attr2+1";  // 5 + 200 + 1
		ASTProgram n = getProgram(code);
		assertEquals( 206L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void test4() throws ParseException {
		String code = "var a = 5+5;\n"
					+ "var b;\n"
					+ "b = a-5;\n"
					+ "a+b";  // 10 + 5
		ASTProgram n = getProgram(code);
		assertEquals( 15L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testObjectWithFunction() throws ParseException {
		String code = "function f(){ return {attr1:1,attr2:20}; }"
					+ "var o = {ref:f};\n"
					+ "o.ref()[\"attr1\"] + o.ref()[\"attr2\"]";  // 1 + 20
		ASTProgram n = getProgram(code);
		assertEquals( 21L, n.evalIn( new DefaultEnvironment() ) );
	}
	
	@Test
	public void testNull() throws ParseException {
		String code = "function f(){ return null; }"
					+ "if( f() == null ){"
					+ "	true;"
					+ "}else{"
					+ "	false;"
					+ "}\n";
		ASTProgram n = getProgram(code);
		assertEquals( "true", n.evalIn( new DefaultEnvironment() ).toString() );
	}
	
	@Test(expected=NullObjectReference.class)
	public void testNullException() throws ParseException {
		String code = "var a = null; \n"
					+ "a.func()";
		ASTProgram n = getProgram(code);
		n.evalIn( new DefaultEnvironment() );
	}
	
}
