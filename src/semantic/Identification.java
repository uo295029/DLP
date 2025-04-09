package semantic;

import java.util.*;

import ast.*;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import ast.expression.FunctionCallE;
import ast.expression.Variable;
import ast.statement.FunctionCallS;
import ast.type.StructType;
import main.ErrorManager;
import visitor.DefaultVisitor;

// This class will be implemented in identification phase

public class Identification extends DefaultVisitor {

    private ErrorManager errorManager;
    private ContextMap<VarDefinition> variables = new ContextMap<VarDefinition>();
    private Map<String, FunctionDefinition> functions = new HashMap<String, FunctionDefinition>();
    private Map<String, StructDefinition> structs = new HashMap<String, StructDefinition>();

    public Identification(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    public void process(AST ast) {
        ast.accept(this, null);
    }

    // # ----------------------------------------------------------
    // class VarDefinition(Type type, String name)
    @Override
    public Object visit(VarDefinition varDefinition, Object param) {
        var definition = variables.getFromTop(varDefinition.getName());
        if (definition != null) 
        	notifyError("Variable already defined: " + varDefinition.getName(), varDefinition);
        else 
        	variables.put(varDefinition.getName(), varDefinition);
        varDefinition.getType().accept(this, param);
        return null;
    }

    // class Variable(String name)
    // phase Identification { VarDefinition varDefinition }
    @Override
    public Object visit(Variable variable, Object param) {
        var definition = variables.getFromAny(variable.getName());
        if(definition == null) 
        	notifyError("Undefined variable: " + variable.getName(), variable);
        else 
        	variable.setVarDefinition(definition);
        return null;
    }
    
    // class FunctionDefinition(String name, List<VarDefinition> params, Type type, List<VarDefinition> v, List<Statement> s)
 	@Override
 	public Object visit(FunctionDefinition functionDefinition, Object param) {
 		var definition = functions.get(functionDefinition.getName());
 		if(definition != null) {
 			notifyError("Function already defined: " + functionDefinition.getName(), functionDefinition);
 		} else {
 			functionDefinition.getType().accept(this, param);
 			functions.put(functionDefinition.getName(), functionDefinition);
 			variables.set();
 			super.visit(functionDefinition, param);
 			variables.reset();
 		}
 		return null;
 	}
    
 	// class FunctionCallS(String name, List<Expression> params)
 	// phase Identification { FunctionDefinition functionDefinition }
 	@Override
 	public Object visit(FunctionCallS functionCallS, Object param) {
 		var definition = functions.get(functionCallS.getName());
        if(definition == null) 
        	notifyError("Undefined function: " + functionCallS.getName(), functionCallS);
        else 
        	functionCallS.setFunctionDefinition(definition);
        super.visit(functionCallS, param);
        return null;
 	}
 	
 	// class FunctionCallE(String name, List<Expression> params)
 	// phase Identification { FunctionDefinition functionDefinition }
 	@Override
 	public Object visit(FunctionCallE functionCallE, Object param) {
 		var definition = functions.get(functionCallE.getName());
        if(definition == null) 
        	notifyError("Undefined function: " + functionCallE.getName(), functionCallE);
        else 
        	functionCallE.setFunctionDefinition(definition);
        super.visit(functionCallE, param);
        return null;
 	}
    
    // class StructDefinition(String name, List<Attribute> attributes)
 	@Override
 	public Object visit(StructDefinition structDefinition, Object param) {
 		Map<String, Attribute> attributes = new HashMap<String, Attribute>();
 		var definition = structs.get(structDefinition.getName());
 		if(definition != null) 
 			notifyError("Struct already defined: " + structDefinition.getName(), structDefinition);
 		else 
 			structs.put(structDefinition.getName(), structDefinition);
 		for(Attribute attribute : structDefinition.getAttributes()) {
 			if(attributes.get(attribute.getName()) != null) {
 				notifyError("Attribute already defined: " + attribute.getName(), attribute);
 			} else {
 				attributes.put(attribute.getName(), attribute);
 				attribute.getType().accept(this, param);
 			}
 		}
 		return null;
 	}
 	
 	// class StructType(String name)
 	@Override
 	public Object visit(StructType structType, Object param) {
 		var definition = structs.get(structType.getName());
 		if(definition == null) 
 			notifyError("Undefined struct: " + structType.getName(), structType);
 		else 
 			structType.setStructDefinition(definition);
 		return null;
 	}
 	
    // # --------------------------------------------------------
    // Métodos auxiliares recomendados (opcionales) -------------

    /*private void notifyError(String msg) {
        errorManager.notify("Identification", msg);
    }*/

    private void notifyError(String msg, Position position) {
        errorManager.notify("Identification", msg, position);
    }

    private void notifyError(String msg, AST node) {
        notifyError(msg, node.start());
    }

}
