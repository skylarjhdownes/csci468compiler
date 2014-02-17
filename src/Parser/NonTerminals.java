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

	public static void error() {
		// TODO STUB!!!!!!
		return;
	}
	public static void systemGoal() {
		switch (Lookahead)
		{
		case "program":
			match(Lookahead);
			program();
			//EOF thing?
			break;

		default: // error
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
		default: // error
			break;
		}
	}

	
	public static void programHeading() {
		switch (Lookahead)
		{
		case "something":
			match("MP_PROGRAM");
			programIdentifier();
			break;
		default: // error
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
		default: // error
			break;
		}
	}
	
	public static void variableDeclarationPart() {
		switch (Lookahead)
		{
		case "something":
			match("MP_VAR");
			variableDeclaration();
			match("MP_SCOLON");
		case "something":
			break;
		default: // End thingy?
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
		case "something":
			break;
		default: // End thingy?
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
		default: // error
			error();
			break;
		}
	}
	
	public static void type()
	{
		switch (Lookahead)
		{
		case "integer":
			match("MP_INTEGER");
			break;
		case "float":
			match("MP_FLOAT");
			break;
		case "string":
			match("MP_STRING");
			break;
		case "boolean":
			match("MP_BOOLEAN");
			break;
		default: // error
			error();
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
		case "something":
			break;	
		default: // error
			error();
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
		default: // error
			error();
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
		default: // error
			error();
			break;
		}
	}
	
	public static void procedureHeading() {
		switch (Lookahead)
		{
		case "something":
			match("MP_PROCEDURE");
			procedureIdentifier();
			optionalFormalParameterList();
			break;
		default: // error
			error();
			break;
		}
	}
	
	public static void functionHeading() {
		switch (Lookahead)
		{
		case "something":
			match("MP_FUNCTION");
			functionIdentifier();
			optionalFormalParameterList();
			type();
			break;
		default: // error
			error();
			break;
		}
	}
	
	public static void optionalFormalParameterList() {
		switch (Lookahead)
		{
		case "something":
			match("MP_LPAREN");
			formalParameterSection();
			formalParameterSectionTail();
			match("MP_RPAREN");
			break;
		case "something":
			break;
		default: // error
			error();
			break;
		}
	}
	
	public static void formalParameterSectionTail() {
		switch (Lookahead)
		{
		case "something":
			match("MP_SCOLON");
			formalParameterSection();
			formalParameterSectionTail();
			break;
		case "something":
			break;
		default: // error
			error();
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
		default: // error
			error();
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
		default: // error
			error();
			break;
		}
	}
	
	public static void variableParameterSection() {
		switch (Lookahead)
		{
		case "something":
			match("MP_VAR");
			identifierList();
			match("MP_COLON");
			type();
			break;
			
		default: // error
			error();
			break;
		}
	}
	
	public static void statementPart() {
		switch (Lookahead)
		{
		case "something":
			compoundStatement();
			break;
		default: // error
			error();
			break;
		}
	}
	
	public static void compoundStatement() {
		switch (Lookahead)
		{
		case "something":
			match("MP_BEGIN");
			statementSequence();
			match("MP_END");
			break;
			
		default: // error
			error();
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
		default: // error
			error();
			break;
		}
	}
	
	public static void statementTail() {
		switch (Lookahead)
		{
		case "something":
			match("MP_SCOLON");
			statement();
			statementTail();
			break;
			
		default: // error
			error();
			break;
		}
	}
			
	public static void statement() {
		// TODO
		// Fix the default case.  Should not be EmptyString(), suppose a non-empty but not matching 
		// string was encountered next, it should throw an error, but how to handle EmptyStatement()???
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

		case "begin":
			compoundStatement();
			break;
			
		case "read":
			readStatement();
			break;
			
		case "write":
			writeStatement();
			break;
			
		case "if":
			ifStatement();
			break;
			
		case "while":
			whileStatement();
			break;
			
		case "repeat":
			repeatStatement();
			break;
			
		case "for":
			forStatement();
			break;
			
		default: // error OR Empty-String
			emptyStatement();
			break;
		}
	}
	
	public static void emptyStatement() {
		// TODO Lolwtf is this right?
		
		switch (Lookahead)
		{
		default: // error
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
				else error();
			}
			else error();
			break;
		default: // error OR Empty-String
			error();
			break;
		}
	}
	
	public static void readParameterTail() {
		switch (Lookahead)
		{
		case ",":
			match(Lookahead);
			readParameter();
			readParameterTail();
			break;
			
		default: // error OR Empty-String
			break;
		}
	}

	public static void readParameter() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			variableIdentifier();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void writeStatement() {
		switch (Lookahead)
		{
		case "write":
		case "writeln":
			match(Lookahead);
			if ( Lookahead.equals("(")) {
				match(Lookahead);
				writeParameter();
				writeParameterTail();
				if ( Lookahead.equals(")") ) {
					match(Lookahead);
				}
				else error();
			}
			else error();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}
	
	public static void writeParameterTail() {
		switch (Lookahead)
		{
		case ",":
			match(Lookahead);
			writeParameter();
			writeParameterTail();
			break;
			
		default: // error OR Empty-String
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
			
		default: // error OR Empty-String
			error();
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
			else error();
			break;
			
		default: // error OR Empty-String
			error();
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
			else error();
			break;
			
		default: // error OR Empty-String
			error();
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
			
		default: // error OR Empty-String
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
			else error();
			break;
			
		default: // error OR Empty-String
			error();
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
			else error();
			break;
			
		default: // error OR Empty-String
			error();
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
				else error();
			}
			else error();			
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void controlVariable() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			variableIdentifier();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}
	
	public static void initialValue() {
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
			
		default: // error OR Empty-String
			error();
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
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void finalValue() {
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
			
		default: // error OR Empty-String
			error();
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
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void optionalActualParameterList() {
		switch (Lookahead)
		{
		case "(":
			match(Lookahead);
			actualParameter();
			actualParameterTail();
			if ( Lookahead.equals(")") ) {
				match(Lookahead);
			}
			else error();
			break;
			
		default: // error OR Empty-String
			break;
		}
	}

	 public static void actualParameterTail(){
         switch (Lookahead){
             case ",":
                 actualParameter();
                 actualParameterTail();
                 break;
             default:
             
         }
         
     }
     
     public static void actualParameter(){
         switch(Lookahead){
             case "something": 
                 ordinalExpression();
                 break;
             
         }
         
     }
     
     public static void expression(){
         switch(Lookahead){
             case "something":
                 simpleExpression();
                 optionalRelationalPart();
                 break;
         }
     }
	
	/*	
	public static void statement() {
		switch (Lookahead)
		{
		case "MP_":
			break;
			
		case "MP_":
			break;
			
		case "MP_":
			break;
			
		case "MP_":
			break;
			
		case "MP_":
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}
	*/


        public static void optionalRelationalPart(){
            switch(Lookahead){
                case "something":
                    relationalOperator();
                    simpleExpression();
                    break;
                default:
                    
            }
        }

			
	public static void relationalOperator(){
            switch(Lookahead){
                case "equal":
                    match("MP_EQUAL");
                    break;
                case "Less":
                    match("MP_LTHAN");
                    break;
                case "Great":
                    match("MP_GTHAN");
                    break;
                case "Greatequal":
                    match("MP_GQUAL");
                    break;
                case "Lessequal":
                    match("MP_LQUAL");
                    break;
                case "Not":
                    match("MP_NQUAL");
                    break;
                default:
                    error();
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
                    
            }
            
        }
        

	
	
        public static void optionalSign(){
            switch(Lookahead){
                case "+":
                    match("MP_PLUS");
                    break;
                case "-":
                    match("MP_MINUS");
                    break;
                    
                    
                    
                default:
                    error();
            }
        }
        
        public static void addingOperator(){
            switch(Lookahead){
            	case "+":
            		match("MP_PLUS");
            		break;
            	case "-":
            		match("MP_MINUS");
            		break;
            	case "or":
            		match("MP_OR");
            		break;
            	default:
            		error();
            }
        }
        
        public static void term(){
            switch(Lookahead){
                case "something":
                    factor();
                    factorTail();
                    break;
                default:
            		error();
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
                    error();
            }
        }
        
        public static void factor(){
            switch(Lookahead){
                case "int":
                    match("MP_INT");
                    break;
                case "float":
                    match("MP_FLOAT");
                    break;
                case "string":
                    match("MP_STRING_LIT");
                    break;
                case "true":
                    match("MP_TRUE");
                    break;
                case "false":
                    match("MP_FALSE");
                    break;
                case "not":
                    match("MP_NOT");
                    factor();
                    break;
                case "(":
                    match("(");
                    expression();
                    match(")");
                    break;
                case "other":
                    functionIdentifier();
                    optionalActualParameterList();
                    break;
            }
        }
        
        public static void programIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    error();
            }
        }
            
            public static void variableIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    error();
            }
            }   
            public static void procedureIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    error();
            }
            }
            public static void functionIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    error();
            }
            }
            public static void booleanExpression(){
                switch(Lookahead){
                    case "something":
                        expression();
                        break;
                    default:
                }
            }
            
            public static void ordinalExpression(){
                switch(Lookahead){
                    case "something":
                        expression();
                        break;
                    default:
                }
            }
            
            public static void identifierList(){
                switch(Lookahead){
                    case "somthign":
                        match("MP_ID");
                        identifierTail();
                        break;
                    default:
                }
            }
            
            public static void identifierTail(){
                switch(Lookahead){
                    case "lask;djf;":
                        match("MP_COMMA");
                        match("MP_ID");
                        identifierTail();
                        break;
                    default:
                }
            }
}
