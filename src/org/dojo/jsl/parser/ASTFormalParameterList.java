/* Generated By:JJTree: Do not edit this line. ASTFormalParameterList.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import java.util.ArrayList;
import java.util.List;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;

public class ASTFormalParameterList extends SimpleNode implements Evalable{
	public ASTFormalParameterList(int id) {
		super(id);
	}

	public ASTFormalParameterList(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	/** @returns List<String> of argument names*/
	@Override
	public Object evalIn(JSEnvironment env) {
		return getFormalsList(env);
	}

	public List<String> getFormalsList(JSEnvironment env) {
		if( children == null || children.length < 1 ) return null;
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < children.length; i++) {
			list.add( ((ASTIdentifier) children[i]).getName() );
		}
		return list;
	}
}
/* JavaCC - OriginalChecksum=92e3e81237c59f44f16ead4e2500ab5e (do not edit this line) */