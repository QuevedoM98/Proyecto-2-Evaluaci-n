package view;

import model.Creador;
import model.Actividad;
import model.Colaborador;
import java.util.Scanner;

public class MenuCreador {
    private Scanner scanner = new Scanner(System.in);
    private Creador creador;

    public MenuCreador(Creador creador) {
        this.creador = creador;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("Menú de Creador");
            System.out.println("1- Crear Actividad");
            System.out.println("2- Eliminar Actividad");
            System.out.println("3- Actualizar Actividad");
            System.out.println("4- Asignar Voluntario");
            System.out.println("5- Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearActividad();
                    break;
                case 2:
                    eliminarActividad();
                    break;
                case 3:
                    actualizarActividad();
                    break;
                case 4:
                    asignarVoluntario();
                    break;
                case 5:
                    System.out.println("Saliendo del menú de Creador...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 5);
    }

    private void crearActividad() {
        System.out.println("Ingrese el nombre de la actividad:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la descripción de la actividad:");
        String descripcion = scanner.nextLine();
        // Implementar lógica para crear actividad
        System.out.println("Actividad creada exitosamente.");
    }

    private void eliminarActividad() {
        System.out.println("Ingrese el nombre de la actividad a eliminar:");
        String nombre = scanner.nextLine();
        // Implementar lógica para eliminar actividad
        System.out.println("Actividad eliminada exitosamente.");
    }

    private void actualizarActividad() {
        System.out.println("Ingrese el nombre de la actividad a actualizar:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la nueva descripción de la actividad:");
        String descripcion = scanner.nextLine();
        // Implementar lógica para actualizar actividad
        System.out.println("Actividad actualizada exitosamente.");
    }

    private void asignarVoluntario() {
        System.out.println("Ingrese el nombre del voluntario:");
        String nombreVoluntario = scanner.nextLine();
        System.out.println("Ingrese el nombre de la actividad:");
        String nombreActividad = scanner.nextLine();
        // Implementar lógica para asignar voluntario a actividad
        System.out.println("Voluntario asignado exitosamente.");
    }
}
