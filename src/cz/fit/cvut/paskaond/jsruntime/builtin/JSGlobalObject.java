package cz.fit.cvut.paskaond.jsruntime.builtin;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.function.JSParseIntFunction;

public class JSGlobalObject extends JSObject {

	public JSGlobalObject() {
		super("Object");
		put("undefined", JSUndefined.getInstance() );
		put("parseInt", new JSParseIntFunction() );
	}

}
