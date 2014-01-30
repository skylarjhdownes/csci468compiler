package Tokenizer;

public abstract class Tokenizer implements I_Tokenizer {

	protected Token token;
	protected Scanner myScanner;
	
	
	public Tokenizer(Scanner in) {
		myScanner = in;
	}

}
