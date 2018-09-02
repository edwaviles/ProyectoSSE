
package edu.dao;
import java.sql.*;

import edu.conexion.Conexion;

/**
 *
 * @author Ernesto Aviles
 */
public class DaoNotificacion extends Conexion{
       public int getNoti()
    {
        ResultSet res;
        int ls=0;
        try 
        {
            this.conectar();
            String sql="select count(*) as numero from notificacion where estado=1";
            PreparedStatement pre=getCon().prepareStatement(sql);
            res=pre.executeQuery();
            while (res.next()) 
            {                
                ls=res.getInt("numero");        
            }
            
        } 
        catch (SQLException e) 
        {
            
        }
        return ls;    
    }
       
}
