######################################### 	
#    CSCI 468 - MicroPascal Program Generator
#										
#  Stephen Bush
#  darkshark.007@gmail.com
#			
#########################################

use Switch;
use strict;

my $FLAG_DEBUG_1 = 0;
my $indent = 1;
my $RecLimit = 50;

my @LLTable;
$LLTable[0][19] = 1;
$LLTable[1][19] = 2;
$LLTable[2][19] = 3;
$LLTable[3][1] = 4;$LLTable[3][12] = 4;$LLTable[3][18] = 4;$LLTable[3][27] = 4;
$LLTable[4][1] = 6;$LLTable[4][12] = 6;$LLTable[4][18] = 6;$LLTable[4][27] = 5;
$LLTable[5][1] = 8;$LLTable[5][12] = 8;$LLTable[5][18] = 8;$LLTable[5][31] = 8;
$LLTable[6][31] = 9;
$LLTable[7][2] = 13;$LLTable[7][10] = 11;$LLTable[7][14] = 10;$LLTable[7][22] = 12;
$LLTable[8][1] = 16;$LLTable[8][12] = 15;$LLTable[8][18] = 14;
$LLTable[9][18] = 17;
$LLTable[10][12] = 18;
$LLTable[11][18] = 19;
$LLTable[12][12] = 20;
$LLTable[13][37] = 22;$LLTable[13][44] = 21;$LLTable[13][51] = 22;
$LLTable[14][50] = 24;$LLTable[14][51] = 23;
$LLTable[15][27] = 26;$LLTable[15][31] = 25;
$LLTable[16][31] = 27;
$LLTable[17][27] = 28;
$LLTable[18][1] = 29;
$LLTable[19][1] = 30;
$LLTable[20][1] = 31;$LLTable[20][6] = 31;$LLTable[20][7] = 31;$LLTable[20][11] = 31;$LLTable[20][13] = 31;$LLTable[20][20] = 31;$LLTable[20][21] = 31;$LLTable[20][26] = 31;$LLTable[20][28] = 31;$LLTable[20][29] = 31;$LLTable[20][30] = 31;$LLTable[20][31] = 31;$LLTable[20][51] = 31;
$LLTable[21][7] = 33;$LLTable[21][26] = 33;$LLTable[21][51] = 32;
$LLTable[22][1] = 35;$LLTable[22][6] = 34;$LLTable[22][7] = 34;$LLTable[22][11] = 42;$LLTable[22][13] = 39;$LLTable[22][20] = 36;$LLTable[22][21] = 41;$LLTable[22][26] = 34;$LLTable[22][28] = 40;$LLTable[22][29] = 37;$LLTable[22][30] = 37;$LLTable[22][31] = 43,38;$LLTable[22][51] = 34;
$LLTable[23][6] = 44;$LLTable[23][7] = 44;$LLTable[23][26] = 44;$LLTable[23][51] = 44;
$LLTable[24][20] = 45;
$LLTable[25][38] = 46;$LLTable[25][50] = 47;
$LLTable[26][31] = 48;
$LLTable[27][29] = 49;$LLTable[27][30] = 50;
$LLTable[28][38] = 51;$LLTable[28][50] = 52;
$LLTable[29][8] = 53;$LLTable[29][16] = 53;$LLTable[29][24] = 53;$LLTable[29][31] = 53;$LLTable[29][32] = 53;$LLTable[29][34] = 53;$LLTable[29][35] = 53;$LLTable[29][44] = 53;$LLTable[29][46] = 53;$LLTable[29][49] = 53;
$LLTable[30][31] = 54,55;
$LLTable[31][13] = 56;
$LLTable[32][6] = 57;$LLTable[32][7] = 58;$LLTable[32][26] = 58;$LLTable[32][51] = 58;
$LLTable[33][21] = 59;
$LLTable[34][28] = 60;
$LLTable[35][11] = 61;
$LLTable[36][31] = 62;
$LLTable[37][8] = 63;$LLTable[37][16] = 63;$LLTable[37][24] = 63;$LLTable[37][31] = 63;$LLTable[37][32] = 63;$LLTable[37][34] = 63;$LLTable[37][35] = 63;$LLTable[37][44] = 63;$LLTable[37][46] = 63;$LLTable[37][49] = 63;
$LLTable[38][5] = 65;$LLTable[38][25] = 64;
$LLTable[39][8] = 66;$LLTable[39][16] = 66;$LLTable[39][24] = 66;$LLTable[39][31] = 66;$LLTable[39][32] = 66;$LLTable[39][34] = 66;$LLTable[39][35] = 66;$LLTable[39][44] = 66;$LLTable[39][46] = 66;$LLTable[39][49] = 66;
$LLTable[40][31] = 67;
$LLTable[41][0] = 69;$LLTable[41][3] = 69;$LLTable[41][4] = 69;$LLTable[41][5] = 69;$LLTable[41][6] = 69;$LLTable[41][7] = 69;$LLTable[41][15] = 69;$LLTable[41][17] = 69;$LLTable[41][23] = 69;$LLTable[41][25] = 69;$LLTable[41][26] = 69;$LLTable[41][38] = 69;$LLTable[41][39] = 69;$LLTable[41][40] = 69;$LLTable[41][41] = 69;$LLTable[41][42] = 69;$LLTable[41][43] = 69;$LLTable[41][44] = 68;$LLTable[41][45] = 69;$LLTable[41][46] = 69;$LLTable[41][47] = 69;$LLTable[41][49] = 69;$LLTable[41][50] = 69;$LLTable[41][51] = 69;$LLTable[41][52] = 69;
$LLTable[42][38] = 70;$LLTable[42][50] = 71;
$LLTable[43][8] = 72;$LLTable[43][16] = 72;$LLTable[43][24] = 72;$LLTable[43][31] = 72;$LLTable[43][32] = 72;$LLTable[43][34] = 72;$LLTable[43][35] = 72;$LLTable[43][44] = 72;$LLTable[43][46] = 72;$LLTable[43][49] = 72;
$LLTable[44][8] = 73;$LLTable[44][16] = 73;$LLTable[44][24] = 73;$LLTable[44][31] = 73;$LLTable[44][32] = 73;$LLTable[44][34] = 73;$LLTable[44][35] = 73;$LLTable[44][44] = 73;$LLTable[44][46] = 73;$LLTable[44][49] = 73;
$LLTable[45][4] = 75;$LLTable[45][5] = 75;$LLTable[45][6] = 75;$LLTable[45][7] = 75;$LLTable[45][23] = 75;$LLTable[45][25] = 75;$LLTable[45][26] = 75;$LLTable[45][38] = 75;$LLTable[45][39] = 74;$LLTable[45][41] = 74;$LLTable[45][42] = 74;$LLTable[45][43] = 74;$LLTable[45][45] = 74;$LLTable[45][47] = 74;$LLTable[45][50] = 75;$LLTable[45][51] = 75;
$LLTable[46][39] = 76;$LLTable[46][41] = 80;$LLTable[46][42] = 78;$LLTable[46][43] = 79;$LLTable[46][45] = 77;$LLTable[46][47] = 81;
$LLTable[47][8] = 82;$LLTable[47][16] = 82;$LLTable[47][24] = 82;$LLTable[47][31] = 82;$LLTable[47][32] = 82;$LLTable[47][34] = 82;$LLTable[47][35] = 82;$LLTable[47][44] = 82;$LLTable[47][46] = 82;$LLTable[47][49] = 82;
$LLTable[48][4] = 84;$LLTable[48][5] = 84;$LLTable[48][6] = 84;$LLTable[48][7] = 84;$LLTable[48][17] = 83;$LLTable[48][23] = 84;$LLTable[48][25] = 84;$LLTable[48][26] = 84;$LLTable[48][38] = 84;$LLTable[48][39] = 84;$LLTable[48][41] = 84;$LLTable[48][42] = 84;$LLTable[48][43] = 84;$LLTable[48][45] = 84;$LLTable[48][46] = 83;$LLTable[48][47] = 84;$LLTable[48][49] = 83;$LLTable[48][50] = 84;$LLTable[48][51] = 84;
$LLTable[49][8] = 87;$LLTable[49][16] = 87;$LLTable[49][24] = 87;$LLTable[49][31] = 87;$LLTable[49][32] = 87;$LLTable[49][34] = 87;$LLTable[49][35] = 87;$LLTable[49][44] = 87;$LLTable[49][46] = 86;$LLTable[49][49] = 85;
$LLTable[50][17] = 90;$LLTable[50][46] = 89;$LLTable[50][49] = 88;
$LLTable[51][8] = 91;$LLTable[51][16] = 91;$LLTable[51][24] = 91;$LLTable[51][31] = 91;$LLTable[51][32] = 91;$LLTable[51][34] = 91;$LLTable[51][35] = 91;$LLTable[51][44] = 91;
$LLTable[52][0] = 92;$LLTable[52][3] = 92;$LLTable[52][4] = 93;$LLTable[52][5] = 93;$LLTable[52][6] = 93;$LLTable[52][7] = 93;$LLTable[52][15] = 92;$LLTable[52][17] = 93;$LLTable[52][23] = 93;$LLTable[52][25] = 93;$LLTable[52][26] = 93;$LLTable[52][38] = 93;$LLTable[52][39] = 93;$LLTable[52][40] = 92;$LLTable[52][41] = 93;$LLTable[52][42] = 93;$LLTable[52][43] = 93;$LLTable[52][45] = 93;$LLTable[52][46] = 93;$LLTable[52][47] = 93;$LLTable[52][49] = 93;$LLTable[52][50] = 93;$LLTable[52][51] = 93;$LLTable[52][52] = 92;
$LLTable[53][0] = 98;$LLTable[53][3] = 96;$LLTable[53][15] = 97;$LLTable[53][40] = 95;$LLTable[53][52] = 94;
$LLTable[54][8] = 103;$LLTable[54][16] = 104;$LLTable[54][24] = 102;$LLTable[54][31] = 106;$LLTable[54][32] = 99;$LLTable[54][34] = 100;$LLTable[54][35] = 101;$LLTable[54][44] = 105;
$LLTable[55][31] = 107;
$LLTable[56][31] = 108;
$LLTable[57][31] = 109;
$LLTable[58][31] = 110;
$LLTable[59][8] = 111;$LLTable[59][16] = 111;$LLTable[59][24] = 111;$LLTable[59][31] = 111;$LLTable[59][32] = 111;$LLTable[59][34] = 111;$LLTable[59][35] = 111;$LLTable[59][44] = 111;$LLTable[59][46] = 111;$LLTable[59][49] = 111;
$LLTable[60][8] = 112;$LLTable[60][16] = 112;$LLTable[60][24] = 112;$LLTable[60][31] = 112;$LLTable[60][32] = 112;$LLTable[60][34] = 112;$LLTable[60][35] = 112;$LLTable[60][44] = 112;$LLTable[60][46] = 112;$LLTable[60][49] = 112;
$LLTable[61][31] = 113;
$LLTable[62][37] = 115;$LLTable[62][38] = 114;




