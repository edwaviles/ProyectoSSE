
package edu.dao;

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
 * Version:
 * Fecha: 15/08/18
 * CopyRight: SSE-ITCA
 * @author Roxana Menjivar
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
                sql ="select * from horarioAtencion where estado=1 and idCoordinador="+men.getLsUs().get(1);
                pre = this.getCon().prepareStatement(sql);            
            }
            else{                                                
               sql ="select * from horarioAtencion where estado=1 and idCoordinador="+men.getLsUs().get(1)+" and dia=?;";
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
            JOptionPane.showMessageDialog(null, "El registro se ha guardado con exito", "Horario coordinador",
                                    JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Ingrese los datos que se piden "+e.getMessage());
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
            String sql="update horarioAtencion set dia =?, horaDesde=?, horaHasta=?, lugar =?, fechaModificacion = ? minutosDesde=?, minutosHasta=? where idHorarioA= ? ;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(2, ho.getDia());
            pre.setInt(3, ho.getHoraDesde());
            pre.setInt(4, ho.getHoraHasta());
            pre.setString(5, ho.getLugar());
            pre.setString(6, ho.getFechaModificacion());
            pre.setInt(7, ho.getMinutosDesde());
            pre.setInt(8, ho.getMinutosHasta());
            pre.setInt(9, ho.getIdHorarioA());
            pre.executeUpdate();
        }
        catch (SQLException e) 
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
               //hor.setHoraDesde(res.getString("horaDesde"));
               //hor.setHoraHasta(res.getString("horaHasta"));
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
