package cz.fit.cvut.paskaond.jsruntime.function.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import cz.fit.cvut.paskaond.jsruntime.exception.FileNotFoundException;
import cz.fit.cvut.paskaond.jsruntime.function.JSFunction;

public class JSReadFileFunction extends JSFunction {

	public JSReadFileFunction() {
		super(null, null);
		formals = new ArrayList<>();
		formals.add("filePath");
	}

	@Override
	public Object call() {
		String filePath = scope.get("filePath").toString();
		String result = null;
		try {
			result = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			throw new FileNotFoundException("File:"+filePath+" not found.");
		}
		
		return result;
	}

}
