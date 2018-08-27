
package edu.modelo;

/**
 * Nombre de la clase: Carrera
 * Version: 1.4
 * Fecha: 12/08/18
 * CopyRight: SSE-ITCA
 * @author Roxana Menjivar
 */
public class Carrera {
    private int idCarrera;
    private String nombre;
    private int idEscuela;

    public Carrera() {
    }

    public Carrera(int idCarrera, String nombre, int idEscuela) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.idEscuela = idEscuela;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    } 
    
}
