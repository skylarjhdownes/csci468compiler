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
        
        public static void ActualParameterTail(){
            switch (Lookahead){
                case ",":
                    ActualParameter();
                    ActualParameterTail();
                    break;
                default:
                
            }
            
        }
        
        public static void ActualParameter(){
            switch(Lookahead){
                case "something": 
                    OrdinalExpression();
                    break;
                
            }
            
        }
        
        public static void Expression(){
            switch(Lookahead){
                case "something":
                    SimpleExpression();
                    OptionalRelationalPart();
                    break;
            }
        }

	
	
	public static void systemGoal() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void program() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void programHeading() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void block() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void variableDeclarationPart() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}

	public static void variableDeclarationTail() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void variableDeclaration() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void type()
	{
		switch (Lookahead)
		{
		case "integer":
		case "float":
		case "string":
		case "boolean":
		default:
		}
	
	}
	
	public static void procedureAndFunctionDeclarationPart() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void procedureDeclaration() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void functionDeclaration() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void procedureHeading() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void functionHeading() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void optionalFormalParameterList() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void formalParameterSectionTail() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void formalParameterSection() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void valueParameterSection() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void variableParameterSection() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void statementPart() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void compundStatement() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void statementSequence() {
		switch (Lookahead)
		{
		case "":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
	
	public static void StatementTail() {
		switch (Lookahead)
		{
		case ";":
			match(Lookahead);
			break;
			
		default: // error
			break;
		}
	}
			
	public static void Statement() {
		// TODO
		// Fix the default case.  Should not be EmptyString(), suppose a non-empty but not matching 
		// string was encountered next, it should throw an error, but how to handle EmptyStatement()???
		switch (Lookahead)
		{
		case "MP_Identifier":
			switch (Lookahead) {
			case ":=":
				AssignmentStatement();
				break;
				
			case "(":
				ProcedureStatement();
				break;
			}
			break;

		case "begin":
			CompoundStatement();
			break;
			
		case "read":
			ReadStatement();
			break;
			
		case "write":
			WriteStatement();
			break;
			
		case "if":
			IfStatement();
			break;
			
		case "while":
			WhileStatement();
			break;
			
		case "repeat":
			RepeatStatement();
			break;
			
		case "for":
			ForStatement();
			break;
			
		default: // error OR Empty-String
			EmptyStatement();
			break;
		}
	}
	
	public static void EmptyStatement() {
		// TODO Lolwtf is this right?
		
		switch (Lookahead)
		{
		default: // error
			break;
		}
	}

	public static void ReadStatement() {
		switch (Lookahead)
		{
		case "read":
			match(Lookahead);
			if (Lookahead.equals("(")) {
				match(Lookahead);
				ReadParameter();
				ReadParameterTail();
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
	
	public static void ReadParameterTail() {
		switch (Lookahead)
		{
		case ",":
			match(Lookahead);
			ReadParameter();
			ReadParameterTail();
			break;
			
		default: // error OR Empty-String
			break;
		}
	}

	public static void ReadParameter() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			VariableIdentifier();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void WriteStatement() {
		switch (Lookahead)
		{
		case "write":
		case "writeln":
			match(Lookahead);
			if ( Lookahead.equals("(")) {
				match(Lookahead);
				WriteParameter();
				WriteParameterTail();
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
	
	public static void WriteParameterTail() {
		switch (Lookahead)
		{
		case ",":
			match(Lookahead);
			WriteParameter();
			WriteParameterTail();
			break;
			
		default: // error OR Empty-String
			break;
		}
	}
	
	public static void WriteParameter() {
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
			OrdinalExpression();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void AssignmentStatement() {
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
				Expression();
			}
			else error();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void IfStatement() {
		switch (Lookahead)
		{
		case "if":
			match(Lookahead);
			BooleanExpression();
			if ( Lookahead.equals("then") ) {
				match(Lookahead);
				Statement();
				OptionalElsePart();
			}
			else error();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}
	
	public static void OptionalElsePart() {
		switch (Lookahead)
		{
		case "else":
			match(Lookahead);
			Statement();
			break;
			
		default: // error OR Empty-String
			break;
		}
	}
	
	public static void RepeatStatement() {
		switch (Lookahead)
		{
		case "repeat":
			match(Lookahead);
			StatementSequence();
			if ( Lookahead.equals("until") ) {
				match(Lookahead);
				BooleanExpression();
			}
			else error();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}
	
	public static void WhileStatement() {
		switch (Lookahead)
		{
		case "while":
			match(Lookahead);
			BooleanExpression();
			if ( Lookahead.equals("do") ) {
				match(Lookahead);
				Statement();
			}
			else error();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void ForStatement() {
		switch (Lookahead)
		{
		case "for":
			match(Lookahead);
			ControlVariable();
			if ( Lookahead.equals(":=") ) {
				match(Lookahead);
				InitialValue();
				StepValue();
				FinalValue();
				if ( Lookahead.equals("do") ) {
					match(Lookahead);
					Statement();
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

	public static void ControlVariable() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			VariableIdentifier();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}
	
	public static void InitialValue() {
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
			OrdinalExpression();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void StepValue() {
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

	public static void FinalValue() {
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
			OrdinalExpression();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void ProcedureStatement() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			ProcedureIdentifier(); 
			OptionalActualParameterList();
			break;
			
		default: // error OR Empty-String
			error();
			break;
		}
	}

	public static void OptionalActualParameterList() {
		switch (Lookahead)
		{
		case "(":
			match(Lookahead);
			ActualParameter();
			ActualParameterTail();
			if ( Lookahead.equals(")") ) {
				match(Lookahead);
			}
			else error();
			break;
			
		default: // error OR Empty-String
			break;
		}
	}


	
	/*	
	public static void Statement() {
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


        public static void OptionalRelationalPart(){
            switch(Lookahead){
                case "something":
                    RelationalOperator();
                    SimpleExpression();
                    break;
                default:
                    
            }
        }

			
	public static void RelationalOperator(){
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
        	
        public static void SimpleExpression(){
            switch(Lookahead){
                case "something":
                    OptionalSign();
                    Term();
                    TermTail();
                    break;
                default:
            }
            
        }
		
	public static void TermTail(){
            switch(Lookahead){
                case "something":
                    AddingOperator();
                    Term();
                    TermTail();
                    break;
                default:
                    
            }
            
        }
        
        public static void OptionalSign(){
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
        
        public static void Term(){
            switch(Lookahead){
                case "something":
                    Factor();
                    FactorTail();
                    break;
            }
        }
        
        public static void FactorTail(){
            switch(Lookahead){
                case "something":
                    MultiplyingOperator();
                    Factor();
                    FactorTail();
                    break;
                default:
            }
        }
        
        public static void MultiplyingOperator(){
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
        
        public static void Factor(){
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
                    Factor();
                    break;
                case "(":
                    match("(");
                    Expression();
                    match(")");
                    break;
                case "other":
                    FunctionIdentifier();
                    OptionalActualParameterList();
                    break;
            }
        }
        
        public static void ProgramIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    error();
            }
        }
            
            public static void VariableIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    error();
            }
            }   
            public static void ProcedureIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    error();
            }
            }
            public static void FunctionIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    error();
            }
            }
            public static void BooleanExpression(){
                switch(Lookahead){
                    case "something":
                        Expression();
                        break;
                    default:
                }
            }
            
            public static void OrdinalExpression(){
                switch(Lookahead){
                    case "something":
                        Expression();
                        break;
                    default:
                }
            }
            
            public static void IdentifierList(){
                switch(Lookahead){
                    case "somthign":
                        match("MP_ID");
                        IdentifierTail();
                        break;
                    default:
                }
            }
            
            public static void IdentifierTail(){
                switch(Lookahead){
                    case "lask;djf;":
                        match("MP_COMMA");
                        match("MP_ID");
                        IdentifierTail();
                        break;
                    default:
                }
            }
}
