// Generated with VGen 2.0.0

package ast.definition;

import ast.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	structDefinition: definition -> name:string attributes:attribute*
	definition -> 
*/
public class StructDefinition extends AbstractDefinition  {

    // ----------------------------------
    // Instance Variables

	// structDefinition: definition -> name:string attributes:attribute*
	private String name;
	private List<Attribute> attributes;

    // ----------------------------------
    // Constructors

	public StructDefinition(String name, List<Attribute> attributes) {
		super();

		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

		if (attributes == null)
			attributes = new ArrayList<>();
		this.attributes = attributes;

		updatePositions(name, attributes);
	}

	public StructDefinition(Object name, Object attributes) {
		super();

        if (name == null)
            throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = (name instanceof Token) ? ((Token) name).getText() : (String) name;

        this.attributes = castList(attributes, unwrapIfContext.andThen(Attribute.class::cast));
		updatePositions(name, attributes);
	}


    // ----------------------------------
    // structDefinition: definition -> name:string attributes:attribute*

	// Child 'name:string' 

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

	}

    public String getName() {
        return name;
    }


	// Child 'attributes:attribute*' 

	public void setAttributes(List<Attribute> attributes) {
		if (attributes == null)
			attributes = new ArrayList<>();
		this.attributes = attributes;

	}

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public Stream<Attribute> attributes() {
        return attributes.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "StructDefinition{" + " name=" + this.getName() + " attributes=" + this.getAttributes() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
