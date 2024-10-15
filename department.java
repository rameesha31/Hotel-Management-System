package hotel.management.system;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class department extends JFrame implements ActionListener  {
    JButton back;
    JTable table;
    department(){
         getContentPane().setBackground(Color.WHITE);
        setLayout(null);

JLabel lb1 =new JLabel("DID");
lb1.setBounds(150,10,100,20);
add(lb1);
JLabel lb2 =new JLabel("NAME");
lb2.setBounds(350,10,100,20);
add(lb2);
JLabel lb3 =new JLabel("BUDGET");
lb3.setBounds(550,10,100,20);
add(lb3);

table=new JTable();
table.setBounds(20,50,700,350);
add(table);
try{
                  Conn conn=new Conn();
                   Statement stmt=conn.createStatement();
                   ResultSet rs= stmt.executeQuery("select * from depts");
                   table.setModel(DbUtils.resultSetToTableModel(rs));
                   }
 catch(Exception e){
                 e.printStackTrace();
               }
back=new JButton("Back");
back.setBounds(280,400,120,30);
back.setBackground(Color.BLACK);
back.setForeground(Color.WHITE);
 back.addActionListener(this);
add(back);
         setBounds(550,270,700,500);
          setVisible(true);
    }
     public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Reception();
     }
        public static void main(String[] args) {
           new department();
}
}