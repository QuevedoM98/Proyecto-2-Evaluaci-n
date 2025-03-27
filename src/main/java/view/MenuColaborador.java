package view;

import model.Colaborador;
import java.util.Scanner;

public class MenuColaborador {
    private Scanner scanner = new Scanner(System.in);
    private Colaborador colaborador;

    public MenuColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("Menú de Colaborador");
            System.out.println("1- Ver Actividades");
            System.out.println("2- Participar en Actividad");
            System.out.println("3- Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verActividades();
                    break;
                case 2:
                    participarEnActividad();
                    break;
                case 3:
                    System.out.println("Saliendo del menú de Colaborador...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 3);
    }

    private void verActividades() {
        System.out.println("Actividades disponibles:");

    }

    private void participarEnActividad() {
        System.out.println("Ingrese el nombre de la actividad en la que desea participar:");
        String nombre = scanner.nextLine();
        System.out.println("Participación registrada exitosamente.");
    }
}