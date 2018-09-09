
package edu.dao;

import ds.desktop.notify.DesktopNotify;
import edu.conexion.Conexion;
import edu.modelo.Usuario;
import edu.utilidades.Encriptacion;
import edu.utilidades.Validaciones;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    Validaciones val=new Validaciones();
    
    public int logear(String user, String pass)
    {   
        us.setNombre(user);
        us.setPass(pass);
        ResultSet res;
        int a=0;
        try 
        {
            Encriptacion en = new Encriptacion();
            this.conectar();
            String sql="call login(?,?);";
            PreparedStatement pre=this.getCon().prepareCall(sql);
            pre.setString(1, user);
            pre.setString(2, en.encriptar(pass));
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
            DesktopNotify.showDesktopMessage("Error al inciar", "",DesktopNotify.FAIL, 3000L);
            //JOptionPane.showMessageDialog(null, "Error al iniciar "+e.getMessage());
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
            Encriptacion en= new  Encriptacion();
            String sql="insert into usuario(idROl, usuario, contrasenia, fechaRegistro, estado) values(2, ?,?,?, 1);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1,nombre);
            pre.setString(2, en.encriptar("Itca123!"));
            pre.setString(3,fecha);
            pre.executeUpdate();
            DesktopNotify.showDesktopMessage("Usuario Creado exitosamente", "Ver usuario Creado",DesktopNotify.INPUT_REQUEST,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    DesktopNotify.showDesktopMessage("Informacion", "Credenciales del usuario:"
                            + "\n \n\n Nombre de usuario:"+nombre+"\n\n"
                            + "contrasña: Itca123!",DesktopNotify.INPUT_REQUEST);
                }
            });
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage("Error al insertar Usuario", "",DesktopNotify.FAIL, 3000L);
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
            Encriptacion en= new Encriptacion();
            this.conectar();
            String sql="select idUsuario as id from usuario where usuario=? and contrasenia=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1,nombre);
            pre.setString(2, en.encriptar("Itca123!"));
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
         catch (SQLException e) 
         {
             DesktopNotify.showDesktopMessage("Error", "",DesktopNotify.FAIL, 1000L);
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
        catch (NumberFormatException | SQLException e) 
        {
            DesktopNotify.showDesktopMessage("Error al mostrar Usuarios", "",DesktopNotify.FAIL, 3000L);
        }
        finally
        {
            desconectar();
        }
      return listaUsuario;
    }
        
    public void eliminarUsu(Usuario us)
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
            DesktopNotify.showDesktopMessage("Usuario Eliminado", "",DesktopNotify.SUCCESS, 3000L);
        }
        catch (HeadlessException | SQLException e)
        {
            DesktopNotify.showDesktopMessage("Error al eliminar Usuario", "",DesktopNotify.FAIL, 3000L);                
        }
    }
    
    public void Eliminar(int id)
        {
            try 
            {
                this.conectar();
                String sql="update usuario set estado=0,fechaEliminacion=? where idUsuario =?;";
                PreparedStatement pre= getCon().prepareStatement(sql);
                pre.setString(1,this.val.CapturarFecha());
                pre.setInt(2,id);
                pre.executeUpdate();                
                DesktopNotify.showDesktopMessage("Usuario eliminado", "",DesktopNotify.SUCCESS, 3000L);
            }
            catch (SQLException e)
            {
                DesktopNotify.showDesktopMessage("Error al eliminar Usuarios", "",DesktopNotify.FAIL, 3000L);
            }
        
        }
    
    public int idUs(int idCoord)  
    {
        ResultSet res;
        int id=-1;
            try 
            {
                this.conectar();
                String sql="select usuario_idUsuario from coordinadorsse where idcoordinador=?;";
                PreparedStatement pre= getCon().prepareStatement(sql);
                pre.setInt(1,idCoord);
                res=pre.executeQuery();
                while(res.next())
                {
                    id=res.getInt("usuario_idUsuario");
                }                
                //JOptionPane.showMessageDialog(null,"Datos eliminados");
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error al extraer el id usuario: "+e.toString());
            }
            finally
            {
                this.desconectar();
            }
            return id;
        }
                 
    public void Modificar(Usuario us)
        {
            try 
            {
                this.conectar();
                String sql="update usuario set usuario=?,fechaModifica=?,contrasenia=? where idUsuario=?";
                PreparedStatement pre= getCon().prepareStatement(sql);
                pre.setString(1,us.getNombre());
                pre.setString(2,us.getFechaModificacion());
                pre.setString(3,us.getPass());
                pre.setInt(4,us.getCodigo());  
                 pre.executeUpdate();
                
                DesktopNotify.showDesktopMessage("EXITO!", us.getNombre()+" Modificado con exito",DesktopNotify.SUCCESS, 3000L);                        

            }
            catch (HeadlessException | SQLException e)
            {
                DesktopNotify.showDesktopMessage("ERROR!", "Ocurrio un error al tratar de modificar Usuario",DesktopNotify.ERROR, 3000L);                        
            }
            finally{
                this.desconectar();                
            }        
        }
    public void Modificar2(Usuario us)
        {
            try 
            {
                this.conectar();
                String sql="update usuario set usuario=?,fechaModifica=? where idUsuario=?";
                PreparedStatement pre= getCon().prepareStatement(sql);
                pre.setString(1,us.getNombre());
                pre.setString(2,us.getFechaModificacion());
                pre.setInt(3,us.getCodigo());  
                pre.executeUpdate();                
               DesktopNotify.showDesktopMessage("EXITO!", "se modificó sus credenciales exitosamente",DesktopNotify.SUCCESS, 3000L);                        
            }
            catch (HeadlessException | SQLException e)
            {
                DesktopNotify.showDesktopMessage("ERROR!", "Ocurrio un error al tratar de modificar su usuario",DesktopNotify.ERROR, 3000L);                        
            }
            finally{
                this.desconectar();                
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
           catch (SQLException e) 
           {
               DesktopNotify.showDesktopMessage("Error al Insertar Usuarios", "",DesktopNotify.FAIL, 3000L);
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
