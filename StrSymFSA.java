
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
                    break;
                
                
                case ',':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_COMMA", line, col);
                    break;
                
                
                case '=':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_EQUAL", line, col);
                    break;
                
                
                case '/':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_FLOAT_DIVIDE", line, col);
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
                    break;
                
                
                case '.':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_PERIOD", line, col);
                    break;
                
                
                case '+':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_PLUS", line, col);
                    break;
                
                
                case '(':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_LPAREN", line, col);
                    break;
                
                
                case ')':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_RPAREN", line, col);
                    break;
                
                
                case ';':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_SCOLON", line, col);
                    break;
                
                
                case '*':
                    lex += nextChar;
                    col++;
                    retTok = new Token(lex, "MP_TIMES", line, col);
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
                    }
                    catch(IndexOutOfBoundsException e){//if hit end of line while looking for string
                        retTok = new Token("", "MP_RUN_STRING", line, col);
                    }
                    catch(ApoExc e){
                        retTok = new Token("", "MP_ERROR_APOSTROPHE_IN_STRING", line, col);
                    }
                    break;
                
                default:
                    System.out.println("StrSymFSA asked to scan for item that was not string or symbol");
                    retTok = new Token("","MP_ERROR_GOVT_TAKING_JOBS", line, col);
            }//end switch
        return retTok;
    }
}

class ApoExc extends Exception{
    public ApoExc(){}
    public ApoExc(String mes){
        super(mes);
    }
    
}
