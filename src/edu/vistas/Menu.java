
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Nombre de la clase: Menu
 * Version: 1.0
 * Fecha:11/08/18
 * CopyRight:SSE-ITCA
 * @author Roxana Menjivar
 */
public class Menu extends javax.swing.JFrame {

    FrmCoordinador gestionC;
    Notificaciones frmNoti;
    FrmHorario gestionH;
    FrmUsuario gestionU;
    FrmSSSE gestionS;
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
        setIconImage(new ImageIcon(getClass().getResource("addd.png")).getImage());
        this.setExtendedState(MAXIMIZED_BOTH); 
    } 
    
    public void visibilidad(){
        if (idUs==1) {
            
        }
        if (idUs==2) {
            
        }
        if (idUs==3) {
            
        }
        if (idUs==4) {
            
        }
    }
    
    public void correr()
    {        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                ActionListener action = new ActionListener()
                {   
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        timer.stop();                     
                        int numero=noti.getNoti();
                        if (numero>0) 
                        {
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
            this.frmNoti = new Notificaciones();
            this.desktopPane.add(frmNoti);
            frmNoti.setVisible(true);
            frmNoti.setLocation(
            desktopPane.getWidth()/2 - frmNoti.getWidth()/2,
            desktopPane.getHeight()/2 - frmNoti.getHeight()/2);
            FormularioVal=true;
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
    public static int retornarUsuario(int id)
    {
        return idUs;
    }
    

    public static List getLsUs() 
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
            this.gestionC = new FrmCoordinador();
            this.desktopPane.add(gestionC);
            gestionC.setVisible(true);
            gestionC.setLocation(
            desktopPane.getWidth()/2 - gestionC.getWidth()/2,
            desktopPane.getHeight()/2 - gestionC.getHeight()/2);
            FormularioVal=true;
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
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana abierta!");
        }
    }//GEN-LAST:event_GestionHorariosMouseClicked
                                             

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
        Login log=new Login();
        log.setVisible(true);
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
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana abierta!");
        }
    }//GEN-LAST:event_solicitudesMouseClicked

    private void GestionarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GestionarUsuarioMouseClicked
        if(FormularioVal==false)
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
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana abierta!");
        }
    }//GEN-LAST:event_GestionarUsuarioMouseClicked
   
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
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu solicitudes;
    // End of variables declaration//GEN-END:variables

}
