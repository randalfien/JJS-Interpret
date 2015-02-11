package cz.fit.cvut.paskaond.jsruntime.builtin.primitive;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSObject;

public class JSUndefined extends JSObject{

	public JSUndefined() {
		super("Undefined");
	}

	private static final String UNDEFINED = "undefined";

	private static final JSUndefined instance = new JSUndefined();
		
	@Override
	public String toString(){
		return UNDEFINED;
	}

	public static JSUndefined getInstance() {
		return instance;
	}
	
	@Override
	public void put(Object propertyName, Object value) {
		//do nothing
	}

	@Override
	public Object get(Object propertyName) {
		return this;
	}

}
