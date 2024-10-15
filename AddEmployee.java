package hotel.management.system;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AddEmployee extends JFrame implements ActionListener {
    private Conn conn;
    private Statement stmt;
    private ResultSet rs;
    
    JTextField tfname, tfemail, tfphone, tfage, tfsalary, tfcnic, tfeid;
    JRadioButton rbmale, rbfemale;
    JButton submit, back;
    JComboBox cbjob;
    Choice  cbDepartment, cbUsername;


    AddEmployee() {
        setLayout(null);

         JLabel lbleid = new JLabel("EId");
        lbleid.setBounds(60, 30, 120, 30);
        lbleid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lbleid);

        tfeid = new JTextField();
        tfeid.setBounds(200, 30, 180, 30);
        add(tfeid);
        
       JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 80, 120, 30);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 80, 180, 30);
        add(tfname);

        JLabel lblgender = new JLabel("GENDER"); 
        lblgender.setBounds(60, 130, 120, 30);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200, 130, 70, 30);
        rbmale.setBackground(Color.WHITE);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(280, 130, 100, 30);
        rbfemale.setBackground(Color.WHITE);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(rbfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);

        JLabel lbljob = new JLabel("JOB"); 
        lbljob.setBounds(60, 180, 120, 30);
        lbljob.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lbljob);

        String str[] = {"Front Desk Clerks", "Porters", "HouseKeeping", "Kitchen Staffs", "Rooms Service", "Chefs", "Waiters/Waitress", "Manager", "Accountant"};
        cbjob = new JComboBox(str);
        cbjob.setBounds(200, 180, 180, 30);
                cbjob.setBackground(Color.WHITE);
        add(cbjob);

        JLabel lblage = new JLabel("AGE"); 
        lblage.setBounds(60, 230, 120, 30);
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblage);

        tfage = new JTextField();
        tfage.setBounds(200, 230, 180, 30);
        add(tfage);

        JLabel lblphone = new JLabel("PHONE-NO"); 
        lblphone.setBounds(60, 280, 120, 30);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(200, 280, 180, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("E-MAIL"); 
        lblemail.setBounds(60, 330, 120, 30);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 330, 180, 30);
        add(tfemail);

        JLabel lblcnic = new JLabel("CNIC"); 
        lblcnic.setBounds(60, 380, 120, 30);
        lblcnic.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblcnic);

        tfcnic = new JTextField();
        tfcnic.setBounds(200, 380, 180, 30);
        add(tfcnic);
        
        // Add JLabels for JComboBox components
JLabel lblDepartment = new JLabel("Department");
lblDepartment.setBounds(60, 430, 120, 30);
lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 20));
add(lblDepartment);

cbDepartment = new Choice();
        try{
            Conn c = new Conn();
            String query="select * from depts";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                cbDepartment.add(rs.getString("DId"));
            }
            
        }catch(Exception e){
        e.printStackTrace();}
        
        cbDepartment.setBounds(200,430,180,30);
        add(cbDepartment);

JLabel lblUsername = new JLabel("Username");
lblUsername.setBounds(60, 480,120, 30);
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
        
        cbUsername.setBounds(200,480,180,30);
        add(cbUsername);

      JLabel lblsalary = new JLabel("SALARY");
        lblsalary.setBounds(60, 530, 120, 30);
        lblsalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(200, 530, 180, 30);
        add(tfsalary);



        submit = new JButton("SUBMIT");
        submit.setBounds(150, 600,120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.setBounds(300, 600, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 60, 450, 370);
        add(image);

        getContentPane().setBackground(Color.WHITE);
        setBounds(550, 270, 850, 720);
        setVisible(true);
    }
   
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == submit) {
        String eid = tfeid.getText();
        String name = tfname.getText();
        String age = tfage.getText();
        String salary = tfsalary.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String cnic = tfcnic.getText();
        String gender = null;
        if (rbmale.isSelected()) {
            gender = "Male";
        } else if (rbfemale.isSelected()) {
            gender = "Female";
        }

        String job = (String) cbjob.getSelectedItem();
        // Use the selected values directly from JComboBoxes
        String selectedDepartment =  cbDepartment.getSelectedItem();
        String selectedUsername =  cbUsername.getSelectedItem();
        
         // Check for empty fields
        if (eid.equals("") || name.equals("") || age.equals("") || salary.equals("") || phone.equals("") || email.equals("") || cnic.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the columns", "Error", JOptionPane.ERROR_MESSAGE);
            return;}

        // Use these values in your INSERT query
        String query = "INSERT INTO employs VALUES('" + eid + "','" + name + "','" + age + "','" + gender + "','" + job + "','" + salary + "','" + phone + "','" + email + "','" + cnic + "', '" + selectedDepartment + "', '" + selectedUsername + "')";

        try {
            // Create a connection using the Conn class
            Conn conn = new Conn();
            // Get the statement from the connection
            Statement stmt = conn.createStatement();
            
            // Use the existing connection and statement
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Employee added successfully");
            setVisible(false);
            new Admin();
        } catch (java.sql.SQLIntegrityConstraintViolationException duplicateKeyException) {
            JOptionPane.showMessageDialog(null, "Duplicate primary key. Please use a different EId.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        setVisible(false);
        new Admin();
    }
}

               


              
              
     public static void main(String[] args) {
           new AddEmployee();
}
    
}
       

        
