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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Publisher extends JFrame {

	private JPanel contentPane;
	private JTextField name_field;
	private JTextField address_field;
	private JTextField phone_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Publisher frame = new Publisher();
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
	public Publisher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 621, 470);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel validation_label1 = new JLabel("");
		validation_label1.setForeground(Color.RED);
		validation_label1.setBounds(232, 331, 285, 16);
		contentPane.add(validation_label1);
		
		name_field = new JTextField();
		name_field.setBounds(234, 106, 130, 26);
		contentPane.add(name_field);
		name_field.setColumns(10);
		
		address_field = new JTextField();
		address_field.setBounds(234, 204, 130, 26);
		contentPane.add(address_field);
		address_field.setColumns(10);
		
		phone_field = new JTextField();
		phone_field.setBounds(234, 306, 130, 26);
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
		contentPane.add(phone_field);
		phone_field.setColumns(10);
		
		JLabel lblPublishers = new JLabel("PUBLISHERS");
		lblPublishers.setForeground(Color.WHITE);
		lblPublishers.setBounds(253, 31, 249, 16);
		contentPane.add(lblPublishers);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.CYAN);
		lblName.setBounds(84, 111, 61, 16);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.CYAN);
		lblAddress.setBounds(84, 209, 61, 16);
		contentPane.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		contentPane.add(lblPhone);
		
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
					String query = "insert into Borrowers values (?, ?, ?)";
					
					ps = connect.prepareStatement(query);
					ps.setString(1, name_field.getText());
					ps.setString(2, address_field.getText());
					ps.setInt(3, Integer.parseInt(phone_field.getText()));
					
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Entered Successfully :) ");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		update_button.setBounds(385, 371, 117, 29);
		contentPane.add(update_button);
		
		JButton home_button = new JButton("Home");
		home_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Home home = new Home();
				home.setVisible(true);
			}
		});
		home_button.setBounds(84, 371, 117, 29);
		contentPane.add(home_button);
		
		JLabel lblPhone_1 = new JLabel("Phone");
		lblPhone_1.setForeground(Color.CYAN);
		lblPhone_1.setBounds(84, 311, 61, 16);
		contentPane.add(lblPhone_1);
		
	}

}
