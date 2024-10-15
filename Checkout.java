
package hotel.management.system;

import java.awt.Choice;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import net.proteanit.sql.*;
import java.awt.Image;
import java.sql.*;
import net.proteanit.sql.*;
import java.util.Date;
public class Checkout extends JFrame  implements ActionListener {
    Choice customer;
    JButton checkout,back;
    JTextField roomnumber,checkintime,driver;
    JLabel checkouttime,lblroom,lblcheck,lbldriver;
    Checkout(){
         setLayout(null);
        getContentPane().setBackground(Color.white);
        
        JLabel text=new JLabel("Checkout");
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
         text.setBounds(100,20,140,30);
          add(text);
          
            JLabel lblid=new JLabel("Customer Id");
        lblid.setFont(new Font("Tahoma",Font.PLAIN,20));
         lblid.setBounds(30,80,120,30);
          add(lblid);
          
           customer=new Choice();
        customer.setBounds(170,85,130,35);
          add(customer);
          
          
// ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
// Image i2=i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
//  ImageIcon i3=new ImageIcon(i2);
//JLabel image=new JLabel(i3);
//image.setBounds(310,80,20,20);
//add(image);

         lblroom=new JLabel("Room no");
        lblroom.setFont(new Font("Tahoma",Font.PLAIN,20));
         lblroom.setBounds(30,130,120,30);
          add(lblroom);
          
          roomnumber =new JTextField();
        roomnumber.setFont(new Font("Tahoma",Font.PLAIN,20));
         roomnumber.setBounds(170,130,130,30);
          add(roomnumber);
        
            lblcheck=new JLabel("Checkintime");
        lblcheck.setFont(new Font("Tahoma",Font.PLAIN,20));
         lblcheck.setBounds(20,180,130,30);
          add(lblcheck);
          
          checkintime =new  JTextField();
        checkintime.setFont(new Font("Tahoma",Font.PLAIN,20));
         checkintime.setBounds(170,180,130,30);
          add(checkintime);
          
           JLabel lblcheckout=new JLabel("Checkouttime");
        lblcheckout.setFont(new Font("Tahoma",Font.PLAIN,20));
         lblcheckout.setBounds(20,230,130,30);
          add(lblcheckout);
          
          Date date=new Date();
          checkouttime =new JLabel(""+date);
        checkouttime.setFont(new Font("Tahoma",Font.PLAIN,20));
         checkouttime.setBounds(170,230,200,30);
          add(checkouttime);
          
           lbldriver=new JLabel("Driver-Id");
        lbldriver.setFont(new Font("Tahoma",Font.PLAIN,20));
         lbldriver.setBounds(30,280,120,30);
          add(lbldriver);
          
          driver =new JTextField();
        driver.setFont(new Font("Tahoma",Font.PLAIN,20));
         driver.setBounds(170,280,130,30);
          add(driver);
          
          checkout = new JButton("Checkout");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);  
        checkout.setBounds(30,340,100,30);
       checkout.addActionListener(this);
        add(checkout);
        
         back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);  
        back.setBounds(170,340,120,30);
       back.addActionListener(this);
        add(back);
        
           try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM Customs");
            while (rs.next()) {
                customer.add(rs.getString("numberid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
           
              customer.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("SELECT * FROM Customs WHERE numberid='" + customer.getSelectedItem() + "'");
                    if (rs.next()) {
                        roomnumber.setText(rs.getString("roomnumber"));
                        checkintime.setText(rs.getString("checkintime"));
                        driver.setText(rs.getString("dId"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
            
        
 ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
 Image i2=i1.getImage().getScaledInstance(400,250,Image.SCALE_DEFAULT);
  ImageIcon i3=new ImageIcon(i2);
JLabel image=new JLabel(i3);
image.setBounds(400,50,400,300);
add(image);
        
        
         setBounds(300,200,900,500);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
          if(ae.getSource()==checkout){
              String query1="delete from Customs where numberid= '"+customer.getSelectedItem()+"'";
              String query2="update Romms set availability='Available' where roomnumber='"+roomnumber.getText()+"'";
              String query3="update Drivrs set available='Available' where dId='"+driver.getText()+"'";
              try{
                   Conn c = new Conn();
                   c.s.executeUpdate(query1);
                    c.s.executeUpdate(query2);
                    c.s.executeUpdate(query3);
                    JOptionPane.showMessageDialog(null, "Checkout Done");
                     setVisible(false);
               new Reception();
              }
               catch(Exception e){
            e.printStackTrace();
        } 
              
          }
          else{
               setVisible(false);
               new Reception();
          }
    
    }
     public static void main(String args[]){
        new Checkout();
    }
    
}
