package automata;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestAnalizadorLexico {

    @Test
    public void testBasicTokenRecognizer() {
        int[][] matrizTransicion = {{1,0}, {-1,1}};
        AutomataFinito automata = new AutomataFinitoMatriz(2, 1, matrizTransicion);
        automata.marcarFinal(1);

        AnalizadorLexico analizador = new AnalizadorLexico(automata, "a");
        analizador.addTokenIdentificativo(1, "Uno");


        Token token1 = analizador.nextToken(); // Esperado: "dos"
        assertEquals("Its correct the token","Uno", token1.getIdToken());
        assertFalse("The analicer haesn't more tokens", analizador.hasMoreTokens());
    }

    @Test
    public void testBasicTokenWithLeftGeneretor() {
        int[][] matrizTransicion = {{1,0}, {1,2}, {-1,2}};
        AutomataFinito automata = new AutomataFinitoMatriz(3, 2, matrizTransicion);
        automata.marcarFinal(2);

        AnalizadorLexico analizador = new AnalizadorLexico(automata, "baaab");
        analizador.addTokenIdentificativo(2, "Baaaa");

        Token token1 = analizador.nextToken();

        //check the left generate
        assertEquals("Its correct the token","Baaaa", token1.getIdToken());
        assertFalse("The analicer haesn't more tokens", analizador.hasMoreTokens());

        analizador = new AnalizadorLexico(automata, "aaaabb");
        analizador.addTokenIdentificativo(2, "Baaaa");

        token1 = analizador.nextToken();
        assertEquals("Its correct the token","Baaaa", token1.getIdToken());
        assertTrue("The analicer has more tokens", analizador.hasMoreTokens());
    }

    @Test
    public void testWithoutToken() {
        AutomataFinito automata = new AutomataFinitoMatriz(3, 2, null);
        AnalizadorLexico analizador = new AnalizadorLexico(automata, "");
        assertEquals("The analicer haesn't more tokens", analizador.nextToken(), null);
        assertFalse("The analicer haesn't more tokens", analizador.hasMoreTokens());
    }

    @Test
    public void testMore() {
        int[][] matrizTransicion = {{1,-1, -1}, {1,2,0}, {-1,-1,2}};
        AutomataFinito automata = new AutomataFinitoMatriz(3, 3, matrizTransicion);
        automata.marcarFinal(2);

        AnalizadorLexico analizador = new AnalizadorLexico(automata, "abccccc");
        analizador.addTokenIdentificativo(2, "abc");

        Token token1 = analizador.nextToken();

        //check the left generate
        assertEquals("Its correct the token","abc", token1.getIdToken());
        assertTrue("The analicer has't more tokens", analizador.hasMoreTokens());

        token1 = analizador.nextToken();
        assertEquals("Its correct the token","abc", token1.getIdToken());
        assertTrue("The analicer haesn't more tokens", analizador.hasMoreTokens());
    }


    @Test
    public void testCentralAutomataTest(){
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

        AnalizadorLexico ana = new AnalizadorLexico(this.automata, nextString);
        putTokensIdentif(ana, tokensIdentif);
        List<Token> tokens = getTokens(ana);

        for(Token token : tokens){
            assertTrue("The token should be like the list", )
        }

    }

    private List<Token> getTokens(AnalizadorLexico ana) {
        List<Token> tokenList = new ArrayList<>();
        while(ana.hasMoreTokens()){
            tokenList.add(ana.nextToken());
        }
        return tokenList;
    }

    private void putTokensIdentif(AnalizadorLexico ana, HashMap<Integer,String> tokensIdentif) {
        for(Integer nEstado : tokensIdentif.keySet()){
            ana.addTokenIdentificativo(nEstado, tokensIdentif.get(nEstado));
        }
    }
}