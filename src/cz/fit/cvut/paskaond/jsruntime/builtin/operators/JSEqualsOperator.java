package cz.fit.cvut.paskaond.jsruntime.builtin.operators;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSBoolean;


public class JSEqualsOperator extends JSBinaryOperator {

	public JSEqualsOperator() {

	}

	@Override
	protected Object combine(Object left, Object right) {
		if( left instanceof Number && right instanceof Number){
			return ((Number)left).longValue() == ((Number)right).longValue(); 
		}
		return left.equals(right) ? JSBoolean.TRUE : JSBoolean.FALSE;
	}

}

  
