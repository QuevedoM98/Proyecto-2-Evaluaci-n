package view;

import controller.IniciativaController;
import model.Colaborador;
import model.Actividad;
import model.Iniciativa;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class MenuColaborador {
    private Scanner scanner = new Scanner(System.in);
    private Colaborador colaborador;
    private IniciativaController iniciativaController = new IniciativaController();

    public MenuColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n==============================");
            System.out.println("     🌠 CONTRIBUYA SIN LÍMITES 🌠");
            System.out.println("==============================");
            System.out.println("      🌠 Menú de Colaborador 🌠");
            System.out.println("==============================");
            System.out.println("1️⃣  Listar Iniciativas");
            System.out.println("2️⃣  Apuntarse a Actividad");
            System.out.println("3️⃣  Listar Actividades Inscritas");
            System.out.println("4️⃣  Ver Mis Puntos");
            System.out.println("5️⃣  Ver Actividades de una Iniciativa");
            System.out.println("6️⃣  🚪 Salir");
            System.out.println("==============================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        listarIniciativas();
                        break;
                    case 2:
                        apuntarseActividad();
                        break;
                    case 3:
                        listarActividadesInscritas();
                        break;
                    case 4:
                        verMisPuntos();
                        break;
                    case 5:
                        verActividadesDeIniciativa();
                        break;
                    case 6:
                        System.out.println("Saliendo del menú de Colaborador...");
                        cerrarSesion();
                        break;
                    default:
                        System.out.println("❌ Opción no válida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                opcion = -1; // Asignar un valor no válido para continuar el bucle
            }
        } while (opcion != 6);
    }

    private void listarIniciativas() {
        List<Iniciativa> iniciativas = iniciativaController.listarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            System.out.println("Iniciativa: " + iniciativa.getNombreIniciativa() + ", Descripción: " + iniciativa.getDescripcion());
        }
    }

    private void apuntarseActividad() {
        List<Iniciativa> iniciativas = iniciativaController.listarIniciativas();
        if (iniciativas.isEmpty()) {
            System.out.println("No hay iniciativas disponibles.");
            return;
        }
        System.out.println("Seleccione una iniciativa:");
        for (int i = 0; i < iniciativas.size(); i++) {
            System.out.println((i + 1) + ". " + iniciativas.get(i).getNombreIniciativa());
        }
        int seleccionIniciativa = scanner.nextInt();
        scanner.nextLine();
        if (seleccionIniciativa < 1 || seleccionIniciativa > iniciativas.size()) {
            System.out.println("Selección no válida.");
            return;
        }
        Iniciativa iniciativaSeleccionada = iniciativas.get(seleccionIniciativa - 1);

        List<Actividad> actividades = iniciativaSeleccionada.getActividades();
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades disponibles en esta iniciativa.");
            return;
        }
        System.out.println("Seleccione una actividad:");
        for (int i = 0; i < actividades.size(); i++) {
            System.out.println((i + 1) + ". " + actividades.get(i).getNombre());
        }
        int seleccionActividad = scanner.nextInt();
        scanner.nextLine();
        if (seleccionActividad < 1 || seleccionActividad > actividades.size()) {
            System.out.println("Selección no válida.");
            return;
        }
        Actividad actividadSeleccionada = actividades.get(seleccionActividad - 1);

        iniciativaController.apuntarseActividad(iniciativaSeleccionada.getNombreIniciativa(), actividadSeleccionada.getNombre(), colaborador);
        System.out.println("Te has apuntado a la actividad exitosamente.");
    }

    private void listarActividadesInscritas() {
        List<Actividad> actividades = iniciativaController.listarActividadesInscritas(colaborador);
        if (actividades.isEmpty()) {
            System.out.println("No estás inscrito en ninguna actividad.");
            return;
        }
        for (Actividad actividad : actividades) {
            System.out.println("Actividad: " + actividad.getNombre() + ", Descripción: " + actividad.getDescripcion() + ", Puntos: " + actividad.getPuntos());
        }
    }

    private void verMisPuntos() {
        System.out.println("Tienes " + colaborador.getPuntos() + " puntos.");
    }

    private void verActividadesDeIniciativa() {
        List<Iniciativa> iniciativas = iniciativaController.listarIniciativas();
        if (iniciativas.isEmpty()) {
            System.out.println("No hay iniciativas disponibles.");
            return;
        }
        System.out.println("Seleccione una iniciativa:");
        for (int i = 0; i < iniciativas.size(); i++) {
            System.out.println((i + 1) + ". " + iniciativas.get(i).getNombreIniciativa());
        }
        int seleccionIniciativa = scanner.nextInt();
        scanner.nextLine();
        if (seleccionIniciativa < 1 || seleccionIniciativa > iniciativas.size()) {
            System.out.println("Selección no válida.");
            return;
        }
        Iniciativa iniciativaSeleccionada = iniciativas.get(seleccionIniciativa - 1);

        List<Actividad> actividades = iniciativaSeleccionada.getActividades();
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades disponibles en esta iniciativa.");
            return;
        }
        for (Actividad actividad : actividades) {
            System.out.println("Actividad: " + actividad.getNombre() + ", Descripción: " + actividad.getDescripcion() + ", Puntos: " + actividad.getPuntos());
        }
    }

    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");
        new InicioRegistro().cerrarSesion();
    }
}
