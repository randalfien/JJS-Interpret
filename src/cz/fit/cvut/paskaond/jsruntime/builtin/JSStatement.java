package cz.fit.cvut.paskaond.jsruntime.builtin;

import org.dojo.jsl.parser.EcmaScript;
import org.dojo.jsl.parser.SimpleNode;

import cz.fit.cvut.paskaond.jsruntime.Evalable;

public abstract class JSStatement extends SimpleNode implements Evalable{

	public JSStatement(int i) {
		super(i);
	}
	public JSStatement(EcmaScript p, int i) {
		super(p, i);
	}

}
