/* Generated By:JJTree: Do not edit this line. ASTAndExpressionSequence.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSBinaryOperator;
import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;

/*
 * children: [BooleanLiteral[true], Operator, BooleanLiteral[false]]
 * 
 */
public class ASTAndExpressionSequence extends SimpleNode implements Evalable {
	public ASTAndExpressionSequence(int id) {
		super(id);
	}

	public ASTAndExpressionSequence(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		Object value = ((Evalable) children[0]).evalIn(env); 
		
		for( int i = 1; i < children.length; i+=2 ){
			ASTOperator op = (ASTOperator) children[i];
			JSBinaryOperator opFunc = (JSBinaryOperator) env.get(op.getType());
			Object rightChild = ((Evalable) children[i+1]).evalIn(env);
			value = opFunc.call( value, rightChild );
		}
		return CastUtils.castToBoolean( value );
	}
}
/* JavaCC - OriginalChecksum=5320d10348eeb81a3e7eb51b90014e21 (do not edit this line) */
