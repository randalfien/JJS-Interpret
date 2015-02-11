package cz.fit.cvut.paskaond.jsruntime.function;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;

public class JSDebugFunction extends JSBuiltInFunction {

	public JSDebugFunction() {
		super();
	}

	@Override
	public Object execute() {
		System.out.println("*"); //put breakpoint here to debug
		return JSUndefined.getInstance();
	}

}
