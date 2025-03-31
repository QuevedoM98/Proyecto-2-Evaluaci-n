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

    }

    private void eliminarActividad() {

    }

    private void actualizarActividad() {

        }


    private void asignarVoluntario() {


    }
}