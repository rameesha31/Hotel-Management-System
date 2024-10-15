
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
public class searchroom extends JFrame implements ActionListener{
    JTable table;
    JLabel text;
    JButton back,submit;
     JComboBox bedType;
     JCheckBox available;
    searchroom(){
        setLayout(null);
        getContentPane().setBackground(Color.white);
        
          text=new JLabel("Search for Room");
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
         text.setBounds(400,30,200,30);
          add(text);
        
           JLabel lblbed=new JLabel("Bed Type");
        lblbed.setFont(new Font("Tahoma",Font.PLAIN,20));
         lblbed.setBounds(50,100,100,20);
          add(lblbed);
       
        String str[]={"Single bed","Double bed"};
        bedType = new JComboBox(str);
        bedType.setBounds(150,100,150,25);
        bedType.setBackground(Color.WHITE);
        add(bedType);
        
        available =new JCheckBox("Only Display Available");
         available.setBounds(650,100,150,25);
        available.setBackground(Color.WHITE);
        add(available);
          
        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(50,160,100,20);
        add(l1);
        
        JLabel l2 = new JLabel("Availibilty");
        l2.setBounds(240,160,100,20);
        add(l2);
        
        JLabel l3 = new JLabel("Cleaning_ Status");
        l3.setBounds(390,160,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("Price");
        l4.setBounds(560,160,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("Type");
        l5.setBounds(750,160,100,20);
        add(l5);
        
         JLabel l6 = new JLabel("Username");
        l6.setBounds(870,160,100,20);
        add(l6);
        
        table = new JTable();
        table.setBounds(0,200,1000,300);
        add(table);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from Romms");
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
          String query1="select * from Romms where bed_type='"+bedType.getSelectedItem()+"'";
          String query2 = "SELECT * FROM Romms WHERE availability = 'Available' AND bed_type = '" + bedType.getSelectedItem() + "'";
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
        new searchroom();
    }
}
