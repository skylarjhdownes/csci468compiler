package Tokenizer;

public class FSA_Literal extends Tokenizer {
	

	/* INHERITED VARIABLES FROM TOKENIZER
	private Token token;
	private Scanner myScanner;
	*/
	
	public FSA_Literal(MicroPascalScanner in) {
		super(in);
	}
	
	public enum State {
		firstDigit,
		secondAndOnDigit,
		justPassedDecimal,
		fixedOrFloat,
		justPassedExponent,
		justPassedPlusOrMinus,
		finalDigits,
		
		RETURN,
		ERROR
	}
	
	public Token getToken()
	{
		String lexeme = "";
		String lastAcceptableLexeme = "";
		String token = "Not yet determined";
		String errorMsg = "";
		State t = State.firstDigit;
		int lineNum = myScanner.getLineNum();
		int colNum = myScanner.getColNum();
		
		char nextChar = '0';
		if ( myScanner.hasNextToken() ) nextChar = myScanner.getNextChar();
		else t = State.RETURN;
		
		while(true)
		{
			switch(t)
			{
			case firstDigit:
				
				if (Character.isDigit(nextChar)) 
				{	
					//If the next char is a digit, record it and change to secondAndOnDigit.
					lexeme = lexeme + nextChar;
					t = State.secondAndOnDigit;
					break;
				}
				else 
				{
					t = State.RETURN;
					// Invalid character
					break;
				}
			case secondAndOnDigit:
				// This state is a valid accept state;
				lastAcceptableLexeme = lexeme;
				
				if (Character.isDigit(nextChar)) 
				{	//If the next char is a digit, record it and stay in this state.
					lexeme = lexeme + nextChar;
					break;
				}
				else if (nextChar == '.')
				{
					//If the next char is a decimal point/period record it and change to justPassedDecimal.
					lexeme = lexeme + nextChar;
					t = State.justPassedDecimal;
					break;
				}
				else if (nextChar == 'e' || nextChar == 'E')
				{
					//If the next char is an e|E, record it and change to justPassedExponent.
					lexeme = lexeme + nextChar;
					t = State.justPassedExponent;
					break;
				}
				else 
				{
					t = State.RETURN;
					// Invalid character
					break;
				}

			case justPassedDecimal:
				
				if (Character.isDigit(nextChar)) 
				{	
					//If the next char is a digit, record it and change to fixedOrFloat.
					lexeme = lexeme + nextChar;
					t = State.secondAndOnDigit;
					break;
				}
				else 
				{
					t = State.RETURN;
					// Invalid character
					break;					
				}
				
			case fixedOrFloat:
				// This state is a valid accept state;
				lastAcceptableLexeme = lexeme;
				
				if (Character.isDigit(nextChar)) 
				{	
					//If the next char is a digit, record it and stay in this state.
					lexeme = lexeme + nextChar;
					break;
				}
				else if (nextChar == 'e' || nextChar == 'E')
				{
					//If the next char is an e|E, record it and change to justPassedExponent.
					lexeme = lexeme + nextChar;
					t = State.justPassedExponent;
					break;
				}
				else 
				{
					t = State.RETURN;
					// Invalid character
					break;
				}
			case justPassedExponent:
				if (Character.isDigit(nextChar)) 
				{	
					//If the next char is a digit, record it and stay change to finalDigits.
					lexeme = lexeme + nextChar;
					t = State.finalDigits;
					break;
				}
				else if (nextChar == '+'|| nextChar == '-')
				{
					//If the next char is a +|-, record it and change to justPassedPlusOrMinus.
					lexeme = lexeme + nextChar;
					t = State.justPassedPlusOrMinus;
					break;
				}
				else 
				{
					t = State.RETURN;
					// Invalid character
					break;
				}
			case justPassedPlusOrMinus:
				if (Character.isDigit(nextChar)) 
				{	
					//If the next char is a digit, record it and stay change to finalDigits.
					lexeme = lexeme + nextChar;
					t = State.finalDigits;
					break;
				}
				else 
				{
					t = State.RETURN;
					// Invalid character
					break;
				}
			case finalDigits:
				// This state is a valid accept state;
				lastAcceptableLexeme = lexeme;
				
				if (Character.isDigit(nextChar)) 
				{	//If the next char is a digit, record it and stay in this state.
					lexeme = lexeme + nextChar;
					break;
				}
				else 
				{
					t = State.RETURN;
					// Invalid character
					break;
				}
			}
			
			if ( t == State.ERROR ) 
			{
				myScanner.throwError(errorMsg);
				t = State.RETURN;
			}

			if ( t == State.RETURN ) 
			{
				return new Token(lastAcceptableLexeme,token,lineNum,colNum);
			}
			
			if ( myScanner.hasNextToken() ) nextChar = myScanner.getNextChar();
			else t = State.RETURN;
		}
	}
}
