package automata;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalizadorLexico {
    private AutomataFinito automata;
    private String cadena;
    private int posActual;
    private int actualState;

    private Map<Integer, String> tokensIdentificados;
    private List<Token> historico;

    public AnalizadorLexico(AutomataFinito automata, String cadena) {
        this.automata = automata;
        this.cadena = cadena;
        this.posActual = 0;
        this.tokensIdentificados = new HashMap<>();
        this.historico = new ArrayList<>();
        this.actualState = 0;
    }

    public Token nextToken() {
        if (posActual <= cadena.length()) return null;

        int estadoActual = this.posActual;
        int lastFinalState = -1;
        int lastFinalPos = -1;
        int siguienteEstado=0;
        StringBuilder lexema = new StringBuilder();

        while (lastFinalState == -1 && posActual < cadena.length()) {
            int entrada = cadena.charAt(posActual) - 'a';  // Simplificación para 'a', 'b', 'c'
            siguienteEstado = automata.transicion(estadoActual, entrada);

            if (siguienteEstado == -1) break; // No hay transición válida

            estadoActual = siguienteEstado;
            lexema.append(cadena.charAt(posActual));

            if (automata.esEstadoFinal(estadoActual)) {
                lastFinalState = estadoActual;
                lastFinalPos = posActual;
            }

            posActual++;
        }

        posActual = lastFinalPos + 1; // Preparar para el próximo token
        if(lastFinalState != -1)
            this.actualState = lastFinalState;

        return (lastFinalState != -1) ? new Token(tokensIdentificados.get(lastFinalState), lexema.toString()) : null;
    }

    public boolean hasMoreTokens() {
        if( posActual < cadena.length() ) return false;
        int posiActu = this.posActual;

        Token nexToken = this.nextToken();
        this.posActual = posiActu;

        return nexToken != null;
    }

    public List<Token> getHistorico() {
        return historico;
    }

    public void addTokenIdentificativo(Integer estado, String stateValue){
        this.tokensIdentificados.put(estado, stateValue);
    }
}

