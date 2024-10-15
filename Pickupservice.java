
        
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
public class Pickupservice extends JFrame implements ActionListener{
    JTable table;
    JLabel text;
    JButton back,submit;
     Choice typeofcar;
     JCheckBox available;
    Pickupservice(){
        setLayout(null);
        getContentPane().setBackground(Color.white);
        
          text=new JLabel("Pickup Service");
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
         text.setBounds(400,30,200,30);
          add(text);
        
           JLabel lblbed=new JLabel("Type of Car");
        lblbed.setFont(new Font("Tahoma",Font.PLAIN,20));
         lblbed.setBounds(30,100,120,25);
          add(lblbed);
       
        typeofcar=new Choice();
        typeofcar.setBounds(150,100,200,25);
          add(typeofcar);
          
          try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from Drivrs");
           while(rs.next()){
               typeofcar.add(rs.getString("model"));
           }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        available =new JCheckBox("Only Display Available");
         available.setBounds(650,100,150,25);
        available.setBackground(Color.WHITE);
        add(available);
          
        JLabel l1 = new JLabel("DId");
        l1.setBounds(20,160,100,20);
        add(l1);
        
        JLabel l2 = new JLabel("Name");
        l2.setBounds(120,160,100,20);
        add(l2);
        
        JLabel l3 = new JLabel("Age");
        l3.setBounds(260,160,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("Gender");
        l4.setBounds(340,160,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("Company");
        l5.setBounds(460,160,100,20);
        add(l5);
        JLabel l6 = new JLabel("Model");
        l6.setBounds(565,160,100,20);
        add(l6);
         JLabel l7 = new JLabel("Available");
        l7.setBounds(660,160,100,20);
        add(l7);
        
         JLabel l8 = new JLabel("Location");
        l8.setBounds(780,160,100,20);
        add(l8);
        
        JLabel l9 = new JLabel("Username");
        l9.setBounds(880,160,100,20);
        add(l9);
        
        table = new JTable();
        table.setBounds(0,200,1000,300);
        add(table);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from Drivrs");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);  
        submit.setBounds(300,520,120,30);
        submit.addActionListener(this);
        add(submit);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);  
        back.setBounds(500,520,120,30);
        back.addActionListener(this);
        add(back);
        
         setBounds(550,270,1000,700);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
              try {
          String query1="select * from Drivrs where model='"+typeofcar.getSelectedItem()+"'";
          String query2 = "SELECT * FROM Drivrs WHERE available  = 'Available' AND model = '" + typeofcar.getSelectedItem() + "'";
             Conn c = new Conn();
             ResultSet rs;
             if(available.isSelected()){
                   rs = c.s.executeQuery(query2);
             }
             else{
                   rs = c.s.executeQuery(query1);
             }
             table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        } 
          }
        else{
             setVisible (false);
            new Reception();
        }
    }
    
    public static void main(String args[]){
        new Pickupservice();
    }
}
