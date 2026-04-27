package ejercicio07;

import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 20;
    static final String FICHERO = "boletin1/ejercicio07/agenda";
    static TreeMap<String, String> agenda = new TreeMap<>();

    public static void main(String[] args) {

        cargarAgenda();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- AGENDA ---");
            System.out.println("1. Nuevo contacto");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Mostrar todos");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> nuevoContacto(sc);
                case 2 -> buscarContacto(sc);
                case 3 -> mostrarTodos();
                case 4 -> guardarAgenda();
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 4);
    }

    static void nuevoContacto(Scanner sc) {
        if (agenda.size() >= MAX) {
            System.out.println("Agenda llena.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim().toLowerCase();

        if (agenda.containsKey(nombre)) {
            System.out.println("Ese contacto ya existe.");
            return;
        }

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine().trim();

        agenda.put(nombre, telefono);
        System.out.println("Contacto añadido.");
    }

    static void buscarContacto(Scanner sc) {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine().trim().toLowerCase();

        if (agenda.containsKey(nombre)) {
            System.out.println("Teléfono: " + agenda.get(nombre));
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    static void mostrarTodos() {
        if (agenda.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        System.out.println("--- CONTACTOS ---");
        for (Map.Entry<String, String> contacto : agenda.entrySet()) {
            System.out.println(contacto.getKey() + " -> " + contacto.getValue());
        }
    }

    static void guardarAgenda() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO))) {
            for (Map.Entry<String, String> contacto : agenda.entrySet()) {
                bw.write(contacto.getKey() + " " + contacto.getValue());
                bw.newLine();
            }
            System.out.println("Agenda guardada. ¡Hasta pronto!");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    static void cargarAgenda() {
        File f = new File(FICHERO);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");
                agenda.put(partes[0], partes[1]);
            }
            System.out.println("Agenda cargada: " + agenda.size() + " contactos.");
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}