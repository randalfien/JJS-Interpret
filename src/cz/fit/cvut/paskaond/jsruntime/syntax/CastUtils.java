package cz.fit.cvut.paskaond.jsruntime.syntax;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSObject;
import cz.fit.cvut.paskaond.jsruntime.builtin.JSString;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSBoolean;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSNull;

public class CastUtils {
	public static JSObject castToJSObject(Object o){
		if( o instanceof JSObject){
			return (JSObject) o;
		}
		if( o instanceof String){
			return new JSString((String) o);
		}
		return null;
	}

	public static boolean castToBoolean(Object o) {
		return JSBoolean.TRUE.compare(o);
	}
	
	public static double castToNumber(Object o){
		if( o instanceof Number ){
			return ((Number)o).doubleValue();
		}
		if( o == JSNull.getInstance() ){
			return 0; 
		}
		if( o instanceof JSBoolean ){
			return o == JSBoolean.TRUE ? 1 : 0;
		}
		if( o instanceof String){
			int i = tryParseIndex((String) o);
			if( i != Integer.MIN_VALUE ) {
				return i;
			}
		}
		/*if( o == JSUndefined.getInstance()){
			return Double.NaN;
		}*/
		
		return Double.NaN;
	}
	
	public static int tryParseIndex(String s) {
		
		int result = 0;
		boolean neg = false;
	    for(int i = 0; i < s.length(); i++) {
	    	result *= 10;
	    	char c = s.charAt(i);
	    	if( i == 0 && c == '-'){
	    		neg = true;
	    		continue;
	    	}
	    	int digit = Character.digit(c,10);
	        if( digit < 0) return Integer.MIN_VALUE;
	        result += digit;
	    }
	    return neg ? -result : result;
	}
}
