
package edu.dao;

import java.sql.*;
import edu.conexion.Conexion;
import edu.modelo.CoordinadorSSE;
import edu.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase:DaoCoordinador
 * Fecha:12/08/18
 * Versión: 1.6
 * CopyRight:SSE-ITCA
 * @author Roxana Menjivar
 */

public class DaoCoordinador extends Conexion{
    
    DaoUsuario daoUs=new DaoUsuario();
    
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
                cor.setIdCarrera(res.getInt("carrera_idCarrera"));//
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
        String[] nombres = nombre.split(" ");
        String nombreUser=nombres[0];        
        int idUser=0;
        
        //Agregar usuario
        daoUs.addUsuario(nombreUser);
        //Capturar usuario
        idUser=daoUs.getIdUsuario(nombreUser);
        //Inserar Coordinador
        addCoordinador(cor, idUser);
    }
    
    public void addCoordinador(CoordinadorSSE cor, int iduser)
    {
        try 
        {
            this.conectar();
            String sql="insert into coordinadorsse (nombre,correo,usuario_idUsuario,carrera_idCarrera,estado) values(?,?,?,?,1);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1,cor.getNombre());
            pre.setString(2,cor.getCorreo());
            pre.setInt(3, iduser);
            pre.setInt(4, cor.getIdCarrera());
            //pre.setInt(4,cor.getIdCarrera());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "coor add");
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
            String sql = "update coordinadorSSE set nombre = ? , correo = ?, carrera_idCarrera =?  where idCoordinador = ? ;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, cor.getNombre());
            pre.setString(2, cor.getCorreo());
            pre.setInt(3, cor.getIdCarrera());
            pre.setInt(4, cor.getIdCoordinador());
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
            String sql = "delete from coordinadorSSE where idCoordinador = ?;";
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
    
    public void eliminarCLogico(int idCoor)
    {
        try 
        {
            this.conectar();
            String sql = "update coordinadorsse set estado=0 where idCoordinador=?;";
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

    public CoordinadorSSE getIdCoordinador(int idCoordinador)
    {
        CoordinadorSSE cor = new  CoordinadorSSE();
        ResultSet res;
        try 
        {
            this.conectar();
            String sql = "select * from coordinadorSSE where idCoordinador = ?";
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
            String sql = "select nombre from coordinadorSSE where estado=1 and carrera_idCarrera = ?;";
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
    }