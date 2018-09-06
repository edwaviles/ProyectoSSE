package edu.dao;

import edu.conexion.Conexion;
import edu.modelo.SolicitudSSE;
import java.awt.HeadlessException;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 * Nombre de la clase: DaoSolicitudSSE
 * Fecha: 28-8-18
 * Version: 1.0
 * CopyRigth: SSE-ITCA
 * @author Garcia
 */
public class DaoSolicitudSSE extends Conexion
{
    public List<SolicitudSSE> extraerSolicitud(int id)
    {
        List<SolicitudSSE> solicitudes = new ArrayList();
        ResultSet res;
        try 
        {
            this.conectar();
            String sql= "select a.carnet,a.nombre, cor.idCoordinador, cor.nombre, s.idSolicitudSSE, s.sedeITCA, s.fechaSolicitud,s.institucion,s.encargado,s.comentarios,s.estado,s.fechaModificacion,\n" +
                        "s.fechaRegistro,s.estadoSSE_idEstado from solicitudsse s inner join alumno a\n" +
                        "on s.idAlumno=a.idAlumno\n" +
                        "inner join carrera c \n" +
                        "on a.carrera=c.idCarrera\n" +
                        "inner join coordinadorsse cor\n" +
                        "on c.idCarrera=cor.carrera_idCarrera WHERE cor.estado=1 AND cor.idCoordinador=?;";
            PreparedStatement pre = getCon().prepareStatement(sql);
            pre.setInt(1, id);
            res=pre.executeQuery();
            while(res.next())
            {
               SolicitudSSE s = new SolicitudSSE();
               s.setNombreAlumno(res.getString("nombre"));
               s.setCarnet(Integer.parseInt(res.getString("carnet")));
               s.setIdSolicitud(Integer.parseInt(res.getString("idSolicitudSSE")));
               s.setIdEstadoSSE(Integer.parseInt(res.getString("estadoSSE_idEstado")));
               s.setSedeItca(res.getString("sedeITCA"));
               s.setFechaSolicitud(res.getString("fechaSolicitud"));
               s.setInstitucion(res.getString("institucion"));
               s.setEncargado(res.getString("encargado"));
               s.setComentarios(res.getString("comentarios"));
               solicitudes.add(s);
            }
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Error al extraer solicitudes: "+ e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
        return solicitudes;
    }
    
    public void modificarS(SolicitudSSE s)
   {
       try 
       {
          this.conectar();
          String sql="";
          if(s.getComentarios()!=null)
          {
                sql="update solicitudsse set comentarios='"+s.getComentarios()+"',estadoSSE_idEstado="+s.getIdEstadoSSE()
                  +" where idSolicitudSSE="+s.getIdSolicitud();          
          }else
          {
            sql="update solicitudsse set comentarios='"+""+"',estadoSSE_idEstado="+s.getIdEstadoSSE()
                  +" where idSolicitudSSE="+s.getIdSolicitud();     
          }
         PreparedStatement pre = getCon().prepareStatement(sql);
          pre.executeUpdate(sql);
          JOptionPane.showMessageDialog(null,"Guardado");
       }
       catch (HeadlessException | SQLException e) 
       {
          JOptionPane.showMessageDialog(null,"Error al modificar solicitud: "+e.getMessage());
       }
       finally
       {
           this.desconectar();
       }
   }
    
    
    public void modificarVistos()
   {
       try 
       {
          this.conectar();
          String sql="";
         PreparedStatement pre = getCon().prepareStatement(sql);
          pre.executeUpdate(sql);
          JOptionPane.showMessageDialog(null,"Guardado");
       }
       catch (HeadlessException | SQLException e) 
       {
          JOptionPane.showMessageDialog(null,"Error al modificar solicitud: "+e.getMessage());
       }
       finally
       {
           this.desconectar();
       }
   }
}
