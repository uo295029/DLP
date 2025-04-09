// Generated with VGen 2.0.0

package ast.expression;

import ast.type.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	cast: expression -> castType:type expression:expression
	expression -> 
	
	PHASE TypeChecking
	expression -> type:type
	expression -> lvalue:boolean
*/
public class Cast extends AbstractExpression  {

    // ----------------------------------
    // Instance Variables

	// cast: expression -> castType:type expression
	private Type castType;
	private Expression expression;

    // ----------------------------------
    // Constructors

	public Cast(Type castType, Expression expression) {
		super();

		if (castType == null)
			throw new IllegalArgumentException("Parameter 'castType' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.castType = castType;

		if (expression == null)
			throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = expression;

		updatePositions(castType, expression);
	}

	public Cast(Object castType, Object expression) {
		super();

        if (castType == null)
            throw new IllegalArgumentException("Parameter 'castType' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.castType = (Type) castType;

        if (expression == null)
            throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = (Expression) expression;

		updatePositions(castType, expression);
	}


    // ----------------------------------
    // cast: expression -> castType:type expression

	// Child 'castType:type' 

	public void setCastType(Type castType) {
		if (castType == null)
			throw new IllegalArgumentException("Parameter 'castType' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.castType = castType;

	}

    public Type getCastType() {
        return castType;
    }


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
        return "Cast{" + " castType=" + this.getCastType() + " expression=" + this.getExpression() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
