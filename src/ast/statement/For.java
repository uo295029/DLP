// Generated with VGen 2.0.0

package ast.statement;

import ast.definition.*;
import ast.expression.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	for: statement -> varDefinition:varDefinition condition:expression increment:statement statements:statement*
	statement -> 
	
	PHASE TypeChecking
	statement -> function:functionDefinition
*/
public class For extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// for: statement -> varDefinition condition:expression increment:statement statement*
	private VarDefinition varDefinition;
	private Expression condition;
	private Statement increment;
	private List<Statement> statements;

    // ----------------------------------
    // Constructors

	public For(VarDefinition varDefinition, Expression condition, Statement increment, List<Statement> statements) {
		super();

		if (varDefinition == null)
			throw new IllegalArgumentException("Parameter 'varDefinition' can't be null. Pass a non-null value or use 'varDefinition?' in the abstract grammar");
		this.varDefinition = varDefinition;

		if (condition == null)
			throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = condition;

		if (increment == null)
			throw new IllegalArgumentException("Parameter 'increment' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.increment = increment;

		if (statements == null)
			statements = new ArrayList<>();
		this.statements = statements;

		updatePositions(varDefinition, condition, increment, statements);
	}

	public For(Object varDefinition, Object condition, Object increment, Object statements) {
		super();

        if (varDefinition == null)
            throw new IllegalArgumentException("Parameter 'varDefinition' can't be null. Pass a non-null value or use 'varDefinition?' in the abstract grammar");
		this.varDefinition = (VarDefinition) varDefinition;

        if (condition == null)
            throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = (Expression) condition;

        if (increment == null)
            throw new IllegalArgumentException("Parameter 'increment' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.increment = (Statement) increment;

        this.statements = castList(statements, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(varDefinition, condition, increment, statements);
	}


    // ----------------------------------
    // for: statement -> varDefinition condition:expression increment:statement statement*

	// Child 'varDefinition' 

	public void setVarDefinition(VarDefinition varDefinition) {
		if (varDefinition == null)
			throw new IllegalArgumentException("Parameter 'varDefinition' can't be null. Pass a non-null value or use 'varDefinition?' in the abstract grammar");
		this.varDefinition = varDefinition;

	}

    public VarDefinition getVarDefinition() {
        return varDefinition;
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


	// Child 'increment:statement' 

	public void setIncrement(Statement increment) {
		if (increment == null)
			throw new IllegalArgumentException("Parameter 'increment' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.increment = increment;

	}

    public Statement getIncrement() {
        return increment;
    }


	// Child 'statement*' 

	public void setStatements(List<Statement> statements) {
		if (statements == null)
			statements = new ArrayList<>();
		this.statements = statements;

	}

    public List<Statement> getStatements() {
        return statements;
    }

    public Stream<Statement> statements() {
        return statements.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "For{" + " varDefinition=" + this.getVarDefinition() + " condition=" + this.getCondition() + " increment=" + this.getIncrement() + " statements=" + this.getStatements() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
