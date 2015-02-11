package cz.fit.cvut.paskaond.jsruntime.test;
import static org.junit.Assert.assertEquals;

import org.dojo.jsl.parser.ASTProgram;
import org.dojo.jsl.parser.ParseException;
import org.junit.Test;

import cz.fit.cvut.paskaond.jsruntime.DefaultEnvironment;


public class ArrayTest extends BaseTest{

	@Test
	public void test() throws ParseException {
		String code = "var a = [2,2]; \n"
					+ "a[0] = 1 \n"
					+ "a[1] = 2 \n"
					+ "a[0]+a[1]";
		ASTProgram n = getProgram(code);
		assertEquals( 3L, n.evalIn( new DefaultEnvironment() ) );

		code = "var a = []; \n"
				+ "a[1] = 1 \n"
				+ "a[2] = 2 \n"
				+ "a.length";
		n = getProgram(code);
		assertEquals( 3, n.evalIn( new DefaultEnvironment() ) );

		code = "var a = [50,50,50]; \n"
				+ "a[1] = 1 \n"
				+ "a[2] = 2 \n"
				+ "a.length+a[0]"; //3+50
		n = getProgram(code);
		assertEquals( 53.0, n.evalIn( new DefaultEnvironment() ) );

		code = "var a = [true,false];\n"
				+ "a.length == 2 \n";
		n = getProgram(code);
		assertEquals( "true", n.evalIn( new DefaultEnvironment() ).toString() );

		code="var a = [1,2,3,4,5];	\n"+
				"var b = [-1,-2,-3];	\n"+
				"var j = 2;				\n"+
				"a[ (-b[j])-1 ]";
		n = getProgram(code);
		assertEquals( "3", n.evalIn( new DefaultEnvironment() ).toString() );
	}
	
	@Test
	public void testIndexEval() throws ParseException {
		String code = "var a = [1,1,2,3,5,8]; \n"
					+ "var b = 1 \n"
					+ "a[ b + 2 ] + 1 \n";
		ASTProgram n = getProgram(code);
		assertEquals( 4L, n.evalIn( new DefaultEnvironment() ) );
	}

	@Test
	public void testJoin() throws ParseException {
		String code = "var a = [1,1,2,3,5,8]; \n"
					+ "a.join()";
		ASTProgram n = getProgram(code);
		assertEquals( "1,1,2,3,5,8", n.evalIn( new DefaultEnvironment() ) );

		code = "var a = [1,1,2,3,5,8]; \n"
				+ "a.join(\" \")";
		n = getProgram(code);
		assertEquals( "1 1 2 3 5 8", n.evalIn( new DefaultEnvironment() ) );
	}

	@Test
	public void testPushPop() throws ParseException {
		String code = "var a = [1,1,2,3,5,8]; \n"
					+ "a.pop() \n"
					+ "a.pop() \n"
					+ "a.push(999) \n"
					+ "a.pop()+a.pop()"; // 999 + 3
		ASTProgram n = getProgram(code);
		assertEquals( 1002L, n.evalIn( new DefaultEnvironment() ) );

		code = "var a = []; \n"
				+ "a.pop()";
		n = getProgram(code);
		assertEquals( "undefined", n.evalIn( new DefaultEnvironment() ).toString() );
	}

}
