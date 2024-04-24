package automata;

public abstract class AutomataFinito {
    protected int numEstados;
    protected boolean[] finales;
    protected int tamAlfabeto;

    public AutomataFinito(int numEstados, int tamAlfabeto) {
        this.numEstados = numEstados;
        this.tamAlfabeto = tamAlfabeto;
        this.finales = new boolean[numEstados];
    }

    public AutomataFinito(int numEstados, int tamAlfabeto, boolean[] finales) {
        this(numEstados, tamAlfabeto);
        this.finales = finales;
    }

    public void marcarFinal(int estado) {
        if (estado < numEstados) {
            finales[estado] = true;
        }
    }

    public boolean esEstadoFinal(int estado) {
        return finales[estado];
    }

    public abstract int transicion(int estado, int letra);
}
