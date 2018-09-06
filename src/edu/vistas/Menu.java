
package edu.vistas;

import edu.dao.DaoUsuario;
import edu.modelo.Usuario;
import java.util.List;
import javax.swing.JOptionPane;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import edu.dao.DaoNotificacion;
import edu.dao.DaoRol;
import edu.modelo.Notificacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Nombre de la clase: Menu
 * Version: 1.0
 * Fecha:11/08/18
 * CopyRight:SSE-ITCA
 * @author Roxana Menjivar
 */
public class Menu extends javax.swing.JFrame {

    FrmCoordinador gestionC;
    FrmSSSE frmsse;
    FrmHorario gestionH;
    FrmUsuario gestionU;
    FrmSSSE gestionS;
    FrmReporteRegistroUsuario reportes;
    Usuario use=new Usuario();
    public static List lsUs;
    public static int idUs;
    public static boolean FormularioVal=false;
    DaoNotificacion noti=new DaoNotificacion();
    DaoRol DRol=new DaoRol();
    private Timer timer;
    private int delay = 30000;
    
    public Menu() {
        initComponents();        
        setIconImage(new ImageIcon(getClass().getResource("../iconos/logo.png")).getImage());
        this.setExtendedState(MAXIMIZED_BOTH); 
        } 
  
