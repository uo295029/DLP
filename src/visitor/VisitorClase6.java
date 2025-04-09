// Generated with VGen 2.0.0


/*

Este fichero es un esqueleto para facilitar la creación de una clase visitor. Para
usarlo hay que realizar dos pasos:
1. Ubicar este código.
2. Completar cada método visit.

## Paso 1. Ubicación de este Código

Este esqueleto será SOBREESCRITO la próxima vez que se ejecuta VGen. Por ello, se debe
copiar su contenido antes de hacer cualquier cambio.

Hay dos opciones:

1) Si ya se tiene hecha una clase para el visitor, basta con copiar a dicha clase los
   métodos visit de este esqueleto (y los import) ignorando el resto.

2) Si no se tiene hecha aún la clase, este esqueleto vale como tal si se mueve a la
   carpeta deseada del proyecto y se le pone el package correspondiente a dicha ubicación.

Una vez hecho esto, ya se tendría un visitor que compilaría sin errores y que, al
ejecutarlo, recorrería todo el árbol (aunque sin hacer nada en cada nodo).


## Paso 2 Completar cada Método Visit

El visit generado para cada nodo se limita a recorrer sus hijos. El código de recorrido
se encuentra en la llamada a 'super.visit'. Los 'accept' comentados encima de cada
'super.visit' son sólo un recordatorio de lo que hace dicho método (son una copia de su
implementación, que se hereda de DefaultVisitor).

Por tanto, hay tres opciones a la hora de implementar cada visit:

1. Si en el visit de un nodo SÓLO SE NECESITA RECORRER sus hijos, se puede borrar
   completamente dicho visit de esta clase. Al no estar el método, se heredará de
   DefaultVisitor la misma implementación que se acaba de borrar. Es decir, en esta
   clase sólo será necesario dejar los visit que tengan alguna acción que realizar.

2. Si se necesita hacer alguna tarea adicional ANTES o DESPUÉS de recorrer todos
   los hijos, se debe añadir su código antes o después de la llamada a 'super.visit' (y
   se pueden borrar los 'accept' comentados).

3. Y, finalmente, si se necesita hacer alguna tarea INTERCALADA en el recorrido de los
   hijos (por ejemplo, comprobar su tipo), se debe borrar el 'super.visit' y descomentar
   los 'accept'. Así se tendría ya implementado el recorrido de los hijos, que es la
   estructura donde se intecalará el código de las acciones adicionales.

*/

// TODO: write package name
// package ...;
package visitor;

import ast.*;
import ast.definition.*;
import ast.statement.*;
import ast.expression.*;
import ast.type.*;


public class VisitorClase6 extends DefaultVisitor {

    public void process(AST ast) {
        ast.accept(this, null);
    }

    // Visit Methods --------------------------------------------------------------

	// class Program(List<Definition> definitions)

