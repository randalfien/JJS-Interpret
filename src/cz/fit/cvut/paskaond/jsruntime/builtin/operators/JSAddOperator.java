package cz.fit.cvut.paskaond.jsruntime.builtin.operators;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;


public class JSAddOperator extends JSBinaryOperator {

	public JSAddOperator() {

	}

	@Override
	protected Object combine(Object left, Object right) {
		if( left instanceof String || right instanceof String){
			return left.toString()+right.toString();
		}if( left == JSUndefined.getInstance() || right == JSUndefined.getInstance() ){
			return JSUndefined.getInstance();
		}if( left instanceof Long && right instanceof Long){
			return (Long)left + (Long)right;
		}else{
			return CastUtils.castToNumber( left ) + CastUtils.castToNumber( right );
		}
	}

}
