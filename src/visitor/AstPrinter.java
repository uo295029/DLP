// Generated with VGen 2.0.0

/**
 * AstPrinter. Utilidad que ayuda a validar un arbol AST:
 * - Muestra la estructura del árbol en HTML.
 * - Destaca los hijos/propiedades a null.
 * - Muestra a qué texto apuntan las posiciones de cada nodo (linea/columna)
 *   ayudando a decidir cual de ellas usar en los errores y generación de código.
 *
 * Esta clase se genera con VGen. El uso de esta clase es opcional (puede eliminarse del proyecto).
 *
 */

package visitor;

import ast.*;
import ast.definition.*;
import ast.statement.*;
import ast.expression.*;
import ast.type.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class AstPrinter implements Visitor {

    // Css classes
    private static final String OMITTED_SOURCE_TEXT_CLASS = "omittedSourceText";
    private static final String POS_CLASS = "vgen-pos";
    private static final String DOTS_CLASS = "vgen-dots";
    private static final String VALUE_CLASS = "vgen-value";
    private static final String BODY_CLASS = "vgen-body";
    private static final String CHILD_NAME_CLASS = "vgen-child-name";
    private static final String TABS_CLASS = "vgen-tabs";
    private static final String TYPE_CLASS = "vgen-type";
    private static final String NON_NODE_SOURCE_TEXT_CLASS = "vgen-nonNodeSourceText";
    private static final String NODE_SOURCE_TEXT_CLASS = "vgen-nodeSourceText";
    private static final String SOURCE_TEXT_CLASS = "vgen-sourceText";
    private static final String NULL_CLASS = "vgen-null";

    /**
    * toHtml. Muestra la estructura del AST indicando qué hay en las posiciones
    * (línea y columna) de cada nodo.
    *
    * @param sourceFile El fichero del cual se ha obtenido el AST.
    * @param root       El AST creado a partir de sourceFile.
    * @param filename   Nombre del fichero HMTL a crear con la traza del AST.
    */

    public static void toHtml(String sourceFile, AST root, String filename) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(filename.endsWith(".html") ? filename : filename + ".html"))) {

            writer.println(HEADER);

            if (root != null) {
                AstPrinter printer = new AstPrinter(writer, loadLines(sourceFile));
                printer.printNodes(root);
            } else
                writer.println("root == null");

            writer.println(FOOTER);

            System.err.println(ls + "AstPrinter: Fichero '" + filename + ".html' generado.");

        } catch (IOException e) {
            System.err.println(ls + "AstPrinter: No se ha podido crear el fichero " + filename);
            e.printStackTrace();
        }
    }

    private void printNodes(AST root) {
        writer.println("<div class=\"" + BODY_CLASS + "\">");

        writeDiv(span(CHILD_NAME_CLASS, root.getClass().getSimpleName()) + " = ");
        root.accept(this, Integer.valueOf(0));

        writer.println("</div>");

    }

    public static void toHtml(AST raiz, String filename) {
        toHtml(null, raiz, filename);
    }

    private AstPrinter(PrintWriter writer, List<String> sourceLines) {
        this(writer, sourceLines, 3);
    }

    private AstPrinter(PrintWriter writer, List<String> sourceLines, int tabSize) {
        this.writer = writer;
        this.sourceLines = sourceLines;
        this.tabSize = tabSize;

        tabulator = "&nbsp;".repeat(tabSize - 1);
    }

    //$ -------------------------------------------------------------------------------------
    // Aquí empiezan los 'visit' de los nodos


	@Override
	public Object visit(Program program, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printListOfNodesChild(indent + 1, "definitions", "List<Definition>", program.getDefinitions());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
		printUnknownFields(indent + 1, program, "definitions");
		return null;
	}

	@Override
	public Object visit(VarDefinition varDefinition, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "name", "String", varDefinition.getName());
        printNodeChild(indent + 1, "type", "Type", varDefinition.getType());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-2", "offset", "int", varDefinition.getOffset());
        printToString(indent + 1, "vgen-attribute-phase-2", "scope", "int", varDefinition.getScope());
		printUnknownFields(indent + 1, varDefinition, "name", "type", "offset", "scope");
		return null;
	}

	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "name", "String", functionDefinition.getName());
        printListOfNodesChild(indent + 1, "params", "List<VarDefinition>", functionDefinition.getParams());
        printNodeChild(indent + 1, "type", "Type", functionDefinition.getType());
        printListOfNodesChild(indent + 1, "v", "List<VarDefinition>", functionDefinition.getV());
        printListOfNodesChild(indent + 1, "s", "List<Statement>", functionDefinition.getS());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-2", "varsSize", "int", functionDefinition.getVarsSize());
        printToString(indent + 1, "vgen-attribute-phase-2", "paramsSize", "int", functionDefinition.getParamsSize());
		printUnknownFields(indent + 1, functionDefinition, "name", "params", "type", "v", "s", "varsSize", "paramsSize");
		return null;
	}

	@Override
	public Object visit(StructDefinition structDefinition, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "name", "String", structDefinition.getName());
        printListOfNodesChild(indent + 1, "attributes", "List<Attribute>", structDefinition.getAttributes());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
		printUnknownFields(indent + 1, structDefinition, "name", "attributes");
		return null;
	}

	@Override
	public Object visit(Attribute attribute, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "name", "String", attribute.getName());
        printNodeChild(indent + 1, "type", "Type", attribute.getType());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-2", "offset", "int", attribute.getOffset());
		printUnknownFields(indent + 1, attribute, "name", "type", "offset");
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "left", "Expression", assignment.getLeft());
        printNodeChild(indent + 1, "right", "Expression", assignment.getRight());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", assignment.getFunction());
		printUnknownFields(indent + 1, assignment, "left", "right", "function");
		return null;
	}

	@Override
	public Object visit(FunctionCallS functionCallS, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "name", "String", functionCallS.getName());
        printListOfNodesChild(indent + 1, "params", "List<Expression>", functionCallS.getParams());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-0", "functionDefinition", "FunctionDefinition", functionCallS.getFunctionDefinition());
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", functionCallS.getFunction());
		printUnknownFields(indent + 1, functionCallS, "name", "params", "function", "functionDefinition");
		return null;
	}

	@Override
	public Object visit(Return returnValue, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "expression", "Optional<Expression>", returnValue.getExpression().orElse(null));

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", returnValue.getFunction());
		printUnknownFields(indent + 1, returnValue, "expression", "function");
		return null;
	}

	@Override
	public Object visit(If ifValue, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "condition", "Expression", ifValue.getCondition());
        printListOfNodesChild(indent + 1, "yes", "List<Statement>", ifValue.getYes());
        printListOfNodesChild(indent + 1, "no", "List<Statement>", ifValue.getNo());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", ifValue.getFunction());
		printUnknownFields(indent + 1, ifValue, "condition", "yes", "no", "function");
		return null;
	}

	@Override
	public Object visit(While whileValue, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "condition", "Expression", whileValue.getCondition());
        printListOfNodesChild(indent + 1, "yes", "List<Statement>", whileValue.getYes());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", whileValue.getFunction());
		printUnknownFields(indent + 1, whileValue, "condition", "yes", "function");
		return null;
	}

	@Override
	public Object visit(Print print, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printListOfNodesChild(indent + 1, "expressions", "List<Expression>", print.getExpressions());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", print.getFunction());
		printUnknownFields(indent + 1, print, "expressions", "function");
		return null;
	}

	@Override
	public Object visit(Printsp printsp, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printListOfNodesChild(indent + 1, "expressions", "List<Expression>", printsp.getExpressions());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", printsp.getFunction());
		printUnknownFields(indent + 1, printsp, "expressions", "function");
		return null;
	}

	@Override
	public Object visit(Println println, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printListOfNodesChild(indent + 1, "expressions", "List<Expression>", println.getExpressions());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", println.getFunction());
		printUnknownFields(indent + 1, println, "expressions", "function");
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "expression", "Expression", read.getExpression());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "function", "FunctionDefinition", read.getFunction());
		printUnknownFields(indent + 1, read, "expression", "function");
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "name", "String", variable.getName());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-0", "varDefinition", "VarDefinition", variable.getVarDefinition());
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", variable.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", variable.isLvalue());
		printUnknownFields(indent + 1, variable, "name", "type", "lvalue", "varDefinition");
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "value", "String", intLiteral.getValue());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", intLiteral.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", intLiteral.isLvalue());
		printUnknownFields(indent + 1, intLiteral, "value", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(FloatLiteral floatLiteral, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "value", "String", floatLiteral.getValue());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", floatLiteral.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", floatLiteral.isLvalue());
		printUnknownFields(indent + 1, floatLiteral, "value", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "value", "String", charLiteral.getValue());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", charLiteral.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", charLiteral.isLvalue());
		printUnknownFields(indent + 1, charLiteral, "value", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "left", "Expression", arithmetic.getLeft());
        printNonNodeChild(indent + 1, "operator", "String", arithmetic.getOperator());
        printNodeChild(indent + 1, "right", "Expression", arithmetic.getRight());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", arithmetic.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", arithmetic.isLvalue());
		printUnknownFields(indent + 1, arithmetic, "left", "operator", "right", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(Negate negate, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "expression", "Expression", negate.getExpression());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", negate.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", negate.isLvalue());
		printUnknownFields(indent + 1, negate, "expression", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(Logic logic, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "left", "Expression", logic.getLeft());
        printNonNodeChild(indent + 1, "operator", "String", logic.getOperator());
        printNodeChild(indent + 1, "right", "Expression", logic.getRight());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", logic.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", logic.isLvalue());
		printUnknownFields(indent + 1, logic, "left", "operator", "right", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(AttributeAccess attributeAccess, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "left", "Expression", attributeAccess.getLeft());
        printNonNodeChild(indent + 1, "right", "String", attributeAccess.getRight());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", attributeAccess.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", attributeAccess.isLvalue());
		printUnknownFields(indent + 1, attributeAccess, "left", "right", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "left", "Expression", comparison.getLeft());
        printNonNodeChild(indent + 1, "operator", "String", comparison.getOperator());
        printNodeChild(indent + 1, "right", "Expression", comparison.getRight());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", comparison.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", comparison.isLvalue());
		printUnknownFields(indent + 1, comparison, "left", "operator", "right", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "left", "Expression", arrayAccess.getLeft());
        printNodeChild(indent + 1, "right", "Expression", arrayAccess.getRight());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", arrayAccess.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", arrayAccess.isLvalue());
		printUnknownFields(indent + 1, arrayAccess, "left", "right", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNodeChild(indent + 1, "castType", "Type", cast.getCastType());
        printNodeChild(indent + 1, "expression", "Expression", cast.getExpression());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", cast.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", cast.isLvalue());
		printUnknownFields(indent + 1, cast, "castType", "expression", "type", "lvalue");
		return null;
	}

	@Override
	public Object visit(FunctionCallE functionCallE, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "name", "String", functionCallE.getName());
        printListOfNodesChild(indent + 1, "params", "List<Expression>", functionCallE.getParams());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-0", "functionDefinition", "FunctionDefinition", functionCallE.getFunctionDefinition());
        printToString(indent + 1, "vgen-attribute-phase-1", "type", "Type", functionCallE.getType());
        printToString(indent + 1, "vgen-attribute-phase-1", "lvalue", "boolean", functionCallE.isLvalue());
		printUnknownFields(indent + 1, functionCallE, "name", "params", "type", "lvalue", "functionDefinition");
		return null;
	}

	@Override
	public Object visit(IntType intType, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
		printUnknownFields(indent + 1, intType, "");
		return null;
	}

	@Override
	public Object visit(FloatType floatType, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
		printUnknownFields(indent + 1, floatType, "");
		return null;
	}

	@Override
	public Object visit(CharType charType, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
		printUnknownFields(indent + 1, charType, "");
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "size", "int", arrayType.getSize());
        printNodeChild(indent + 1, "type", "Type", arrayType.getType());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
		printUnknownFields(indent + 1, arrayType, "size", "type");
		return null;
	}

	@Override
	public Object visit(StructType structType, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)
        printNonNodeChild(indent + 1, "name", "String", structType.getName());

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
        printToString(indent + 1, "vgen-attribute-phase-0", "structDefinition", "StructDefinition", structType.getStructDefinition());
		printUnknownFields(indent + 1, structType, "name", "structDefinition");
		return null;
	}

	@Override
	public Object visit(VoidType voidType, Object param) {

		int indent = ((Integer)param);

		// Imprimir los hijos (y recorrer si son nodos del AST)

		// Imprimir el 'toString()' de los atributos (pero no recorrer)
		printUnknownFields(indent + 1, voidType, "");
		return null;
	}



    //$ -------------------------------------------------------------------------------------
    // Métodos invocados desde los métodos visit para imprimir los hijos

    /**
     * Método para imprimir aquel Child que sea un nodo del AST. Después de imprimirlo recorre sus hijos.
     */
    private void printNodeChild(int indent, String name, String type, AST node) {

        if (node == null) {
            writeDivWithTabs(indent,
                    span(CHILD_NAME_CLASS, name) + " = " + valueSpan(null) + "  " + span(TYPE_CLASS, type));
            return;
        }

        String text = name + " " + node.getClass().getSimpleName() + " ="; // Usado sólo para calcular tamaño sin tag 'span'
        String sourceText = getAlignedSourceText(indent, text.length(), node);

        writeDivWithTabs(indent, span(CHILD_NAME_CLASS, name) + " " + span(TYPE_CLASS, node.getClass().getSimpleName())
                + " =" + sourceText);

        node.accept(this, indent);
    }

    /**
     * Método para imprimir aquel Child que sea una lista de nodos del AST (pe, 'sentence*').
     * Imprime y recorre cada nodo de la lista.
     */
    private void printListOfNodesChild(int indent, String name, String type, List<? extends AST> nodes) {
        writeDivWithTabs(indent, span(CHILD_NAME_CLASS, name) + escapedSpan(TYPE_CLASS, " " + type) + " =");

        if (nodes != null)
            for (int i = 0; i < nodes.size(); i++) {
                AST node = nodes.get(i);

                String text = i + ": " + node.getClass().getSimpleName() + " ="; // Usado sólo para calcular tamaño sin tag 'span'
                String sourceText = getAlignedSourceText(indent + 1, text.length(), node);

                writeDivWithTabs(indent + 1, span(CHILD_NAME_CLASS, i + "") + ": "
                        + span(TYPE_CLASS, node.getClass().getSimpleName()) + " =" + sourceText);

                node.accept(this, indent + 1);
            }
        else
            writer.print(" " + valueSpan(null));
    }

    /**
     * Método para imprimir todo Child que no sea visitable, es decir, todo hijo que no sea un
     * nodo o lista de nodos.
     * Se limita a imprimir el 'toString()' de dicho hijo.
     */
    private void printNonNodeChild(int indent, String name, String type, Object value) {
        printToString(indent, CHILD_NAME_CLASS, name, type, value);
    }

    /**
     * Dado un nodo, busca e imprime todo aquel miembro que no se haya declarado en la Gramática Abstracta.
     * La razón por la que no se hace todo con Introspección es porque se quiere respetar el orden de
     * los hijos declarados en la Gramática Abstracta y, por ello, éstos se imprimen antes en el
     * 'visit'.
     * El parámetro knownFields son los miembros ya imprimidos del nodo y que no deben repetirse.
     */
    private void printUnknownFields(int indent, AST node, String... knownFields) {

        List<String> knownNames = new ArrayList<>(Arrays.asList(knownFields));
        knownNames.addAll(List.of("start", "end"));

        // Extraer todos las variables de instancia de la clase (propios o derivados)
        List<java.lang.reflect.Field> allFields = new ArrayList<>();
        Class<?> currentClass = node.getClass();
        while (currentClass != null && currentClass != Object.class) {
            java.util.Collections.addAll(allFields, currentClass.getDeclaredFields());
            currentClass = currentClass.getSuperclass();
        }

        // Imprimir el 'toString' de aquellas que no se hayan impreso ya y no sean static
        for (java.lang.reflect.Field field : allFields) {
            if ((!knownNames.contains(field.getName()))
                    && (!java.lang.reflect.Modifier.isStatic(field.getModifiers()))) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(node);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    value = "!!Error accesing value by introspection";
                }
                printToString(indent, "unknown-field", field.getName(), field.getType().getSimpleName(), value);
            }
        }
    }

    //$ -------------------------------------------------------------------------------------
    // Métodos auxiliares usados por los métodos anteriores

    // Imprime el 'toString()' del value que se le pase
    private void printToString(int indent, String cssClass, String name, String type, Object value) {

        if (type.equals("String"))
            type = "string"; // Queda mejor en minúsculas

        String typeText = escapedSpan(TYPE_CLASS, "  " + type);

        // No imprimir el tipo del miembro si es un AST y su valor es del mismo tipo (es decir, el
        // valor no es un tipo derivado y, por tanto, son iguales). Dicho tipo ya aparecerá en el
        // toString() del nodo.
        if (value instanceof AST && type.equals(value.getClass().getSimpleName()))
            typeText = "";

        writeDivWithTabs(indent, span(cssClass, name) + " = " + valueSpan(value) + typeText);
    }

    private void writeDiv(String text) {
        writer.println("<div>" + text + "</div>");
    }

    private void writeDivWithTabs(int indent, String text) {
        writeDiv(getTabuladores(indent) + text);
    }

    private String getTabuladores(int count) {
        var cadena = new StringBuilder();
        for (int i = 0; i < count; i++)
            cadena.append((i % 2 == 0 ? '|' : '.') + tabulator);
        return span(TABS_CLASS, cadena.toString());
    }

    private static String span(String cssClass, String text) {
        return "<span class=\"" + cssClass + "\">" + text + "</span>";
    }

    private static String escapedSpan(String cssClass, String text) {
        return span(cssClass, text.replace("<", "&lt;").replace(">", "&gt;"));
    }

    private static String valueSpan(Object value) {
        final int MAX_LENGTH = 46;

        if (value == null)
            return span(NULL_CLASS, "null");

        String text = value.toString();
        if (text.length() > MAX_LENGTH)
            text = text.substring(0, MAX_LENGTH) + "...";
        if (value instanceof String)
            text = '"' + text + '"';
        return escapedSpan(VALUE_CLASS, text);
    }

    // -----------------------------------------------------------------
    // Métodos para mostrar las Posiciones

    private String getAlignedSourceText(int indent, int textLength, AST node) {

        final int width = 75;

        int neededSpaces = width - (indent * tabSize) - textLength;
        var spaces = new StringBuilder();
        for (int i = 0; i < neededSpaces / 2; i++)
            spaces.append(" .");
        if (neededSpaces % 2 == 1)
            spaces.append(" ");

        return span(DOTS_CLASS, spaces.toString()) + getSourceText(node);
    }

    private String getSourceText(AST node) {

        String text = "[" + node.start() + " " + node.end() + "]";

        final int PADDING_LENGTH = 14;
        String paddedText = (text.length() < PADDING_LENGTH)
                ? text + "&nbsp;".repeat(PADDING_LENGTH - text.length())
                : text;

        text = span(POS_CLASS, paddedText);
        text = text.replace("null", "<span class=\"" + NULL_CLASS + "\">null</span>");
        String sourceText = findSourceText(node);
        if (sourceText != null)
            text = text + sourceText;
        return text;
    }


    private boolean isValidPosition(Position position) {
        return position != null && position.getLine() > 0 && position.getColumn() > 0;
    }

    private String findSourceText(AST node) {
        if (sourceLines.isEmpty())
            return null;

        Position start = node.start();
        Position end = node.end();

        var validPositions = isValidPosition(start) && isValidPosition(end) && start.lessThan(end);
        if (!validPositions)
            return null;

        // Single line source text
        if (start.getLine() == end.getLine()) {
            String line = sourceLines.get(start.getLine() - 1);

            var firstCharPosition = start.getColumn() - 1;
            var nextCharPosition = end.getColumn() - 1;

            var beforeText = line.substring(0, firstCharPosition);
            var text = line.substring(firstCharPosition, nextCharPosition);
            var afterText = line.substring(nextCharPosition);

            return span(SOURCE_TEXT_CLASS,
                    escapedSpan(NON_NODE_SOURCE_TEXT_CLASS, beforeText.stripLeading())
                            + escapedSpan(NODE_SOURCE_TEXT_CLASS, text)
                            + escapedSpan(NON_NODE_SOURCE_TEXT_CLASS, afterText.stripTrailing()));
        }

        // Multiline source text
        String firstLine = sourceLines.get(start.getLine() - 1);
        var firstCharPosition = start.getColumn() - 1;
        var beforeText = firstLine.substring(0, firstCharPosition);
        var firstLineText = firstLine.substring(firstCharPosition);

        String lastLine = sourceLines.get(end.getLine() - 1);
        var nextCharPosition = end.getColumn() - 1;
        var lastLineText = lastLine.substring(0, nextCharPosition);
        var afterText = lastLine.substring(nextCharPosition);

        return span(SOURCE_TEXT_CLASS,
                escapedSpan(NON_NODE_SOURCE_TEXT_CLASS, beforeText.stripLeading())
                        + escapedSpan(NODE_SOURCE_TEXT_CLASS, firstLineText.stripTrailing())
                        + span(OMITTED_SOURCE_TEXT_CLASS, " &bull;&bull;&bull; ")
                        + escapedSpan(NODE_SOURCE_TEXT_CLASS, lastLineText.stripLeading())
                        + escapedSpan(NON_NODE_SOURCE_TEXT_CLASS, afterText.stripTrailing()));
    }

    private static List<String> loadLines(String sourceFile) {
        if (sourceFile == null)
            return java.util.Collections.emptyList();

        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
            String line;
            while ((line = br.readLine()) != null)
                lines.add(line);

        } catch (FileNotFoundException e) {
            System.out.println("Warning. No se pudo encontrar el fichero fuente '" + sourceFile
                    + "'. No se mostrará información de posición.");
        } catch (IOException e) {
            System.out.println("Warning. Error al leer del fichero fuente '" + sourceFile
                    + "'. No se mostrará información de posición.");
        }
        return lines;
    }

    //$ -------------------------------------------------------------------------------------
    // Texto literal

    private static final String HEADER = """
            <html>
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>AstPrinter</title>
                <style type="text/css">
                    .vgen-body {
                        font-family: monospace;
                        color: #404040;
                    }
                    .vgen-div {
                        white-space: pre;
                    }
                    .vgen-tabs {
                        color: #96c0d9;
                    }
                    .vgen-child-name {
                        color: #2A5873;
                    }
                    .vgen-attribute-phase-0 {
                        color: #04ab04ff;
                    }
                    .vgen-attribute-phase-1 {
                        color: #ab0479ff;
                    }
                    .vgen-attribute-phase-2 {
                        color: #abab04ff;
                    }
                    .vgen-unknown-field {
                        color: grey;
                    }
                    .vgen-type {
                        color: #A7CADF;
                    }
                    .vgen-value {
                        color: #D6630E;
                    }
                    .vgen-dots {
                        color: #7BB0CF;
                    }
                    .vgen-pos {
                        color: #7BB0CF;
                    }
                    .vgen-nonNodeSourceText {
                    }
                    .vgen-nodeSourceText {
                        background-color: #d7f9d6;
                        border-radius: 4px;
                        padding-left: 3px;
                        padding-right: 3px;
                    }
                    .vgen-omittedSourceText {
                        color: #a6f2a4ff;
                    }
                    .vgen-null {
                        color: white;
                        background-color: #c7655b;
                        border-radius: 7px;
                        padding-left: 4px;
                        padding-right: 4px;
                    }
                </style>
            </head>
            <body>
            """;

    private static final String FOOTER = """

            </body>
            </html>
            """;

    //$ -----------------------------------------------------------------
    // Variables de instancia

    private List<String> sourceLines;
    private static String ls = System.getProperty("line.separator");
    private PrintWriter writer;

    private String tabulator;
    private int tabSize;


}
