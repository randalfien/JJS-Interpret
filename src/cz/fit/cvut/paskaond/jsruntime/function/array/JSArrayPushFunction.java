package cz.fit.cvut.paskaond.jsruntime.function.array;

import java.util.ArrayList;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSArray;
import cz.fit.cvut.paskaond.jsruntime.function.JSBuiltInFunction;

public class JSArrayPushFunction extends JSBuiltInFunction{
	public JSArrayPushFunction() {
		super();
		this.formals = new ArrayList<>();
		this.formals.add("value");
	}

	@Override
	public Object execute(){
		return ((JSArray)scope.get("this")).push( scope.get("value") );
	}

}
