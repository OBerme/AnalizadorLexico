package automata;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnalizadorLexicoTest {
    @Test
    public void testAnalizadorBasico() {
        int[][] matrizTransicion = {{1,0}, {-1,1}};
        AutomataFinitoMatriz autoMatri = new AutomataFinitoMatriz(2,2,matrizTransicion);
        String cadena = "a";
        AnalizadorLexico anaLex = new AnalizadorLexico(autoMatri, cadena);

        autoMatri.marcarFinal(1);
        anaLex.addTokenIdentificador(1,"B");

        assertTrue("Has more tokens the robot", anaLex.hasMoreTokens());

        //Check the nexts tokens
        Token nextToken = new Token("B", "a");
        assertEquals("The next token is a", anaLex.nextToken(), nextToken);
        assertFalse("Hasn't more tokens the robot", anaLex.hasMoreTokens());
    }

    @Test
    public void testAnalizadorMoreTokens() {
        int[][] matrizTransicion = {{1,0}, {1,2}, {-1,2}};
        AutomataFinitoMatriz autoMatri = new AutomataFinitoMatriz(2,2,matrizTransicion);
        String cadena = "a";
        AnalizadorLexico anaLex = new AnalizadorLexico(autoMatri, cadena);

        autoMatri.marcarFinal(1);
        anaLex.addTokenIdentificador(1,"B");

        assertTrue("Has more tokens the robot", anaLex.hasMoreTokens());

        //Check the nexts tokens
        Token nextToken = new Token("B", "a");

        assertEquals("The next token is a", anaLex.nextToken(), nextToken);

    }
}