package cz.fit.cvut.paskaond.jsruntime.function.array;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSArray;
import cz.fit.cvut.paskaond.jsruntime.function.JSBuiltInFunction;

public class JSArrayPopFunction extends JSBuiltInFunction{
	
	@Override
	public Object execute(){
		return ((JSArray)scope.get("this")).pop();
	}

} 
