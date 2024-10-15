package hotel.management.system;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Reception extends JFrame implements ActionListener {
  JButton newcustomer,rooms,department,employee,managers,customers,search,checkout,update,roomupdate,pickup,admin,feedback,logout;
    Reception(){
         getContentPane().setBackground(Color.WHITE);
         setLayout(null);
         
  newcustomer=new JButton("New Customer Form");
newcustomer.setBounds(20,30,200,30);
newcustomer.setBackground(Color.BLACK);
newcustomer.setForeground(Color.WHITE);
newcustomer.addActionListener(this);
add(newcustomer);

 rooms=new JButton("Rooms");
rooms.setBounds(20,70,200,30);
rooms.setBackground(Color.BLACK);
rooms.setForeground(Color.WHITE);
rooms.addActionListener(this);
add(rooms);        
        
   department=new JButton("Department");
department.setBounds(20,110,200,30);
department.setBackground(Color.BLACK);
department.setForeground(Color.WHITE);
department.addActionListener(this);
add(department);  

  employee=new JButton("All Employees");
employee.setBounds(20,150,200,30);
employee.setBackground(Color.BLACK);
employee.setForeground(Color.WHITE);
employee.addActionListener(this);
add(employee);  

 customers=new JButton("Customer info");
customers.setBounds(20,190,200,30);
customers.setBackground(Color.BLACK);
customers.setForeground(Color.WHITE);
customers.addActionListener(this);
add(customers);  
        
 managers=new JButton("Manager info");
managers.setBounds(20,230,200,30);
managers.setBackground(Color.BLACK);
managers.setForeground(Color.WHITE);
managers.addActionListener(this);
add(managers);   
      
  checkout=new JButton("Checkout");
checkout.setBounds(20,270,200,30);
checkout.setBackground(Color.BLACK);
checkout.setForeground(Color.WHITE);
checkout.addActionListener(this);
add(checkout);

  update=new JButton("Update Status");
update.setBounds(20,310,200,30);
update.setBackground(Color.BLACK);
update.setForeground(Color.WHITE);
update.addActionListener(this);
add(update);

  roomupdate=new JButton("Update Room Status");
roomupdate.setBounds(20,350,200,30);
roomupdate.setBackground(Color.BLACK);
roomupdate.setForeground(Color.WHITE);
roomupdate.addActionListener(this);
add(roomupdate);

 pickup=new JButton("Pickup Services");
pickup.setBounds(20,390,200,30);
pickup.setBackground(Color.BLACK);
pickup.setForeground(Color.WHITE);
pickup.addActionListener(this);
add(pickup);

 admin=new JButton("Only-For Admin");
admin.setBounds(20,430,200,30);
admin.setBackground(Color.BLACK);
admin.setForeground(Color.WHITE);
admin.addActionListener(this);
add(admin);

 search=new JButton(" Search Room");
search.setBounds(20,470,200,30);
search.setBackground(Color.BLACK);
search.setForeground(Color.WHITE);
search.addActionListener(this);
add(search);

 feedback=new JButton(" Feedback");
feedback.setBounds(20,510,200,30);
feedback.setBackground(Color.BLACK);
feedback.setForeground(Color.WHITE);
feedback.addActionListener(this);
add(feedback);

 logout=new JButton("Logout");
logout.setBounds(20,550,200,30);
logout.setBackground(Color.BLACK);
logout.setForeground(Color.WHITE);
logout.addActionListener(this);
add(logout);

ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
 Image i2=i1.getImage().getScaledInstance(500,450,Image.SCALE_DEFAULT);
  ImageIcon i3=new ImageIcon(i2);
JLabel image=new JLabel(i3);
image.setBounds(250,-25,600,570);
add(image);



         setBounds(550,270,900,670);
          setVisible(true);
    }
       public void actionPerformed(ActionEvent ae){
          if(ae.getSource()==newcustomer){
              setVisible(false);
              new AddCustomer();
          }
          else if(ae.getSource()==rooms){
              setVisible(false);
              new Allrooms();
          }
           else if(ae.getSource()==department){
              setVisible(false);
              new department();
          }
          else if(ae.getSource()==employee){
              setVisible(false);
              new Employeeinfo();
          }
           else if(ae.getSource()==managers){
              setVisible(false);
               new Manager();
          }
          else if(ae.getSource()==customers){
              setVisible(false);
               new customerinfo();
          }
           else if(ae.getSource()==search){
              setVisible(false);
               new searchroom();
          }
          
          else if(ae.getSource()==update){
              setVisible(false);
               new Updatecheck();
          }
          else if(ae.getSource()==roomupdate){
              setVisible(false);
              new Updateroom();
          }
          else if(ae.getSource()==pickup){
              setVisible(false);
               new Pickupservice();
          }
           else if(ae.getSource()==feedback){
              setVisible(false);
               new feedback();
          }
          else if(ae.getSource()==checkout){
              setVisible(false);
                 new Checkout();
          }
          else if(ae.getSource()==logout){
              setVisible(false);
                System.exit(0);
          }

else if(ae.getSource()==admin){
              setVisible(false);
         //if (ae.getActionCommand().equals("Admin")) {
        try {
            new Login();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database");
        }
    }
          }
    
       public static void main(String[] args) {
           new Reception();
}
  
}