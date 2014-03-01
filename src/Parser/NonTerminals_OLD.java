package Parser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import Tokenizer.Token;

public class NonTerminals_OLD 
{
	static String Lookahead;
	static LinkedList<Token> listOfTokens;
	static Iterator<Token> TokenListIterator;
	static int indent = 0;

	public static void parse(LinkedList<Token> list) {
		listOfTokens = list;
		TokenListIterator = listOfTokens.iterator();
		if ( TokenListIterator.hasNext() ) Lookahead = TokenListIterator.next().getToken();

		if ( Lookahead.equals("MP_PROGRAM") ) {
			systemGoal();
		}
		else { System.out.println("PARSE-START ERROR:  "+Lookahead); }
	}

	public static void match(String in) {
		// TODO STUB!!!!!!

		if (in.equals(Lookahead)){
			// Bump to next lookahead
			// and return.

			System.out.printf(""
					+String.format("%1$" + 25 + "s", Lookahead)
					+" =="
					+String.format("%1$" + (indent*3+1) + "s", "")
					+Lookahead
					+"\n");


			if ( TokenListIterator.hasNext() ) Lookahead = TokenListIterator.next().getToken();

		}
		else {
			System.out.println("Match Error> "+Lookahead+" =/= "+in);
			syntaxError();
		}


		return;
	}

	public static void syntaxError() { //int line, int column) {
		// TODO STUB!!!!!!
		System.out.println("Syntax error found on line " + 
				//line + 
				", column" + 
				//column + 
				".");
		return;
	}

