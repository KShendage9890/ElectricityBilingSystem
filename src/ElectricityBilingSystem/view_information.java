package ElectricityBilingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class view_information extends JFrame implements ActionListener {
    String meterNumber;
    JButton cancel;

    view_information(String meterNumber) {
        this.meterNumber = meterNumber;
        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(70, 80, 100, 20);
        add(nameLabel);

        JLabel nameLabelText = new JLabel("");
        nameLabelText.setBounds(200, 80, 150, 20);
        add(nameLabelText);

        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(70, 140, 100, 20);
        add(meterno);

        JLabel meternoText = new JLabel("");
        meternoText.setBounds(200, 140, 150, 20);
        add(meternoText);

        JLabel address = new JLabel("Address");
        address.setBounds(70, 200, 100, 20);
        add(address);

        JLabel addressText = new JLabel("");
        addressText.setBounds(200, 200, 150, 20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70, 260, 100, 20);
        add(city);

        JLabel cityText = new JLabel("");
        cityText.setBounds(200, 260, 150, 20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(500, 80, 100, 20);
        add(state);

        JLabel stateText = new JLabel("");
        stateText.setBounds(600, 80, 150, 20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(500, 140, 100, 20);
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(600, 140, 150, 20);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(500, 200, 100, 20);
        add(phone);

        JLabel phoneText = new JLabel("");
        phoneText.setBounds(600, 200, 150, 20);
        add(phoneText);

        // Debugging: Check what meter number is being passed
        System.out.println("Looking up customer with meter number: " + meterNumber);

        // Execute the SQL query
        try {
            database c = new database();
            Connection conn = c.getConnection(); // Assuming this method exists to get the connection
            String query = "SELECT * FROM new_customer WHERE meter_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, meterNumber);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                nameLabelText.setText(resultSet.getString("name"));
                meternoText.setText(resultSet.getString("meter_number"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
                System.out.println("Data found: " + resultSet.getString("name"));
            } else {
                System.out.println("No data found for meter number: " + meterNumber);
            }

            // Clean up resources
            resultSet.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(24, 118, 242));
        cancel.setForeground(Color.white);
        cancel.setBounds(220, 350, 120, 25);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            setVisible(false);
        }
    }
}
