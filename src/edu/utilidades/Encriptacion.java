
package edu.utilidades;

import edu.conexion.Conexion;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase:Encriptacion
 * Verion:1.0
 * Fecha:25/08/18
 * CopyRight:SSE-ITCA
 * @author Nancy Lopez
 */
public class Encriptacion extends Conexion{
    
    public String encriptar(String md5)
    {
        try 
        {
            this.conectar();
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<array.length;i++)
            {
                sb.append(Integer.toHexString((array[i] & 0xFF)|0x100).substring(1,3));
            }
            return sb.toString();
        } 
        catch (java.security.NoSuchAlgorithmException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al encriptar contraseña: "+ e,"Error",JOptionPane.WARNING_MESSAGE);
        }       
        return null;
    }  
}
