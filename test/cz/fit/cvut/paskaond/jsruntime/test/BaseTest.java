package cz.fit.cvut.paskaond.jsruntime.test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.dojo.jsl.parser.ASTProgram;
import org.dojo.jsl.parser.EcmaScript;
import org.dojo.jsl.parser.ParseException;


public class BaseTest {
	protected ASTProgram getProgram(String code) throws ParseException{
		InputStream is = new ByteArrayInputStream( code.getBytes() );
		EcmaScript parser = new EcmaScript( is );
		return parser.Program();
	}
}
