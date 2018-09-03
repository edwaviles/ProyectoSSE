package edu.modelo;

/**
 * Nombre de la clase: Alumno
 * Fecha: 28-8-18
 * Version: 1.0
 * CopyRigth: SSE-ITCA
 * @author Garcia
 */
public class Alumno extends Usuario
{
    private int idAlumno;
    private String nombreAlumno;

    public Alumno(int idAlumno, String nombreAlumno, int carnet, String carrera, String escuela, String grupo, String cursaMaterias, String tipoCarrera, int ciclo, int horesActuales) {
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.carnet = carnet;
        this.carrera = carrera;
        this.escuela = escuela;
        this.grupo = grupo;
        this.cursaMaterias = cursaMaterias;
        this.tipoCarrera = tipoCarrera;
        this.ciclo = ciclo;
        this.horesActuales = horesActuales;
    }

    public Alumno(int idAlumno, String nombreAlumno, int carnet, String carrera, String escuela, String grupo, String cursaMaterias, String tipoCarrera, int ciclo, int horesActuales, int codigo, String nombre, String pass, int rol) {
        super(codigo, nombre, pass, rol);
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.carnet = carnet;
        this.carrera = carrera;
        this.escuela = escuela;
        this.grupo = grupo;
        this.cursaMaterias = cursaMaterias;
        this.tipoCarrera = tipoCarrera;
        this.ciclo = ciclo;
        this.horesActuales = horesActuales;
    }
    private int carnet;
    private String carrera;
    private String escuela;
    private String grupo;
    private String cursaMaterias;
    private String tipoCarrera;
    private int ciclo;
    private int horesActuales;

    public Alumno() {
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCursaMaterias() {
        return cursaMaterias;
    }

    public void setCursaMaterias(String cursaMaterias) {
        this.cursaMaterias = cursaMaterias;
    }

    public String getTipoCarrera() {
        return tipoCarrera;
    }

    public void setTipoCarrera(String tipoCarrera) {
        this.tipoCarrera = tipoCarrera;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public int getHoresActuales() {
        return horesActuales;
    }

    public void setHoresActuales(int horesActuales) {
        this.horesActuales = horesActuales;
    }
    
    
}
