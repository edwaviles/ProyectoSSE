
package edu.dao;

import edu.conexion.Conexion;
import edu.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase:DaoUsuario
 * Verion:
 * Fecha:25/08/18
 * CopyRight:SSE-ITCA
 * @author Ernesto Aviles
 */
public class DaoUsuario extends Conexion{
    
    Usuario us=new Usuario();
    
    public boolean logear(String user, String pass)
    {   
        us.setNombre(user);
        us.setPass(pass);
        ResultSet res;
        boolean a=false;
        try 
        {
            this.conectar();
            String sql="call login(?,?);";
            PreparedStatement pre=this.getCon().prepareCall(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            res=pre.executeQuery();
            if (res.next()) {
                if (res.getInt("numero")==1) 
                {                    
                    a=true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos, intentelo nuevamente");
                }
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al iniciar "+e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
        return a;
    }
    
    public void addUsuario(String nombre)
    {
        try 
        {
            this.conectar();
            String sql="insert into usuario(idROl, usuario, contrasenia, fecha_Registro, fecha_modifica, estado) values(1, ?,'Itca123!',null, null, 1);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1,nombre);
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuario creado exitosamente",
                                "Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al insertar "+e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
    } 
    
     public int getIdUsuario(String nombre)
    {
        ResultSet res;
        int id=0;
        try 
        {
            this.conectar();
            String sql="select idUsuario as id from usuario where usuario=? and contrasenia='Itca123!'";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1,nombre);
            res=pre.executeQuery();
            while (res.next()) 
            {                
                id=res.getInt("id");
                JOptionPane.showMessageDialog(null, "id agarrado");
            }            
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al insertar ","Fallo al insertar " ,
                    JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
        return id;
    }
     
     public String getNombreUsuario(int id)
     {
         String nombreUs="";
         ResultSet res;
         try 
         {
            this.conectar();
            String sql=" select usuario from usuario where idUsuario=?;";
            PreparedStatement pre =this.getCon().prepareStatement(sql);
            pre.setInt(1, id);
            res=pre.executeQuery();
            while(res.next())     
            {
                nombreUs=res.getString("usuario");
            }
         } 
         catch (SQLException e) 
         {
             
         }
         finally
         {
             this.desconectar();
         }
         return nombreUs;
     }
}
