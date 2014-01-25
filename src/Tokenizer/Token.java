package Tokenizer;

public class Token {

	private String lexeme;
	private String token;
	private int lineNum;
	private int colNum;
	
	public Token() { }
	public Token(String l, String t) {
		lexeme = l;
		token = t;
	}
	public Token(String l, String t,int lN, int cN) {
		lexeme = l;
		token = t;
		lineNum = lN;
		colNum = cN;
	}
	
	public void setLexeme(String in) { lexeme = in; }
	public void setToken(String in) { token = in; }
	public void setLineNumber(int in) { lineNum = in; }
	public void setColumnNumber(int in) { colNum = in; }

	public String getLexeme() { return lexeme; }
	public String getToken() { return token; }
	public int getLineNumber() { return lineNum; }
	public int getColumnNumber() { return colNum; }

	

}