    public void correr()
    {        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                ActionListener action = new ActionListener()
                {   int numero2=0;
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        timer.stop();                         
                        int numero=noti.getNoti(Integer.parseInt(lsUs.get(1).toString()));                        
                        if (numero>numero2) 
                        {
                            numero2=numero;
                            DesktopNotify.showDesktopMessage("", "Tienes "+numero+" de Solicitudes de SSE\n ¿desea atenderlas ahora?",DesktopNotify.INPUT_REQUEST, new ActionListener() 
                            {
                                @Override
                                public void actionPerformed(ActionEvent ae) 
                                {

                                    int SioNo = JOptionPane.showConfirmDialog(null, "¿Desea revisar solicuudes?", "Advertencia", JOptionPane.YES_NO_OPTION);                            
                                    if (SioNo == 0) 
                                    {
                                        abrirNotificaciones(); 
                                        timer.start();
                                    }
                                    else
                                    {
                                        timer.start();
                                    }
                                }                            
                            });
                        }else{
                            timer.start();
                        }
                            
                    }
                };
                timer = new Timer(delay, action);
                timer.setInitialDelay(0);
                timer.start();
            }
        });
    }
    
    public void abrirNotificaciones()
    {
         if(FormularioVal==false)
        {
            this.frmsse = new FrmSSSE();
            this.desktopPane.add(frmsse);
            frmsse.setVisible(true);
            frmsse.setLocation(
            desktopPane.getWidth()/2 - frmsse.getWidth()/2,
            desktopPane.getHeight()/2 - frmsse.getHeight()/2);
            FormularioVal=true;
            int numero=noti.getNoti(Integer.parseInt(lsUs.get(1).toString()));
            List lsidSSE=noti.getSSE(Integer.parseInt(lsUs.get(1).toString()));
            for (int i = 0; i < lsidSSE.size(); i++) 
            {
                noti.elimiarVistos(Integer.parseInt(lsidSSE.get(i).toString()));
            }
        }
    }
    
    public void pasarDatos(List r)
    {
        lsUs=r; 
        try {
            this.setTitle("Sistema SSE - "+lsUs.get(0).toString());
        } catch (Exception e) {
            
        }        
    }
    public void pasarUsuario(int id)
    {
        idUs=id;
    }
    public static int retornarUsuario()
    {
        return idUs;
    }    
    

    public  List getLsUs() 
    {
        return lsUs;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        GestionarUsuario = new javax.swing.JMenu();
        solicitudes = new javax.swing.JMenu();
        GestionCoordinador = new javax.swing.JMenu();
        GestionHorarios = new javax.swing.JMenu();
        editMenu1 = new javax.swing.JMenu();
        jreport = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setBackground(java.awt.SystemColor.activeCaption);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 941, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );

        menuBar.setBackground(new java.awt.Color(0, 0, 0));

        GestionarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/us.png"))); // NOI18N
        GestionarUsuario.setText("Gestion de Usuarios");
        GestionarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GestionarUsuarioMouseClicked(evt);
            }
        });
        menuBar.add(GestionarUsuario);

        solicitudes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/sss.png"))); // NOI18N
        solicitudes.setText("Solicitudes");
        solicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solicitudesMouseClicked(evt);
            }
        });
        menuBar.add(solicitudes);

        GestionCoordinador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/coordinador.png"))); // NOI18N
        GestionCoordinador.setMnemonic('f');
        GestionCoordinador.setText("Gestion de Coordinador");
        GestionCoordinador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GestionCoordinadorMouseClicked(evt);
            }
        });
        menuBar.add(GestionCoordinador);

        GestionHorarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/calendar.png"))); // NOI18N
        GestionHorarios.setMnemonic('e');
        GestionHorarios.setText("Gestion de Horarios");
        GestionHorarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GestionHorariosMouseClicked(evt);
            }
        });
        menuBar.add(GestionHorarios);

        editMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/reporte.png"))); // NOI18N
        editMenu1.setMnemonic('e');
        editMenu1.setText("Reportes");
        editMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMenu1MouseClicked(evt);
            }
        });

        jreport.setText("Reporte de usuarios");
        jreport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jreportActionPerformed(evt);
            }
        });
        editMenu1.add(jreport);

        jMenuItem3.setText("Reporte de coordinadores");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        editMenu1.add(jMenuItem3);

        jMenuItem4.setText("Reporte de coordinadores y usuarios");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        editMenu1.add(jMenuItem4);

        jMenuItem5.setText("Reporte de registro de usuarios");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        editMenu1.add(jMenuItem5);

        menuBar.add(editMenu1);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/ajustes.png"))); // NOI18N

        jMenuItem1.setText("Cerrar seession");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GestionCoordinadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GestionCoordinadorMouseClicked
        if(FormularioVal==false)
        {
            if(idUs==1||idUs==3)
            {
                this.gestionC = new FrmCoordinador();
                this.desktopPane.add(gestionC);
                gestionC.setVisible(true);
                gestionC.setLocation(
                desktopPane.getWidth()/2 - gestionC.getWidth()/2,
                desktopPane.getHeight()/2 - gestionC.getHeight()/2);
                FormularioVal=true;
                idUs=0;
                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No autorizado");
            }           
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana abierta!");
        }
    }//GEN-LAST:event_GestionCoordinadorMouseClicked
   
    public void cerrar()
    {
         FormularioVal=false;
    }
    
    private void GestionHorariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GestionHorariosMouseClicked
        if(FormularioVal==false)
        {
             if(idUs==2)
            {
                this.gestionH = new FrmHorario();
                this.desktopPane.add(gestionH);
                gestionH.setVisible(true);
                gestionH.setLocation(
                desktopPane.getWidth()/2 - gestionH.getWidth()/2,
                desktopPane.getHeight()/2 - gestionH.getHeight()/2);
                FormularioVal=true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No autorizado");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana abierta!");
        }
    }//GEN-LAST:event_GestionHorariosMouseClicked
                                             

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
        Login log=new Login();
        log.setVisible(true);
        FormularioVal=false;
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int r=JOptionPane.showConfirmDialog(null, "Desea salir", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION);
        if (r==0) 
        {
            if (!FormularioVal) {
                 System.exit(0);
            }    
            else{
                JOptionPane.showMessageDialog(null, "Cierre la ventana abierta antes de cerrar");
            }
        }
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed
      
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        
    }//GEN-LAST:event_jMenu2MouseClicked

    private void solicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solicitudesMouseClicked
        if(FormularioVal==false)
        {    
             if(idUs==2)
            {
                this.gestionS = new FrmSSSE();
                this.desktopPane.add(gestionS);
                gestionS.setVisible(true);
                gestionS.setLocation(
                desktopPane.getWidth()/2 - gestionS.getWidth()/2,
                desktopPane.getHeight()/2 - gestionS.getHeight()/2);
                FormularioVal=true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No autorizado");
            }
           
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana abierta!");
        }
    }//GEN-LAST:event_solicitudesMouseClicked

    private void GestionarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GestionarUsuarioMouseClicked
        if(FormularioVal==false)
        {
             if(idUs==1||idUs==3)
            {
                this.gestionU = new FrmUsuario();
                this.desktopPane.add(gestionU);
                gestionU.setVisible(true);
                gestionU.setLocation(
                desktopPane.getWidth()/2 - gestionU.getWidth()/2,
                desktopPane.getHeight()/2 - gestionU.getHeight()/2);
                FormularioVal=true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No autorizado");
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana abierta!");
        }
    }//GEN-LAST:event_GestionarUsuarioMouseClicked

    private void jreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jreportActionPerformed
        if (idUs==1) 
            {                
                Connection Conexion=null;
                JasperReport reporte;

               try 
               {
                   Class.forName("com.mysql.jdbc.Driver");
                   Conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectosse","root","");
               } 
               catch (ClassNotFoundException | SQLException e) 
               {
                   System.out.println(e.getMessage());
               }
               try {
                   reporte=JasperCompileManager.compileReport("src/edu/reportes/ReporteUser.jrxml");
                   JasperPrint jp= JasperFillManager.fillReport(reporte,null,Conexion);
                   JasperViewer.viewReport(jp,false);
               } catch (JRException ex) {
                   Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
               }
            }else{
            JOptionPane.showMessageDialog(null, "No autorizado");
        }
    }//GEN-LAST:event_jreportActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (idUs==1) 
            {                    
                Connection Conexion=null; 
               JasperReport reporte;

              try 
              {
                  Class.forName("com.mysql.jdbc.Driver");
                  Conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectosse","root","");
              } 
              catch (ClassNotFoundException | SQLException e) 
              {
                  System.out.println(e.getMessage());
              }
              try {
                  reporte=JasperCompileManager.compileReport("src/edu/reportes/reporteCoor.jrxml");
                  JasperPrint jp= JasperFillManager.fillReport(reporte,null,Conexion);
                  JasperViewer.viewReport(jp,false);
              } catch (JRException ex) {
                  Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
              }
            }else{
            JOptionPane.showMessageDialog(null, "No aurotizado");
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (idUs==1) 
        {                
            Connection Conexion=null;
             JasperReport reporte;

            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                Conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectosse","root","");
            } 
            catch (ClassNotFoundException | SQLException e) 
            {
                System.out.println(e.getMessage());
            }
            try {
                reporte=JasperCompileManager.compileReport("src/edu/reportes/reporteUsuarioCoor.jrxml");
                JasperPrint jp= JasperFillManager.fillReport(reporte,null,Conexion);
                JasperViewer.viewReport(jp,false);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
            } 
        }else{
            JOptionPane.showMessageDialog(null, "No autorizado");
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
         if(FormularioVal==false)
        {
            if (idUs==1) 
            {
                this.reportes = new FrmReporteRegistroUsuario();
                this.desktopPane.add(reportes);
                reportes.setVisible(true);
                reportes.setLocation(
                desktopPane.getWidth()/2 - reportes.getWidth()/2,
                desktopPane.getHeight()/2 - reportes.getHeight()/2);
                FormularioVal=true;
            }else{
                JOptionPane.showMessageDialog(null, "No autorizado");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana abierta!");
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void editMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMenu1MouseClicked
      
    }//GEN-LAST:event_editMenu1MouseClicked
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu GestionCoordinador;
    private javax.swing.JMenu GestionHorarios;
    private javax.swing.JMenu GestionarUsuario;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jreport;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu solicitudes;
    // End of variables declaration//GEN-END:variables

}
