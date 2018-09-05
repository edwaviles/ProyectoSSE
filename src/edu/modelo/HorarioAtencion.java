
package edu.modelo;

/**
 * Nombre de la clase: HorarioAtencion
 * Version:1.0
 * Fecha: 07/08/18
 * CopyRight: SSE-ITCA
 * @author Ernesto Aviles
 */
public class HorarioAtencion {
    private int idHorarioA;
    private int idCoordinador;
    private String dia;
    private int horaDesde;
    private int horaHasta;
    private int minutosDesde;
    private int minutosHasta;
    private String lugar;
    private String fechaRegistro;
    private String fechaModificacion;
    private String fechaEliminacion;

    public int getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(int horaDesde) {
        this.horaDesde = horaDesde;
    }

    public int getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(int horaHasta) {
        this.horaHasta = horaHasta;
    }

    public int getMinutosDesde() {
        return minutosDesde;
    }

    public void setMinutosDesde(int minutosDesde) {
        this.minutosDesde = minutosDesde;
    }

    public int getMinutosHasta() {
        return minutosHasta;
    }

    public void setMinutosHasta(int minutosHasta) {
        this.minutosHasta = minutosHasta;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(String fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public HorarioAtencion() {
    }

    public int getIdHorarioA() {
        return idHorarioA;
    }

    public void setIdHorarioA(int idHorarioA) {
        this.idHorarioA = idHorarioA;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }


    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }  
}
