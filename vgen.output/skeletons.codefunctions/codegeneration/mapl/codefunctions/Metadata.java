// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.*;
import ast.definition.*;
import codegeneration.mapl.*;


public class Metadata extends AbstractCodeFunction {

    public Metadata(MaplCodeSpecification specification) {
        super(specification);
    }


	// class Program(List<Definition> definitions)
	@Override
	public Object visit(Program program, Object param) {

		out("<instruction>");

		return null;
	}

	// class VarDefinition(String name, Type type)
	// phase MemoryAllocation { int offset, int scope }
	@Override
	public Object visit(VarDefinition varDefinition, Object param) {

		out("<instruction>");

		return null;
	}

}
