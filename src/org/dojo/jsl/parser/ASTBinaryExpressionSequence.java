/* Generated By:JJTree: Do not edit this line. ASTBinaryExpressionSequence.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.JSStatement;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSBinaryOperator;

public
class ASTBinaryExpressionSequence extends JSStatement {
	public ASTBinaryExpressionSequence(int id) {
		super(id);
	}

	public ASTBinaryExpressionSequence(EcmaScript p, int id) {
		super(p, id);
	}

	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	public Object evalIn(JSEnvironment env) {
		if(children.length < 3){
			throw new RuntimeException("BinaryExpression missing children");
		}
		Object value = ((Evalable) children[0]).evalIn(env); 
		
		for( int i = 1; i < children.length; i+=2 ){
			ASTOperator op = (ASTOperator) children[i];
			JSBinaryOperator opFunc = (JSBinaryOperator) env.get(op.getType());
			Object rightChild = ((Evalable) children[i+1]).evalIn(env);
			value = opFunc.call( value, rightChild );
		}
		
		return value;
	}
}
/* JavaCC - OriginalChecksum=6214425386529a059c437200ecf70d8a (do not edit this line) */