package ejercicio4;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        // Cargar codec
        String alfabeto = "";
        String cifrado  = "";

        try (BufferedReader br = new BufferedReader(new FileReader("boletin2/ejercicio4/Codec.txt"))) {
            alfabeto = br.readLine().trim();
            cifrado  = br.readLine().trim();
        } catch (IOException e) {
            System.out.println("Error al leer codec: " + e.getMessage());
            return;
        }

        // Codificar mensaje
        try (BufferedReader br = new BufferedReader(new FileReader("boletin2/ejercicio4/Mensaje.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("boletin2/ejercicio4/MensajeCifrado.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(codificar(linea, alfabeto, cifrado));
                bw.newLine();
            }

            System.out.println("Mensaje cifrado guardado en MensajeCifrado.txt");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static String codificar(String linea, String alfabeto, String cifrado) {
        StringBuilder resultado = new StringBuilder();

        for (char c : linea.toCharArray()) {
            int index = alfabeto.indexOf(Character.toLowerCase(c));

            if (index != -1) {
                char cCifrado = cifrado.charAt(index);
                // Respetar mayúsculas
                if (Character.isUpperCase(c)) {
                    cCifrado = Character.toUpperCase(cCifrado);
                }
                resultado.append(cCifrado);
            } else {
                // Espacios, números, signos -> sin cambio
                resultado.append(c);
            }
        }

        return resultado.toString();
    }
}