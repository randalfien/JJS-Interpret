package cz.fit.cvut.paskaond.jsruntime.builtin.operators;


public class JSAssignOperator extends JSBinaryOperator {


	@Override
	protected Object combine(Object left, Object right) {
		return right;
	}

}
