// Generated with VGen 2.0.0

package ast.expression;

import ast.type.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	attributeAccess: expression -> left:expression right:string
	expression -> 
	
	PHASE TypeChecking
	expression -> type:type
	expression -> lvalue:boolean
*/
public class AttributeAccess extends AbstractExpression  {

    // ----------------------------------
    // Instance Variables

	// attributeAccess: expression -> left:expression right:string
	private Expression left;
	private String right;

    // ----------------------------------
    // Constructors

	public AttributeAccess(Expression left, String right) {
		super();

		if (left == null)
			throw new IllegalArgumentException("Parameter 'left' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.left = left;

		if (right == null)
			throw new IllegalArgumentException("Parameter 'right' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.right = right;

		updatePositions(left, right);
	}

	public AttributeAccess(Object left, Object right) {
		super();

        if (left == null)
            throw new IllegalArgumentException("Parameter 'left' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.left = (Expression) left;

        if (right == null)
            throw new IllegalArgumentException("Parameter 'right' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.right = (right instanceof Token) ? ((Token) right).getText() : (String) right;

		updatePositions(left, right);
	}


    // ----------------------------------
    // attributeAccess: expression -> left:expression right:string

	// Child 'left:expression' 

	public void setLeft(Expression left) {
		if (left == null)
			throw new IllegalArgumentException("Parameter 'left' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.left = left;

	}

    public Expression getLeft() {
        return left;
    }


	// Child 'right:string' 

	public void setRight(String right) {
		if (right == null)
			throw new IllegalArgumentException("Parameter 'right' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.right = right;

	}

    public String getRight() {
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
        return "AttributeAccess{" + " left=" + this.getLeft() + " right=" + this.getRight() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
