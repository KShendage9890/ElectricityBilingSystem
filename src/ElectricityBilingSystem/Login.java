package ElectricityBilingSystem;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JTextField userText, passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login");
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel username = new JLabel("UserName");
        username.setBounds(300, 60, 100, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        JLabel loggin = new JLabel("Login In As ");
        loggin.setBounds(300, 140, 100, 20);
        add(loggin);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400, 140, 150, 20);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(330, 180, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460, 180, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("SignUp");
        signupButton.setBounds(400, 215, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        // Use the absolute path for the profile image
        String profileImagePath = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\profile.png";
        ImageIcon profileOne = new ImageIcon(profileImagePath);
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(50, 30, 250, 250); // Set the bounds to position the image
        profileLabel.setBounds(5,5, 250,250);
        add(profileLabel);

        setSize(640, 300);
        setLocation(400, 200); // left-right shift x, y axis
        setVisible(true);
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginButton) {
			String susername=userText.getText();
			String spassword=passwordText.getText();
			String suser=loginChoice.getSelectedItem();
			try {
			    database db = new database();
			    Connection con = db.getConnection();
			    String query = "SELECT * FROM Signup WHERE username=? AND password=? AND usertype=?";
			    PreparedStatement pstmt = con.prepareStatement(query);
			    pstmt.setString(1, susername);
			    pstmt.setString(2, spassword);
			    pstmt.setString(3, suser);
			    ResultSet rs = pstmt.executeQuery();
			    
			    if (rs.next()) {
			    	String meter=rs.getString("meter_no");
			        setVisible(false);
			        new main_class(suser,meter);
			    } else {
			        JOptionPane.showMessageDialog(null, "Invalid Login");
			    }

			    rs.close();  // Close ResultSet
			    pstmt.close(); // Close PreparedStatement
			    db.closeConnection(); // Close Connection

			} catch (Exception eee) {
			    eee.printStackTrace();
			
			}}

		else if(e.getSource()==cancelButton) {
			setVisible(false);
		}
		else if(e.getSource()==signupButton) {
			setVisible(false);
			new Signup();
		}
	}
    public static void main(String[] args) {
        new Login();
    }

	
}
