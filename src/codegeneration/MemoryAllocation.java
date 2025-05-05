package codegeneration;

import visitor.DefaultVisitor;
import ast.*;
import ast.definition.*;

public class MemoryAllocation extends DefaultVisitor {

	private int currentAddress = 0;
	private int localAddress = 0;
	
    public void process(AST ast) {
        ast.accept(this, null);
    }

    // Visit Methods --------------------------------------------------------------

	// class VarDefinition(String name, Type type)
	// phase MemoryAllocation { int address }
	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		super.visit(varDefinition, param);
		if(varDefinition.getScope() == 0) {
			varDefinition.setOffset(currentAddress);
			currentAddress += varDefinition.getType().getBytes();
		} else if(varDefinition.getScope() == 2) {
			localAddress += varDefinition.getType().getBytes();
			varDefinition.setOffset(-localAddress);
		}
		return null;
	}

	// class FunctionDefinition(String name, List<VarDefinition> params, Type type, List<VarDefinition> v, List<Statement> s)
	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {
		super.visit(functionDefinition, param);
		localAddress = 0;
		int n = 4;
		int varsSize = 0;
		VarDefinition p;
		for(int i = functionDefinition.getParams().size() - 1; i >= 0; i--) {
			p = functionDefinition.getParams().get(i);
			p.setOffset(n);
			n += p.getType().getBytes();
		}
		for (VarDefinition def : functionDefinition.getV()) {
            varsSize += def.getType().getBytes();
        }
		functionDefinition.setVarsSize(varsSize);
        functionDefinition.setParamsSize(n - 4);
		return null;
	}

	// class StructDefinition(String name, List<Attribute> attributes)
	@Override
	public Object visit(StructDefinition structDefinition, Object param) {
		int address = 0;
		super.visit(structDefinition, param);
		for(Attribute attribute : structDefinition.getAttributes()) {
			attribute.setOffset(address);
			address += attribute.getType().getBytes();
		}
		return null;
	}
}
