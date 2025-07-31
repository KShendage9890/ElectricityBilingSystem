package ElectricityBilingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {
    JLabel nameText, addressText;
    TextField unitText;
    Choice meternumCho, monthCho;
    JButton submit, cancel;

    calculate_bill() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70, 10, 300, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meternum = new JLabel("Meter Number");
        meternum.setBounds(50, 80, 100, 20);
        panel.add(meternum);

        meternumCho = new Choice();
        try {
            database c = new database();
            ResultSet resultSet = c.st.executeQuery("select * from new_customer");
            while (resultSet.next()) {
                meternumCho.add(resultSet.getString("meter_number"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        meternumCho.setBounds(180, 80, 100, 20);
        panel.add(meternumCho);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 120, 100, 20);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180, 120, 150, 20);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        // Fetch customer details when meter number is selected
        meternumCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    database c = new database();
                    String selectedMeter = meternumCho.getSelectedItem();
                    System.out.println("Selected Meter: " + selectedMeter); // Debugging line
                    ResultSet resultSet = c.st.executeQuery("select * from new_customer where meter_number = '" + selectedMeter + "'");
                    
                    if (resultSet.next()) { // Check if we have results
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));
                        System.out.println("Name: " + resultSet.getString("name")); // Debugging line
                        System.out.println("Address: " + resultSet.getString("address")); // Debugging line
                    } else {
                        nameText.setText("");
                        addressText.setText("");
                        System.out.println("No data found for meter number: " + selectedMeter); // Debugging line
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        // Add units consumed input
        JLabel unitsConsumed = new JLabel("Units Consumed");
        unitsConsumed.setBounds(50, 200, 120, 20);
        panel.add(unitsConsumed);

        unitText = new TextField();
        unitText.setBounds(180, 200, 150, 20);
        panel.add(unitText);

        // Add month selection
        JLabel monthLabel = new JLabel("Month");
        monthLabel.setBounds(50, 240, 100, 20);
        panel.add(monthLabel);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(180, 240, 150, 20);
        panel.add(monthCho);

        submit = new JButton("Submit");
        submit.setBounds(80, 300, 100, 25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(220, 300, 100, 25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setSize(650, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterNo = meternumCho.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthCho.getSelectedItem();

            int totalBill = 0;
            try {
                int units = Integer.parseInt(sunit);
                String query_tax = "select * from tax";
                database c = new database();
                ResultSet resultSet = c.st.executeQuery(query_tax);
                while (resultSet.next()) {
                    totalBill += units * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }

                String query_total_bill = "insert into bill values('" + smeterNo + "', '" + smonth + "','" + sunit + "', '" + totalBill + "','Not Paid')";
                c.st.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter valid units consumed.");
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calculate_bill();
    }
}
