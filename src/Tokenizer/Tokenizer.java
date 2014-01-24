package Tokenizer;

public abstract class Tokenizer implements I_Tokenizer {

	String token;
	String type;
	int startAt;
	Scanner myScanner;
	
	
	public Tokenizer(Scanner in) {
		myScanner = in;
	}

	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLexeme() {
		return token;
	}

	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return 0;
	}

}
