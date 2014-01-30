package csci468compiler;

public class TokenFSAs
{
	public static void dispatcher()
	{
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
	public static void isNumericToken()
	{
		char currentChar = 'd';
		if (Character.isDigit(currentChar))
		microPascalScanner.lexeme="sdf";
		
	}
	public static void isSymbolToken()
	{
		//TODO
	}
}