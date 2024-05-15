package automata;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    public void testFinalPractice(){
        int[][] matrizTransicion = {
                {1,2,3},
                {4,8,5},
                {-1,2,-1},
                {-1,-1,6},
                {7,-1,5},
                {-1,-1,-1},
                {-1,-1,6},
                {7,-1,5},
                {-1,8,-1}
        };
        AutomataFinito automata = new AutomataFinitoMatriz(9, 3, matrizTransicion);
        int[] finales = {2,3,4,5,6,8};
        for(int i = 0; i< finales.length;i++){
            automata.marcarFinal(finales[i]);
        }
        HashMap<Integer, String> tokensIdentif = new HashMap<>();
        tokensIdentif.put(2,"C");
        tokensIdentif.put(3,"D");
        tokensIdentif.put(4,"E");
        tokensIdentif.put(5,"F");
        tokensIdentif.put(6,"G");
        tokensIdentif.put(8,"I");

        AnalizadorLexico analizador = new AnalizadorLexico(automata, "aa");

        for(Iterator<Integer> keysIte =  tokensIdentif.keySet().iterator();keysIte.hasNext();){
            Integer nKey = keysIte.next();
            analizador.addTokenIdentificativo(nKey, tokensIdentif.get(nKey));
        }

        // Matriz con la cadena a analizar, el token correspondiente y si se espera que haya mas tokens siguientes
        Object[][] estadosTest = {
                {"aa", "E", false},
                {"abbbbb", "I", true},
                {"a", null, false},
                {"c", "D", false}
        };
        int longMatri = estadosTest.length;
        for(int i = 0; i < longMatri; i++){
            String nextState = (String)estadosTest[i][1];

            analizador.setCadena((String)estadosTest[i][0]);
            //check the left generate

            Token nToken = analizador.nextToken();
            if(nextState != null)
                assertEquals("Its correct the token " + nextState,nextState,  nToken.getIdToken());
            else
                assertNull("Its correct the token "+ nextState, nToken);


            boolean hasMoreTokens = (Boolean)estadosTest[i][2];
            assertEquals("The analicer has't more tokens " + nextState,
                    hasMoreTokens, analizador.hasMoreTokens());
        }

        //With more than one transition
        Object[][] mEstadosTest = {
                {"aaac", "F"},
                {"cccccc", "G"}
        };

        longMatri = mEstadosTest.length;
        for(int i = 0; i < longMatri; i++){
            String nextState = (String)mEstadosTest[i][1];

            analizador.setCadena((String)mEstadosTest[i][0]);
            //check the left generate

            Token nToken = analizador.nextToken();

            assertNotNull("Its correct the next token "+ nextState, nToken);
            assertTrue("The analicer has't more tokens " + nextState,
                    analizador.hasMoreTokens());

            nToken = analizador.nextToken();
            assertEquals("Its correct the token " + nextState,nextState,  nToken.getIdToken());
        }


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

//        AnalizadorLexico ana = new AnalizadorLexico(this.automata, nextString);
//        putTokensIdentif(ana, tokensIdentif);
//        List<Token> tokens = getTokens(ana);
//
//        for(Token token : tokens){
//            assertTrue("The token should be like the list", )
//        }

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