
package edu.vistas;

import edu.dao.DaoCarrera;
import edu.dao.DaoCoordinador;
import edu.dao.DaoEscuela;
import edu.dao.DaoUsuario;
import edu.modelo.Carrera;
import edu.modelo.CoordinadorSSE;
import edu.modelo.Escuela;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import edu.utilidades.Validaciones;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.table.TableColumnModel;

/**
 * Nombre de la clase: FrmCoordinador
 * Version: 2.0
 * Fecha:11/08/18
 * CopyRight:SSE-ITCA
 * @author Roxana Menjivar
 */

public class FrmCoordinador extends javax.swing.JInternalFrame {

   DaoCoordinador daoC= new DaoCoordinador();
   CoordinadorSSE cor = new CoordinadorSSE();
   DaoEscuela daoE = new DaoEscuela();
   DaoCarrera daoCa = new DaoCarrera();
   DaoUsuario daoU = new DaoUsuario();
   Validaciones validar = new Validaciones();
   Menu menu = new Menu();
   
   //Instancias para cargar dos combos
   List<Escuela> lista;
   List<Carrera> li;
   Escuela esc = new Escuela();
   Carrera car = new Carrera();
   
    public FrmCoordinador(){
        initComponents();
        mostrar();
        cargarComboE();
        
        this.jTxtCodigo.setEnabled(false);
        this.jTxtUsuario.setEnabled(false);
        this.jTxtNombre.setEnabled(false);
        this.jTxtCorreo.setEnabled(false);
    }
    
     public void tamanioTabla()
    {
        TableColumnModel m=jTablaDatos.getColumnModel();
        m.getColumn(0).setWidth(1);
        m.getColumn(1).setWidth(3);
        m.getColumn(2).setWidth(5);
        m.getColumn(3).setWidth(1);
        m.getColumn(4).setWidth(5);
        m.getColumn(5).setWidth(5);
    }
     
    public Image getIcon() 
    {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("edu.iconos/logoI.png"));

