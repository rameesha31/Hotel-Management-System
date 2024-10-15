
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

public class Updatecheck extends JFrame implements ActionListener {
     JLabel text,lblid,lblnumber,lblname,lblcheck,lblpaid,lblpending;
     Choice ccustomer;
      JButton back,check,update;
     JTextField tfnumber,tfname,tfcheck,tfpaid,tfpending;
    Updatecheck(){
        setLayout(null);
        
         text=new JLabel("Update Status");
        text.setFont(new Font("Tahoma",Font.PLAIN,16));
         text.setBounds(90,20,200,30);
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
            String query="select * from Customerss";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                ccustomer.add(rs.getString("numberid"));
            }
            
        }catch(Exception e){
        e.printStackTrace();}
        
        lblnumber = new JLabel(" Room No#");
        lblnumber.setBounds(30,120,100,20);
        lblnumber.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblnumber);
        
        tfnumber=new JTextField();
        tfnumber.setBounds(200,120,150,25);
        add(tfnumber);
        
        lblname = new JLabel(" Name");
        lblname.setBounds(30,160,100,20);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblname);
        
        tfname=new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);
        
        lblcheck = new JLabel("Check-intime");
        lblcheck.setBounds(30,200,100,20);
        lblcheck.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblcheck);
        
        tfcheck=new JTextField();
        tfcheck.setBounds(200,200,150,25);
        add(tfcheck);
        
        lblpaid = new JLabel("Paid Amount");
        lblpaid.setBounds(30,240,100,20);
        lblpaid.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblpaid);
        
        tfpaid=new JTextField();
        tfpaid.setBounds(200,240,150,25);
        add(tfpaid);
        
        lblpending= new JLabel("Pending Amount");
        lblpending.setBounds(30,280,120,20);
        lblpending.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblpending);
        
        tfpending=new JTextField();
        tfpending.setBounds(200,280,150,25);
        add(tfpending);
        
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);  
        check.setBounds(30,340,100,30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);  
        update.setBounds(150,340,100,30);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);  
        back.setBounds(270,340,100,30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400,50,500,300);
        add(image);
      
        getContentPane().setBackground(Color.WHITE);
    setBounds(550,270,980,500);
          setVisible(true);
    }
     public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==check){
        String id=ccustomer.getSelectedItem();
        String query="select * from Customerss where numberid=+'"+id+"'";
          try {
            Conn c=new Conn();
              ResultSet rs = c.s.executeQuery(query);
              while(rs.next()){
                  tfnumber.setText(rs.getString("roomnumber"));
                  tfname.setText(rs.getString("name"));
                  tfcheck.setText(rs.getString("checkintime"));
                  tfpaid.setText(rs.getString("deposit"));   
              }
              String query2="select *from Roms where roomnumber= '"+tfnumber.getText()+"'";
               ResultSet rs1 = c.s.executeQuery(query2);
               
                while(rs1.next()){
                    String price=rs1.getString("price");
                    int amountpaid=Integer.parseInt(price)-Integer.parseInt(tfpaid.getText());
                  tfpending.setText(""+amountpaid);       
         }
          }
        catch(Exception e){
            e.printStackTrace();
        } 
        }      
     else if(ae.getSource()==update){
         
         String number=ccustomer.getSelectedItem();
         String room=tfnumber.getText();
         String name=tfname.getText();
         String checkin=tfname.getText();
         String deposit=tfpaid.getText();
         try{
             Conn c=new Conn();
            c.s.executeUpdate("update Customerss set roomnumber ='"+room+"',name ='"+name+"',checkintime ='"+checkin+"',deposit ='"+deposit+"' where numberid='"+number+"'"); 
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
           new Updatecheck();
}
}
