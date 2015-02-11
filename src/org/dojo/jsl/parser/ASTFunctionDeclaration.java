/* Generated By:JJTree: Do not edit this line. ASTFunctionDeclaration.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import java.util.List;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.function.JSFunction;

/*
 * NAMED:
 * FunctionDeclaration
		Identifier[a]
		FormalParameterList
			Identifier[arg1]
		Block
			ReturnStatement
				BinaryExpressionSequence
					Identifier[arg1]
					Operator
					StringLiteral[World]
					
	ANNONYMOUS:
	FunctionDeclaration
			FormalParameterList
			Block
				ReturnStatement
					AssignmentExpression
						Identifier[counter]
						Operator
						NumberLiteral[1]
 * 
 */

public class ASTFunctionDeclaration extends SimpleNode implements Evalable {

	public ASTFunctionDeclaration(int id) {
		super(id);
	}

	public ASTFunctionDeclaration(EcmaScript p, int id) {
		super(p, id);
	}

	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		String name = null;
		ASTFormalParameterList paramList;
		ASTBlock block;
		
		if( children.length == 2 ){ //Annonymous function
			paramList = (ASTFormalParameterList) children[0];
			block = (ASTBlock) children[1];
		}else{ //Named function
			name = ((ASTIdentifier) children[0]).getName();	
			paramList = (ASTFormalParameterList) children[1];
			block = (ASTBlock) children[2];
		}
		block.setCanReturn(true);
		List<String> formals = paramList.getFormalsList(env);
		JSFunction func = new JSFunction(formals, block);
		if( name != null ){
			env.put(name, func);
		}
		
		func.setScope( env ); //TODO , find if block contains free variables, else dont save scope
		
		return func; 
	}
	
}
/* JavaCC - OriginalChecksum=e0ea58a8a2a98b5d3bee0dbb8a2f72d3 (do not edit this line) */