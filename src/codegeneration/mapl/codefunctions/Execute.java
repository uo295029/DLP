// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.definition.FunctionDefinition;
import ast.expression.Expression;
import ast.statement.*;
import ast.type.*;
import codegeneration.mapl.*;


public class Execute extends AbstractCodeFunction {

	private int label = 0;
	
    public Execute(MaplCodeSpecification specification) {
        super(specification);
    }

    
    @Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {
		out("#LINE " + functionDefinition.start().getLine());
		out("\t" + functionDefinition.getName() + ":");
		out("enter " + functionDefinition.getVarsSize());
		for (Statement statement : functionDefinition.getS()) {
			execute(statement);
		}
		if (functionDefinition.getType() instanceof VoidType) {
			out("ret\t" + 0 + "," + functionDefinition.getVarsSize() + "," + functionDefinition.getParamsSize());
		}

		return null;
	}

	// class Assignment(Expression left, Expression right)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Assignment assignment, Object param) {

		out("#LINE " + assignment.end().getLine());
		address(assignment.getLeft());
		value(assignment.getRight());
		out("store" + getSuffix(assignment.getLeft().getType()));
		
		return null;
	}

	// class FunctionCallS(String name, List<Expression> params)
	// phase Identification { FunctionDefinition functionDefinition }
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(FunctionCallS functionCallS, Object param) {

		out("#LINE " + functionCallS.end().getLine());
		for (Expression expression: functionCallS.getParams()) {
			value(expression);
		}
		out("call " + functionCallS.getName());
		if (!(functionCallS.getFunctionDefinition().getType() instanceof VoidType)) {
			out("pop" + getSuffix(functionCallS.getFunctionDefinition().getType()));
		}

		return null;
	}

	// class Return(Optional<Expression> expression)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Return returnValue, Object param) {

		out("#LINE " + returnValue.end().getLine());
		Expression exp = null;
		int bytes = 0;
		if (returnValue.getExpression().isPresent()) {
			exp = returnValue.getExpression().get();
		}
		if (exp != null) {
			value(exp);
			bytes = exp.getType().getBytes();
		}
		out("ret\t" + bytes + "," +
				returnValue.getFunction().getVarsSize() + "," +
				returnValue.getFunction().getParamsSize());

		return null;
	}

	// class If(Expression condition, List<Statement> yes, List<Statement> no)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(If ifValue, Object param) {

		String falseLabel = "label" + getLabel();
		String endLabel = "label" + getLabel();
		out("#LINE " + ifValue.getCondition().end().getLine());
		value(ifValue.getCondition());
		out("jz " + falseLabel);
		for (Statement statement : ifValue.getYes()) {
			execute(statement);
		}
		out("jmp " + endLabel);
		out(falseLabel + ":");
		for (Statement statement: ifValue.getNo()) {
			execute(statement);
		}
		out(endLabel + ":");

		return null;
	}

	// class While(Expression condition, List<Statement> yes)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(While whileValue, Object param) {

		String condition = "label" + getLabel();
		String end = "label" + getLabel();
		out(condition + ":");
		out("#LINE " + whileValue.getCondition().end().getLine());
		value(whileValue.getCondition());
		out("jz " + end);
		for (Statement statement : whileValue.getYes()) {
			execute(statement);
		}
		out("jmp " + condition);
		out(end + ":");

		return null;
	}
	
	// class For(VarDefinition varDefinition, Expression condition, Statement increment, List<Statement> statements)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(For f, Object param) {

		String condition = "label" + getLabel();
		String end = "label" + getLabel();
		out(condition + ":");
		out("#LINE " + f.getCondition().end().getLine());
		value(f.getCondition());
		out("jz " + end);
		for (Statement statement : f.getStatements()) {
			execute(statement);
		}
		execute(f.getIncrement());
		out("jmp " + condition);
		out(end + ":");

		return null;
	}

	// class Print(List<Expression> expressions)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Print print, Object param) {

		out("#LINE " + print.end().getLine());
		for (Expression expression : print.getExpressions()) {
			value(expression);
			out("out" + getSuffix(expression.getType()));
		}

		return null;
	}

	// class Printsp(List<Expression> expressions)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Printsp printsp, Object param) {

		out("#LINE " + printsp.end().getLine());
		if (printsp.getExpressions().isEmpty()) {
			out("pushb 32");
			out("outb");
		}
		for (Expression expression : printsp.getExpressions()) {
			value(expression);
			out("out" + getSuffix(expression.getType()));
			out("pushb 32");
			out("outb");
		}

		return null;
	}

	// class Println(List<Expression> expressions)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Println println, Object param) {

		out("#LINE " + println.end().getLine());
		if (println.getExpressions().isEmpty()) {
			out("pushb 10");
			out("outb");
		}
		for (Expression expression : println.getExpressions()) {
			value(expression);
			out("out" + getSuffix(expression.getType()));
			out("pushb 10");
			out("outb");
		}

		return null;
	}

	// class Read(Expression expression)
	// phase TypeChecking { FunctionDefinition function }
	@Override
	public Object visit(Read read, Object param) {

		out("#LINE " + read.end().getLine());

		address(read.getExpression());
		out("in" + getSuffix(read.getExpression().getType()));
		out("store" + getSuffix(read.getExpression().getType()));

		return null;
	}

	private int getLabel() {
		return ++label;
	}
}
