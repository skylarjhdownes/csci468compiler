package Tokenizer;

import java.io.*;
import java.util.Scanner;

public class testDriver 
{
	public static void main(String[] args) throws java.io.IOException
	{
		File testFile = new File("TestPrograms\\derpFile.derp");
		Scanner reader = new Scanner(System.in);
		int userChoice = 0;
		
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
		    		System.out.println((i+1) + ": " + listOfFiles[i].getName());
		    	}
		    }
			
			System.out.print ("Enter an integer:");
			
			boolean goodInput = false;
			while(!goodInput)
			{
				try
				{
					userChoice = reader.nextInt();
					testFile = new File("TestPrograms\\" + listOfFiles[userChoice-1].getName());
					goodInput = true;
				}
				catch (java.util.InputMismatchException e)
				{
					System.out.println("That's not an int.  Please enter an integer from the list.");
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					System.out.println("That's not in the list.  Please enter an integer from the list.");
				}
			}			
			
			MicroPascalScanner sc = new MicroPascalScanner(testFile);
			System.out.println(testFile);
			Token current;
			while ( sc.hasNextToken() ) 
			{
				current = sc.getToken();
				System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
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
