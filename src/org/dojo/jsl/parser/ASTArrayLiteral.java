/* Generated By:JJTree: Do not edit this line. ASTArrayLiteral.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.JSArray;
import cz.fit.cvut.paskaond.jsruntime.builtin.JSObject;

public class ASTArrayLiteral extends SimpleNode implements Evalable{
	public ASTArrayLiteral(int id) {
		super(id);
	}

	public ASTArrayLiteral(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		JSObject object = new JSArray();
		if( children == null || children.length == 0){
			return object;
		}
		for (int i = 0; i < children.length; i++) {
			Object value = ((Evalable) children[i]).evalIn(env);
			object.put( i, value);
		}
		return object;
	}
}
/* JavaCC - OriginalChecksum=6f8d88c50355c338f8a478cc87415936 (do not edit this line) */
