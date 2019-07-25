package Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Library_Branches extends JFrame {

	private JPanel contentPane;
	private JTextField branchid_field;
	private JTextField branchname_field;
	private JButton update_button;
	private JLabel lblBranchId;
	private JLabel lblBranchName;
	private JLabel lblLibraryBranches;
	private JLabel validation_label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library_Branches frame = new Library_Branches();
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
	public Library_Branches() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 621, 470);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		validation_label = new JLabel("");
		validation_label.setForeground(new Color(255, 0, 0));
		validation_label.setBounds(234, 127, 193, 16);
		contentPane.add(validation_label);
		
		branchid_field = new JTextField();
		branchid_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int i = Integer.parseInt(branchid_field.getText());
					validation_label.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					validation_label.setText("Please enter a valid number");
					
				}
			}
		});
		branchid_field.setText("");
		branchid_field.setBounds(234, 91, 130, 26);
		contentPane.add(branchid_field);
		branchid_field.setColumns(10);
		
		branchname_field = new JTextField();
		branchname_field.setBounds(234, 188, 130, 26);
		contentPane.add(branchname_field);
		branchname_field.setColumns(10);
		
		JButton home_button = new JButton("Home");
		home_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
			}
		});
		home_button.setBounds(91, 301, 117, 29);
		contentPane.add(home_button);
		
		update_button = new JButton("Update");
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
					String query = "insert into Library_Branches values (?, ?)";
					
					ps = connect.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(branchid_field.getText()));
					ps.setString(2, branchname_field.getText());
					
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Entered Successfully :) ");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		update_button.setBounds(374, 301, 117, 29);
		contentPane.add(update_button);
		
		lblBranchId = new JLabel("Branch ID");
		lblBranchId.setForeground(Color.CYAN);
		lblBranchId.setBounds(71, 96, 61, 16);
		contentPane.add(lblBranchId);
		
		lblBranchName = new JLabel("Branch Name");
		lblBranchName.setForeground(Color.CYAN);
		lblBranchName.setBounds(71, 193, 87, 16);
		contentPane.add(lblBranchName);
		
		lblLibraryBranches = new JLabel("LIBRARY BRANCHES");
		lblLibraryBranches.setForeground(Color.WHITE);
		lblLibraryBranches.setBounds(234, 17, 144, 16);
		contentPane.add(lblLibraryBranches);
		
		
	}

}
