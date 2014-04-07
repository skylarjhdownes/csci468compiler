package SemanticAnalyzer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

import Tokenizer.Token;
import SymbolTable.*;



/*
 * 
 */
public class SemanticAnalyzer{
	
	private static String FILE_NAME = "Program.il";
	private static String[][] labels = new String[50][3];
	private static String topOfStack = "empty";
	private static String topOfStackBeforeFunctionCall = "empty";
	private static boolean makeNeg = false;
	private static boolean makePos = false;
	private static FileWriter program;
	private static int labelnum = 0;
	
	public SemanticAnalyzer(){
		try {
			File file = new File(FILE_NAME);
			if(!(file.exists())){
				file.createNewFile();
			}
			program = new FileWriter(FILE_NAME);
			program.write("BR L1\n");
		} catch (IOException e) {
			System.out.println("File Program.il doesn't exist, stopping");
			System.exit(1);
		}
		
	}
	
	public void addLabel(SymbolTable symTab){
		labels[labelnum][0] = symTab.getName();
		if(symTab.getParent() != null){
			labels[labelnum][1] = symTab.getParent().findVariable(symTab.getName()).getKind();
		}
		else{
			labels[labelnum][1] = "program";
		}
		labels[labelnum][2] = "L" + (labelnum + 1);
		labelnum++;
	}
	
	public void addLabel(String name, int num){
		labels[labelnum][0] = name + num;
		labels[labelnum][1] = name;
		labels[labelnum][2] = "L" + (labelnum + 1);
		labelnum++;
	}

	public void branch(String name, String when){
		if(when.equals("always")){
			for(int i = labelnum -1; i >= 0; i--){
				if(labels[i][0].equals(name)){
					write("BR " + labels[i][2]);
				}
			}
		}
		
		else if(when.equals("true")){
			if(!(topOfStack.equals("boolean"))){
				error("Branching on term that is not a boolean on " + name + " call" );
			}
			else{
				for(int i = labelnum -1; i >= 0; i--){
					if(labels[i][0].equals(name)){
						write("BRTS " + labels[i][2]);
					}
				}
			}
		}
		else if(when.equals("false")){
			if(!(topOfStack.equals("boolean"))){
				error("Branching on term that is not a boolean on " + name + " call" );
			}
			else{
				for(int i = labelnum -1; i >= 0; i--){
					if(labels[i][0].equals(name)){
						write("BRFS " + labels[i][2]);
					}
				}
			}
		}
		else if(when.equals("equals")){
			for(int i = labelnum -1; i >= 0; i--){
				if(labels[i][0].equals(name)){
					if(topOfStack.equals("integer")||topOfStack.equals("boolean")){
						write("CMPEQS");
					}
					else{
						write("CMPEQSF");
					}
					write("BRTS " + labels[i][2]);
				}
			}
		}
		
		topOfStack = "empty";
	}

	public void placeLabel(String name){
		for(int i = labelnum -1; i >= 0; i--){
			if(labels[i][0].equals(name)){
				write(labels[i][2] + ":");
			}
		}
	}
	
	public void pushCheck(Token variable, SymbolTable symTab){
		Row info = symTab.findVariable(variable.getLexeme());
		
		if (info == null){
			pushLiteral(variable);
		}
		else if(info.getKind().equals("function")){
			//do nothign at the moment
		}
		else{
			pushVariable(variable, symTab);
		}
		
	}
	
