/* Generated By:JJTree: Do not edit this line. ASTCaseGroup.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

public
class ASTCaseGroup extends SimpleNode {
  public ASTCaseGroup(int id) {
    super(id);
  }

  public ASTCaseGroup(EcmaScript p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=8c22a9176c34a873d47d35b05c34d98e (do not edit this line) */
