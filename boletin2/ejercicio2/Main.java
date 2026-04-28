package ejercicio2	;

import java.io.*;
import java.util.*;

public class Main {

    static final String FICHERO = "boletin2/ejercicio2/Firmas.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- LIBRO DE FIRMAS ---");
            System.out.println("1. Mostrar firmas");
            System.out.println("2. Insertar firma");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> mostrarFirmas();
                case 2 -> insertarFirma(sc);
                case 3 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 3);
    }

    static List<String> cargarFirmas() {
        List<String> firmas = new ArrayList<>();
        File f = new File(FICHERO);
        if (!f.exists()) return firmas;

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    firmas.add(linea.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        return firmas;
    }

    static void mostrarFirmas() {
        List<String> firmas = cargarFirmas();

        if (firmas.isEmpty()) {
            System.out.println("El libro de firmas está vacío.");
            return;
        }

        System.out.println("\n--- FIRMAS ---");
        for (int i = 0; i < firmas.size(); i++) {
            System.out.println((i + 1) + ". " + firmas.get(i));
        }
    }

    static void insertarFirma(Scanner sc) {
        System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine().trim();

        List<String> firmas = cargarFirmas();

        for (String firma : firmas) {
            if (firma.equalsIgnoreCase(nombre)) {
                System.out.println("Ese nombre ya está en el libro de firmas.");
                return;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO, true))) {
            bw.write(nombre);
            bw.newLine();
            System.out.println("Firma añadida correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}