
package edu.dao;

import ds.desktop.notify.DesktopNotify;
import edu.conexion.Conexion;
import edu.modelo.CoordinadorSSE;
import java.sql.*;
import edu.modelo.HorarioAtencion;
import edu.vistas.Menu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoHorario
 * Version:1.0
 * Fecha: 15/08/18
 * CopyRight: SSE-ITCA
 * @author Ernesto Aviles
 */
public class DaoHorario extends Conexion{
    Menu men= new Menu();
    public List<HorarioAtencion>mostrarHorario(String dia)
    {
       List<HorarioAtencion> lista = new ArrayList();
       ResultSet res;
        try 
        {
            this.conectar();
            String sql="";  
            PreparedStatement pre;
            
            if (dia.equals("Todos")) 
            {               
                sql ="select * from horarioatencion where estado=1 and idCoordinador="+men.getLsUs().get(1);
                pre = this.getCon().prepareStatement(sql);            
            }
            else{                                                
               sql ="select * from horarioatencion where estado=1 and idCoordinador="+men.getLsUs().get(1)+" and dia=?;";
               pre = this.getCon().prepareStatement(sql);
               pre.setString(1, dia);
            }
            
            res=pre.executeQuery();
            while(res.next())
            {
                HorarioAtencion ho = new HorarioAtencion();
                ho.setIdHorarioA(res.getInt("idHorarioA"));
                ho.setIdCoordinador(res.getInt("idCoordinador"));
                ho.setDia(res.getString("dia"));
                ho.setHoraDesde(res.getInt("horaDesde"));
                ho.setMinutosDesde(res.getInt("minutosDesde"));
                ho.setHoraHasta(res.getInt("horaHasta"));
                ho.setMinutosHasta(res.getInt("minutosHasta"));
                ho.setLugar(res.getString("lugar"));
                lista.add(ho);
            }
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage("Ocurrió un problema al mostrar sus Horarios", "",DesktopNotify.FAIL, 3000L);                                    
            //JOptionPane.showMessageDialog(null,"Ocurrio un problema al mostrar los datos " +e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
       return lista;
    }
    
    
    public List<HorarioAtencion>MostrarParaEditarHorario(String dia, int idH)
    {
       List<HorarioAtencion> lista = new ArrayList();
       ResultSet res;
        try 
        {
            this.conectar();
            String sql="";  
            PreparedStatement pre;
            sql ="select * from horarioatencion where estado=1 and idCoordinador="+men.getLsUs().get(1)+" and dia=? and `idHorarioA` not IN (?);";
            pre = this.getCon().prepareStatement(sql);
            pre.setString(1, dia);
            pre.setInt(2, idH);  
            res=pre.executeQuery();
            while(res.next())
            {
                HorarioAtencion ho = new HorarioAtencion();
                ho.setHoraDesde(res.getInt("horaDesde"));
                ho.setMinutosDesde(res.getInt("minutosDesde"));
                ho.setHoraHasta(res.getInt("horaHasta"));
                ho.setMinutosHasta(res.getInt("minutosHasta"));
                lista.add(ho);
            }
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage("Error!", "",DesktopNotify.FAIL, 3000L);
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
            String sql="select * from coordinadorsse;";
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
            String sql="INSERT INTO `horarioatencion` (`idCoordinador`, `dia`, `horaDesde`, `minutosDesde`, `horaHasta`, `minutosHasta`, `lugar`, `estado`, `fechaRegistro`) VALUES (?,?,?,?,?,?,?,1,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(men.getLsUs().get(1).toString()));
            pre.setString(2, hor.getDia());
            pre.setInt(3, hor.getHoraDesde());
            pre.setInt(4, hor.getMinutosDesde());
            pre.setInt(5, hor.getHoraHasta());
            pre.setInt(6, hor.getMinutosHasta());
            pre.setString(7, hor.getLugar());
            pre.setString(8, hor.getFechaRegistro());
            pre.executeUpdate();
            DesktopNotify.showDesktopMessage("Horario coordinador", "El registro se ha guardado con exito",DesktopNotify.SUCCESS, 3000L);                                    
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage("Ingrese los datos que se piden", "",DesktopNotify.WARNING, 3000L);
//            JOptionPane.showMessageDialog(null,"Ingrese los datos que se piden "+e.getMessage());
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
            String sql="update horarioatencion set dia =?, horaDesde=?, minutosDesde=?, horaHasta=?, minutosHasta=?, lugar =?, fechaModificacion = ? where idHorarioA= ? ;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, ho.getDia());
            pre.setInt(2, ho.getHoraDesde());
            pre.setInt(3, ho.getMinutosDesde());
            pre.setInt(4, ho.getHoraHasta());
            pre.setInt(5, ho.getMinutosHasta());
            pre.setString(6, ho.getLugar());
            pre.setString(7, ho.getFechaModificacion());                        
            pre.setInt(8, ho.getIdHorarioA());
            pre.executeUpdate();
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage("Error al modificar Horario", "",DesktopNotify.FAIL, 3000L);
        }
        finally
        {
            this.desconectar();
        }
    }
    
    public void eliminar(HorarioAtencion hor)
    {
        try 
        {
            this.conectar();
            String sql="update horarioatencion set estado=0, fechaEliminacion = ? where idHorarioA = ? ;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, hor.getFechaEliminacion());
            pre.setInt(2, hor.getIdHorarioA());
            pre.executeUpdate();
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage("Error al eliminar horario", "",DesktopNotify.FAIL, 3000L);
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
           String sql="select * from horarioatencion where idHorarioA = ?";
           PreparedStatement pre = this.getCon().prepareStatement(sql);
           pre.setInt(1, idHorarioA);
           res = pre.executeQuery();
           while(res.next())
           {
               hor.setIdHorarioA(res.getInt("idHorarioA"));
               hor.setIdCoordinador(res.getInt("idCoordinador"));
               hor.setDia(res.getString("dia"));
               //hor.setHoraDesde(res.getString("horaDesde"));
               //hor.setHoraHasta(res.getString("horaHasta"));
               hor.setLugar(res.getString("lugar"));
           }
        }
        catch (SQLException e) 
        {
            DesktopNotify.showDesktopMessage(e.getMessage(), "",DesktopNotify.WARNING, 3000L);
        }
        finally
        {
            this.desconectar();
        }
        return hor;
    }
}
