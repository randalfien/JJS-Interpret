/* Generated By:JJTree: Do not edit this line. ASTVariableDeclarationList.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

public
class ASTVariableDeclarationList extends SimpleNode {
  public ASTVariableDeclarationList(int id) {
    super(id);
  }

  public ASTVariableDeclarationList(EcmaScript p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=d59945caaad9959c37484417d7c766be (do not edit this line) */
