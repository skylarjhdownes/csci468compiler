package Tokenizer;

import java.io.File;


public class Scanner implements I_Tokenizer {
	
	private File input;
	

	public Scanner(File in) {
		input = in;
	}

	@Override
	public Token getToken() {
		return null;
	}
	public char getNextChar() {
		
		
		return '\u0000';
	}
}
