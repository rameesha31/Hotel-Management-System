package hotel.management.system;

import java.awt.Choice;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener {

    JComboBox available;
    JLabel name, number, lblgender, country, roomnumber, lbltime, checkintime, lbldeposit,driver;
    JTextField tfname, tfnumber, tfcountry, tfdeposit;
    JRadioButton male, female;
    JButton add, back;
    Choice croom,cdriver;

    AddCustomer() {
        setLayout(null);
        getContentPane().setBackground(Color.white);
        setBounds(550, 270, 750, 600);

        JLabel text = new JLabel("New Customer Form");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(text);

        JLabel lblid = new JLabel("Document");
        lblid.setBounds(35, 80, 100, 20);
        lblid.setForeground(Color.BLACK);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblid);

        String str[] = { "Passport", "Driving Licence", "Cnic" };
        available = new JComboBox(str);
        available.setBounds(200, 80, 150, 25);
        available.setBackground(Color.WHITE);
        add(available);

        number = new JLabel("Number#id");
        number.setBounds(35, 120, 100, 20);
        number.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(number);

        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        add(tfnumber);

        name = new JLabel("Name");
        name.setBounds(35, 160, 100, 20);
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(name);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        lblgender = new JLabel("Gender");
        lblgender.setBounds(35, 200, 100, 20);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        male = new JRadioButton("Male");
        male.setBounds(200, 200, 70, 30);
        male.setFont(new Font("Tahoma", Font.PLAIN, 14));
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(270, 200, 70, 30);
        female.setFont(new Font("Tahoma", Font.PLAIN, 14));
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        country = new JLabel("Country");
        country.setBounds(35, 240, 100, 20);
        country.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(country);

        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);

        roomnumber = new JLabel("Alloc Room");
        roomnumber.setBounds(35, 280, 100, 20);
        roomnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(roomnumber);

        croom = new Choice();
        try {
            Conn c = new Conn();
            String query = "select * from Romms where availability = 'Available'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                croom.add(rs.getString("roomnumber"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        croom.setBounds(200, 280, 150, 25);
        add(croom);

        lbltime = new JLabel("Check-In");
        lbltime.setBounds(35, 320, 100, 20);
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbltime);

        Date d = new Date();
        // Assuming 'd' is your Date object
        Timestamp checkinTime = new Timestamp(d.getTime());

        checkintime = new JLabel(" " + checkinTime);
        checkintime.setBounds(200, 320, 150, 25);
        checkintime.setFont(new Font("Tahoma", Font.PLAIN, 11));
        add(checkintime);

        lbldeposit = new JLabel("Deposit");
        lbldeposit.setBounds(35, 360, 100, 20);
        lbldeposit.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldeposit);

        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        add(tfdeposit);
        
          driver = new JLabel("Driver");
        driver.setBounds(35, 400, 100, 20);
        driver.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(driver);

        cdriver = new Choice();
        try {
            Conn c = new Conn();
            String query = "select * from Drivrs where available = 'Available'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cdriver.add(rs.getString("dId"));
            }
         
     // ResultSet rs1 = c.s.executeQuery(query1);

          



        } catch (Exception e) {
            e.printStackTrace();
        }

        cdriver.setBounds(200, 400, 150, 25);
        add(cdriver);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 300, 400);
        add(image);

        add = new JButton("Add Customer");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(40, 450, 120, 30);
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(200, 450, 120, 30);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    // Inside your actionPerformed method after validating and processing customer data
     public void actionPerformed(ActionEvent ae){
try {
    Conn c = new Conn();
    String documentTypeValue = (String) available.getSelectedItem();
    String numberId = tfnumber.getText();
    String name = tfname.getText();
    String gender = male.isSelected() ? "Male" : "Female";
    String country = tfcountry.getText();
    String roomNumber = croom.getSelectedItem();
    String checkinTime = getCheckinTime();
    String deposit = tfdeposit.getText();
    String driverId = cdriver.getSelectedItem(); // Assuming cdriver is your Choice for selecting driverId

    // Construct the SQL query with a join to fetch the driver name
    String query = "INSERT INTO Customs (document, numberid, name, gender, country, roomnumber, checkintime, deposit, dId, dName) " +
                   "SELECT '" + documentTypeValue + "', '" + numberId + "', '" + name + "', '" + gender + "', '" + country + "', '" + roomNumber + "', '" + checkinTime + "', '" + deposit + "', Drivrs.dId, Drivrs.name " +
                   "FROM Drivrs " +
                   "WHERE Drivrs.dId = '" + driverId + "'";

    // Execute the query
    c.s.executeUpdate(query);

    // Update room availability
    String updateRoomQuery = "UPDATE Romms SET availability = 'Occupied' WHERE roomnumber = '" + roomNumber + "'";
    c.s.executeUpdate(updateRoomQuery);

    // Update driver availability
    String updateDriverQuery = "UPDATE Drivrs SET available = 'Occupied' WHERE dId = '" + driverId + "'";
    c.s.executeUpdate(updateDriverQuery);

    JOptionPane.showMessageDialog(null, "New customer added successfully");
    setVisible(false);
    new Reception();

} catch (SQLException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
}}


    private String getCheckinTime() {
        Timestamp checkinTime = new Timestamp(System.currentTimeMillis());
        return " " + checkinTime;
    }

    public static void main(String args[]) {
        new AddCustomer();
    }
}