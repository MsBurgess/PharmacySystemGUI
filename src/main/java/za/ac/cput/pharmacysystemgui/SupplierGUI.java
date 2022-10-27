/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
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
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.SupplierFactory;
import static za.ac.cput.pharmacysystemgui.PrescriptionGUI.JSON;

/**
 *
 * @author Ilyaas Davids
 */
public class SupplierGUI extends javax.swing.JFrame {
    private String suppId,suppName;
    LoginGUI loginGUI = new LoginGUI();
    private static OkHttpClient client = new OkHttpClient();
    ///
        private void Authentication(){

        if(loginGUI.isAdmin() == false){
            
            lblDelete.setEnabled(false);
            btnDeleteSupp.setEnabled(false);
            btnSaveSupp.setEnabled(false);
            editSuppName.setEditable(false);
           
        }

        
    }
    ///
            private void showTable(){
        DefaultTableModel dtModel = (DefaultTableModel) tblSupp.getModel();
        
        List supplierList = getAll();
        List<Supplier> suppliers = supplierList;
        
        dtModel.setRowCount(0);
        
        for(int i = 0; i <supplierList.size(); i++){
            dtModel.addRow(
                    new Object[] {
                        suppliers.get(i).getSuppId(),
                        suppliers.get(i).getSuppName(),      
                    }
            );
        }
    }
      ////      
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
 //////   
      public static List<Supplier> getAll(){
        List<Supplier> supplierList = new ArrayList<>();
        
        try{ 
            final String URL = "http:localhost:8080/PharmacySystem/supplier/all";
            String responseBody = get(URL);
            JSONArray supplier = new JSONArray(responseBody);
            
            for (int i = 0; i < supplier.length();i++){
                JSONObject supplierJSONObject = supplier.getJSONObject(i);
                
                Gson g = new Gson();
                Supplier p = g.fromJson(supplierJSONObject.toString(), Supplier.class);
                supplierList.add(p);
                System.out.println(p.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return supplierList;
    }
    ////
         public void find(String id){
         
        List supplierList = getAll();
        List<Supplier> supplier = supplierList;
       
        
        for(int i = 0; i < supplierList.size(); i++){
            suppId = supplier.get(i).getSuppId();
            suppName = supplier.get(i).getSuppName();
 
            
            if(suppId.equals(id)){
                editSuppName.setText(suppName);
                break;
            }else{
                 JOptionPane.showMessageDialog(null, "Supplier ID "+editSuppId.getText().trim()+" NOT FOUND");
                 break;
            }
            
        }

     }  
    ///
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
         public static void save(String suppId, String suppName){
    
        try{
            
        final String URL = "http:localhost:8080/PharmacySystem/supplier/save";
        Supplier supplier = SupplierFactory.createSupplier(suppId, suppName);
        Gson g = new Gson();
        String jsonString = g.toJson(supplier);
        String r = post(URL, jsonString);
        if(r != null)
            JOptionPane.showMessageDialog(null, "Successfully saved.");
        else
            JOptionPane.showMessageDialog(null, "An error has occurred");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
         ////Delete
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
    
    public static void deleteSupplier(String suppId){
    try{
        final String deleteURL ="http:localhost:8080/PharmacySystem/supplier/delete/" + suppId;
        delete(deleteURL);
        String responseBody = get(deleteURL);
        
        //maybe change this??
         JOptionPane.showMessageDialog(null,"Entry successfully deleted!");
    }catch(IOException e){
        
         JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    
    /**
     * Creates new form SupplierGUI
     */
    public SupplierGUI() {
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

        jLabel4 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel7 = new javax.swing.JPanel();
        lblDelete = new javax.swing.JLabel();
        btnDeleteSupp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupp = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        editSuppName = new javax.swing.JTextField();
        btnSaveSupp = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblSuppContact = new javax.swing.JLabel();
        btnSuppContact = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        SuppId = new javax.swing.JLabel();
        editSuppId = new javax.swing.JTextField();
        btnSuppDisplay = new javax.swing.JButton();
        btnSuppFind = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(219, 194, 173));
        setForeground(java.awt.Color.white);

        jPanel7.setBackground(new java.awt.Color(219, 194, 173));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        lblDelete.setText("Delete Supplier:");
        lblDelete.setName("lblMedDelete"); // NOI18N

        btnDeleteSupp.setText("Delete");
        btnDeleteSupp.setName("btnDeleteMed"); // NOI18N
        btnDeleteSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSuppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDelete))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteSupp)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tblSupp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "supp_ID", "supp_Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSupp);

        jPanel1.setBackground(new java.awt.Color(219, 194, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51)));
        jPanel1.setName("btnMedSave"); // NOI18N

        jLabel1.setText("Supplier Name:");
        jLabel1.setName("lblMedName"); // NOI18N

        editSuppName.setName("editSuppName"); // NOI18N

        btnSaveSupp.setText("Save");
        btnSaveSupp.setName("btnSaveSupp"); // NOI18N
        btnSaveSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSuppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSaveSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editSuppName, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel1)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editSuppName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveSupp)
                .addGap(33, 33, 33))
        );

        jPanel2.setBackground(new java.awt.Color(219, 194, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        lblSuppContact.setText("Supplier Contact:");

        btnSuppContact.setText("Contact");
        btnSuppContact.setName("btnUpdateMed"); // NOI18N
        btnSuppContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuppContactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnSuppContact, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(lblSuppContact)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(lblSuppContact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuppContact)
                .addGap(31, 31, 31))
        );

        btnHome.setText("RETURN HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        SuppId.setText("ID:");

        btnSuppDisplay.setText("Display Suppliers");
        btnSuppDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuppDisplayActionPerformed(evt);
            }
        });

        btnSuppFind.setText("Find");
        btnSuppFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuppFindActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(SuppId)
                .addGap(18, 18, 18)
                .addComponent(editSuppId, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuppFind, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuppDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SuppId)
                    .addComponent(editSuppId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuppDisplay))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnSuppFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuppContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuppContactActionPerformed
        // TODO add your handling code here:
        SupplierContactGUI supplierContact = new SupplierContactGUI();
        supplierContact.show();
        
        dispose();
    }//GEN-LAST:event_btnSuppContactActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        HomeGUI home = new HomeGUI();
        home.show();
        
        dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnSaveSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSuppActionPerformed
        // TODO add your handling code here:
        suppId = editSuppId.getText().trim();
        suppName = editSuppName.getText().trim();
        
         if (suppId.isEmpty()){
           JOptionPane.showMessageDialog(null, "Invalid Supplier ID"); 
        }else if(suppName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Invalid Supplier Name"); 
        }else if(evt.getSource() == btnSaveSupp)
        {
            save(suppId,suppName);
        }
    }//GEN-LAST:event_btnSaveSuppActionPerformed

    private void btnSuppDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuppDisplayActionPerformed
        // TODO add your handling code here:
        showTable();
    }//GEN-LAST:event_btnSuppDisplayActionPerformed

    private void btnDeleteSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSuppActionPerformed
        // TODO add your handling code here:
        suppId = editSuppId.getText().trim();
    if(evt.getSource() == btnDeleteSupp){
        if (suppId.isEmpty()){
           JOptionPane.showMessageDialog(null, "Invalid Supplier ID");  
        }else{deleteSupplier(suppId);}
    }//GEN-LAST:event_btnDeleteSuppActionPerformed
    }
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        editSuppId.setText("");
        editSuppName.setText(""); 
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSuppFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuppFindActionPerformed
        // TODO add your handling code here:
        suppId = editSuppId.getText().trim();
        
        if (suppId.isEmpty()){
        JOptionPane.showMessageDialog(null, "Invalid Supplier ID"); 
        }else{
            find(suppId);
        }
    }//GEN-LAST:event_btnSuppFindActionPerformed

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
            java.util.logging.Logger.getLogger(SupplierGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupplierGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupplierGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupplierGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierGUI().setVisible(true);
            }
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SuppId;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeleteSupp;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnSaveSupp;
    private javax.swing.JButton btnSuppContact;
    private javax.swing.JButton btnSuppDisplay;
    private javax.swing.JButton btnSuppFind;
    private javax.swing.JTextField editSuppId;
    private javax.swing.JTextField editSuppName;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblSuppContact;
    private javax.swing.JTable tblSupp;
    // End of variables declaration//GEN-END:variables
}
