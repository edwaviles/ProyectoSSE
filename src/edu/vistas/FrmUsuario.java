
package edu.vistas;

import edu.dao.DaoRol;
import edu.dao.DaoUsuario;
import edu.modelo.Combo;
import edu.modelo.Rol;
import edu.modelo.Usuario;
import edu.utilidades.Encriptacion;
import edu.utilidades.Validaciones;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase:FrmUsuario
 * Verion:
 * Fecha:25/08/18
 * CopyRight:SSE-ITCA
 * @author Nancy Lopez
 */
public class FrmUsuario extends javax.swing.JInternalFrame {

     DaoUsuario daou=new DaoUsuario();
     edu.modelo.Usuario us=new edu.modelo.Usuario();
     Validaciones validar=new Validaciones();
     Encriptacion encrip = new Encriptacion();
     int controlGuardar=0;
     DaoRol daoR=new DaoRol();
     Rol rol=new Rol();
     List<Rol> lista;
     Combo cmb = new Combo();
      Menu menu = new Menu();
     
    public FrmUsuario() {
        initComponents();
         mostrarUsuario();
        cargarCombo();
        controlBtn(0);
        controlTxt(false);
       
    }
    
    //metodos
    
     public void cargarCombo()
    {
        lista = daoR.getRol();
        for(int i=0;i<lista.size();i++)
        {
            cmb = new Combo(lista.get(i).getIdRol(),lista.get(i).getNombre());
            this.jcmRol.addItem(cmb.toString());    
        }    
    }
    
      public void controlBtn(int btn)
    {
        if(btn==0)
        {
            this.jbtnEliminar.setEnabled(false);
            this.jbtnModificar.setEnabled(false);
            this.jbtnNuevo.setEnabled(true);
            this.jbtnCancelar.setEnabled(false);
            this.jbtnGuardar.setEnabled(false);
        }
         if(btn==1)
        {
            this.jbtnEliminar.setEnabled(false);
            this.jbtnModificar.setEnabled(false);
            this.jbtnNuevo.setEnabled(false);
            this.jbtnCancelar.setEnabled(true);
            this.jbtnGuardar.setEnabled(true);
        }
          if(btn==2)
        {
            this.jbtnEliminar.setEnabled(true);
            this.jbtnModificar.setEnabled(true);
            this.jbtnNuevo.setEnabled(false);
            this.jbtnCancelar.setEnabled(true);
            this.jbtnGuardar.setEnabled(false);
        }
           if(btn==3)
        {
            this.jbtnEliminar.setEnabled(false);
            this.jbtnModificar.setEnabled(false);
            this.jbtnNuevo.setEnabled(false);
            this.jbtnCancelar.setEnabled(true);
            this.jbtnGuardar.setEnabled(true);
        }           
    }
    
    
    public void controlTxt(boolean x)
    {
        this.jtxtId.setEnabled(false);
       this.jtxtContra.setEnabled(x);
       this.jtxtUsuario3.setEnabled(x);
       this.jtxtcontra2.setEnabled(x); 
       this.jcmRol.setEnabled(x);
    }
   
    
 
    public void mostrarUsuario()
 {
     String[]columna={"codigo","nombre","Fecha Ingreso","Tipo de Usuario"};
     Object[]obj= new Object[4];
     DefaultTableModel tabla = new DefaultTableModel(null,columna);
     try 
       {
         List lista=daou.mostrar();
         for (int i = 0; i < lista.size(); i++) {
             us=(Usuario)lista.get(i);
             obj[0]=us.getCodigo();
             obj[1]=us.getNombre();
             obj[2]=us.getFechaRegistro();
             obj[3]=daoR.getidRol(us.getRol()).getNombre();      
             tabla.addRow(obj);                        
         }
         this.jTableuser.setModel(tabla);
     }
     catch (Exception e)
     {
         JOptionPane.showMessageDialog(null,"Error al mostrar"+e.toString());
     }
     
 }
     
