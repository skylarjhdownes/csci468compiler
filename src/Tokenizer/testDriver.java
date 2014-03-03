package Tokenizer;

import java.io.*;
import java.util.Scanner;

public class testDriver 
{
	private static Scanner reader;

	public static void main(String[] args) throws java.io.IOException
	{
		File testFile;
		reader = new Scanner(System.in);
		
		if( args.length == 0 )
		{
			File folder = new File("TestPrograms");
			File[] listOfFiles = folder.listFiles();
		    
			System.out.println("No input file given, ALL IS LOST. ABANDON SHIP!!!!!!!!!!!!!!!!");
			System.out.println("Actually, lets use a local test file.  Let's see...");
			
			
			System.out.println("Try picking from this list:");
			for (int i = 0; i < listOfFiles.length; i++) 
		    {
		    	if (listOfFiles[i].isFile()) {
		    		System.out.println(i + ": " + listOfFiles[i].getName());
		    	}
		    }
			
			System.out.print ("Enter an integer:");
			
//			boolean goodInput;
//			while(!goodInput)
//			{
				int userChoice = reader.nextInt();
				
//			}
		    
			testFile = new File("TestPrograms\\" + listOfFiles[userChoice].getName());
			
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
