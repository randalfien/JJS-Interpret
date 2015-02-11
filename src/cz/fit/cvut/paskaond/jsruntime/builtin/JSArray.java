package cz.fit.cvut.paskaond.jsruntime.builtin;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.function.array.JSArrayJoinFunction;
import cz.fit.cvut.paskaond.jsruntime.function.array.JSArrayPopFunction;
import cz.fit.cvut.paskaond.jsruntime.function.array.JSArrayPushFunction;
import cz.fit.cvut.paskaond.jsruntime.syntax.CastUtils;

public class JSArray extends JSObject{

	private static final Object JOIN_FUNCTION = new JSArrayJoinFunction();
	private static final Object PUSH_FUNCTION = new JSArrayPushFunction();
	private static final Object POP_FUNCTION = new JSArrayPopFunction();
	
	private int maxIndex = -1;
	
	public JSArray() {
		super("Array");
		properties.put("length", 0);
		properties.put("join", JOIN_FUNCTION);
		properties.put("push", PUSH_FUNCTION);
		properties.put("pop", POP_FUNCTION);
	}
	
	@Override
	public void put(Object propertyName, Object value) {
		
		// Array handles integer properties differently
		
		int val = -1;
		if( propertyName instanceof Number ){
			val = ((Number) propertyName).intValue();
		}else if( propertyName instanceof String ){
			val = CastUtils.tryParseIndex((String) propertyName);
		}
		if( val > maxIndex){
			maxIndex = val;
			updateLength();
		}
		
		if( propertyName.equals("length") && value.equals(maxIndex+1) == false){
			//TODO truncate arrray
			throw new RuntimeException("Not implemented yet");
		}
		
		if( val >=  0){
			super.put(val, value);
		}else{
			super.put(propertyName, value);
		}
	}

	@Override
	public Object get(Object propertyName) {
		if( propertyName instanceof Number ){
			int val = ((Number) propertyName).intValue();
			return super.get(val);
			
		}
		return super.get(propertyName);
	}


	private void updateLength() {
		properties.put("length", maxIndex+1);
	}

	public Object join(String separator) {
		StringBuilder sb = new StringBuilder();
		String val = null;
		for (int i = 0; i <= maxIndex; i++) {
			Object ret =  properties.get( String.valueOf(i) );
			val = ret == null ? "null" : ret.toString();
			if( val != null ){
				sb.append( val );
			}
			if( i != maxIndex ){
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	
	public Object push(Object o){
		maxIndex++;
		updateLength();
		properties.put( String.valueOf(maxIndex), o);
		return o;
	}
	
	public Object pop(){
		maxIndex--;
		updateLength();
		Object result = properties.get( String.valueOf(maxIndex+1) );
		return result == null ? JSUndefined.getInstance() : result;
	}
	

	@Override
	public String toString() {
		return /*"Array["+*/join(",").toString()/*+"]"*/;
	}

	
}
