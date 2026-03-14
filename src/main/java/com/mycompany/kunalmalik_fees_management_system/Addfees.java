/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.kunalmalik_fees_management_system;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author MY LENOVO
 */
public class Addfees extends javax.swing.JFrame {

   // private void initComponents() {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   // }

    /**
     * Creates new form Addfees
     */
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

        public static void main(final String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Amount : ");
            int n = sc.nextInt();

            System.out.println(convert(n) + " Only");

        }
    }

    public void displayCashFirst() {
        lblcheque.setVisible(false);
        lblbank.setVisible(false);
        
        Textcheque.setVisible(false);
        Textbank.setVisible(false);

    }
    
    
    
    public String insertdata(){
       int recieptno=Integer.parseInt(Textreceipt.getText());
        String sname=Textrename.getText();
        String paymentmode=ComboBox1.getSelectedItem().toString();
        String chequeno=Textcheque.getText();
        String bankname=Textbank.getText();
        String coursename=jComboBox3.getSelectedItem().toString();
        float totalamount=Float.parseFloat(jTextField15.getText());
        SimpleDateFormat d1=new SimpleDateFormat("yyyy-MM-dd");
        String date=d1.format(DateChooser1.getDate());
        float gst=Float.parseFloat(jTextField16.getText());
        String father_name = jTextField1.getText();
        
       
        
        String remark=jTextArea1.getText();
          String status="";
          
              String rollno = jroll.getText();
              String amount = jTextField14.getText();
              String totalwords = jTextField13.getText();
              
              
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = "insert into fees_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
           st.setInt(1,recieptno);
           st.setString(2,sname);
           st.setString(3, paymentmode);
           st.setString(4, chequeno);
           st.setString(5, bankname);
           st.setString(6, coursename);
           st.setFloat(8, totalamount);
           st.setString(9, date);
           st.setFloat(7, gst);
           st.setString(10, remark);
           st.setString(11,rollno);
           st.setString(12,amount);
           st.setString(13, totalwords);
           st.setString(14,father_name);
           
        int c=st.executeUpdate();
       
         if(c==1)
         {
         status="success";
         }
         else
         {
         status="failed";
         }
           
        }
       catch(Exception e1)
       {
       e1.printStackTrace();
       }
      return status;     
    }

    public Addfees() {
        //////
        initComponents();
        displayCashFirst();
        fillcomboboxandAmount();
//       amount();
         int r=getreceipt();
         r++;
        Textreceipt.setText(Integer.toString(r));
    }
    
    

    boolean validation() {
        if (Textrename.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter receiver name" + " first ");
            return false;
        }
        if (DateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please enter date");
            return false;
        }
        
        if (ComboBox1.getSelectedItem().toString().equalsIgnoreCase("Cheque")) {
            if (Textcheque.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter cheque number");
                return false;
            }
            if (Textbank.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter bank name");
                return false;
            }
        }
        
        
        if(jTextField1.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Please Enter father name");
                return false; 
        }
        
        
        if(jroll.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Please enter roll no");
             return false;
        }
        
        
        if (jTextField14.getText().equals(""))  // || jTextField14.getText().matches("(0-9) + ")==false)
        {
            JOptionPane.showMessageDialog(this, "Please enter amount");
            return false;
        }
        return true;
    }

    public void fillcomboboxandAmount() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = "select cname from course";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                jComboBox3.addItem(rs.getString("cname"));
               
            }
           //  jTextField14.setText(rs.getString("cost"));
          

        }
        catch (Exception e1) {
            e1.printStackTrace();
        }

    }
    
 //   public void amount(){
 //         try{
 ///          Class.forName("com.mysql.cj.jdbc.Driver");
  //         String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
  //        Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");
