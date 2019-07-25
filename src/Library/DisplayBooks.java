package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DisplayBooks extends JFrame {

	private JPanel contentPane;
	private JTextField cardno_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayBooks frame = new DisplayBooks();
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
	public DisplayBooks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 621, 470);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLibraryBranch = new JLabel("Library Branch");
		lblLibraryBranch.setForeground(Color.CYAN);
		lblLibraryBranch.setBounds(72, 53, 108, 16);
		contentPane.add(lblLibraryBranch);
		
		JLabel lblBorrower = new JLabel("Borrower Card No");
		lblBorrower.setForeground(Color.CYAN);
		lblBorrower.setBounds(428, 53, 172, 16);
		contentPane.add(lblBorrower);
		
		JComboBox lbranch_comboBox = new JComboBox();
		lbranch_comboBox.addItem("100");
		lbranch_comboBox.addItem("101");
		lbranch_comboBox.addItem("102");
		lbranch_comboBox.addItem("103");
		lbranch_comboBox.setBounds(32, 102, 179, 27);
		contentPane.add(lbranch_comboBox);
		
		JButton btnNewButton = new JButton("Display Books at Library Branch");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					connect = DriverManager
					        .getConnection("jdbc:mysql://localhost:3307/SP?"
					        		+ " verifyServerCertificate=false&useSSL=true&"
					                + "user=msandbox&password=cs510sum19&"
					        		+ "serverTimezone=UTC");
					
					//JOptionPane.showMessageDialog(null, "Connection Successfull");
					Object lbcb = lbranch_comboBox.getSelectedItem();
					
					String query = 
							"select Books.Title from Books join Book_Copies on (Books.BookID = Book_Copies.BookID) join Library_Branches "
							+ "on (Book_Copies.BranchID = Library_Branches.branchID)"
							+ "where Library_Branches.BranchID =" + lbcb + ";";
					
					ps = connect.prepareStatement(query);

					rs = ps.executeQuery();
					ArrayList<String> ar = new ArrayList<String>();
					while(rs.next()) {
					String ans = rs.getString("Books.Title");
					ar.add(ans);
					}
					String yes=ar.toString();
					JOptionPane.showMessageDialog(null, ar);
					
					
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 				
			}			
		});
		btnNewButton.setBounds(32, 170, 221, 98);
		contentPane.add(btnNewButton);
		
		JButton btnDisplayBooksChecked = new JButton("Display Books checked out");
		btnDisplayBooksChecked.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					connect = DriverManager
					        .getConnection("jdbc:mysql://localhost:3307/SP?"
					        		+ " verifyServerCertificate=false&useSSL=true&"
					                + "user=msandbox&password=cs510sum19&"
					        		+ "serverTimezone=UTC");
					
					//JOptionPane.showMessageDialog(null, "Connection Successfull");
					String CardNo = cardno_field.getText();
					
					String query = 
							"select Books.Title from Books join Book_Loans "
							+ "on (Books.BookID = Book_Loans.BookID) "
							+ "join Borrowers "
							+ "on (Book_Loans.CardNo = Borrowers.CardNo) "
							+ "where Borrowers.CardNo =" + CardNo + ";";
					
					ps = connect.prepareStatement(query);

					rs = ps.executeQuery();
					ArrayList<String> ar = new ArrayList<String>();
					while(rs.next()) {
					String ans = rs.getString("Books.Title");
					ar.add(ans);
					}
					String yes=ar.toString();
					JOptionPane.showMessageDialog(null, ar);
					
					
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnDisplayBooksChecked.setBounds(370, 170, 221, 98);
		contentPane.add(btnDisplayBooksChecked);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home hm1 = new Home();
				hm1.setVisible(true);
			}
		});
		button.setBounds(94, 334, 117, 29);
		contentPane.add(button);
		
		JLabel lblDisplayBooks = new JLabel("DISPLAY BOOKS");
		lblDisplayBooks.setForeground(Color.WHITE);
		lblDisplayBooks.setBackground(Color.WHITE);
		lblDisplayBooks.setBounds(260, 6, 143, 16);
		contentPane.add(lblDisplayBooks);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataModel dm = new DataModel();
				dm.setVisible(true);
			}
		});
		btnBack.setBounds(417, 334, 117, 29);
		contentPane.add(btnBack);
		
		JLabel valid_label = new JLabel("");
		valid_label.setForeground(Color.RED);
		valid_label.setEnabled(false);
		valid_label.setBounds(395, 127, 165, 16);
		contentPane.add(valid_label);
		
		cardno_field = new JTextField();
		cardno_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int i = Integer.parseInt(cardno_field.getText());
					valid_label.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					valid_label.setText("Please enter a valid number");
					
				}
			}
		});
		cardno_field.setColumns(10);
		cardno_field.setBounds(417, 101, 143, 26);
		contentPane.add(cardno_field);
		
		
		
	}

}
