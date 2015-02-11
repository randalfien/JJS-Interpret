package cz.fit.cvut.paskaond.jsruntime.builtin.operators;

import cz.fit.cvut.paskaond.jsruntime.function.JSFunction;

public abstract class JSBinaryOperator extends JSFunction   {

	public JSBinaryOperator( ) {
		super(null,null);
	}
	
	public Object call(Object arg1, Object arg2){
		return combine(arg1,arg2);
	}
/*	
	@Override
	public Object call(){
		if( args.size() != 2 ){
			throw new RuntimeException("BinaryOperator got "+args.size()+" args, expected 2");
		}
		return combine(args.get(0),args.get(1));
	}*/

	abstract protected Object combine(Object left, Object right);
}
