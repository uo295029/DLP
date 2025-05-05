// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.*;
import ast.definition.Definition;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import codegeneration.mapl.*;


public class Run extends AbstractCodeFunction {

    public Run(MaplCodeSpecification specification) {
        super(specification);
    }


	// class Program(List<Definition> definitions)
	@Override
	public Object visit(Program program, Object param) {

		out("#source \"" + getSpecification().getSourceFile() + "\"");
		for (Definition definition: program.getDefinitions()) {
			if (definition instanceof VarDefinition varDef) metadata(varDef);
			else if (definition instanceof StructDefinition structDefinition) metadata(structDefinition);
		}
		out("call main");
		out("halt");
		for (Definition definition:program.getDefinitions()) {
			if (definition instanceof FunctionDefinition def) {
				metadata(def);
				execute(def);
			}
		}
		return null;
	}

}
