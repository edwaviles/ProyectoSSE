
package edu.vistas;

import ds.desktop.notify.DesktopNotify;
import edu.dao.DaoCoordinador;
import edu.dao.DaoHorario;
import edu.modelo.CoordinadorSSE;
import edu.modelo.HorarioAtencion;
import edu.modelo.Usuario;
import edu.utilidades.Validaciones;
import static edu.vistas.FrmCoordinador.estado;
import static edu.vistas.Login.idUsuario;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase:FrmHorario
 * Fecha:12/08/18
 * Versión: 1.0
 * CopyRight:SSE-ITCA
 * @author Ernesto Aviles
 */
public class FrmHorario extends javax.swing.JInternalFrame {

    DaoHorario daoH = new DaoHorario();
    HorarioAtencion hor = new HorarioAtencion();
    DaoCoordinador daoC = new DaoCoordinador();
    Validaciones validar = new Validaciones();
    Usuario Us=new Usuario();
    Menu menu = new Menu();
    List lista =daoH.mostrarHorario("Todos");
    
    public static int estado=0;
    
    List<CoordinadorSSE> listaCoord;
    CoordinadorSSE cor = new CoordinadorSSE();
    
    public FrmHorario() {
        initComponents();
        mostrar();
        this.jTxtCodigo.setText(menu.getLsUs().get(1).toString());
        this.lblNombreCor.setText(""+menu.getLsUs().get(0).toString());
        
        this.jCmbDia.setEnabled(false);
        this.jTxtCodigo.setEnabled(false); 
        this.spnHD.setEnabled(false);
        this.spnMD.setEnabled(false);
        this.spnHH.setEnabled(false);
        this.spnMH.setEnabled(false);
        this.jTxtLugar.setEnabled(false);
        this.jTxtCodigoH.setEnabled(false);
    }
    
    //CONTROL DE BARRA DE ACCIONES
    public void OnOFF(int mando)
    {
        //NUEVO
        if (mando == 1 || mando == 2) 
        {
            this.jCmbDia.setEnabled(true);
            this.spnHD.setEnabled(true);
            this.spnMD.setEnabled(true);
            this.spnHH.setEnabled(true);
            this.spnMH.setEnabled(true);
            this.jTxtLugar.setEnabled(true);
        }

        //limpiar
        if (mando==0) 
        {
            this.jCmbDia.setEnabled(false);
            this.spnHD.setEnabled(false);
            this.spnMD.setEnabled(false);
            this.spnHH.setEnabled(false);
            this.spnMH.setEnabled(false);
            this.jTxtLugar.setEnabled(false);
            //this.jBtnAgregar.setEnabled(false);
            this.jBtnLimpiar.setEnabled(false);
            this.jBtnNuevo.setEnabled(true);
            //this.jBtnEditar.setEnabled(false);
            this.jBtnEliminar.setEnabled(false);
            this.jTableDatos.setEnabled(true);
            this.jBtnGuardar.setEnabled(false);
        }
        //nuevo
        if (mando==1) 
        {          
            //this.jBtnAgregar.setEnabled(true);
            this.jBtnLimpiar.setEnabled(true);
            this.jBtnNuevo.setEnabled(false);
            //this.jBtnEditar.setEnabled(false);
            this.jBtnEliminar.setEnabled(false);
            this.jTableDatos.setEnabled(false);
            this.jBtnGuardar.setEnabled(true);
            this.jTableDatos.setCellSelectionEnabled(false);
        }
        //click en tabla
        if (mando==2) 
        {
            //this.jBtnAgregar.setEnabled(false);
            this.jBtnLimpiar.setEnabled(true);
            this.jBtnNuevo.setEnabled(false);
            //this.jBtnEditar.setEnabled(true);
            this.jBtnEliminar.setEnabled(true);
            this.jTableDatos.setEnabled(true);
            this.jBtnGuardar.setEnabled(true);
        }   
    }
    
