package ElectricityBilingSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deposit_details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchMonthCho;
    JTable table;
    JButton search, print, close;

    deposit_details() {
        super("Deposit Details");
        getContentPane().setBackground(new Color(192, 186, 254));
        setSize(700, 500);
        setLocation(400, 200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20, 20, 150, 20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180, 20, 150, 20);
        add(searchMeterCho);

        loadMeterNumbers();

        JLabel searchMonth = new JLabel("Search By Month");
        searchMonth.setBounds(400, 20, 100, 20);
        add(searchMonth);

        searchMonthCho = new Choice();
        String[] months = {"January", "February", "March", "April", "May", "June",
                           "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            searchMonthCho.add(month);
        }
        searchMonthCho.setBounds(520, 20, 150, 20);
        add(searchMonthCho);

        table = new JTable();
        loadTableData();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 700, 400);
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600, 70, 80, 20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    private void loadMeterNumbers() {
        try {
            database db = new database();
            ResultSet resultSet = db.st.executeQuery("SELECT meter_no FROM bill");
            while (resultSet.next()) {
                searchMeterCho.add(resultSet.getString("meter_no"));
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTableData() {
        try {
            database db = new database();
            ResultSet resultSet = db.st.executeQuery("SELECT * FROM bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String querySearch = "SELECT * FROM bill WHERE meter_no = ? AND month = ?";
            try {
                database db = new database();
                PreparedStatement pstmt = db.getConnection().prepareStatement(querySearch);
                pstmt.setString(1, searchMeterCho.getSelectedItem());
                pstmt.setString(2, searchMonthCho.getSelectedItem());

                ResultSet resultSet = pstmt.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

                pstmt.close();
                db.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new deposit_details();
    }
}
