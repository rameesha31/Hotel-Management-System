package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Admin extends JFrame implements ActionListener {
    JButton driver, employees, rooms,back;

    Admin() {
        setLayout(new BorderLayout());

        // Add image to the north (top) of the layout
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/adm1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, BorderLayout.NORTH);

        // Create a panel for buttons and add it to the center of the layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Adjust the layout manager and spacing

        driver = new JButton(" Add Drivers");
        driver.setPreferredSize(new Dimension(150, 30));
        driver.setBackground(Color.BLACK);
        driver.setForeground(Color.WHITE);
        driver.addActionListener(this);
        buttonPanel.add(driver);

        employees = new JButton(" Add Employees");
        employees.setPreferredSize(new Dimension(150, 30));
        employees.setBackground(Color.BLACK);
        employees.setForeground(Color.WHITE);
        employees.addActionListener(this);
        buttonPanel.add(employees);

        rooms = new JButton("Add Rooms");
        rooms.setPreferredSize(new Dimension(150, 30));
        rooms.setBackground(Color.BLACK);
        rooms.setForeground(Color.WHITE);
        rooms.addActionListener(this);
        buttonPanel.add(rooms);
        
         back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 30));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        buttonPanel.add(back);

        add(buttonPanel, BorderLayout.CENTER);

        getContentPane().setBackground(Color.WHITE);
        setBounds(550, 270, 850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
         if(ae.getSource()==driver){
              setVisible(false);
              new AddDrivers();
          }
          else if(ae.getSource()==employees){
              setVisible(false);
             new AddEmployee();
          }
           else if(ae.getSource()==rooms){
              setVisible(false);
               new AddRooms();
          }
           else{
               setVisible(false);
               new Dashboard();
           }
    }

    public static void main(String args[]) {
        new Admin();
    }
}