    public void llenarTabla()
    {
        int fila = this.jTableDatos.getSelectedRow();

        if (fila > -1) 
        {
            this.jTxtCodigoH.setText(String.valueOf(this.jTableDatos.getValueAt(fila, 0)));
            String coord = String.valueOf(this.jTableDatos.getValueAt(fila, 1));
            String dia = String.valueOf(this.jTableDatos.getValueAt(fila, 2));
            this.jCmbDia.getModel().setSelectedItem(dia);
            
            String horaCOmpleta=this.jTableDatos.getValueAt(fila, 3).toString();
            boolean it =false; 
            String hora="";
            String minutos="";
            for (int i = 0; i < horaCOmpleta.length(); i++) 
            {                              
                char letra=horaCOmpleta.charAt(i);
                if (letra==':') 
                {
                    it=true;
                }
                if (letra!=':') 
                {
                    if (it==false) 
                    {
                        hora+=letra;
                    }else
                    {
                        minutos+=letra;
                    }
                }
            }            
            this.spnHD.setValue(Integer.parseInt(hora));
            this.spnMD.setValue(Integer.parseInt(minutos));
            
            String HorasMinDeHasta=this.jTableDatos.getValueAt(fila, 4).toString();
            boolean it2 =false; 
            String hora2="";
            String minutos2="";
            for (int i = 0; i < HorasMinDeHasta.length(); i++) 
            {                              
                char letra2=HorasMinDeHasta.charAt(i);
                if (letra2==':') 
                {
                    it2=true;
                }
                if (letra2!=':') 
                {
                    if (it2==false) 
                    {
                        hora2+=letra2;
                    }else
                    {
                        minutos2+=letra2;
                    }
                }
            }
            spnHH.setValue(Integer.parseInt(hora2));
            spnMH.setValue(Integer.parseInt(minutos2));            
            this.jTxtLugar.setText(String.valueOf(this.jTableDatos.getValueAt(fila, 5)));
        }
        OnOFF(2);
     }
    
