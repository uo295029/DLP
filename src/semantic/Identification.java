package semantic;

import java.util.*;

import ast.*;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import ast.expression.CharLiteral;
import ast.expression.Expression;
import ast.expression.FloatLiteral;
import ast.expression.FunctionCallE;
import ast.expression.IntLiteral;
import ast.expression.Variable;
import ast.statement.FunctionCallS;
import ast.type.CharType;
import ast.type.FloatType;
import ast.type.IntType;
import ast.type.StructType;
import ast.type.Type;
import main.ErrorManager;
import visitor.DefaultVisitor;

// This class will be implemented in identification phase

public class Identification extends DefaultVisitor {

    private ErrorManager errorManager;
    private ContextMap<VarDefinition> variables = new ContextMap<VarDefinition>();
    private List<FunctionDefinition> functions = new ArrayList<FunctionDefinition>();
    private Map<String, StructDefinition> structs = new HashMap<String, StructDefinition>();

    public Identification(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    public void process(AST ast) {
        ast.accept(this, null);
    }

    // # ----------------------------------------------------------
    
    @Override
    public Object visit(VarDefinition varDefinition, Object param) {
    	if (varDefinition.getScope() != 1 && varDefinition.getScope() != 2) 
    		varDefinition.setScope(0); // Scope 0 es para variables globales
        var definition = variables.getFromTop(varDefinition.getName());
        if (definition != null) 
        	notifyError("Variable already defined: " + varDefinition.getName(), varDefinition);
        else 
        	variables.put(varDefinition.getName(), varDefinition);
        varDefinition.getType().accept(this, param);
        return null;
    }

    @Override
    public Object visit(Variable variable, Object param) {
        var definition = variables.getFromAny(variable.getName());
        if(definition == null) 
        	notifyError("Undefined variable: " + variable.getName(), variable);
        else 
        	variable.setVarDefinition(definition);
        return null;
    }
    
 	@Override
 	public Object visit(FunctionDefinition functionDefinition, Object param) {
 		FunctionDefinition fd = findFunctionDef(functionDefinition.getName(), functionDefinition.getParams());
 		if(fd != null) {
 			notifyError("Function already defined with the same params: " + functionDefinition.getName(), functionDefinition);
 		} else {
 			functionDefinition.getType().accept(this, param);
 			functions.add(functionDefinition);
 			variables.set();
 			functionDefinition.getParams().forEach(varDefinition -> {
 				varDefinition.setScope(1); // Scope 1 es para los parámetros
 				varDefinition.accept(this, param);
 			});
 			functionDefinition.getV().forEach(varDefinition -> {
 	            varDefinition.setScope(2); // Scope 2 es para variables locales
 	            varDefinition.accept(this, param);
 	        });
 			functionDefinition.getS().forEach(statement -> statement.accept(this, param));
 			variables.reset();
 		}
 		return null;
 	}
    
 	@Override
 	public Object visit(FunctionCallS functionCallS, Object param) {
 		for (Expression e : functionCallS.getParams()) {
            e.accept(this,param);
        }
 		var definition = findFunctionCall(functionCallS.getName(), functionCallS.getParams());
        if(definition == null) 
        	notifyError("Undefined function: " + functionCallS.getName(), functionCallS);
        else 
        	functionCallS.setFunctionDefinition(definition);
        super.visit(functionCallS, param);
        return null;
 	}
 	
 	@Override
 	public Object visit(FunctionCallE functionCallE, Object param) {
 		for (Expression e : functionCallE.getParams()) {
            e.accept(this,param);
        }
 		var definition = findFunctionCall(functionCallE.getName(), functionCallE.getParams());
        if(definition == null) 
        	notifyError("Undefined function: " + functionCallE.getName(), functionCallE);
        else 
        	functionCallE.setFunctionDefinition(definition);
        super.visit(functionCallE, param);
        return null;
 	}
    
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
 	
 	@Override
 	public Object visit(StructType structType, Object param) {
 		var definition = structs.get(structType.getName());
 		if(definition == null) 
 			notifyError("Undefined struct: " + structType.getName(), structType);
 		else 
 			structType.setStructDefinition(definition);
 		return null;
 	}
 
    @Override
    public Object visit(IntLiteral intLiteral, Object param) {
        intLiteral.setType(new IntType());
        return null;
    }

    @Override
    public Object visit(FloatLiteral floatLiteral, Object param) {
        floatLiteral.setType(new FloatType());
        return null;
    }
    
    @Override
    public Object visit(CharLiteral charLiteral, Object param) {
        charLiteral.setType(new CharType());
        return null;
    }
    // # --------------------------------------------------------
    // Métodos auxiliares recomendados (opcionales) -------------

 	private FunctionDefinition findFunction(String name, List<Type> params) {
 		boolean sameParams = false;
 		for(FunctionDefinition fd : functions) {
 			if(fd.getName().equals(name) && fd.getParams().size() == params.size()) {
 				sameParams = true;
 				for(int i = 0; i < params.size(); i++) {
 					if(params.get(i).getClass() == fd.getParams().get(i).getType().getClass()) {
 						sameParams = false;
 					}
 				}
 				if(sameParams) return fd;
 			}
 		}
 		return null;
 	}
 	
 	private FunctionDefinition findFunctionCall(String name, List<Expression> params) {
 		List<Type> types = new ArrayList<Type>();
 		for(Expression p : params) {
 			types.add(p.getType());
 		}
 		return findFunction(name, types);
 	}
 	
 	private FunctionDefinition findFunctionDef(String name, List<VarDefinition> params) {
 		List<Type> types = new ArrayList<Type>();
 		for(VarDefinition p : params) {
 			types.add(p.getType());
 		}
 		return findFunction(name, types);
 	}
 	
    private void notifyError(String msg, Position position) {
        errorManager.notify("Identification", msg, position);
    }

    private void notifyError(String msg, AST node) {
        notifyError(msg, node.start());
    }

}
