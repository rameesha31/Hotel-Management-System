
        
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

public class customerinfo extends JFrame implements ActionListener{
    JTable table;
    JButton back;
    customerinfo(){
        setLayout(null);
        getContentPane().setBackground(Color.white);
      //  setBounds(550,270,1050,600);
        
        JLabel l1 = new JLabel("Document-type");
        l1.setBounds(20,10,100,20);
        add(l1);
        
        JLabel l2 = new JLabel("NumberID");
        l2.setBounds(135,10,100,20);
        add(l2);
        
        JLabel l3 = new JLabel("Name");
        l3.setBounds(230,10,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("Gender");
        l4.setBounds(340,10,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("Country");
        l5.setBounds(430,10,100,20);
        add(l5);
        
        JLabel l6 = new JLabel("room#no");
        l6.setBounds(530,10,100,20);
        add(l6);
        
        JLabel l7= new JLabel("Check-intime");
        l7.setBounds(620,10,100,20);
        add(l7);
        
        JLabel l8 = new JLabel("deposit");
        l8.setBounds(720,10,100,20);
        add(l8);
        
        JLabel l9 = new JLabel("DID");
        l9.setBounds(830,10,100,20);
        add(l9);
        
         JLabel l10 = new JLabel("DName");
        l10.setBounds(920,10,100,20);
        add(l10);
        
        table = new JTable();
        table.setBounds(0,40,1000,400);
        add(table);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from Customs");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);  
        back.setBounds(420,500,120,30);
        back.addActionListener(this);
        add(back);
        
        setBounds(550,270,1000,600);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
//        try {
            setVisible (false);
            new Reception();
//        }catch(Exception s){
//            s.printStackTrace();
//        }
    }
    
    public static void main(String args[]){
        new customerinfo();
    }
}