    public void mostrar()
    {
        String[] columnas={"Codigo","Coordinador","Dia","Hora desde","Hora hasta","lugar"};
        Object[] obj =new Object[6];
        DefaultTableModel tabla = new DefaultTableModel(null,columnas);
        lista =daoH.mostrarHorario(this.jCmbDia1.getSelectedItem().toString());
        try 
        {    
            
            for (int i = 0; i < lista.size(); i++) 
            {
                hor=(HorarioAtencion)lista.get(i);
                obj[0]=hor.getIdHorarioA();
                obj[1]=daoC.getIdCoordinador(hor.getIdCoordinador()).getNombre();
                obj[2]=hor.getDia();
                if (hor.getMinutosDesde()<10) 
                {
                    obj[3]=hor.getHoraDesde()+":0"+hor.getMinutosDesde();
                }else{
                    obj[3]=hor.getHoraDesde()+":"+hor.getMinutosDesde();
                }
                if (hor.getMinutosHasta()<10) 
                {
                    obj[4]=hor.getHoraHasta()+":0"+hor.getMinutosHasta();
                }else{
                    obj[4]=hor.getHoraHasta()+":"+hor.getMinutosHasta();
                }
                obj[5]=hor.getLugar();
                tabla.addRow(obj);
            }
            this.jTableDatos.setModel(tabla);
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos en la tabla ", "Carga Fallida "
                    + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public void insertar()
    {
        try 
        {
            if (!validarHoras(0)) 
            {
                hor=new HorarioAtencion();
                hor.setDia(this.jCmbDia.getSelectedItem().toString());
                hor.setHoraDesde(Integer.parseInt(this.spnHD.getValue().toString()));
                hor.setMinutosDesde(Integer.parseInt(this.spnMD.getValue().toString()));
                hor.setHoraHasta(Integer.parseInt(this.spnHH.getValue().toString()));
                hor.setMinutosHasta(Integer.parseInt(this.spnMH.getValue().toString()));
                hor.setLugar(this.jTxtLugar.getText());
                hor.setFechaRegistro(validar.CapturarFecha());

                if (!validar.IsNullOrEmpty(String.valueOf(hor.getIdCoordinador()))
                           && !validar.IsNullOrEmpty(hor.getDia())
                           && !validar.IsNullOrEmpty(hor.getLugar())) 
                   {
                       try 
                       {
                           daoH.insertar(hor);
                           limpiar();                        
                       } 
                       catch (Exception e) 
                       {                           
                           JOptionPane.showMessageDialog(null,"Ocurrio un problema al insertar: " + e.getMessage());
                           limpiar();
                       }
                   } 
                   else 
                   {
                       JOptionPane.showMessageDialog(null,"¡No dejar campos vacios! ","Datos Incompletos",JOptionPane.WARNING_MESSAGE);                       
                   } 
            }
        }
        catch (HeadlessException | NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null, "Error validando horas, "+e.getMessage());
        }
        finally
        {
            mostrar();
        }
    }
    
    public void modificar()
    {
        try
            {                
                int idHorario = Integer.parseInt(this.jTxtCodigoH.getText());

                hor.setDia(this.jCmbDia.getSelectedItem().toString());
                hor.setHoraDesde(Integer.parseInt(this.spnHD.getValue().toString()));
                hor.setMinutosDesde(Integer.parseInt(this.spnMD.getValue().toString()));
                hor.setHoraHasta(Integer.parseInt(this.spnHH.getValue().toString()));
                hor.setMinutosHasta(Integer.parseInt(this.spnMH.getValue().toString()));
                hor.setLugar(this.jTxtLugar.getText());
                hor.setFechaModificacion(validar.CapturarFecha());
                hor.setIdHorarioA(idHorario);

                if(!validar.IsNullOrEmpty(hor.getDia()) 
                        && !validar.IsNullOrEmpty(hor.getLugar()))
                {
                    try 
                    {
                        int SioNo=JOptionPane.showConfirmDialog(null,"¿Desea modificar el registro?","Modificar horario",
                        JOptionPane.INFORMATION_MESSAGE);
                        if(SioNo==0)
                        {
                            daoH.modificar(hor);
                            JOptionPane.showMessageDialog(null,"Registro modificado correctamente",
                                    "Horario Coordinador", JOptionPane.INFORMATION_MESSAGE);
                            mostrar();
                            limpiar();
                            OnOFF(3);
                        }
                    }
                    catch (HeadlessException e) 
                    {
                        JOptionPane.showMessageDialog(null,"Ocurrio un problema al modificar " + e.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        limpiar();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"¡No dejar campos vacios! ",
                                "Datos Incompletos",JOptionPane.WARNING_MESSAGE);
                }

        }
        catch (HeadlessException | NumberFormatException e) 
        {
           JOptionPane.showMessageDialog(null,"Por favor, seleccione un registro para modificar","Seleccionar",
                   JOptionPane.INFORMATION_MESSAGE);
        }
    }
  
    public void eliminar()
    {
         try 
        {
           int idHora = Integer.parseInt(this.jTxtCodigoH.getText());
           hor.setIdHorarioA(idHora);
           hor.setFechaEliminacion(validar.CapturarFecha());
           
            int SioNo = JOptionPane.showConfirmDialog(this, "¿Desea Eliminar definitivamente el registro?", "Eliminar Horario",
                    JOptionPane.YES_NO_OPTION);

            if (SioNo == 0) 
            {
                daoH.eliminar(hor);
                JOptionPane.showMessageDialog(null, "Se ha eliminado Exitosamente");
                mostrar();
                limpiar();
                OnOFF(4);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione un registro para poder eliminarlo",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
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
        jTxtCodigoH.setText("");        
        spnHD.setValue(8);
        spnMD.setValue(0);
        spnHH.setValue(8);
        spnMH.setValue(0);
        jTxtLugar.setText("");
        jCmbDia.setSelectedIndex(0);
        OnOFF(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jBtnEliminar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jBtnNuevo = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblNombreCor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCmbDia1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTxtCodigoH = new javax.swing.JTextField();
        spnMD = new javax.swing.JSpinner();
        spnHD = new javax.swing.JSpinner();
        spnHH = new javax.swing.JSpinner();
        spnMH = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCmbDia = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTxtCodigo = new javax.swing.JTextField();
        jTxtLugar = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatos = new javax.swing.JTable();

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Coordinador:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Control de Servicio Social Estudiantil");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/l.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jBtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/delete.png"))); // NOI18N
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnEliminarMouseEntered(evt);
            }
        });
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
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

        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/g.png"))); // NOI18N
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.setEnabled(false);
        jBtnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnGuardarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jBtnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

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

