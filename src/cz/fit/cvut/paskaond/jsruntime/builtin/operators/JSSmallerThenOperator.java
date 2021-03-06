package cz.fit.cvut.paskaond.jsruntime.builtin.operators;

import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;


public class JSSmallerThenOperator extends JSBinaryOperator {

	public JSSmallerThenOperator() {

	}

	@Override
	protected Object combine(Object left, Object right) {
		return CastUtils.castToNumber( left ) < CastUtils.castToNumber( right );
	}

}
