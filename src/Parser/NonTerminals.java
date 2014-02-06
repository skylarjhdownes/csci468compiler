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
        
        public static void ActualParameterTail(){
            switch (Lookahead){
                case ',':
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
                    Error();
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
                    Error();
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
                    matchI+("MP_AND");
                    break;
                default:
                    Error();
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
                    Error;
            }
        }
            
            public static void VariableIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    Error;
            }
            }   
            public static void ProcedureIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    Error;
            }
            }
            public static void FunctionIdentifier(){
            switch(Lookahead){
                case "id":
                    match("MP_ID");
                    break;
                default:
                    Error;
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