        lblNombreCor.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Ver dia: ");

        jCmbDia1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" }));
        jCmbDia1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCmbDia1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbDia1ItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel11.setText("HORARIOS");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Codigo Horario:");

        jTxtCodigoH.setEditable(false);
        jTxtCodigoH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCodigoHActionPerformed(evt);
            }
        });

        spnMD.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 10));

        spnHD.setModel(new javax.swing.SpinnerNumberModel(8, 8, 12, 1));

        spnHH.setModel(new javax.swing.SpinnerNumberModel(8, 8, 12, 1));

        spnMH.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 10));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Dia:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Lugar:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Codigo Coordinador:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Hora hasta:");

        jCmbDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" }));
        jCmbDia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Hora desde:");

        jTxtCodigo.setEditable(false);
        jTxtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCodigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTxtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTxtCodigoH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnMD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtCodigoH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxtCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCmbDia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spnHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnMD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spnHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(spnMH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDatos);

        jTabbedPane1.addTab("Lista de horarios", jScrollPane1);

        lblNombreCor.setText("jLabel1");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Codigo Horario:");

        jTxtCodigoH.setEditable(false);
        jTxtCodigoH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCodigoHActionPerformed(evt);
            }
        });

        spnMD.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 10));

        spnHD.setModel(new javax.swing.SpinnerNumberModel(8, 8, 12, 1));

        spnHH.setModel(new javax.swing.SpinnerNumberModel(8, 8, 12, 1));

        spnMH.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Ver dia: ");

        jCmbDia1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" }));
        jCmbDia1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCmbDia1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbDia1ItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel11.setText("HORARIOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(jCmbDia1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(lblNombreCor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblNombreCor)))
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel1))
                            .addComponent(jCmbDia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDatosMouseClicked
        llenarTabla();
        estado=2;
    }//GEN-LAST:event_jTableDatosMouseClicked

    private void jBtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_jBtnEliminarMouseClicked

    private void jBtnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLimpiarMouseClicked
        limpiar();
    }//GEN-LAST:event_jBtnLimpiarMouseClicked

    private void jBtnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnNuevoMouseClicked
        OnOFF(1);
        estado=1;
    }//GEN-LAST:event_jBtnNuevoMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Cerrar();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTxtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCodigoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTxtCodigoHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCodigoHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCodigoHActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnEliminarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEliminarMouseEntered

    private void jCmbDia1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbDia1ItemStateChanged
        mostrar();
    }//GEN-LAST:event_jCmbDia1ItemStateChanged

    private void jBtnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnGuardarMouseClicked
        guardar();
    }//GEN-LAST:event_jBtnGuardarMouseClicked
  
    private void guardar()
    {
        if(estado==1)
        {
            insertar();
        }
        else if(estado==2)
        {            
            if (!validarHoras(1)) 
            {                
                modificar();             
            }else       
            if (!verificarJHoras(Integer.parseInt(this.spnHD.getValue().toString()), 
                    Integer.parseInt(this.spnMD.getValue().toString()), 
                    Integer.parseInt(this.spnHH.getValue().toString()), 
                    Integer.parseInt(this.spnMH.getValue().toString()))) 
            {
                int fila = this.jTableDatos.getSelectedRow();
                int cod=Integer.parseInt(String.valueOf(this.jTableDatos.getValueAt(fila,0)));
                if (fila > -1) 
                {
                cod=Integer.parseInt(String.valueOf(this.jTableDatos.getValueAt(fila, 0)));
                }
                if (verificarHMBD2(Integer.parseInt(this.spnHH.getValue().toString()), Integer.parseInt(this.spnMH.getValue().toString()),cod))
                {
                    modificar();
                }else
                {
                    JOptionPane.showMessageDialog(null, "ya ocupado HASTA");
                }
            }
        }
        else
        {
            
        }
    }
    

    public boolean validarHoras(int accion)
    {
        //verifica si entra en el rango con los horarios de los jSpiner     
        boolean res=false;
        //veifica si concuerdan ambas horas desde
        boolean res2=false;
        //verifica si concuerdan horas hasta
        boolean res3=false;
       
        int horaDesde=Integer.parseInt(spnHD.getValue().toString());
        int minDesde=Integer.parseInt(spnMD.getValue().toString());
        int horaHasta=Integer.parseInt(spnHH.getValue().toString());
        int minHasta=Integer.parseInt(spnMH.getValue().toString()); 
        
        res=verificarJHoras(horaDesde,minDesde,horaHasta,minHasta);
        res2=verificarHMBD(horaDesde, minDesde);
        res3=verificarHMBD(horaHasta, minHasta);        
        
        MsjVerificacionesHoras(res,res2,res3,accion);
        if (res || res2 || res3) 
        {          
            return true;
        }else
        {
            return false;
        }
    }
    public boolean verificarJHoras(int horaDesde,int minDesde,int horaHasta,int minHasta)
    {
      boolean res=false;
      
      if (horaDesde<=horaHasta) 
        {
            if (horaDesde==horaHasta) 
            {
                if (horaDesde==horaHasta) 
                {
                    if (minDesde>=minHasta) 
                    {
                        res=true;
                    }                                                        
                }
            }            
        }else
        {
            res=true;
        }  
      return res;
    }    
    public boolean verificarHMBD(int hora, int min)
    {
        boolean res=false;
        lista=daoH.mostrarHorario("Todos");
        if (lista.size()>0) 
        {
            for (int i = 0; i < lista.size(); i++) 
            {
                hor=(HorarioAtencion)lista.get(i);
                if (hor.getDia().equals(jCmbDia.getSelectedItem())) 
                {                    
                    if (hora<=hor.getHoraDesde() || hora>=hor.getHoraHasta() || hora>=hor.getHoraDesde()|| hora<=hor.getHoraHasta()) 
                    {
                        if (hora==hor.getHoraDesde()) 
                        {
                           if (min>=hor.getMinutosDesde())
                            {
                                res=true;
                            } 
                        }
                        if (hora==hor.getHoraHasta()) 
                        {
                           if (min<=hor.getMinutosHasta())
                            {
                                res=true;
                            } 
                        }
                    }                   
                }
            }
        }
       
        return  res;
    }    
    
       public boolean verificarHMBD2(int hora, int min, int id)
    {
        boolean res=false;
        int fila = this.jTableDatos.getSelectedRow();
        String dia="";
        if (fila > -1) 
        {
            dia = String.valueOf(this.jTableDatos.getValueAt(fila, 2));
        }
        lista=daoH.MostrarParaEditarHorario(dia, id);
        JOptionPane.showMessageDialog(null, lista.size());
        if (lista.size()>0) 
        {
            for (int i = 0; i < lista.size(); i++) 
            {
                hor=(HorarioAtencion)lista.get(i); 
                if (hora<=hor.getHoraDesde() || hora>=hor.getHoraHasta()) 
                {   
                    if (hora==hor.getHoraDesde()) 
                    {
                       if (min<hor.getMinutosDesde())
                        {
                            res=true;
                        } 
                    }else
                    if (hora==hor.getHoraHasta()) 
                    {
                       if (min>hor.getMinutosHasta())
                        {
                            res=true;
                        } 
                    }
                }                   
                
            }
        }else{
            res=true;
        }
       
        return  res;
    }
    public void MsjVerificacionesHoras(boolean  res,boolean  res2,boolean  res3, int a)
    {        
        if (res2==true && res==true && res3==true ) 
          {
              JOptionPane.showMessageDialog(null, "Las horas no son logicas");      
          }else
          {
              if (res==true && a!=1) 
              {
                  JOptionPane.showMessageDialog(null, "Las hora de incio debe ser meno a la hora final"); 
              }else
              {
                if (res2==true || res3==true ) 
                {
                    if (a!=1) 
                    {
                        JOptionPane.showMessageDialog(null, "Ya tiene un horario dentro del rango selecionado");
                    }
                    
                }  
              }
          }
 
    }
      //compara elemento de tabla seleecionado con elemento de los Jspiner
    public boolean CompararALModificar()
    {
        boolean SioNo=false;
        int horaDesde=Integer.parseInt(this.spnHD.getValue().toString());
        int minDesde=Integer.parseInt(this.spnMD.getValue().toString());
        int horaHasta=Integer.parseInt(this.spnHH.getValue().toString());
        int minHasta=Integer.parseInt(this.spnMH.getValue().toString());        
        int fila=this.jTableDatos.getSelectedRow();       
        String dia=this.jTableDatos.getValueAt(fila, 2).toString();
        String horaCOmpleta=this.jTableDatos.getValueAt(fila, 3).toString();
        String HorasMinDeHasta=this.jTableDatos.getValueAt(fila, 4).toString();
        
           boolean it =false; 
           String hora="";
           String minutos="";
           for (int i = 0; i < horaCOmpleta.length(); i++) 
           {                              
               char letra=horaCOmpleta.charAt(i);
               if (letra==':') 
               {
                   it=true;
               }
               if (letra!=':') 
               {
                   if (it==false) 
                   {
                       hora+=letra;
                   }else
                   {
                       minutos+=letra;
                   }
               }
            }
            boolean it2 =false; 
            String hora2="";
            String minutos2="";
            for (int i = 0; i < HorasMinDeHasta.length(); i++) 
            {                              
                char letra2=HorasMinDeHasta.charAt(i);
                if (letra2==':') 
                {
                    it2=true;
                }
                if (letra2!=':') 
                {
                    if (it2==false) 
                    {
                        hora2+=letra2;
                    }else
                    {
                        minutos2+=letra2;
                    }
                }
            }
            if (dia.equals(this.jCmbDia.getSelectedItem())) 
            {      
                JOptionPane.showMessageDialog(null, "dias =");
                if (horaDesde>=Integer.parseInt(hora) && horaHasta<=Integer.parseInt(hora2)) 
                {
                    if (horaDesde==Integer.parseInt(hora)) 
                    {
                        if (minDesde>=Integer.parseInt(minutos)) 
                        {
                           SioNo=true; 
                        }
                    }
                     if (horaHasta==Integer.parseInt(hora2)) 
                    {
                        if (minHasta<=Integer.parseInt(minutos2)) 
                        {
                           SioNo=true; 
                        }
                    }               
                }
            }else{
                //acaaas
            }
           return SioNo;
        
    }

    
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCmbDia;
    private javax.swing.JComboBox<String> jCmbDia1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableDatos;
    private javax.swing.JTextField jTxtCodigo;
    private javax.swing.JTextField jTxtCodigoH;
    private javax.swing.JTextField jTxtLugar;
    private javax.swing.JLabel lblNombreCor;
    private javax.swing.JSpinner spnHD;
    private javax.swing.JSpinner spnHH;
    private javax.swing.JSpinner spnMD;
    private javax.swing.JSpinner spnMH;
    // End of variables declaration//GEN-END:variables
}
