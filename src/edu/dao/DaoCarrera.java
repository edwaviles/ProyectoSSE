
package edu.dao;

import ds.desktop.notify.DesktopNotify;
import edu.conexion.Conexion;
import edu.modelo.Carrera;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase:DaoCarrera
 * Fecha:12/08/18
 * Versión: 1.0
 * CopyRight:SSE-ITCA
 * @author Roxana Menjivar
 */
public class DaoCarrera extends Conexion{
    
    public List<Carrera>mostrarCarrera(int idEscuela)
    {
        List<Carrera> lista = new ArrayList();
        ResultSet res;
        try 
        {
            this.conectar();
            String sql = "select * from carrera where escuela_idEscuela=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idEscuela);
            res=pre.executeQuery();
            while(res.next())
            {
                Carrera car = new Carrera();
                car.setIdCarrera(Integer.parseInt(res.getString("idCarrera")));
                car.setNombre(res.getString("nombre"));
                car.setIdEscuela(res.getInt("escuela_idEscuela"));
                lista.add(car);
            }
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage("Error al mostrar la lista de carreras", "",DesktopNotify.FAIL, 3000L);                        
            //JOptionPane.showMessageDialog(null,"No se pudo mostrar la lista de carreras " +e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
        return lista;
    }
    
    public Carrera getidCarrera(int car)
    {
        Carrera c = new Carrera();
        ResultSet res;
        
        try 
        {
            this.conectar();
            String sql = "Select * from carrera where idCarrera = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, car);
            res = pre.executeQuery();
            
            while(res.next())
            {
                c.setIdCarrera(res.getInt("idCarrera"));
                c.setNombre(res.getString("nombre"));
                c.setIdEscuela(res.getInt("escuela_idEscuela"));
            }
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage("No se mostrar datos", "",DesktopNotify.FAIL, 3000L);                        
            //JOptionPane.showMessageDialog(null,"No se pudo mostrar datos " +e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
        return c;
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
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Hubo un problema en la consulta  " +e.getMessage());
        } 
        finally 
        {
            this.desconectar();
        }
        return r;
    } 
     
    public int verificarCarM(int idCar)
    {
        int r=0;
        ResultSet res;
        try 
        {
            this.conectar();
            String sql = "select idCoordinador from coordinadorsse where carrera_idCarrera = ? and estado=1;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idCar);
            res = pre.executeQuery();
            if(res.next()) 
            {
                r=res.getInt("idCoordinador");
            }
        } 
        catch (SQLException e) 
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
