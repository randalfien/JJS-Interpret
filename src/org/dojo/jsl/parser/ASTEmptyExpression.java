/* Generated By:JJTree: Do not edit this line. ASTEmptyExpression.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;

public class ASTEmptyExpression extends SimpleNode implements Evalable {
	public ASTEmptyExpression(int id) {
		super(id);
	}

	public ASTEmptyExpression(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		return JSUndefined.getInstance();
	}
}
/* JavaCC - OriginalChecksum=a9177717512563db26fb83f7b8523f50 (do not edit this line) */
