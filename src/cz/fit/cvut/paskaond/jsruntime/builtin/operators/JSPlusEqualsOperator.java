package cz.fit.cvut.paskaond.jsruntime.builtin.operators;


public class JSPlusEqualsOperator extends JSBinaryOperator {

	private static final JSAddOperator ADD_OP = new JSAddOperator();
	
	@Override
	protected Object combine(Object left, Object right) {
		return ADD_OP.combine(left, right);
	}

}
