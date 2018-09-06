
package edu.dao;

import java.sql.*;
import edu.conexion.Conexion;
import edu.modelo.CoordinadorSSE;
import edu.modelo.Usuario;
import edu.utilidades.Encriptacion;
import edu.vistas.Menu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase:DaoCoordinador
 * Fecha:12/08/18
 * Versión: 1.0
 * CopyRight:SSE-ITCA
 * @author Roxana Menjivar
 */

public class DaoCoordinador extends Conexion{
    
    DaoUsuario daoUs=new DaoUsuario();
    Menu menu=new Menu();
    
    public List mostrarCoordinador()
    {
        List lista = new ArrayList();
        ResultSet res;
        try 
        {
            this.conectar();
            String sql="select * from coordinadorsse where estado=1;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                CoordinadorSSE cor = new CoordinadorSSE();
                cor.setIdCoordinador(res.getInt("idCoordinador"));
                cor.setNombre(res.getString("nombre"));
                cor.setCorreo(res.getString("correo"));
                cor.setIdUsuario(res.getInt("usuario_idUsuario"));
                cor.setIdCarrera(res.getInt("carrera_idCarrera"));
                lista.add(cor);
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al mostrar " +e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
        return lista;
    }
    
    public void insertarCoordinador(CoordinadorSSE cor)
    {
        //Capturar un solo nombre
        String nombre=cor.getNombre();
        String fecha=cor.getFechaRegistro();
        
        if(daoUs.VerificarUsuario(nombre)==0)
        {
            String[] nombres = nombre.split(" ");
            String nombreUser=nombres[0];        
            int idUser=0;
            
            //Agregar usuario
            daoUs.addUsuario(nombreUser,fecha);
            //Capturar usuario
            idUser=daoUs.getIdUsuario(nombreUser);
            //Inserar Coordinador
            addCoordinador(cor, idUser);
        }
        else
        {
            String[] nombres = nombre.split(" ");
            int idCor=cor.getIdCoordinador();
            String nombreUser=nombres[0]+idCor;        
            int idUser=0;
            
            //Agregar usuario
            daoUs.addUsuario(nombreUser,fecha);
            //Capturar usuario
            idUser=daoUs.getIdUsuario(nombreUser);
            //Inserar Coordinador
            addCoordinador(cor, idUser);
        }
    }
    
    public void addCoordinador(CoordinadorSSE cor, int iduser)
    {
        try 
        {
            this.conectar();
            String sql="insert into coordinadorsse (nombre,correo,estado,usuario_idUsuario,fechaRegistro,carrera_idCarrera) values(?,?,1,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1,cor.getNombre());
            pre.setString(2,cor.getCorreo());
            pre.setInt(3, iduser);
            pre.setString(4,cor.getFechaRegistro());
            pre.setInt(5, cor.getIdCarrera());
            //pre.setInt(4,cor.getIdCarrera());
            pre.executeUpdate();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al insertar coordinador "+e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
    }
    
    public void modificarCoordinador(CoordinadorSSE cor)
    {
        try 
        {
            this.conectar();
            String sql = "update coordinadorsse set nombre = ? , correo = ?,fechaModificacion =?, carrera_idCarrera =?  where idCoordinador = ? ;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, cor.getNombre());
            pre.setString(2, cor.getCorreo());
            pre.setString(3,cor.getFechaModificacion());
            pre.setInt(4, cor.getIdCarrera());
            pre.setInt(5, cor.getIdCoordinador());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
          JOptionPane.showMessageDialog(null,"Error al modificar datos "+e.getMessage());
        }
        finally 
        {
            this.desconectar();
        }
    }

    public void eliminarCoordinador(int idCoor)
    {
        try 
        {
            this.conectar();
            String sql = "delete from coordinadorsse where idCoordinador = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idCoor);
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,
                    "Imposible Eliminar no seleccinado " + e.getMessage(),
                    "Eliminacion Fallida " ,
                    JOptionPane.ERROR_MESSAGE);
        } 
        finally 
        {
            this.desconectar();
        }
    }
    
    public void eliminarCLogico(CoordinadorSSE cor)
    {
        try 
        {
            this.conectar();
            String sql = "update coordinadorsse set estado=0, fechaEliminacion=? where idCoordinador=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1,cor.getFechaEliminacion());
            pre.setInt(2, cor.getIdCoordinador());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,
                    "Imposible Eliminar no seleccinado " + e.getMessage(),
                    "Eliminacion Fallida " ,
                    JOptionPane.ERROR_MESSAGE);
        } 
        finally 
        {
            this.desconectar();
        }
    }

