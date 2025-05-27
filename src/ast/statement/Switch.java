// Generated with VGen 2.0.0

package ast.statement;

import ast.expression.*;
import ast.*;
import ast.definition.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	switch: statement -> conditioner:expression cases:case*
	statement -> 
	
	PHASE TypeChecking
	statement -> function:functionDefinition
*/
public class Switch extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// switch: statement -> conditioner:expression case*
	private Expression conditioner;
	private List<Case> cases;

    // ----------------------------------
    // Constructors

	public Switch(Expression conditioner, List<Case> cases) {
		super();

		if (conditioner == null)
			throw new IllegalArgumentException("Parameter 'conditioner' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.conditioner = conditioner;

		if (cases == null)
			cases = new ArrayList<>();
		this.cases = cases;

		updatePositions(conditioner, cases);
	}

	public Switch(Object conditioner, Object cases) {
		super();

        if (conditioner == null)
            throw new IllegalArgumentException("Parameter 'conditioner' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.conditioner = (Expression) conditioner;

        this.cases = castList(cases, unwrapIfContext.andThen(Case.class::cast));
		updatePositions(conditioner, cases);
	}


    // ----------------------------------
    // switch: statement -> conditioner:expression case*

	// Child 'conditioner:expression' 

	public void setConditioner(Expression conditioner) {
		if (conditioner == null)
			throw new IllegalArgumentException("Parameter 'conditioner' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.conditioner = conditioner;

	}

    public Expression getConditioner() {
        return conditioner;
    }


	// Child 'case*' 

	public void setCases(List<Case> cases) {
		if (cases == null)
			cases = new ArrayList<>();
		this.cases = cases;

	}

    public List<Case> getCases() {
        return cases;
    }

    public Stream<Case> cases() {
        return cases.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Switch{" + " conditioner=" + this.getConditioner() + " cases=" + this.getCases() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
