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
	BufferedReader fileReader; 
	public static Token currentToken;  //I'm going to build this using public stuff to get it off the ground
	public static String lexeme = "";
	public static int lineNumber = 0;
	public static int columnNumber = 0;
	public static int currentFilePosition = 0;
	public static char lookaheadCharacter;


	public void openFile() throws FileNotFoundException
	{
		try
		{
			fileReader = new BufferedReader(new FileReader("source-code-file.pas"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File doesn't seem to exist.  Wacky.");
		}
	}
	public Token getToken()
	{
		return currentToken;
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
	
	private void nextCharacter() {
		try {
			lookaheadCharacter = (char)fileReader.read(); columnNumber++; currentFilePosition++;
			if (lookaheadCharacter == '\n') {lineNumber++; columnNumber = 0;}
			else if (lookaheadCharacter == '\uffff') lookaheadCharacter = '\u0080';
		} catch (IOException e) {
			lookaheadCharacter = '\u0080';
		}
	}
	
	public static void dispatcher()
	{
		lexeme = "";  //Make lexeme string blank since we're about to write a new one.  We may want to move this later.
		if (Character.isLetter(microPascalScanner.lookaheadCharacter))
		{
			
		}
		else if (Character.isDigit(microPascalScanner.lookaheadCharacter))
		{
			isNumericToken();
		}
		else if (Character.isDigit(microPascalScanner.lookaheadCharacter))
		{
			
		}
	}
	
	public static void isIdentifierOrReservedToken()
	{
		//TODO 
	}
	
	public static Token isNumericToken()
	{
		Token returnToken;
		char currentChar = 'd';
		if (Character.isDigit(currentChar))
		{
			lexeme.concat(Character.toString(currentChar)); //I'm ending up casting between char and String a lot, 
			returnToken = MP_INTEGER_LIT; 					//hopefully I'll be able to make it work as one or the other eventually.
		}
		else
		{
			return Token.MP_ERROR;
		}
		
		while
		
	}
	
	public static void isSymbolToken()
	{
		//TODO
	}
	
	
}
	
