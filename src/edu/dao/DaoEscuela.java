
package edu.dao;

import edu.conexion.Conexion;
import edu.modelo.Escuela;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase:DaoEscuela
 * Fecha:12/08/18
 * Versión: 1.7
 * CopyRight:SSE-ITCA
 * @author Roxana Menjivar
 */
public class DaoEscuela extends Conexion{
    
    public List<Escuela>mostrarEscuela()
    {
        List<Escuela>lista=new ArrayList();
        ResultSet res;
        try 
        {
            this.conectar();
            String sql="select * from escuela ;";//order by nombre ASC
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Escuela esc = new Escuela();
                esc.setIdEscuela(Integer.parseInt(res.getString("idEscuela")));
                esc.setNombre(res.getString("nombre"));
                lista.add(esc);
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al mostrar escuela " +e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
     return lista;
    }
    
    public Escuela getidEscuela(int escu)
     {
        Escuela esc= new Escuela();

        ResultSet res=null;
        try 
        {
            this.conectar();
            String sql = "select * from escuela where idEscuela = ?;";
            PreparedStatement pre = this.getCon().prepareCall(sql);
            pre.setInt(1, escu);   
            res = pre.executeQuery();

               while (res.next()) 
               {              
                   esc.setIdEscuela(res.getInt("idEscuela"));
                   esc.setNombre(res.getString("nombre"));                
               }
           } catch (SQLException e) 
           {
               JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
           } 
           finally 
           {
               this.desconectar();
           }
           return esc;
    }
    
    public String getnombre(int idEs)
    {
        ResultSet res;
        String nombre="";
         try 
        {
            this.conectar();
            String sql = "select e.nombre from escuela e inner join carrera ca on e.idEscuela = ca.escuela_idEscuela where ca.idCarrera=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idEs); 
            res = pre.executeQuery();

               while (res.next()) 
               {
                   nombre=res.getString("nombre");
               }
           }
           catch (SQLException e) 
           {
               JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
           } 
           finally 
           {
               this.desconectar();
           }
        return nombre;
    }
}
