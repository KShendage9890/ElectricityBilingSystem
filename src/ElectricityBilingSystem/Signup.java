package ElectricityBilingSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Signup extends JFrame implements ActionListener {
	Choice loginAsCho;
	TextField meterText,EmployerText,userNameText,nameText,passwordText;
	JButton create,back;
Signup(){
	super("Signup page");
	
	getContentPane().setBackground(new Color(168,203,255));
	
	
	JLabel createAs=new JLabel("Create Accoun As");
	createAs.setBounds(30, 50, 125, 20);
	add(createAs);
	loginAsCho=new  Choice();
	loginAsCho.add("Admin");
	loginAsCho.add("Customer");
	loginAsCho.setBounds(170, 50, 120, 20);
	add(loginAsCho);
	
	JLabel meterNo=new JLabel("Meter Number");
	meterNo.setBounds(30,100,125,20);
	meterNo.setVisible(false);
	add(meterNo);
	
	meterText=new TextField();
	meterText.setBounds(170,100,125,20);
	meterText.setVisible(false);
	add(meterText);
	
	JLabel Employer=new JLabel("Employer ID");
	Employer.setBounds(30,100,125,20);
	Employer.setVisible(true);
	add(Employer);
	
	
	
	EmployerText=new TextField();
	EmployerText.setBounds(170,100,125,20);
	EmployerText.setVisible(true);
	add(EmployerText);
	
	JLabel userName=new JLabel( "UserName");
	userName.setBounds(30,140,125,20);
	add(userName);
	
	
	userNameText=new TextField();
	userNameText.setBounds(170,140,125,20);
	add(userNameText);
	
	
	
	JLabel name=new JLabel("Name");
	name.setBounds(30,180,125,20);
	add(name);
	
	nameText=new TextField("");
	nameText.setBounds(170,180,125,20);
	add(nameText);
	
	meterText.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            try {
                database c = new database();
                ResultSet resultSet = c.st.executeQuery("select * from Signup  where meter_no = '"+meterText.getText()+"'");
                if (resultSet.next()) {
                    nameText.setText(resultSet.getString("name")); // Retrieve the name
                } else {
                    nameText.setText(""); // Clear name if not found
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
	JLabel password=new JLabel("Password");
	password.setBounds(30,220,125,20);
	add(password);
	
	passwordText=new TextField();
	passwordText.setBounds(170,220,125,20);
	add(passwordText);

	loginAsCho.addItemListener(new ItemListener(){
			@Override
		public void itemStateChanged(ItemEvent e) {
			String user=loginAsCho.getSelectedItem();
			if(user.equals("Customer")) {
				Employer.setVisible(false);
				nameText.setEditable(false);	
				EmployerText.setVisible(false);
				meterNo.setVisible(true);
				meterText.setVisible(true);
			}
			else {
				Employer.setVisible(true);
				EmployerText.setVisible(true);
				meterNo.setVisible(false);
				meterText.setVisible(false);
			}
		}
	});
	
	create=new JButton("Create");
	create.setBackground(new Color(66,127,219));
	create.setBounds(50,280,100,25);
	create.addActionListener(this);
	add(create);
	
	back=new JButton("Back");
	back.setBackground(new Color(66,127,219));
	back.setBounds(180,285,100,25);
	back.addActionListener(this);
	add(back);
	
	
	
    
    
    
	String profileImagePath = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\sign-up.png"; // Corrected path
	ImageIcon profileOne = new ImageIcon(profileImagePath);
	Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
	ImageIcon fprofileOne = new ImageIcon(profileTwo);
	JLabel profileLabel = new JLabel(fprofileOne);
	profileLabel.setBounds(320, 30, 250, 250); // Set the bounds and position of the image
	add(profileLabel);

	
	setSize(600,380);
	setLocation(500,200);
	setLayout(null);
	setVisible(true);
	
	
	
}
@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == create) {
        String sloginAs = loginAsCho.getSelectedItem();
        String susername = userNameText.getText();
        String sname = nameText.getText();
        String spassword = passwordText.getText();
        String smeter = meterText.getText();
        try {
            database db = new database();
            String query=null;
            if (loginAsCho.equals("Admin")) {
                query = "insert into Signup value('" + smeter + "', '" + susername + "', '" + sname + "','" + spassword + "','" + sloginAs + "')";
            }else {
                query = "update Signup set username = '"+susername+"', password = '"+spassword+"', usertype = '"+sloginAs+"' where meter_no = '"+smeter+"'";
            }
             db.st.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Account Created Successfully");
            setVisible(false);
            new Login();
            
            // Close PreparedStatement
            db.closeConnection(); // Close Connection
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }



	else if(e.getSource()==back) {
		setVisible(false);
		new Login();
	}
}

public static void main(String[]args) {
	new Signup();
}



}