processTerm(50);

exit(0);


sub processTerm {
	my $sw = @_[0];
	if ( $FLAG_DEBUG_1 ) { print("Process Term:  ".$sw."\n"); }
	switch ( $sw ) {
		case 1 { #ActualParameter 
			executeRule(chooseRandomNextTerm(43));
		}
		case 2 { #ActualParameterTail 
			executeRule(chooseRandomNextTerm(42));
		}
		case 3 { #AddingOperator 
			executeRule(chooseRandomNextTerm(50));
		}
		case 4 { #AssignmentStatement 
			executeRule(chooseRandomNextTerm(30));
		}
		case 5 { #Block 
			executeRule(chooseRandomNextTerm(3));
		}
		case 6 { #BooleanExpression 
			executeRule(chooseRandomNextTerm(59));
		}
		case 7 { #CompoundStatement 
			executeRule(chooseRandomNextTerm(19));
		}
		case 8 { #ControlVariable 
			executeRule(chooseRandomNextTerm(36));
		}
		case 9 { #EmptyStatement 
			executeRule(chooseRandomNextTerm(23));
		}
		case 10 { #Expression 
			executeRule(chooseRandomNextTerm(44));
		}
		case 11 { #Factor 
			executeRule(chooseRandomNextTerm(54));
		}
		case 12 { #FactorTail 
			executeRule(chooseRandomNextTerm(52));
		}
		case 13 { #FinalValue 
			executeRule(chooseRandomNextTerm(39));
		}
		case 14 { #FormalParameterSection 
			executeRule(chooseRandomNextTerm(15));
		}
		case 15 { #FormalParameterSectionTail 
			executeRule(chooseRandomNextTerm(14));
		}
		case 16 { #ForStatement 
			executeRule(chooseRandomNextTerm(35));
		}
		case 17 { #FunctionDeclaration 
			executeRule(chooseRandomNextTerm(10));
		}
		case 18 { #FunctionHeading 
			executeRule(chooseRandomNextTerm(12));
		}
		case 19 { #FunctionIdentifier 
			executeRule(chooseRandomNextTerm(58));
		}
		case 20 { #IdentifierList 
			executeRule(chooseRandomNextTerm(61));
		}
		case 21 { #IdentifierTail 
			executeRule(chooseRandomNextTerm(62));
		}
		case 22 { #IfStatement 
			executeRule(chooseRandomNextTerm(31));
		}
		case 23 { #InitialValue 
			executeRule(chooseRandomNextTerm(37));
		}
		case 24 { #MultiplyingOperator 
			executeRule(chooseRandomNextTerm(53));
		}
		case 25 { #OptionalActualParameterList 
			executeRule(chooseRandomNextTerm(41));
		}
		case 26 { #OptionalElsePart 
			executeRule(chooseRandomNextTerm(32));
		}
		case 27 { #OptionalFormalParameterList 
			executeRule(chooseRandomNextTerm(13));
		}
		case 28 { #OptionalRelationalPart 
			executeRule(chooseRandomNextTerm(45));
		}
		case 29 { #OptionalSign 
			executeRule(chooseRandomNextTerm(49));
		}
		case 30 { #OrdinalExpression 
			executeRule(chooseRandomNextTerm(60));
		}
		case 31 { #ProcedureAndFunctionDeclarationPart 
			executeRule(chooseRandomNextTerm(8));
		}
		case 32 { #ProcedureDeclaration 
			executeRule(chooseRandomNextTerm(9));
		}
		case 33 { #ProcedureHeading 
			executeRule(chooseRandomNextTerm(11));
		}
		case 34 { #ProcedureIdentifier 
			executeRule(chooseRandomNextTerm(57));
		}
		case 35 { #ProcedureStatement 
			executeRule(chooseRandomNextTerm(40));
		}
		case 36 { #Program 
			executeRule(chooseRandomNextTerm(1));
		}
		case 37 { #ProgramHeading 
			executeRule(chooseRandomNextTerm(2));
		}
		case 38 { #ProgramIdentifier 
			executeRule(chooseRandomNextTerm(55));
		}
		case 39 { #ReadParameter 
			executeRule(chooseRandomNextTerm(26));
		}
		case 40 { #ReadParameterTail 
			executeRule(chooseRandomNextTerm(25));
		}
		case 41 { #ReadStatement 
			executeRule(chooseRandomNextTerm(24));
		}
		case 42 { #RelationalOperator 
			executeRule(chooseRandomNextTerm(46));
		}
		case 43 { #RepeatStatement 
			executeRule(chooseRandomNextTerm(33));
		}
		case 44 { #SimpleExpression 
			executeRule(chooseRandomNextTerm(47));
		}
		case 45 { #Statement 
			executeRule(chooseRandomNextTerm(22));
		}
		case 46 { #StatementPart 
			executeRule(chooseRandomNextTerm(18));
		}
		case 47 { #StatementSequence 
			executeRule(chooseRandomNextTerm(20));
		}
		case 48 { #StatementTail 
			executeRule(chooseRandomNextTerm(21));
		}
		case 49 { #StepValue 
			executeRule(chooseRandomNextTerm(38));
		}
		case 50 { #SystemGoal 
			executeRule(chooseRandomNextTerm(0));
		}
		case 51 { #Term 
			executeRule(chooseRandomNextTerm(51));
		}
		case 52 { #TermTail 
			executeRule(chooseRandomNextTerm(48));
		}
		case 53 { #Type 
			executeRule(chooseRandomNextTerm(7));
		}
		case 54 { #ValueParameterSection 
			executeRule(chooseRandomNextTerm(16));
		}
		case 55 { #VariableDeclaration 
			executeRule(chooseRandomNextTerm(6));
		}
		case 56 { #VariableDeclarationPart 
			executeRule(chooseRandomNextTerm(4));
		}
		case 57 { #VariableDeclarationTail 
			executeRule(chooseRandomNextTerm(5));
		}
		case 58 { #VariableIdentifier 
			executeRule(chooseRandomNextTerm(56));
		}
		case 59 { #VariableParameterSection 
			executeRule(chooseRandomNextTerm(17));
		}
		case 60 { #WhileStatement 
			executeRule(chooseRandomNextTerm(34));
		}
		case 61 { #WriteParameter 
			executeRule(chooseRandomNextTerm(29));
		}
		case 62 { #WriteParameterTail 
			executeRule(chooseRandomNextTerm(28));
		}
		case 63 { #WriteStatement 
			executeRule(chooseRandomNextTerm(27));
		}
		case 1001 { #- 
			print("-");
		}
		case 1002 { #( 
			print("(");
		}
		case 1003 { #) 
			print(")");
		}
		case 1004 { #, 
			print(",");			
		}
		case 1005 { #. 
			print(".");
		}
		case 1006 { #/ 
			print("/");
		}
		case 1007 { #: 
			print(":");
		}
		case 1008 { #:= 
			print(":=");
		}
		case 1009 { #; 
			print(";");
			printIndent();
		}
		case 1010 { #+ 
			print("+");
		}
		case 1011 { #< 
			print("<");
		}
		case 1012 { #<= 
			print("<=");
		}
		case 1013 { #= 
			print("=");
		}
		case 1014 { #> 
			print(">");
		}
		case 1015 { #>= 
			print(">=");
		}
		case 1016 { #and 
			print(" and");
		}
		case 1017 { #begin 
			printIndent();
			print("begin");
			$indent += 3;
			printIndent();
		}
		case 1018 { #Boolean 
			print(" boolean");
		}
		case 1019 { #div 
			print(" div");
		}
		case 1020 { #do 
			print(" do");
		}
		case 1021 { #downto 
			print(" downto");
		}
		case 1022 { #e 
		}
		case 1023 { #else 
			print(" else");
		}
		case 1024 { #end 
			$indent -= 3;
			printIndent();
			print("end");
		}
		case 1025 { #EOF 
		}
		case 1026 { #Float 
			print(" float");
		}
		case 1027 { #for 
			print(" for");
		}
		case 1028 { #function 
			printIndent();
			print("function");
			$indent += 3;
		}
		case 1029 { #Identifier 
			print(" ID");
		}
		case 1030 { #if 
			print(" if");
		}
		case 1031 { #Integer 
			print(" integer");
		}
		case 1032 { #mod 
			print(" mod");
		}
		case 1033 { #not 
			print(" not");
		}
		case 1034 { #or 
			print(" or");
		}
		case 1035 { #procedure
			printIndent();
			print("procedure");
			$indent += 3;
		}
		case 1036 { #read 
			print(" read");
		}
		case 1037 { #repeat 
			print(" repeat");
		}
		case 1038 { #String 
			print(" string");
		}
		case 1039 { #StringLiteral 
			print(" \"STRING\"");
		}
		case 1040 { #then 
			print(" then");
		}
		case 1041 { #to
			print(" to");
		}
		case 1042 { #UnsignedFloat 
			print(" 3.14159");
		}
		case 1043 { #UnsignedInteger 
			print(" 42");
		}
		case 1044 { #until 
			print(" until");
		}
		case 1045 { #var 
			print(" var");
		}
		case 1046 { #while 
			print(" while");
		}
		case 1047 { #write 
			print(" write");
		}
		case 1048 { #writeln 
			print(" writeln");
		}
		case 1049 { #FALSE 
			print(" false");
		}
		case 1050 { #TRUE 
			print(" true");
		}


		case 1051 { #<>
			print("<>");
		}
		case 1052 { #PROGRAM
			$indent += 3;
			print("program");
		}		
		case 1053 { #*
			print("*");
		}		
	}
}

