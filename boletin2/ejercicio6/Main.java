package ejercicio6;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("boletin2/ejercicio6/Deportistas.txt"))) {

            // Saltar cabecera
            br.readLine();

            String nombreMayorEdad = "", nombreMayorPeso = "", nombreMayorEstatura = "";
            int mayorEdad = Integer.MIN_VALUE;
            double mayorPeso = Double.MIN_VALUE;
            double mayorEstatura = Double.MIN_VALUE;

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                String nombre   = partes[0].trim();
                int edad        = Integer.parseInt(partes[1].trim());
                double peso     = Double.parseDouble(partes[2].trim());
                double estatura = Double.parseDouble(partes[3].trim());

                if (edad > mayorEdad) {
                    mayorEdad = edad;
                    nombreMayorEdad = nombre;
                }
                if (peso > mayorPeso) {
                    mayorPeso = peso;
                    nombreMayorPeso = nombre;
                }
                if (estatura > mayorEstatura) {
                    mayorEstatura = estatura;
                    nombreMayorEstatura = nombre;
                }
            }

            System.out.println("Mayor edad:     " + nombreMayorEdad    + " (" + mayorEdad + " años)");
            System.out.println("Mayor peso:     " + nombreMayorPeso    + " (" + mayorPeso + " kg)");
            System.out.println("Mayor estatura: " + nombreMayorEstatura + " (" + mayorEstatura + " m)");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}