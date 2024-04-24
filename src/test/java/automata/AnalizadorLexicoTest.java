package automata;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class AnalizadorLexicoTest {

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
        assertTrue("The analicer haesn't more tokens", analizador.hasMoreTokens());


    }

}