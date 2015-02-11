package cz.fit.cvut.paskaond.jsruntime.function;


public abstract class JSBuiltInFunction extends JSFunction{

	public JSBuiltInFunction() {
		super(null, null);
	}
	

	@Override
	public Object call() {
		Object value = execute();
		setTarget(null);
		return value;
	}
	
	protected abstract Object execute();

}
