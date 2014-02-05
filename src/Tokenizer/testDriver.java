package Tokenizer;

import java.io.File;

public class testDriver {

	public static void main(String[] args) {
		File testFile;

		if( args.length == 0 )
		{
			System.out.println("No input file give, ALL IS LOST. ABANDON SHIP!!!!!!!!!!!!!!!!");
			
			testFile = new File("C:\\Users\\Stephen Bush\\Documents\\Test.txt");

			MicroPascalScanner sc = new MicroPascalScanner(testFile);

			Token current;
			while ( sc.hasNextToken() ) {
				current = sc.getToken();
				System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
			}
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
