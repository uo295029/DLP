// Generated with VGen 2.0.0

package ast.statement;

import ast.expression.*;
import ast.definition.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	doWhile: statement -> yes:statement* condition:expression
	statement -> 
	
	PHASE TypeChecking
	statement -> function:functionDefinition
*/
public class DoWhile extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// doWhile: statement -> yes:statement* condition:expression
	private List<Statement> yes;
	private Expression condition;

    // ----------------------------------
    // Constructors

	public DoWhile(List<Statement> yes, Expression condition) {
		super();

		if (yes == null)
			yes = new ArrayList<>();
		this.yes = yes;

		if (condition == null)
			throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = condition;

		updatePositions(yes, condition);
	}

	public DoWhile(Object yes, Object condition) {
		super();

        this.yes = castList(yes, unwrapIfContext.andThen(Statement.class::cast));
        if (condition == null)
            throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = (Expression) condition;

		updatePositions(yes, condition);
	}


    // ----------------------------------
    // doWhile: statement -> yes:statement* condition:expression

	// Child 'yes:statement*' 

	public void setYes(List<Statement> yes) {
		if (yes == null)
			yes = new ArrayList<>();
		this.yes = yes;

	}

    public List<Statement> getYes() {
        return yes;
    }

    public Stream<Statement> yes() {
        return yes.stream();
    }


	// Child 'condition:expression' 

	public void setCondition(Expression condition) {
		if (condition == null)
			throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = condition;

	}

    public Expression getCondition() {
        return condition;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "DoWhile{" + " yes=" + this.getYes() + " condition=" + this.getCondition() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
