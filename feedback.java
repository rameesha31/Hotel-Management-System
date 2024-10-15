package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class feedback extends JFrame implements ActionListener {

    JTextField tfid, tfremark;
    JComboBox<String> ratingcombo;
    JButton submit, cancel;

    feedback() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel heading = new JLabel("FEEDBACK-FORM");
        heading.setBounds(150, 20, 200, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(heading);

        JLabel lblid = new JLabel("Number-Id");
        lblid.setBounds(60, 80, 120, 30);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblid);

        tfid = new JTextField();
        tfid.setBounds(200, 80, 150, 30);
        add(tfid);

        JLabel lblrating = new JLabel("Rating-Stars");
        lblrating.setBounds(60, 130, 120, 30);
        lblrating.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblrating);

        String ratingOptions[] = {"1", "2", "3", "4", "5"};
        ratingcombo = new JComboBox<>(ratingOptions);
        ratingcombo.setBounds(200, 130, 150, 30);
        ratingcombo.setBackground(Color.WHITE);
        add(ratingcombo);

        JLabel lblremark = new JLabel("Remarks");
        lblremark.setBounds(60, 180, 120, 30);
        lblremark.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblremark);

        tfremark = new JTextField();
        tfremark.setBounds(200, 180, 150, 60);
        add(tfremark);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/f1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(370, 0, 400, 450);
        add(image);

        submit = new JButton("Submit");
        submit.setBounds(100, 300, 130, 30);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Back");
        cancel.setBounds(250, 300, 130, 30);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);

        setBounds(550, 270, 750, 450);
        setVisible(true);
    }

   public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == submit) {
        try (Conn conn = new Conn()) {
            try (Connection connection = conn.getConnection()) {
                // Set autocommit to false
                connection.setAutoCommit(false);

                String numberid = tfid.getText();

                // Check if the numberid exists in the Customerss table
                if (isNumberIdValid(connection, numberid)) {
                    String rating = (String) ratingcombo.getSelectedItem();
                    int intRating = Integer.parseInt(rating);

                    // Call the stored procedure if the numberid is valid
                    callFeedbackProcedure(connection, numberid, intRating);

                    // Commit the transaction
                    connection.commit();

                    // Provide user feedback
                    JOptionPane.showMessageDialog(this, "Feedback submitted successfully!");
                } else {
                    // Provide user feedback if the numberid is not valid
                    JOptionPane.showMessageDialog(this, "Invalid Number-Id. Please enter a valid Number-Id.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                // Handle SQL exceptions
                e.printStackTrace();
                // Rollback the transaction in case of an exception
                if (conn != null) {
                    conn.rollback();
                }
                // Provide user feedback about the error
                JOptionPane.showMessageDialog(this, "Error submitting feedback. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            setVisible(false);
        new Reception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        setVisible(false);
        new Reception();
    }
}

private boolean isNumberIdValid(Connection connection, String numberid) {
    // Check if the numberid exists in the Customerss table
    String query = "SELECT * FROM Customs WHERE numberid = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, numberid);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next(); // If the result set has at least one row, the numberid is valid
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false in case of an exception
    }
}

   private void callFeedbackProcedure(Connection connection, String numberid, int rating) {
    // Call the stored procedure
    String storedProcedureCall = "{call feedbacks(?, ?)}";
    try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
        callableStatement.setString(1, numberid);
        callableStatement.setInt(2, rating);
        callableStatement.execute();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static void main(String[] arg) {
        new feedback();
    }
}
