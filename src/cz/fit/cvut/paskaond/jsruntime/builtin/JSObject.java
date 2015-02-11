package cz.fit.cvut.paskaond.jsruntime.builtin;

import java.util.HashMap;
import java.util.Map;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;


public class JSObject extends VMObject {
	
	protected Map<String, Object> properties = new HashMap<String, Object>(); 
	
	public JSObject (String className) {
		properties.put("class", className);
	}
	
	public void put(Object propertyName, Object value){
		properties.put(propertyName.toString(), value);
	}
	
	public Object get(Object propertyName){
		Object result = properties.get(propertyName.toString());
		if( result == null ){
			return JSUndefined.getInstance();
		}
		return result;
	}
	
	public boolean hasProperty(String propertyName){
		return properties.containsKey(propertyName);
	}
}
