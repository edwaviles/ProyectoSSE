
package edu.modelo;

/**
 * Nombre de la clase: Combo
 * Version:1.0
 * Fecha:25/08/18
 * CopyRight:SSE-ITCA
 * @author Nancy Lopez
 */
public class Combo {
         private int idCombo;
     private String descripcion;

    public Combo() {
    }

    public Combo(int idCombo, String descripcion) {
        this.idCombo = idCombo;
        this.descripcion = descripcion;
    }

    public int getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
