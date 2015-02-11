package cz.fit.cvut.paskaond.jsruntime.builtin.operators;

import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;

public class JSAndOperator extends JSBinaryOperator{

	@Override
	protected Object combine(Object left, Object right) {
		return CastUtils.castToBoolean( left ) && CastUtils.castToBoolean( right );
	}
}