   public void llenarTabla()
   {
     int fila=this.jTableuser.getSelectedRow();
     this.jtxtUsuario3.setText(String.valueOf(this.jTableuser.getValueAt(fila,1)));  
     String rol=String.valueOf(this.jTableuser.getValueAt(fila, 3));
     this.jtxtId.setText(String.valueOf(this.jTableuser.getValueAt(fila,0)));
     jcmRol.getModel().setSelectedItem(rol);   
 
   }
   
   public void limpiar()
   {
    
     this.jtxtId.setText("");
     this.jtxtUsuario3.setText("");
     this.jcmRol.setSelectedItem(""); 
     this.jtxtContra.setText("");
     this.jtxtcontra2.setText("");
   }
   
   public void insertar()
   {
    try {
             
    java.util.Date date = new java.util.Date();
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(date);
    
    int id=0;
    
     for(int i=0;i<lista.size();i++)
     {
      if(jcmRol.getSelectedItem().equals(lista.get(i).getNombre()))
         {
          id = lista.get(i).getIdRol();
         }
     }
    
     String pass=new String(jtxtContra.getPassword());
     String repitepass = new String(jtxtcontra2.getPassword());
    
     if(pass.equals(repitepass))
     { 
         if(daou.Verificasiexiste(jtxtUsuario3.getText())==0)
         {
            us.setNombre(jtxtUsuario3.getText());  
            us.setFechaRegistro(fecha);
            us.setPass(encrip.encriptar(pass));
            us.setEstado(1);
            us.setFechaModificacion(null);
            us.setFechaEliminacion(null);
            us.setRol(id);
            daou.insertar(us); 
            mostrarUsuario();
            limpiar();
            JOptionPane.showMessageDialog(null,"Datos insertados");
         }else
         {
         
          JOptionPane.showMessageDialog(null,"El usuario Ya existe");
         }
     }
     else
     {
       JOptionPane.showMessageDialog(null,"las contraseñas no coinciden");
     }
   
       } 
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null,"Error al insertar" +e.toString());
       }
   }
   
   public void modificar()
   {
           java.util.Date date = new java.util.Date();
           java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
           String fecha = sdf.format(date); 
           String pass=new String(jtxtContra.getPassword());
           String repitepass = new String(jtxtcontra2.getPassword());
           int id=0;
    
           for(int i=0;i<lista.size();i++)
             {
               if(jcmRol.getSelectedItem().equals(lista.get(i).getNombre()))
                 {
                      id = lista.get(i).getIdRol();
                 }
              }
          
         if(pass.equals(repitepass))
              { 
                        us.setNombre(jtxtUsuario3.getText());
                        us.setCodigo(Integer.parseInt(jtxtId.getText()));
                        us.setFechaModificacion(fecha);
                        us.setPass(encrip.encriptar(pass));
                        us.setRol(id);
                        int pregunta= JOptionPane.showConfirmDialog(null,"Desea modificar el registro","modificar",JOptionPane.YES_NO_OPTION);
                        if(pregunta==0)
                         {
                            daou.Modificar(us);
                            mostrarUsuario();
                            limpiar();
                        }     
                      }
                  else{
                        JOptionPane.showMessageDialog(null,"las contraseñas no coinciden");
                      }
                       }
     
   
    public void eliminar()
   {
       
    java.util.Date date = new java.util.Date();
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(date);
    
     us.setCodigo(Integer.parseInt(jtxtId.getText()));
     us.setEstado(2);
     us.setFechaEliminacion(fecha);
     int pregunta= JOptionPane.showConfirmDialog(null,"Desea eliminar el registro","eliminar",JOptionPane.YES_NO_OPTION);
     if(pregunta==0)
     {
       daou.Eliminar(us);
       mostrarUsuario();
       limpiar();
     }
   
   }
         public void Cerrar()
     {
        try 
        {
            int SioNo = JOptionPane.showConfirmDialog(this, "¿Esta seguro de salir del formulario?", 
                    "Advertencia", JOptionPane.YES_NO_OPTION);

            if (SioNo == 0) 
            {
                this.setVisible(false);
                menu.cerrar(); 
            }
        } 
        catch (Exception e) 
        {
            
        }
     }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jbtnModificar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jtxtId = new javax.swing.JTextField();
        jtxtUsuario3 = new javax.swing.JTextField();
        jcmRol = new javax.swing.JComboBox<>();
        jtxtContra = new javax.swing.JPasswordField();
        jtxtcontra2 = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableuser = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/usuario.png"))); // NOI18N
        jLabel1.setText("Registro de Usuario");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        jbtnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/mod.png"))); // NOI18N
        jbtnModificar.setText("   MODIFICAR");
        jbtnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnModificarMouseClicked(evt);
            }
        });
        jbtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarActionPerformed(evt);
            }
        });

        jbtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/delete.png"))); // NOI18N
        jbtnEliminar.setText("  ELIMINAR");
        jbtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnEliminarMouseClicked(evt);
            }
        });
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });

        jbtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/insertar.png"))); // NOI18N
        jbtnNuevo.setText("NUEVO");
        jbtnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnNuevoMouseClicked(evt);
            }
        });
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });

        jbtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/agregar.png"))); // NOI18N
        jbtnGuardar.setText("GUARDAR");
        jbtnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnGuardarMouseClicked(evt);
            }
        });
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        jbtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/modificar.png"))); // NOI18N
        jbtnCancelar.setText("CANCELAR");
        jbtnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnCancelarMouseClicked(evt);
            }
        });
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jbtnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jbtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        jLabel12.setText("CODIGO");

        jtxtId.setEnabled(false);

        jtxtUsuario3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtUsuario3KeyTyped(evt);
            }
        });

        jLabel15.setText("REPITA CONTRASEÑA");

        jLabel14.setText("CONTRASEÑA");

        jLabel13.setText("TIPO DE USUARIO");

        jLabel11.setText("USUARIO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxtUsuario3)
                    .addComponent(jcmRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtContra)
                    .addComponent(jtxtcontra2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtxtUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jcmRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtxtContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jtxtcontra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableuserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableuser);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/exit.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtUsuario3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtUsuario3KeyTyped
        validar.wordsOnly(evt);
    }//GEN-LAST:event_jtxtUsuario3KeyTyped

    private void jbtnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnModificarMouseClicked
        controlBtn(1);
        controlTxt(true);
        controlGuardar=2;
    }//GEN-LAST:event_jbtnModificarMouseClicked

    private void jbtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarActionPerformed

    }//GEN-LAST:event_jbtnModificarActionPerformed

    private void jbtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnEliminarMouseClicked
        eliminar();
        controlBtn(0);
    }//GEN-LAST:event_jbtnEliminarMouseClicked

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed

    }//GEN-LAST:event_jbtnEliminarActionPerformed

    private void jbtnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnNuevoMouseClicked
        controlBtn(1);
        controlTxt(true);
        controlGuardar=1;
    }//GEN-LAST:event_jbtnNuevoMouseClicked

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed

    }//GEN-LAST:event_jbtnNuevoActionPerformed

    private void jbtnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnGuardarMouseClicked
        
        if(controlGuardar==1)
        {
            this.insertar();
        }
        else
        if(controlGuardar==2)
        {
            this.modificar();
        }
    }//GEN-LAST:event_jbtnGuardarMouseClicked

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCancelarMouseClicked
        controlBtn(0);
        controlTxt(false);
        limpiar();
    }//GEN-LAST:event_jbtnCancelarMouseClicked

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jTableuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableuserMouseClicked
        controlBtn(2);
        controlTxt(false);
        llenarTabla();
    }//GEN-LAST:event_jTableuserMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Cerrar();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableuser;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnModificar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JComboBox<String> jcmRol;
    private javax.swing.JPasswordField jtxtContra;
    private javax.swing.JTextField jtxtId;
    private javax.swing.JTextField jtxtUsuario3;
    private javax.swing.JPasswordField jtxtcontra2;
    // End of variables declaration//GEN-END:variables
}
