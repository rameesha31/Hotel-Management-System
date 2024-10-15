package hotel.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn implements AutoCloseable {
    Connection conn;
    Statement s;

     Conn() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "fatu@123");
               s=conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
     
      public Connection getConnection() {
        return conn;
    }

    public Statement createStatement() {
        return s;
    }

    public void close() {
        try {
            if (s != null) {
                s.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // Handle exceptions appropriately, such as logging or throwing a custom exception
            e.printStackTrace();
        }
    }
//  public Connection getConnection() {
//        return conn;
//    }
//    public Statement createStatement() throws SQLException {
//        return conn.createStatement();
//    }
     public static void main(String[] args) {
        Conn app = new Conn();
        //app.connect();
    }

    void rollback() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}