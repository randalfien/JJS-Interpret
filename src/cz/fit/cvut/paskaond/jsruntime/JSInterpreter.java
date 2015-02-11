package cz.fit.cvut.paskaond.jsruntime;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.dojo.jsl.parser.ASTProgram;
import org.dojo.jsl.parser.EcmaScript;
import org.dojo.jsl.parser.ParseException;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSArray;
import cz.fit.cvut.paskaond.jsruntime.exception.JSRuntimeException;

public class JSInterpreter {
	
	private ASTProgram program;
	private JSArray args;
	
	public JSInterpreter(){
		
	}
	
	public void loadProgram(ASTProgram program) {
		this.program = program;
	}
	
	public void loadProgram(String s) {
		InputStream is = new ByteArrayInputStream( s.getBytes() );
		EcmaScript parser = new EcmaScript( is );
		parse(parser);
	}
	
	public void loadProgram(InputStream is) {
		if( is == System.in ){
			parse( new EcmaScript( is ) );
		}else{
			parse( new EcmaScript( is, "UTF-8" ) );
		}
	}
	
	protected void parse(EcmaScript parser){
		try {
			this.program = parser.Program();
			//n.dump("");
			System.out.println("Parsing successfull.");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.out.println("Encountered errors during parse.");
		}
	}
	
	public Object execute() {
		return execute( new DefaultEnvironment() );
	}
	
	public Object execute(JSEnvironment env) {
		System.out.println("Executing . . .");
		if( args != null ){
			env.put("arguments", args);
		}
		Object value = null;
		try{
			value = this.program.evalIn( env );
		}catch (JSRuntimeException e){
			System.out.println(e.getMessage());
			System.out.println("Encountered javascript error.");
		}catch (RuntimeException e){
			System.out.println(e.getMessage());
			System.out.println("Encountered native error.");
		}
		return value;
	}

	public void setArguments(JSArray jsargs) {
		this.args = jsargs;
	}
	
}
