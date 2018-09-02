
package edu.utilidades;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Nombre de la clase: Validaciones
 * Version:
 * Fecha: 11/08/18
 * CopyRight: Proyecto Control SSE
 * @author Roxana Menjivar
 */
public class Validaciones {
    
    public void numbersOnly(KeyEvent evt) 
    {
        if (!Character.isDigit(evt.getKeyChar())) 
        {
            evt.consume();
        }
    }
    
    public void wordsOnly(KeyEvent evt) 
    {
        if (!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE)
        {
            evt.consume();
        }
    }

    public boolean IsNullOrEmpty(String val) 
    {
        if (val.equals("")) 
        {
            return true;
        }
        if (val.length() == 0) 
        {
            return true;
        }
        if (val == null) 
        {
            return true;
        }
        return false;
    } 
    
    public String CapturarFecha(){
        String fecha="";
        
        Date f = new Date();
        SimpleDateFormat formatoF = new SimpleDateFormat("yyyy-MM-dd");
        fecha=formatoF.format(f);
        
        return fecha;
    }
    
    public boolean ValidarC(String correo)
    {
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$");
        
        Matcher mather = pattern.matcher(correo);
 
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }
}