//String selectedcourse=(String)jComboBox3.getSelectedItem();
          
        //   String sql = "select cost from course where cname=?";               
      //    PreparedStatement st = con.prepareStatement(sql);
          
          
         // st.setString(1,selectedcourse);
       //    ResultSet rs = st.executeQuery(sql);
     //      rs.next();
            
   //        jTextField14.setText("cost");
            
 //      }catch(Exception e){
        // e.printStackTrace();
      // }
    //}

    
    
    public int getreceipt()
    {
        int rno=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/codingseekho fees?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "kunalmalik@123");

            String sql = "select max(reciept_no)from fees_details";               // reciept_no
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()==true)
            {
            rno=rs.getInt(1);
            }
          

        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return rno;
    }
    
    
    
    
    
    
    @SuppressWarnings({"unchecked", "unused"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        PanelParent = new javax.swing.JPanel();
        lblreceipt = new javax.swing.JLabel();
        lblpayment = new javax.swing.JLabel();
        ComboBox1 = new javax.swing.JComboBox<>();
        lblcheque = new javax.swing.JLabel();
        Textcheque = new javax.swing.JTextField();
        Textreceipt = new javax.swing.JTextField();
        lblbank = new javax.swing.JLabel();
        Textbank = new javax.swing.JTextField();
        lbldate = new javax.swing.JLabel();
        DateChooser1 = new com.toedter.calendar.JDateChooser();
        lblgst = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        lblgst1 = new javax.swing.JLabel();
        jroll = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        PanelChild = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        Textrename = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\search2.png")); // NOI18N
        jButton1.setText("Search Record");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 255, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\edit2.png")); // NOI18N
        jButton2.setText("Edit Course");

        jButton3.setBackground(new java.awt.Color(102, 255, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\view all record.png")); // NOI18N
        jButton3.setText("View all Record");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(102, 255, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\logout.png")); // NOI18N
        jButton4.setText("LOGOUT");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 255, 102));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\list.png")); // NOI18N
        jButton6.setText("Course List");

        jButton9.setBackground(new java.awt.Color(102, 255, 102));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\add2.png")); // NOI18N
        jButton9.setText("ADD COURSE");

        jButton5.setBackground(new java.awt.Color(102, 255, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\home.png")); // NOI18N
        jButton5.setText("Home");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(102, 255, 102));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\MY LENOVO\\Documents\\my icons\\back1.png")); // NOI18N
        jButton7.setText("BACK");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelParent.setBackground(new java.awt.Color(0, 153, 153));
        PanelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblreceipt.setText("Receipt No");
        PanelParent.add(lblreceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 107, 24));

        lblpayment.setText("Mode of payment");
        PanelParent.add(lblpayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 127, -1));

        ComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "UPI", "NetBanking", "Cheque", " " }));
        ComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox1ActionPerformed(evt);
            }
        });
        PanelParent.add(ComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 125, -1));

        lblcheque.setText("Cheque No");
        PanelParent.add(lblcheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 94, 25));
        PanelParent.add(Textcheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 125, -1));

        Textreceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextreceiptActionPerformed(evt);
            }
        });
        PanelParent.add(Textreceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 125, -1));

        lblbank.setText("Bank name");
        PanelParent.add(lblbank, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 94, 27));

        Textbank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextbankActionPerformed(evt);
            }
        });
        PanelParent.add(Textbank, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 134, -1));

        lbldate.setText("Date");
        PanelParent.add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 58, 22));
        PanelParent.add(DateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 137, -1));

        lblgst.setText("Roll no");
        PanelParent.add(lblgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 67, -1));

        jLabel10.setText("AXTINH1235745");
        PanelParent.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 112, 24));

        jButton8.setText("Print");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        PanelParent.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        lblgst1.setText("GSTIN");
        PanelParent.add(lblgst1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 67, -1));
        PanelParent.add(jroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 170, 30));

        jLabel1.setText("Father name");
        PanelParent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 80, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        PanelParent.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 210, 30));

        PanelChild.setBackground(new java.awt.Color(0, 153, 153));

        jLabel21.setText("Course");

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jSeparator4.setBackground(new java.awt.Color(51, 51, 0));
        jSeparator4.setForeground(new java.awt.Color(255, 0, 51));

        jLabel22.setText("Sr No");

        jLabel23.setText("Head");

        jLabel24.setText("Amount");

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jLabel25.setText("Remark");

        jLabel26.setText("GST 18%");

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jLabel27.setText("Recever Signature");

        jLabel28.setText(" Total in words");

        jLabel29.setText("Total");

        jSeparator5.setBackground(new java.awt.Color(51, 51, 0));
        jSeparator5.setForeground(new java.awt.Color(255, 0, 51));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel6.setText("Receiever Name");

        Textrename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextrenameActionPerformed(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(102, 102, 255));

        jSeparator7.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout PanelChildLayout = new javax.swing.GroupLayout(PanelChild);
        PanelChild.setLayout(PanelChildLayout);
        PanelChildLayout.setHorizontalGroup(
            PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(Textrename, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(580, 580, 580)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel22))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(530, 530, 530)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        PanelChildLayout.setVerticalGroup(
            PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChildLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Textrename, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel19)))
                .addGap(16, 16, 16)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))))
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelChildLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(PanelChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelChild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(PanelParent, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelParent, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelChild, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void TextreceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextreceiptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextreceiptActionPerformed

    private void ComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox1ActionPerformed
        if (ComboBox1.getSelectedIndex() == 3) {
            lblcheque.setVisible(true);
            Textcheque.setVisible(true);
            lblbank.setVisible(true);
          Textbank.setVisible(true);
        }

        if (ComboBox1.getSelectedIndex() == 0) {
         
    lblcheque.setVisible(false);
            Textcheque.setVisible(false);
            lblbank.setVisible(false);
          Textbank.setVisible(false);
        }
        
        if(ComboBox1.getSelectedIndex()==2){
                lblcheque.setVisible(false);
            Textcheque.setVisible(false);
            lblbank.setVisible(true);
          Textbank.setVisible(true);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_ComboBox1ActionPerformed

    private void TextbankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextbankActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextbankActionPerformed

    private void TextrenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextrenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextrenameActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String s6 = jComboBox3.getSelectedItem().toString();
        jTextField17.setText(s6);
//      jTextField14.setText(getString("cost"));
// amount();// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        String s1 = jTextField14.getText();
        float amt = Float.parseFloat(s1);
        float gst = amt * 0.18f;
        jTextField16.setText(Float.toString(gst));
        float t = amt + gst;
        jTextField15.setText(Float.toString(t));
        jTextField13.setText(NumberToWordsConverter.convert((int) t));

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (validation() == true) {
       String s=insertdata();
        if(s.equals("success"))
        {
            JOptionPane.showMessageDialog(this,"Record inserted successfully");
            
            PrintReciept n1 = new PrintReciept();
            n1.setVisible(true);
            this.dispose();
        }
        else
        {
        JOptionPane.showMessageDialog(this,"Record not inserted");
        }
        }  // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SignUp s3 = new SignUp();
       s3.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        Homepage m2 = new Homepage();
        m2.setVisible(true);
        this.dispose();     // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        Homepage m1 = new Homepage();
        m1.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
Color c3=new Color(0,153,153);
jButton5.setBackground(c3);// TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseEntered

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
SearchRecord m4 = new SearchRecord();
m4.setVisible(true);
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt){
    
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
            java.util.logging.Logger.getLogger(Addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Addfees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBox1;
    private com.toedter.calendar.JDateChooser DateChooser1;
    private javax.swing.JPanel PanelChild;
    private javax.swing.JPanel PanelParent;
    private javax.swing.JTextField Textbank;
    private javax.swing.JTextField Textcheque;
    private javax.swing.JTextField Textreceipt;
    private javax.swing.JTextField Textrename;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jroll;
    private javax.swing.JLabel lblbank;
    private javax.swing.JLabel lblcheque;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblgst;
    private javax.swing.JLabel lblgst1;
    private javax.swing.JLabel lblpayment;
    private javax.swing.JLabel lblreceipt;
    // End of variables declaration//GEN-END:variables
}
