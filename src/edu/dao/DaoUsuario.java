
package edu.dao;

import edu.conexion.Conexion;
import edu.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
   
    
    public int logear(String user, String pass)
    {   
        us.setNombre(user);
        us.setPass(pass);
        ResultSet res;
        int a=0;
        try 
        {
            this.conectar();
            String sql="call login(?,?);";
            PreparedStatement pre=this.getCon().prepareCall(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            res=pre.executeQuery();
            if (res.next()) 
            {
                if (res.getInt("tipo")==2) 
                {                    
                    a=2;
                    
                }
                if (res.getInt("tipo")==1) 
                {                    
                    a=1;
                }                              
            }
            else
            {
                a=3;
                //new rojerusan.RSNotifyFade("Datos incorrectos", "intentelo nuevamente", 3, RSNotifyFade.PositionNotify.BottomLeft, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
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
    
    public void addUsuario(String nombre,String fecha)
    {
        try 
        {
            this.conectar();
            String sql="insert into usuario(idROl, usuario, contrasenia, fechaRegistro, estado) values(2, ?,'Itca123!',?, 1);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1,nombre);
            pre.setString(2,fecha);
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
     
     public int VerificarUsuario(String usuario)
     {
         ResultSet res;     
         try 
         {
            this.conectar();
            String sql="select count(idUsuario) from usuario where usuario=?"; 
            PreparedStatement pre=getCon().prepareStatement(sql);
            pre.setString(1, usuario);
            res=pre.executeQuery();
            
            if(res.next())
            {
               return res.getInt(1);
            }

         }
         catch (Exception e) 
         {
             
         }
         finally
         {
             this.desconectar();
         }
         return 1;
     } 
     
    public List<Usuario> mostrar()
    {
        List<Usuario>listaUsuario= new ArrayList();
        ResultSet res;
        try 
        {
          
          this.conectar();
            String sql="select*from usuario where estado=1";
            PreparedStatement pre= getCon().prepareStatement(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Usuario us =new Usuario();
                us.setCodigo(Integer.parseInt(res.getString("idUsuario")));
                us.setNombre(res.getString("usuario"));
                us.setFechaRegistro(res.getString("fechaRegistro"));
                us.setEstado(Integer.parseInt(res.getString("estado")));
                us.setRol(Integer.parseInt(res.getString("idRol")));
                listaUsuario.add(us);                                      
            }
            
            
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Error al mostrar"+ e.getMessage());
        }
        finally
        {
            desconectar();
        }
      return listaUsuario;
    }
        
        public void Eliminar(Usuario us)
        {
            try 
            {
                this.conectar();
                String sql="update usuario set estado=?,fechaEliminacion=? where idUsuario =?;";
                PreparedStatement pre= getCon().prepareStatement(sql);
                pre.setInt(1,us.getEstado());
                pre.setString(2,us.getFechaEliminacion());
                pre.setInt(3,us.getCodigo());
                pre.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Datos elimnados");
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro "+e.toString());
            }
        
        }
        
               
        public void Modificar(Usuario us)
        {
            try 
            {
                this.conectar();
                String sql="update usuario set usuario=?,fechaModifica=?,contrasenia=?,idRol=? where idUsuario=?";
                PreparedStatement pre= getCon().prepareStatement(sql);
                pre.setString(1,us.getNombre());
                pre.setString(2,us.getFechaModificacion());
                pre.setString(3,us.getPass());
                pre.setInt(4,us.getRol());
                pre.setInt(5,us.getCodigo());  
                 pre.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Datos Modificados");
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error al Modiificar"+e.toString());
            }
        
        }
        
        
       public void insertar(Usuario us)
       {
           try 
           {
            this.conectar();
            String sql="INSERT INTO usuario (idUsuario, idRol, usuario, contrasenia,"
                    + " fechaRegistro,fechaModifica,estado,fechaEliminacion)\n" +
                      " VALUES (?, ?, ?, ?, ?,?, ?,?);"; 
                    PreparedStatement pre=getCon().prepareStatement(sql);
                    pre.setInt(1, 0);
                    pre.setInt(2,us.getRol());
                    pre.setString(3,us.getNombre());
                    pre.setString(4,us.getPass());
                    pre.setString(5,us.getFechaRegistro());
                    pre.setString(6,us.getFechaModificacion());
                    pre.setInt(7,us.getEstado());
                    pre.setString(8,us.getFechaEliminacion());
                    pre.executeUpdate();
           }
           catch (Exception e) 
           {
               JOptionPane.showMessageDialog(null,"Error al insertar"+e.toString());
           }
           
           finally
           {
               desconectar();
           }
       
       }   
      
       public int Verificasiexiste(String usuario)
       {
           try 
           {
             ResultSet res=null;
            this.conectar();
            String sql="select count(idUsuario) from usuario where usuario=?"; 
                    PreparedStatement pre=getCon().prepareStatement(sql);
                    pre.setString(1, usuario);
                    
                   res=pre.executeQuery();
                   if(res.next())
                   {
                      return res.getInt(1);
                   }
                   return 1;
                   
           }
           catch (Exception e) 
           {
               return 1;
           }
           
           finally
           {
               desconectar();
           }
       
       } 
}
