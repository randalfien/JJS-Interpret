/* Generated By:JJTree: Do not edit this line. ASTFinallyClause.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

public
class ASTFinallyClause extends SimpleNode {
  public ASTFinallyClause(int id) {
    super(id);
  }

  public ASTFinallyClause(EcmaScript p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=5ee4a32523d28574e78bbf05bba465dd (do not edit this line) */
