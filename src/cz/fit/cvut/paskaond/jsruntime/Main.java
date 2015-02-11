package cz.fit.cvut.paskaond.jsruntime;

import java.io.FileInputStream;

import cz.fit.cvut.paskaond.jsruntime.builtin.JSArray;


public class Main {

	public static void main(String[] args) {
		
		JSInterpreter interpreter = new JSInterpreter();
		
		if(args.length == 0){
			System.out.println("Reading from standard input . . .");
			interpreter.loadProgram(System.in);
		} else if(args.length >= 1){
			System.out.println("Reading from file " + args[0] + " . . .");
			try {
				interpreter.loadProgram( new FileInputStream(args[0]) );
			} catch(java.io.FileNotFoundException e){
				System.out.println("File " + args[0] + " not found.");
				return;
			}
			
			if(args.length > 1 ){
				JSArray jsargs = new JSArray();
				System.out.print("SuppliedArguments: ");
				for (int i = 1; i < args.length; i++) {
					jsargs.push(args[i]);
					System.out.print( args[i] );
					if( i < args.length - 1 ){
						System.out.print(", ");
					}else{
						System.out.println("");
					}
				}
				interpreter.setArguments( jsargs );
			}
		}
		try{	
			interpreter.execute();
		}catch(Exception e){
			System.out.print("Exception occurred: \n"+e.toString());
		}
    }

}
