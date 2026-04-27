	package ejercicio06;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<>();

        // Lectura
        try (BufferedReader br = new BufferedReader(new FileReader("boletin1/ejercicio06/Numeros"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                numeros.add(Integer.parseInt(linea.trim()));
            }

        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
            return;
        }

        // Ordenar
        Collections.sort(numeros);

        // Escritura
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("boletin1/ejercicio06/NumerosOrdenados"))) {

            for (int n : numeros) {
                bw.write(String.valueOf(n));
                bw.newLine();
            }

            System.out.println("Fichero ordenado guardado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }
}