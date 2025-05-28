// Generated with VGen 2.0.0

package ast.type;

import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	charType: type -> 
	type -> 
*/
public class CharType extends AbstractType  {



    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "CharType{" + "}";
    }


    // %% User Members -------------------------

    @Override
    public int getBytes() {
    	return 1;
    }
    
    @Override
    public boolean isSimple() {
    	return true;
    }
    
    @Override
    public boolean isValidConversion(Type t) {
    	return (t instanceof CharType || t instanceof IntType || t instanceof FloatType);
    }

    // %% --------------------------------------
}
