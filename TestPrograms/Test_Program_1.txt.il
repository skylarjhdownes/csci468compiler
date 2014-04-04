BR L_Prog_lab1

L_Function_fred:
POP -1(SP)
PUSH D0
MOV SP D0
ADD SP #3
POP 2(D0)
SUB SP #3
PUSH 2(D0)
MOV D0 3(D0)
RET
{End block}


L_Function_mary:
POP -1(SP)
PUSH D0
MOV SP D0
ADD SP #3
POP 2(D0)
SUB SP #3
PUSH 2(D0)
MOV D0 3(D0)
RET
{End block}


L_Procedure_mixit:
POP -3(SP)
POP -3(SP)
POP -3(SP)
PUSH D0
MOV SP D0
ADD SP #7
POP 4(D0)
SUB SP #7
MOV D0 7(D0)
RET
{End block}


L_Prog_lab1:
POP -5(SP)
PUSH D0
MOV SP D0
ADD SP #7
RD 1(D0)
RDF 3(D0)
WRT 
SUB SP #7
MOV D0 7(D0)
{End block}

HLT
