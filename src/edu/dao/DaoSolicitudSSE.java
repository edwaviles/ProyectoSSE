package edu.dao;

import edu.conexion.Conexion;
import edu.modelo.SolicitudSSE;
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
    public List<SolicitudSSE> extraerSolicitud()
    {
        List<SolicitudSSE> solicitudes = new ArrayList();
        ResultSet res;
        try 
        {
            this.conectar();
            String sql= "SELECT * FROM solicitudsse s INNER JOIN alumno a ON a.idAlumno=s.idAlumno ";
            PreparedStatement pre = getCon().prepareStatement(sql);
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
               s.setEstado(Integer.parseInt(res.getString("estadoSSE_idEstado")));
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
}
