// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.Attribute;
import ast.expression.*;
import ast.type.StructType;
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

		if (variable.getVarDefinition().getScope() == 0) {
			out("pusha " + variable.getVarDefinition().getOffset());
		} else {
			out("pusha BP");
			out("pushi " + variable.getVarDefinition().getOffset());
			out("addi");
		}

		return null;
	}

	// class AttributeAccess(Expression left, String right)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(AttributeAccess attributeAccess, Object param) {

		address(attributeAccess.getLeft());
		for(Attribute a : ((StructType) attributeAccess.getLeft().getType()).getStructDefinition().getAttributes()) {
			if(a.getName().equals(attributeAccess.getRight())) out("pushi " + a.getOffset());
			out("addi");
		}

		return null;
	}

	// class ArrayAccess(Expression left, Expression right)
	// phase TypeChecking { Type type, boolean lvalue }
	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		address(arrayAccess.getLeft());
		value(arrayAccess.getRight());
		out("pushi " + arrayAccess.getType().getBytes());
		out("muli");
		out("addi");

		return null;
	}
}
