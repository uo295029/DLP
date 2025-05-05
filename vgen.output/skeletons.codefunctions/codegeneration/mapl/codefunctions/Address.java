// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.expression.*;
import codegeneration.mapl.*;


public class Address extends AbstractCodeFunction {

    public Address(MaplCodeSpecification specification) {
        super(specification);
    }


	// class Variable(String name)
	// phase Identification { VarDefinition varDefinition }
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(Variable variable, Object param) {

		out("<instruction>");

		return null;
	}

	// class IntLiteral(String value)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(IntLiteral intLiteral, Object param) {

		out("<instruction>");

		return null;
	}

	// class FloatLiteral(String value)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(FloatLiteral floatLiteral, Object param) {

		out("<instruction>");

		return null;
	}

	// class CharLiteral(String value)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(CharLiteral charLiteral, Object param) {

		out("<instruction>");

		return null;
	}

	// class Arithmetic(Expression left, String operator, Expression right)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(Arithmetic arithmetic, Object param) {

		// value(arithmetic.getLeft());
		// address(arithmetic.getLeft());

		// value(arithmetic.getRight());
		// address(arithmetic.getRight());

		out("<instruction>");

		return null;
	}

	// class Negate(Expression expression)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(Negate negate, Object param) {

		// value(negate.getExpression());
		// address(negate.getExpression());

		out("<instruction>");

		return null;
	}

	// class Logic(Expression left, String operator, Expression right)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(Logic logic, Object param) {

		// value(logic.getLeft());
		// address(logic.getLeft());

		// value(logic.getRight());
		// address(logic.getRight());

		out("<instruction>");

		return null;
	}

	// class AttributeAccess(Expression left, String right)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(AttributeAccess attributeAccess, Object param) {

		// value(attributeAccess.getLeft());
		// address(attributeAccess.getLeft());

		out("<instruction>");

		return null;
	}

	// class Comparison(Expression left, String operator, Expression right)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(Comparison comparison, Object param) {

		// value(comparison.getLeft());
		// address(comparison.getLeft());

		// value(comparison.getRight());
		// address(comparison.getRight());

		out("<instruction>");

		return null;
	}

	// class ArrayAccess(Expression left, Expression right)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		// value(arrayAccess.getLeft());
		// address(arrayAccess.getLeft());

		// value(arrayAccess.getRight());
		// address(arrayAccess.getRight());

		out("<instruction>");

		return null;
	}

	// class Cast(Type castType, Expression expression)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(Cast cast, Object param) {

		// value(cast.getExpression());
		// address(cast.getExpression());

		out("<instruction>");

		return null;
	}

	// class FunctionCallE(String name, List<Expression> params)
	// phase Identification { FunctionDefinition functionDefinition }
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(FunctionCallE functionCallE, Object param) {

		// value(functionCallE.params());
		// address(functionCallE.params());

		out("<instruction>");

		return null;
	}

}
