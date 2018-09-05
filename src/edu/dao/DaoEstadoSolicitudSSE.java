package edu.dao;

import edu.conexion.Conexion;
import edu.modelo.EstadoSolicitudSSE;
import edu.modelo.SolicitudSSE;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoEstadoSolicitudSSE
 * Fecha: 28-8-18
 * Version: 1.0
 * CopyRigth: SSE-ITCA
 * @author Reniery Garcia
 */
public class DaoEstadoSolicitudSSE extends Conexion
{
   public List<EstadoSolicitudSSE> extraerEstado()
   {
       List<EstadoSolicitudSSE> estados= new ArrayList<>();
       ResultSet res;
       try 
       {
           this.conectar();
           String sql= "select * from estadosolicitudsse";
           PreparedStatement pre = getCon().prepareStatement(sql);
           res = pre.executeQuery();
           while(res.next())
           {
               EstadoSolicitudSSE e = new EstadoSolicitudSSE();
               e.setIdEstado(Integer.parseInt(res.getString("idEstado")));
               e.setDescripcion(res.getString("descripcion"));
               estados.add(e);
           }
       } 
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null,"Error al extraer estadosSSE: "+ e.getMessage());
       }
       finally
       {
           this.desconectar();
       }
       return estados;
   }   
}