        return retValue;
    }
    
    public boolean validarCorreo(String correo)
    {
        Pattern pat = null;
        Matcher mat = null;
        
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if(mat.find())
        {
            return true;
        }
        else
        {
            return false;
        }
        //String valCor = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@"+"[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
    }
  
    public void cargarComboE()
    {
        lista = daoE.mostrarEscuela();
        
        for(int i=0;i<lista.size();i++)
        {
            esc = new Escuela(lista.get(i).getIdEscuela(),lista.get(i).getNombre());
            this.jCmbEscuela.addItem(esc.getNombre()); 
        } 
    }
    
    //CONTROL DE BARRA DE ACCIONES
    public void OnOFF(int mando)
    {
        //NUEVO
        if (mando == 1 || mando == 2) 
        {
            this.jTxtNombre.setEnabled(true);
            this.jTxtCorreo.setEnabled(true);   
        }

        //limpiar
        if (mando==0) 
        {
            this.jTxtNombre.setEnabled(false);
            this.jTxtCorreo.setEnabled(false);
            this.jBtnAgregar.setEnabled(false);
            this.jBtnLimpiar.setEnabled(false);
            this.jBtnNuevo.setEnabled(true);
            this.jBtnEditar.setEnabled(false);
            this.jBtnEliminar.setEnabled(false);
            this.jTablaDatos.setEnabled(true);
        }
        //nuevo
        if (mando==1) 
        {          
            this.jBtnAgregar.setEnabled(true);
            this.jBtnLimpiar.setEnabled(true);
            this.jBtnNuevo.setEnabled(false);
            this.jBtnEditar.setEnabled(false);
            this.jBtnEliminar.setEnabled(false);
            this.jTablaDatos.setEnabled(false);
            this.jTablaDatos.setCellSelectionEnabled(false);
        }
        //click en tabla
        if (mando==2) 
        {
            this.jBtnAgregar.setEnabled(false);
            this.jBtnLimpiar.setEnabled(true);
            this.jBtnNuevo.setEnabled(false);
            this.jBtnEditar.setEnabled(true);
            this.jBtnEliminar.setEnabled(true);
            this.jTablaDatos.setEnabled(true);
        }   
    }
        
    public void llenarTabla()
    {
        int fila = this.jTablaDatos.getSelectedRow();

        if (fila > -1) 
        {       
            this.jTxtCodigo.setText(String.valueOf(this.jTablaDatos.getValueAt(fila, 0)));
            this.jTxtNombre.setText(String.valueOf(this.jTablaDatos.getValueAt(fila, 1)));
            this.jTxtCorreo.setText(String.valueOf(this.jTablaDatos.getValueAt(fila, 2)));
            this.jTxtUsuario.setText(String.valueOf(this.jTablaDatos.getValueAt(fila, 3)));
            String escuela = String.valueOf(this.jTablaDatos.getValueAt(fila, 4));
            jCmbEscuela.getModel().setSelectedItem(escuela);
            String carrera = String.valueOf(this.jTablaDatos.getValueAt(fila, 5));
            jCmbCarrera.getModel().setSelectedItem(carrera);
        }
        OnOFF(2);
     }
        
    public void mostrar()
    {
        String[] columnas={"Codigo","Nombre","Correo","Usuario","Escuela","Carrera asignada"};
        Object[] obj =new Object[6];
        DefaultTableModel tabla = new DefaultTableModel(null,columnas);
        
        try 
        {
            List lista =daoC.mostrarCoordinador();
            for (int i = 0; i < lista.size(); i++) 
            {
                cor=(CoordinadorSSE)lista.get(i);
                obj[0]=cor.getIdCoordinador();
                obj[1]=cor.getNombre();
                obj[2]=cor.getCorreo();
                String nombreUs = daoU.getNombreUsuario(cor.getIdUsuario());
                obj[3]=nombreUs;
                //obj[3]=daoE.getidEscuela(esc.getIdCombo()).getNombre();                
                //obj[3]=daoE.getnombre(esc.getIdCombo());
                int idcarrera=daoCa.getidCarrera(cor.getIdCarrera()).getIdCarrera();
                obj[4]=daoE.getnombre(idcarrera);
                obj[5]=daoCa.getidCarrera(cor.getIdCarrera()).getNombre();
                
                tabla.addRow(obj);
            }
            this.jTablaDatos.setModel(tabla);
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos en tabla "+e.getMessage(), "Carga Fallida "
                    + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //METODOS INSERTAR
    private void insertar(CoordinadorSSE cor)
    {
        try 
        {      
            daoC.insertarCoordinador(cor);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Ocurrio un error guardando " + e.getMessage(),
                    "Coordinador",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void insertarCoordinador()
    {
        if(validarCorreo(this.jTxtCorreo.getText()))
        {
            cor.setNombre(jTxtNombre.getText());
            cor.setCorreo(jTxtCorreo.getText());
            cor.setIdUsuario(1);

            int idCarrera=0;

                for(int i=0;i<li.size();i++)
                {
                    if(jCmbCarrera.getSelectedItem().equals(li.get(i).getNombre()))
                    {
                         idCarrera = li.get(i).getIdCarrera();
                    }
                }
            cor.setIdCarrera(idCarrera);
            if(!validar.IsNullOrEmpty(String.valueOf(cor.getIdCoordinador()))
                                     && !validar.IsNullOrEmpty(cor.getNombre())
                                     && !validar.IsNullOrEmpty(cor.getCorreo()))
            {
                try 
                {
                    int NomCar = daoC.verificarCarrera(idCarrera);
                    if(NomCar==1)
                    {
                        JOptionPane.showMessageDialog(null, "Ya existe un coordinador asignado a este carrera ","Carrera asignada",
                                JOptionPane.WARNING_MESSAGE);
                        limpiar();
                    }
                    else
                    {
                        insertar(cor);
                        mostrar();
                        limpiar();
                        JOptionPane.showMessageDialog(null, "El registro se ha guardado con exito", "Coordinador",
                                        JOptionPane.INFORMATION_MESSAGE);
                    }     
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al insertar: " + e.getMessage());
                        limpiar();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"¡No dejar campos vacios! ",
                        "Datos Incompletos",
                        JOptionPane.WARNING_MESSAGE); 
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Correo electronico invalido ","Validacion de Email", JOptionPane.ERROR_MESSAGE);
            this.jTxtCorreo.requestFocus();
        }
    }
    
    //METODOS MODIFICAR
    public void modificar()
    {
        try 
        {
            if(validarCorreo(this.jTxtCorreo.getText()))
            { 
                int idCoordinador = Integer.parseInt(jTxtCodigo.getText());
                cor.setNombre(jTxtNombre.getText());
                cor.setCorreo(jTxtCorreo.getText());

                int idCar=0;

                for(int i=0;i<li.size();i++)
                {
                    if(jCmbCarrera.getSelectedItem().equals(li.get(i).getNombre()))
                    {
                         idCar = li.get(i).getIdCarrera();
                    }
                }
                cor.setIdCarrera(idCar);
                cor.setIdCoordinador(idCoordinador);

                if (!validar.IsNullOrEmpty(cor.getNombre())
                        && !validar.IsNullOrEmpty(cor.getCorreo())
                        && !validar.IsNullOrEmpty(String.valueOf(cor.getIdCoordinador()))) 
                {
                    try 
                    {
                        modificarCoordinador(cor);
                        limpiar();
                        mostrar();
                        JOptionPane.showMessageDialog(null, "Coordinador Modificado Exitosamente", "Guardado", 
                        JOptionPane.INFORMATION_MESSAGE);
                        OnOFF(3);
                    } 
                    catch (Exception e) 
                    {
                        JOptionPane.showMessageDialog(null,"Ocurrio un error al modificar " + e.getMessage(),
                                "Error",JOptionPane.ERROR_MESSAGE);
                        limpiar();
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null,"¡No dejar campos vacios! ",
                            "Datos Incompletos",JOptionPane.WARNING_MESSAGE);
                }
            }
            else 
            {
                JOptionPane.showMessageDialog(null,"Correo electronico invalido ","Validacion de Email", JOptionPane.ERROR_MESSAGE);
                this.jTxtCorreo.requestFocus();
            }
        } 
        catch (Exception e) 
        {

        }
    }
    
    private void modificarCoordinador(CoordinadorSSE cor) 
    {
        int SioNo = JOptionPane.showConfirmDialog(this, "¿Desea modificar el registro seleccionado? ", "Modificar Coordinador", 
                JOptionPane.YES_NO_OPTION);

        if (SioNo == 0) 
        {
            daoC.modificarCoordinador(cor);
            limpiar();
            mostrar();
        }
    }
    
    //METODO ELIMINAR
     public void eliminar()
     {
         try 
        {
            int idCoordinador = Integer.parseInt(jTxtCodigo.getText());

            int SioNo = JOptionPane.showConfirmDialog(this, "¿Desea Eliminar el registro seleccionado?", 
                    "Eliminar Coordinador", JOptionPane.YES_NO_OPTION);

            if (SioNo == 0) 
            {
                daoC.eliminarCLogico(idCoordinador);
                JOptionPane.showMessageDialog(null, "Se elimino exitosamente");
                mostrar();
                limpiar();
                OnOFF(4);
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Ocurrio un problema al eliminar el registro",
                    "Error",JOptionPane.ERROR_MESSAGE);
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
       
    private void limpiar() 
    {
        jTxtCodigo.setText("");
        jTxtUsuario.setText("");
        jTxtNombre.setText("");
        jTxtCorreo.setText("");
        jCmbEscuela.setSelectedIndex(0);
        jCmbCarrera.setSelectedIndex(0);
        OnOFF(0);
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTxtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaDatos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCmbEscuela = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTxtNombre = new javax.swing.JTextField();
        jTxtUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtCodigo = new javax.swing.JTextField();
        jCmbCarrera = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnAgregar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jBtnNuevo = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setTitle("Control SSE - Coordinador");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Control de Servicio Social Estudiantil");

        jTxtCorreo.setToolTipText("");
        jTxtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtCorreoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtCorreoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nombre:");

        jTablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaDatos);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Codigo Usuario:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Codigo:");

        jCmbEscuela.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbEscuelaItemStateChanged(evt);
            }
        });
        jCmbEscuela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCmbEscuelaMouseClicked(evt);
            }
        });
        jCmbEscuela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbEscuelaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Correo:");

        jTxtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtNombreKeyTyped(evt);
            }
        });

        jTxtUsuario.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Escuela:");

        jTxtCodigo.setEditable(false);

        jCmbCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCmbCarreraMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Carrera asignada:");

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jBtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/mod.png"))); // NOI18N
        jBtnEditar.setText("Modificar");
        jBtnEditar.setEnabled(false);
        jBtnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnEditarMouseClicked(evt);
            }
        });
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jBtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/delete.png"))); // NOI18N
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnEliminarMouseClicked(evt);
            }
        });

        jBtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/n.png"))); // NOI18N
        jBtnAgregar.setText("Agregar");
        jBtnAgregar.setEnabled(false);
        jBtnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnAgregarMouseClicked(evt);
            }
        });
        jBtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/lim.png"))); // NOI18N
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.setEnabled(false);
        jBtnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnLimpiarMouseClicked(evt);
            }
        });

        jBtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/nue.png"))); // NOI18N
        jBtnNuevo.setText("Nuevo");
        jBtnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnNuevoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(jBtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jBtnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jBtnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/l.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addGap(23, 23, 23)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCmbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTxtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCmbEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(18, 18, 18)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCmbEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCmbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(55, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarActionPerformed
         
    }//GEN-LAST:event_jBtnAgregarActionPerformed

    private void jBtnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnAgregarMouseClicked
        insertarCoordinador();       
    }//GEN-LAST:event_jBtnAgregarMouseClicked

    private void jTxtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNombreKeyTyped
        validar.wordsOnly(evt);
    }//GEN-LAST:event_jTxtNombreKeyTyped

    private void jTablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaDatosMouseClicked
       llenarTabla();
    }//GEN-LAST:event_jTablaDatosMouseClicked

    private void jBtnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnEditarMouseClicked
        modificar();
    }//GEN-LAST:event_jBtnEditarMouseClicked

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLimpiarMouseClicked
        limpiar();
    }//GEN-LAST:event_jBtnLimpiarMouseClicked

    private void jBtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_jBtnEliminarMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Cerrar();
    }//GEN-LAST:event_jButton1MouseClicked
    
    private void jCmbEscuelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCmbEscuelaMouseClicked

    }//GEN-LAST:event_jCmbEscuelaMouseClicked

    private void jCmbCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCmbCarreraMouseClicked

    }//GEN-LAST:event_jCmbCarreraMouseClicked

    private void jCmbEscuelaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbEscuelaItemStateChanged
        this.jCmbCarrera.setModel(new DefaultComboBoxModel<>());
        
        if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            int idEsc=0;
        
            for(int i=0;i<lista.size();i++)
            {
                if(jCmbEscuela.getSelectedItem().equals(lista.get(i).getNombre()))
                {
                     idEsc = lista.get(i).getIdEscuela();
                     li = daoCa.mostrarCarrera(idEsc);
                        for(int j=0;j<li.size();j++)
                        {
                            car = new Carrera(li.get(j).getIdCarrera(),li.get(j).getNombre(),li.get(j).getIdEscuela());
                            this.jCmbCarrera.addItem(car.getNombre());   
                        } 
                }
            }
        }
    }//GEN-LAST:event_jCmbEscuelaItemStateChanged

    private void jCmbEscuelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbEscuelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCmbEscuelaActionPerformed

    private void jBtnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnNuevoMouseClicked
        OnOFF(1);
    }//GEN-LAST:event_jBtnNuevoMouseClicked

    private void jTxtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCorreoKeyTyped
        
    }//GEN-LAST:event_jTxtCorreoKeyTyped

    private void jTxtCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCorreoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCorreoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAgregar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCmbCarrera;
    private javax.swing.JComboBox<String> jCmbEscuela;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaDatos;
    private javax.swing.JTextField jTxtCodigo;
    private javax.swing.JTextField jTxtCorreo;
    private javax.swing.JTextField jTxtNombre;
    private javax.swing.JTextField jTxtUsuario;
    // End of variables declaration//GEN-END:variables
}
