package Parser;

public class NonTerminals 
{
	static String Lookahead;
	
	public static void Type()
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
	
	public static void Match(String in) {
		// TODO STUB!!!!!!
		
		// Match the identifier in the parse tree.
		
		// Bump to next lookahead.
		return;
	}

	public static void Error() {
		// TODO STUB!!!!!!
		return;
	}
	
	public static void StatementTail() {
		switch (Lookahead)
		{
		case ";":
			Match(Lookahead);
			break;
			
		default: // Error
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
			
		default: // Error OR Empty-String
			EmptyStatement();
			break;
		}
	}
	
	public static void EmptyStatement() {
		// TODO Lolwtf is this right?
		
		switch (Lookahead)
		{
		default: // Error
			break;
		}
	}

	public static void ReadStatement() {
		switch (Lookahead)
		{
		case "read":
			Match(Lookahead);
			if (Lookahead.equals("(")) {
				Match(Lookahead);
				ReadParameter();
				ReadParameterTail();
				if (Lookahead.equals(")")) {
					Match(Lookahead);
				}
				else Error();
			}
			else Error();
			break;
		default: // Error OR Empty-String
			Error();
			break;
		}
	}
	
	public static void ReadParameterTail() {
		switch (Lookahead)
		{
		case ",":
			Match(Lookahead);
			ReadParameter();
			ReadParameterTail();
			break;
			
		default: // Error OR Empty-String
			break;
		}
	}

	public static void ReadParameter() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			VariableIdentifier();
			break;
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}

	public static void WriteStatement() {
		switch (Lookahead)
		{
		case "write":
		case "writeln":
			Match(Lookahead);
			if ( Lookahead.equals("(")) {
				Match(Lookahead);
				WriteParameter();
				WriteParameterTail();
				if ( Lookahead.equals(")") ) {
					Match(Lookahead);
				}
				else Error();
			}
			else Error();
			break;
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}
	
	public static void WriteParameterTail() {
		switch (Lookahead)
		{
		case ",":
			Match(Lookahead);
			WriteParameter();
			WriteParameterTail();
			break;
			
		default: // Error OR Empty-String
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
			
		default: // Error OR Empty-String
			Error();
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
			Match(Lookahead);
			if ( Lookahead.equals(":=") ) {
				Match(Lookahead);
				Expression();
			}
			else Error();
			break;
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}

	public static void IfStatement() {
		switch (Lookahead)
		{
		case "if":
			Match(Lookahead);
			BooleanExpression();
			if ( Lookahead.equals("then") ) {
				Match(Lookahead);
				Statement();
				OptionalElsePart();
			}
			else Error();
			break;
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}
	
	public static void OptionalElsePart() {
		switch (Lookahead)
		{
		case "else":
			Match(Lookahead);
			Statement();
			break;
			
		default: // Error OR Empty-String
			break;
		}
	}
	
	public static void RepeatStatement() {
		switch (Lookahead)
		{
		case "repeat":
			Match(Lookahead);
			StatementSequence();
			if ( Lookahead.equals("until") ) {
				Match(Lookahead);
				BooleanExpression();
			}
			else Error();
			break;
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}
	
	public static void WhileStatement() {
		switch (Lookahead)
		{
		case "while":
			Match(Lookahead);
			BooleanExpression();
			if ( Lookahead.equals("do") ) {
				Match(Lookahead);
				Statement();
			}
			else Error();
			break;
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}

	public static void ForStatement() {
		switch (Lookahead)
		{
		case "for":
			Match(Lookahead);
			ControlVariable();
			if ( Lookahead.equals(":=") ) {
				Match(Lookahead);
				InitialValue();
				StepValue();
				FinalValue();
				if ( Lookahead.equals("do") ) {
					Match(Lookahead);
					Statement();
				}
				else Error();
			}
			else Error();			
			break;
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}

	public static void ControlVariable() {
		switch (Lookahead)
		{
		case "MP_Identifier":
			ControlVariable();
			break;
			
		default: // Error OR Empty-String
			Error();
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
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}

	public static void StepValue() {
		switch (Lookahead)
		{
		case "to":
		case "downto":
			Match(Lookahead);
			break;
			
		default: // Error OR Empty-String
			Error();
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
			
		default: // Error OR Empty-String
			Error();
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
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}

	public static void OptionalActualParameterList() {
		switch (Lookahead)
		{
		case "(":
			Match(Lookahead);
			ActualParameter();
			ActualParameterTail();
			if ( Lookahead.equals(")") ) {
				Match(Lookahead);
			}
			else Error();
			break;
			
		default: // Error OR Empty-String
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
			
		default: // Error OR Empty-String
			Error();
			break;
		}
	}
	*/
	
			
		
		
	
}