	private void pushLiteral(Token variable){
		
		if(topOfStack.equals("empty")){
			if(variable.getToken().equals("MP_STRING_LIT")){
				write("PUSH #\"" + variable.getLexeme().substring(1,variable.getLexeme().length() -1) + "\"");
				topOfStack = "string";
			}
			else{
				write("PUSH #" + variable.getLexeme());
			}
			if(variable.getToken().equals("MP_FIXED_LIT")||variable.getToken().equals("MP_FLOAT_LIT")){
				topOfStack = "float";
			}
			else if(variable.getToken().equals("MP_TRUE")||variable.getToken().equals("MP_FALSE")){
				topOfStack = "boolean";
			}
			else if(variable.getToken().equals("MP_INTEGER_LIT")){
				topOfStack = "integer";
			}
			else{
				topOfStack = "string";
			}
		}
		
		else if(topOfStack.equals("integer")||topOfStack.equals("boolean")){
			if(variable.getToken().equals("MP_FIXED_LIT")||variable.getToken().equals("MP_FLOAT_LIT")){
				write("CASTSF");
				write("PUSH #" + variable.getLexeme());
				topOfStack = "float";
			}
			else if(variable.getToken().equals("MP_TRUE")){
				write("PUSH #1");
				topOfStack = "boolean";
			}
			else if(variable.getToken().equals("MP_FALSE")){
				write("PUSH #0");
				topOfStack = "boolean";
			}
			else if(variable.getToken().equals("MP_INTEGER_LIT")){
				write("PUSH #" + variable.getLexeme());
			}
			else{
				error("Found a "+ variable.getToken()+" called "+variable.getLexeme() + "in expression");
			}
			
			if(makeNeg || makePos){
				write("NEGS");
				makeNeg = false;
				makePos = false;
			}
			
		}
		else if(topOfStack.equals("float")){
			if(variable.getToken().equals("MP_FIXED_LIT")||variable.getToken().equals("MP_FLOAT_LIT")){
				write("PUSH #" + variable.getLexeme());
			}
			else if(variable.getToken().equals("MP_TRUE")){
				write("PUSH #1");
				write("CASTSF");
			}
			else if(variable.getToken().equals("MP_FALSE")){
				write("PUSH #0");
				write("CASTSF");

			}
			else if(variable.getToken().equals("MP_INTEGER_LIT")){
				write("PUSH #" + variable.getLexeme());
				write("CASTSF");
			}
			else{
				error("Found a "+ variable.getToken()+" called "+variable.getLexeme() + "in expression");
			}
			
			if(makeNeg||makePos){
				write("NEGSF");
				makeNeg = false;
				makePos = false;
			}
		}
		
		
	}
	
	public void functionProcedureCall(String paramTypesList, SymbolTable symTab, Token call){
		Row callinfo = symTab.findVariable(call.getLexeme());
		String[] paramsGiven = paramTypesList.split(" ");
		String[] actualParams = callinfo.getInputParameters().split(" ");
		if(paramsGiven.length != actualParams.length){
			error("Wrong number of parameters for call " + call.getLexeme() + " at line:" + call.getLineNumber() + " col:" + call.getColumnNumber());
		}
		
		for(int i = 0; i < paramsGiven.length; i++){
			if(!(paramsGiven[i].equals(actualParams[i]))){
				error("Incorrect parameter type. Looking for "+actualParams[i]+" but found "+paramsGiven[i] + " line:" + call.getLineNumber() + " col:" + call.getColumnNumber());
			}
		}
		
		
		for(int i = labelnum -1; i >= 0; i--){//make the call
			if(labels[i][0].equals(callinfo.getID())){
				write("CALL " + labels[i][2]);
			}
		}
		if(!(actualParams[0].equals(""))) write("SUB SP #" + actualParams.length + " SP");//deconstruct the parameters leaving the return value on top, or no value
		
		if(symTab.findVariable(call.getLexeme()).getKind().equals("function")){
			String type = symTab.findVariable(call.getLexeme()).getType();
			if(topOfStackBeforeFunctionCall.equals("empty")){
				topOfStack = type;
				topOfStackBeforeFunctionCall = "empty";
			}
			else if(topOfStackBeforeFunctionCall.equals("float")){
				if(type.equals("integer")||type.equals("boolean")){
					topOfStack = "float";
					write("CASTSF");
					topOfStackBeforeFunctionCall = "empty";
				}
				else if(type.equals("float")){
					topOfStack = "float";
					topOfStackBeforeFunctionCall = "empty";
				}
				else{
					error("String concatenation and stuff not implemented yet Line:" + call.getLineNumber() + " col:" + call.getColumnNumber());
				}
			}
			else if(topOfStackBeforeFunctionCall.equals("integer")||topOfStackBeforeFunctionCall.equals("boolean")){
				if(type.equals("integer")||type.equals("boolean")){
					topOfStack = type;
					topOfStackBeforeFunctionCall = "empty";
				}
				else if(type.equals("float")){
					topOfStack = "float";
					write("CASTSF");
					topOfStackBeforeFunctionCall = "empty";
				}
				else{
					error("String concatenation and stuff not implemented yet Line:" + call.getLineNumber() + " col:" + call.getColumnNumber());
				}
			}

		}
		
	}
	
	public void comma(){
		topOfStack = "empty";
	}
	
