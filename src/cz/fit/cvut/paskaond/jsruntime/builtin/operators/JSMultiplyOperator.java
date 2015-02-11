package cz.fit.cvut.paskaond.jsruntime.builtin.operators;

import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;


public class JSMultiplyOperator extends JSBinaryOperator {

	@Override
	protected Object combine(Object left, Object right) {
		if( left instanceof Long && right instanceof Long){
			return (Long)left * (Long)right;
		}
		return CastUtils.castToNumber( left ) * CastUtils.castToNumber( right );
	}

}
