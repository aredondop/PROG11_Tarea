/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.prog11.princ;

import com.prog11.bbdd.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Ángel Redondo
 */
public class ListarPropietarios extends javax.swing.JInternalFrame {

    ConnectionDB con = new ConnectionDB();
    
    public ListarPropietarios() {
        initComponents();
        mostrarDatosPropietarios(0,null);
    }
    
    public void mostrarDatosPropietarios( int opcion, String valor){
        
        String query  = "SELECT p.id_prop, p.nombre_prop, p.dni_prop, (SELECT COUNT(id_prop) FROM vehiculo WHERE id_prop = p.id_prop) AS total FROM propietario p";;
        
        if( opcion == 0 && valor == null)
            query = "SELECT p.id_prop, p.nombre_prop, p.dni_prop, (SELECT COUNT(id_prop) FROM vehiculo WHERE id_prop = p.id_prop) AS total FROM propietario p";
        else{
            if( opcion == 1 && valor != null)
                query = "SELECT p.id_prop, p.nombre_prop, p.dni_prop, (SELECT COUNT(id_prop) FROM vehiculo WHERE id_prop = p.id_prop) AS total FROM propietario p WHERE p.nombre_prop LIKE '%"+valor+"%'";
            
            if( opcion == 2 && valor != null)
                query = "SELECT p.id_prop, p.nombre_prop, p.dni_prop, (SELECT COUNT(id_prop) FROM vehiculo WHERE id_prop = p.id_prop) AS total FROM propietario p WHERE p.dni_prop LIKE '%"+valor+"%'";
            
        }
        DefaultTableModel tPropietarios = new DefaultTableModel();
        tPropietarios.addColumn("#");
        tPropietarios.addColumn("Nombre");
        tPropietarios.addColumn("DNI");
        tPropietarios.addColumn("Vehiculos");
        
        tablaPropietarios.setModel(tPropietarios);
        
        String []datos = new String[4];
        
        try{
            
            con.openConnection();
            
            PreparedStatement statement = con.getConnection().prepareStatement(
                query
            );
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                tPropietarios.addRow(datos); //Agregamos al modelo
            }
            
            tablaPropietarios.setModel(tPropietarios);
            
            con.closeConnection();
            
        }catch ( SQLException e){
            JOptionPane.showMessageDialog(null, e + "Error en la consulta");
        }
    }
    
    public void actualizarPropietario(){
        int row = tablaPropietarios.getSelectedRow();
        
        int id = Integer.parseInt( this.tablaPropietarios.getValueAt(row, 0).toString());
        String nombre =  this.tablaPropietarios.getValueAt(row, 1).toString();
        String dni =  this.tablaPropietarios.getValueAt(row, 2).toString();
        
       
        if( PropietariosDAO.editarPropietario(con, id, nombre, dni) == 0)
            JOptionPane.showMessageDialog(null, "Propietario actualizado con exito");
        else
            JOptionPane.showMessageDialog(null, "Error actualizando el Propietario");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPropietarios = new javax.swing.JTable();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        comboBuscar = new javax.swing.JComboBox<>();
        textoBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        tablaPropietarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaPropietarios);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));

        comboBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar Todos", "Nombre", "DNI" }));
        comboBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBuscarActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(comboBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(textoBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(textoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("Actualizar Propietario");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton2)
                        .addGap(0, 277, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(22, 22, 22)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int opcion = comboBuscar.getSelectedIndex();
        String valor = textoBuscar.getText();
        mostrarDatosPropietarios(opcion,valor);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        actualizarPropietario();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPropietarios;
    private javax.swing.JTextField textoBuscar;
    // End of variables declaration//GEN-END:variables
}
