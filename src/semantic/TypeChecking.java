/**
 * MLang. Programming Language Design Tutorial
 * @author Raúl Izquierdo (raul@uniovi.es)
 */

package semantic;

import java.util.List;

import ast.*;
import ast.definition.*;
import ast.expression.*;
import ast.statement.*;
import ast.type.*;
import main.ErrorManager;
import visitor.DefaultVisitor;

public class TypeChecking extends DefaultVisitor {

    private ErrorManager errorManager;

    public TypeChecking(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    public void process(AST ast) {
        ast.accept(this, null);
    }

    // # ----------------------------------------------------------

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object param) {
		functionDefinition.getParams().forEach(varDefinition -> varDefinition.accept(this, param));
		functionDefinition.getType().accept(this, param);
    	predicate(functionDefinition.getType().isSimple() || functionDefinition.getType() instanceof VoidType, "The return type must be an instance of a primitive type", functionDefinition);
    	for(VarDefinition v : functionDefinition.getParams()) {
    		predicate(v.getType().isSimple(), "The parameters must be an instance of a primitive type", functionDefinition);
    	}
    	for(Statement s : functionDefinition.getS()) {
    		s.setFunction(functionDefinition);
    	}
    	super.visit(functionDefinition, param);
    	return null;
    }
    
    @Override
    public Object visit(Assignment assignment, Object param) {
        super.visit(assignment, param);
        predicate(assignment.getRight().getType().isValidConversion(assignment.getLeft().getType()), "The expression type can not be converted", assignment);
        predicate(assignment.getLeft().getType().isSimple(), "The expression on the left must be an instance of a primitive type", assignment);
        predicate(assignment.getLeft().isLvalue(), "The expression on the left is not modifiable", assignment.getLeft());
        return null;
    }
    
    @Override
    public Object visit(FunctionCallS functionCallS, Object param) {
    	super.visit(functionCallS, param);
    	if(predicate(functionCallS.getParams().size() == functionCallS.getFunctionDefinition().getParams().size(), "The number of params is invalid", functionCallS))
    		predicate(validParams(functionCallS.getParams(), functionCallS.getFunctionDefinition().getParams()), "The params types can not be converted", functionCallS);
    	return null;
    }
    
    @Override
    public Object visit(Return r, Object param) {
    	super.visit(r, param);
    	if(r.getExpression().isEmpty()) 
    		predicate(r.getFunction().getType() instanceof VoidType, "The return of the function " + r.getFunction().getName() + " can not be void", r);
    	else 
    		predicate(r.getExpression().get().getType().isValidConversion(r.getFunction().getType()), "The type of the function " + r.getFunction().getName() + " does not match the return type", r);
    	return null;
    }
    
    @Override
    public Object visit(If i, Object param) {
    	i.getCondition().accept(this, param);
    	predicate(i.getCondition().getType().isValidConversion(new IntType()), "The condition type is not valid", i);
    	for(Statement s : i.getYes()) {
    		s.setFunction(i.getFunction());
    	}
    	for(Statement s : i.getNo()) {
    		s.setFunction(i.getFunction());
    	}
    	super.visit(i, param);
    	return null;
    }
    
    @Override
    public Object visit(While w, Object param) {
    	w.getCondition().accept(this, param);
    	predicate(w.getCondition().getType().isValidConversion(new IntType()), "The condition type is not valid", w);
    	for(Statement s : w.getYes()) {
    		s.setFunction(w.getFunction());
    	}
    	super.visit(w, param);
    	return null;
    }
    
    @Override
    public Object visit(Print print, Object param) {
    	super.visit(print, param);
    	for(Expression e : print.getExpressions()) {
    		predicate(e.getType().isSimple(), "The expression to print must be an instance of a primitive type", print);
    	}
    	return null;
    }
    
    @Override
    public Object visit(Printsp printsp, Object param) {
    	super.visit(printsp, param);
    	for(Expression e : printsp.getExpressions()) {
    		predicate(e.getType().isSimple(), "The expression to print must be an instance of a primitive type", printsp);
    	}
    	return null;
    }
    
    @Override
    public Object visit(Println println, Object param) {
    	super.visit(println, param);
    	for(Expression e : println.getExpressions()) {
    		predicate(e.getType().isSimple(), "The expression to print must be an instance of a primitive type", println);
    	}
    	return null;
    }
    
    @Override
    public Object visit(Read read, Object param) {
    	super.visit(read, param);
    	predicate(read.getExpression().getType().isSimple(), "The expression to read must be an instance of a primitive type", read);
    	predicate(read.getExpression().isLvalue(), "The expression is not modifiable", read);
    	return null;
    }
    
    @Override
    public Object visit(Variable variable, Object param) {
    	super.visit(variable, param);
        variable.setType(variable.getVarDefinition().getType());
        variable.setLvalue(true);
        return null;
    }

    @Override
    public Object visit(IntLiteral intLiteral, Object param) {
        intLiteral.setType(new IntType());
        intLiteral.setLvalue(false);
        return null;
    }

    @Override
    public Object visit(FloatLiteral floatLiteral, Object param) {
        floatLiteral.setType(new FloatType());
        floatLiteral.setLvalue(false);
        return null;
    }
    
    @Override
    public Object visit(CharLiteral charLiteral, Object param) {
        charLiteral.setType(new CharType());
        charLiteral.setLvalue(false);
        return null;
    }

