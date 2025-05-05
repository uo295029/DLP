// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.*;
import ast.definition.*;
import ast.type.*;
import codegeneration.mapl.*;


public class Metadata extends AbstractCodeFunction {

    public Metadata(MaplCodeSpecification specification) {
        super(specification);
    }


	// class Program(List<Definition> definitions)
	@Override
	public Object visit(Program program, Object param) {

		out("#source \"" + getSpecification().getSourceFile() + "\"");
		for (Definition definition  : program.getDefinitions()) {
			if (definition instanceof VarDefinition varDef) {
				metadata(varDef);
			}
		}

		return null;
	}

	// class VarDefinition(String name, Type type)
	// phase MemoryAllocation { int offset, int scope }
	@Override
	public Object visit(VarDefinition varDefinition, Object param) {

		out("#GLOBAL " + varDefinition.getName() + ":" + getMaplName(varDefinition.getType()));

		return null;
	}
	
	@Override
	public Object visit(StructDefinition structDefinition, Object param) {

		out("#TYPE " + structDefinition.getName() + ": {");
		for (Attribute a : structDefinition.getAttributes()) {
			out(a.getName() + ":" + getMaplName(a.getType()));
		}
		out("}");
		
		return null;
	}
	
	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {
		out("#FUNC " + functionDefinition.getName());
		for (VarDefinition par : functionDefinition.getParams()) {
			out("#PARAM " + par.getName() + ":" + getMaplName(par.getType()));
		}
		out("#RET " + (functionDefinition.getType() instanceof VoidType? "VOID"
				: getMaplName(functionDefinition.getType())));
		for (VarDefinition local : functionDefinition.getV()) {
			out("#LOCAL " + local.getName() + ":" + getMaplName(local.getType()));
		}
		return null;
	}

	
	private String getMaplName(Type type) {
		if (type instanceof IntType)
			return "int";
		if (type instanceof FloatType)
			return "float";
		if (type instanceof CharType)
			return "char";
		if (type instanceof ArrayType tp)
			return tp.getSize() + " * " + getMaplName(tp.getType());
		if (type instanceof StructType st)
			return st.getName();

		// Sealed classes + pattern matching would avoid this situation. Those features were not
		// finished when this code was implemented
		throw new IllegalArgumentException("Unknown Type: " + type);
	}
}
