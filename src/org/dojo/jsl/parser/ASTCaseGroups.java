/* Generated By:JJTree: Do not edit this line. ASTCaseGroups.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

public
class ASTCaseGroups extends SimpleNode {
  public ASTCaseGroups(int id) {
    super(id);
  }

  public ASTCaseGroups(EcmaScript p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=e6951277ec2d22a0104f60edda6f0d33 (do not edit this line) */
