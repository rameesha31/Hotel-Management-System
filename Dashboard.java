package hotel.management.system;
import java.awt.*;
import javax.swing.*; 
import java.sql.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
Dashboard(){
    setBounds(0,0,1950,1300);
    setLayout(null);
//     ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
//     Image i2 = i1.getImage().getScaledInstance(1950, 1200, Image.SCALE_SMOOTH);
//ImageIcon i3 = new ImageIcon(i2);
//JLabel image = new JLabel(i3);
//image.setBounds(0, 0, 1950, 1200);
//add(image);

ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Hint.jpg"));
JLabel image=new JLabel(i1);
image.setBounds(0,0,1900,1000);
add(image);


//JLabel text = new JLabel("THE KING HOTEL WELCOMES YOU"); 
//text.setBounds(330, 100, 1000, 50);
//text.setFont(new Font("Tahoma",Font.PLAIN,46));
// text.setForeground(Color.WHITE);
//image.add(text);

JMenuBar mb =new JMenuBar();
mb.setBounds(0,0, 1950, 30);
image.add(mb);  

JMenu hotel =new JMenu("Hotel Management");
 hotel.setForeground(Color.RED);
 hotel.setFont(new Font("Tahoma",Font.PLAIN,20));
 mb.add(hotel);
 
   JMenuItem reception =new JMenuItem("Reception");
    reception.addActionListener(this);
    reception.setFont(new Font("Tahoma",Font.PLAIN,16));
   hotel.add(reception);
   
    JMenuItem admin =new JMenuItem("Admin");
    admin.setFont(new Font("Tahoma",Font.PLAIN,16));
    admin.addActionListener(this);
   hotel.add(admin);
 

//text.setForeground(Color.WHITE);


        setVisible(true);
}    
  public void actionPerformed(ActionEvent ae) {
       if (ae.getActionCommand().equals("Admin")) {
        try {
            new Login();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database");
        }
    }

       else if(ae.getActionCommand().equals("Reception")){
          new Reception();
      }
  
  }
    public static void main(String[] arg){
        new Dashboard();

}
    
}