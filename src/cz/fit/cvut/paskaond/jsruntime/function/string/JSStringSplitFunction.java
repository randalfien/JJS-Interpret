package cz.fit.cvut.paskaond.jsruntime.function.string;

import java.util.ArrayList;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSString;
import cz.fit.cvut.paskaond.jsruntime.function.JSBuiltInFunction;

public class JSStringSplitFunction extends JSBuiltInFunction{
	public JSStringSplitFunction() {
		super();
		this.formals = new ArrayList<>();
		this.formals.add("separator");
	}

	@Override
	public Object execute(){
		Object separator = scope.get("separator");
		return ((JSString)scope.get("this")).split( separator.toString() );
	}

}
