package Tokenizer;

public class FSA_Identifier extends Tokenizer {
	
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
	@Override
	public Token getToken() {
		
		String lexeme = "";
		String lastAcceptableLexeme = "";
		String token = "Identifier";
		String errorMsg = "";
		State t = State.firstChar;
		int lineNum = myScanner.getLineNum();
		int colNum = myScanner.getColNum();

		
		char nextChar = '0';
		if ( myScanner.hasNextToken() ) nextChar = myScanner.getNextChar();
		else t = State.RETURN;
		
		while (true) {
			// Process this character depending on what state i'm in
			switch(t) {
			case firstChar:
				// In this state, i am only able to accept a LETTER or an UNDERSCORE
				if ( Character.isLetter(nextChar) ) {
					// If the next character is a letter, record it and change states.
					lexeme = lexeme + nextChar;
					t = State.nextChar;
					break;
				}
				else if ( nextChar == '_' ) {
					// If the next character is a letter, record it and change states.
					lexeme = lexeme + nextChar;					
					t = State.nonUnderscore;
					break;
				}
				else {
					t = State.RETURN;
					// Invalid character
					break;
				}
				
			case nextChar:
				// This state is a valid accept state;
				lastAcceptableLexeme = lexeme;
				
				// In this state, i am able to accept a LETTER, DIGIT or an UNDERSCORE
				if ( Character.isLetter(nextChar) ) {
					// If the next character is a letter, record it and change states.
					lexeme = lexeme + nextChar;
					t = State.nextChar;
					break;
				}
				else if ( Character.isDigit(nextChar) ) {
					// If the next character is a letter, record it and change states.
					lexeme = lexeme + nextChar;
					t = State.nextChar;
					break;
				}
				else if ( nextChar == '_' ) {
					// If the next character is a letter, record it and change states.
					lexeme = lexeme + nextChar;					
					t = State.nonUnderscore;
					break;
				}
				else {
					t = State.RETURN;
					// Invalid character
					break;
				}
				
			case nonUnderscore:
				// In this state, i am able to accept a LETTER or DIGIT
				if ( Character.isLetter(nextChar) ) {
					// If the next character is a letter, record it and change states.
					lexeme = lexeme + nextChar;
					t = State.nextChar;
					break;
				}
				else if ( Character.isDigit(nextChar) ) {
					// If the next character is a letter, record it and change states.
					lexeme = lexeme + nextChar;
					t = State.nextChar;
					break;
				}
				else if ( nextChar == '_' ) {
					// ERROR, not an acceptable character.
					errorMsg = "Underscores cannot follow an underscore in an Identifier.";
					t = State.ERROR;
					break;
				}
				else {
					t = State.RETURN;
					// Invalid character
					break;
				}
				
			default:
				break;
				
			}
			
			if ( t == State.ERROR ) {
				myScanner.throwError(errorMsg);
				t = State.RETURN;
			}

			if ( t == State.RETURN ) {
				return new Token(lastAcceptableLexeme,token,lineNum,colNum);
			}

			// Get the next char
			if ( myScanner.hasNextToken() ) nextChar = myScanner.getNextChar();
			else t = State.RETURN;
			
			//System.out.println("Processing "+nextChar+", Entering state  "+t);
		}
		// Something went wrong, unable to parse a token.
		//return null;
	}
	
	

}
