package Parser;

public class NonTerminals 
{
	static String Lookahead;
	
	public static void match(String in) {
		// TODO STUB!!!!!!
		
		// match the identifier in the parse tree.
		
		// Bump to next lookahead.
		return;
	}

	public static void syntaxError() {
		// TODO STUB!!!!!!
		return;
	}
	public static void systemGoal() {
		switch (Lookahead)
		{
		case "MP_PROGRAM":
			match(Lookahead);
			program();
			//EOF thing?
			break;

		default: // syntaxsyntaxError
			break;
		}
	}

	public static void program() {
		switch (Lookahead)
		{
		case "something":
			
			programHeading();
			match("MP_SCOLON");
			block();
			match("MP_PERIOD");
			break;
		default: // syntaxsyntaxError
			break;
		}
	}

	
	public static void programHeading() {
		switch (Lookahead)
		{
		case "MP_PROGRAM":
			match("MP_PROGRAM");
			programIdentifier();
			break;
		default: // syntaxsyntaxError
			break;
		}
	}
	
	public static void block() {
		switch (Lookahead)
		{
		case "something":
			variableDeclarationPart();
			procedureAndFunctionDeclarationPart();
			break;
		default: // syntaxsyntaxError
			break;
		}
	}
	
	public static void variableDeclarationPart() {
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
	}

	public static void variableDeclarationTail() {
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
	}
	
	public static void variableDeclaration() {
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
	}
	
	public static void type()
	{
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
	
	}
	
	public static void procedureAndFunctionDeclarationPart() {
		switch (Lookahead)
		{
		case "something":
			match(Lookahead);
			procedureDeclaration();
			procedureAndFunctionDeclarationPart();
			break;
		case "something":
			match(Lookahead);
			functionDeclaration();
			procedureAndFunctionDeclarationPart();
			break;
		default: // syntaxError
			emptyStatement();
			break;
		}
	}
	
	public static void procedureDeclaration() {
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
	}
	
	public static void functionDeclaration() {
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
	}
	
	public static void procedureHeading() {
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
	}
	
	public static void functionHeading() {
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
	}
	
	public static void optionalFormalParameterList() {
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
	}
	
	public static void formalParameterSectionTail() {
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
	}
	
	public static void formalParameterSection() {
		switch (Lookahead)
		{
		case "something":
			valueParameterSection();
			break;
		case "something":
			variableParameterSection();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
	}
	
	public static void valueParameterSection() {
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
	}
	
	public static void variableParameterSection() {
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
	}
	
	public static void statementPart() {
		switch (Lookahead)
		{
		case "something":
			compoundStatement();
			break;
		default: // syntaxError
			syntaxError();
			break;
		}
	}
	
	public static void compoundStatement() {
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
	}
	
	public static void statementSequence() {
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
	}
	
	public static void statementTail() {
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
	}
			
	public static void statement() {
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
	}
	
	public static void emptyStatement() {
		// TODO Lolwtf is this right?
		
		switch (Lookahead)
		{
		default: // syntaxError
			break;
		}
	}

	public static void readStatement() {
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
	}
	
	public static void readParameterTail() {
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
	}

	public static void readParameter() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			variableIdentifier();
			break;
			
		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
	}

	public static void writeStatement() {
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
	}
	
	public static void writeParameterTail() {
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
	}
	
	public static void writeParameter() {
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
	}

	public static void assignmentStatement() {
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
	}

	public static void ifStatement() {
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
	}
	
	public static void optionalElsePart() {
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
	}
	
	public static void repeatStatement() {
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
	}
	
	public static void whileStatement() {
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
	}

	public static void forStatement() {
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
	}

	public static void controlVariable() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			variableIdentifier();
			break;
			
		default: // syntaxError OR Empty-String
			syntaxError();
			break;
		}
	}
	
	public static void initialValue() {
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
	}

	public static void stepValue() {
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
	}

	public static void finalValue() {
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
	}

	public static void procedureStatement() {
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
	}

	public static void optionalActualParameterList() {
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
	}

	 public static void actualParameterTail(){
         switch (Lookahead){
             case "MP_COMMA":
                 actualParameter();
                 actualParameterTail();
                 break;
             default:
            	 emptyStatement();
            	 break;
         }
         
     }
     
     public static void actualParameter(){
         switch(Lookahead){
             case "something": 
                 ordinalExpression();
                 break;
             default:
            	 syntaxError();
            	 break;
         }
         
     }
     
     public static void expression(){
         switch(Lookahead){
             case "something":
                 simpleExpression();
                 optionalRelationalPart();
                 break;
             default:
            	 syntaxError();
            	 break;
         }
     }

        public static void optionalRelationalPart(){
            switch(Lookahead){
                case "something":
                    relationalOperator();
                    simpleExpression();
                    break;
                default:
                	emptyStatement();
                	break;
            }
        }

			
	public static void relationalOperator(){
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
        }
        	
        public static void simpleExpression(){
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
            
        }
		
	public static void termTail(){
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
            
        }
        

	
	
        public static void optionalSign(){
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
        }
        
        public static void addingOperator(){
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
        }
        
        public static void term(){
            switch(Lookahead){
                case "something":
                    factor();
                    factorTail();
                    break;
                default:
            		syntaxError();
            }
        }
        
        public static void factorTail(){
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
        }
        
        public static void multiplyingOperator(){
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
        }
        
        public static void factor(){
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
        }
        
        public static void programIdentifier(){
            switch(Lookahead){
                case "MP_ID":
                    match("MP_ID");
                    break;
                default:
                	syntaxError();
                	break;
            }
        }
            
            public static void variableIdentifier(){
            switch(Lookahead){
                case "MP_ID":
                    match("MP_ID");
                    break;
                default:
                	syntaxError();
                	break;            
                }
            }   
            public static void procedureIdentifier(){
            switch(Lookahead){
                case "MP_ID":
                    match("MP_ID");
                    break;
                default:
                	syntaxError();
                	break;            
                }
            }
            public static void functionIdentifier(){
            switch(Lookahead){
                case "MP_ID":
                    match("MP_ID");
                    break;
                default:
                	syntaxError();
                	break;
            	}
            }
            public static void booleanExpression(){
                switch(Lookahead){
                    case "something":
                        expression();
                        break;
                    default:
                    	syntaxError();
                    	break;
                }
            }
            
            public static void ordinalExpression(){
                switch(Lookahead){
                    case "something":
                        expression();
                        break;
                    default:
                    	syntaxError();
                    	break;
                }
            }
            
            public static void identifierList(){
                switch(Lookahead){
                    case "something":
                        match("MP_ID");
                        identifierTail();
                        break;
                    default:
                    	syntaxError();
                    	break;
                }
            }
            
            public static void identifierTail(){
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
            }
}
