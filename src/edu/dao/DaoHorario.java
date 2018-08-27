
package edu.dao;

import edu.conexion.Conexion;
import edu.modelo.CoordinadorSSE;
import java.sql.*;
import edu.modelo.HorarioAtencion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoHorario
 * Version:
 * Fecha: 15/08/18
 * CopyRight: SSE-ITCA
 * @author Roxana Menjivar
 */
public class DaoHorario extends Conexion{
    
    public List<HorarioAtencion>mostrarHorario()
    {
       List<HorarioAtencion> lista = new ArrayList();
       ResultSet res;
        try 
        {
            this.conectar();
            String sql ="select * from horarioAtencion where estado=1;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                HorarioAtencion ho = new HorarioAtencion();
                ho.setIdHorarioA(res.getInt("idHorarioA"));
                ho.setIdCoordinador(res.getInt("idCoordinador"));
                ho.setDia(res.getString("dia"));
                ho.setHoraDesde(res.getString("horaDesde"));
                ho.setHoraHasta(res.getString("horaHasta"));
                ho.setLugar(res.getString("lugar"));
                lista.add(ho);
            }
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Ocurrio un problema al mostrar los datos " +e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
       return lista;
    }
    
    public List<CoordinadorSSE>mostrarCoordinador()
    {
        List<CoordinadorSSE>listaCoord=new ArrayList();
        ResultSet res;
        try 
        {
            this.conectar();
            String sql="select * from coordinadorSSE;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                CoordinadorSSE cor = new CoordinadorSSE();
                cor.setIdCoordinador(Integer.parseInt(res.getString("idCoordinador")));
                cor.setNombre(res.getString("nombre"));
                listaCoord.add(cor);
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al mostrar coordinador " +e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
     return listaCoord;
    }
    
    public void insertar(HorarioAtencion hor)
    {
        try 
        {
            this.conectar();
            String sql="insert into horarioAtencion (idCoordinador,dia,horaDesde,horaHasta,lugar) values (?,?,?,?,?); ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, hor.getIdCoordinador());
            pre.setString(2, hor.getDia());
            pre.setString(3, hor.getHoraDesde());
            pre.setString(4, hor.getHoraHasta());
            pre.setString(5, hor.getLugar());
            pre.executeUpdate();
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Ingrese los datos que se piden ","Llenar campos",
                   JOptionPane.INFORMATION_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
    }
    
    public void modificar(HorarioAtencion ho)
    {
        try 
        {
            this.conectar();
            String sql="update horarioAtencion set idCoordinador=?, dia =?, horaDesde=?, horaHasta=?, lugar =?  where idHorarioA= ? ;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, ho.getIdCoordinador());
            pre.setString(2, ho.getDia());
            pre.setString(3, ho.getHoraDesde());
            pre.setString(4, ho.getHoraHasta());
            pre.setString(5, ho.getLugar());
            pre.setInt(6, ho.getIdHorarioA());
            pre.executeUpdate();
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,
                    "Imposible modificar, seleccione un registro " + e.getMessage(),
                    "Fallo al modificar " ,
                    JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
    }
    
    public void eliminar(int idHora)
    {
        try 
        {
            this.conectar();
            String sql="update horarioatencion set estado=0 where idHorarioA = ? ;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idHora);
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
    
    public HorarioAtencion getHorarioA(int idHorarioA)
    {
        HorarioAtencion hor = new HorarioAtencion();
        ResultSet res;
        try 
        {
           this.conectar();
           String sql="select * from horarioAtencion where idHorarioA = ?";
           PreparedStatement pre = this.getCon().prepareStatement(sql);
           pre.setInt(1, idHorarioA);
           res = pre.executeQuery();
           while(res.next())
           {
               hor.setIdHorarioA(res.getInt("idHorarioA"));
               hor.setIdCoordinador(res.getInt("idCoordinador"));
               hor.setDia(res.getString("dia"));
               hor.setHoraDesde(res.getString("horaDesde"));
               hor.setHoraHasta(res.getString("horaHasta"));
               hor.setLugar(res.getString("lugar"));
           }
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Error " +e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
        return hor;
    }
}
