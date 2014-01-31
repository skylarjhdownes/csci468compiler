package Tokenizer;

/**
 *
 * @author Jon Koenes
 */
public class Token {
    
    protected String lexeme;
    protected String token;
    protected int lineNum;
    protected int colNum;
    
    public Token(String in_lex, String in_token, int lnum, int colnum){
        lexeme = in_lex;
        token = in_token;
        lineNum = lnum;
        colNum = colnum;
    }
    
    public Token(){
        lexeme = "";
        token = "EMPTY_TOKEN";
        lineNum = 0;
        colNum = 0;
    }
    
    public String getLex(){
        return lexeme;
    }
    
    public String getTok(){
        return token;
    }
    
    public int getLine(){
        return lineNum;
    }
    
    public int getCol(){
        return colNum;
    }
}
