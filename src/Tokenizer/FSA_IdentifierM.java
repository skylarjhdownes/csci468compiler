package Tokenizer;

public class FSA_IdentifierM extends Tokenizer {
	
    		/* INHERITED VARIABLES FROM TOKENIZER
	private Token token;
	private Scanner myScanner;
     		*/
	
	

	
	public FSA_IdentifierM(MicroPascalScanner in) {
		super(in);
	}

	
	String lexeme = "";
	String lastAcceptableLexeme = "";
	String token = "Identifier";
	String errorMsg = "";
	int lineNum = myScanner.getLineNum();
	int colNum = myScanner.getColNum();
	char nextChar = '0';
	@Override
	public Token getToken() {
		
		return state_firstChar();
	}
	
	private Token state_firstChar() {
		// Get the next char
		if ( myScanner.hasNextToken() ) nextChar = myScanner.getNextChar();
		else return state_RETURN();

		// In this state, i am only able to accept a LETTER or an UNDERSCORE
		if ( Character.isLetter(nextChar) ) {
			// If the next character is a letter, record it and change states.
			lexeme = lexeme + nextChar;
			return state_nextChar();
		}
		else if ( nextChar == '_' ) {
			// If the next character is a letter, record it and change states.
			lexeme = lexeme + nextChar;					
			return state_nonUnderscore();
		}
		else {
			return state_RETURN();
		}
	}

	private Token state_RETURN() {
		return new Token(lastAcceptableLexeme,token,lineNum,colNum);
	}

	private Token state_nonUnderscore() {
		// Get the next char
		if ( myScanner.hasNextToken() ) nextChar = myScanner.getNextChar();
		else return state_RETURN();

		// In this state, i am able to accept a LETTER or DIGIT
		if ( Character.isLetter(nextChar) ) {
			// If the next character is a letter, record it and change states.
			lexeme = lexeme + nextChar;
			return state_nextChar();
		}
		else if ( Character.isDigit(nextChar) ) {
			// If the next character is a letter, record it and change states.
			lexeme = lexeme + nextChar;
			return state_nextChar();
		}
		else if ( nextChar == '_' ) {
			// ERROR, not an acceptable character.
			errorMsg = "Underscores cannot follow an underscore in an Identifier.";
			return state_ERROR();
		}
		else {
			return state_RETURN();
			// Invalid character
		}
	}

	private Token state_ERROR() {
		myScanner.throwError(errorMsg);
		return state_RETURN();
	}

	private Token state_nextChar() {
		// This state is a valid accept state;
		lastAcceptableLexeme = lexeme;
		
		// Get the next char
		if ( myScanner.hasNextToken() ) nextChar = myScanner.getNextChar();
		else return state_RETURN();

		// In this state, i am able to accept a LETTER, DIGIT or an UNDERSCORE
		if ( Character.isLetter(nextChar) ) {
			// If the next character is a letter, record it and change states.
			lexeme = lexeme + nextChar;
			return state_nextChar();
		}
		else if ( Character.isDigit(nextChar) ) {
			// If the next character is a letter, record it and change states.
			lexeme = lexeme + nextChar;
			return state_nextChar();
		}
		else if ( nextChar == '_' ) {
			// If the next character is a letter, record it and change states.
			lexeme = lexeme + nextChar;					
			return state_nonUnderscore();
		}
		else {
			return state_RETURN();
			// Invalid character
		}
	}
}
