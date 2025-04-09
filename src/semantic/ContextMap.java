package semantic;

import java.util.*;

/*
* Implementaciónn de una tabla Hash con contextos.
* Permite:
* - Insertar símbolos (put) en el contexto actual.
* - Buscar tanto en el contexto actual (getFromTop) como en todos los contextos (getFromAny).
* - Crear y destruir contextos mediante las operaciones set y reset.
*
* La forma habitual de instanciarla será:
* 	ContextMap<String, DefinicionVariable> variables = new ContextMap<String, DefinicionVariable>();
*
*/
public class ContextMap<T> {

    private Stack<Map<String, T>> contextos = new Stack<>();

    public ContextMap() {
        set();
    }

    public void put(String nombre, T declaration) {
        contextos.peek().put(nombre, declaration);
    }

    public T getFromTop(String nombre) { // Vardefinitions
        return contextos.peek().get(nombre);
    }

    public T getFromAny(String nombre) { // Variables
        for (int i = contextos.size() - 1; i >= 0; i--) {
            Map<String, T> contexto = contextos.get(i);
            T declaration = contexto.get(nombre);
            if (declaration != null)
                return declaration;
        }
        return null;
    }

    public void set() {
        contextos.push(new HashMap<>());
    }

    public void reset() {
        contextos.pop();
    }

    // --------------------------------------------------
    // Para usar en depuración

    @Override
    public String toString() {
        var buffer = new StringBuilder();
        for (int i = contextos.size() - 1; i >= 0; i--) {
            buffer.append("------------------\nNivel " + i
                    + " (OJO: recordar que no están en el orden en el que se insertaron!!!)\n");
            for (Map.Entry<String, T> entry : contextos.get(i).entrySet())
                buffer.append("\t" + entry.getKey() + " = " + entry.getValue() + "\n");
            buffer.append('\n');
        }
        return buffer.toString();
    }

}