    @Override
    public Object visit(Arithmetic arithmetic, Object param) {
        super.visit(arithmetic, param);
        predicate(arithmetic.getLeft().getType().isSimple(), "The type of the expression on the left must be a simple (int, char, double)", arithmetic);
        predicate(arithmetic.getRight().getType().isSimple(), "The type of the expression on the right must be a simple (int, char, double)", arithmetic);
        if(arithmetic.getOperator().equals("%")) predicate(arithmetic.getLeft().getType() instanceof IntType && arithmetic.getRight().getType() instanceof IntType, "Both expressions must be integers", arithmetic);
        arithmetic.setType(arithmetic.getLeft().getType().arithmetic(arithmetic.getRight().getType()));
        arithmetic.setLvalue(false);
        return null;
    }
    
    @Override
    public Object visit(Negate negate, Object param) {
    	super.visit(negate, param);
    	predicate(negate.getExpression().getType().isValidConversion(new IntType()), "The expression type can not be converted to Int", negate);
    	negate.setType(negate.getExpression().getType());
    	negate.setLvalue(false);
    	return null;
    }
    
    @Override
    public Object visit(Logic logic, Object param) {
    	super.visit(logic, param);
    	predicate(logic.getLeft().getType() instanceof IntType, "The left expression must be an integer", logic);
    	predicate(logic.getRight().getType() instanceof IntType, "The right expression must be an integer", logic);
    	logic.setType(new IntType());
    	logic.setLvalue(false);
    	return null;
    }
    
    @Override
    public Object visit(AttributeAccess attributeAccess, Object param) {
    	super.visit(attributeAccess, param);
    	if(predicate(attributeAccess.getLeft().getType() instanceof StructType, "The expression on the left must be a struct", attributeAccess)) {
    		StructDefinition struct = ((StructType) attributeAccess.getLeft().getType()).getStructDefinition();
    		boolean found = false;
    		for(Attribute a : struct.getAttributes()) {
    			if(found = a.getName().equals(attributeAccess.getRight())) {
    				attributeAccess.setType(a.getType());
    				break;
    			}
    		}
    		predicate(found, "The attribute does not exist", attributeAccess);
    	}
    	attributeAccess.setLvalue(true);
    	return null;
    }
    
    @Override
    public Object visit(Comparison comparison, Object param) {
    	super.visit(comparison, param);
    	predicate(comparison.getLeft().getType().isSimple(), "The expression on the left must be an instance of a primitive type", comparison);
    	predicate(comparison.getRight().getType().isSimple(), "The expression on the right must be an instance of a primitive type", comparison);
    	comparison.setType(new IntType());
    	comparison.setLvalue(false);
    	return null;
    }
    
    @Override
    public Object visit(ArrayAccess arrayAccess, Object param) {
    	super.visit(arrayAccess, param);
    	predicate(arrayAccess.getRight().getType().isValidConversion(new IntType()), "The expression between the brackets must be an integer", arrayAccess);
    	if(predicate(arrayAccess.getLeft().getType() instanceof ArrayType, "The expression on the left must be an array", arrayAccess)) {
    		arrayAccess.setLvalue(true);
    		arrayAccess.setType(((ArrayType) arrayAccess.getLeft().getType()).getType());
    	}
    	return null;
    }
    
    @Override
    public Object visit(Cast cast, Object param) {
    	super.visit(cast, param);
    	predicate(cast.getCastType().getClass() != cast.getExpression().getType().getClass(), "The cast type must be different from the expression type", cast);
    	predicate(cast.getCastType().isSimple(), "The cast type must be an instance of a primitive type", cast);
    	predicate(cast.getExpression().getType().isSimple(), "The expression type must be an instance of a primitive type", cast);
    	cast.setType(cast.getCastType());
    	cast.setLvalue(false);
    	return null;
    }
    
    @Override
    public Object visit(FunctionCallE functionCallE, Object param) {
    	super.visit(functionCallE, param);
    	if(predicate(functionCallE.getParams().size() == functionCallE.getFunctionDefinition().getParams().size(), "The number of params is invalid", functionCallE))
    		predicate(validParams(functionCallE.getParams(), functionCallE.getFunctionDefinition().getParams()), "The params types do not match", functionCallE);
    	predicate(!(functionCallE.getFunctionDefinition().getType() instanceof VoidType), "The function return type can not be void: ", functionCallE);
    	functionCallE.setType(functionCallE.getFunctionDefinition().getType());
    	functionCallE.setLvalue(false);
    	return null;
    }
    //# ----------------------------------------------------------
    //# Auxiliary methods (optional)
    
    private boolean validParams(List<Expression> a, List<VarDefinition> b) {
    	for(int i = 0; i < a.size(); i++) {
    		if(!a.get(i).getType().isValidConversion(b.get(i).getType())) return false;
    	}
    	return true;
    }

    private void notifyError(String errorMessage, Position position) {
        errorManager.notify("Type Checking", errorMessage, position);
    }

    /**
     * predicate. Auxiliary method to implement predicates. Delete if not needed.
     *
     * Usage examples:
     *
     *    predicate(expr.type != null), "Type cannot be null", expr.start());
     *
     *    predicate(expr.type != null), "Type cannot be null", expr);       // expr.start() is assumed
     *
     * The start() method (example 1) indicates the position in the file where the node was. If VGen is used, this method
     * will have been generated in all AST nodes.
     *
     * @param condition     Must be met to avoid an error
     * @param errorMessage  Printed if the condition is not met
     * @param errorPosition Row and column in the file where the error occurred.
     * @return true if the condition is met
     */

    private boolean predicate(boolean condition, String errorMessage, Position position) {
        if (!condition) {
            notifyError(errorMessage, position);
            return false;
        }

        return true;
    }

    private boolean predicate(boolean condition, String errorMessage, AST node) {
        return predicate(condition, errorMessage, node.start());
    }

}
