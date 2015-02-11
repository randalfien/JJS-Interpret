package cz.fit.cvut.paskaond.jsruntime.builtin.primitive;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSObject;
import cz.fit.cvut.paskaond.jsruntime.exception.NullObjectReference;


public class JSNull extends JSObject{

	private static final String NULL = "null";

	private static final JSNull instance = new JSNull();
		
	public JSNull() {
		super("Null");
	}

	
	@Override
	public String toString(){
		return NULL;
	}

	public static JSNull getInstance() {
		return instance;
	}
	
	@Override
	public void put(Object propertyName, Object value) {
		//do nothing
	}

	@Override
	public Object get(Object propertyName) {
		throw new NullObjectReference("Property "+propertyName+" called on a null object.");
	}
}
