package cz.fit.cvut.paskaond.jsruntime.function;

import java.util.ArrayList;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;

public class JSParseIntFunction extends JSBuiltInFunction {

	public JSParseIntFunction() {
		super();
		formals = new ArrayList<>();
		formals.add("arg1");
	}

	@Override
	public Object execute() {
		double toNumber = CastUtils.castToNumber(scope.get("arg1"));
		if( Double.isNaN( toNumber ) ){
			return JSUndefined.getInstance();
		}
		return  ((Double) toNumber).intValue();
	}

}
