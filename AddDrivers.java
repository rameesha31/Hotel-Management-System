package hotel.management.system;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AddDrivers extends JFrame implements ActionListener {
    private Conn conn;
    private Statement stmt;
    private ResultSet rs;
    JButton add, cancel;
    JTextField tfDId, tfname, tfage, tfcompany, tfmodel, tfloc;
    JComboBox gendercombo, availablecombo;
    Choice cbUsername;

    AddDrivers() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblDId = new JLabel("DId");
        lblDId.setBounds(60, 60, 120, 30);
        lblDId.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDId);

        tfDId = new JTextField();
        tfDId.setBounds(200, 60, 150, 30);
        add(tfDId);

        JLabel heading = new JLabel("Add Driver");
        heading.setBounds(150, 10, 200, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 100, 120, 30);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 100, 150, 30);
        add(tfname);

        JLabel lblage = new JLabel("Age");
        lblage.setBounds(60, 140, 120, 30);
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblage);

        tfage = new JTextField();
        tfage.setBounds(200, 140, 150, 30);
        add(tfage);

        JLabel lblclean = new JLabel("Gender");
        lblclean.setBounds(60, 180, 120, 30);
        lblclean.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblclean);

        String genderOptions[] = {"Male", "Female"};
        gendercombo = new JComboBox(genderOptions);
        gendercombo.setBounds(200, 180, 150, 30);
        gendercombo.setBackground(Color.WHITE);
        add(gendercombo);

        JLabel lblprice = new JLabel(" Car Company");
        lblprice.setBounds(50, 220, 120, 30);
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblprice);

        tfcompany = new JTextField();
        tfcompany.setBounds(200, 220, 150, 30);
        add(tfcompany);

        JLabel lblcm = new JLabel("Car Model");
        lblcm.setBounds(50, 260, 120, 30);
        lblcm.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcm);

        tfmodel = new JTextField();
        tfmodel.setBounds(200, 260, 150, 30);
        add(tfmodel);

        JLabel lblavailable = new JLabel("Available");
        lblavailable.setBounds(50, 300, 120, 30);
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblavailable);

        String DriverOptions[] = {"Available", "Busy"};
        availablecombo = new JComboBox(DriverOptions);
        availablecombo.setBounds(200, 300, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);

        JLabel lblloc = new JLabel("Location");
        lblloc.setBounds(50, 340, 120, 30);
        lblloc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblloc);

        tfloc = new JTextField();
        tfloc.setBounds(200, 340, 150, 30);
        add(tfloc);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 380, 120, 30);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblUsername);

        cbUsername = new Choice();
        try {
            Conn c = new Conn();
            String query = "select * from logins";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cbUsername.add(rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        cbUsername.setBounds(200, 380, 150, 30);
        add(cbUsername);

        add = new JButton("Add Driver");
        add.setBounds(60, 440, 130, 30);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("Back");
        cancel.setBounds(230, 440, 130, 30);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 400);
        add(image);

        setBounds(550, 270, 980, 650);
        setVisible(true);
    }

     public void actionPerformed(ActionEvent ae) {
       if (ae.getSource() == add) {
              String dId = tfDId.getText();
            String name = tfname.getText();
            String age = tfage.getText();
             String gender = (String)gendercombo.getSelectedItem();
              String company = tfcompany.getText();
               String model = tfmodel.getText();
                String available = (String)availablecombo.getSelectedItem();
                String location = tfloc.getText();
                   String selectedUsername =  cbUsername.getSelectedItem();
                   
                    // Validate if any field is empty
        if (dId.isEmpty()|| name.isEmpty() || age.isEmpty() || gender.isEmpty() || company.isEmpty() || model.isEmpty() || available.isEmpty() || location.isEmpty() || selectedUsername.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if validation fails
        }
              
              try{
                   Conn conn=new Conn();
                   Statement stmt=conn.createStatement();
                    String str = "INSERT INTO Drivrs (dId, name, age, gender, company, model, available, location, username) VALUES ('" + dId + "','" + name + "','" + age + "','" + gender + "','" + company + "','" + model + "','" + available + "','" + location + "','" + selectedUsername + "')";
                   stmt.executeUpdate(str);
              JOptionPane.showMessageDialog(null, " New Driver added successfully");
                setVisible(false);
                  new Admin();
              }
              catch (java.sql.SQLIntegrityConstraintViolationException duplicateKeyException) {
            JOptionPane.showMessageDialog(null, "Duplicate primary key. Please use a different Driver ID.", "Error", JOptionPane.ERROR_MESSAGE);
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
        new AddDrivers();
    }
    
}