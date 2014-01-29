package csci468compiler;

import java.util.Scanner;
import java.io.*;

public class microPascalScanner {
	public enum Token 
	{
		//Reserved words:
	    MP_AND, MP_BEGIN, MP_DIV, MP_DO, MP_DOWNTO, MP_ELSE,
	    MP_END, MP_FIXED, MP_FLOAT, MP_FOR, MP_FUNCTION, MP_IF,
	    MP_INTEGER, MP_MOD, MP_NOT, MP_OR, MP_PROCEDURE, 
	    MP_PROGRAM, MP_READ, MP_REPEAT, MP_THEN, MP_TO, MP_UNTIL, 
	    MP_VAR, MP_WHILE, MP_WRITE,
	    
	    //Identifiers and literals:
	    MP_IDENTIFIER, MP_INTEGER_LIT, MP_FIXED_LIT, MP_FLOAT_LIT , MP_STRING_LIT, 
	    
	    //Symbols:
	    MP_PERIOD, MP_COMMA, MP_SCOLON, MP_LPAREN, MP_RPAREN, 
	    MP_EQUAL, MP_GTHAN, MP_GEQUAL, MP_LTHAN, MP_LEQUAL, 
	    MP_NEQUAL, MP_ASSIGN, MP_PLUS, MP_MINUS, MP_TIMES, MP_COLON,
	    
	    //End of file:
	    MP_EOF,
	    
	    //Errors:
	    MP_RUN_COMMENT, MP_RUN_STRING, MP_ERROR
	}
	Scanner scanner;
	String lexeme;
	int lineNumber;
	int columnNumber;
	
	public void openFile() throws FileNotFoundException
	{
		try
		{
			scanner = new Scanner(new BufferedReader(new FileReader("source-code-file.pas")));
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
	
