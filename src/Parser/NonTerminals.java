package Parser;

import java.util.LinkedList;

import Tokenizer.Token;

public class NonTerminals {

    static int tokindex;
    static LinkedList<Token> tokens;
    static Token LATok;
    static String Lookahead;
	
    // Variables for the parse-tree print
    static int indent = 0;
	static final boolean PRINT_PARSE_TREE = true;
	static final int LAPad = 25;


    public static void match(String in) {
		// TODO STUB!!!!!!

        if (in.equals(Lookahead)) {
            
    		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" =="+String.format("%1$" + (indent*3+1) + "s", "")
    				+Lookahead+"\n");
        	
        	if ( tokindex < tokens.size() ) {
        		LATok = tokens.get(tokindex++);
                Lookahead = LATok.getToken();
        	}
        	else { 
        		// **Stephen:  Modified this so it doesnt throw an out-of-bounds exception.  Also, added an else statement to insert the EOF token.  Dont know if this should be the final solution to the EOF problem, but meh.  WORKAROUND!
        		Lookahead = "MP_EOF";
        	}
            
            
        } else {
			System.out.println("Match Error>\n  Expected: "+in+"  =<>=  "+Lookahead);

            syntaxError();
        }

        return;
    }

    public static void syntaxError() { //int line, int column) {
        // TODO STUB!!!!!!
        System.out.println("Syntax error found on line " + LATok.getLineNumber() + ", column" + LATok.getColumnNumber() + ".");
        return;
    }

    public static void start(LinkedList<Token> list) {
        tokens = list;
        tokindex = 1;
        LATok = tokens.getFirst();
        Lookahead = LATok.getToken();
        systemGoal();
    }

    public static void systemGoal() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"SystemGoal"+"\n");
        switch (Lookahead) {
            case "MP_PROGRAM":
                program();
                match("MP_EOF");
                break;

            default: // syntaxError
                syntaxError();
                break;
        }
        indent--;
    }

    public static void program() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Program"+"\n");
        switch (Lookahead) {
            case "MP_PROGRAM":
                programHeading();
                match("MP_SCOLON");
                block();
                match("MP_PERIOD");
                break;
            default:
                syntaxError();// syntaxError
                break;
        }
        indent--;
    }

    public static void programHeading() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProgramHeading"+"\n");
        switch (Lookahead) {
            case "MP_PROGRAM":
                match("MP_PROGRAM");
                programIdentifier();
                break;
            default:
                syntaxError();// syntaxError
                break;
        }
        indent--;
    }

    public static void block() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Block"+"\n");
        switch (Lookahead) {
            case "MP_BEGIN":
            case "MP_FUNCTION":
            case "MP_PROCEDURE":
            case "MP_VAR":
                variableDeclarationPart();
                procedureAndFunctionDeclarationPart();
                statementPart();
                break;
            default:
                syntaxError();// syntaxError
                break;
        }
        indent--;
    }

    public static void variableDeclarationPart() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableDeclarationPart"+"\n");
        switch (Lookahead) {
            case "MP_BEGIN":
            case "MP_FUNCTION":
            case "MP_PROCEDURE":
                break;
            case "MP_VAR":
                match("MP_VAR");
                variableDeclaration();
                match("MP_SCOLON");
                variableDeclarationTail();
                break;
            default: // End thingy?
                syntaxError();
                break;
        }
        indent--;
    }

    public static void variableDeclarationTail() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableDeclarationTail"+"\n");
        switch (Lookahead) {
            case "MP_BEGIN":
            case "MP_FUNCTION":
            case "MP_PROCEDURE":
                break;
            case "MP_IDENTIFIER":
                variableDeclaration();
                match("MP_SCOLON");
                variableDeclarationTail();
                break;
            default: // End thingy?
                syntaxError();
                break;
        }
        indent--;
    }

    public static void variableDeclaration() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableDelcaration"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
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

    public static void type() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Type"+"\n");
        switch (Lookahead) {
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureAndFunctionDeclarationPart"+"\n");
        switch (Lookahead) {
            case "MP_PROCEDURE":
                procedureDeclaration();
                procedureAndFunctionDeclarationPart();
                break;
            case "MP_FUNCTION":
                functionDeclaration();
                procedureAndFunctionDeclarationPart();
                break;
            case "MP_BEGIN":
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
        indent--;
    }

    public static void procedureDeclaration() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureDeclaration"+"\n");
        switch (Lookahead) {
            case "MP_PROCEDURE":
                procedureHeading();
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

    public static void functionDeclaration() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FunctionDeclaration"+"\n");
        switch (Lookahead) {
            case "MP_FUNCTION":
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureHeading"+"\n");
        switch (Lookahead) {
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FunctionHeading"+"\n");
        switch (Lookahead) {
            case "MP_FUNCTION":
                match("MP_FUNCTION");
                functionIdentifier();
                optionalFormalParameterList();
                // match("MP_COLON"); <-- **Stephen**:  Why was this one in here??  Looking at rule #20, there is no colon in there. 
                type();
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
        indent--;
    }

    public static void optionalFormalParameterList() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalFormalParameterList"+"\n");
        switch (Lookahead) {
            case "MP_LPAREN":
                match("MP_LPAREN");
                formalParameterSection();
                formalParameterSectionTail();
                match("MP_RPAREN");
                break;
            case "MP_BOOLEAN":
            case "MP_FLOAT":
            case "MP_INTEGER":
            case "MP_STRING":
            case "MP_SCOLON":
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
        indent--;
    }

    public static void formalParameterSectionTail() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FormalParameterList"+"\n");
        switch (Lookahead) {
            case "MP_SCOLON":
                match("MP_SCOLON");
                formalParameterSection();
                formalParameterSectionTail();
                break;
            case "MP_RPAREN":
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
        indent--;
    }

    public static void formalParameterSection() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FormalParameterSection"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                valueParameterSection();
                break;
            case "MP_VAR":
                variableParameterSection();
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
        indent--;
    }

    public static void valueParameterSection() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ValueParameterSection"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableParameterSection"+"\n");
        switch (Lookahead) {
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"StatementPart"+"\n");
        switch (Lookahead) {
            case "MP_BEGIN":
                compoundStatement();
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
        indent--;
    }

    public static void compoundStatement() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"CompoundStatement"+"\n");
        switch (Lookahead) {
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"StatementSequence"+"\n");
        switch (Lookahead) {
            case "MP_BEGIN":
            case "MP_ELSE":
            case "MP_END":
            case "MP_FOR":
            case "MP_IF":
            case "MP_READ":
            case "MP_REPEAT":
            case "MP_UNTIL":
            case "MP_WHILE":
            case "MP_WRITE":
            case "MP_WRITELN":
            case "MP_IDENTIFIER":
            case "MP_SCOLON":
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"StatementTail"+"\n");
        switch (Lookahead) {
            case "MP_SCOLON":
                match("MP_SCOLON");
                statement();
                statementTail();
                break;
            case "MP_END":
            case "MP_UNTIL":
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
        indent--;
    }

    public static void statement() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Statement"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");//workaround, just match id and move on
                switch (Lookahead) {
                    case "MP_ASSIGN":
                        assignmentStatement();
                        break;

                    case "MP_LPAREN":
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
            case "MP_WRITELN":
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
                
            case "MP_ELSE":
            case "MP_END":
            case "MP_UNTIL":
            case "MP_SCOLON":
                emptyStatement();
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void emptyStatement() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"EmptyStatement"+"\n");
        switch (Lookahead) {
            case "MP_ELSE":
            case "MP_END":
            case "MP_UNTIL":
            case "MP_SCOLON":
                break;
            default:
                syntaxError();// syntaxError
                break;
        }
        indent--;
    }

    public static void readStatement() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ReadStatement"+"\n");
        switch (Lookahead) {
            case "MP_READ":
                match("MP_READ");
                match("MP_LPAREN");
                readParameter();
                readParameterTail();
                match("MP_RPAREN");
                break;
            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void readParameterTail() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ReadParameterTail"+"\n");
        switch (Lookahead) {
            case "MP_COMMA":
                match("MP_COMMA");
                readParameter();
                readParameterTail();
                break;
            case "MP_RPAREN":
                break;
            default: // syntaxError OR Empty-String
                break;
        }
        indent--;
    }

    public static void readParameter() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ReadParameter"+"\n");
        switch (Lookahead) {
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"WriteStatement"+"\n");
        switch (Lookahead) {
            case "MP_WRITE":
                match("MP_WRITE");
                match("MP_LPAREN");
                writeParameter();
                writeParameterTail();
                match("MP_RPAREN");
                break;
            case "MP_WRITELN":
                match("MP_WRITELN");
                match("MP_LPAREN");
                writeParameter();
                writeParameterTail();
                match("MP_RPAREN");
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void writeParameterTail() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"WriteParameterTail"+"\n");
        switch (Lookahead) {
            case "MP_COMMA":
                match("MP_COMMA");
                writeParameter();
                writeParameterTail();
                break;
            case "MP_RPAREN":
                break;
            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void writeParameter() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"WriteParameter"+"\n");
        switch (Lookahead) {
            case "MP_PLUS":
            case "MP_MINUS":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
            case "MP_INTEGER_LIT":
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

    public static void assignmentStatement() {
    	if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"AssignmentStatement"+"\n");
    	// **Stephen** The parser breaks here, the logical consistency is broken in the Statement method, where it uses an "extra" look-ahead.  
        /*
    	switch (Lookahead) {
        case "MP_IDENTIFIER"://both rules are the same
            match("MP_IDENTIFIER");
            match("MP_ASSIGN");
            expression();
            break;
        default: // syntaxError OR Empty-String
            syntaxError();
            break;
        }
        */
        
        
        switch (Lookahead) {
        case "MP_ASSIGN"://both rules are the same
            match("MP_ASSIGN");
            expression();
            break;
        default: // syntaxError OR Empty-String
            syntaxError();
            break;
    }


        indent--;
    }

    public static void ifStatement() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"IfStatement"+"\n");
        switch (Lookahead) {
            case "MP_IF":
                match("MP_IF");
                booleanExpression();
                match("MP_THEN");
                statement();
                optionalElsePart();
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void optionalElsePart() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalElsePart"+"\n");
        switch (Lookahead) {
            case "MP_ELSE":
                match("MP_ELSE");
                statement();
                break;
            case "MP_END":
            case "MP_UNTIL":
            case "MP_SCOLON":
                break;

            default: // syntaxError OR Empty-String
                emptyStatement();
                break;
        }
        indent--;
    }

    public static void repeatStatement() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"RepeatStatement"+"\n");
        switch (Lookahead) {
            case "MP_REPEAT":
                match("MP_REPEAT");
                statementSequence();
                match("MP_UNTIL");
                booleanExpression();
                break;
            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void whileStatement() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"WhileStatement"+"\n");
        switch (Lookahead) {
            case "MP_WHILE":
                match("MP_WHILE");
                booleanExpression();
                match("MP_DO");
                statement();
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void forStatement() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ForStatement"+"\n");
        switch (Lookahead) {
            case "MP_FOR":
                match("MP_FOR");
                controlVariable();
                match("MP_ASSIGN");
                initialValue();
                stepValue();
                finalValue();
                match("MP_DO");
                statement();
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void controlVariable() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ControlVariable"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                variableIdentifier();
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void initialValue() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"InitialValue"+"\n");
        switch (Lookahead) {
            case "MP_PLUS":
            case "MP_MINUS":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"StepValue"+"\n");
        switch (Lookahead) {
            case "MP_TO":
                match("MP_TO");
                break;
            case "MP_DOWNTO":
                match("MP_DOWNTO");
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void finalValue() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FinalValue"+"\n");
        switch (Lookahead) {
            case "MP_PLUS":
            case "MP_MINUS":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureStatment"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
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
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalActualParameterList"+"\n");
        switch (Lookahead) {
            case "MP_LPAREN":
                match("MP_LPAREN");
                actualParameter();
                actualParameterTail();
                match("MP_RPAREN");
                break;
            case "MP_AND":
            case "MP_DIV":
            case "MP_DO":
            case "MP_DOWNTO":
            case "MP_ELSE":
            case "MP_END":
            case "MP_MOD":
            case "MP_OR":
            case "MP_THEN":
            case "MP_TO":
            case "MP_UNTIL":
            case "MP_COMMA":
            case "MP_EQUAL":
            case "MP_FLOAT_DIVIDE":
            case "MP_GEQUAL":
            case "MP_GTHAN":
            case "MP_LEQUAL":
            case "MP_LTHAN":
            case "MP_MINUS":
            case "MP_NEQUAL":
            case "MP_PLUS":
            case "MP_RPAREN":
            case "MP_SCOLON":
            case "MP_TIMES":
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
        indent--;
    }

    public static void actualParameterTail() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ActualParameterTail"+"\n");
        switch (Lookahead) {
            case "MP_COMMA":
                match("MP_COMMA");
                actualParameter();
                actualParameterTail();
                break;
            case "MP_RPAREN":
                break;
            default:
                emptyStatement();
                break;
        }
        indent--;
   }

    public static void actualParameter() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ActualParameter"+"\n");
        switch (Lookahead) {
            case "MP_PLUS":
            case "MP_MINUS":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
            case "MP_TRUE":
            case "MP_FALSE":
            case "MP_NOT":
            case "MP_LPAREN":
            case "MP_IDENTIFIER":
                ordinalExpression();
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void expression() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Expression"+"\n");
        switch (Lookahead) {
            case "MP_PLUS":
            case "MP_MINUS":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
            case "MP_TRUE":
            case "MP_FALSE":
            case "MP_NOT":
            case "MP_LPAREN":
            case "MP_IDENTIFIER":
                simpleExpression();
                optionalRelationalPart();
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void optionalRelationalPart() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalRelationalPart"+"\n");
        switch (Lookahead) {
            case "MP_EQUAL":
            case "MP_GEQUAL":
            case "MP_GTHAN":
            case "MP_LEQUAL":
            case "MP_LTHAN":
            case "MP_NEQUAL":
                relationalOperator();
                simpleExpression();
                break;
            case "MP_DO":
            case "MP_DOWNTO":
            case "MP_ElSE":
            case "MP_END":
            case "MP_THEN":
            case "MP_TO":
            case "MP_UNTIL":
            case "MP_COMMA":
            case "MP_RPAREN":
            case "MP_SCOLON":
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void relationalOperator() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"RelationalOperator"+"\n");
        switch (Lookahead) {
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

    public static void simpleExpression() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"SimpleExpression"+"\n");
        switch (Lookahead) {
            case "MP_FALSE":
            case "MP_NOT":
            case "MP_TRUE":
            case "MP_IDENTIFIER":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
            case "MP_LPAREN":
            case "MP_MINUS":
            case "MP_PLUS":
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

    public static void termTail() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"TermTail"+"\n");
        switch (Lookahead) {
            case "MP_PLUS":
            case "MP_MINUS":
            case "MP_OR":
                addingOperator();
                term();
                termTail();
                break;
            case "MP_EQUAL":
            case "MP_GEQUAL":
            case "MP_GTHAN":
            case "MP_LEQUAL":
            case "MP_LTHAN":
            case "MP_NEQUAL":
            case "MP_DO":
            case "MP_DOWNTO":
            case "MP_ElSE":
            case "MP_END":
            case "MP_THEN":
            case "MP_TO":
            case "MP_UNTIL":
            case "MP_COMMA":
            case "MP_RPAREN":
            case "MP_SCOLON":
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void optionalSign() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OptionalSign"+"\n");
        switch (Lookahead) {
            case "MP_PLUS":
                match("MP_PLUS");
                break;
            case "MP_MINUS":
                match("MP_MINUS");
                break;
            case "MP_FALSE":
            case "MP_NOT":
            case "MP_TRUE":
            case "MP_IDENTIFIER":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
            case "MP_LPAREN":
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
  }

    public static void addingOperator() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"AddingOperator"+"\n");
        switch (Lookahead) {
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

    public static void term() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Term"+"\n");
        switch (Lookahead) {
            case "MP_FALSE":
            case "MP_NOT":
            case "MP_TRUE":
            case "MP_IDENTIFIER":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
            case "MP_LPAREN":
                factor();
                factorTail();
                break;
            default:
                syntaxError();
        }
        indent--;
    }

    public static void factorTail() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FactorTail"+"\n");
        switch (Lookahead) {
            case "MP_DO":
            case "MP_DOWNTO":
            case "MP_ELSE":
            case "MP_END":
            case "MP_OR":
            case "MP_THEN":
            case "MP_TO":
            case "MP_UNTIL":
            case "MP_COMMA":
            case "MP_EQUAL":
            case "MP_GEQUAL":
            case "MP_GTHAN":
            case "MP_LEQUAL":
            case "MP_LTHAN":
            case "MP_MINUS":
            case "MP_NEQUAL":
            case "MP_PLUS":
            case "MP_RPAREN":
            case "MP_SCOLON":
                break;
                
            case "MP_TIMES":
            case "MP_FLOAT_DIVIDE":
            case "MP_AND":
            case "MP_DIV":
            case "MP_MOD":
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

    public static void multiplyingOperator() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"MultiplyingOperator"+"\n");
        switch (Lookahead) {
            case "MP_TIMES":
                match("MP_TIMES");
                break;
            case "MP_FLOAT_DIVIDE":
                match("MP_FLOAT_DIVIDE");
                break;
            case "MP_DIV":
                match("MP_INT_DIV");
                break;
            case "MP_AND":
                match("MP_AND");
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void factor() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"Factor"+"\n");
        switch (Lookahead) {
            case "MP_INTEGER_LIT":
                match("MP_INTEGER_LIT");
                break;
            case "MP_FLOAT_LIT":
                match("MP_FLOAT_LIT");
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
                match("MP_LPAREN");
                expression();
                match("MP_RPAREN");
                break;
            case "MP_IDENTIFIER":
                functionIdentifier();
                optionalActualParameterList();
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void programIdentifier() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProgramIdentifier"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void variableIdentifier() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"VariableIdentifier"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void procedureIdentifier() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"ProcedureIdentifier"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void functionIdentifier() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"FunctionIdentifier"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void booleanExpression() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"BooleanExpression"+"\n");
        switch (Lookahead) {
            case "MP_FALSE":
            case "MP_NOT":
            case "MP_TRUE":
            case "MP_IDENTIFIER":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
            case "MP_LPAREN":
            case "MP_MINUS":
            case "MP_PLUS":
                expression();
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void ordinalExpression() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"OrdinalExpression"+"\n");
        switch (Lookahead) {
            case "MP_FALSE":
            case "MP_NOT":
            case "MP_TRUE":
            case "MP_IDENTIFIER":
            case "MP_INTEGER_LIT":
            case "MP_FLOAT_LIT":
            case "MP_STRING_LIT":
            case "MP_LPAREN":
            case "MP_MINUS":
            case "MP_PLUS":
                expression();
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void identifierList() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"IdentifierList"+"\n");
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                identifierTail();
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }

    public static void identifierTail() {
		if ( PRINT_PARSE_TREE ) System.out.printf(""+String.format("%1$" + LAPad + "s", Lookahead)+" ->"+String.format("%1$" + (indent++*3+1) + "s", "")
				+"IdentifierTail"+"\n");
        switch (Lookahead) {
            case "MP_COMMA":
                match("MP_COMMA");
                match("MP_IDENTIFIER");
                identifierTail();
                break;
            case "MP_COLON":
                break;
            default:
                syntaxError();
                break;
        }
        indent--;
    }
}
