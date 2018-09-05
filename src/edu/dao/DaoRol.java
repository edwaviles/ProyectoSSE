
package edu.dao;
import edu.conexion.Conexion;
import edu.modelo.Escuela;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import edu.modelo.Rol;
/**
 * Nombre de la clase:DaoRol
 * Verion:1.0
 * Fecha:25/08/18
 * CopyRight:SSE-ITCA
 * @author Nancy Lopez
 */
public class DaoRol extends Conexion{
     
    public ArrayList<Rol>getRol(){
    ArrayList<Rol> ls=new  ArrayList<>();
    ResultSet res;
        try 
        {
            this.conectar();
            String sql="Select * from rol;";
            PreparedStatement pre=getCon().prepareStatement(sql);
            res=pre.executeQuery();
            while (res.next()) 
            {                
                Rol rol=new Rol();
                rol.setIdRol(res.getInt("idRol"));
                rol.setNombre(res.getString("nombre"));
                ls.add(rol);
            }    
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "No se pudo obtener los roles"+e.toString());
        }
        finally
        {
            this.desconectar();
        }
        return ls;
    }
    
    public Rol getidRol(int rol)
     {
        Rol ro= new Rol();

        ResultSet res=null;
        try 
        {
            this.conectar();
            String sql = "select * from rol where idRol = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, rol);   
            res = pre.executeQuery();
             while (res.next()) 
               {              
                   ro.setIdRol(res.getInt("idRol"));
                   ro.setNombre(res.getString("nombre"));                
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
        return ro;
    }  
    
    public int getidUser(int idus)
    {
        ResultSet res;
        int codigo=-1;
        try 
        {
            this.conectar();
            String sql="select idRol from usuario where idUsuario=?";            
            PreparedStatement pre= getCon().prepareStatement(sql);
            pre.setInt(1, idus);
            res=pre.executeQuery();
            while (res.next()) 
            {                
                codigo=res.getInt("idRol");
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "no fue posible obtener informacion de usuario");
        }
        finally
        {
            this.desconectar();
        }
        
        return codigo;
    }
}
