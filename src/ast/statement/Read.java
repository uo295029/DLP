// Generated with VGen 2.0.0

package ast.statement;

import ast.expression.*;
import ast.definition.*;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	read: statement -> expression:expression
	statement -> 
	
	PHASE TypeChecking
	statement -> function:functionDefinition
*/
public class Read extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// read: statement -> expression
	private Expression expression;

    // ----------------------------------
    // Constructors

	public Read(Expression expression) {
		super();

		if (expression == null)
			throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = expression;

		updatePositions(expression);
	}

	public Read(Object expression) {
		super();

        if (expression == null)
            throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = (Expression) expression;

		updatePositions(expression);
	}


    // ----------------------------------
    // read: statement -> expression

	// Child 'expression' 

	public void setExpression(Expression expression) {
		if (expression == null)
			throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = expression;

	}

    public Expression getExpression() {
        return expression;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Read{" + " expression=" + this.getExpression() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
