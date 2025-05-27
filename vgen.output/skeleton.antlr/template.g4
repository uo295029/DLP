// Generated with VGen 2.0.0

grammar Grammar;

@header {
	    import ast.type.*;
	    import ast.statement.*;
	    import ast.expression.*;
	    import ast.definition.*;
	    import ast.*;
}

program returns[Program ast]
    : definitions+=definition*            { $ast = new Program($definitions); }                  
	;

definition returns[Definition ast]
    : name=IDENT type                     { $ast = new VarDefinition($name, $type.ast); }        
    | name=IDENT params+=varDefinition* type v+=varDefinition* s+=statement* { $ast = new FunctionDefinition($name, $params, $type.ast, $v, $s); }
    | name=IDENT attributes+=attribute*   { $ast = new StructDefinition($name, $attributes); }   
	;

type returns[Type ast]
    :                                     { $ast = new IntType(); }                              
    |                                     { $ast = new FloatType(); }                            
    |                                     { $ast = new CharType(); }                             
    |                                     { $ast = new BoolType(); }                             
    | INT_LITERAL type                    { $ast = new ArrayType($INT_LITERAL, $type.ast); }     
    | name=IDENT                          { $ast = new StructType($name); }                      
    |                                     { $ast = new VoidType(); }                             
	;

varDefinition returns[VarDefinition ast]
    : name=IDENT type                     { $ast = new VarDefinition($name, $type.ast); }        
	;

statement returns[Statement ast]
    : left=expression right=expression    { $ast = new Assignment($left.ast, $right.ast); }      
    | name=IDENT params+=expression*      { $ast = new FunctionCallS($name, $params); }          
    | expression?                         { $ast = new Return(($expression.ctx == null) ? null : $expression.ast); }
    | expression yes+=statement* no+=statement* { $ast = new If($expression.ast, $yes, $no); }         
    | expression yes+=statement*          { $ast = new While($expression.ast, $yes); }           
    | expressions+=expression*            { $ast = new Print($expressions); }                    
    | expressions+=expression*            { $ast = new Printsp($expressions); }                  
    | expressions+=expression*            { $ast = new Println($expressions); }                  
    | expression                          { $ast = new Read($expression.ast); }                  
	;

expression returns[Expression ast]
    : name=IDENT                          { $ast = new Variable($name); }                        
    | value=IDENT                         { $ast = new IntLiteral($value); }                     
    | value=IDENT                         { $ast = new FloatLiteral($value); }                   
    | value=IDENT                         { $ast = new CharLiteral($value); }                    
    | value=IDENT                         { $ast = new BoolLiteral($value); }                    
    | left=expression operator=IDENT right=expression { $ast = new Arithmetic($left.ast, $operator, $right.ast); }
    | expression                          { $ast = new Negate($expression.ast); }                
    | left=expression operator=IDENT right=expression { $ast = new Logic($left.ast, $operator, $right.ast); }
    | expression right=IDENT              { $ast = new AttributeAccess($expression.ast, $right); }
    | left=expression operator=IDENT right=expression { $ast = new Comparison($left.ast, $operator, $right.ast); }
    | left=expression right=expression    { $ast = new ArrayAccess($left.ast, $right.ast); }     
    | type expression                     { $ast = new Cast($type.ast, $expression.ast); }       
    | name=IDENT params+=expression*      { $ast = new FunctionCallE($name, $params); }          
	;

attribute returns[Attribute ast]
    : name=IDENT type                     { $ast = new Attribute($name, $type.ast); }            
	;


// ---------------------------------------------------------------
// Tokens

IDENT: [a-zA-Z_][a-zA-Z0-9_]*;
INT_LITERAL: [0-9]+;
