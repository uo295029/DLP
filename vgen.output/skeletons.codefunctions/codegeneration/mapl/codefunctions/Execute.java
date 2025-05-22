// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.statement.*;
import codegeneration.mapl.*;


public class Execute extends AbstractCodeFunction {

    public Execute(MaplCodeSpecification specification) {
        super(specification);
    }


	// class Assignment(Expression left, Expression right)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Assignment assignment, Object param) {

		// value(assignment.getLeft());
		// address(assignment.getLeft());

		// value(assignment.getRight());
		// address(assignment.getRight());

		out("<instruction>");

		return null;
	}

	// class FunctionCallS(String name, List<Expression> params)
	// phase Identification { FunctionDefinition functionDefinition }
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(FunctionCallS functionCallS, Object param) {

		// value(functionCallS.params());
		// address(functionCallS.params());

		out("<instruction>");

		return null;
	}

	// class Return(Optional<Expression> expression)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Return returnValue, Object param) {

		// value(returnValue.getExpression());
		// address(returnValue.getExpression());

		out("<instruction>");

		return null;
	}

	// class If(Expression condition, List<Statement> yes, List<Statement> no)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(If ifValue, Object param) {

		// value(ifValue.getCondition());
		// address(ifValue.getCondition());

		// execute(ifValue.yes());

		// execute(ifValue.no());

		out("<instruction>");

		return null;
	}

	// class While(Expression condition, List<Statement> yes)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(While whileValue, Object param) {

		// value(whileValue.getCondition());
		// address(whileValue.getCondition());

		// execute(whileValue.yes());

		out("<instruction>");

		return null;
	}

	// class For(VarDefinition varDefinition, Expression condition, Statement increment, List<Statement> statements)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(For forValue, Object param) {

		// metadata(forValue.getVarDefinition());

		// value(forValue.getCondition());
		// address(forValue.getCondition());

		// execute(forValue.getIncrement());

		// execute(forValue.statements());

		out("<instruction>");

		return null;
	}

	// class Print(List<Expression> expressions)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Print print, Object param) {

		// value(print.expressions());
		// address(print.expressions());

		out("<instruction>");

		return null;
	}

	// class Printsp(List<Expression> expressions)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Printsp printsp, Object param) {

		// value(printsp.expressions());
		// address(printsp.expressions());

		out("<instruction>");

		return null;
	}

	// class Println(List<Expression> expressions)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Println println, Object param) {

		// value(println.expressions());
		// address(println.expressions());

		out("<instruction>");

		return null;
	}

	// class Read(Expression expression)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Read read, Object param) {

		// value(read.getExpression());
		// address(read.getExpression());

		out("<instruction>");

		return null;
	}

}
