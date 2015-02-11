package cz.fit.cvut.paskaond.jsruntime.builtin.primitive;

public class JSNumber extends JSPrimitive{
	//private final static String NaN_String = "NaN";
	private final JSNumber NaN = new JSNumber(Double.NaN);
	private double value;
	public JSNumber(double value){
		this.setValue(value);
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public JSNumber getNaN() {
		return NaN;
	}
}
