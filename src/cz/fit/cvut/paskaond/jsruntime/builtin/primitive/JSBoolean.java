package cz.fit.cvut.paskaond.jsruntime.builtin.primitive;

public class JSBoolean extends JSPrimitive{
	public static final JSBoolean TRUE = new JSBoolean("true");
	public static final JSBoolean FALSE = new JSBoolean("false");
	private final String value;
	
	public JSBoolean(String value){
		this.value = value;
	}
	
	public boolean compare(Object other){
		if( this == TRUE){
			if( other == TRUE){
				return true;
			}
			String strValue = other.toString();
			if( strValue.equals(FALSE.value) ){
				return false;
			}
			if( strValue.equals("0") || other.equals(0.0d)){
				return false;
			}
			if( other == JSUndefined.getInstance() ){
				return false;
			}
			if( other == JSNull.getInstance() ){
				return false;
			}
			if( other instanceof String && ((String)other).length() == 0 ){
				return false;
			}
			return true;
		}else{
			return !TRUE.equals(other);
		}
	}
	
	
	public JSBoolean TRUE() {
		return TRUE;
	}
	public JSBoolean FALSE() {
		return FALSE;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
