
package edu.conexion;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: Conexion
 * Version: 1.0
 * Fecha:05/08/18
 * CopyRight: SSE-ITCA
 * @author Roxana Menjivar
 */
public class Conexion {
    private Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public void conectar()
    {
        try 
        {
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectosse", "reniery", "19980802");
        } 
        catch (SQLException | ClassNotFoundException e) 
        {
            JOptionPane.showMessageDialog(null,"Error de conexion " +e.getMessage());
        }   
    }
    
    public void desconectar()
    {
        try 
        {
            if(con!=null)
            {
                if(!con.isClosed())
                {
                    con.close();
                }
            }
        }
        catch (Exception e) 
        {
            
        }
    }
}
