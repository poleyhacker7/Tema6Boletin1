package ejercicio5;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      
        String ruta1 = "boletin2/ejercicio5/Fichero1.txt";
        String ruta2 = "boletin2/ejercicio5/Fichero2.txt";

        try (BufferedReader br1 = new BufferedReader(new FileReader(ruta1));
             BufferedReader br2 = new BufferedReader(new FileReader(ruta2))) {

            String linea1, linea2;
            int numLinea = 0;
            boolean iguales = true;

            while (true) {
                linea1 = br1.readLine();
                linea2 = br2.readLine();

                if (linea1 == null && linea2 == null) break;

                numLinea++;

                if (linea1 == null || linea2 == null) {
                    System.out.println("Los ficheros tienen distinto número de líneas.");
                    System.out.println("Primera diferencia en línea " + numLinea + ".");
                    iguales = false;
                    break;
                }

                if (!linea1.equals(linea2)) {
                    int numChar = 0;

                    for (int i = 0; i < Math.min(linea1.length(), linea2.length()); i++) {
                        if (linea1.charAt(i) != linea2.charAt(i)) {
                            numChar = i + 1;
                            break;
                        }
                    }

                    if (numChar == 0) {
                        numChar = Math.min(linea1.length(), linea2.length()) + 1;
                    }

                    System.out.println("Los ficheros son DISTINTOS.");
                    System.out.println("Primera diferencia en línea " + numLinea +
                                       ", carácter " + numChar + ".");
                    System.out.println("  Fichero 1: \"" + linea1 + "\"");
                    System.out.println("  Fichero 2: \"" + linea2 + "\"");
                    iguales = false;
                    break;
                }
            }

            if (iguales) {
                System.out.println("Los ficheros son IGUALES.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}