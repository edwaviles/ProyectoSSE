
package edu.modelo;

/**
 * Nombre de la clase: Escuela
 * Version:1.0
 * Fecha: 12/08/18
 * CopyRight: SSE-ITCA
 * @author Roxana Menjivar
 */
public class Escuela {
     private int idEscuela;
     private String nombre;

    public Escuela() {
    }

    public Escuela(int idEscuela, String nombre) {
        this.idEscuela = idEscuela;
        this.nombre = nombre;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }        
}
