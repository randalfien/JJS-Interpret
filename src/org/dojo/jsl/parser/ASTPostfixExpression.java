/* Generated By:JJTree: Do not edit this line. ASTPostfixExpression.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSBinaryOperator;

/*
 * 		PostfixExpression
			Identifier[i]
			Operator
 * 
 */

public class ASTPostfixExpression extends SimpleNode implements Evalable {
	public ASTPostfixExpression(int id) {
		super(id);
	}

	public ASTPostfixExpression(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		Evalable ch0 = (Evalable) children[0];
		Node ch1 = children[1];
		Object newValue = null;
		if( ch0 instanceof ASTIdentifier ){
			String name = ((ASTIdentifier) ch0).getName();
			Object oldValue = env.get(name);
			JSBinaryOperator opFunc = null;
			String opType =  ((ASTOperator) ch1).getType();
			if( opType == "++" ){
				opFunc = (JSBinaryOperator) env.get("+");
			}else if( opType == "--"){
				opFunc = (JSBinaryOperator) env.get("-");
			}else{
				throw new RuntimeException("Unknown postfix operator:"+opType);
			}
			newValue = opFunc.call(oldValue, 1L);
			env.update(name, newValue);
		}else{
			throw new RuntimeException("Not implemented yet");
		}
		return newValue;
	}
}
/* JavaCC - OriginalChecksum=65b126c19fc404a1a72125712ce78ba4 (do not edit this line) */
