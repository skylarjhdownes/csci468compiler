package Tokenizer;

public abstract class Tokenizer implements I_Tokenizer {

	private Token token;
	private Scanner myScanner;
	
	
	public Tokenizer(Scanner in) {
		myScanner = in;
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

}
