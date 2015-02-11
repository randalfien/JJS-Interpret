package cz.fit.cvut.paskaond.jsruntime.function;

import java.util.ArrayList;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;

public class JSPrintFunction extends JSBuiltInFunction {

	public JSPrintFunction() {
		super();
		formals = new ArrayList<>();
		formals.add("arg1");
	}

	@Override
	public Object execute() {
		for (String s : formals) {
			System.out.println( scope.get(s).toString() );
		}
		return JSUndefined.getInstance();
	}

}
