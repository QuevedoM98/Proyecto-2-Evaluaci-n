package model;

public enum EstadoActividad {

    NO_INICIADA("No iniciada"),
    EN_PROGRESO("En progreso"),
    COMPLETADA("Esta completada");

    private final String descripcion;

    EstadoActividad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion () {
        return descripcion;
    }

}
