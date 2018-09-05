/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.modelo;

/**
 * Nombre de la claseUsuario
 * Verion:
 * Fecha:25/08/18
 * CopyRight:SSE-ITCA
 * @author Nancy Lopez
 */
public class Usuario {
    private int codigo;
    private String nombre;
    private String pass;
    private int rol;
     private String fechaRegistro;
    private String fechaModificacion;
    private int estado;
    private String fechaEliminacion;

    public Usuario() {
    }

    public Usuario(int codigo, String nombre, String pass, int rol, String fechaRegistro, String fechaModificacion, int estado, String fechaEliminacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pass = pass;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
        this.fechaEliminacion = fechaEliminacion;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }  
    
    public void setUser(Usuario us)
    {
        this.setNombre(us.getNombre());
        this.setPass(us.getPass());
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(String fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }
    
}
