package ElectricityBilingSystem;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class main_class extends JFrame implements ActionListener {
String acctype;
String meter_pass;
    main_class( String acctype,String meter_pass) {
        // Set the frame to be maximized
        this.acctype=acctype;
        this.meter_pass=meter_pass;
    	setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Use the image path you provided
        String imagePath = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\ebs.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image=imageIcon.getImage().getScaledInstance(1530,830,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);
        
        JMenu menu=new JMenu("Menu");
        menu.setFont(new Font("serif",Font.PLAIN,15));
        
        JMenuBar menuBar=new JMenuBar();
        setJMenuBar(menuBar);

        
        JMenuItem newcustomer=new JMenuItem("New Customer");
        newcustomer.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\newcustomer.png";
        ImageIcon customerImg = new ImageIcon(customerImagePath);
        Image customerImage = customerImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(customerImage));
        newcustomer.addActionListener(this);
        menu.add(newcustomer);
        
        
        
        JMenuItem customerdetails=new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath2 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\customerDetails.png";
        ImageIcon customerdetailsImg = new ImageIcon(customerImagePath2);
        Image customerdetailsImage = customerdetailsImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        
        customerdetails.setIcon(new ImageIcon(customerdetailsImage));
        customerdetails.addActionListener(this);

        menu.add(customerdetails);
        
        
        JMenuItem depositdetails=new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath3 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\depositdetails.png";
        ImageIcon depositdetailsImg = new ImageIcon(customerImagePath3);
        Image depositdetailsImage = depositdetailsImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositdetails.setIcon(new ImageIcon(depositdetailsImage));
        depositdetails.addActionListener(this);
        menu.add(depositdetails);
        
        
        
        JMenuItem calculatebill=new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath4 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\calculatorbills.png";
        ImageIcon calculatebillImg = new ImageIcon(customerImagePath4);
        Image calculatebillImage = calculatebillImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculatebill.setIcon(new ImageIcon(calculatebillImage));
        calculatebill.addActionListener(this);
        menu.add(calculatebill);
        
        JMenu  info=new JMenu("Information");
        info.setFont(new Font("serif",Font.PLAIN,15));
        
        
        JMenuItem upinfo=new JMenuItem("Update Information");
        upinfo.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath5 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\refresh.png";
        ImageIcon upinfoImg = new ImageIcon(customerImagePath5);
        Image upinfoImage = upinfoImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        upinfo.setIcon(new ImageIcon(upinfoImage));
        upinfo.addActionListener(this);
        info.add(upinfo);
        
        JMenuItem viewInfo=new JMenuItem("View Information");
        viewInfo.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath6 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\information.png";
        ImageIcon viewInfoImg = new ImageIcon(customerImagePath6);
        Image viewInfoImage = viewInfoImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(viewInfoImage));
        viewInfo.addActionListener(this);
        info.add(viewInfo);
        
        
        JMenu user=new JMenu(" User");
        user.setFont(new Font("serif",Font.PLAIN,15));
         
        
        JMenuItem paybill=new JMenuItem("Pay Bill");
        paybill.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath7 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\pay.png";
        ImageIcon paybillImg = new ImageIcon(customerImagePath7);
        Image paybillImage = paybillImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        paybill.setIcon(new ImageIcon(paybillImage));
        paybill.addActionListener(this);
        user.add(paybill);
        
        
        JMenuItem billdetails=new JMenuItem("Bill Details");
        billdetails.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath8 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\detail.png";
        ImageIcon billdetailsImg = new ImageIcon(customerImagePath8);
        Image billdetailsImage = billdetailsImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billdetails.setIcon(new ImageIcon(billdetailsImage));
        billdetails.addActionListener(this);
        user.add(billdetails);
        
        
        
        
        
        JMenu bill=new JMenu("Bill");
        bill.setFont(new Font("serif",Font.PLAIN,15));
        
        
        JMenuItem genBill=new JMenuItem("Genrate Bill");
        genBill.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath9 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\bill.png";
        ImageIcon genBillImg = new ImageIcon(customerImagePath9);
        Image genBillImage = genBillImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        genBill.setIcon(new ImageIcon(genBillImage));
        genBill.addActionListener(this);
        bill.add(genBill);
        
        
        
        
        JMenu utility=new JMenu("Utility");
        utility.setFont(new Font("serif",Font.PLAIN,15));
        
        
        JMenuItem notepad=new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath10 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\notepad.png";
        ImageIcon notepadImg = new ImageIcon(customerImagePath10);
        Image notepadImage = notepadImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(notepadImage));
        notepad.addActionListener(this);
        utility.add(notepad);
        
        
        
        
        
        JMenuItem calculator=new JMenuItem("Calculator");
        calculator.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath11 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\calculator.png";
        ImageIcon calculatorImg = new ImageIcon(customerImagePath11);
        Image calculatorImage = calculatorImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(calculatorImage));
        calculator.addActionListener(this);
        utility.add(calculator);
        
        
        
        JMenu exit=new JMenu("Exit");
        exit.setFont(new Font("serif",Font.PLAIN,15));
        
        
        JMenuItem eexit=new JMenuItem("Exit");
        eexit.setFont(new Font("monospaced",Font.PLAIN,14));
        String customerImagePath12 = "D:\\javarahulsirfold\\ElectricityBilingSystem\\src\\icon\\exit.png";
        ImageIcon eexitImg = new ImageIcon(customerImagePath12);
        Image eexitImage = eexitImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        eexit.setIcon(new ImageIcon(eexitImage));
        eexit.addActionListener(this);
        exit.add(eexit);

        
       
        
        
        
        
      
    if(acctype.equals("Admin")) {
        menuBar.add(menu);

    }else {
        menuBar.add(bill);
        menuBar.add(info);
        menuBar.add(user);
               
    }
    menuBar.add(utility);
    menuBar.add(exit);
    setLayout(new FlowLayout());
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ensure the application closes properly

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if (msg.equals("New Customer")) {
            new newCustomer();
        } else if (msg.equals("Customer Details")) {
          new customer_details();
        } else if (msg.equals("Deposit Details")) {
            new deposit_details();
        } else if (msg.equals("Calculate Bill")) {
            new calculate_bill();
        } else if (msg.equals("View Information")) {
            new view_information(meter_pass);
            
        } else if (msg.equals("Update Information")) {
            new update_information(meter_pass);
        } else if (msg.equals("Bill Details")) {
            new bill_details(meter_pass);
        } else if (msg.equals("Calculator")) {
            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (msg.equals("Notepad")) {
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        } else if (msg.equals("Pay Bill")) {
            new pay_bill(meter_pass);
        } else if (msg.equals("Genrate Bill")) {
            new generate_bill(meter_pass);
        }
    }
    
		
	
    public static void main(String[] args) {
        new main_class("","");  // Create and show the frame
    }
	
}
