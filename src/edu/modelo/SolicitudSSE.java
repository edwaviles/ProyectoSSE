package edu.modelo;

/**
 * Nombre de la clase: SolicitudSSE
 * Fecha: 28-8-18
 * Version: 1.0
 * CopyRigth: SSE-ITCA
 * @author Garcia
 */
public class SolicitudSSE extends Alumno
{
    private int idSolicitud;
    private String sedeItca;
    private String fechaSolicitud;
    private String institucion;
    private String encargado;
    private String comentarios;
    private int estado;
    private String fechaModificacion;
    private String fechaResgistro;
    private int idEstadoSSE;

    public SolicitudSSE() {
    }

    public SolicitudSSE(int idSolicitud, String sedeItca, String fechaSolicitud, String institucion, String encargado, String comentarios, int estado, String fechaModificacion, String fechaResgistro, int idEstadoSSE, int idAlumno, String nombreAlumno, int carnet, String carrera, String escuela, String grupo, String cursaMaterias, String tipoCarrera, int ciclo, int horesActuales) {
        super(idAlumno, nombreAlumno, carnet, carrera, escuela, grupo, cursaMaterias, tipoCarrera, ciclo, horesActuales);
        this.idSolicitud = idSolicitud;
        this.sedeItca = sedeItca;
        this.fechaSolicitud = fechaSolicitud;
        this.institucion = institucion;
        this.encargado = encargado;
        this.comentarios = comentarios;
        this.estado = estado;
        this.fechaModificacion = fechaModificacion;
        this.fechaResgistro = fechaResgistro;
        this.idEstadoSSE = idEstadoSSE;
    }

    public SolicitudSSE(int idSolicitud, String sedeItca, String fechaSolicitud, String institucion, String encargado, String comentarios, int estado, String fechaModificacion, String fechaResgistro, int idEstadoSSE, int idAlumno, String nombreAlumno, int carnet, String carrera, String escuela, String grupo, String cursaMaterias, String tipoCarrera, int ciclo, int horesActuales, int codigo, String nombre, String pass, int rol) {
        super(idAlumno, nombreAlumno, carnet, carrera, escuela, grupo, cursaMaterias, tipoCarrera, ciclo, horesActuales, codigo, nombre, pass, rol);
        this.idSolicitud = idSolicitud;
        this.sedeItca = sedeItca;
        this.fechaSolicitud = fechaSolicitud;
        this.institucion = institucion;
        this.encargado = encargado;
        this.comentarios = comentarios;
        this.estado = estado;
        this.fechaModificacion = fechaModificacion;
        this.fechaResgistro = fechaResgistro;
        this.idEstadoSSE = idEstadoSSE;
    }

    public SolicitudSSE(int idSolicitud, String sedeItca, String fechaSolicitud, String institucion, String encargado, String comentarios, int estado, String fechaModificacion, String fechaResgistro, int idEstadoSSE) {
        this.idSolicitud = idSolicitud;
        this.sedeItca = sedeItca;
        this.fechaSolicitud = fechaSolicitud;
        this.institucion = institucion;
        this.encargado = encargado;
        this.comentarios = comentarios;
        this.estado = estado;
        this.fechaModificacion = fechaModificacion;
        this.fechaResgistro = fechaResgistro;
        this.idEstadoSSE = idEstadoSSE;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getSedeItca() {
        return sedeItca;
    }

    public void setSedeItca(String sedeItca) {
        this.sedeItca = sedeItca;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaResgistro() {
        return fechaResgistro;
    }

    public void setFechaResgistro(String fechaResgistro) {
        this.fechaResgistro = fechaResgistro;
    }

    public int getIdEstadoSSE() {
        return idEstadoSSE;
    }

    public void setIdEstadoSSE(int idEstadoSSE) {
        this.idEstadoSSE = idEstadoSSE;
    }

    
}
