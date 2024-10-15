package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    private JTextField username, password;
    private JButton login, cancel;

    public Login() throws SQLException {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel user = new JLabel("Username");
        user.setBounds(40, 30, 100, 30);
        add(user);

        username = new JTextField();
        username.setBounds(150, 30, 200, 30);
        add(username);

        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 100, 100, 30);
        add(pass);

        password = new JTextField();
        password.setBounds(150, 100, 200, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(40, 170, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 170, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        setBounds(550, 270, 600, 300);
        setVisible(true);

        // Initialize conn and stmt
        conn = connect();
        stmt = conn.createStatement();
    }

    public Connection connect() throws SQLException {
        try {
            // Provide your database connection details
            String url = "jdbc:mysql://localhost:3306/hotel";
            String user = "root";
            String pass = "fatu@123";

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to the database", e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String user = username.getText();
            String pass = password.getText();

            try {
                String query = "SELECT * FROM logins WHERE username='" + user + "' AND password='" + pass + "'";
                rs = stmt.executeQuery(query);

                if (rs.next()) {
                  new Admin().setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } else if (ae.getSource() == cancel) {
            setVisible(false);
             new Reception();
        }
    }

    public static void main(String[] arg) {
        try {
            new Login();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database");
        }
    }
}