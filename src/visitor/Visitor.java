// Generated with VGen 2.0.0

/**
* Este interfaz es el resultado de la aplicación del patrón Visitor a los nodos del AST.
*/

package visitor;

import ast.*;
import ast.definition.*;
import ast.statement.*;
import ast.expression.*;
import ast.type.*;




public interface Visitor {
	public Object visit(Program program, Object param);

	public Object visit(VarDefinition varDefinition, Object param);

	public Object visit(FunctionDefinition functionDefinition, Object param);

	public Object visit(StructDefinition structDefinition, Object param);

	public Object visit(Attribute attribute, Object param);

	public Object visit(Assignment assignment, Object param);

	public Object visit(FunctionCallS functionCallS, Object param);

	public Object visit(Return returnValue, Object param);

	public Object visit(If ifValue, Object param);

	public Object visit(While whileValue, Object param);

	public Object visit(Print print, Object param);

	public Object visit(Printsp printsp, Object param);

	public Object visit(Println println, Object param);

	public Object visit(Read read, Object param);

	public Object visit(Variable variable, Object param);

	public Object visit(IntLiteral intLiteral, Object param);

	public Object visit(FloatLiteral floatLiteral, Object param);

	public Object visit(CharLiteral charLiteral, Object param);

	public Object visit(Arithmetic arithmetic, Object param);

	public Object visit(Negate negate, Object param);

	public Object visit(Logic logic, Object param);

	public Object visit(AttributeAccess attributeAccess, Object param);

	public Object visit(Comparison comparison, Object param);

	public Object visit(ArrayAccess arrayAccess, Object param);

	public Object visit(Cast cast, Object param);

	public Object visit(FunctionCallE functionCallE, Object param);

	public Object visit(IntType intType, Object param);

	public Object visit(FloatType floatType, Object param);

	public Object visit(CharType charType, Object param);

	public Object visit(ArrayType arrayType, Object param);

	public Object visit(StructType structType, Object param);

	public Object visit(VoidType voidType, Object param);


}
