lexer grammar Tokenizer;

ID: [a-zA-Z][a-zA-Z0-9_]*;

INT_LITERAL: [0-9]+;

FLOAT_LITERAL: [0-9]+'.'[0-9]+;

CHAR_LITERAL: '\'' ~[\n\r] '\''
	| '\'' '\\n' '\'';

LINE_COMMENT: '//' .*? ('\n' | EOF) -> skip;

MULTILINE_COMMENT: '/*' .*? '*/' -> skip;

WHITESPACE: [ \t\r\n]+ -> skip;
