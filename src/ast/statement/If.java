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
	if: statement -> condition:expression yes:statement* no:statement*
	statement -> 
	
	PHASE TypeChecking
	statement -> function:functionDefinition
*/
public class If extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// if: statement -> condition:expression yes:statement* no:statement*
	private Expression condition;
	private List<Statement> yes;
	private List<Statement> no;

    // ----------------------------------
    // Constructors

	public If(Expression condition, List<Statement> yes, List<Statement> no) {
		super();

		if (condition == null)
			throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = condition;

		if (yes == null)
			yes = new ArrayList<>();
		this.yes = yes;

		if (no == null)
			no = new ArrayList<>();
		this.no = no;

		updatePositions(condition, yes, no);
	}

	public If(Object condition, Object yes, Object no) {
		super();

        if (condition == null)
            throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = (Expression) condition;

        this.yes = castList(yes, unwrapIfContext.andThen(Statement.class::cast));
        this.no = castList(no, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(condition, yes, no);
	}


    // ----------------------------------
    // if: statement -> condition:expression yes:statement* no:statement*

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


	// Child 'no:statement*' 

	public void setNo(List<Statement> no) {
		if (no == null)
			no = new ArrayList<>();
		this.no = no;

	}

    public List<Statement> getNo() {
        return no;
    }

    public Stream<Statement> no() {
        return no.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "If{" + " condition=" + this.getCondition() + " yes=" + this.getYes() + " no=" + this.getNo() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
