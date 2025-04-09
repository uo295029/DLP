// Generated with VGen 2.0.0

package ast.type;

import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	arrayType: type -> size:int type:type
	type -> 
*/
public class ArrayType extends AbstractType  {

    // ----------------------------------
    // Instance Variables

	// arrayType: type -> size:int type
	private int size;
	private Type type;

    // ----------------------------------
    // Constructors

	public ArrayType(int size, Type type) {
		super();

		this.size = size;

		if (type == null)
			throw new IllegalArgumentException("Parameter 'type' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.type = type;

		updatePositions(size, type);
	}

	public ArrayType(Object size, Object type) {
		super();

        if (size == null)
            throw new IllegalArgumentException("Parameter 'size' can't be null. Pass a non-null value or use 'int?' in the abstract grammar");
        var size_temp = size;
        if (size_temp instanceof Token)
            size_temp = ((Token) size_temp).getText();
        if (size_temp instanceof String)
            size_temp = Integer.valueOf((String) size_temp);
        this.size = (int) size_temp;

        if (type == null)
            throw new IllegalArgumentException("Parameter 'type' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.type = (Type) type;

		updatePositions(size, type);
	}


    // ----------------------------------
    // arrayType: type -> size:int type

	// Child 'size:int' 

	public void setSize(int size) {
		this.size = size;

	}

    public int getSize() {
        return size;
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


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "ArrayType{" + " size=" + this.getSize() + " type=" + this.getType() + "}";
    }


    // %% User Members -------------------------

    @Override
    public int getBytes() {
    	return this.size * this.type.getBytes();
    }

    // %% --------------------------------------
}
