// Generated with VGen 2.0.0

/**
* El objetivo de esta clase DefaultVisitor es ser la implementación base de la que
* deriven todos los demás visitor.
*
* Sus métodos visit recorren los hijos del nodo que se está visitando. Por tanto,
* todo visitor que derive de este ya tendrá implementado todo el código de
* recorrido del AST.
*
* Para crear nuevos visitor, en vez de modificar esta clase, se recomienda copiar
* 'SkeletonForNewVisitors.java' o, mejor aún, definir una nueva PHASE en la gramática
* y usar el visitor generado para la misma.
*/

package visitor;

import ast.*;
import ast.definition.*;
import ast.statement.*;
import ast.expression.*;
import ast.type.*;




public class DefaultVisitor implements Visitor {
	@Override
	public Object visit(Program program, Object param) {

		program.getDefinitions().forEach(definition -> definition.accept(this, param));
		return null;
	}

	@Override
	public Object visit(VarDefinition varDefinition, Object param) {

		varDefinition.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {

		functionDefinition.getParams().forEach(varDefinition -> varDefinition.accept(this, param));
		functionDefinition.getType().accept(this, param);
		functionDefinition.getV().forEach(varDefinition -> varDefinition.accept(this, param));
		functionDefinition.getS().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(StructDefinition structDefinition, Object param) {

		structDefinition.getAttributes().forEach(attribute -> attribute.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Attribute attribute, Object param) {

		attribute.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Case caseValue, Object param) {

		caseValue.getCondition().accept(this, param);
		caseValue.getStatements().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {

		assignment.getLeft().accept(this, param);
		assignment.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunctionCallS functionCallS, Object param) {

		functionCallS.getParams().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Return returnValue, Object param) {

		returnValue.getExpression().ifPresent(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(If ifValue, Object param) {

		ifValue.getCondition().accept(this, param);
		ifValue.getYes().forEach(statement -> statement.accept(this, param));
		ifValue.getNo().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(While whileValue, Object param) {

		whileValue.getCondition().accept(this, param);
		whileValue.getYes().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Switch switchValue, Object param) {

		switchValue.getConditioner().accept(this, param);
		switchValue.getCases().forEach(caseValue -> caseValue.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Print print, Object param) {

		print.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Printsp printsp, Object param) {

		printsp.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Println println, Object param) {

		println.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {

		read.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {

		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {

		return null;
	}

	@Override
	public Object visit(FloatLiteral floatLiteral, Object param) {

		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {

		return null;
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {

		arithmetic.getLeft().accept(this, param);
		arithmetic.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Negate negate, Object param) {

		negate.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Logic logic, Object param) {

		logic.getLeft().accept(this, param);
		logic.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(AttributeAccess attributeAccess, Object param) {

		attributeAccess.getLeft().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object param) {

		comparison.getLeft().accept(this, param);
		comparison.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		arrayAccess.getLeft().accept(this, param);
		arrayAccess.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {

		cast.getCastType().accept(this, param);
		cast.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunctionCallE functionCallE, Object param) {

		functionCallE.getParams().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(IntType intType, Object param) {

		return null;
	}

	@Override
	public Object visit(FloatType floatType, Object param) {

		return null;
	}

	@Override
	public Object visit(CharType charType, Object param) {

		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {

		arrayType.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(StructType structType, Object param) {

		return null;
	}

	@Override
	public Object visit(VoidType voidType, Object param) {

		return null;
	}


}