	public static void systemGoal() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"SystemGoal"
				+"\n");

		switch (Lookahead)
		{
		case "MP_PROGRAM":
			// Call Rule #2
			program();			
			//EOF thing?
			match("MP_EOF"); // Stephen: Dunno.  maybe this?  There is a token defined for EOF....does our scanner actually detect it? >_>
			break;

		default: // syntaxError
			break;
		}
		indent--;
	}

	public static void program() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Program"
				+"\n");
		switch (Lookahead)
		{
		case "MP_PROGRAM":
			// Call Rule #2

			programHeading();
			match("MP_SCOLON");
			block();
			match("MP_PERIOD");
			break;
		default: // syntaxError
			break;
		}
		indent--;
	}


	public static void programHeading() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"programHeading"
				+"\n");
		switch (Lookahead)
		{
		case "MP_PROGRAM":
			match("MP_PROGRAM");
			programIdentifier();
			break;
		default: // syntaxError
			break;
		}
		indent--;
	}

	public static void block() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Block"
				+"\n");
		switch (Lookahead)
		{
		case "MP_BEGIN":
		case "MP_FUNCTION":
		case "MP_PROCEDURE":
		case "MP_VAR":
			// Rule 4
			variableDeclarationPart();
			procedureAndFunctionDeclarationPart();
			break;
		default: // syntaxError
			break;
		}
		indent--;
	}

	public static void variableDeclarationPart() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableDeclarationPart"
				+"\n");
		switch (Lookahead)
		{
		case "MP_VAR":
			match("MP_VAR");
			variableDeclaration();
			match("MP_SCOLON");
		default: // End thingy?
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void variableDeclarationTail() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableDeclarationTail"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			variableDeclaration();
			match("MP_SCOLON");
			variableDeclarationTail();
			break;
		default: // End thingy?
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void variableDeclaration() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableDeclaration"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			identifierList();
			match("MP_COLON");
			type();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void type()
	{
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Type"
				+"\n");
		switch (Lookahead)
		{
		case "MP_INTEGER":
			match("MP_INTEGER");
			break;
		case "MP_FLOAT":
			match("MP_FLOAT");
			break;
		case "MP_STRING":
			match("MP_STRING");
			break;
		case "MP_BOOLEAN":
			match("MP_BOOLEAN");
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void procedureAndFunctionDeclarationPart() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureAndFunctionDeclarationPart"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			match(Lookahead);
			procedureDeclaration();
			procedureAndFunctionDeclarationPart();
			break;
		case "somethingElse":
			match(Lookahead);
			functionDeclaration();
			procedureAndFunctionDeclarationPart();
			break;
		default: // syntaxError
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void procedureDeclaration() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureDeclaration"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			procedureHeading();
			match("MP_SCOLON");
			block();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void functionDeclaration() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FunctionDeclaration"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			functionHeading();
			match("MP_SCOLON");
			block();
			match("MP_SCOLON");
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void procedureHeading() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureHeading"
				+"\n");
		switch (Lookahead)
		{
		case "MP_PROCEDURE":
			match("MP_PROCEDURE");
			procedureIdentifier();
			optionalFormalParameterList();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void functionHeading() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FunctionHeading"
				+"\n");
		switch (Lookahead)
		{
		case "MP_FUNCTION":
			match("MP_FUNCTION");
			functionIdentifier();
			optionalFormalParameterList();
			type();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void optionalFormalParameterList() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalFormalParameterList"
				+"\n");
		switch (Lookahead)
		{
		case "MP_LPAREN":
			match("MP_LPAREN");
			formalParameterSection();
			formalParameterSectionTail();
			match("MP_RPAREN");
			break;
		default: // syntaxError
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void formalParameterSectionTail() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FormalParameterSectionTail"
				+"\n");
		switch (Lookahead)
		{
		case "MP_SCOLON":
			match("MP_SCOLON");
			formalParameterSection();
			formalParameterSectionTail();
			break;
		default: // syntaxError
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void formalParameterSection() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FormalParameterSection"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			valueParameterSection();
			break;
		case "somethingElse":
			variableParameterSection();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void valueParameterSection() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ValueParameterSection"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			identifierList();
			match("MP_COLON");
			type();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void variableParameterSection() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableParameterSection"
				+"\n");
		switch (Lookahead)
		{
		case "MP_VAR":
			match("MP_VAR");
			identifierList();
			match("MP_COLON");
			type();
			break;

		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void statementPart() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"StatementPart"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			compoundStatement();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void compoundStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"CompoundStatement"
				+"\n");
		switch (Lookahead)
		{
		case "MP_BEGIN":
			match("MP_BEGIN");
			statementSequence();
			match("MP_END");
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void statementSequence() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"StatementSequence"
				+"\n");
		switch (Lookahead)
		{
		case "something":
			statement();
			statementTail();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void statementTail() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"StatementTail"
				+"\n");
		switch (Lookahead)
		{
		case "MP_SCOLON":
			match("MP_SCOLON");
			statement();
			statementTail();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
		indent--;
	}

	public static void statement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Statement"
				+"\n");
		// TODO
		// Fix the default case.  Should not be EmptyString(), suppose a non-empty but not matching 
		// string was encountered next, it should throw an syntaxError, but how to handle EmptyStatement()???
		switch (Lookahead)
		{
		case "MP_Identifier":
			switch (Lookahead) {
			case ":=":
				assignmentStatement();
				break;

			case "(":
				procedureStatement();
				break;
			}
			break;

		case "MP_BEGIN":
			compoundStatement();
			break;

		case "MP_READ":
			readStatement();
			break;

		case "MP_WRITE":
			writeStatement();
			break;

		case "MP_IF":
			ifStatement();
			break;

		case "MP_WHILE":
			whileStatement();
			break;

		case "MP_REPEAT":
			repeatStatement();
			break;

		case "MP_FOR":
			forStatement();
			break;

		default: // syntaxError OR Empty-String
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void emptyStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"EmptyStatement"
				+"\n");
		// TODO Lolwtf is this right?

		switch (Lookahead)
		{
		default: // syntaxError
			break;
		}
		indent--;
	}

	public static void readStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ReadStatement"
				+"\n");
		switch (Lookahead)
		{
		case "read":
			match(Lookahead);
			if (Lookahead.equals("(")) {
				match(Lookahead);
				readParameter();
				readParameterTail();
				if (Lookahead.equals(")")) {
					match(Lookahead);
				}
				else syntaxError();
			}
			else syntaxError();
			break;
		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void readParameterTail() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ReadParameterTail"
				+"\n");
		switch (Lookahead)
		{
		case "MP_COMMA":
			match(Lookahead);
			readParameter();
			readParameterTail();
			break;

		default: // syntaxError OR Empty-String
			break;
		}
		indent--;
	}

	public static void readParameter() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ReadParameter"
				+"\n");
		switch (Lookahead)
		{
		case "MP_Identifier":
			variableIdentifier();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void writeStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"WriteStatement"
				+"\n");
		switch (Lookahead)
		{
		case "MP_WRITE":
		case "MP_WRITELN":
			match(Lookahead);
			if ( Lookahead.equals("(")) {
				match(Lookahead);
				writeParameter();
				writeParameterTail();
				if ( Lookahead.equals(")") ) {
					match(Lookahead);
				}
				else syntaxError();
			}
			else syntaxError();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void writeParameterTail() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"WriteParameterTail"
				+"\n");
		switch (Lookahead)
		{
		case "MP_COMMA":
			match(Lookahead);
			writeParameter();
			writeParameterTail();
			break;
		default: // syntaxError OR Empty-String
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void writeParameter() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"WriteParameter"
				+"\n");
		switch (Lookahead)
		{
		case "+":
		case "-":
		case "UnsignedInteger":
		case "UnsignedFloat":
		case "StringLiteral":
		case "True":
		case "False":
		case "not":
		case "(":
		case "MP_Identifier":
			ordinalExpression();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void assignmentStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"AssignmentStatement"
				+"\n");
		switch (Lookahead)
		{
		case "MP_Identifier":
			// TODO This statement allows a valid identifier of type 'VariableIdentifier' OR 'FunctionIdentifier', but otherwise the syntax is the same.
			// Is there a distinction between these two?
			// For that matter, should there be VariableIdentifier() and FunctionIdentifier() functions?  
			// These Non-terminals evaluate only to Identifiers, but what if they didnt?  Could they?
			match(Lookahead);
			if ( Lookahead.equals(":=") ) {
				match(Lookahead);
				expression();
			}
			else syntaxError();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void ifStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"IfStatement"
				+"\n");
		switch (Lookahead)
		{
		case "if":
			match(Lookahead);
			booleanExpression();
			if ( Lookahead.equals("then") ) {
				match(Lookahead);
				statement();
				optionalElsePart();
			}
			else syntaxError();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void optionalElsePart() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalElsePart"
				+"\n");
		switch (Lookahead)
		{
		case "else":
			match(Lookahead);
			statement();
			break;

		default: // syntaxError OR Empty-String
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void repeatStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"RepeatStatement"
				+"\n");
		switch (Lookahead)
		{
		case "repeat":
			match(Lookahead);
			statementSequence();
			if ( Lookahead.equals("until") ) {
				match(Lookahead);
				booleanExpression();
			}
			else syntaxError();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void whileStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"WhileStatement"
				+"\n");
		switch (Lookahead)
		{
		case "while":
			match(Lookahead);
			booleanExpression();
			if ( Lookahead.equals("do") ) {
				match(Lookahead);
				statement();
			}
			else syntaxError();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void forStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ForStatement"
				+"\n");
		switch (Lookahead)
		{
		case "for":
			match(Lookahead);
			controlVariable();
			if ( Lookahead.equals(":=") ) {
				match(Lookahead);
				initialValue();
				stepValue();
				finalValue();
				if ( Lookahead.equals("do") ) {
					match(Lookahead);
					statement();
				}
				else syntaxError();
			}
			else syntaxError();			
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void controlVariable() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ControlVariable"
				+"\n");
		switch (Lookahead)
		{
		case "MP_Identifier":
			variableIdentifier();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void initialValue() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"InitialValue"
				+"\n");
		switch (Lookahead)
		{
		case "MP_PLUS":
		case "MP_MINUS":
		case "UnsignedInteger":
		case "UnsignedFloat":
		case "StringLiteral":
		case "MP_TRUE":
		case "MP_FALSE":
		case "MP_NOT":
		case "MP_LPAREN":
		case "MP_IDENTIFIER":
			ordinalExpression();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void stepValue() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"StepValue"
				+"\n");
		switch (Lookahead)
		{
		case "to":
		case "downto":
			match(Lookahead);
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void finalValue() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FinalValue"
				+"\n");
		switch (Lookahead)
		{
		case "MP_PLUS":
		case "MP_MINUS":
		case "UnsignedInteger":
		case "UnsignedFloat":
		case "StringLiteral":
		case "MP_TRUE":
		case "MP_FALSE":
		case "MP_NOT":
		case "MP_LPAREN":
		case "MP_IDENTIFIER":
			ordinalExpression();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void procedureStatement() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureStatement"
				+"\n");
		switch (Lookahead)
		{
		case "MP_Identifier":
			procedureIdentifier(); 
			optionalActualParameterList();
			break;

		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
		indent--;
	}

	public static void optionalActualParameterList() {
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalActualParameterList"
				+"\n");
		switch (Lookahead)
		{
		case "MP_LPAREN":
			match(Lookahead);
			actualParameter();
			actualParameterTail();
			if ( Lookahead.equals(")") ) {
				match(Lookahead);
			}
			else syntaxError();
			break;

		default: // syntaxError OR Empty-String
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void actualParameterTail(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ActualParameterTail"
				+"\n");
		switch (Lookahead){
		case "MP_COMMA":
			actualParameter();
			actualParameterTail();
			break;
		default:
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void actualParameter(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ActualParameter"
				+"\n");
		switch(Lookahead){
		case "something": 
			ordinalExpression();
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void expression(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Expression"
				+"\n");
		switch(Lookahead){
		case "something":
			simpleExpression();
			optionalRelationalPart();
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void optionalRelationalPart(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalRelationalPart"
				+"\n");
		switch(Lookahead){
		case "something":
			relationalOperator();
			simpleExpression();
			break;
		default:
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void relationalOperator(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"RelationalOperator"
				+"\n");
		switch(Lookahead){
		case "MP_EQUAL":
			match("MP_EQUAL");
			break;
		case "MP_LTHAN":
			match("MP_LTHAN");
			break;
		case "MP_GTHAN":
			match("MP_GTHAN");
			break;
		case "MP_GQUAL":
			match("MP_GQUAL");
			break;
		case "MP_LQUAL":
			match("MP_LQUAL");
			break;
		case "MP_NQUAL":
			match("MP_NQUAL");
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void simpleExpression(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"SimpleExpression"
				+"\n");
		switch(Lookahead){
		case "something":
			optionalSign();
			term();
			termTail();
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void termTail(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"TermTail"
				+"\n");
		switch(Lookahead){
		case "something":
			addingOperator();
			term();
			termTail();
			break;
		default:
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void optionalSign(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalSign"
				+"\n");
		switch(Lookahead){
		case "MP_PLUS":
			match("MP_PLUS");
			break;
		case "MP_MINUS":
			match("MP_MINUS");
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void addingOperator(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"AddingOperator"
				+"\n");
		switch(Lookahead){
		case "MP_PLUS":
			match("MP_PLUS");
			break;
		case "MP_MINUS":
			match("MP_MINUS");
			break;
		case "MP_OR":
			match("MP_OR");
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void term(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Term"
				+"\n");
		switch(Lookahead){
		case "something":
			factor();
			factorTail();
			break;
		default:
			syntaxError();
		}
		indent--;
	}

	public static void factorTail(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FactorTail"
				+"\n");
		switch(Lookahead){
		case "something":
			multiplyingOperator();
			factor();
			factorTail();
			break;
		default:
			emptyStatement();
			break;
		}
		indent--;
	}

	public static void multiplyingOperator(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"MultiplyingOperator"
				+"\n");
		switch(Lookahead){
		case "*":
			match("MP_MULT");
			break;
		case "/":
			match("MP_FLOAT_DIV");
			break;
		case "div":
			match("MP_INT_DIV");
			break;
		case "and":
			match("MP_AND");
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void factor(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Factor"
				+"\n");
		switch(Lookahead){
		case "MP_INT":
			match("MP_INT");
			break;
		case "MP_FLOAT":
			match("MP_FLOAT");
			break;
		case "MP_STRING_LIT":
			match("MP_STRING_LIT");
			break;
		case "MP_TRUE":
			match("MP_TRUE");
			break;
		case "MP_FALSE":
			match("MP_FALSE");
			break;
		case "MP_NOT":
			match("MP_NOT");
			factor();
			break;
		case "MP_LPAREN":
			match("(");
			expression();
			match(")");
			break;
		case "other":
			functionIdentifier();
			optionalActualParameterList();
			break;    
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void programIdentifier(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"programIdentifier"
				+"\n");
		switch(Lookahead){
		case "MP_IDENTIFIER":
			match("MP_IDENTIFIER");
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void variableIdentifier(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableIdentifier"
				+"\n");
		switch(Lookahead){
		case "MP_ID":
			match("MP_ID");
			break;
		default:
			syntaxError();
			break;            
		}
		indent--;
	}   

	public static void procedureIdentifier(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureIdentifier"
				+"\n");
		switch(Lookahead){
		case "MP_ID":
			match("MP_ID");
			break;
		default:
			syntaxError();
			break;            
		}
		indent--;
	}

	public static void functionIdentifier(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FunctionIdentifier"
				+"\n");
		switch(Lookahead){
		case "MP_ID":
			match("MP_ID");
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void booleanExpression(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"BooleanExpression"
				+"\n");
		switch(Lookahead){
		case "something":
			expression();
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void ordinalExpression(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OrdinalExpression"
				+"\n");
		switch(Lookahead){
		case "something":
			expression();
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void identifierList(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"IdentifierList"
				+"\n");
		switch(Lookahead){
		case "something":
			match("MP_ID");
			identifierTail();
			break;
		default:
			syntaxError();
			break;
		}
		indent--;
	}

	public static void identifierTail(){
		System.out.printf(""
				+String.format("%1$" + 25 + "s", Lookahead)
				+" ->"
				+String.format("%1$" + (indent++*3+1) + "s", "")
				+"IdentifierTail"
				+"\n");
		switch(Lookahead){
		case "something":
			match("MP_COMMA");
			match("MP_ID");
			identifierTail();
			break;
		default:
			emptyStatement();
			break;
		}
		indent--;
	}
}
