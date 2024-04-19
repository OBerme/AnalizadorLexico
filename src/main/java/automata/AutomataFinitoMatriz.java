package automata;

public class AutomataFinitoMatriz extends AutomataFinito {
    private int[][] matriz;

    public AutomataFinitoMatriz(int numEstados, int tamAlfabeto, int[][] matrizTransicion) {
        super(numEstados, tamAlfabeto);
        this.matriz = matrizTransicion;
    }

    @Override
    public int transicion(int estado, int letra) {
        return matriz[estado][letra];
    }
}

