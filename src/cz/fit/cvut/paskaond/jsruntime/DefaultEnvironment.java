package cz.fit.cvut.paskaond.jsruntime;

import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSAddOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSAndOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSAssignOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSEqualsOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSLargerThenOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSMinusOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSMultiplyOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSNotEqualsOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSOrOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSSmallerThenOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.function.JSDebugFunction;
import cz.fit.cvut.paskaond.jsruntime.function.JSParseIntFunction;
import cz.fit.cvut.paskaond.jsruntime.function.JSPrintFunction;
import cz.fit.cvut.paskaond.jsruntime.function.io.JSReadFileFunction;
import cz.fit.cvut.paskaond.jsruntime.function.io.JSWriteFileFunction;

public class DefaultEnvironment extends JSEnvironment{
	public DefaultEnvironment(){
		put("+", new JSAddOperator()); 
		put("*", new JSMultiplyOperator());
		put("-", new JSMinusOperator());
		put("=", new JSAssignOperator());
		put("==", new JSEqualsOperator());
		put("!=", new JSNotEqualsOperator());
		put("+=", new JSAddOperator());
		put(">", new JSLargerThenOperator());
		put("<", new JSSmallerThenOperator());
		put("||", new JSOrOperator());
		put("&&", new JSAndOperator());
		put("trace", new JSPrintFunction());
		put("undefined", JSUndefined.getInstance() );
		put("parseInt", new JSParseIntFunction() );
		put("readFile", new JSReadFileFunction() );
		put("writeFile", new JSWriteFileFunction() );
		put("debug", new JSDebugFunction() );
		//TODO THESE ATTRIBUTES SHOULD BE READONLY
	}
}
