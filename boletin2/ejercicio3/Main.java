package ejercicio3;

import java.io.*;
import java.util.Scanner;

public class Main {

    static final int LINEAS_PAGINA = 24;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (BufferedReader in = new BufferedReader(new FileReader("boletin2/ejercicio3/Texto.txt"))) {

            String linea;
            int contador = 0;

            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
                contador++;

                if (contador == LINEAS_PAGINA) {
                    System.out.print("\n--- Pulsa Enter para continuar... ---");
                    sc.nextLine();
                    contador = 0;
                }
            }

            System.out.println("\n--- Fin del fichero ---");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}