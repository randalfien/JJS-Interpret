/* Generated By:JJTree: Do not edit this line. ASTExpressionStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.JSStatement;

public
class ASTExpressionStatement extends JSStatement {
	public ASTExpressionStatement(int id) {
		super(id);
	}

	public ASTExpressionStatement(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	public Object evalIn(JSEnvironment env) {
		//for (int i = 0; i < children.length; i++) {
			Node child = children[0];
			return ((Evalable)child).evalIn(env);
		//}
		//return null;
	}
}
/* JavaCC - OriginalChecksum=233b032b7d949b4de6bea955fd1f009f (do not edit this line) */
