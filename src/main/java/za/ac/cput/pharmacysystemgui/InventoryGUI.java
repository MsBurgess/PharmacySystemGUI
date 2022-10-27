/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.pharmacysystemgui;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.PharmacyContact;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.PharmacyContactFactory;
import static za.ac.cput.pharmacysystemgui.PharmacyContactGUI.getAll;
import static za.ac.cput.pharmacysystemgui.PharmacyContactGUI.save;
import static za.ac.cput.pharmacysystemgui.PharmacyGUI.deletePharmacy;
import static za.ac.cput.pharmacysystemgui.PrescriptionGUI.JSON;

/**
 *
 * @author WaseemD
 */
public class InventoryGUI extends javax.swing.JFrame {
    
     
        LoginGUI loginGUI = new LoginGUI() ;
    

    
    private static OkHttpClient client = new OkHttpClient();
    

    
    private void Authentication(){

        if(loginGUI.isAdmin() == false){
            
            txtInventoryId.setEnabled(false);
            txtTabletStockAmount.setEnabled(false);
            txtMedicineStockAmount.setEnabled(false);
          
           
            btnAddPhar.setEnabled(false);
            
            txtDeleteInventoryId.setEnabled(false);
            btnDeleteInventoryId.setEnabled(false);  
        }

    
    }
    
     private void showTable(){
        DefaultTableModel dtModel = (DefaultTableModel) TableInventoryView.getModel();
        
        List inventoryList = getAll();
        List<Inventory>inventory = inventoryList;
        
        dtModel.setRowCount(0);
        
        for(int i = 0; i <inventoryList.size(); i++){
            dtModel.addRow(
                    new Object[] {
                        inventory.get(i).getInventoryID(),
                        inventory.get(i).getTableStockAmount(),
                       inventory.get(i).getMedicineStockAmount()
                          
                    }
            );
        }
    }
     
      //post/save
    private static String post(final String postURL, String json) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(postURL)
                .header("Authorization",  Credentials.basic("Admin", "12345"))
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute())
        {
            return response.body().string();
        }
    }
    
     public static void save(String inventoryId, String tabletStockAmount, String medicineStockAmount){
    
        try{
            
        final String URL = "http:localhost:8080/PharmacySystem/inventory/save";
        Inventory inventory = InventoryFactory.createInventory(inventoryId, tabletStockAmount, medicineStockAmount);
        Gson g = new Gson();
        String jsonString = g.toJson(inventory);
        String r = post(URL, jsonString);
        if(r != null)
            JOptionPane.showMessageDialog(null, "Successfully saved.");
        else
            JOptionPane.showMessageDialog(null, "An error has occurred");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
     
     //delete
    private static String delete  (final String deleteURL) throws IOException{
        Request request = new Request.Builder()
                .url(deleteURL)
                .header("Authorization",  Credentials.basic("Admin", "12345"))
                .delete()
                .build();
        
        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }
    
    public static void deleteInventory(String inventoryId){
    try{
        final String deleteURL ="http:localhost:8080/PharmacySystem/inventory/delete/" + inventoryId;
        delete(deleteURL);
        String responseBody = get(deleteURL);
        
        //maybe change this??
         JOptionPane.showMessageDialog(null,"Entry successfully deleted!");
    }catch(IOException e){
        
         JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    
    //GET ALL
      private static  String get(String getURL) throws IOException
    {
        Request request = new Request.Builder()
                .url(getURL)
                .header("Authorization",  Credentials.basic("User", "54321"))
                .build();
        try (Response response = client.newCall(request).execute())
        {
            return response.body().string();
        }
    }
      
      public static List<Inventory> getAll(){
        List<Inventory>inventoryList = new ArrayList<>();
        
        try{ 
            final String URL = "http:localhost:8080/PharmacySystem/inventory/all";
            String responseBody = get(URL);
            JSONArray inventorys = new JSONArray(responseBody);
            
            for (int i = 0; i < inventorys.length();i++){
                JSONObject inventory = inventorys.getJSONObject(i);
                
                Gson g = new Gson();
                Inventory p = g.fromJson(inventory.toString(), Inventory.class);
                inventoryList.add(p);
                System.out.println(p.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return inventoryList;
    }
      
      

    /**
     * Creates new form InventoryGUI
     */
    public InventoryGUI() {
        initComponents();
        
        Authentication();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableInventoryView = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtInventoryId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTabletStockAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMedicineStockAmount = new javax.swing.JTextField();
        btnAddPhar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtDeleteInventoryId = new javax.swing.JTextField();
        btnDeleteInventoryId = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TableInventoryView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "InventoryId", "Tablet Stock Amount", "Medicine Stock Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableInventoryView);

        jTabbedPane1.addTab("TABLE", jScrollPane1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("InventoryId");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tablet Stock Amount");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Medicine Stock Amount");

        btnAddPhar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAddPhar.setText("ADD PHARMACY");
        btnAddPhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddPhar, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(txtInventoryId)
                    .addComponent(txtTabletStockAmount)
                    .addComponent(txtMedicineStockAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtInventoryId, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTabletStockAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMedicineStockAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btnAddPhar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("ADD", jPanel1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("InventoryId");

        btnDeleteInventoryId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDeleteInventoryId.setText("DELETE");
        btnDeleteInventoryId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteInventoryIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(txtDeleteInventoryId, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnDeleteInventoryId, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDeleteInventoryId, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnDeleteInventoryId, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        jTabbedPane1.addTab("DELETE", jPanel2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Inventory Database");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("VIEW/UPDATE TABLE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("HOME");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("EXIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         showTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        HomeGUI home=new HomeGUI();
        home.show();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnAddPharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPharActionPerformed
        // TODO add your handling code here:
        if(evt.getSource()== btnAddPhar){
        
        save(txtInventoryId.getText().trim(),txtTabletStockAmount.getText().trim(),txtMedicineStockAmount.getText().trim());
        }
    }//GEN-LAST:event_btnAddPharActionPerformed

    private void btnDeleteInventoryIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteInventoryIdActionPerformed
        // TODO add your handling code here:
         if(evt.getSource()==btnDeleteInventoryId)
        {deletePharmacy(txtDeleteInventoryId.getText().trim());}
    }//GEN-LAST:event_btnDeleteInventoryIdActionPerformed

    private void GetAllActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        showTable();
         //getAll();
    }                                      

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
            System.exit(0);        // TODO add your handling code here:
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        HomeGUI home = new HomeGUI();
        home.show();
        
        dispose(); //closes current frame
    }                                        

    private void PostBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        if(evt.getSource() == btnAddPhar)
        {
            
            //fix
            save(txtInventoryId.getText().trim(), txtTabletStockAmount.getText().toString().trim(), txtMedicineStockAmount.getText().trim());
        }
    }                                       

    private void txtPharmNameActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void btnDeletePharmActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        if(evt.getSource() == btnDeleteInventoryId){
            deletePharmacy(txtDeleteInventoryId.getText().trim());
        }
    }       
    
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
            java.util.logging.Logger.getLogger(PharmacyContactGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PharmacyContactGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PharmacyContactGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PharmacyContactGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PharmacyContactGUI().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableInventoryView;
    private javax.swing.JButton btnAddPhar;
    private javax.swing.JButton btnDeleteInventoryId;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtDeleteInventoryId;
    private javax.swing.JTextField txtInventoryId;
    private javax.swing.JTextField txtMedicineStockAmount;
    private javax.swing.JTextField txtTabletStockAmount;
    // End of variables declaration//GEN-END:variables
}
