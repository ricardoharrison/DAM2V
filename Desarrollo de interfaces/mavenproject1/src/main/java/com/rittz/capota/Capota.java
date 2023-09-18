/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.rittz.capota;

/**
 *
 * @author Rittz
 */
public class Capota extends javax.swing.JFrame {

    /**
     * Creates new form Capota
     */
    public Capota() {
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

        labelTitle = new javax.swing.JLabel();
        brandLabel = new javax.swing.JLabel();
        labelModel = new javax.swing.JLabel();
        textBrand = new javax.swing.JTextField();
        textModel = new javax.swing.JTextField();
        labelVehicleType = new javax.swing.JLabel();
        labelExtras = new javax.swing.JLabel();
        checkBoxCoche = new javax.swing.JCheckBox();
        checkBoxMoto = new javax.swing.JCheckBox();
        checkBoxCamion = new javax.swing.JCheckBox();
        checkBoxCapota = new javax.swing.JCheckBox();
        checkBoxSidecar = new javax.swing.JCheckBox();
        checkBoxRemolque = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitle.setText("Alquiler de vehículos");
        labelTitle.setName(""); // NOI18N

        brandLabel.setText("Marca:");

        labelModel.setText("Modelo:");

        textBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBrandActionPerformed(evt);
            }
        });

        labelVehicleType.setText("Tipo vehiculo");

        labelExtras.setText("Extras:");

        checkBoxCoche.setText("Coche");
        checkBoxCoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxCocheActionPerformed(evt);
            }
        });

        checkBoxMoto.setText("Moto");
        checkBoxMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxMotoActionPerformed(evt);
            }
        });

        checkBoxCamion.setText("Camión");

        checkBoxCapota.setText("Capota");

        checkBoxSidecar.setText("Sidecar");
        checkBoxSidecar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxSidecarActionPerformed(evt);
            }
        });

        checkBoxRemolque.setText("Remolque");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(labelVehicleType)
                        .addGap(100, 100, 100)
                        .addComponent(labelExtras))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkBoxMoto)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(brandLabel)
                                        .addComponent(labelModel))
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(checkBoxCamion)
                                .addComponent(checkBoxCoche))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkBoxCapota)
                                .addComponent(checkBoxRemolque)
                                .addComponent(checkBoxSidecar)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(labelTitle)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brandLabel)
                    .addComponent(textBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelModel)
                    .addComponent(textModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelVehicleType)
                    .addComponent(labelExtras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxCoche)
                    .addComponent(checkBoxCapota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxMoto)
                    .addComponent(checkBoxSidecar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxCamion)
                    .addComponent(checkBoxRemolque))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxCocheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxCocheActionPerformed

    private void textBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBrandActionPerformed

    private void checkBoxSidecarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxSidecarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxSidecarActionPerformed

    private void checkBoxMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxMotoActionPerformed
        // TODO add your handling code here:
        //checkBoxMoto..se
    }//GEN-LAST:event_checkBoxMotoActionPerformed

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
            java.util.logging.Logger.getLogger(Capota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JCheckBox checkBoxCamion;
    private javax.swing.JCheckBox checkBoxCapota;
    private javax.swing.JCheckBox checkBoxCoche;
    private javax.swing.JCheckBox checkBoxMoto;
    private javax.swing.JCheckBox checkBoxRemolque;
    private javax.swing.JCheckBox checkBoxSidecar;
    private javax.swing.JLabel labelExtras;
    private javax.swing.JLabel labelModel;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelVehicleType;
    private javax.swing.JTextField textBrand;
    private javax.swing.JTextField textModel;
    // End of variables declaration//GEN-END:variables
}
