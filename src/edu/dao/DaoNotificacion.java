
package edu.dao;
import java.sql.*;

import edu.conexion.Conexion;

/**
 * Nombre de la clase: DaoNotificacion
 * Version:1.0
 * Fecha:22/08/2018
 * CopyRight:SSE-ITCA
 * @author Ernesto Aviles
 */
public class DaoNotificacion extends Conexion{
    
    public int getNoti(int id)
    {
        ResultSet res;
        int ls=0;
        try 
        {
            this.conectar();
            String sql="select count(*) as numero from solicitudsse s inner join alumno a "
                    + "on s.idAlumno= a.idAlumno INNER JOIN carrera c "
                    + "on c.idCarrera=a.carrera INNER JOIN coordinadorsse cor "
                    + "on cor.carrera_idCarrera=c.idCarrera WHERE cor.idCoordinador=? and s.estado=0;";
            PreparedStatement pre=getCon().prepareStatement(sql);
            pre.setInt(1, id);
            res=pre.executeQuery();
            while (res.next()) 
            {                
                ls=res.getInt("numero");        
            }
            
        } 
        catch (SQLException e) 
        {
            
        }
        finally
        {
            this.desconectar();
        }
        return ls;    
    }
       
}
