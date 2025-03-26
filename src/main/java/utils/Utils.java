package utils;

import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Valida un ISBN en formato estándar (ISBN-13).
     * El formato debe ser: 978-XX-XXXXXXX-X
     * @param isbn ISBN a validar
     * @return true si el ISBN es válido, false si no lo es
     */
    public static boolean validarISBN(String isbn){
        String isbnRegex = "^97[8-9]-[0-9]{2}-[0-9]{7}-[0-9]$";
        return isbn.matches(isbnRegex);
    }

    /**
     * Valida un ISBN en formato de revista.
     * El formato debe ser: XXXX-XXXX
     * @param isbn ISBN a validar
     * @return true si el ISBN es válido, false si no lo es
     */
    public static boolean validarISBNrevista (String isbn){
        String isbnRegex = "^[0-9]{4}-[0-9]{4}$";
        return isbn.matches(isbnRegex);
    }

    /**
     * Lee un número entero desde la entrada estándar, asegurándose de que es un valor válido.
     * @param mensaje Mensaje que se muestra al usuario antes de ingresar el número
     * @return El número entero ingresado por el usuario
     */
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
