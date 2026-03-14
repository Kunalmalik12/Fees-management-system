/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.kunalmalik_fees_management_system;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MY LENOVO
 */
public class ViewReport extends javax.swing.JFrame {

    /**
     * Creates new form ViewReport
     */
    public ViewReport() {
        initComponents();
        fillcombobox();
    }

   
    
     public class NumberToWordsConverter {

        public static final String[] units = {"", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};

        public static final String[] tens = {
            "", // 0
            "", // 1
            "Twenty", // 2
            "Thirty", // 3
            "Forty", // 4
            "Fifty", // 5
            "Sixty", // 6
            "Seventy", // 7
            "Eighty", // 8
            "Ninety" // 9
        };

        public static String convert(final int n) {
            if (n < 0) {
                return "Minus " + convert(-n);
            }

            if (n < 20) {
                return units[n];
            }

            if (n < 100) {
                return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
            }

            if (n < 1000) {
                return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
            }

            if (n < 100000) {
                return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
            }

            if (n < 10000000) {
                return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
            }

            return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
        }
     }
    
    
    
    
    DefaultTableModel model;
    
    public void fillcombobox() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = "select cname from course";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                jComboBox1.addItem(rs.getString("cname"));
            }
          

        }
        catch (Exception e1) {
            e1.printStackTrace();
        }

    }
    
   
    
    
    public void setRecordToTable(){
        
        String cname = jComboBox1.getSelectedItem().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromdate = dateFormat.format(jDateChooser2.getDate());
        String todate = dateFormat.format(jDateChooser1.getDate());
        
        Float totalAmount = 0.0f;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = ("select *   FROM fees_details WHERE date BETWEEN ? AND ? AND course_name = ?");             
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, fromdate);
            st.setString(2, todate);
             st.setString(3, cname);
            
            ResultSet rs = st.executeQuery();
          while(rs.next()){
              String Reciept_no = rs.getString("reciept_no");
               String Roll_no = rs.getString("roll_no");
              String Student_name = rs.getString("student_name");
              String Father_name = rs.getString("Father_name");
              String Payment_mode = rs.getString("payment_mode");
              String Course_taken=rs.getString("course_name");
              float Amount = rs.getFloat("total_amount"); 
              
             totalAmount = totalAmount + Amount;
              
              Object[] obj = {Reciept_no, Roll_no, Student_name, Father_name, Payment_mode, Course_taken, Amount,};
              model = (DefaultTableModel)jTable1.getModel();
              model.addRow(obj);
          }
          
          jLabel6.setText(cname);
          jLabel7.setText(totalAmount.toString());
          jLabel2.setText(NumberToWordsConverter.convert(totalAmount.intValue()));
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void clearTable(){
         DefaultTableModel model = ( DefaultTableModel)jTable1.getModel();
         model.setRowCount(0);
     }
         
  //  public void exportToexcel(){
 //       XSSFWorkbook wb =  new XSSFWorkbook();
 ////      XSSFSheet ws = wb.createSheet();
 //      DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
      
  //    TreeMap<String, Object[]>map=new TreeMap<>();
 // map.put("0", new Object[]{model.getColumnName(0), model.getColumnName(1), model.getColumnName(2), model.getColumnName(3),model.getColumnName(4),model.getColumnName(5)});
 //    for(int i=1; i< model.getRowCount();i++){
//map.put(Integer.toString(i),new Object[]{model.getValueAt(i,0),model.getValueAt(i,1),model.getValueAt(i,2),model.getValueAt(i,3),model.getValueAt(i,4),model.getValueAt(i,5)});
//}
//Set<String> id = map.keySet();
//XSSFRow fRow;
//int rowId=0;
           
///for(String key : id){
//fRow = ws.createRow(rowId++);
//Object[] value = map.get(key);
//int cellId = 0;
//for(Object object : value){
//XSSFCell cell= fRow.createCell(cellId++);
//cell.setCellValue(object.toString());
//}
//}
//try{
//// FileOutputStream fos = new FileOutputStream(new File(jTextField1.getText()));
//wb.write(fos);
//fos.close();
// JOptionPane.showMessageDialog(this,"file exported succesfuuly" +jTextField1.getText());
//}catch(Exception e){
//e.printStackTrace();
//}

    
  //  }


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
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(102, 255, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\search2.png")); // NOI18N
        jButton3.setText("Search Record");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 104, 215, 69));

        jButton4.setBackground(new java.awt.Color(102, 255, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\edit2.png")); // NOI18N
        jButton4.setText("Edit Course");
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 185, 215, 74));

        jButton5.setBackground(new java.awt.Color(102, 255, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\view all record.png")); // NOI18N
        jButton5.setText("View all Record");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 423, 215, -1));

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
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 572, 215, -1));

        jButton7.setBackground(new java.awt.Color(102, 255, 102));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\list.png")); // NOI18N
        jButton7.setText("Course List");
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 348, 215, 62));

        jButton9.setBackground(new java.awt.Color(102, 255, 102));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\add2.png")); // NOI18N
        jButton9.setText("ADD COURSE");
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 271, 215, 65));

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
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 25, 215, 61));

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
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 500, 215, 60));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 1050));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("To Date :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 80, 30));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 150, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Select Date :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 110, 30));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 140, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("From Date :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 110, 30));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 140, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Export to excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 150, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 90, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 310, -1));

        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton11.setText("Print");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 90, -1));

        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton12.setText("Browse");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 90, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Reciept_no", "Roll_no", "Student name", "Father_name", "Payment_mode", "Course_taken", "Amount"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 1040, 510));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 400, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 170, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 200, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Course selected :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total amount collected :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 200, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total amount in words :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 200, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 460, 180));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Select Course :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1270, 1050));

        setSize(new java.awt.Dimension(1550, 1064));
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
        Color c3=new Color(0,153,153);
        jButton5.setBackground(c3);// TODO add your handling code here:
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
clearTable();
        setRecordToTable();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
     SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
     String datefrom = Date_Format.format(jDateChooser2.getDate());
     String dateto = Date_Format.format(jDateChooser1.getDate());
     
     MessageFormat header = new MessageFormat("Report From "+datefrom+" To " +dateto);
     MessageFormat footer = new MessageFormat("page{0,number,integer}");
     try{
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer); 
     }catch(Exception e){
      e.printStackTrace();
     }
     
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
JFileChooser filechooser = new JFileChooser();
filechooser.showOpenDialog(this);
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String date= dateFormat.format(new Date());

        try{
           File F = filechooser.getSelectedFile();
           String path = F.getAbsolutePath();
           
           path = path+"_"+date+".xlsx";
           
           jTextField1.setText(path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

  
    
    
    
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
            java.util.logging.Logger.getLogger(ViewReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
