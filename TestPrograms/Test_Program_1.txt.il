_L_Prog_lab1:

_L_Procedure_mixit:

_L_Function_fred:
POP -1(SP)
PUSH D0
MOV SP D0
ADD SP #3
SUB SP #3
MOV D0 3(D0)
{End block}


_L_Function_mary:
POP -1(SP)
PUSH D0
MOV SP D0
ADD SP #3
SUB SP #3
MOV D0 3(D0)
{End block}

PUSH D0
MOV SP D0
ADD SP #7
SUB SP #7
MOV D0 7(D0)
{End block}

PUSH D0
MOV SP D0
ADD SP #7
SUB SP #7
MOV D0 7(D0)
{End block}

HLT
