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
import za.ac.cput.domain.Medication;
import za.ac.cput.factory.MedicationFactory;
import static za.ac.cput.pharmacysystemgui.EmployeeGUI.deleteEmployee;
import static za.ac.cput.pharmacysystemgui.EmployeeGUI.save;
import static za.ac.cput.pharmacysystemgui.PrescriptionGUI.JSON;

/**
 *
 * @author Ilyaas Davids
 */
public class MedicationGUI extends javax.swing.JFrame {
    LoginGUI loginGUI = new LoginGUI(); ;
    private String medName , medManuf ,medId ,suppId ;
    private static OkHttpClient client = new OkHttpClient();
//////
    private void Authentication(){

        if(loginGUI.isAdmin() == false){
            
            lblDelete.setEnabled(false);
            btnDeleteMed.setEnabled(false);
            btnSaveMed.setEnabled(false);
            editMedName.setEditable(false);
            editMedManuf.setEditable(false);
            editSuppId.setEditable(false);
           
        }

        
    }
            ////
        private void showTable(){
        DefaultTableModel dtModel = (DefaultTableModel) tblMed.getModel();
        
        List medicationList = getAll();
        List<Medication> medication = medicationList;
        
        dtModel.setRowCount(0);
        
        for(int i = 0; i <medicationList.size(); i++){
            dtModel.addRow(
                    new Object[] {
                        medication.get(i).getMedId(),
                        medication.get(i).getMedName(),
                        medication.get(i).getMedManufacturer(),
                        medication.get(i).getSuppId()
                    }
            );
        }
    }
    ///////   
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
      public static List<Medication> getAll(){
        List<Medication> medicationList = new ArrayList<>();
        
        try{ 
            final String URL = "http:localhost:8080/PharmacySystem/medication/all";
            String responseBody = get(URL);
            JSONArray medication = new JSONArray(responseBody);
            
            for (int i = 0; i < medication.length();i++){
                JSONObject medications = medication.getJSONObject(i);
                
                Gson g = new Gson();
                Medication p = g.fromJson(medications.toString(), Medication.class);
                medicationList.add(p);
                System.out.println(p.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return medicationList;
    }
      /////find
     public void find(String id){
         
        List medicationList = getAll();
        List<Medication> medication = medicationList;
       
        
        for(int i = 0; i <medicationList.size(); i++){
            medId = medication.get(i).getMedId();
            medName = medication.get(i).getMedName();
            medManuf = medication.get(i).getMedManufacturer();
            suppId = medication.get(i).getSuppId();
            
            if(medId.equals(id)){
                editMedName.setText(medName);
                editMedManuf.setText(medManuf);
                editSuppId.setText(suppId);
                break;
            }else{
                 JOptionPane.showMessageDialog(null, "Medication ID "+editMedId.getText().trim()+" NOT FOUND");
                 break;
            }
            
        }

     } 
      
/////post/save
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
         public static void save(String medId, String medName, String medManuf, String SuppId){
    
        try{
            
        final String URL = "http:localhost:8080/PharmacySystem/medication/save";
        Medication medication = MedicationFactory.createMedication(medId, medName, medManuf, SuppId);
        Gson g = new Gson();
        String jsonString = g.toJson(medication);
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
    
    public static void deleteMedication(String medId){
    try{
        final String deleteURL ="http:localhost:8080/PharmacySystem/medication/delete/" + medId;
        delete(deleteURL);
        String responseBody = get(deleteURL);
        
        //maybe change this??
         JOptionPane.showMessageDialog(null,"Entry successfully deleted!");
    }catch(IOException e){
        
         JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    
      
      
    /**
     * Creates new form MedicationGUI
     */
    public MedicationGUI() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMed = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblDelete = new javax.swing.JLabel();
        btnDeleteMed = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        editMedName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        editMedManuf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnSaveMed = new javax.swing.JButton();
        editSuppId = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnFindMed = new javax.swing.JButton();
        editMedId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Medication");
        setBackground(new java.awt.Color(219, 194, 173));
        setName("frameMedication"); // NOI18N

        tblMed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "med_ID", "med_Name", "med_Manufacturer", "supp_ID"
            }
        ));
        jScrollPane1.setViewportView(tblMed);

        jPanel3.setBackground(new java.awt.Color(219, 194, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        lblDelete.setText("Delete Medication:");
        lblDelete.setName("lblMedDelete"); // NOI18N

        btnDeleteMed.setText("Delete");
        btnDeleteMed.setName("btnDeleteMed"); // NOI18N
        btnDeleteMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteMed, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDelete))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteMed)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(219, 194, 173));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51)));
        jPanel4.setName("btnMedSave"); // NOI18N

        jLabel6.setText("Medication Name:");
        jLabel6.setName("lblMedName"); // NOI18N

        editMedName.setName("editMedNameUpdate"); // NOI18N

        jLabel7.setText("Medication Manufacturer:");
        jLabel7.setName("lblMedManuf"); // NOI18N

        editMedManuf.setName("editMedManufUpdate"); // NOI18N

        jLabel8.setText("Supplier ID:");
        jLabel8.setName("lblSuppName"); // NOI18N

        btnSaveMed.setText("Save");
        btnSaveMed.setName("btnSaveMed"); // NOI18N
        btnSaveMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(editMedName)
                    .addComponent(editMedManuf)
                    .addComponent(btnSaveMed, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(editSuppId))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editMedName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editMedManuf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editSuppId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSaveMed)
                .addGap(46, 46, 46))
        );

        jButton5.setText("Display Medications");
        jButton5.setName("btnDisplayMed"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnHome.setText("RETURN HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.setName("btnFetchMed"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnFindMed.setText("Find");
        btnFindMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindMedActionPerformed(evt);
            }
        });

        jLabel4.setText("ID :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(editMedId, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFindMed, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editMedId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFindMed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHome)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMedActionPerformed
        // TODO add your handling code here:
        medId = editMedId.getText().trim();
        medName = editMedName.getText().trim();
        medManuf = editMedManuf.getText().trim();
        suppId = editSuppId.getText().trim();
        
        if (medId.isEmpty()){
            JOptionPane.showMessageDialog(null, "Invalid Medication ID");  
        }else if (medName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Invalid Medication Name");
        }else if(medManuf.isEmpty()){
            JOptionPane.showMessageDialog(null, "Invalid Medication Manufacturer");
        }else if (suppId.isEmpty()){
           JOptionPane.showMessageDialog(null, "Invalid Supplier ID"); 
        }else if(evt.getSource() == btnSaveMed)
        {
            save(medId,medName,medManuf,suppId);
        }
        
    }//GEN-LAST:event_btnSaveMedActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        HomeGUI home = new HomeGUI();
        home.show();
        
        dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        showTable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnFindMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindMedActionPerformed
        // TODO add your handling code here:
        medId = editMedId.getText().trim();
        
        if (medId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Medication ID"); 
        }else{
            find(medId);
        }
        
    }//GEN-LAST:event_btnFindMedActionPerformed

    private void btnDeleteMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMedActionPerformed
        // TODO add your handling code here:
                if(evt.getSource() == btnDeleteMed){
                    deleteMedication(editMedId.getText().trim());
        }
    }//GEN-LAST:event_btnDeleteMedActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
          editMedId.setText("");
          editMedName.setText("");
          editMedManuf.setText("");
          editSuppId.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(MedicationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MedicationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MedicationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MedicationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MedicationGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeleteMed;
    private javax.swing.JButton btnFindMed;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnSaveMed;
    private javax.swing.JTextField editMedId;
    private javax.swing.JTextField editMedManuf;
    private javax.swing.JTextField editMedName;
    private javax.swing.JTextField editSuppId;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JTable tblMed;
    // End of variables declaration//GEN-END:variables
}
