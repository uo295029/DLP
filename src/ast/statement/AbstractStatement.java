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
public abstract class AbstractStatement extends AbstractAST implements Statement {

    // ----------------------------------
    // Instance Variables


    // PHASE TypeChecking
	private FunctionDefinition function;



    // --------------------------------
    // PHASE TypeChecking

	// Attribute 'function:functionDefinition' 

	public void setFunction(FunctionDefinition function) {
		if (function == null)
			throw new IllegalArgumentException("Parameter 'function' can't be null. Pass a non-null value or use 'functionDefinition?' in the abstract grammar");
		this.function = function;

	}

    public FunctionDefinition getFunction() {
        return function;
    }



    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
