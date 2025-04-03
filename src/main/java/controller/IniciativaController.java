package controller;

import DataAccess.XMLManager;
import model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class IniciativaController {

    /**
     * Asigna puntos aleatorios a las actividades que no tienen puntos y actualiza los puntos de los colaboradores.
     */
    private void asignarPuntosAleatorios(List<Iniciativa> iniciativas) {
        Random random = new Random();
        for (Iniciativa iniciativa : iniciativas) {
            for (Actividad actividad : iniciativa.getActividades()) {
                if (actividad.getPuntos() == 0) {
                    int puntosAleatorios = random.nextInt(100) + 1; // Asignar puntos aleatorios entre 1 y 100
                    actividad.setPuntos(puntosAleatorios);
                    if (actividad.getVoluntariosAsignados() != null) {
                        for (Colaborador voluntario : actividad.getVoluntariosAsignados()) {
                            voluntario.agregarPuntos(puntosAleatorios);
                        }
                    }
                }
            }
        }
    }

    /**
     * Guarda una lista de iniciativas en un archivo XML.
     * @param iniciativas Lista de iniciativas a guardar.
     */
    public void guardarIniciativas(List<Iniciativa> iniciativas) {
        try {
            asignarPuntosAleatorios(iniciativas); // Asignar puntos aleatorios antes de guardar
            XMLManager.writeXML(WrapperIniciativa.class, iniciativas, "iniciativas.xml");
        } catch (Exception e) {
            System.err.println("Error al guardar iniciativas: " + e.getMessage());
        }
    }

    /**
     * Carga una lista de iniciativas desde un archivo XML.
     * @return Lista de iniciativas cargadas.
     */
    public List<Iniciativa> cargarIniciativas() {
        try {
            return XMLManager.readXML(WrapperIniciativa.class, Iniciativa.class ,"iniciativas.xml");
        } catch (Exception e) {
            System.err.println("Error al cargar iniciativas: " + e.getMessage());
            return new ArrayList<>(); // Retornar lista vacía en caso de error
        }
    }

    /**
     * Agrega una iniciativa a la lista de iniciativas y la guarda.
     * @param iniciativa Iniciativa a agregar.
     */
    public void agregarIniciativa(Iniciativa iniciativa) {
        List<Iniciativa> iniciativas = cargarIniciativas();
        iniciativas.add(iniciativa);
        guardarIniciativas(iniciativas);
    }

    /**
     * Elimina una iniciativa de la lista de iniciativas y la guarda.
     * @param nombre Nombre de la iniciativa a eliminar.
     */
    public void eliminarIniciativa(String nombre) {
        List<Iniciativa> iniciativas = cargarIniciativas();
        iniciativas.removeIf(iniciativa -> iniciativa.getNombreIniciativa().equalsIgnoreCase(nombre));
        guardarIniciativas(iniciativas);
    }

    /**
     * Lista todas las iniciativas cargadas.
     * @return Lista de iniciativas.
     */
    public List<Iniciativa> listarIniciativas() {
        return cargarIniciativas();
    }

    /**
     * Guarda una lista de actividades en un archivo XML.
     * @param actividades Lista de actividades a guardar.
     */
    public void guardarActividades(List<Actividad> actividades) {
        try {
            XMLManager.writeXML(WrapperActividad.class, actividades, "actividades.xml");
        } catch (Exception e) {
            System.err.println("Error al guardar actividades: " + e.getMessage());
        }
    }

    /**
     * Carga una lista de actividades desde un archivo XML.
     * @return Lista de actividades cargadas.
     */
    public List<Actividad> cargarActividades() {
        try {
            return XMLManager.readXML(WrapperActividad.class, Actividad.class, "actividades.xml");
        } catch (Exception e) {
            System.err.println("Error al cargar actividades: " + e.getMessage());
            return new ArrayList<>(); // Retornar lista vacía en caso de error
        }
    }

    /**
     * Agrega una actividad a una iniciativa existente.
     * @param nombreIniciativa Nombre de la iniciativa a la que se agregará la actividad.
     * @param actividad Actividad a agregar.
     */
    public void agregarActividadAIniciativa(String nombreIniciativa, Actividad actividad) {
        List<Iniciativa> iniciativas = cargarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombreIniciativa().equalsIgnoreCase(nombreIniciativa)) {
                iniciativa.agregarActividad(actividad);
                guardarIniciativas(iniciativas);
                return;
            }
        }
        System.err.println("Iniciativa no encontrada: " + nombreIniciativa);
    }

    /**
     * Elimina una actividad de una iniciativa existente.
     * @param nombreIniciativa Nombre de la iniciativa de la que se eliminará la actividad.
     * @param nombreActividad Nombre de la actividad a eliminar.
     */
    public void eliminarActividadDeIniciativa(String nombreIniciativa, String nombreActividad) {
        List<Iniciativa> iniciativas = cargarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombreIniciativa().equalsIgnoreCase(nombreIniciativa)) {
                iniciativa.eliminarActividad(nombreActividad);
                guardarIniciativas(iniciativas);
                return;
            }
        }
        System.err.println("Iniciativa o actividad no encontrada: " + nombreIniciativa + " - " + nombreActividad);
    }

    /**
     * Lista todas las actividades de una iniciativa.
     * @param nombreIniciativa Nombre de la iniciativa.
     * @return Lista de actividades de la iniciativa.
     */
    public List<Actividad> listarActividadesDeIniciativa(String nombreIniciativa) {
        List<Iniciativa> iniciativas = cargarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombreIniciativa().equalsIgnoreCase(nombreIniciativa)) {
                return iniciativa.getActividades();
            }
        }
        System.err.println("Iniciativa no encontrada: " + nombreIniciativa);
        return new ArrayList<>();
    }

    /**
     * Asigna un voluntario a una actividad de una iniciativa.
     * @param nombreIniciativa Nombre de la iniciativa.
     * @param nombreActividad Nombre de la actividad.
     * @param voluntario Voluntario a asignar.
     */
    public void asignarVoluntarioAActividad(String nombreIniciativa, String nombreActividad, Colaborador voluntario) {
        List<Iniciativa> iniciativas = cargarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombreIniciativa().equalsIgnoreCase(nombreIniciativa)) {
                for (Actividad actividad : iniciativa.getActividades()) {
                    if (actividad.getNombre().equalsIgnoreCase(nombreActividad)) {
                        if (actividad.getVoluntariosAsignados() == null) {
                            actividad.setVoluntariosAsignados(new ArrayList<>());
                        }
                        // Verificar si el voluntario ya está asignado
                        boolean yaAsignado = actividad.getVoluntariosAsignados().stream()
                                .anyMatch(v -> v.getUsuario().equals(voluntario.getUsuario()));
                        if (!yaAsignado) {
                            actividad.getVoluntariosAsignados().add(voluntario);
                            actualizarPuntosColaborador(voluntario, actividad.getPuntos());
                            guardarIniciativas(iniciativas);
                        } else {
                            System.err.println("El voluntario ya está asignado a esta actividad.");
                        }
                        return;
                    }
                }
            }
        }
        System.err.println("Iniciativa o actividad no encontrada: " + nombreIniciativa + " - " + nombreActividad);
    }

    /**
     * Permite a un colaborador inscribirse en una actividad de una iniciativa.
     * @param nombreIniciativa Nombre de la iniciativa.
     * @param nombreActividad Nombre de la actividad.
     * @param colaborador Colaborador que se inscribe.
     */
    public void apuntarseActividad(String nombreIniciativa, String nombreActividad, Colaborador colaborador) {
        List<Iniciativa> iniciativas = cargarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombreIniciativa().equalsIgnoreCase(nombreIniciativa)) {
                for (Actividad actividad : iniciativa.getActividades()) {
                    if (actividad.getNombre().equalsIgnoreCase(nombreActividad)) {
                        if (actividad.getVoluntariosAsignados() == null) {
                            actividad.setVoluntariosAsignados(new ArrayList<>());
                        }
                        // Verificar si el colaborador ya está inscrito
                        boolean yaInscrito = actividad.getVoluntariosAsignados().stream()
                                .anyMatch(v -> v.getUsuario().equals(colaborador.getUsuario()));
                        if (!yaInscrito) {
                            actividad.getVoluntariosAsignados().add(colaborador);
                            actualizarPuntosColaborador(colaborador, actividad.getPuntos());
                            guardarIniciativas(iniciativas);
                        } else {
                            System.err.println("El colaborador ya está inscrito en esta actividad.");
                        }
                        return;
                    }
                }
            }
        }
        System.err.println("Iniciativa o actividad no encontrada: " + nombreIniciativa + " - " + nombreActividad);
    }

    /**
     * Lista todas las actividades en las que un colaborador está inscrito.
     * @param colaborador Colaborador cuyas actividades inscritas se listarán.
     * @return Lista de actividades inscritas.
     */
    public List<Actividad> listarActividadesInscritas(Colaborador colaborador) {
        List<Actividad> actividadesInscritas = new ArrayList<>();
        List<Iniciativa> iniciativas = cargarIniciativas();
        for (Iniciativa iniciativa : iniciativas) {
            for (Actividad actividad : iniciativa.getActividades()) {
                if (actividad.getVoluntariosAsignados() != null) {
                    for (Colaborador voluntario : actividad.getVoluntariosAsignados()) {
                        if (voluntario.getUsuario().equals(colaborador.getUsuario())) {
                            actividadesInscritas.add(actividad);
                            break;
                        }
                    }
                }
            }
        }
        return actividadesInscritas;
    }

    private void actualizarPuntosColaborador(Colaborador colaborador, int puntos) {
        colaborador.agregarPuntos(puntos);
        List<Colaborador> colaboradores = XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");
        for (Colaborador c : colaboradores) {
            if (c.getUsuario().equals(colaborador.getUsuario())) {
                c.setPuntos(colaborador.getPuntos());
                break;
            }
        }
        XMLManager.writeXML(WrapperColaborador.class, colaboradores, "colaboradores.xml");
    }
}

