package csci468compiler;

public class FSA_Literal extends Tokenizer {
	

	/* INHERITED VARIABLES FROM TOKENIZER
	private Token token;
	private Scanner myScanner;
	*/
	
	public FSA_Identifier(MicroPascalScanner in) {
		super(in);
	}
	
	public enum State {
		firstChar,
		nextChar,
		nonUnderscore,
		
		RETURN,
		ERROR
	}
	
	public Token getToken()
//	markReturnSpot();
//	Token returnToken;
//	char currentChar = 'd';
//	if (Character.isDigit(currentChar))
//	{
//		lexeme.concat(Character.toString(currentChar)); 
//		returnToken = Token.MP_INTEGER_LIT; 			
//	}
//	else
//	{
//		try
//		{
//		fileReader.reset();	
//		} catch (IOException e) 
//		{
//			System.out.print("The fileReader is confused about jumping back to the mark.");
//		}
//		nextCharacter();
//		return Token.MP_ERROR;
//	}
//	nextCharacter();
//	while (Character.isDigit(currentChar))
//	{
//		lexeme.concat(Character.toString(currentChar));
//		nextCharacter();
//	}
//	if (currentChar == '.')
//	{
//		
//		nextCharacter();
//		if (Character.isDigit(currentChar))
//		{
//			lexeme.concat("."); 
//			lexeme.concat(Character.toString(currentChar)); 
//			returnToken = Token.MP_INTEGER_LIT; 			
//		}
//		else
//		{
//			//Stuff and things.  Sleep time now.
//		}
//	}
//	
//	return returnToken;
}
