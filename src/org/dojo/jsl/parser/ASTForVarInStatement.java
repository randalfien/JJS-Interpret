/* Generated By:JJTree: Do not edit this line. ASTForVarInStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

public
class ASTForVarInStatement extends SimpleNode {
  public ASTForVarInStatement(int id) {
    super(id);
  }

  public ASTForVarInStatement(EcmaScript p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=ea54e9f8973155b8787608844030a390 (do not edit this line) */
