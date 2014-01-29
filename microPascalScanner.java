package csci468compiler;

import java.util.Scanner;
import java.io.*;

public class microPascalScanner {
	Scanner scanner;
	String lexeme;
	int lineNumber;
	int columnNumber;
	
	public void openFile() throws FileNotFoundException
	{
		try
		{
			scanner = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File doesn't seem to exist.  Wacky.");
		}
	}
	public String getToken()
	{
		return scanner.next();
	}
	public String getLexeme(String token)
	{
		
		return lexeme;
	}
	public int getLineNumber(String token)
	{
		return lineNumber;
	}
	public int getColumnNumber(String token)
	{
		return columnNumber;
	}
	
	
}
	
