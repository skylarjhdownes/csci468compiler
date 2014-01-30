
import java.util.Scanner;

/**
 *
 * @author Jon Koenes
 */
public class StrSymFSA {

    public static Scanner in = new Scanner(System.in);

    public static Token findToken(int line, int col) {
        Token retTok = new Token();
        String lex = "";

        String nextLine = in.nextLine();
        char nextChar = nextLine.charAt(col);

        boolean foundToken = false;

        while (!foundToken) {
            switch (nextChar) {
                case ':':
                    lex += nextChar;
                    col++;
                    if (nextLine.charAt(col + 1) == '=') {
                        lex += nextLine.charAt(col + 1);
                        col++;
                        retTok = new Token(lex, "MP_ASSIGN", line, col);
                    } else {
                        retTok = new Token(lex, "MP_COLON", line, col);
                    }
                    foundToken = true;
                    break;
                
                
                case ',':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_COMMA", line, col);
                    foundToken = true;
                    break;
                
                
                case '=':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_EQUAL", line, col);
                    foundToken = true;
                    break;
                
                
                case '/':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_FLOAT_DIVIDE", line, col);
                    foundToken = true;
                    break;
                
                
                case '>':
                    lex += nextChar;
                    col++;
                    if (nextLine.charAt(col + 1) == '=') {
                        lex += nextLine.charAt(col + 1);
                        col++;
                        retTok = new Token(lex, "MP_GEQUAL", line, col);
                    } else {
                        retTok = new Token(lex, "MP_GTHAN", line, col);
                    }
                    foundToken = true;
                    break;
                
                
                case '<':
                    lex += nextChar;
                    col++;
                    if (nextLine.charAt(col + 1) == '=') {
                        lex += nextLine.charAt(col + 1);
                        col++;
                        retTok = new Token(lex, "MP_LEQUAL", line, col);
                    } else if (nextLine.charAt(col + 1) == '>') {
                        lex += nextLine.charAt(col + 1);
                        col++;
                        retTok = new Token(lex, "MP_NEQUAL", line, col);
                    } else {
                        retTok = new Token(lex, "MP_LTHAN", line, col);
                    }
                    foundToken = true;
                    break;
                
                
                case '.':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_PERIOD", line, col);
                    foundToken = true;
                    break;
                
                
                case '+':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_PLUS", line, col);
                    foundToken = true;
                    break;
                
                
                case '(':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_LPAREN", line, col);
                    foundToken = true;
                    break;
                
                
                case ')':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_RPAREN", line, col);
                    foundToken = true;
                    break;
                
                
                case ';':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_SCOLON", line, col);
                    foundToken = true;
                    break;
                
                
                case '*':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_TIMES", line, col);
                    foundToken = true;
                    break;
                
                
                case'"':
                    lex+= nextChar;
                    col++;
                    try{
                        while(nextLine.charAt(col) != '"'){
                            if(nextLine.charAt(col) == '\''){//contains apostrophe
                                throw new ApoExc();
                            }
                            lex+=nextLine.charAt(col);
                            col++;
                        }
                        lex+= nextLine.charAt(col);
                        col++;
                        retTok = new Token(lex, "MP_STRING_LIT", line, col);
                        foundToken = true;
                    }
                    catch(IndexOutOfBoundsException e){//if hit end of line while looking for string
                        retTok = new Token("", "MP_RUN_STRING", line, col);
                        foundToken = true;
                    }
                    catch(ApoExc e){
                        retTok = new Token("", "MP_ERROR_APOSTROPHE_IN_STRING", line, col);
                        foundToken = true;
                    }
                    break;
                
                default:
                    System.out.println("StrSymFSA asked to scan for item that was not string or symbol");
                    foundToken = true;
                    retTok = new Token("","MP_ERROR_GOVT_TAKING_JOBS", line, col);
            }//end switch
        }//end while
        return retTok;
    }
}

class ApoExc extends Exception{
    public ApoExc(){}
    public ApoExc(String mes){
        super(mes);
    }
    
}
