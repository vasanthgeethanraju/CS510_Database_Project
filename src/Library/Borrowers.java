package Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Borrowers extends JFrame {

	private JPanel contentPane;
	private JTextField cardno_field;
	private JTextField borrowername_field;
	private JTextField address_field;
	private JTextField phone_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrowers frame = new Borrowers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Borrowers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 621, 470);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel validation_label = new JLabel("");
		validation_label.setForeground(new Color(255, 0, 0));
		validation_label.setBounds(237, 93, 193, 16);
		contentPane.add(validation_label);
		
		JLabel validation_label1 = new JLabel("");
		validation_label1.setForeground(Color.RED);
		validation_label1.setBounds(237, 295, 285, 16);
		contentPane.add(validation_label1);
		
		cardno_field = new JTextField();
		cardno_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int i = Integer.parseInt(cardno_field.getText());
					validation_label.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					validation_label.setText("Please enter a valid number");
					
				}
				
			}
		});
		cardno_field.setBounds(237, 68, 130, 26);
		contentPane.add(cardno_field);
		cardno_field.setColumns(10);
		
		borrowername_field = new JTextField();
		borrowername_field.setBounds(237, 139, 130, 26);
		contentPane.add(borrowername_field);
		borrowername_field.setColumns(10);
		
		address_field = new JTextField();
		address_field.setBounds(237, 201, 130, 26);
		contentPane.add(address_field);
		address_field.setColumns(10);
		
		phone_field = new JTextField();
		phone_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int i = Integer.parseInt(phone_field.getText());
					validation_label1.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					validation_label1.setText("Please enter a valid Phone Number");
					
				}
				
			}
		});
		phone_field.setBounds(237, 267, 130, 26);
		contentPane.add(phone_field);
		phone_field.setColumns(10);
		
		JLabel lblBorrowers = new JLabel("BORROWERS");
		lblBorrowers.setForeground(Color.WHITE);
		lblBorrowers.setBounds(253, 17, 139, 16);
		contentPane.add(lblBorrowers);
		
		JButton update_button = new JButton("Update");
		update_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Connection connect = null;
				PreparedStatement ps = null;
				try {
					connect = DriverManager
					        .getConnection("jdbc:mysql://localhost:3307/SP?"
					        		+ " verifyServerCertificate=false&useSSL=true&"
					                + "user=msandbox&password=cs510sum19&"
					        		+ "serverTimezone=UTC");
					
					//JOptionPane.showMessageDialog(null, "Connection Successfull");
					String query = "insert into Borrowers values (?, ?, ?, ?)";
					
					ps = connect.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(cardno_field.getText()));
					ps.setString(2, borrowername_field.getText());
					ps.setString(3, address_field.getText());
					ps.setInt(4, Integer.parseInt(phone_field.getText()));
					
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Entered Successfully :) ");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});

		update_button.setBounds(405, 340, 117, 29);
		contentPane.add(update_button);
		
		JButton home_button = new JButton("Home");
		home_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
			}
		});
		home_button.setBounds(89, 340, 117, 29);
		contentPane.add(home_button);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setForeground(Color.CYAN);
		lblCardNumber.setBounds(108, 73, 98, 16);
		contentPane.add(lblCardNumber);
		
		JLabel lblBorrowerName = new JLabel("Borrower Name");
		lblBorrowerName.setForeground(Color.CYAN);
		lblBorrowerName.setBounds(108, 144, 98, 16);
		contentPane.add(lblBorrowerName);
		
		JLabel lblBorrowersAddress = new JLabel("Address");
		lblBorrowersAddress.setForeground(Color.CYAN);
		lblBorrowersAddress.setBounds(108, 206, 98, 16);
		contentPane.add(lblBorrowersAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.CYAN);
		lblPhone.setBounds(108, 272, 61, 16);
		contentPane.add(lblPhone);
		
		
		
		
	}

}
