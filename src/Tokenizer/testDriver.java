package Tokenizer;

import java.io.File;

public class testDriver 
{
	public static void main(String[] args) throws java.io.IOException
	{
		File testFile;

		if( args.length == 0 )
		{
			System.out.println("No input file given, ALL IS LOST. ABANDON SHIP!!!!!!!!!!!!!!!!");
			System.out.println("Unless, perhaps, we have a local test file?  Let's see...");
			testFile = new File("src\\Test.txt");
			if (testFile.isFile())
			{
				System.out.println("Hey!  There's a test file.  Let's use that.");
			}
			else
			{
				System.out.println("NOOOOOOPE!  There is no test file at src\\Test.txt.  RUN FOR THE HILLS!");
			}
			MicroPascalScanner sc = new MicroPascalScanner(testFile);

			Token current;
			while ( sc.hasNextToken() ) 
			{
				current = sc.getToken();
//				System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
				current.getToken();
			}
		}
		else
		{
			testFile = new File(args[0]);

			MicroPascalScanner sc = new MicroPascalScanner(testFile);

			Token current;
			while ( sc.hasNextToken() ) 
			{
				current = sc.getToken();
				System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
			}
		}
	}
}
