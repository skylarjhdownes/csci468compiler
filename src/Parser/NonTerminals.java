package Parser;

import java.util.LinkedList;

import Tokenizer.Token;

public class NonTerminals {

    static int tokindex;
    static LinkedList<Token> tokens;
    static Token LATok;
    static String Lookahead;

    public static void match(String in) {
		// TODO STUB!!!!!!

        if (in.equals(Lookahead)) {
            LATok = tokens.get(tokindex++);
            Lookahead = LATok.getToken();
        } else {
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
        switch (Lookahead) {
            case "MP_PROGRAM":
                program();
                match("MP_EOF");
                break;

            default: // syntaxError
                syntaxError();
                break;
        }
    }

    public static void program() {
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
    }

    public static void programHeading() {
        switch (Lookahead) {
            case "MP_PROGRAM":
                match("MP_PROGRAM");
                programIdentifier();
                break;
            default:
                syntaxError();// syntaxError
                break;
        }
    }

    public static void block() {
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
    }

    public static void variableDeclarationPart() {
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
            default: // End thingy?
                syntaxError();
                break;
        }
    }

    public static void variableDeclarationTail() {
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
    }

    public static void variableDeclaration() {
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
    }

    public static void type() {
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

    }

    public static void procedureAndFunctionDeclarationPart() {
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
    }

    public static void procedureDeclaration() {
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
    }

    public static void functionDeclaration() {
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
    }

    public static void procedureHeading() {
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
    }

    public static void functionHeading() {
        switch (Lookahead) {
            case "MP_FUNCTION":
                match("MP_FUNCTION");
                functionIdentifier();
                optionalFormalParameterList();
                match("MP_COLON");
                type();
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
    }

    public static void optionalFormalParameterList() {
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
    }

    public static void formalParameterSectionTail() {
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
    }

    public static void formalParameterSection() {
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
    }

    public static void valueParameterSection() {
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
    }

    public static void variableParameterSection() {
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
    }

    public static void statementPart() {
        switch (Lookahead) {
            case "MP_BEGIN":
                compoundStatement();
                break;
            default: // syntaxError
                syntaxError();
                break;
        }
    }

    public static void compoundStatement() {
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
    }

    public static void statementSequence() {
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
    }

    public static void statementTail() {
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
    }

    public static void statement() {
		
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
    }

    public static void emptyStatement() {
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
    }

    public static void readStatement() {
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
    }

    public static void readParameterTail() {
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
    }

    public static void readParameter() {
        switch (Lookahead) {
            case "MP_Identifier":
                variableIdentifier();
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
    }

    public static void writeStatement() {
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
    }

    public static void writeParameterTail() {
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
    }

    public static void writeParameter() {
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
    }

    public static void assignmentStatement() {
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
    }

    public static void ifStatement() {
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
    }

    public static void optionalElsePart() {
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
    }

    public static void repeatStatement() {
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
    }

    public static void whileStatement() {
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
    }

    public static void forStatement() {
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
    }

    public static void controlVariable() {
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                variableIdentifier();
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
    }

    public static void initialValue() {
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
    }

    public static void stepValue() {
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
    }

    public static void finalValue() {
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
    }

    public static void procedureStatement() {
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                procedureIdentifier();
                optionalActualParameterList();
                break;

            default: // syntaxError OR Empty-String
                syntaxError();
                break;
        }
    }

    public static void optionalActualParameterList() {
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
    }

    public static void actualParameterTail() {
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

    }

    public static void actualParameter() {
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

    }

    public static void expression() {
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
    }

    public static void optionalRelationalPart() {
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
    }

    public static void relationalOperator() {
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
    }

    public static void simpleExpression() {
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

    }

    public static void termTail() {
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

    }

    public static void optionalSign() {
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
    }

    public static void addingOperator() {
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
    }

    public static void term() {
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
    }

    public static void factorTail() {
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
    }

    public static void multiplyingOperator() {
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
    }

    public static void factor() {
        switch (Lookahead) {
            case "MP_INT_LIT":
                match("MP_INT_LIT");
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
    }

    public static void programIdentifier() {
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                break;
            default:
                syntaxError();
                break;
        }
    }

    public static void variableIdentifier() {
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                break;
            default:
                syntaxError();
                break;
        }
    }

    public static void procedureIdentifier() {
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                break;
            default:
                syntaxError();
                break;
        }
    }

    public static void functionIdentifier() {
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                break;
            default:
                syntaxError();
                break;
        }
    }

    public static void booleanExpression() {
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
    }

    public static void ordinalExpression() {
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
    }

    public static void identifierList() {
        switch (Lookahead) {
            case "MP_IDENTIFIER":
                match("MP_IDENTIFIER");
                identifierTail();
                break;
            default:
                syntaxError();
                break;
        }
    }

    public static void identifierTail() {
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
    }
}
