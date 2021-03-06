/* Generated By:JJTree: Do not edit this line. ASTVariableDeclaration.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;

public class ASTVariableDeclaration extends SimpleNode implements Evalable {
	public ASTVariableDeclaration(int id) {
		super(id);
	}

	public ASTVariableDeclaration(EcmaScript p, int id) {
		super(p, id);
	}

	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		String name = ((ASTIdentifier) children[0]).getName();
		Object value;
		if( children.length > 1 ){
			value = ((Evalable) children[1]).evalIn(env);
		}else{
			value = JSUndefined.getInstance();
		}
		env.put(name, value);
		return value; //could be useful for a = b = 3 
	}
	
	
}
/* JavaCC - OriginalChecksum=d348ae915dad149efd82bbcd1bca15f4 (do not edit this line) */
