package codegeneration;

import visitor.DefaultVisitor;
import ast.*;
import ast.definition.*;
import ast.statement.*;
import ast.expression.*;
import ast.type.*;


public class MemoryAllocation extends DefaultVisitor {

	private int currentAddress = 0;
	
    public void process(AST ast) {
        ast.accept(this, null);
    }

    // Visit Methods --------------------------------------------------------------

	// class VarDefinition(String name, Type type)
	// phase MemoryAllocation { int address }
	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		super.visit(varDefinition, param);
		varDefinition.setOffset(currentAddress);
		return null;
	}

	// class FunctionDefinition(String name, List<VarDefinition> params, Type type, List<VarDefinition> v, List<Statement> s)
	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {

		// functionDefinition.getParams().forEach(varDefinition -> varDefinition.accept(this, param));
		// functionDefinition.getType().accept(this, param);
		// functionDefinition.getV().forEach(varDefinition -> varDefinition.accept(this, param));
		// functionDefinition.getS().forEach(statement -> statement.accept(this, param));
		super.visit(functionDefinition, param);

		return null;
	}

	// class StructDefinition(String name, List<Attribute> attributes)
	@Override
	public Object visit(StructDefinition structDefinition, Object param) {

		// structDefinition.getAttributes().forEach(attribute -> attribute.accept(this, param));
		super.visit(structDefinition, param);

		return null;
	}
}
