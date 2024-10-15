package hotel.management.system; 
import java.awt.*;
import javax.swing.*; 
import java.awt.event.*;
import java.sql.SQLException;

public class HotelManagementSystem extends JFrame implements ActionListener { 

public HotelManagementSystem() {
   setSize(1366,565);	
setLocation(100,100);
setBounds(0,0,1900,1000);
setLayout(null);
//ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/forty.jpg"));
//JLabel image=new JLabel(i1);
//image.setBounds(0,0,1900,1000);
//add(image);

ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/forty.jpg"));
    Image i2=i1.getImage().getScaledInstance(1900,1000,Image.SCALE_DEFAULT);
    ImageIcon i3= new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(0,0,1900,1000);
    add(image);

JLabel text =new JLabel("Hotel Management System");
text.setBounds(650,830,1000,90);
text.setForeground(Color.YELLOW);
text.setFont(new Font("serif",Font.PLAIN,50));
image.add(text);
JButton next=new JButton("Next");
next.setBounds(1550,850,150,50);
next.setBackground(Color.YELLOW);
next.setForeground(Color.BLACK);
next.addActionListener(this);
next.setFont(new Font("serif",Font.PLAIN,25));
image.add(next);
setVisible(true);

while(true){
    text.setVisible(false );
    try{
        Thread.sleep(500);
    }
    catch(Exception e){
        e.printStackTrace();
    }
     text.setVisible(true );
      try{
        Thread.sleep(500);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

}
public void actionPerformed(ActionEvent ae) {
    setVisible(false);
     new Dashboard();
}



public static void main(String[] args) {
new HotelManagementSystem(); 


}
}