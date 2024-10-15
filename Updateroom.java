
package hotel.management.system;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import net.proteanit.sql.DbUtils;

public class Updateroom extends JFrame implements ActionListener {
     JLabel text,lblid,lblnumber,lblname,lblcheck,lblpaid,lblpending;
     Choice ccustomer;
      JButton back,check,update;
     JTextField tfnumber,tfavailable,tfclean,tfpaid,tfpending;
    Updateroom(){
        setLayout(null);
        
         text=new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma",Font.PLAIN,16));
         text.setBounds(30,20,250,30);
          add(text);
          
        lblid=new JLabel("Customer ID");
        lblid.setFont(new Font("Tahoma",Font.PLAIN,16));
         lblid.setBounds(30,80,120,20);
          add(lblid);
          
           ccustomer = new Choice();
          ccustomer.setBounds(200,80,150,25);
        add(ccustomer);
        
        try{
            Conn c = new Conn();
            String query="select * from Customs";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                ccustomer.add(rs.getString("numberid"));
            }
            
        }catch(Exception e){
        e.printStackTrace();}
        
        lblnumber = new JLabel(" Room No#");
        lblnumber.setBounds(30,130,100,20);
        lblnumber.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblnumber);
        
        tfnumber=new JTextField();
        tfnumber.setBounds(200,130,150,25);
        add(tfnumber);
        
        lblname = new JLabel(" Availability");
        lblname.setBounds(30,180,100,20);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblname);
        
        tfavailable=new JTextField();
         tfavailable.setBounds(200,180,150,25);
        add(tfavailable);
        
        lblcheck = new JLabel("Cleaning-status");
        lblcheck.setBounds(30,230,120,20);
        lblcheck.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblcheck);
        
        tfclean=new JTextField();
        tfclean.setBounds(200,230,150,25);
        add(tfclean);
        
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);  
        check.setBounds(30,300,100,30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);  
        update.setBounds(150,300,100,30);
        update.addActionListener(this);
        add(update);
        
         back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);  
        back.setBounds(270,300,100,30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
 Image i2=i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
  ImageIcon i3=new ImageIcon(i2);
JLabel image=new JLabel(i3);
image.setBounds(400,50,500,300);
add(image);
      
        getContentPane().setBackground(Color.WHITE);
    setBounds(550,270,980,450);
    
          setVisible(true);
    }
     public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==check){
        String id=ccustomer.getSelectedItem();
        String query="select * from Customs where numberid=+'"+id+"'";
          try {
            Conn c=new Conn();
              ResultSet rs = c.s.executeQuery(query);
              while(rs.next()){
                  tfnumber.setText(rs.getString("roomnumber"));  
              }
              String query2="select *from Romms where roomnumber= '"+tfnumber.getText()+"'";
               ResultSet rs1 = c.s.executeQuery(query2);
                while(rs1.next()){
                     tfavailable.setText(rs1.getString("availability"));    
                     tfclean.setText(rs1.getString("cleaning_status"));  
         }
          }
        catch(Exception e){
            e.printStackTrace();
        } 
        
        }      
     else if(ae.getSource()==update){    
         String number=ccustomer.getSelectedItem();
         String room=tfnumber.getText();
         String available=tfavailable.getText();
         String clean =tfclean.getText();
         try{
             Conn c=new Conn();
           c.s.executeUpdate("update Romms set availability='" + available + "', cleaning_status='" + clean + "' where roomnumber='" + room + "'");
       JOptionPane.showMessageDialog(null, "Data Updated Successfully");
       setVisible (false);
            new Reception();
         } 
          catch(Exception e){
            e.printStackTrace();
        } 
          
     }
        else{
             setVisible (false);
            new Reception();
        }
    }
    
     public static void main(String[] args) {
           new Updateroom();
}
}
