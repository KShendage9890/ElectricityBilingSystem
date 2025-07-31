package ElectricityBilingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class meterInfo extends JFrame implements ActionListener {

    Choice meterLocCho, meterTypCho, phaseCodeCho, billtypCho;
    JButton submit;
    String meternumber;

    meterInfo(String meternumber) {
        this.meternumber = meternumber;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 186, 3));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50, 80, 100, 20);
        panel.add(meterNumber);

        JLabel meterNumberText = new JLabel(meternumber);
        meterNumberText.setBounds(180, 80, 150, 20);
        panel.add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Location");
        meterLoc.setBounds(50, 120, 100, 20);
        panel.add(meterLoc);

        meterLocCho = new Choice();
        meterLocCho.add("Outside");
        meterLocCho.add("Inside");
        meterLocCho.setBounds(180, 120, 150, 20);
        panel.add(meterLocCho);

        JLabel meterTyp = new JLabel("Meter Type");
        meterTyp.setBounds(50, 160, 100, 20);
        panel.add(meterTyp);

        meterTypCho = new Choice();
        meterTypCho.add("Electric Meter");
        meterTypCho.add("Solar Meter");
        meterTypCho.add("Smart Meter");
        meterTypCho.setBounds(180, 160, 150, 20);
        panel.add(meterTypCho);

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50, 200, 100, 20);
        panel.add(phaseCode);

        phaseCodeCho = new Choice();
        phaseCodeCho.add("011");
        phaseCodeCho.add("022");
        phaseCodeCho.add("033");
        phaseCodeCho.add("044");
        phaseCodeCho.add("055");
        phaseCodeCho.add("066");
        phaseCodeCho.add("077");
        phaseCodeCho.add("088");
        phaseCodeCho.add("099");
        phaseCodeCho.setBounds(180, 200, 150, 20);
        panel.add(phaseCodeCho);

        JLabel billtyp = new JLabel("Bill Type");
        billtyp.setBounds(50, 240, 100, 20);
        panel.add(billtyp);

        billtypCho = new Choice();
        billtypCho.add("Normal");
        billtypCho.add("Industrial");
        billtypCho.setBounds(180, 240, 150, 20);
        panel.add(billtypCho);

        JLabel day = new JLabel("30 Days Billing Time...");
        day.setBounds(50, 280, 150, 20);
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50, 320, 100, 20);
        panel.add(note);

        JLabel note1 = new JLabel("By default, bill is calculated for 30 days only");
        note1.setBounds(50, 340, 300, 20);
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(220, 390, 100, 25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        // Load image using absolute path
        String imagePath = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\details.png";
        File file = new File(imagePath);
        if (!file.exists()) {
            System.err.println("Image file not found at: " + imagePath);
        } else {
            ImageIcon i1 = new ImageIcon(imagePath);
            Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel imgLabel = new JLabel(i3);
            add(imgLabel, "East");
        }

        setSize(700, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterNum = meternumber;
            String smeterLoc = meterLocCho.getSelectedItem();
            String smeterTyp = meterTypCho.getSelectedItem();
            String sphaseCode = phaseCodeCho.getSelectedItem();
            String sbillTyp = billtypCho.getSelectedItem();
            String sday = "30";

            String query_meterInfo = "INSERT INTO meter_info (meter_number, meter_location, meter_type, phase_code, bill_type, days) VALUES (?, ?, ?, ?, ?, ?)";
            try { database db = new database();
                 Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query_meterInfo);
                 
                pstmt.setString(1, smeterNum);
                pstmt.setString(2, smeterLoc);
                pstmt.setString(3, smeterTyp);
                pstmt.setString(4, sphaseCode);
                pstmt.setString(5, sbillTyp);
                pstmt.setString(6, sday);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Meter Information Submitted Successfully");
                setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error submitting meter information: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new meterInfo(""); // Example meter number
    }
}
