package ElectricityBilingSystem;


import java.awt.*;

import javax.swing.*;

public class Splash extends JFrame {
    Splash() {
        // Use the absolute path for the image
        String imagePath = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\splash\\Splash.jpg";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image imageOne=imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
       ImageIcon imageIcon2=new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);
        
        setSize(600, 400);
        setLocation(500, 200); // left-right shift x, y axis
        setVisible(true);
        try {
        	Thread.sleep(3000);
        	setVisible(false);
        	new Login();
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}

