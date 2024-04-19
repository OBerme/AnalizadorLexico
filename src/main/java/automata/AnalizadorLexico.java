package automata;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnalizadorLexico {
    private AutomataFinito automata;
    private String cadena;
    private int posActual;
    private Map<Integer, String> tokensIdentificados;
    private List<Token> historico;

    public AnalizadorLexico(AutomataFinito automata, String cadena) {
        this.automata = automata;
        this.cadena = cadena;
        this.posActual = 0;
        this.tokensIdentificados = new HashMap<>();
        this.historico = new ArrayList<>();
    }

    public void addTokenIdentificador(Integer estado, String token){
        this.tokensIdentificados.put(estado, token);
    }

    public Token nextToken() {
        if (!hasMoreTokens()) return null;

        int estadoActual = 0;
        int lastFinalState = -1;
        int lastFinalPos = -1;
        boolean exit = false;

        StringBuilder lexema = new StringBuilder();


        while (hasMoreTokens() && !exit) {
            //TODO: No lo veo
            int entrada = cadena.charAt(posActual) - 'a';  // Simplificación para 'a', 'b', 'c'
            int siguienteEstado = automata.transicion(estadoActual, entrada);


            if (siguienteEstado == -1) break; // No hay transición válida

            estadoActual = siguienteEstado;
            lexema.append(cadena.charAt(posActual));

            if (automata.esEstadoFinal(estadoActual)) {
                lastFinalState = estadoActual;
                lastFinalPos = posActual;
                exit = true;
            }

            posActual++;
        }

        posActual = lastFinalPos + 1; // Preparar para el próximo token
        return new Token(tokensIdentificados.get(lastFinalState), lexema.toString());
    }

    public boolean hasMoreTokens() {
        return posActual < cadena.length();

    }

    public List<Token> getHistorico() {
        return historico;
    }
}

