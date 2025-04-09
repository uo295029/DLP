// Generated with VGen 2.0.0

package ast.expression;

import ast.type.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	arrayAccess: expression -> left:expression right:expression
	expression -> 
	
	PHASE TypeChecking
	expression -> type:type
	expression -> lvalue:boolean
*/
public class ArrayAccess extends AbstractExpression  {

    // ----------------------------------
    // Instance Variables

	// arrayAccess: expression -> left:expression right:expression
	private Expression left;
	private Expression right;

    // ----------------------------------
    // Constructors

	public ArrayAccess(Expression left, Expression right) {
		super();

		if (left == null)
			throw new IllegalArgumentException("Parameter 'left' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.left = left;

		if (right == null)
			throw new IllegalArgumentException("Parameter 'right' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.right = right;

		updatePositions(left, right);
	}

	public ArrayAccess(Object left, Object right) {
		super();

        if (left == null)
            throw new IllegalArgumentException("Parameter 'left' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.left = (Expression) left;

        if (right == null)
            throw new IllegalArgumentException("Parameter 'right' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.right = (Expression) right;

		updatePositions(left, right);
	}


    // ----------------------------------
    // arrayAccess: expression -> left:expression right:expression

	// Child 'left:expression' 

	public void setLeft(Expression left) {
		if (left == null)
			throw new IllegalArgumentException("Parameter 'left' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.left = left;

	}

    public Expression getLeft() {
        return left;
    }


	// Child 'right:expression' 

	public void setRight(Expression right) {
		if (right == null)
			throw new IllegalArgumentException("Parameter 'right' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.right = right;

	}

    public Expression getRight() {
        return right;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "ArrayAccess{" + " left=" + this.getLeft() + " right=" + this.getRight() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
