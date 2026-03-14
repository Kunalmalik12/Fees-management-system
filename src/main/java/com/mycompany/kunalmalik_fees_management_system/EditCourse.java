/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.kunalmalik_fees_management_system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MY LENOVO
 */
public class EditCourse extends javax.swing.JFrame {

    /**
     * Creates new form EditCourse
     */
    public EditCourse() {
        initComponents();
        setRecordToTable();
    }

     DefaultTableModel model;

    
       
     public void clearTable(){
         DefaultTableModel model = ( DefaultTableModel)jTable1.getModel();
         model.setRowCount(0);
     }
     
     
    public void setRecordToTable(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = ("select * from course");               
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
          while(rs.next()){
              String Id = rs.getString("Id");
              String Course_name = rs.getString("CNAME");
              String Amount = rs.getString("COST");
             
              
              Object[] obj = {Id, Course_name, Amount,};
              model = (DefaultTableModel)jTable1.getModel();
              model.addRow(obj);
          }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
     public void search(String str){
        model = (DefaultTableModel)jTable1.getModel();
        TableRowSorter<DefaultTableModel> n1=new TableRowSorter<>(model);
        jTable1.setRowSorter(n1);
        n1.setRowFilter(RowFilter.regexFilter(str));
        
    }
     
     public void addCourse(int id, String cname, double cost){
         try{
              Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = ("insert into course value(?,?,?)");               
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2,cname );
            st.setDouble(3, cost);
            int rowCount = st.executeUpdate();
            if(rowCount==1){
                 JOptionPane.showMessageDialog(this, "course added succesfuuly");
                 clearTable();
                 setRecordToTable();
            }else{
             JOptionPane.showMessageDialog(this, "course insertion failed");
            }
            
         }catch(Exception e){
             JOptionPane.showMessageDialog(this, "course insertion failed");
             e.printStackTrace();
         }
     }
     
     
     public void update(int id, String cname, double cost){
        try{
              Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = ("update course set cname =?, cost=? where id=? ");               
            PreparedStatement st = con.prepareStatement(sql);
           
            st.setString(1,cname );
            st.setDouble(2, cost);
            st.setInt(3, id);
            int rowCount = st.executeUpdate();
            if(rowCount==1){
                 JOptionPane.showMessageDialog(this, "course update succesfuuly");
                 clearTable();
                 setRecordToTable();
            }else{
             JOptionPane.showMessageDialog(this, "course updation failed");
            }
            
         }catch(Exception e){
             JOptionPane.showMessageDialog(this, "course updation failed");
             e.printStackTrace();
         } 
     }
     
     
     
     public void delete(int id){
        try{
              Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = ("delete from course where id = ? ");               
            PreparedStatement st = con.prepareStatement(sql);
           
            st.setInt(1, id);
            int rowCount = st.executeUpdate();
            if(rowCount==1){
                 JOptionPane.showMessageDialog(this, "course deleted succesfuuly");
                 clearTable();
                 setRecordToTable();
            }else{
             JOptionPane.showMessageDialog(this, "course deletion failed");
            }
            
         }catch(Exception e){
             JOptionPane.showMessageDialog(this, "course deletion failed");
             e.printStackTrace();
         } 
     }
   
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtcost = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtcname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jButton3.setBackground(new java.awt.Color(102, 255, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\search2.png")); // NOI18N
        jButton3.setText("Search Record");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 255, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\view all record.png")); // NOI18N
        jButton5.setText("View all Record");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 255, 102));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\logout.png")); // NOI18N
        jButton6.setText("LOGOUT");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(102, 255, 102));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\list.png")); // NOI18N
        jButton7.setText("Course List");

        jButton8.setBackground(new java.awt.Color(102, 255, 102));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\home.png")); // NOI18N
        jButton8.setText("Home");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(102, 255, 102));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\back1.png")); // NOI18N
        jButton10.setText("BACK");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton6)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 760));

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Course_Name", "Amount"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 600, 630));

        txtcost.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtcost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcostActionPerformed(evt);
            }
        });
        jPanel6.add(txtcost, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 340, 220, 40));

        txtid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        jPanel6.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 180, 220, 40));

        txtcname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel6.add(txtcname, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 220, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Amount :");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 350, 90, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Course Id :");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 186, 90, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Course name :");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 120, 30));

        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\delete.png")); // NOI18N
        jButton11.setText("DELETE");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 460, -1, -1));

        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\add2.png")); // NOI18N
        jButton12.setText("ADD");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 460, 110, -1));

        jButton13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\update.png")); // NOI18N
        jButton13.setText("UPDATE");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, -1, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setText("Edit Details");
        jPanel6.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 120, 30));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 1140, 770));

        setSize(new java.awt.Dimension(1417, 767));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        SearchRecord m4 = new SearchRecord();
        m4.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        SignUp s3 = new SignUp();
        s3.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        Homepage m2 = new Homepage();
        m2.setVisible(true);
        this.dispose();     // TODO add your handling code here:
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
     // TODO add your handling code here:
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        Homepage m1 = new Homepage();
        m1.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtcostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcostActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
int id = Integer.parseInt(txtid.getText());
delete(id);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
int rowNo = jTable1.getSelectedRow();
TableModel model = jTable1.getModel();
   txtid.setText(model.getValueAt(rowNo, 0).toString());
   txtcname.setText(model.getValueAt(rowNo, 1).toString());
txtcost.setText(model.getValueAt(rowNo, 2).toString());// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
int id = Integer.parseInt(txtid.getText());
String cname = txtcname.getText();
double cost = Double.parseDouble(txtcost.getText());
addCourse(id, cname,cost);// TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
   int id = Integer.parseInt(txtid.getText());
String cname = txtcname.getText();
double cost = Double.parseDouble(txtcost.getText());


update(id,cname,cost);// TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

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
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditCourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtcname;
    private javax.swing.JTextField txtcost;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
