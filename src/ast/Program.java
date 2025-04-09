// Generated with VGen 2.0.0

package ast;

import ast.definition.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	program -> definitions:definition*
*/
public class Program extends AbstractAST  {

    // ----------------------------------
    // Instance Variables

	// program -> definition*
	private List<Definition> definitions;

    // ----------------------------------
    // Constructors

	public Program(List<Definition> definitions) {
		super();

		if (definitions == null)
			definitions = new ArrayList<>();
		this.definitions = definitions;

		updatePositions(definitions);
	}

	public Program(Object definitions) {
		super();

        this.definitions = castList(definitions, unwrapIfContext.andThen(Definition.class::cast));
		updatePositions(definitions);
	}


    // ----------------------------------
    // program -> definition*

	// Child 'definition*' 

	public void setDefinitions(List<Definition> definitions) {
		if (definitions == null)
			definitions = new ArrayList<>();
		this.definitions = definitions;

	}

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public Stream<Definition> definitions() {
        return definitions.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Program{" + " definitions=" + this.getDefinitions() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
