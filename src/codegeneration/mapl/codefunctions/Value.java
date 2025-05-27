// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import java.util.HashMap;
import java.util.Map;

import ast.expression.*;
import ast.type.*;
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
	public Object visit(Arithmetic arithmetic, Object param) {

		value(arithmetic.getLeft());
		convertTo(arithmetic.getLeft().getType(), arithmetic.getType());
		value(arithmetic.getRight());
		convertTo(arithmetic.getRight().getType(), arithmetic.getType());
		out(instruccion.get(arithmetic.getOperator()) + getSuffix(arithmetic.getType()));

		return null;
	}

	@Override
	public Object visit(Negate negate, Object param) {

		value(negate.getExpression());
		convertTo(negate.getExpression().getType(), new IntType());
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

		boolean isFloat = comparison.getLeft().getType() instanceof FloatType || comparison.getRight().getType() instanceof FloatType;
		
		value(comparison.getLeft());
		if(isFloat) convertTo(comparison.getLeft().getType(), new FloatType());
		else convertTo(comparison.getLeft().getType(), new IntType());
		
		value(comparison.getRight());
		if(isFloat) convertTo(comparison.getRight().getType(), new FloatType());
		else convertTo(comparison.getRight().getType(), new IntType());
		
		out(instruccion.get(comparison.getOperator()) + getSuffix(isFloat ? new FloatType() : new IntType()));

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

		for(int i = 0; i < functionCallE.getParams().size(); i++) {
			value(functionCallE.getParams().get(i));
			convertTo(functionCallE.getParams().get(i).getType(), functionCallE.getFunctionDefinition().getParams().get(i).getType());
		}
		out("call " + functionCallE.getName());

		return null;
	}

}
