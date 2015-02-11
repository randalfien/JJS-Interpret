package cz.fit.cvut.paskaond.jsruntime;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSObject;
import cz.fit.cvut.paskaond.jsruntime.exception.NotDefinedException;

public class JSEnvironment extends JSObject{
	private JSEnvironment parent;
	
	private Object returnValue;
	private boolean returnCalled;
	private boolean breakCalled;
	
	public JSEnvironment( ){
		super("GlobalObject");
	}
	
	public JSEnvironment( JSEnvironment parent ){
		super("GlobalObject");
		this.parent = parent;
	}
	
	public JSEnvironment( JSEnvironment parent, Object thisObject ){
		super("GlobalObject");
		this.parent = parent;
		super.put("this", thisObject);
	}

	@Override
	public  Object get( Object key) {
		if( properties.containsKey(key)){
			return properties.get(key);
		}else if( parent != null ){
			return parent.get(key);
		}
		throw new NotDefinedException("Binding for "+key+" not found.");
	}
	/**
	 * Override key with object value only if already exists, to create new key use {@link putOrCreate}
	 * @param key
	 * @param object
	 */
	public void update( String key,  Object object ){
		if( properties.containsKey(key)){
			properties.put(key, object);
		}else if( parent != null ){
			parent.update(key, object);
		}else{
			//if we are top environment, create variable
			put(key, object);
		}
	}
	
	public void setThis(JSObject value) {
		put("this", value);
	}
	
	//===================
	//  RETURN HANDLING
	
	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
		this.returnCalled = true;
	}
	
	public boolean isReturnCalled(){
		return this.returnCalled;
	}
	
	public Object getReturnValue(){
		return this.returnValue;
	}
	
	public void setReturnCalled(boolean value) {
		returnCalled = value;
	}
	
	public boolean isBreakCalled() {
		return breakCalled;
	}

	public void setBreakCalled(boolean value) {
		this.breakCalled = value;
	}

	public JSEnvironment getParent() {
		return parent;
	}
}
