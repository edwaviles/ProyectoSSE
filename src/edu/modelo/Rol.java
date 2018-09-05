
package edu.modelo;

/**
 * Nombre de la clase: Rol
 * Verion:1.0
 * Fecha:25/08/18
 * CopyRight:SSE-ITCA
 * @author Nancy Lopez
 */
public class Rol
{
    private int idRol;
    private String nombre;

    public Rol() {
    }

    public Rol(int idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
