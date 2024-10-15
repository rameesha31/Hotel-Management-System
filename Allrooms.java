
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
public class Allrooms extends JFrame implements ActionListener{
    JTable table;
    JButton back;
    Allrooms(){
        setLayout(null);
        getContentPane().setBackground(Color.white);
        setBounds(550,270,1050,600);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2=i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,0,600,600);
        add(image);
        
        JLabel l1 = new JLabel("Room#no");
        l1.setBounds(10,10,100,20);
        add(l1);
        
        JLabel l2 = new JLabel("Availibilty");
        l2.setBounds(100,10,100,20);
        add(l2);
        
        JLabel l3 = new JLabel("Status");
        l3.setBounds(190,10,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("Price");
        l4.setBounds(270,10,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("Type");
        l5.setBounds(340,10,100,20);
        add(l5);
        
         JLabel l6 = new JLabel("Username");
        l6.setBounds(420,10,100,20);
        add(l6);
        
        table = new JTable();
        table.setBounds(0,40,500,400);
        add(table);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from Romms");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);  
        back.setBounds(200,500,120,30);
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
       try {
            setVisible (false);
            new Reception();
        }catch(Exception s){
           s.printStackTrace();
       }
    }
    
    public static void main(String args[]){
        new Allrooms();
    }
}
