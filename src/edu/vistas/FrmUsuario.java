
package edu.vistas;

import ds.desktop.notify.DesktopNotify;
import edu.dao.DaoCoordinador;
import edu.dao.DaoRol;
import edu.dao.DaoUsuario;
import edu.modelo.Combo;
import edu.modelo.Rol;
import edu.modelo.Usuario;
import edu.utilidades.Encriptacion;
import edu.utilidades.Validaciones;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase:FrmUsuario
 * Verion:1.0
 * Fecha:25/08/18
 * CopyRight:SSE-ITCA
 * @author Nancy Lopez
 */
public class FrmUsuario extends javax.swing.JInternalFrame {

     DaoUsuario daou=new DaoUsuario();
     DaoCoordinador daoc=new DaoCoordinador();
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
        OnOFF(0);
        this.jtxtId.setEnabled(false);
        this.jtxtcontra2.setEnabled(false); 
        this.jtxtContra.setEnabled(false);
        this.jtxtUsuario3.setEnabled(false);
          
      
    }
    
    //metodos  
     public void cargarCombo()
    {
        lista = daoR.getRol();
        for(int i=0;i<lista.size();i++)
        {
            cmb = new Combo(lista.get(i).getIdRol(),lista.get(i).getNombre());
              
        }    
    }
    
    public void OnOFF(int mando)
    {
        //NUEVO
        if (mando == 1 || mando == 2) 
        {
      
            this.jtxtcontra2.setEnabled(true); 
            this.jtxtContra.setEnabled(true);
            this.jtxtUsuario3.setEnabled(true);
          
        }

        //limpiar
        if (mando==0) 
        {
           this.jtxtcontra2.setEnabled(false); 
            this.jtxtContra.setEnabled(false);
            this.jtxtUsuario3.setEnabled(false);
            //this.jBtnAgregar.setEnabled(false);
            this.jBtnLimpiar.setEnabled(false);
            this.jBtnNuevo.setEnabled(true);
            //this.jBtnEditar.setEnabled(false);
            this.jBtnEliminar.setEnabled(false);
            this.jBtnGuardar.setEnabled(false);
            this.jTableuser.setEnabled(true);
          
        }
        //nuevo
        if (mando==1) 
        {          
            //this.jBtnAgregar.setEnabled(true);
            this.jBtnLimpiar.setEnabled(true);
            this.jBtnNuevo.setEnabled(false);
            //this.jBtnEditar.setEnabled(false);
            this.jBtnEliminar.setEnabled(false);
            this.jBtnGuardar.setEnabled(true);
            this.jTableuser.setEnabled(false);
            this.jTableuser.setCellSelectionEnabled(false);
        }
        //click en tabla
        if (mando==2) 
        {
            //this.jBtnAgregar.setEnabled(false);
            this.jBtnLimpiar.setEnabled(true);
            this.jBtnNuevo.setEnabled(false);
            //this.jBtnEditar.setEnabled(true);
            this.jBtnEliminar.setEnabled(true);
            this.jTableuser.setEnabled(true);
            this.jBtnGuardar.setEnabled(true);
        }   
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
            DesktopNotify.showDesktopMessage("Error al mostrar Usuarios", "",DesktopNotify.FAIL, 3000L);
        }

    }
     
    public void llenarTabla()
    {   
        
        int fila=this.jTableuser.getSelectedRow();
        if (fila>-1) 
        {            
            this.jtxtUsuario3.setText(String.valueOf(this.jTableuser.getValueAt(fila,1)));  
            String rol=String.valueOf(this.jTableuser.getValueAt(fila, 3));
            this.jtxtId.setText(String.valueOf(this.jTableuser.getValueAt(fila,0)));
        }

       

    }

    public void limpiar()
    {

      this.jtxtId.setText("");
      this.jtxtUsuario3.setText("");
      this.jtxtContra.setText("");
      this.jtxtcontra2.setText("");
        OnOFF(0);
    }
   
   public void insertar()
    {
        try 
        {
           java.util.Date date = new java.util.Date();
           java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
           String fecha = sdf.format(date);

            String pass=new String(jtxtContra.getPassword());
            String repitepass = new String(jtxtcontra2.getPassword());
            
            us.setNombre(jtxtUsuario3.getText());  
            us.setFechaRegistro(fecha);
            us.setPass(encrip.encriptar(pass));
            us.setEstado(1);
            us.setFechaModificacion(null);
            us.setFechaEliminacion(null);
            us.setRol(1);
                       
            if(!validar.IsNullOrEmpty(String.valueOf(us.getNombre())) && !validar.IsNullOrEmpty(String.valueOf(pass)))
            {
                if(pass.equals(repitepass))
                { 
                    if(daou.Verificasiexiste(jtxtUsuario3.getText())==0)
                    {                       
                       daou.insertar(us); 
                       mostrarUsuario();
                       limpiar();
                       DesktopNotify.showDesktopMessage("USuario Agregado", "",DesktopNotify.SUCCESS, 5000L);
                    }else
                    {

                     DesktopNotify.showDesktopMessage("Error!", "Nombre de usuario no disponible",DesktopNotify.WARNING, 3000L);
                    }
                }
                else
                {
                  JOptionPane.showMessageDialog(null,"las contraseñas no coinciden");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No enviar campos vacios");
            }

         } 
        catch (HeadlessException e)
        {
            DesktopNotify.showDesktopMessage("Error al insertar Usuarios", "",DesktopNotify.FAIL, 3000L);
        }
    }
   
   public void modificar()
   {
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(date); 
        String pass=new String(jtxtContra.getPassword());
        String repitepass = new String(jtxtcontra2.getPassword());
        us.setNombre(jtxtUsuario3.getText());
        us.setCodigo(Integer.parseInt(jtxtId.getText()));
        us.setFechaModificacion(fecha);
        us.setPass(encrip.encriptar(pass));
        if(!validar.IsNullOrEmpty(String.valueOf(us.getNombre())))
        { 
            if (this.jtxtContra.getPassword().length>6) 
            {
                if(pass.equals(repitepass))
                {                     
                    int pregunta= JOptionPane.showConfirmDialog(null,"Desea modificar el registro","modificar",JOptionPane.YES_NO_OPTION);
                    if(pregunta==0)
                    {
                        daou.Modificar(us);
                        mostrarUsuario();
                        limpiar();
                    }     
                }
                else
                {
                  JOptionPane.showMessageDialog(null,"las contraseñas no coinciden");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Contraseña muy corta, minimo 6 caracteres");
            }
        }else{
        JOptionPane.showMessageDialog(null, "El campo nombre de usuario no puede estar vacio");
        }
    }

    public void eliminar()
   {
       
    java.util.Date date = new java.util.Date();
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(date);
    int iduser=Integer.parseInt(jtxtId.getText());
     us.setCodigo(Integer.parseInt(jtxtId.getText()));
     us.setEstado(2);
     us.setFechaEliminacion(fecha);
     
     int pregunta= JOptionPane.showConfirmDialog(null,"Desea eliminar el registro","eliminar",JOptionPane.YES_NO_OPTION);
     if(pregunta==0)
     {
         int fila = this.jTableuser.getSelectedRow();
         String rol = String.valueOf(this.jTableuser.getValueAt(fila, 3));
         if(!rol.equals("Administrador"))
         {
             int idcor=daoc.idCoor(iduser);
            if (idcor>=0)
                {
                daoc.eliminarCoordinador(idcor);
                daou.eliminarUsu(us);
                mostrarUsuario();
                limpiar();
                }
         }
         else
         {
             daou.eliminarUsu(us);
             mostrarUsuario();
             limpiar();
         }
         
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
        catch (HeadlessException e) 
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

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jBtnEliminar = new javax.swing.JButton();
        jBtnNuevo = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jtxtId = new javax.swing.JTextField();
        jtxtUsuario3 = new javax.swing.JTextField();
        jtxtContra = new javax.swing.JPasswordField();
        jtxtcontra2 = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableuser = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        jBtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/delete.png"))); // NOI18N
        jBtnEliminar.setText("  ELIMINAR");
        jBtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnEliminarMouseClicked(evt);
            }
        });
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/nue.png"))); // NOI18N
        jBtnNuevo.setText("NUEVO");
        jBtnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnNuevoMouseClicked(evt);
            }
        });
        jBtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNuevoActionPerformed(evt);
            }
        });

        jBtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/lim.png"))); // NOI18N
        jBtnLimpiar.setText("LIMPIAR");
        jBtnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnLimpiarMouseClicked(evt);
            }
        });
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/g.png"))); // NOI18N
        jBtnGuardar.setText("GUARDAR");
        jBtnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnGuardarMouseClicked(evt);
            }
        });
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jBtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Codigo:");

        jtxtId.setEnabled(false);

        jtxtUsuario3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtUsuario3KeyTyped(evt);
            }
        });

        jtxtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtContraKeyTyped(evt);
            }
        });

        jtxtcontra2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtcontra2KeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Confirmar contraseña:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Contraseña:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Usuario:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(104, 104, 104))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtcontra2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
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
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtxtContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jtxtcontra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/l.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Control de Servicio Social Estudiantil");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Registro de usuarios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel2)))
                        .addComponent(jScrollPane1)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtUsuario3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtUsuario3KeyTyped
        validar.wordsOnly2(evt);        
    }//GEN-LAST:event_jtxtUsuario3KeyTyped

    private void jBtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnEliminarMouseClicked
        eliminar();
        OnOFF(0);
    }//GEN-LAST:event_jBtnEliminarMouseClicked

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed

    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnNuevoMouseClicked
        OnOFF(1);       
        controlGuardar=1;
    }//GEN-LAST:event_jBtnNuevoMouseClicked

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed

    }//GEN-LAST:event_jBtnNuevoActionPerformed

    private void jBtnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnGuardarMouseClicked
       
        if(controlGuardar==1)
        {           
            this.insertar();
        }
        else
        if(controlGuardar==2)
        {
            this.modificar();
        }
    }//GEN-LAST:event_jBtnGuardarMouseClicked

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLimpiarMouseClicked
      OnOFF(1);
        
        limpiar();
    }//GEN-LAST:event_jBtnLimpiarMouseClicked

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

    private void jTableuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableuserMouseClicked
        OnOFF(2);
       controlGuardar=2;
        llenarTabla();
    }//GEN-LAST:event_jTableuserMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Cerrar();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtxtContraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtContraKeyTyped
            
    }//GEN-LAST:event_jtxtContraKeyTyped

    private void jtxtcontra2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtcontra2KeyTyped
        
    }//GEN-LAST:event_jtxtcontra2KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableuser;
    private javax.swing.JPasswordField jtxtContra;
    private javax.swing.JTextField jtxtId;
    private javax.swing.JTextField jtxtUsuario3;
    private javax.swing.JPasswordField jtxtcontra2;
    // End of variables declaration//GEN-END:variables
}
