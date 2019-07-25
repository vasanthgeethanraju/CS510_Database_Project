package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ReturnBooks extends JFrame {

	private JPanel contentPane;
	private JTextField bookloanid_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBooks frame = new ReturnBooks();
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
	public ReturnBooks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 621, 470);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReturnBooks = new JLabel("RETURN BOOKS");
		lblReturnBooks.setForeground(Color.WHITE);
		lblReturnBooks.setBounds(252, 21, 187, 16);
		contentPane.add(lblReturnBooks);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home hm = new Home();
				hm.setVisible(true);
			}
		});
		button.setBounds(252, 206, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Return Book");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect = null;
				PreparedStatement ps = null;
				try {
					connect = DriverManager
					        .getConnection("jdbc:mysql://localhost:3307/SP?"
					        		+ " verifyServerCertificate=false&useSSL=true&"
					                + "user=msandbox&password=cs510sum19&"
					        		+ "serverTimezone=UTC");
					
					
					String blcb = bookloanid_field.getText();
					String query = "delete from Book_Loans where BookLoanID ="+blcb+";";
					
					ps = connect.prepareStatement(query);
					
					
					ps.execute();
					JOptionPane.showMessageDialog(null, "Book Returned Successfully :) ");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		button_1.setBounds(252, 152, 117, 29);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Book Loan ID");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBounds(146, 94, 117, 16);
		contentPane.add(lblNewLabel);
		
		bookloanid_field = new JTextField();
		bookloanid_field.setColumns(10);
		bookloanid_field.setBounds(362, 89, 130, 26);
		contentPane.add(bookloanid_field);
		
		
	}

}
