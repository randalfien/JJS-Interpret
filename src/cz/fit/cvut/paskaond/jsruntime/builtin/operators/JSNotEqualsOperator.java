package cz.fit.cvut.paskaond.jsruntime.builtin.operators;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSBoolean;


public class JSNotEqualsOperator extends JSBinaryOperator {

	public JSNotEqualsOperator() {

	}

	@Override
	protected Object combine(Object left, Object right) {
		return left.equals(right) ? JSBoolean.FALSE : JSBoolean.TRUE;
	}

}

  
