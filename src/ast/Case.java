// Generated with VGen 2.0.0

package ast;

import ast.expression.*;
import ast.statement.*;
import ast.type.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	case -> condition:expression statements:statement*
	
	PHASE TypeChecking
	case -> type:type
*/
public class Case extends AbstractAST  {

    // ----------------------------------
    // Instance Variables

	// case -> condition:expression statement*
	private Expression condition;
	private List<Statement> statements;

    // PHASE TypeChecking
	private Type type;

    // ----------------------------------
    // Constructors

	public Case(Expression condition, List<Statement> statements) {
		super();

		if (condition == null)
			throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = condition;

		if (statements == null)
			statements = new ArrayList<>();
		this.statements = statements;

		updatePositions(condition, statements);
	}

	public Case(Object condition, Object statements) {
		super();

        if (condition == null)
            throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = (Expression) condition;

        this.statements = castList(statements, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(condition, statements);
	}


    // ----------------------------------
    // case -> condition:expression statement*

	// Child 'condition:expression' 

	public void setCondition(Expression condition) {
		if (condition == null)
			throw new IllegalArgumentException("Parameter 'condition' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.condition = condition;

	}

    public Expression getCondition() {
        return condition;
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



    // --------------------------------
    // PHASE TypeChecking

	// Attribute 'type' 

	public void setType(Type type) {
		if (type == null)
			throw new IllegalArgumentException("Parameter 'type' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.type = type;

	}

    public Type getType() {
        return type;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Case{" + " condition=" + this.getCondition() + " statements=" + this.getStatements() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
