package view;

import model.Colaborador;
import model.Actividad;
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
            System.out.println("3- Cerrar Sesión");
            System.out.println("4- Salir");

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
                    cerrarSesion();
                    return;
                case 4:
                    System.out.println("Saliendo del menú de Colaborador...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 4);
    }

    private void verActividades() {
        System.out.println("Actividades disponibles:");
        for (Actividad actividad : colaborador.getListaActividades()) {
            System.out.println("- " + actividad.getNombre());
        }
    }

    private void participarEnActividad() {
        System.out.println("Ingrese el nombre de la actividad en la que desea participar:");
        String nombre = scanner.nextLine();
        System.out.println("Nombre de la actividad ingresada: " + nombre); // Mensaje de depuración
        Actividad actividad = buscarActividadPorNombre(nombre);
        if (actividad != null) {
            colaborador.getListaActividades().add(actividad);
            System.out.println("Participación registrada exitosamente.");
        } else {
            System.out.println("Actividad no encontrada.");
        }
    }

    private Actividad buscarActividadPorNombre(String nombre) {
        for (Actividad actividad : colaborador.getListaActividades()) {
            if (actividad.getNombre().equalsIgnoreCase(nombre)) {
                return actividad;
            }
        }
        return null;
    }

    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");
        // Implementar lógica para cerrar sesión
    }
}
