// Generated with VGen 2.0.0

package ast.definition;

import ast.type.*;
import ast.statement.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	functionDefinition: definition -> name:string params:varDefinition* type:type v:varDefinition* s:statement*
	definition -> 
*/
public class FunctionDefinition extends AbstractDefinition  {

    // ----------------------------------
    // Instance Variables

	// functionDefinition: definition -> name:string params:varDefinition* type v:varDefinition* s:statement*
	private String name;
	private List<VarDefinition> params;
	private Type type;
	private List<VarDefinition> v;
	private List<Statement> s;

    // ----------------------------------
    // Constructors

	public FunctionDefinition(String name, List<VarDefinition> params, Type type, List<VarDefinition> v, List<Statement> s) {
		super();

		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

		if (params == null)
			params = new ArrayList<>();
		this.params = params;

		if (type == null)
			throw new IllegalArgumentException("Parameter 'type' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.type = type;

		if (v == null)
			v = new ArrayList<>();
		this.v = v;

		if (s == null)
			s = new ArrayList<>();
		this.s = s;

		updatePositions(name, params, type, v, s);
	}

	public FunctionDefinition(Object name, Object params, Object type, Object v, Object s) {
		super();

        if (name == null)
            throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = (name instanceof Token) ? ((Token) name).getText() : (String) name;

        this.params = castList(params, unwrapIfContext.andThen(VarDefinition.class::cast));
        if (type == null)
            throw new IllegalArgumentException("Parameter 'type' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.type = (Type) type;

        this.v = castList(v, unwrapIfContext.andThen(VarDefinition.class::cast));
        this.s = castList(s, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(name, params, type, v, s);
	}


    // ----------------------------------
    // functionDefinition: definition -> name:string params:varDefinition* type v:varDefinition* s:statement*

	// Child 'name:string' 

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

	}

    public String getName() {
        return name;
    }


	// Child 'params:varDefinition*' 

	public void setParams(List<VarDefinition> params) {
		if (params == null)
			params = new ArrayList<>();
		this.params = params;

	}

    public List<VarDefinition> getParams() {
        return params;
    }

    public Stream<VarDefinition> params() {
        return params.stream();
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


	// Child 'v:varDefinition*' 

	public void setV(List<VarDefinition> v) {
		if (v == null)
			v = new ArrayList<>();
		this.v = v;

	}

    public List<VarDefinition> getV() {
        return v;
    }

    public Stream<VarDefinition> v() {
        return v.stream();
    }


	// Child 's:statement*' 

	public void setS(List<Statement> s) {
		if (s == null)
			s = new ArrayList<>();
		this.s = s;

	}

    public List<Statement> getS() {
        return s;
    }

    public Stream<Statement> s() {
        return s.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "FunctionDefinition{" + " name=" + this.getName() + " params=" + this.getParams() + " type=" + this.getType() + " v=" + this.getV() + " s=" + this.getS() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
