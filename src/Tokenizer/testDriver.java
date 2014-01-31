package Tokenizer;

import java.io.File;

public class testDriver {

	public static void main(String[] args) {
		File testFile;
		
		if(args[0].isEmpty())
		{
			System.out.println("No input file given, ALL IS LOST. ABANDON SHIP!!!!!!!!!!!!!!!!");
		}
		else{
			testFile = new File(args[0]);

			MicroPascalScanner sc = new MicroPascalScanner(testFile);

			Token current;
			while ( sc.hasNextToken() ) {
				current = sc.getToken();
				System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
			}
		}
	}
}
