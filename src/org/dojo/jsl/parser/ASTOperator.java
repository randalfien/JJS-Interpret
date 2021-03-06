/* Generated By:JJTree: Do not edit this line. ASTOperator.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

public
class ASTOperator extends SimpleNode {
	private String type;

	public ASTOperator(int id) {
		super(id);
	}

	public ASTOperator(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	@Override
	public void setBeginToken(Token beginToken) {
		super.setBeginToken(beginToken);
		this.type = beginToken.image;
	}

	public String getType(){
		return type;
	}
}
/* JavaCC - OriginalChecksum=5ed52daf53d693065c3c17efe4fffea3 (do not edit this line) */
