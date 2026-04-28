package ejercicio7;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

    static final String FICHERO = "boletin2/ejercicio7/Clientes.txt";
    static TreeSet<Cliente> clientes = new TreeSet<>();

    public static void main(String[] args) {

        cargarClientes();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- BANCO ---");
            System.out.println("1. Alta cliente");
            System.out.println("2. Baja cliente");
            System.out.println("3. Listar clientes");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> altaCliente(sc);
                case 2 -> bajaCliente(sc);
                case 3 -> listarClientes();
                case 4 -> guardarClientes();
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 4);
    }

    static void altaCliente(Scanner sc) {
        System.out.print("DNI: ");
        String dni = sc.nextLine().trim();

        // Comprobar si ya existe
        for (Cliente c : clientes) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                System.out.println("Ya existe un cliente con ese DNI.");
                return;
            }
        }

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine().trim());

        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(sc.nextLine().trim());

        clientes.add(new Cliente(dni, nombre, fecha, saldo));
        System.out.println("Cliente dado de alta correctamente.");
    }

    static void bajaCliente(Scanner sc) {
        System.out.print("DNI del cliente a eliminar: ");
        String dni = sc.nextLine().trim();

        Cliente aEliminar = null;
        for (Cliente c : clientes) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                aEliminar = c;
                break;
            }
        }

        if (aEliminar != null) {
            clientes.remove(aEliminar);
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún cliente con ese DNI.");
        }
    }

    static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        double saldoMax = Double.MIN_VALUE;
        double saldoMin = Double.MAX_VALUE;
        double saldoTotal = 0;

        System.out.println("\n--- LISTADO DE CLIENTES ---");
        System.out.printf("%-12s %-25s %10s %5s%n", "DNI", "Nombre", "Saldo", "Edad");
        System.out.println("-".repeat(55));

        for (Cliente c : clientes) {
            System.out.printf("%-12s %-25s %10.2f %5d%n",
                c.getDni(), c.getNombre(), c.getSaldo(), c.getEdad());

            if (c.getSaldo() > saldoMax) saldoMax = c.getSaldo();
            if (c.getSaldo() < saldoMin) saldoMin = c.getSaldo();
            saldoTotal += c.getSaldo();
        }

        System.out.println("-".repeat(55));
        System.out.printf("Saldo máximo:  %10.2f€%n", saldoMax);
        System.out.printf("Saldo mínimo:  %10.2f€%n", saldoMin);
        System.out.printf("Saldo promedio:%10.2f€%n", saldoTotal / clientes.size());
    }

    static void cargarClientes() {
        File f = new File(FICHERO);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                String dni    = p[0];
                String nombre = p[1];
                LocalDate fecha = LocalDate.parse(p[2]);
                double saldo  = Double.parseDouble(p[3]);
                clientes.add(new Cliente(dni, nombre, fecha, saldo));
            }
            System.out.println("Clientes cargados: " + clientes.size());
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }

    static void guardarClientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO))) {
            for (Cliente c : clientes) {
                bw.write(c.toString());
                bw.newLine();
            }
            System.out.println("Datos guardados. ¡Hasta pronto!");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}