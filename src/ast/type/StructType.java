// Generated with VGen 2.0.0

package ast.type;

import ast.definition.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

import ast.Attribute;
    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	structType: type -> name:string
	type -> 
	
	PHASE Identification
	structType -> structDefinition:structDefinition
*/
public class StructType extends AbstractType  {

    // ----------------------------------
    // Instance Variables

	// structType: type -> name:string
	private String name;

    // PHASE Identification
	private StructDefinition structDefinition;

    // ----------------------------------
    // Constructors

	public StructType(String name) {
		super();

		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

		updatePositions(name);
	}

	public StructType(Object name) {
		super();

        if (name == null)
            throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = (name instanceof Token) ? ((Token) name).getText() : (String) name;

		updatePositions(name);
	}


    // ----------------------------------
    // structType: type -> name:string

	// Child 'name:string' 

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

	}

    public String getName() {
        return name;
    }



    // --------------------------------
    // PHASE Identification

	// Attribute 'structDefinition' 

	public void setStructDefinition(StructDefinition structDefinition) {
		if (structDefinition == null)
			throw new IllegalArgumentException("Parameter 'structDefinition' can't be null. Pass a non-null value or use 'structDefinition?' in the abstract grammar");
		this.structDefinition = structDefinition;

	}

    public StructDefinition getStructDefinition() {
        return structDefinition;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "StructType{" + " name=" + this.getName() + "}";
    }


    // %% User Members -------------------------

    @Override
    public int getBytes() {
    	int size = 0;
    	for(Attribute a : this.structDefinition.getAttributes()) {
    		size += a.getType().getBytes();
    	}
    	return size;
    }

    // %% --------------------------------------
}
