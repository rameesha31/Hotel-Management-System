package hotel.management.system;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
public class AddRooms extends JFrame implements ActionListener{
     private Conn conn;
     private Statement stmt;
    private ResultSet rs;
    JButton add,cancel;
    JTextField tfroom,tfprice;
    JComboBox typecombo,cleancombo,availablecombo;
     Choice  cbUsername;
    
    AddRooms(){
       getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
         JLabel heading = new JLabel("Add Rooms"); 
heading.setBounds(150, 20, 200, 30);
heading.setFont(new Font("Tahoma",Font.BOLD,18));
add(heading);
    
        JLabel lblroomno = new JLabel("Room Number"); 
lblroomno.setBounds(60, 80, 120, 30);
lblroomno.setFont(new Font("Tahoma",Font.PLAIN,16));
add(lblroomno);
    
  tfroom=new JTextField();
tfroom.setBounds(200, 80, 150, 30);
add(tfroom);
        
         JLabel lblavailable = new JLabel("Available"); 
lblavailable.setBounds(60, 130, 120, 30);
lblavailable.setFont(new Font("Tahoma",Font.PLAIN,16));
add(lblavailable);

String availableOptions[]={"Available","Occupied"};
  availablecombo=new JComboBox(availableOptions);
availablecombo.setBounds(200, 130, 150, 30);
 availablecombo.setBackground(Color.WHITE);
add(availablecombo);

      JLabel lblclean = new JLabel("Cleaning Status"); 
lblclean.setBounds(60, 180, 120, 30);
lblclean.setFont(new Font("Tahoma",Font.PLAIN,16));
add(lblclean);

String cleanOptions[]={"Cleaned","Dirty"};
  cleancombo=new JComboBox(cleanOptions);
cleancombo.setBounds(200, 180, 150, 30);
 cleancombo.setBackground(Color.WHITE);
add(cleancombo);

     JLabel lblprice = new JLabel(" Price"); 
lblprice.setBounds(60, 230, 120, 30);
lblprice.setFont(new Font("Tahoma",Font.PLAIN,16));
add(lblprice);
    
  tfprice=new JTextField();
tfprice.setBounds(200, 230, 150, 30);
add(tfprice);
        
 JLabel lbltype = new JLabel("Bed Type"); 
lbltype.setBounds(60, 280, 120, 30);
lbltype.setFont(new Font("Tahoma",Font.PLAIN,16));
add(lbltype);

String typeOptions[]={"Single Bed","Double Bed"};
  typecombo=new JComboBox(typeOptions);
typecombo.setBounds(200, 280, 150, 30);
 typecombo.setBackground(Color.WHITE);
add(typecombo);

JLabel lblUsername = new JLabel("Username");
lblUsername.setBounds(60, 330,120, 30);
lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
add(lblUsername);

cbUsername = new Choice();
        try{
            Conn c = new Conn();
            String query="select * from logins";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                cbUsername.add(rs.getString("username"));
            }
            
        }catch(Exception e){
        e.printStackTrace();}
        
        cbUsername.setBounds(200,330,150,30);
        add(cbUsername);

  add=new JButton("Add Rooms");
add.setBounds(60, 390, 130, 30);
add.setForeground(Color.WHITE);
 add.setBackground(Color.BLACK);
 add.addActionListener(this);
add(add);
        
          cancel=new JButton("Back");
cancel.setBounds(230, 390, 130, 30);
cancel.setForeground(Color.WHITE);
 cancel.setBackground(Color.BLACK);
 cancel.addActionListener(this);
add(cancel);

ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
JLabel image=new JLabel(i1);
image.setBounds(400,30,500,300);
add(image);

    setBounds(550,270,950,550);
          setVisible(true);
    }
     public void actionPerformed(ActionEvent ae) {
       if (ae.getSource() == add) {
            String roomnumber = tfroom.getText();
            String availability = (String)availablecombo.getSelectedItem();
             String status = (String)cleancombo.getSelectedItem();
              String price = tfprice.getText();
              String type = (String)typecombo.getSelectedItem();
               String selectedUsername =  cbUsername.getSelectedItem();
               
               if (roomnumber.isEmpty() || availability.isEmpty() || status.isEmpty() || price.isEmpty() || type.isEmpty() || selectedUsername.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if validation fails
        }

              
              try{
                   Conn conn=new Conn();
                   Statement stmt=conn.createStatement();
                  // String str = "Insert into room values('" + roomnumber +"','" + availability +"','" + status +"','" + price +"','" + type +"')";
              String str = "INSERT INTO Romms (roomnumber, availability, cleaning_status, price, bed_type, username) VALUES ('" + roomnumber + "','" + availability + "','" + status + "','" + price + "','" + type + "','" + selectedUsername + "')";

                  stmt.executeUpdate(str);
              JOptionPane.showMessageDialog(null, " New Room added successfully");
                setVisible(false);
                  new Admin();
              }
              catch (java.sql.SQLIntegrityConstraintViolationException duplicateKeyException) {
            JOptionPane.showMessageDialog(null, "Duplicate primary key. Please use a different RoomNumber.", "Error", JOptionPane.ERROR_MESSAGE);
        }
               catch(Exception e){
                 e.printStackTrace();
               }
       
              
              
              
        }
       else {
           setVisible(false);
            new Admin();
       }
           }
    
    public static void main(String[] arg) {
        new AddRooms();
    }
    
}