	private void pushVariable(Token variable, SymbolTable symTab){
		
		Row info = symTab.findVariable(variable.getLexeme());
		
		if (topOfStack.equals("empty")){
			write("PUSH " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
			topOfStack =info.getType();
		}
		else if(topOfStack.equals("integer")||topOfStack.equals("boolean")){
			
			if(info.getType().equals("float")){
				write("CASTSF");
				write("PUSH " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
				topOfStack =info.getType();
			}
			else if(info.getType().equals("boolean")||info.getType().equals("integer")){
				write("PUSH " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
				topOfStack = info.getType();
			}
			else{
				error("String found in statement");
			}
			
			if(makeNeg || makePos){
				write("NEGS");
				makeNeg = false;
				makePos = false;
			}
			
		}
		else if(topOfStack.equals("float")){
			if(info.getType().equals("boolean")||info.getType().equals("integer")){
				write("PUSH " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
				write("CASTSF");
			}
			else if(info.getType().equals("float")){
				write("PUSH " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
			}
			else{
				error("String found in statement");
			}
			if(makeNeg || makePos){
				write("NEGSF");
				makeNeg = false;
				makePos = false;
			}
			
		}
	}
	
	public void pushRoomForRetVal(String type){
		
		topOfStackBeforeFunctionCall = topOfStack;
		
		if(type.equals("float")){
			write("PUSH #0.0");
		}
		else if (type.equals("string")){
			write("PUSH #\"\"");
		}
		else{
			write("PUSH #0");
		}
	}
	
	public void pop(Token variable, SymbolTable symTab){
		
		Row info = symTab.findVariable(variable.getLexeme());
		
		if (info == null){
			System.out.println(symTab.getName());
			error("Trying to assign to value that is not a variable: " + variable.getLexeme() + ". Line:"+ variable.getLineNumber() + " Col:" + variable.getColumnNumber());
		}
		else{
			//write("POP " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
			if(topOfStack.equals("integer")||topOfStack.equals("boolean")){
				if(info.getType().equals("float")){
					write("CASTSF");
					write("POP " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
				}
				else if(info.getType().equals("integer")||info.getType().equals("boolean")){
					write("POP " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
				}
				else{
					error("Implicit conversion from " + info.getType() + " to " + topOfStack + " not possible Line:" + variable.getLineNumber() + " col: " + variable.getColumnNumber());
				}
			}
			else if(topOfStack.equals("float")){
				if(info.getType().equals("float")){
					write("POP " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
				}
				else if(info.getType().equals("integer")||info.getType().equals("boolean")){
					write("CASTSI");
					write("POP " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
				}
				else{
					error("Implicit conversion from " + info.getType() + " to " + topOfStack + " not possible Line:" + variable.getLineNumber() + " col: " + variable.getColumnNumber());
				}
			}
			else if(topOfStack.equals("string")){
				if(info.getType().equals("string")){
					write("POP " + info.getOffset() + "(D" + info.getNestingLevel() + ")");
				}
				else{
					error("Implicit conversion from " + info.getType() + " to " + topOfStack + " not possible Line:" + variable.getLineNumber() + " col: " + variable.getColumnNumber());
				}
			}
			topOfStack = "empty";
		}
		
	}
	
	public void stepValue(Token Tokenvalue){
		String value = Tokenvalue.getLexeme();
		if(topOfStack.equals("integer")|| topOfStack.equals("boolean")){
			if(value.equals("to")){
				write("PUSH #1");
			}
			else{
				write("PUSH #-1");
			}
		}
		else{
			if(value.equals("to")){
				write("PUSH #1.0");
			}
			else{
				write("PUSH #-1.0");
			}
		}
	}
	
	public void makeNeg(){
		makeNeg = true;
		
	}
	
	public void makePos(){
		makePos = true;
		
	}
	
	public void addOp(Token operation){
		if(topOfStack.equals("integer")||topOfStack.equals("boolean")){
			if(operation.getLexeme().equals("+")){
				write("ADDS");
			}
			else if(operation.getLexeme().equals("-")){
				write("SUBS");
			}
			else if(operation.getToken().equals("MP_DIV")){
				write("DIVS");
			}
			else if(operation.getLexeme().equals("*")){
				write("MULS");
			}
			else{
				error("Can't use operation " + operation.getLexeme() + " on type " + topOfStack);
			}
		}
		else if(topOfStack.equals("float")){
			if(operation.getLexeme().equals("+")){
				write("ADDSF");
			}
			else if(operation.getLexeme().equals("-")){
				write("SUBSF");
			}
			else if(operation.getLexeme().equals("/")){
				write("DIVSF");
			}
			else if(operation.getToken().equals("MP_DIV")){
				write("DIVSF");
				write("CASTI");
			}
			else if(operation.getLexeme().equals("*")){
				write("MULSF");
			}
			else{
				error("Can't use operation " + operation.getLexeme() + " on type " + topOfStack);
			}
		}
		else{
			error("String operations and concatatenations not implemented at this point Line: " + operation.getLineNumber() + " col:" + operation.getColumnNumber());
		}
		
	}
	
	public void addComp(Token comparator){
		
		if(topOfStack.equals("integer")||topOfStack.equals("boolean")){
			if(comparator.getLexeme().equals(">")){
				write("CMPGTS");
			}
			else if(comparator.getLexeme().equals(">=")){
				write("CMPGES");
			}
			else if(comparator.getLexeme().equals("<")){
				write("CMPLTS");
			}
			else if(comparator.getLexeme().equals("<=")){
				write("CMPLES");
			}
			else if(comparator.getLexeme().equals("=")){
				write("CMPEQS");
			}
			else if(comparator.getLexeme().equals("<>")){
				write("CMPNES");
			}
			topOfStack = "boolean";
		}
		else if(topOfStack.equals("float")){
			if(comparator.getLexeme().equals(">")){
				write("CMPGTSF");
			}
			else if(comparator.getLexeme().equals(">=")){
				write("CMPGESF");
			}
			else if(comparator.getLexeme().equals("<")){
				write("CMPLTSF");
			}
			else if(comparator.getLexeme().equals("<=")){
				write("CMPLESF");
			}
			else if(comparator.getLexeme().equals("=")){
				write("CMPEQSF");
			}
			else if(comparator.getLexeme().equals("<>")){
				write("CMPNESF");
			}
			topOfStack = "boolean";
		}
		else{
			error("Problem adding comparison on line:" + comparator.getLineNumber() + " col:" + comparator.getColumnNumber());
		}
	}
	
	public void read(ArrayList<Token> params,SymbolTable symTab){
		Row current;
		for(int i = 0; i < params.size(); i++){
			current = symTab.findVariable(params.get(i).getLexeme());
			if (current == null){
				error("Can't read into variable " + params.get(i).getLexeme() + " at line:"+ params.get(i).getLineNumber() + " col:" + params.get(i).getColumnNumber());
			}
			else{
				String type = current.getType();
				if(type.equals("integer")||type.equals("boolean")){
					write("RD " + current.getOffset() +"(D" + current.getNestingLevel() + ")");
				}
				else if(type.equals("float")){
					write("RDF " + current.getOffset() + "(D" + current.getNestingLevel() + ")");
				}
				else{
					write("RDS " + current.getOffset() + "(D" + current.getNestingLevel() + ")");
				}
			}
		}
	}
	
	public void write(){
		write("WRTS");
		topOfStack = "empty";
	}
	
	public void writeln(){
		write("WRTLN #\"\"");
		topOfStack = "empty";
	}
	
	public void statementStart(SymbolTable symTab){
		
		int i;
		for(i = labelnum-1; i >= 0; i--){
			if(labels[i][0].equals(symTab.getName())){
				write(labels[i][2] + ":");
				write("PUSH D" + symTab.getNestingLevel());
				write("MOV SP D" + symTab.getNestingLevel());
				write("ADD SP #" + symTab.getSize() + " SP");
				break;
			}
		}
		if(labels[i][1].equals("function")||labels[i][1].equals("procedure")){//grab input variables from under our position
			String[] params = symTab.getParameters().split(" ");
			for(int j = params.length; j > 0; j--){
				write("MOV " + (j - params.length - 3) + "(D" + symTab.getNestingLevel() + ") " + j + "(D" + symTab.getNestingLevel() + ")");
			}
		}
	}
	
	public void statementEnd(SymbolTable symTab){

		if(symTab.getParent() != null && symTab.getParent().findVariable(symTab.getName()).getKind() == "function"){
			String[] params = symTab.getParameters().split(" ");
			write("MOV " + (symTab.findVariable(symTab.getName()).getOffset()) + "(D" + symTab.getNestingLevel() + ") " + (-3 - params.length) + "(D" + symTab.getNestingLevel() + ")" );//store return variable at the very bottom of everything

		}
		
		write("SUB SP #" + symTab.getSize() + " SP");
		write("POP D" + symTab.getNestingLevel());
		for(int i = labelnum - 1; i >= 0; i--){
			if(labels[i][0].equals(symTab.getName())&&!(labels[i][1].equals("program"))){
				write("RET");
			}
		}
		
	}
	
	public  String topOfStackType(){
		return topOfStack;
	}
	
	public void end(){
		try{
			program.write("HLT\n");
			program.close();
		}catch(IOException e){
			System.out.println("Couldn't close the file");
			System.exit(1);
		}
	}
	
	private void write(String command){
		try{
			program.write(command + "\n");
		}catch(IOException e){
			System.out.println("Couldn't write command " + command + " for some reason");
		}
	}
	
	private void error(String msg){
		System.out.println(msg);
		System.exit(1);
	}

}
