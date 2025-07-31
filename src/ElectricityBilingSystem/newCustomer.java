package ElectricityBilingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {
    JLabel heading, customerName, meterNum, address, city, email, phone, state;
    JButton next, cancel;
    TextField nameText, addressText, cityText, stateText, emailText, phoneText;
    JLabel meternumText; // Change to JLabel for displaying meter number

    newCustomer() {
        super("New Customer");
        setSize(700, 500);
        setLocation(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 186, 3));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        customerName = new JLabel("Customer Name");
        customerName.setBounds(50, 80, 100, 20);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(180, 80, 150, 20);
        panel.add(nameText);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50, 120, 100, 20);
        panel.add(meterNum);

        meternumText = new JLabel("");
        meternumText.setBounds(180, 120, 150, 20);
        panel.add(meternumText);

        // Generate random meter number
        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meternumText.setText("" + Math.abs(number));

        address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        city = new JLabel("City");
        city.setBounds(50, 200, 100, 20);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180, 200, 150, 20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setBounds(50, 240, 100, 20);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180, 240, 150, 20);
        panel.add(stateText);

        email = new JLabel("Email");
        email.setBounds(50, 280, 100, 20);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180, 280, 150, 20);
        panel.add(emailText);

        phone = new JLabel("Phone");
        phone.setBounds(50, 320, 100, 20);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180, 320, 150, 20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(230, 390, 100, 25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        // Image loading using absolute path
        String profileImagePath = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\profile.png"; // Updated to use an absolute path
        try {
            ImageIcon profileOne = new ImageIcon(profileImagePath);
            if (profileOne.getIconWidth() == -1) {
                throw new Exception("Image not found at: " + profileImagePath);
            }
            Image i2 = profileOne.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel imgLabel = new JLabel(i3);
            add(imgLabel, "West");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            String sname = nameText.getText();
            String smeter = meternumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();

            String query_customer = "INSERT INTO new_customer (name, meter_number, address, city, state, email, phone_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String query_signup = "INSERT INTO Signup (meter_no, username, password, name, usertype) VALUES (?, ?, '', ?, 'customer')"; // Adjusted to match your Signup table

            try {
                database c = new database();

                // Insert into new_customer
                PreparedStatement psCustomer = c.getConnection().prepareStatement(query_customer);
                psCustomer.setString(1, sname);
                psCustomer.setString(2, smeter);
                psCustomer.setString(3, saddress);
                psCustomer.setString(4, scity);
                psCustomer.setString(5, sstate);
                psCustomer.setString(6, eemail);
                psCustomer.setString(7, sphone);
                psCustomer.executeUpdate();

                // Insert into Signup
                PreparedStatement psSignup = c.getConnection().prepareStatement(query_signup);
                psSignup.setString(1, smeter); // Meter number
                psSignup.setString(2, eemail);  // Using email as username (or change as needed)
                psSignup.setString(3, sname);   // Name
                psSignup.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer details added successfully");
                setVisible(false);
                new meterInfo(smeter);
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
