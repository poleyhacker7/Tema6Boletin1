package ejercicio7;

import java.time.LocalDate;

public class Cliente implements Comparable<Cliente> {

    private String dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private double saldo;

    public Cliente(String dni, String nombre, LocalDate fechaNacimiento, double saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }

    public int getEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    @Override
    public int compareTo(Cliente otro) {
        return this.dni.compareTo(otro.dni);
    }

    // Getters
    public String getDni()                  { return dni; }
    public String getNombre()               { return nombre; }
    public LocalDate getFechaNacimiento()   { return fechaNacimiento; }
    public double getSaldo()                { return saldo; }

    @Override
    public String toString() {
        return dni + ";" + nombre + ";" + fechaNacimiento + ";" + saldo;
    }
}