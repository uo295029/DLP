// Generated with VGen 2.0.0

package ast.statement;

import ast.*;
import ast.definition.*;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	statement -> 
	
	PHASE TypeChecking
	statement -> function:functionDefinition
*/
public interface Statement extends AST {




    // --------------------------------
    // PHASE TypeChecking

	// Attribute 'function:functionDefinition' 

	public void setFunction(FunctionDefinition function);
	public FunctionDefinition getFunction();


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
