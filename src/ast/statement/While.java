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
	while: statement -> condition:expression yes:statement*
	statement -> 
	
	PHASE TypeChecking
	statement -> function:functionDefinition
*/
public class While extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// while: statement -> condition:expression yes:statement*
	private Expression condition;
	private List<Statement> yes;

    // ----------------------------------
    // Constructors

	public While(Expression condition, List<Statement> yes) {
		super();

		if (condition == null)
			throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = condition;

		if (yes == null)
			yes = new ArrayList<>();
		this.yes = yes;

		updatePositions(condition, yes);
	}

	public While(Object condition, Object yes) {
		super();

        if (condition == null)
            throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = (Expression) condition;

        this.yes = castList(yes, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(condition, yes);
	}


    // ----------------------------------
    // while: statement -> condition:expression yes:statement*

	// Child 'condition:expression' 

	public void setCondition(Expression condition) {
		if (condition == null)
			throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = condition;

	}

    public Expression getCondition() {
        return condition;
    }


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


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "While{" + " condition=" + this.getCondition() + " yes=" + this.getYes() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
