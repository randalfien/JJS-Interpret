/* Generated By:JJTree: Do not edit this line. ASTContinueStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

public
class ASTContinueStatement extends SimpleNode {
  public ASTContinueStatement(int id) {
    super(id);
  }

  public ASTContinueStatement(EcmaScript p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=c6939da4611621045a9a063dc75c17c6 (do not edit this line) */
