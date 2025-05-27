grammar Grammar;

import Tokenizer;

@header {
	    import ast.type.*;
	    import ast.statement.*;
	    import ast.expression.*;
	    import ast.definition.*;
	    import ast.*;
}


program returns[Program ast]
	: defs+=definition* EOF { $ast = new Program($defs); }
	;

definition returns[Definition ast]
	: v=varDefinition { $ast = $v.ast; }
	| s=structDefinition { $ast = $s.ast; }
	| f=functionDefinition { $ast = $f.ast; }
	;

varDefinition returns[VarDefinition ast]
	: 'var' i=ID ':' t=type ';'	{ $ast = new VarDefinition($i, $t.ast); }
	;

functionDefinition returns[FunctionDefinition ast]
	: i=ID '(' p+=param* ')' (':' t=type)? '{' v+=varDefinition* s+=statement* '}' { $ast = new FunctionDefinition($i, $p, ($t.ctx == null) ? new VoidType() : $t.ast, $v, $s); }
	;

structDefinition returns[StructDefinition ast]
	: 'struct' i=ID '{' sa=struct_attributes '}'	{ $ast = new StructDefinition($i, $sa.ast); }
	;

param returns[VarDefinition ast]
	: (',')? i=ID ':' t=type { $ast = new VarDefinition($i, $t.ast); }
	;
	
statement returns[Statement ast]
	: i=ID '(' (args+=expression (',' args+=expression)*)? ')'';'											{ $ast = new FunctionCallS($i, $args); }
	| 'print' e+=expression? (',' e+=expression)* ';'														{ $ast = new Print($e); $ast.updatePositions($ctx.start); }
	| 'printsp' e+=expression? (',' e+=expression)* ';'														{ $ast = new Printsp($e); $ast.updatePositions($ctx.start); }
	| 'println' e+=expression? (',' e+=expression)* ';'														{ $ast = new Println($e); $ast.updatePositions($ctx.start); }
	| 'return' expression? ';'																		      	{ $ast = new Return(($expression.ctx == null) ? null : $expression.ast); $ast.updatePositions($ctx.start); }
	| 'read' expression ';'																					{ $ast = new Read($expression.ast); }
	| e1=expression '=' e2=expression';'																	{ $ast = new Assignment($e1.ast, $e2.ast); }
	| 'if' '('expression')' '{' s1+=statement* '}' ('else' '{' s2+=statement* '}')?							{ $ast = new If($expression.ast, $s1, $s2); }
	| 'while' '('expression')' '{' s+=statement* '}'														{ $ast = new While($expression.ast, $s); }
	| 'for' '(' v=varDefinition e1=expression ';' e2=expression '=' e3=expression ')' '{' s+=statement* '}'	{ $ast = new For($v.ast, $e1.ast, new Assignment($e2.ast, $e3.ast), $s); }
	;
	
expression returns[Expression ast]
	: i=ID '(' (args+=expression (',' args+=expression)*)? ')'	{ $ast = new FunctionCallE($i, $args); }
	| '(' expression ')'										{ $ast = $expression.ast; }
	| '<' t=type '>' '(' e=expression ')'						{ $ast = new Cast($t.ast, $e.ast); }
	| e1=expression '[' e2=expression ']' 						{ $ast = new ArrayAccess($e1.ast, $e2.ast); }
	| e=expression '.' i=ID										{ $ast = new AttributeAccess($e.ast, $i); }
	| '!' e=expression											{ $ast = new Negate($e.ast); }
	| e1=expression op=('*'|'/'|'%') e2=expression				{ $ast = new Arithmetic($e1.ast, $op, $e2.ast); }
	| e1=expression op=('+'|'-') e2=expression					{ $ast = new Arithmetic($e1.ast, $op, $e2.ast); }
	| e1=expression op=('<'|'>'|'<='|'>=') e2=expression		{ $ast = new Comparison($e1.ast, $op, $e2.ast); }
	| e1=expression op=('=='|'!=') e2=expression				{ $ast = new Comparison($e1.ast, $op, $e2.ast); }
	| e1=expression op='&&' e2=expression						{ $ast = new Logic($e1.ast, $op, $e2.ast); }
	| e1=expression op='||' e2=expression						{ $ast = new Logic($e1.ast, $op, $e2.ast); }
	| INT_LITERAL												{ $ast = new IntLiteral($INT_LITERAL); }
	| FLOAT_LITERAL												{ $ast = new FloatLiteral($FLOAT_LITERAL); }
	| CHAR_LITERAL												{ $ast = new CharLiteral($CHAR_LITERAL); }
	| i=ID														{ $ast = new Variable($i); }
	;
	
type returns[Type ast]
	: 'int' 					{ $ast = new IntType(); }
	| 'float'					{ $ast = new FloatType(); }
	| 'char'					{ $ast = new CharType(); }
	| i=ID						{ $ast = new StructType($i); }
	| '[' INT_LITERAL ']' type  { $ast = new ArrayType($INT_LITERAL, $type.ast); }
	;
	
struct_attributes returns[List<Attribute> ast = new ArrayList<Attribute>()]
	: (i=ID ':' t=type ';' { $ast.add(new Attribute($i, $t.ast)); })* 
	;