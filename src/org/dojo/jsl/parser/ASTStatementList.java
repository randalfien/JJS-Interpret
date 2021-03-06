/* Generated By:JJTree: Do not edit this line. ASTStatementList.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;

public class ASTStatementList extends SimpleNode implements Evalable {
	public ASTStatementList(int id) {
		super(id);
	}

	public ASTStatementList(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	public Object evalIn(JSEnvironment env) {

		Object lastValue = null;
		for (int i = 0; i < children.length; i++) {
			if( env.isReturnCalled() ){
				return env.getReturnValue();
			}
			Node child = children[i];
			lastValue = ((Evalable) child).evalIn(env);
		}
		return lastValue;
	}

}
/* JavaCC - OriginalChecksum=20541445835ba3f7188bda309b98bafa (do not edit this line) */
