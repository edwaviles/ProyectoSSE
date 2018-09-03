package edu.modelo;

/**
 * Nombre de la clase: EstadoSolicitudSSE
 * Fecha: 28-818
 * Version: 1.0
 * CopyRigth: SSE-ITCA
 * @author Garcia
 */
public class EstadoSolicitudSSE 
{
    private int idEstado;
    private String descripcion;

    public EstadoSolicitudSSE() {
    }

    public EstadoSolicitudSSE(int idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
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
