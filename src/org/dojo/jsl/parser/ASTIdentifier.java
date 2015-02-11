package org.dojo.jsl.parser;

import org.dojo.jsl.parser.EcmaScript;
import org.dojo.jsl.parser.EcmaScriptVisitor;
import org.dojo.jsl.parser.Token;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;

public class ASTIdentifier extends SimpleNode implements Evalable {

	private String identifierName = null;

	private int hash = 0;

	public ASTIdentifier(int id) {
		super(id);
	}

	public ASTIdentifier(EcmaScript p, int id) {
		super(p, id);
	}

	/** Accept the visitor. * */
	@Override
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	public void setName(String identifierName) {
		this.identifierName = identifierName.intern(); // to lower number of
														// strings
		hash = identifierName.hashCode();
		Token token = getBeginToken();
		token.image = identifierName;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj.getClass().equals(this.getClass()))) {
			ASTIdentifier other = (ASTIdentifier) obj;
			return identifierName.equals(other.identifierName);
		}

		return false;
	}

	public String getName() {
		return identifierName;
	}

	@Override
	public String toString() {
		return "Identifier[" + identifierName + "]";
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		return env.get(identifierName);
	}

}