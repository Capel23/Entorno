package org.example;

import java.util.Scanner;

public class ControlFinanzas {
    private String usuario;
    private double saldo;

    public ControlFinanzas() {
        this.usuario = null;
        this.saldo = 0.0;
    }

    public boolean setUsuario(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        this.usuario = nombre;
        return true;
    }

    public boolean tieneUsuario() {
        return usuario != null;
    }

    public boolean agregarIngreso(double cantidad) {
        if (!tieneUsuario() || cantidad <= 0) return false;
        saldo += cantidad;
        System.out.println(usuario + ", ingreso añadido: " + cantidad);
        return true;
    }

    public boolean agregarGasto(double cantidad, String tipoGasto) {
        if (!tieneUsuario() || cantidad <= 0) return false;

        if (cantidad > saldo) {
            System.out.println("No se puede realizar el gasto. Saldo insuficiente.");
            return false;
        }

        saldo -= cantidad;
        System.out.println(usuario + ", ha registrado un gasto en " + tipoGasto + " de: " + cantidad);
        return true;
    }

    public double consultarSaldo() {
        if (!tieneUsuario()) return -1;
        System.out.println(usuario + ", su saldo actual es: " + saldo);
        return saldo;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n1.- Introduce nombre de usuario");
            System.out.println("2.- Introducir ingresos");
            System.out.println("3.- Introducir gasto");
            System.out.println("4.- Mostrar saldo");
            System.out.println("5.- Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Introduce tu nombre: ");
                    String nombre = scanner.nextLine();
                    if (setUsuario(nombre)) {
                        System.out.println("Usuario establecido: " + nombre);
                    } else {
                        System.out.println("Nombre no válido.");
                    }
                    break;

                case 2:
                    if (!tieneUsuario()) {
                        System.out.println("Debes introducir un usuario primero.");
                        break;
                    }
                    System.out.print(usuario + ", introduce el ingreso (Nómina): ");
                    double ingreso = scanner.nextDouble();
                    agregarIngreso(ingreso);
                    break;

                case 3:
                    if (!tieneUsuario()) {
                        System.out.println("Debes introducir un usuario primero.");
                        break;
                    }
                    System.out.println("Tipos de gasto: Vacaciones, Alquiler, Vicios variados");
                    System.out.print("Introduce tipo de gasto: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Introduce el monto del gasto: ");
                    double gasto = scanner.nextDouble();
                    scanner.nextLine(); // limpiar buffer
                    agregarGasto(gasto, tipo);
                    break;

                case 4:
                    consultarSaldo();
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    public static void main(String[] args) {
        ControlFinanzas cf = new ControlFinanzas();
        cf.mostrarMenu();
    }
}
