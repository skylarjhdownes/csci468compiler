package Tokenizer;

import java.io.File;

public class testDriver {

	public static void main(String[] args) {
		File testFile;
<<<<<<< HEAD
		
		if(args[0].isEmpty()
=======

		if( args[0].isEmpty() )
>>>>>>> 1362b83e8e0b568d211409a15f947308c3e0d84a
		{
			System.out.println("No input file give, ALL IS LOST. ABANDON SHIP!!!!!!!!!!!!!!!!");
		}
		else{
<<<<<<< HEAD
		testFile = new File(args[0]);
		
		Scanner sc = new Scanner(testFile);
		
		Token current;
		while ( sc.hasNextToken() ) {
			current = sc.getToken();
			System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
=======
			testFile = new File(args[0]);

			MicroPascalScanner sc = new MicroPascalScanner(testFile);

			Token current;
			while ( sc.hasNextToken() ) {
				current = sc.getToken();
				System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
			}
>>>>>>> 1362b83e8e0b568d211409a15f947308c3e0d84a
		}
		}
	}

}
