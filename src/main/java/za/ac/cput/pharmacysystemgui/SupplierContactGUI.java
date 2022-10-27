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
import za.ac.cput.domain.SupplierContact;
import za.ac.cput.factory.SupplierContactFactory;
import static za.ac.cput.pharmacysystemgui.PrescriptionGUI.JSON;
import za.ac.cput.util.Helper;

/**
 *
 * @author Ilyaas Davids
 */
public class SupplierContactGUI extends javax.swing.JFrame {
    private String suppId,suppNo,suppEmail;
          LoginGUI loginGUI = new LoginGUI();
    private static OkHttpClient client = new OkHttpClient();
    ///
            private void Authentication(){

        if(loginGUI.isAdmin() == false){
            
            lblDelete.setEnabled(false);
            btnSuppCDelete.setEnabled(false);
            btnSaveSuppContact.setEnabled(false);
            editContactNo.setEditable(false);
            editSuppEmail.setEditable(false);
           
        }
    
    }
    
    ///
            private void showTable(){
        DefaultTableModel dtModel = (DefaultTableModel) tblSuppC.getModel();
        
        List supplierContactList = getAll();
        List<SupplierContact> supplierContact = supplierContactList;
        
        dtModel.setRowCount(0);
        
        for(int i = 0; i <supplierContactList.size(); i++){
            dtModel.addRow(
                    new Object[] {
                        supplierContact.get(i).getSuppId(),
                        supplierContact.get(i).getContact()

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
      public static List<SupplierContact> getAll(){
        List<SupplierContact> supplierContactList = new ArrayList<>();
        
        try{ 
            final String URL = "http:localhost:8080/PharmacySystem/supplierContact/all";
            String responseBody = get(URL);
            JSONArray supplierContact = new JSONArray(responseBody);
            
            for (int i = 0; i < supplierContact.length();i++){
                JSONObject supplierJSONObject = supplierContact.getJSONObject(i);
                
                Gson g = new Gson();
                SupplierContact p = g.fromJson(supplierJSONObject.toString(), SupplierContact.class);
                supplierContactList.add(p);
                System.out.println(p.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return supplierContactList;
    }
    ////
               public void find(String id){
         
        List supplierCList = getAll();
        List<SupplierContact> supplier = supplierCList;
       
        
        for(int i = 0; i < supplierCList.size(); i++){
            suppId = supplier.get(i).getSuppId();
            suppNo = supplier.get(i).getContact().getContactNumber();
            suppEmail = supplier.get(i).getContact().getEmail();
 
            
            if(suppId.equals(id)){
                editContactNo.setText(suppNo);
                editSuppEmail.setText(suppEmail);
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
         public static void save(String suppId, String suppContactNo,String suppEmail){
    
        try{
            
        final String URL = "http:localhost:8080/PharmacySystem/supplierContact/save";
        SupplierContact supplierContact = SupplierContactFactory.createSupplierContact(suppId, suppContactNo, suppEmail);
        Gson g = new Gson();
        String jsonString = g.toJson(supplierContact);
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
    
    public static void deleteSupplierContact(String suppId){
    try{
        final String deleteURL ="http:localhost:8080/PharmacySystem/supplierContact/delete/" + suppId;
        delete(deleteURL);
        String responseBody = get(deleteURL);
        
        //maybe change this??
         JOptionPane.showMessageDialog(null,"Entry successfully deleted!");
    }catch(IOException e){
        
         JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

    /**
     * Creates new form SupplierContactGUI
     */
    public SupplierContactGUI() {
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

        btnDisplaySuppContact = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuppC = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        editContactNo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnSaveSuppContact = new javax.swing.JButton();
        editSuppEmail = new javax.swing.JTextField();
        btnSupplierReturn = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        editSuppId = new javax.swing.JTextField();
        btnSuppContFind = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblDelete = new javax.swing.JLabel();
        btnSuppCDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(219, 194, 173));

        btnDisplaySuppContact.setText("Display Supplier Contact");
        btnDisplaySuppContact.setName("btnDisplayMed"); // NOI18N
        btnDisplaySuppContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplaySuppContactActionPerformed(evt);
            }
        });

        tblSuppC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "supp_ID", "supp_Contact"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSuppC);

        jPanel5.setBackground(new java.awt.Color(219, 194, 173));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51)));
        jPanel5.setName("btnMedSave"); // NOI18N

        jLabel10.setText("Supplier Contact Number:");
        jLabel10.setName("lblMedManuf"); // NOI18N

        editContactNo.setName("editMedManufUpdate"); // NOI18N

        jLabel11.setText("Supplier Email Address:");
        jLabel11.setName("lblSuppName"); // NOI18N

        btnSaveSuppContact.setText("Save");
        btnSaveSuppContact.setName("btnUpdateSuppContact"); // NOI18N
        btnSaveSuppContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSuppContactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(editContactNo)
                    .addComponent(btnSaveSuppContact, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(editSuppEmail))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editSuppEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnSaveSuppContact)
                .addGap(54, 54, 54))
        );

        btnSupplierReturn.setText("RETURN TO SUPPLIER");
        btnSupplierReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierReturnActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.setName("btnDisplayMed"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel9.setText("ID:");
        jLabel9.setName("lblMedName"); // NOI18N

        editSuppId.setName("editMedManufUpdate"); // NOI18N

        btnSuppContFind.setText("Find");
        btnSuppContFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuppContFindActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(219, 194, 173));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDelete.setText("Delete Supplier Contact:");
        lblDelete.setName("lblMedDelete"); // NOI18N

        btnSuppCDelete.setText("Delete");
        btnSuppCDelete.setName("btnDeleteMed"); // NOI18N
        btnSuppCDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuppCDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuppCDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDelete))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuppCDelete)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editSuppId, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addComponent(btnSupplierReturn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDisplaySuppContact, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                        .addComponent(btnSuppContFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(editSuppId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisplaySuppContact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSuppContFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSupplierReturn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisplaySuppContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplaySuppContactActionPerformed
        // TODO add your handling code here:
        showTable();
    }//GEN-LAST:event_btnDisplaySuppContactActionPerformed

    private void btnSaveSuppContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSuppContactActionPerformed
        // TODO add your handling code here:
        suppId = editSuppId.getText().trim();
        suppNo = editContactNo.getText().trim();
        suppEmail = editSuppEmail.getText().trim();
        
         if (suppId.isEmpty()){
           JOptionPane.showMessageDialog(null, "Invalid Supplier ID"); 
        }else if(suppNo.isEmpty()){
            JOptionPane.showMessageDialog(null, "Invalid Supplier Number"); 
        }else if(suppEmail.isEmpty() || !Helper.isValidEmail(suppEmail)){
            JOptionPane.showMessageDialog(null, "Invalid Supplier Email"); 
        }else if(evt.getSource() == btnSaveSuppContact)
        {
            save(suppId,suppNo,suppEmail);
        }
    }//GEN-LAST:event_btnSaveSuppContactActionPerformed

    private void btnSupplierReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierReturnActionPerformed
        // TODO add your handling code here:
        SupplierGUI supplier = new SupplierGUI();
        supplier.show();
        
        dispose();
    }//GEN-LAST:event_btnSupplierReturnActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        editContactNo.setText("");
        editSuppId.setText("");
        editSuppEmail.setText("");

    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSuppCDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuppCDeleteActionPerformed
        // TODO add your handling code here:
            if(evt.getSource() == btnSuppCDelete){
        deleteSupplierContact(editSuppId.getText().trim());
    } 
    }//GEN-LAST:event_btnSuppCDeleteActionPerformed

    private void btnSuppContFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuppContFindActionPerformed
        // TODO add your handling code here:
       suppId = editSuppId.getText().trim();
        
        if (suppId.isEmpty()){
        JOptionPane.showMessageDialog(null, "Invalid Supplier ID"); 
        }else{
            find(suppId);
        }
    }//GEN-LAST:event_btnSuppContFindActionPerformed

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
            java.util.logging.Logger.getLogger(SupplierContactGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupplierContactGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupplierContactGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupplierContactGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierContactGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDisplaySuppContact;
    private javax.swing.JButton btnSaveSuppContact;
    private javax.swing.JButton btnSuppCDelete;
    private javax.swing.JButton btnSuppContFind;
    private javax.swing.JButton btnSupplierReturn;
    private javax.swing.JTextField editContactNo;
    private javax.swing.JTextField editSuppEmail;
    private javax.swing.JTextField editSuppId;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JTable tblSuppC;
    // End of variables declaration//GEN-END:variables
}