    public CoordinadorSSE getIdCoordinador(int idCoordinador)
    {
        CoordinadorSSE cor = new  CoordinadorSSE();
        ResultSet res;
        try 
        {
            this.conectar();
            String sql = "select * from coordinadorsse where idCoordinador = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idCoordinador);
            res = pre.executeQuery();
            while (res.next()) 
            {
                cor.setIdCoordinador(res.getInt("idCoordinador"));
                cor.setNombre(res.getString("nombre"));
                cor.setCorreo(res.getString("correo"));
                cor.setIdUsuario(res.getInt("usuario_idUsuario"));
                cor.setIdCarrera(res.getInt("carrera_idCarrera"));
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Hubo un problema en la consulta  " +e.getMessage());
        } 
        finally 
        {
            this.desconectar();
        }
        return cor;
    }
    
    public int verificarCarrera(int idCar)
    {
        int r=0;
        ResultSet res;
        try 
        {
            this.conectar();
            String sql = "select nombre from coordinadorsse where estado=1 and carrera_idCarrera = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idCar);
            res = pre.executeQuery();
            if(res.next()) 
            {
                r=1;
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Hubo un problema en la consulta  " +e.getMessage());
        } 
        finally 
        {
            this.desconectar();
        }
        return r;
        } 
    
    public int getidUs(String nombre, String pass)
        {            
            ResultSet res;
            int a=0;
            try 
            {
                Encriptacion n= new Encriptacion();                
                this.conectar();
                String sql="select  idUsuario from  usuario where usuario=? and contrasenia=?;";
                PreparedStatement pre=this.getCon().prepareCall(sql);                
                pre.setString(1, nombre);
                pre.setString(2, n.encriptar(pass));              
                res=pre.executeQuery();
                if (res.next()) 
                {
                    a=res.getInt("idUsuario");                    
                    JOptionPane.showMessageDialog(null, a);
                }
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error al capturar ID de usuario"+e.getMessage());
            }
            finally
            {
                this.desconectar();
            }
            return a;            
        }
            
    public List getNombreIdCoR(int id)
        {            
            ResultSet res;
            String nombre="";
            int idCor=0;
            List ls=new ArrayList();                        
            try 
            {
                this.conectar();
                String sql="select idCoordinador as id ,nombre from coordinadorsse where usuario_IdUSuario=?;";
                PreparedStatement pre=this.getCon().prepareCall(sql);
                pre.setInt(1, id);
                res=pre.executeQuery();
                if (res.next()) 
                {
                    nombre=res.getString("nombre");
                    idCor=res.getInt("id");
                    ls.add(nombre);
                    ls.add(idCor);
                }
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error al capturar ID de usuario"+e.getMessage());
            }
            finally
            {
                this.desconectar();
            }
            return ls;            
        }
    
    
       public int idCoor(int idus)  
    {
        ResultSet res;
        int id=-1;
            try 
            {
                this.conectar();
                String sql="select idcoordinador from coordinadorsse where usuario_idUsuario=?;";
                PreparedStatement pre= getCon().prepareStatement(sql);
                pre.setInt(1,idus);
                res=pre.executeQuery();
                while(res.next())
                {
                    id=res.getInt("idcoordinador");
                }
                
                JOptionPane.showMessageDialog(null,"Datos elimnados");
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro "+e.toString());
            }
            finally
            {
                this.desconectar();
            }
            return id;
        }
    }
