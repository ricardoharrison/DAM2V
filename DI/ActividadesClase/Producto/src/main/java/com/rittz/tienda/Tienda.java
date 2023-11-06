/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.rittz.tienda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
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
        jButtonLoad = new javax.swing.JButton();
        jTextFieldPotencia = new javax.swing.JTextField();
        jTextFieldAutonomia = new javax.swing.JTextField();
        jTextFieldPrecio = new javax.swing.JTextField();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldMaletero = new javax.swing.JTextField();
        jLabel2Modelo = new javax.swing.JLabel();
        jLabelAutonomia = new javax.swing.JLabel();
        jLabelPotencia = new javax.swing.JLabel();
        jLabelPrecio = new javax.swing.JLabel();
        jLabelMaletero = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonLoadFromCSV = new javax.swing.JButton();
        jButtonLoadJson = new javax.swing.JButton();
        jButtonLoadFromCSV2 = new javax.swing.JButton();
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
                "ID", "Modelo", "Potencia", "Autonomía", "Precio", "Maletero"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButtonLoad.setText("Cargar BBDD");
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jLabel2Modelo.setText("Modelo");

        jLabelAutonomia.setText("Autonomía");

        jLabelPotencia.setText("Potencia");

        jLabelPrecio.setText("Precio");
        jLabelPrecio.setToolTipText("");

        jLabelMaletero.setText("Maletero");

        jButtonAdd.setText("Añadir Registro");
        jButtonAdd.setToolTipText("");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Borrar Registro");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonLoadFromCSV.setText("Cargar CSV");
        jButtonLoadFromCSV.setActionCommand("");
        jButtonLoadFromCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadFromCSVActionPerformed(evt);
            }
        });

        jButtonLoadJson.setText("Crear JSON");
        jButtonLoadJson.setActionCommand("");
        jButtonLoadJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadJsonActionPerformed(evt);
            }
        });

        jButtonLoadFromCSV2.setText("Crear CSV");
        jButtonLoadFromCSV2.setActionCommand("");
        jButtonLoadFromCSV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadFromCSV2ActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldModelo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2Modelo)
                                .addGap(68, 68, 68)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPotencia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldAutonomia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelAutonomia)
                                .addGap(48, 48, 48)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelPrecio)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldPrecio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMaletero)
                            .addComponent(jTextFieldMaletero)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonLoadJson))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonLoad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLoadFromCSV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLoadFromCSV2)))
                        .addGap(55, 55, 55)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoadJson, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoadFromCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoadFromCSV2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2Modelo)
                    .addComponent(jLabelMaletero)
                    .addComponent(jLabelPotencia)
                    .addComponent(jLabelAutonomia)
                    .addComponent(jLabelPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAutonomia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaletero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        // TODO add your handling code here:
        loadDataBase();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        jTextFieldModelo.setText(jTable1.getValueAt(selectedRow, 1).toString());
        jTextFieldPotencia.setText(jTable1.getValueAt(selectedRow, 2).toString());
        jTextFieldAutonomia.setText(jTable1.getValueAt(selectedRow, 3).toString());
        jTextFieldPrecio.setText(jTable1.getValueAt(selectedRow, 4).toString());
        jTextFieldMaletero.setText(jTable1.getValueAt(selectedRow, 5).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
        addNewRecord();
        loadDataBase();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        deleteRecord();
        loadDataBase();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonLoadFromCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadFromCSVActionPerformed
        // TODO add your handling code here:
            loadFromCsv();
            loadDataBase();
    }//GEN-LAST:event_jButtonLoadFromCSVActionPerformed

    private void jButtonLoadJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadJsonActionPerformed
        // TODO add your handling code here:
        exportToJson();
    }//GEN-LAST:event_jButtonLoadJsonActionPerformed

    private void jButtonLoadFromCSV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadFromCSV2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLoadFromCSV2ActionPerformed

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
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonLoadFromCSV;
    private javax.swing.JButton jButtonLoadFromCSV2;
    private javax.swing.JButton jButtonLoadJson;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2Modelo;
    private javax.swing.JLabel jLabelAutonomia;
    private javax.swing.JLabel jLabelMaletero;
    private javax.swing.JLabel jLabelPotencia;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JMenuBar jMenuBarTienda;
    private javax.swing.JMenuItem jMenuItemProductoSillas;
    private javax.swing.JMenuItem jMenuItemProductoSofas;
    private javax.swing.JMenu jMenuLocalizacion;
    private javax.swing.JMenu jMenuProducto;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAutonomia;
    private javax.swing.JTextField jTextFieldMaletero;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldPotencia;
    private javax.swing.JTextField jTextFieldPrecio;
    // End of variables declaration//GEN-END:variables

    private void loadDataBase() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
      
        try {
            // Establish a database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesla", "root", "");
            //System.out.println("data: " + connection.getMetaData().getDatabaseProductVersion()); //linea para debuggear            
            
            // Define your SQL query to retrieve data
            String query = "SELECT * FROM COCHES";

            // Create a statement
            statement = connection.createStatement();

            // Execute the query
            resultSet = statement.executeQuery(query);

            // Get the DefaultTableModel of jTable1
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            // Clear the existing rows in the table
            model.setRowCount(0);

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
        } catch (SQLException e) {
            // Handle any database errors
            System.out.println("Excepción: " + e.getMessage() + " sql " + e.getSQLState());
           
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
            }
            
        }
    }

    private void addNewRecord() {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesla", "root", "");
            conn.createStatement();
            
            String query = "INSERT into COCHES(modelo, potencia, autonomia, precio, maletero) values (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            
            ps.setString(1, jTextFieldModelo.getText());
            ps.setInt(2, Integer.parseInt(jTextFieldPotencia.getText()));
            ps.setInt(3, Integer.parseInt(jTextFieldAutonomia.getText()));
            ps.setInt(4, Integer.parseInt(jTextFieldPrecio.getText()));
            ps.setInt(5, Integer.parseInt(jTextFieldMaletero.getText()));
            
            int filasInsertadas = ps.executeUpdate();
            
            conn.close();
            
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        
    }
    
    private void deleteRecord(){
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesla", "root", "");
            conn.createStatement();
            
            String query = "DELETE FROM coches where id = ?";            
            ps = conn.prepareStatement(query);            
           
            int idToDelete = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0); // 0 = primera columna
            
            ps.setInt(1, idToDelete);
            
            int rowsAffected = ps.executeUpdate();
            
            conn.close();
           
        } catch (Exception e){}
    }

    private void loadFromCsv() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv"); //para que solo muestre .csv
        fileChooser.setFileFilter(filter);
        
        int option = fileChooser.showOpenDialog(jButtonLoadFromCSV);
        
        File file = fileChooser.getSelectedFile();
        
        ArrayList<Coche> listaCoches = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))){
        String line;
            while((line = reader.readLine()) != null){
                String[] container = line.split(",");
                Coche newCoche = new Coche(container[0], Integer.parseInt(container[1]), 
                        Integer.parseInt(container[2]), Integer.parseInt(container[3]), Integer.parseInt(container[4]));
                listaCoches.add(newCoche);
            }
            
            Connection conn = null;
            PreparedStatement ps = null;

            
            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesla", "root", "");
                conn.createStatement();
                
                String query = "INSERT into COCHES(modelo, potencia, autonomia, precio, maletero) values (?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);
               
                for(Coche coche : listaCoches){
                    ps.setString(1, coche.getModelo());
                    ps.setInt(2, coche.getPotencia());
                    ps.setInt(3, coche.getAutonomia());
                    ps.setInt(4, coche.getPrecio());
                    ps.setInt(5, coche.getMaletero());
                    ps.executeUpdate();
                }  
                
                conn.close();
               
            } catch (SQLException sqle){
                sqle.printStackTrace();
            }
            


        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }    

    private void exportToCsv() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        System.out.println("Working Directory: " + System.getProperty("user.dir")); //ChatGPT at its finest
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesla", "root", "");
            String query = "SELECT * from coches";
            ps = conn.prepareStatement(query);
            ps.executeQuery();
            
            rs = ps.getResultSet();
            
            ArrayList<Coche> listaCoches = new ArrayList<>();
           
            while(rs.next()){             
                Coche coche = new Coche(rs.getString("MODELO"), rs.getInt("POTENCIA"),
                                    rs.getInt("AUTONOMIA"), rs.getInt("PRECIO"),rs.getInt("MALETERO"));
                listaCoches.add(coche);
            }
            
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("salida.txt"))){
                for(Coche coche : listaCoches){
                    String csvLine = coche.toCsvLine();
                    writer.write(csvLine);
                }
            } catch (Exception e){
                e.printStackTrace();
            }           
            
               
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportToJson() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        System.out.println("Working Directory: " + System.getProperty("user.dir")); //ChatGPT at its finest
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesla", "root", "");
            String query = "SELECT * from coches";
            ps = conn.prepareStatement(query);
            ps.executeQuery();
            
            rs = ps.getResultSet();
            
            ArrayList<Coche> listaCoches = new ArrayList<>();
           
            while(rs.next()){             
                Coche coche = new Coche(rs.getString("MODELO"), rs.getInt("POTENCIA"),
                                    rs.getInt("AUTONOMIA"), rs.getInt("PRECIO"),rs.getInt("MALETERO"));
                listaCoches.add(coche);
            }
          /*  
        Gson gson = new Gson();
        String json = gson.toJson(coche);  //imprime JSON de 1 solo objeto
        System.out.println("");
            */
            
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); //imprime el JSON entero
        String json = gson.toJson(listaCoches);
        System.out.println(json);
        
               
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}