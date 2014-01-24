package Tokenizer;

import java.io.File;


public class Scanner implements I_Tokenizer {
	
	File input;
	

	public Scanner(File in) {
		input = in;
	}

	@Override
	public String getToken() {
		return null;
	}

	@Override
	public int getLine() {
		return 0;
	}

	@Override
	public String getLexeme() {
		return null;
	}

	@Override
	public int getColumn() {
		return 0;
	}

	public char getNextChar() {
		
		
		return '\u0000';
	}
}