	// class VarDefinition(String name, Type type)
	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		System.out.print(varDefinition.getName() + ":");
		varDefinition.getType().accept(this, param);
		System.out.println(';');
		return null;
	}

	// class FunctionDefinition(String name, List<VarDefinition> params, Type type, List<VarDefinition> v, List<Statement> s)
	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {
		functionDefinition.getType().accept(this, param);
		System.out.print(" " + functionDefinition.getName() + "("); 
		functionDefinition.getParams().forEach(varDefinition -> {System.out.print(varDefinition.getName() + ":" + varDefinition.getType() + ", ");});
		System.out.println(") {");
		functionDefinition.getV().forEach(varDefinition -> varDefinition.accept(this, param));
		functionDefinition.getS().forEach(statement -> statement.accept(this, param));
		System.out.println("}");
		return null;
	}

	// class StructDefinition(String name, List<Attribute> attributes)
	@Override
	public Object visit(StructDefinition structDefinition, Object param) {
		System.out.println(structDefinition.getName() + "{");
		super.visit(structDefinition, param);
		System.out.println("}");
		return null;
	}

	// class Attribute(String name, Type type)
	@Override
	public Object visit(Attribute attribute, Object param) {
		System.out.print(attribute.getName() + ":");
		super.visit(attribute, param);
		return null;
	}

	// class Assignment(Expression left, Expression right)
	@Override
	public Object visit(Assignment assignment, Object param) {
		assignment.getLeft().accept(this, param);
		System.out.print(" = ");
		assignment.getRight().accept(this, param);
		System.out.println(";");
		return null;
	}

	// class FunctionCallS(String name, List<Expression> params)
	@Override
	public Object visit(FunctionCallS functionCallS, Object param) {
		System.out.print(functionCallS.getName() + "(");
		functionCallS.getParams().forEach(expression -> {expression.accept(this, param); System.out.print(", ");});
		System.out.println(");");
		return null;
	}

	// class Return(Optional<Expression> expression)
	@Override
	public Object visit(Return returnValue, Object param) {
		System.out.print("return ");
		super.visit(returnValue, param);
		System.out.println(";");
		return null;
	}

	// class If(Expression condition, List<Statement> yes, List<Statement> no)
	@Override
	public Object visit(If ifValue, Object param) {
		System.out.print("if(");
		ifValue.getCondition().accept(this, param);
		System.out.println(") {");
		ifValue.getYes().forEach(statement -> statement.accept(this, param));
		if(!ifValue.getNo().isEmpty()) {
			System.out.println("} else {");
			ifValue.getNo().forEach(statement -> statement.accept(this, param));
		}
		System.out.println("}");
		return null;
	}

	// class While(Expression condition, List<Statement> yes)
	@Override
	public Object visit(While whileValue, Object param) {
		System.out.print("while(");
		whileValue.getCondition().accept(this, param);
		System.out.println(") {");
		whileValue.getYes().forEach(statement -> statement.accept(this, param));
		System.out.println("}");
		return null;
	}

	// class Print(List<Expression> expressions)
	@Override
	public Object visit(Print print, Object param) {
		System.out.print("print ");
		super.visit(print, param);
		System.out.println(";");
		return null;
	}

	// class Printsp(List<Expression> expressions)
	@Override
	public Object visit(Printsp printsp, Object param) {
		System.out.print("printsp ");
		super.visit(printsp, param);
		System.out.println(";");
		return null;
	}

	// class Println(List<Expression> expressions)
	@Override
	public Object visit(Println println, Object param) {
		System.out.print("println ");
		super.visit(println, param);
		System.out.println(";");
		return null;
	}

	// class Read(Expression expression)
	@Override
	public Object visit(Read read, Object param) {
		System.out.print("read ");
		super.visit(read, param);
		System.out.println(";");
		return null;
	}

	// class Variable(String name)
	@Override
	public Object visit(Variable variable, Object param) {
		System.out.print(variable.getName());
		return null;
	}

	// class IntLiteral(String value)
	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		System.out.print(intLiteral.getValue());
		return null;
	}

	// class FloatLiteral(String value)
	@Override
	public Object visit(FloatLiteral floatLiteral, Object param) {
		System.out.print(floatLiteral.getValue());
		return null;
	}

	// class CharLiteral(String value)
	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		System.out.print(charLiteral.getValue());
		return null;
	}

	// class Arithmetic(Expression left, String operator, Expression right)
	@Override
	public Object visit(Arithmetic arithmetic, Object param) {
		arithmetic.getLeft().accept(this, param);
		System.out.print(" " + arithmetic.getOperator() + " ");
		arithmetic.getRight().accept(this, param);
		return null;
	}

	// class Negate(Expression expression)
	@Override
	public Object visit(Negate negate, Object param) {
		System.out.print("!");
		super.visit(negate, param);
		return null;
	}

	// class Logic(Expression left, String operator, Expression right)
	@Override
	public Object visit(Logic logic, Object param) {
		logic.getLeft().accept(this, param);
		System.out.print(" " + logic.getOperator() + " ");
		logic.getRight().accept(this, param);
		return null;
	}

	// class AttributeAccess(Expression left, String right)
	@Override
	public Object visit(AttributeAccess attributeAccess, Object param) {
		super.visit(attributeAccess, param);
		System.out.print("." + attributeAccess.getRight());
		return null;
	}

	// class Comparison(Expression left, String operator, Expression right)
	@Override
	public Object visit(Comparison comparison, Object param) {
		comparison.getLeft().accept(this, param);
		System.out.print(" " + comparison.getOperator() + " ");
		comparison.getRight().accept(this, param);
		return null;
	}

	// class ArrayAccess(Expression left, Expression right)
	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {
		arrayAccess.getLeft().accept(this, param);
		System.out.print("[");
		arrayAccess.getRight().accept(this, param);
		System.out.print("]");
		return null;
	}

	// class Cast(Type castType, Expression expression)
	@Override
	public Object visit(Cast cast, Object param) {
		System.out.print("<");
		cast.getCastType().accept(this, param);
		System.out.print("> (");
		cast.getExpression().accept(this, param);
		System.out.print(")");
		return null;
	}

	// class FunctionCallE(String name, List<Expression> params)
	@Override
	public Object visit(FunctionCallE functionCallE, Object param) {
		System.out.print(functionCallE.getName() + "(");
		functionCallE.getParams().forEach(expression -> {expression.accept(this, param); System.out.print(", ");});
		System.out.print(")");
		return null;
	}

	// class IntType()
	@Override
	public Object visit(IntType intType, Object param) {
		System.out.print("int");
		return null;
	}

	// class FloatType()
	@Override
	public Object visit(FloatType floatType, Object param) {
		System.out.print("float");
		return null;
	}

	// class CharType()
	@Override
	public Object visit(CharType charType, Object param) {
		System.out.print("char");
		return null;
	}

	// class ArrayType(int size, Type type)
	@Override
	public Object visit(ArrayType arrayType, Object param) {
		System.out.print("[");
		System.out.print(arrayType.getSize());
		System.out.print("]");
		super.visit(arrayType, param);
		return null;
	}

	// class StructType(String name)
	@Override
	public Object visit(StructType structType, Object param) {
		System.out.print(structType.getName());
		return null;
	}

	// class VoidType()
	@Override
	public Object visit(VoidType voidType, Object param) {
		System.out.print("void");
		return null;
	}

}
