package cz.fit.cvut.paskaond.jsruntime.function.string;

import java.util.ArrayList;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSString;
import cz.fit.cvut.paskaond.jsruntime.function.JSBuiltInFunction;
import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;

public class JSStringCharAtFunction extends JSBuiltInFunction{
	public JSStringCharAtFunction() {
		super();
		this.formals = new ArrayList<>();
		this.formals.add("index");
	}

	@Override
	public Object execute(){
		Object position = scope.get("index");
		int numberPosition =  ((Double) CastUtils.castToNumber( position )).intValue();
		return ((JSString)scope.get("this")).charAt( numberPosition );
	}

}

