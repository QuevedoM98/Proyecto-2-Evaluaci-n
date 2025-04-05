package utils;

import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);


    public static int leeEntero(String mensaje) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensaje);
                numero = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número entero válido.");
            }
        }
        return numero;
    }

    /**
     * Lee un número decimal (double) desde la entrada estándar, asegurándose de que es un valor válido.
     * @param mensaje Mensaje que se muestra al usuario antes de ingresar el número
     * @return El número decimal ingresado por el usuario
     */
    public static double leeDouble (String mensaje) {
        double numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensaje);
                numero = Double.parseDouble(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número Double válido.");
            }
        }
        return numero;
    }

    /**
     * Solicita un texto desde la entrada estándar.
     * @param mensaje Mensaje que se muestra al usuario antes de ingresar el texto
     * @return El texto ingresado por el usuario
     */
    public static String pideString (String mensaje){
        String texto = "";
        scanner.nextLine(); // Limpiar el buffer de entrada
        System.out.println(mensaje);
        texto = scanner.nextLine();
        return texto;
    }

}
