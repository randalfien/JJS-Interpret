package cz.fit.cvut.paskaond.jsruntime.function;

import java.util.List;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.JSObject;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;

public class JSFunction extends JSObject {

	protected List<String> formals;
	protected Evalable body;
	protected JSObject target;
	protected JSEnvironment scope;
	
	public JSFunction( List<String> formals, Evalable body){
		super("Function");
		this.formals = formals;
		this.body = body;
	}
	
	public Object call() {
		Object value;
		if( body != null ){
			value = body.evalIn(scope);
		}else{
			value = JSUndefined.getInstance();
		}
		setTarget(null);
		return value;
	}

	public List<String> getFormals() {
		return formals;
	}

	public JSObject getTarget() {
		return target;
	}

	public void setTarget(JSObject target) {
		this.target = target;
	}

	public JSEnvironment getScope() {
		return scope;
	}

	public void setScope(JSEnvironment scope) {
		this.scope = scope;
	}

}
