package view;

import controller.IniciativaController;
import controller.UserController;
import model.Creador;
import model.Iniciativa;
import model.Actividad;
import model.Colaborador;

import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MenuCreador {
    private Scanner scanner = new Scanner(System.in);
    private Creador creador;

    public MenuCreador(Creador creador) {
        this.creador = creador;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n==============================");
            System.out.println("     🌠 CONTRIBUYA SIN LÍMITES 🌠");
            System.out.println("==============================");
            System.out.println("          🌠 Menú de Creador 🌠");
            System.out.println("==============================");
            System.out.println("1️⃣  Crear Iniciativa");
            System.out.println("2️⃣  Eliminar Iniciativa");
            System.out.println("3️⃣  Crear Actividad en Iniciativa");
            System.out.println("4️⃣  Eliminar Actividad de Iniciativa");
            System.out.println("5️⃣  Asignar Voluntario a Actividad");
            System.out.println("6️⃣  Listar Iniciativas");
            System.out.println("7️⃣  Ver Actividades y Colaboradores");
            System.out.println("8️⃣  🚪 Salir");
            System.out.println("==============================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        crearIniciativa();
                        break;
                    case 2:
                        if (hayIniciativasDisponibles()) {
                            eliminarIniciativa();
                        }
                        break;
                    case 3:
                        if (hayIniciativasDisponibles()) {
                            crearActividadEnIniciativa();
                        }
                        break;
                    case 4:
                        if (hayIniciativasDisponibles()) {
                            eliminarActividadDeIniciativa();
                        }
                        break;
                    case 5:
                        if (hayIniciativasDisponibles()) {
                            asignarVoluntario();
                        }
                        break;
                    case 6:
                        listarIniciativas();
                        break;
                    case 7:
                        if (hayIniciativasDisponibles()) {
                            verActividadesYColaboradores();
                        }
                        break;
                    case 8:
                        System.out.println("Saliendo del menú de Creador...");
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
        } while (opcion != 8);
    }

    private boolean hayIniciativasDisponibles() {
        IniciativaController iniciativaController = new IniciativaController();
        List<Iniciativa> iniciativas = iniciativaController.listarIniciativas();
        if (iniciativas.isEmpty()) {
            System.out.println("No hay iniciativas disponibles.");
            return false;
        }
        return true;
    }

    private void crearIniciativa() {
        System.out.println("Ingrese el nombre de la iniciativa:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la descripción de la iniciativa:");
        String descripcion = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Fecha de inicio (yyyy-MM-dd): ");
        String fechaInicioStr = scanner.nextLine();
        Date fechaInicio = null;
        try {
            fechaInicio = dateFormat.parse(fechaInicioStr);
        } catch (ParseException e) {
            System.err.println("Formato de fecha incorrecto. Usando fecha actual.");
            fechaInicio = new Date();
        }

        System.out.print("Fecha de fin (yyyy-MM-dd): ");
        String fechaFinStr = scanner.nextLine();
        Date fechaFin = null;
        try {
            fechaFin = dateFormat.parse(fechaFinStr);
        } catch (ParseException e) {
            System.err.println("Formato de fecha incorrecto. Usando fecha actual.");
            fechaFin = new Date();
        }

        System.out.print("Estado de la iniciativa (PENDIENTE, EN_PROGRESO, COMPLETADA, CANCELADA): ");
        String estadoStr = scanner.nextLine();
        Iniciativa.Estado estado = Iniciativa.Estado.valueOf(estadoStr.toUpperCase());

        List<Actividad> actividades = new ArrayList<>();
        System.out.print("¿Desea agregar actividades? (s/n): ");
        String agregarActividades = scanner.nextLine();
        while (agregarActividades.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nombre de la actividad:");
            String nombreActividad = scanner.nextLine();
            System.out.println("Ingrese la descripción de la actividad:");
            String descripcionActividad = scanner.nextLine();
            System.out.print("Puntos que otorga la actividad: ");
            int puntos = scanner.nextInt();
            scanner.nextLine();
            // Crear la actividad y agregarla a la lista
            Actividad actividad = new Actividad(nombreActividad, descripcionActividad, fechaInicio, fechaFin, Actividad.Estado.PENDIENTE, new ArrayList<>(), creador, puntos);
            actividades.add(actividad);
            System.out.print("¿Desea agregar otra actividad? (s/n): ");
            agregarActividades = scanner.nextLine();
        }

        Iniciativa iniciativa = new Iniciativa(nombre, descripcion, creador, fechaInicio, fechaFin, estado);
        iniciativa.setActividades(actividades);

        // Guardar la iniciativa
        IniciativaController iniciativaController = new IniciativaController();
        iniciativaController.agregarIniciativa(iniciativa);

        System.out.println("Iniciativa creada exitosamente.");
    }

    private void eliminarIniciativa() {
        listarIniciativas();
        System.out.println("Ingrese el nombre de la iniciativa a eliminar:");
        String nombre = scanner.nextLine();
        IniciativaController iniciativaController = new IniciativaController();
        iniciativaController.eliminarIniciativa(nombre);
        System.out.println("Iniciativa eliminada exitosamente.");
    }

    private void crearActividadEnIniciativa() {
        listarIniciativas();
        System.out.println("Ingrese el nombre de la iniciativa:");
        String nombreIniciativa = scanner.nextLine();

        System.out.println("Ingrese el nombre de la actividad:");
        String nombreActividad = scanner.nextLine();
        System.out.println("Ingrese la descripción de la actividad:");
        String descripcionActividad = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Fecha de inicio (yyyy-MM-dd): ");
        String fechaInicioStr = scanner.nextLine();
        Date fechaInicio = null;
        try {
            fechaInicio = dateFormat.parse(fechaInicioStr);
        } catch (ParseException e) {
            System.err.println("Formato de fecha incorrecto. Usando fecha actual.");
            fechaInicio = new Date();
        }

        System.out.print("Fecha de fin (yyyy-MM-dd): ");
        String fechaFinStr = scanner.nextLine();
        Date fechaFin = null;
        try {
            fechaFin = dateFormat.parse(fechaFinStr);
        } catch (ParseException e) {
            System.err.println("Formato de fecha incorrecto. Usando fecha actual.");
            fechaFin = new Date();
        }

        System.out.print("Estado de la actividad (PENDIENTE, EN_PROGRESO, COMPLETADA, CANCELADA): ");
        String estadoStr = scanner.nextLine();
        Actividad.Estado estado = Actividad.Estado.valueOf(estadoStr.toUpperCase());

        System.out.print("Puntos que otorga la actividad: ");
        int puntos = scanner.nextInt();
        scanner.nextLine();

        Actividad actividad = new Actividad(nombreActividad, descripcionActividad, fechaInicio, fechaFin, estado, new ArrayList<>(), creador, puntos);

        IniciativaController iniciativaController = new IniciativaController();
        iniciativaController.agregarActividadAIniciativa(nombreIniciativa, actividad);

        System.out.println("Actividad creada exitosamente en la iniciativa.");
    }

    private void eliminarActividadDeIniciativa() {
        listarIniciativas();
        System.out.println("Ingrese el nombre de la iniciativa:");
        String nombreIniciativa = scanner.nextLine();
        listarActividadesDeIniciativa(nombreIniciativa);
        System.out.println("Ingrese el nombre de la actividad a eliminar:");
        String nombreActividad = scanner.nextLine();
        IniciativaController iniciativaController = new IniciativaController();
        iniciativaController.eliminarActividadDeIniciativa(nombreIniciativa, nombreActividad);
        System.out.println("Actividad eliminada exitosamente de la iniciativa.");
    }

    private void asignarVoluntario() {
        UserController userController = new UserController();
        List<Colaborador> voluntarios = userController.cargarColaboradores();
        for (Colaborador voluntario : voluntarios) {
            System.out.println("Voluntario: " + voluntario.getNombre());
        }

        System.out.println("Ingrese el nombre del voluntario:");
        String nombreVoluntario = scanner.nextLine();

        Colaborador voluntarioSeleccionado = null;
        for (Colaborador voluntario : voluntarios) {
            if (voluntario.getNombre().equalsIgnoreCase(nombreVoluntario)) {
                voluntarioSeleccionado = voluntario;
                break;
            }
        }

        if (voluntarioSeleccionado == null) {
            System.err.println("Voluntario no encontrado.");
            return;
        }

        listarIniciativas();
        System.out.println("Ingrese el nombre de la iniciativa:");
        String nombreIniciativa = scanner.nextLine();
        listarActividadesDeIniciativa(nombreIniciativa);
        System.out.println("Ingrese el nombre de la actividad:");
        String nombreActividad = scanner.nextLine();

        IniciativaController iniciativaController = new IniciativaController();
        iniciativaController.asignarVoluntarioAActividad(nombreIniciativa, nombreActividad, voluntarioSeleccionado);

        System.out.println("Voluntario asignado exitosamente.");
    }

    private void listarIniciativas() {
        IniciativaController iniciativaController = new IniciativaController();
        List<Iniciativa> iniciativas = iniciativaController.listarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            System.out.println("Iniciativa: " + iniciativa.getNombreIniciativa() + ", Descripción: " + iniciativa.getDescripcion());
        }
    }

    private void verActividadesYColaboradores() {
        IniciativaController iniciativaController = new IniciativaController();
        List<Iniciativa> iniciativas = iniciativaController.listarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            System.out.println("\nIniciativa: " + iniciativa.getNombreIniciativa());
            System.out.println("Descripción: " + iniciativa.getDescripcion());
            System.out.println("Estado: " + iniciativa.getEstado());
            System.out.println("Actividades:");
            for (Actividad actividad : iniciativa.getActividades()) {
                System.out.println("  - Actividad: " + actividad.getNombre());
                System.out.println("    Descripción: " + actividad.getDescripcion());
                System.out.println("    Estado: " + actividad.getEstado());
                System.out.println("    Voluntarios Asignados:");
                if (actividad.getVoluntariosAsignados() != null) {
                    for (Colaborador voluntario : actividad.getVoluntariosAsignados()) {
                        System.out.println("      * " + voluntario.getNombre());
                    }
                } else {
                    System.out.println("      No hay voluntarios asignados.");
                }
            }
        }
    }

    private void listarActividadesDeIniciativa(String nombreIniciativa) {
        IniciativaController iniciativaController = new IniciativaController();
        List<Actividad> actividades = iniciativaController.listarActividadesDeIniciativa(nombreIniciativa);
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
