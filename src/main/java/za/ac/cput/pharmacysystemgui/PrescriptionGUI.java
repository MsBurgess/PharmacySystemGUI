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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Prescription;
import za.ac.cput.factory.PrescriptionFactory;
import static za.ac.cput.pharmacysystemgui.EmployeeGUI.deleteEmployee;
import static za.ac.cput.pharmacysystemgui.EmployeeGUI.getAll;

/**
 *
 * @author Zaeem
 */
public class PrescriptionGUI extends javax.swing.JFrame {

     LoginGUI loginGUI = new LoginGUI() ;
     
 private static OkHttpClient client = new OkHttpClient();
 
 public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

 //old
//  public static String run(String , String json) throws IOException{
//        ResquestBody Body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(URL)
//                //security part
//                .header("Authorization",  Credentials.basic("User", "54321"))
//                .build();
//        try(Response response = client.newCall(request).execute()){
//            return response.body().string();
//        }
//    }
  
  private void Authentication(){

        if(loginGUI.isAdmin() == false){
            
            btnPrescriptionDelete.setEnabled(false);
            txtPrescriptionId.setEnabled(false);
            txtPrescriptionDoses.setEnabled(false);
            txtIdDelete.setEnabled(false);
            cboPrescriptionType.setEnabled(false);
            
            btnPrescriptionPost.setEnabled(false);
            txtIdDelete.setEnabled(false);
        }

        
    }
  
      private void showTable(){
        DefaultTableModel dtModel = (DefaultTableModel) tablePrescriptionView.getModel();
        
        List prescriptionList = getAll();
        List<Prescription>prescription = prescriptionList;
        
        dtModel.setRowCount(0);
        
        for(int i = 0; i <prescriptionList.size(); i++){
            dtModel.addRow(
                    new Object[] {
                        prescription.get(i).getPrescID(),
                        prescription.get(i).getPrescType(),
                        prescription.get(i).getPrescDoses()
                    }
            );
        }
    }
 
 //get all stuff
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
      
        public static List <Prescription> getAll(){
            
            List<Prescription>prescriptionList = new ArrayList<>();
        try{
            final String URL = "http:localhost:8080/PharmacySystem/prescription/all";
            String responseBody = get(URL);
            JSONArray prescriptions = new JSONArray(responseBody);
            
            for (int i = 0; i < prescriptions.length();i++){
                JSONObject prescription = prescriptions.getJSONObject(i);
                
                Gson g = new Gson();
                Prescription p = g.fromJson(prescription.toString(), Prescription.class);
                prescriptionList.add(p);
                System.out.println(p.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return prescriptionList;
    }

        //post Add
    private String post(final String postURL, String json) throws IOException
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
    
  public void save(String prescID, String prescType, String prescDoses){
    
        try{
        final String SaveURL = "http:localhost:8080/PharmacySystem/prescription/save";
        Prescription prescription = PrescriptionFactory.createPrescription(prescID, prescType, prescDoses);
        Gson g = new Gson();
        String jsonString = g.toJson(prescription);
        String r = post(SaveURL, jsonString);
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
    
    public static void deletePrescription(String prescID){
    try{
        final String deleteURL ="http:localhost:8080/PharmacySystem/prescription/delete/" + prescID;
        delete(deleteURL);
        String responseBody = get(deleteURL);
        
         //maybe change this??
         JOptionPane.showMessageDialog(null,"Entry successfully deleted!");
    }catch(IOException e){
        
         JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    /**
     * Creates new form PharmacySystemGUI
     */
    public PrescriptionGUI() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        GetAll = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePrescriptionView = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnPrescriptionPost = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrescriptionId = new javax.swing.JTextField();
        txtPrescriptionDoses = new javax.swing.JTextField();
        cboPrescriptionType = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnPrescriptionDelete = new javax.swing.JButton();
        txtIdDelete = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Prescription Database");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        GetAll.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        GetAll.setText("VIEW/UPDATE TABLE");
        GetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(GetAll, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(GetAll, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("HOME");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("EXIT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(325, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tablePrescriptionView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Type", "Doses"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablePrescriptionView);

        jTabbedPane2.addTab("TABLE", jScrollPane1);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnPrescriptionPost.setText("ADD PRESCRIPTION");
        btnPrescriptionPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrescriptionPostActionPerformed(evt);
            }
        });

        jLabel2.setText("Type");

        jLabel3.setText("Doses");

        jLabel4.setText("ID");

        cboPrescriptionType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pain reliever", "Tranquilizer", "Stimulant", "Sedative" }));
        cboPrescriptionType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPrescriptionTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(btnPrescriptionPost, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrescriptionDoses, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(cboPrescriptionType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(183, 183, 183)
                    .addComponent(txtPrescriptionId, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(172, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(cboPrescriptionType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrescriptionDoses, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btnPrescriptionPost, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(90, 90, 90)
                    .addComponent(txtPrescriptionId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(236, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("ADD", jPanel4);

        jLabel5.setText("ID");

        btnPrescriptionDelete.setText("DELETE");
        btnPrescriptionDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrescriptionDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(txtIdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(btnPrescriptionDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(btnPrescriptionDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("DELETE", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetAllActionPerformed
        // TODO add your handling code here:
        showTable();
        //getAll();
    }//GEN-LAST:event_GetAllActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        HomeGUI home = new HomeGUI();
        home.show();

        dispose(); //closes current frame
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnPrescriptionPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrescriptionPostActionPerformed
        // TODO add your handling code here:
        if(evt.getSource() == btnPrescriptionPost)
        {
            //save btn
            save(txtPrescriptionId.getText(), cboPrescriptionType.getSelectedItem().toString(), txtPrescriptionDoses.getText());
        }
    }//GEN-LAST:event_btnPrescriptionPostActionPerformed

    private void cboPrescriptionTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPrescriptionTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPrescriptionTypeActionPerformed

    private void btnPrescriptionDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrescriptionDeleteActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == btnPrescriptionDelete){
            deletePrescription(txtIdDelete.getText().trim());
        }
    }//GEN-LAST:event_btnPrescriptionDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(PrescriptionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrescriptionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrescriptionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrescriptionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrescriptionGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GetAll;
    private javax.swing.JButton btnPrescriptionDelete;
    private javax.swing.JButton btnPrescriptionPost;
    private javax.swing.JComboBox<String> cboPrescriptionType;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tablePrescriptionView;
    private javax.swing.JTextField txtIdDelete;
    private javax.swing.JTextField txtPrescriptionDoses;
    private javax.swing.JTextField txtPrescriptionId;
    // End of variables declaration//GEN-END:variables
}
