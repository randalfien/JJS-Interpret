package cz.fit.cvut.paskaond.jsruntime.function.array;

import java.util.ArrayList;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSArray;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.function.JSBuiltInFunction;

public class JSArrayJoinFunction extends JSBuiltInFunction{
	public JSArrayJoinFunction() {
		super();
		this.formals = new ArrayList<>();
		this.formals.add("separator");
	}

	@Override
	public Object execute(){
		Object separator = scope.get("separator");
		if( separator == JSUndefined.getInstance() ){
			separator = ",";
		}
		return ((JSArray)scope.get("this")).join( separator.toString() );
	}

}
