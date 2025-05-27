// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import java.util.HashMap;
import java.util.Map;

import ast.expression.*;
import codegeneration.mapl.*;


public class Value extends AbstractCodeFunction {

	private Map<String, String> instruccion = new HashMap<>();
	
    public Value(MaplCodeSpecification specification) {
        super(specification);
        
        instruccion.put("+", "add");
		instruccion.put("-", "sub");
		instruccion.put("*", "mul");
		instruccion.put("/", "div");
		instruccion.put("%", "mod");

		instruccion.put("&&", "and");
		instruccion.put("||", "or");
		instruccion.put("!", "not");

		instruccion.put(">", "gt");
		instruccion.put("<", "lt");
		instruccion.put(">=", "ge");
		instruccion.put("<=", "le");
		instruccion.put("==", "eq");
		instruccion.put("!=", "ne");
    }

	@Override
	public Object visit(Variable variable, Object param) {

		address(variable);
		out("load" + getSuffix(variable.getType()));

		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {

		out("pushi " + intLiteral.getValue());
		
		return null;
	}

	@Override
	public Object visit(FloatLiteral floatLiteral, Object param) {

		out("pushf " + floatLiteral.getValue());

		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {

		String literal = charLiteral.getValue().split("'")[1];
		if (literal.equalsIgnoreCase("\\n")) out("pushb 10");
		else if (literal.equalsIgnoreCase("\\t")) out("pushb 9");
		else out("pushb " + literal.hashCode());

		return null;
	}
	
	@Override
	public Object visit(BoolLiteral boolLiteral, Object param) {
		
		int value = boolLiteral.getValue().equals("true") ? 1 : 0;
		out("pushi " + value);

		return null;
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {

		value(arithmetic.getLeft());
		value(arithmetic.getRight());
		out(instruccion.get(arithmetic.getOperator()) + getSuffix(arithmetic.getType()));

		return null;
	}

	@Override
	public Object visit(Negate negate, Object param) {

		value(negate.getExpression());
		out("not");

		return null;
	}

	@Override
	public Object visit(Logic logic, Object param) {

		value(logic.getLeft());
		value(logic.getRight());
		out(instruccion.get(logic.getOperator()));

		return null;
	}
	
	@Override
	public Object visit(AttributeAccess attributeAccess, Object param) {

		address(attributeAccess);
		out("load" + getSuffix(attributeAccess.getType()));

		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object param) {

		value(comparison.getLeft());
		value(comparison.getRight());
		out(instruccion.get(comparison.getOperator()) + getSuffix(comparison.getLeft().getType()));

		return null;
	}

	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		address(arrayAccess);
		out("load" + getSuffix(arrayAccess.getType()));
		
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {

		value(cast.getExpression());
		convertTo(cast.getExpression().getType(), cast.getType());

		return null;
	}

	@Override
	public Object visit(FunctionCallE functionCallE, Object param) {

		for (Expression expression:functionCallE.getParams()) {
			value(expression);
		}
		out("call " + functionCallE.getName());

		return null;
	}

}