sub printIndent() {
	print("\n");
	printf("%".$indent."s"," ");
}


sub executeRule {	
	my $sw = @_[0];
	if ( $FLAG_DEBUG_1 ) { print("Execute Rule:  ".$sw."\n"); }
	switch ( $sw ) {
		case 1 {
			processTerm(36);
			processTerm(1025);
		}

		case 2 {
			processTerm(37);
			processTerm(1009);
			processTerm(5);
			processTerm(1005);
		}

		case 3 {
			processTerm(1052);
			processTerm(38);
		}

		case 4 {
			processTerm(56);
			processTerm(31);
			processTerm(46);
		}

		case 5 {
			processTerm(1045);
			processTerm(55);
			processTerm(1009);
			processTerm(57);
		}

		case 6 {
			processTerm(1022);
		}

		case 7 {
			processTerm(55);
			processTerm(1009);
			processTerm(57);
		}

		case 8 {
			processTerm(1022);
		}

		case 9 {
			processTerm(20);
			processTerm(1007);
			processTerm(53);
		}

		case 10 {
			processTerm(1031);
		}

		case 11 {
			processTerm(1026);
		}

		case 12 {
			processTerm(1038);
		}

		case 13 {
			processTerm(1018);
		}

		case 14 {
			processTerm(32);
			processTerm(31);
		}

		case 15 {
			processTerm(17);
			processTerm(31);
		}

		case 16 {
			processTerm(1022);
		}

		case 17 {
			processTerm(33);
			processTerm(1009);
			processTerm(5);
			$indent -= 3;
			processTerm(1009);

		}

		case 18 {
			processTerm(18);
			processTerm(1009);
			processTerm(5);
			$indent -= 3;
			processTerm(1009);
		}

		case 19 {
			processTerm(1035);
			processTerm(34);
			processTerm(27);
		}

		case 20 {
			processTerm(1028);
			processTerm(19);
			processTerm(27);
			processTerm(1007);
			processTerm(53);
		}

		case 21 {
			processTerm(1002);
			processTerm(14);
			processTerm(15);
			processTerm(1003);
		}

		case 22 {
			processTerm(1022);
		}

		case 23 {
			processTerm(1009);
			processTerm(14);
			processTerm(15);
		}

		case 24 {
			processTerm(1022);
		}

		case 25 {
			processTerm(54);
		}

		case 26 {
			processTerm(59);
		}

		case 27 {
			processTerm(20);
			processTerm(1007);
			processTerm(53);
		}

		case 28 {
			processTerm(1045);
			processTerm(20);
			processTerm(1007);
			processTerm(53);
		}

		case 29 {
			processTerm(7);
		}

		case 30 {
			processTerm(1017);
			processTerm(47);
			processTerm(1024);
		}

		case 31 {
			processTerm(45);
			processTerm(48);
		}

		case 32 {
			processTerm(1009);
			processTerm(45);
			processTerm(48);
		}

		case 33 {
			processTerm(1022);
		}

		case 34 {
			processTerm(9);
		}

		case 35 {
			processTerm(7);
		}

		case 36 {
			processTerm(41);
		}

		case 37 {
			processTerm(63);
		}

		case 38 {
			processTerm(4);
		}

		case 39 {
			processTerm(22);
		}

		case 40 {
			processTerm(60);
		}

		case 41 {
			processTerm(43);
		}

		case 42 {
			processTerm(16);
		}

		case 43 {
			processTerm(35);
		}

		case 44 {
			processTerm(1022);
		}

		case 45 {
			processTerm(1036);
			processTerm(1002);
			processTerm(39);
			processTerm(40);
			processTerm(1003);
		}

		case 46 {
			processTerm(1004);
			processTerm(39);
			processTerm(40);
		}

		case 47 {
			processTerm(1022);
		}

		case 48 {
			processTerm(58);
		}

		case 49 {
			processTerm(1047);
			processTerm(1002);
			processTerm(61);
			processTerm(62);
			processTerm(1003);
		}

		case 50 {
			processTerm(1048);
			processTerm(1002);
			processTerm(61);
			processTerm(62);
			processTerm(1003);
		}

		case 51 {
			processTerm(1004);
			processTerm(61);
			processTerm(62);
		}

		case 52 {
			processTerm(1022);
		}

		case 53 {
			processTerm(30);
		}

		case 54 {
			processTerm(58);
			processTerm(1008);
			processTerm(10);
		}

		case 55 {
			processTerm(19);
			processTerm(1008);
			processTerm(10);
		}

		case 56 {
			processTerm(1030);
			processTerm(6);
			processTerm(1040);
			processTerm(45);
			processTerm(26);
		}

		case 57 {
			processTerm(1023);
			processTerm(45);
		}

		case 58 {
			processTerm(1022);
		}

		case 59 {
			processTerm(1037);
			processTerm(47);
			processTerm(1044);
			processTerm(6);
		}

		case 60 {
			processTerm(1046);
			processTerm(6);
			processTerm(1020);
			processTerm(45);
		}

		case 61 {
			processTerm(1027);
			processTerm(8);
			processTerm(1008);
			processTerm(23);
			processTerm(49);
			processTerm(13);
			processTerm(1020);
			processTerm(45);
		}

		case 62 {
			processTerm(58);
		}

		case 63 {
			processTerm(30);
		}

		case 64 {
			processTerm(1041);
		}

		case 65 {
			processTerm(1021);
		}

		case 66 {
			processTerm(30);
		}

		case 67 {
			processTerm(34);
			processTerm(25);
		}

		case 68 {
			processTerm(1002);
			processTerm(1);
			processTerm(2);
			processTerm(1003);
		}

		case 69 {
			processTerm(1022);
		}

		case 70 {
			processTerm(1004);
			processTerm(1);
			processTerm(2);
		}

		case 71 {
			processTerm(1022);
		}

		case 72 {
			processTerm(30);
		}

		case 73 {
			processTerm(44);
			processTerm(28);
		}

		case 74 {
			processTerm(42);
			processTerm(44);
		}

		case 75 {
			processTerm(1022);
		}

		case 76 {
			processTerm(1013);
		}

		case 77 {
			processTerm(1011);
		}

		case 78 {
			processTerm(1014);
		}

		case 79 {
			processTerm(1012);
		}

		case 80 {
			processTerm(1015);
		}

		case 81 {
			processTerm(1051);
		}

		case 82 {
			processTerm(29);
			processTerm(51);
			processTerm(52);
		}

		case 83 {
			processTerm(3);
			processTerm(51);
			processTerm(52);
		}

		case 84 {
			processTerm(1022);
		}

		case 85 {
			processTerm(1010);
		}

		case 86 {
			processTerm(1001);
		}

		case 87 {
			processTerm(1022);
		}

		case 88 {
			processTerm(1010);
		}

		case 89 {
			processTerm(1001);
		}

		case 90 {
			processTerm(1034);
		}

		case 91 {
			processTerm(11);
			processTerm(12);
		}

		case 92 {
			processTerm(24);
			processTerm(11);
			processTerm(12);
		}

		case 93 {
			processTerm(1022);
		}

		case 94 {
			processTerm(1053);
		}

		case 95 {
			processTerm(1006);
		}

		case 96 {
			processTerm(1019);
		}

		case 97 {
			processTerm(1032);
		}

		case 98 {
			processTerm(1016);
		}

		case 99 {
			processTerm(1043);
		}

		case 100 {
			processTerm(1042);
		}

		case 101 {
			processTerm(1039);
		}

		case 102 {
			processTerm(1050);
		}

		case 103 {
			processTerm(1049);
		}

		case 104 {
			processTerm(1033);
			processTerm(11);
		}

		case 105 {
			processTerm(1002);
			processTerm(10);
			processTerm(1003);
		}

		case 106 {
			processTerm(19);
			processTerm(25);
		}

		case 107 {
			processTerm(1029);
		}

		case 108 {
			processTerm(1029);
		}

		case 109 {
			processTerm(1029);
		}

		case 110 {
			processTerm(1029);
		}

		case 111 {
			processTerm(10);
		}

		case 112 {
			processTerm(10);
		}

		case 113 {
			processTerm(1029);
			processTerm(21);
		}

		case 114 {
			processTerm(1004);
			processTerm(1029);
			processTerm(21);
		}

		case 115 {
			processTerm(1022);
		}
	}
}

sub chooseRandomNextTerm {
	my $Row = @_[0];
	if ( $FLAG_DEBUG_1 ) { print("Selecting a Rule for:  ".$Row."\n"); }
	
	# SPECIAL CASE: Non-Terminal #8, ProcedureAndFunctionDeclarationPart.  If the recursion level is too high, manually force it to return the empty string for the sake of program termination.  (It tends to enter an infinite sequence of nested procedure/function declarations.
	if ( $Row == 8 && $indent > $RecLimit ) { 
		$RecLimit -= 1;
		return 16; 
	}
	
	my $testInd;
	do {
		$testInd = int(rand()*57);
		if ( $FLAG_DEBUG_1 ) { print("Trying: ".$testInd."     == ".$LLTable[$Row][$testInd]."\n"); }
	} while ( $LLTable[$Row][$testInd] == undef );
	
	return $LLTable[$Row][$testInd];
}