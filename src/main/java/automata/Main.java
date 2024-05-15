package automata;

import automata.appInterface.AutomataInterfaceConsole;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[][] matrizTransicion = {
                {1,2,3},
                {5,4,-1},
                {-1,4,-1},
                {-1,-1,3},
                {-1,4,-1},
                {6,7,-1},
                {6,4,-1},
                {-1,8,-1},
                {-1,4,-1}
        };
        AutomataFinito automata = new AutomataFinitoMatriz(10, 3, matrizTransicion);
        int[] finales = {1,2,3,4,8};
        HashMap<Integer, String> tokensIdentif = new HashMap<>();
        tokensIdentif.put(1,"B");
        tokensIdentif.put(2,"C");
        tokensIdentif.put(3,"D");
        tokensIdentif.put(4,"E");
        tokensIdentif.put(8,"I");

        for(int i = 0; i< finales.length;i++){
            automata.marcarFinal(finales[i]);
        }

        new AutomataInterfaceConsole(automata, tokensIdentif);


    }
}