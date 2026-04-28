package ejercicio1;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("boletin2/ejercicio1/Carta.txt"))) {

            int caracteres = 0;
            int lineas = 0;
            int palabras = 0;

            String linea;
            while ((linea = br.readLine()) != null) {
                lineas++;
                caracteres += linea.length();

                if (!linea.trim().isEmpty()) {
                    palabras += linea.trim().split(" ").length;
                }
            }

            System.out.println("Caracteres: " + caracteres);
            System.out.println("Líneas:     " + lineas);
            System.out.println("Palabras:   " + palabras);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}