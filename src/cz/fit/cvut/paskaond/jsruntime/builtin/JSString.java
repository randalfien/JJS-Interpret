package cz.fit.cvut.paskaond.jsruntime.builtin;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.function.string.JSStringCharAtFunction;
import cz.fit.cvut.paskaond.jsruntime.function.string.JSStringSplitFunction;


public class JSString extends JSObject{

	private static final Object SPLIT_FUNCTION = new JSStringSplitFunction();
	private static final Object CHAR_AT_FUNCTION = new JSStringCharAtFunction();
	/*private static final Object POP_FUNCTION = new JSArrayPopFunction();*/
	
	private String value;
	
	public JSString(String value) {
		super("String");
		properties.put("length", 0);
		properties.put("split", SPLIT_FUNCTION);
		properties.put("charAt", CHAR_AT_FUNCTION);
		setValue(value); 
	}
	
	private void updateLength() {
		properties.put("length", value == null ? 0 : value.length() );
	}

	public Object split(String separator) {
		if( separator.length() > 1 ){
			throw new RuntimeException("Not implemented yet");
		}
		char separatorChar = separator.charAt(0);
		JSArray result = new JSArray();
		StringBuilder nextVal = new StringBuilder();
		for(int i = 0; i < value.length(); i++) {
			char ch =  value.charAt(i);
			if( ch == separatorChar ){
				result.push(nextVal.toString());
				nextVal = new StringBuilder();
			}else{
				nextVal.append(ch);
			}
	    }
		result.push(nextVal.toString());
		return result;
	}

	public Object charAt(int position){
		if( position < 0 || position >= value.length()){
			return JSUndefined.getInstance();
		}
		return String.valueOf( value.charAt(position) );
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		updateLength();
	}

	@Override
	public String toString() {
		return value;
	}

}
