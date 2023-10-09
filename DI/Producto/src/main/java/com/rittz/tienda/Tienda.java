/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.rittz.tienda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author at1DAM2
 */
public class Tienda extends javax.swing.JFrame {

    /**
     * Creates new form Tienda
     */
    public Tienda() {
        initComponents();
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
        jTable1 = new javax.swing.JTable();
        jButtonSubmit = new javax.swing.JButton();
        jMenuBarTienda = new javax.swing.JMenuBar();
        jMenuProducto = new javax.swing.JMenu();
        jMenuItemProductoSofas = new javax.swing.JMenuItem();
        jMenuItemProductoSillas = new javax.swing.JMenuItem();
        jMenuLocalizacion = new javax.swing.JMenu();
        jMenuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tienda feliz");
        jLabel1.setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonSubmit.setText("jButton1");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });

        jMenuProducto.setText("Productos");

        jMenuItemProductoSofas.setText("Sofás");
        jMenuItemProductoSofas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProductoSofasActionPerformed(evt);
            }
        });
        jMenuProducto.add(jMenuItemProductoSofas);

        jMenuItemProductoSillas.setText("Sillas");
        jMenuItemProductoSillas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProductoSillasActionPerformed(evt);
            }
        });
        jMenuProducto.add(jMenuItemProductoSillas);

        jMenuBarTienda.add(jMenuProducto);

        jMenuLocalizacion.setText("Localización");
        jMenuLocalizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuLocalizacionMouseClicked(evt);
            }
        });
        jMenuBarTienda.add(jMenuLocalizacion);

        jMenuSalir.setText("Salir");
        jMenuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSalirMouseClicked(evt);
            }
        });
        jMenuBarTienda.add(jMenuSalir);

        setJMenuBar(jMenuBarTienda);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonSubmit)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(137, 137, 137)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSubmit)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSalirMouseClicked
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres salir?", "Confirma tu selección", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_NO_OPTION){
            dispose();
        }        
    }//GEN-LAST:event_jMenuSalirMouseClicked

    private void jMenuLocalizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuLocalizacionMouseClicked
        // TODO add your handling code here:
        Localizacion loc = new Localizacion();
        loc.setVisible(true);
    }//GEN-LAST:event_jMenuLocalizacionMouseClicked

    private void jMenuItemProductoSofasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProductoSofasActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "¡Nuestros sofas molan!");
    }//GEN-LAST:event_jMenuItemProductoSofasActionPerformed

    private void jMenuItemProductoSillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProductoSillasActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Sillas que te cagas");
    }//GEN-LAST:event_jMenuItemProductoSillasActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesla", "root", "");

            // Define your SQL query to retrieve data
            String query = "SELECT * FROM COCHES";

            // Create a statement
            statement = connection.createStatement();

            // Execute the query
            resultSet = statement.executeQuery(query);

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("MODELO");
            model.addColumn("POTENCIA");
            model.addColumn("AUTONOMIA");
            model.addColumn("PRECIO");
            model.addColumn("MALETERO");

            // Populate the table model with the retrieved data
            while (resultSet.next()) {
                Object[] rowData = {
                    resultSet.getInt("ID"),
                    resultSet.getString("MODELO"),
                    resultSet.getInt("POTENCIA"),
                    resultSet.getInt("AUTONOMIA"),
                    resultSet.getInt("PRECIO"),
                    resultSet.getInt("MALETERO")
                };
                model.addRow(rowData);
            }

            // Set the JTable's model to the populated model
            //JTable miTabla = new JTable(model);

        } catch (SQLException e) {
            // Handle any database errors
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_jButtonSubmitActionPerformed

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
            java.util.logging.Logger.getLogger(Tienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tienda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBarTienda;
    private javax.swing.JMenuItem jMenuItemProductoSillas;
    private javax.swing.JMenuItem jMenuItemProductoSofas;
    private javax.swing.JMenu jMenuLocalizacion;
    private javax.swing.JMenu jMenuProducto;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
