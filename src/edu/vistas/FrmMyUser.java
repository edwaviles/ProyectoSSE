/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vistas;

import edu.dao.DaoUsuario;
import edu.modelo.Usuario;
import edu.utilidades.Encriptacion;
import javax.swing.JOptionPane;

/**
 *
 * @author Ernesto Aviles
 */
public class FrmMyUser extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmMyUser
     */
    Login log=new Login();
    int id=log.idUsuario;
    DaoUsuario daoUs= new DaoUsuario();
    Usuario us=new Usuario();
    Menu men= new Menu();
    Encriptacion en=new Encriptacion();
    public FrmMyUser() {
        initComponents();
        this.txtUsuario.setText(daoUs.getNombreUsuario(id));
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Usuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCambia = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtPass1 = new javax.swing.JPasswordField();
        txtPass2 = new javax.swing.JPasswordField();
        chmod = new javax.swing.JCheckBox();

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/l.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Mi Usuario");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Configuracion de mi usuario");

        Usuario.setText("Usuario");

        jLabel2.setText("Contraseña: ");

        jLabel3.setText("Confirmar Contraseña:");

        btnCambia.setText("Cambiar");
        btnCambia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCambiaMouseClicked(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        txtPass1.setEnabled(false);

        txtPass2.setEnabled(false);

        chmod.setText("Modificar");
        chmod.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chmodItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Usuario)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chmod)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPass1)
                                    .addComponent(txtPass2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCambia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Usuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambia))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chmod))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(btnCancelar)
                    .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
     Cerrar();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCambiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCambiaMouseClicked
        if (this.txtPass1.getText()== this.txtPass2.getText()) 
        {
            modificarMiUser();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
        }
    }//GEN-LAST:event_btnCambiaMouseClicked
 
    
    
    public void Cerrar()
     {
        try 
        {
            int SioNo = JOptionPane.showConfirmDialog(this, "¿Esta seguro de salir del formulario?", 
                    "Advertencia", JOptionPane.YES_NO_OPTION);

            if (SioNo == 0) 
            {
                this.setVisible(false);
                men.cerrar(); 
            }
        } 
        catch (Exception e) 
        {
            
        }
     }
    private void chmodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chmodItemStateChanged
        if (chmod.isSelected()) 
        {
            this.txtPass1.setEnabled(true);
            this.txtPass2.setEnabled(true);
        }else
        {
            this.txtPass1.setEnabled(false);
            this.txtPass2.setEnabled(false);
        }
           
    }//GEN-LAST:event_chmodItemStateChanged
    public void modificarMiUser()
    {
        if(this.chmod.isSelected())
        {
            
        }
            us.setCodigo(id);
            us.setNombre(this.txtUsuario.getText());
            us.setPass(en.encriptar(this.txtPass1.getText()));
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
            String fecha = sdf.format(date); 
            us.setFechaModificacion(fecha);        
            daoUs.Modificar(us);
            this.dispose();
            men.cerrarSession();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Usuario;
    private javax.swing.JButton btnCambia;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chmod;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}