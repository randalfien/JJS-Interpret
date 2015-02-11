package cz.fit.cvut.paskaond.jsruntime.function.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSUndefined;
import cz.fit.cvut.paskaond.jsruntime.function.JSFunction;

public class JSWriteFileFunction extends JSFunction {

	public JSWriteFileFunction() {
		super(null, null);
		formals = new ArrayList<>();
		formals.add("filePath");
		formals.add("contents");
	}

	@Override
	public Object call() {
		String filePath = scope.get("filePath").toString();
		String contents = scope.get("contents").toString();
		System.out.println(contents);
		
		PrintWriter out;
		try {
			out = new PrintWriter(filePath);
			out.println(contents);
			out.close();
		} catch (FileNotFoundException e) {
			throw new cz.fit.cvut.paskaond.jsruntime.exception.FileNotFoundException(e.getMessage());
		}

		
		return JSUndefined.getInstance();
	}

}

