// Generated with VGen 2.0.0

package ast;

import ast.type.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	attribute -> name:string type:type
	
	PHASE MemoryAllocation
	attribute -> offset:int
*/
public class Attribute extends AbstractAST  {

    // ----------------------------------
    // Instance Variables

	// attribute -> name:string type
	private String name;
	private Type type;

    // PHASE MemoryAllocation
	private int offset;

    // ----------------------------------
    // Constructors

	public Attribute(String name, Type type) {
		super();

		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

		if (type == null)
			throw new IllegalArgumentException("Parameter 'type' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.type = type;

		updatePositions(name, type);
	}

	public Attribute(Object name, Object type) {
		super();

        if (name == null)
            throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = (name instanceof Token) ? ((Token) name).getText() : (String) name;

        if (type == null)
            throw new IllegalArgumentException("Parameter 'type' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.type = (Type) type;

		updatePositions(name, type);
	}


    // ----------------------------------
    // attribute -> name:string type

	// Child 'name:string' 

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

	}

    public String getName() {
        return name;
    }


	// Child 'type' 

	public void setType(Type type) {
		if (type == null)
			throw new IllegalArgumentException("Parameter 'type' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.type = type;

	}

    public Type getType() {
        return type;
    }



    // --------------------------------
    // PHASE MemoryAllocation

	// Attribute 'offset:int' 

	public void setOffset(int offset) {
		this.offset = offset;

	}

    public int getOffset() {
        return offset;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Attribute{" + " name=" + this.getName() + " type=" + this.getType() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
