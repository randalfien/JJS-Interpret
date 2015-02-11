/* Generated By:JJTree: Do not edit this line. ASTCompositeReference.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.dojo.jsl.parser;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.JSObject;
import cz.fit.cvut.paskaond.jsruntime.builtin.operators.JSBinaryOperator;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.function.JSFunction;
import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;
/*
 * Case A:
 * 		CompositeReference
				Identifier[a]
				PropertyIdentifierReference
					Identifier[b]
				PropertyIdentifierReference
					Identifier[c]
				PropertyIdentifierReference
					Identifier[d]
 * 
 * Case B (b.c.funkce()[\"ref\"] = 5 ;):   
 * 		CompositeReference
				CompositeReference
					Identifier[b]
					PropertyIdentifierReference
						Identifier[c]
					PropertyIdentifierReference
						Identifier[funkce]
				FunctionCallParameters
				PropertyValueReference
					StringLiteral[ref]
 * 
 */
public class ASTCompositeReference extends SimpleNode implements Evalable {
	public ASTCompositeReference(int id) {
		super(id);
	}

	public ASTCompositeReference(EcmaScript p, int id) {
		super(p, id);
	}


	/** Accept the visitor. **/
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		JSObject target = getFinalObject(env);
		Evalable n = (Evalable) children[children.length-1];
		Object value;
		if( n instanceof ASTFunctionCallParameters ){
			value = callFunction( target, (ASTFunctionCallParameters) n, env);
		}else{
			Object targetName = n.evalIn(env);
			value = target.get(targetName);
		}
		if( value instanceof JSFunction){
			if( ((JSFunction) value).getTarget() == null){
				((JSFunction) value).setTarget(target);
			}
		}
		return value;	
	}

	private Object callFunction(Object func, ASTFunctionCallParameters params, JSEnvironment env) {
		
		if( func == JSUndefined.getInstance() ){
			return func;
		}
		
		JSFunction f = (JSFunction) func;
		
		if( f.getScope() == null){
			f.setScope(new JSEnvironment(env));
		}else{
			f.setScope( new JSEnvironment(f.getScope()));
		}
		JSEnvironment context = f.getScope();
		if( f.getTarget() == null ){
			f.setTarget(env);
		}
		context.setThis(f.getTarget());
		params.evalToEnv( f.getFormals(), env, context);		
		
		return f.call();
	}

	public Object setToFinalObject(Object toAssign, JSBinaryOperator op, JSEnvironment env) {
		JSObject target = getFinalObject(env);
		Object property = ((Evalable) children[children.length-1]).evalIn(env);
		toAssign = op.call(property, toAssign);
		target.put(property, toAssign);
		return toAssign;
	}
	
	public JSObject getFinalObject(JSEnvironment env){
		Evalable n = (Evalable) children[0];
		JSObject target = CastUtils.castToJSObject( n.evalIn(env) );
		Object value = null;
		for (int i = 1; i < children.length-1; i++) {
			n = (Evalable) children[i];	
			if( n instanceof ASTFunctionCallParameters ){
				value = callFunction( target, (ASTFunctionCallParameters) n, env );
			}else{
				Object property = ((Evalable) n).evalIn(env);
				value = target.get( property );
			}
			if( value instanceof JSFunction){
				if( ((JSFunction) value).getTarget() == null){
					((JSFunction) value).setTarget(target);
				}
			}
			target = CastUtils.castToJSObject( value );
		}
		return target;
	}
}
/* JavaCC - OriginalChecksum=6987dfae2b92d8a44a4b832dd63bfee7 (do not edit this line) */