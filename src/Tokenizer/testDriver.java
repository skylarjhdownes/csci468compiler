package Tokenizer;

import java.io.File;

public class testDriver {

	public static void main(String[] args) {
		File testFile;
		
		testFile = new File("C:\\Users\\Stephen Bush\\Documents\\Test.txt");
		
		Scanner sc = new Scanner(testFile);
		
		Token current;
		while ( sc.hasNextToken() ) {
			current = sc.getToken();
			System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
		}
	}

}
