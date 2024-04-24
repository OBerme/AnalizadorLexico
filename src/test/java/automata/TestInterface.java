package automata;


import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TestInterface {
    @Test
    public void testTextInputSystemIn() {
        String simulatedInput = "3\n4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a Scanner object to read the input
        Scanner scanner = new Scanner(System.in);

        // Perform tests
        int value = scanner.nextInt();
        assertEquals("The first value is equals 3", 3, value);
        assertTrue("The user has more lines to analyze", scanner.hasNextLine());

        value = scanner.nextInt();
        assertEquals("The second value is equals 4", 4, value);

        // Close the scanner
        scanner.close();
    }
}