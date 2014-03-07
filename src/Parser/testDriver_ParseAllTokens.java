package Parser;

import java.io.File;
import java.util.LinkedList;

import Tokenizer.MicroPascalScanner;
import Tokenizer.Token;

public class testDriver_ParseAllTokens {

	public static void main(String[] args) {
		File testFile;

		if( args.length == 0 ) 
		{
			System.out.println("No input file given, ALL IS LOST. ABANDON SHIP!!!!!!!!!!!!!!!!");
			//testFile = new File("C:\\Users\\Stephen Bush\\Documents\\Test.txt");
			//testFile = new File("C:\\Users\\Stephen Bush\\Documents\\Test_Program.txt");
			//testFile = new File("C:\\Users\\Stephen Bush\\workspace\\HashList\\src\\hashList\\HashList.java");
			//testFile = new File("TestPrograms/Test_Program_1.txt");
			//testFile = new File("TestPrograms/Gen_Prog_7.txt");
			//testFile = new File("TestPrograms/Gen_Prog_5.txt");

			testFile = new File("TestPrograms/MicroPascalProgramGenerator/Share/TestProgram4.txt");
}
		else
		{
			testFile = new File(args[0]);
		}
		
		MicroPascalScanner sc = new MicroPascalScanner(testFile);
		
		//Token current;
		//while ( sc.hasNextToken() ) 
		//{
		//	current = sc.getToken();
		//	System.out.println("Token>>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
		//}
		
		LinkedList<Token> list = new LinkedList<Token>();
		list = sc.getAllTokens();
		
		System.out.println("Token List:");
		for ( Token current : list ) {
			System.out.println("  >>  "+"Ln:   "+current.getLineNumber()+"\tCl: "+current.getColumnNumber()+"\t\tType:  "+current.getToken()+"\t\tLex:  "+current.getLexeme());
		}
		
		System.out.println("Parsing...");
		NonTerminals.start(list);
		
	}
}
