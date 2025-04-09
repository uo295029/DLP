// Generated with VGen 2.0.0

package ast.statement;

import ast.expression.*;
import ast.definition.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	functionCallS: statement -> name:string params:expression*
	statement -> 
	
	PHASE Identification
	functionCallS -> functionDefinition:functionDefinition
	
	PHASE TypeChecking
	statement -> function:functionDefinition
*/
public class FunctionCallS extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// functionCallS: statement -> name:string params:expression*
	private String name;
	private List<Expression> params;

    // PHASE Identification
	private FunctionDefinition functionDefinition;

    // ----------------------------------
    // Constructors

	public FunctionCallS(String name, List<Expression> params) {
		super();

		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

		if (params == null)
			params = new ArrayList<>();
		this.params = params;

		updatePositions(name, params);
	}

	public FunctionCallS(Object name, Object params) {
		super();

        if (name == null)
            throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = (name instanceof Token) ? ((Token) name).getText() : (String) name;

        this.params = castList(params, unwrapIfContext.andThen(Expression.class::cast));
		updatePositions(name, params);
	}


    // ----------------------------------
    // functionCallS: statement -> name:string params:expression*

	// Child 'name:string' 

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

	}

    public String getName() {
        return name;
    }


	// Child 'params:expression*' 

	public void setParams(List<Expression> params) {
		if (params == null)
			params = new ArrayList<>();
		this.params = params;

	}

    public List<Expression> getParams() {
        return params;
    }

    public Stream<Expression> params() {
        return params.stream();
    }



    // --------------------------------
    // PHASE Identification

	// Attribute 'functionDefinition' 

	public void setFunctionDefinition(FunctionDefinition functionDefinition) {
		if (functionDefinition == null)
			throw new IllegalArgumentException("Parameter 'functionDefinition' can't be null. Pass a non-null value or use 'functionDefinition?' in the abstract grammar");
		this.functionDefinition = functionDefinition;

	}

    public FunctionDefinition getFunctionDefinition() {
        return functionDefinition;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "FunctionCallS{" + " name=" + this.getName() + " params=" + this.getParams() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
