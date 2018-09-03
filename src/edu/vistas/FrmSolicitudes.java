/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vistas;

import edu.dao.DaoEstadoSolicitudSSE;
import edu.dao.DaoSolicitudSSE;
import edu.modelo.EstadoSolicitudSSE;
import edu.modelo.SolicitudSSE;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author reniery
 */
public class FrmSolicitudes extends javax.swing.JInternalFrame {
   
    
    Menu menu = new Menu();
    DaoEstadoSolicitudSSE daoe = new DaoEstadoSolicitudSSE();
    EstadoSolicitudSSE est = new EstadoSolicitudSSE();
    List<EstadoSolicitudSSE> estadosSSE;
    DaoSolicitudSSE daos = new DaoSolicitudSSE();
    SolicitudSSE sol= new SolicitudSSE();
    List<SolicitudSSE> solicitudes;
    List idSolLocal=new ArrayList();
    
    
    
    public FrmSolicitudes() {
        initComponents();
        llenarTabla();
       this.txtMostrar.setEnabled(false);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSolicitudes = new javax.swing.JTable();
        txtMostrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Solicitudes Servicio Social Estudiantil");

        tbSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSolicitudesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSolicitudes);

        txtMostrar.setText("Mostrar");
        txtMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMostrarMouseClicked(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/iconos/exit.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(131, 131, 131))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtMostrar)
                                .addGap(47, 47, 47))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(txtMostrar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        Cerrar();
    }//GEN-LAST:event_jButton2MouseClicked

    private void tbSolicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSolicitudesMouseClicked
       this.txtMostrar.setEnabled(true);
    }//GEN-LAST:event_tbSolicitudesMouseClicked

    private void txtMostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMostrarMouseClicked
        
       int fila = this.tbSolicitudes.getSelectedRow();
       int id = Integer.parseInt(idSolLocal.get(fila).toString());
    }//GEN-LAST:event_txtMostrarMouseClicked

    
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
      
    public void llenarTabla()
    {
        String[] columnas= {"Carnet","Nombre Alumno","Estado SSE","Fecha solicitud"};
        DefaultTableModel tabla = new DefaultTableModel(null,columnas);
        Object[] obj = new Object[4];
        solicitudes = daos.extraerSolicitud();
        estadosSSE = daoe.extraerEstado();
        for(int i=0 ; i<solicitudes.size() ; i++)
        {
            sol= (SolicitudSSE)solicitudes.get(i);
            idSolLocal.add(sol.getIdSolicitud());
            obj[0]=sol.getCarnet();
            obj[1]=sol.getNombreAlumno();
            for(int j=0 ; j<estadosSSE.size() ; j++)
            {
                est = (EstadoSolicitudSSE)estadosSSE.get(i);
                if(sol.getIdEstadoSSE()==est.getIdEstado())
                {
                    obj[2]=est.getDescripcion();
                }
            }            
            obj[3]=sol.getFechaSolicitud();
            tabla.addRow(obj);
        }
        this.tbSolicitudes.setModel(tabla);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbSolicitudes;
    private javax.swing.JButton txtMostrar;
    // End of variables declaration//GEN-END:variables
}
