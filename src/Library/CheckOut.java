package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CheckOut extends JFrame {

	private JPanel contentPane;
	private JTextField booktitle_field;
	private JTextField branchid_field;
	private JTextField bookid_field;
	private JTextField cardno_field;
	private JTextField uid_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOut frame = new CheckOut();
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
	public CheckOut() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 621, 470);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home hm = new Home();
				hm.setVisible(true);
			}
		});
		button.setBounds(51, 364, 117, 29);
		contentPane.add(button);
		
		JButton btnClickHere = new JButton("To list the number of book copies checked out from each branch. Click Here!");
		btnClickHere.addActionListener(new ActionListener() {
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
					String btf = booktitle_field.getText();
					
					String query = 
							"select Book_Loans.BranchID, count(Book_Loans.BookID) from Books "
							+ "join Book_Loans "
							+ "on (Books.BookID = Book_Loans.BookID) "
							+ "where Books.Title ='" + btf + "'"
							+ " group by Book_Loans.BranchID;";
					
					ps = connect.prepareStatement(query);

					rs = ps.executeQuery();
					ArrayList<String> ar = new ArrayList<String>();
					String ans = null; 
					while(rs.next()) {
					ans = rs.getString("Book_Loans.BranchID");
					String ans2 = rs.getString("count(Book_Loans.BookID)");
					ar.add(ans);
					ar.add(ans2);
					}
					String yes=ar.toString();
					JOptionPane.showMessageDialog(null, "Library Branch ID, \t No of Books \n" + yes );
					
					
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnClickHere.setBounds(51, 93, 519, 34);
		contentPane.add(btnClickHere);
		
		JLabel lblCheckOut = new JLabel("CHECK OUT");
		lblCheckOut.setForeground(Color.WHITE);
		lblCheckOut.setBackground(Color.WHITE);
		lblCheckOut.setBounds(283, 6, 143, 16);
		contentPane.add(lblCheckOut);
		
		JLabel lblBookTitle = new JLabel("Book Title");
		lblBookTitle.setForeground(Color.CYAN);
		lblBookTitle.setBounds(45, 27, 70, 16);
		contentPane.add(lblBookTitle);
		
		booktitle_field = new JTextField();
		booktitle_field.setColumns(10);
		booktitle_field.setBounds(20, 55, 130, 26);
		contentPane.add(booktitle_field);
		
		JButton button_1 = new JButton("Check Out");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect = null;
				PreparedStatement ps = null;
				PreparedStatement ps1 = null;
				try {
					connect = DriverManager
					        .getConnection("jdbc:mysql://localhost:3307/SP?"
					        		+ " verifyServerCertificate=false&useSSL=true&"
					                + "user=msandbox&password=cs510sum19&"
					        		+ "serverTimezone=UTC");
					
					String query = "insert into Book_Loans (BookID, BranchID, CardNo, UID)"
							+ "values (?, ?, ?, ?)";
					
					ps = connect.prepareStatement(query);
					ps.setString(1, bookid_field.getText());
					ps.setString(2, branchid_field.getText());
					ps.setString(3, cardno_field.getText());
					ps.setString(4, uid_field.getText());
					
					ps.execute();
					

					JOptionPane.showMessageDialog(null, "Checkout Successful :) ");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		button_1.setBounds(256, 279, 117, 29);
		contentPane.add(button_1);
		
		JLabel lblBranchId = new JLabel("Branch ID");
		lblBranchId.setForeground(Color.CYAN);
		lblBranchId.setBounds(45, 158, 70, 16);
		contentPane.add(lblBranchId);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setForeground(Color.CYAN);
		lblBookId.setBounds(45, 216, 70, 16);
		contentPane.add(lblBookId);
		
		JLabel lblCardNo = new JLabel("Card No");
		lblCardNo.setForeground(Color.CYAN);
		lblCardNo.setBounds(389, 158, 70, 16);
		contentPane.add(lblCardNo);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setForeground(Color.CYAN);
		lblUid.setBounds(389, 216, 70, 16);
		contentPane.add(lblUid);
		

		JLabel valid_label1 = new JLabel("");
		valid_label1.setForeground(Color.RED);
		valid_label1.setEnabled(false);
		valid_label1.setBounds(140, 177, 165, 16);
		contentPane.add(valid_label1);
		
		JLabel valid_label2 = new JLabel("");
		valid_label2.setForeground(Color.RED);
		valid_label2.setEnabled(false);
		valid_label2.setBounds(141, 238, 165, 16);
		contentPane.add(valid_label2);
		
		JLabel valid_label3 = new JLabel("");
		valid_label3.setForeground(Color.RED);
		valid_label3.setEnabled(false);
		valid_label3.setBounds(450, 177, 165, 16);
		contentPane.add(valid_label3);
		
		JLabel valid_label4 = new JLabel("");
		valid_label4.setForeground(Color.RED);
		valid_label4.setEnabled(false);
		valid_label4.setBounds(450, 244, 165, 16);
		contentPane.add(valid_label4);
		
		branchid_field = new JTextField();
		branchid_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int j = Integer.parseInt(branchid_field.getText());
					valid_label1.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					valid_label1.setText("Please enter a valid number");
					
				}
			}
		});
		branchid_field.setColumns(10);
		branchid_field.setBounds(141, 153, 130, 26);
		contentPane.add(branchid_field);
		
		bookid_field = new JTextField();
		bookid_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int j = Integer.parseInt(branchid_field.getText());
					valid_label2.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					valid_label2.setText("Please enter a valid number");
					
				}
			}
		});
		bookid_field.setColumns(10);
		bookid_field.setBounds(141, 211, 130, 26);
		contentPane.add(bookid_field);
		
		cardno_field = new JTextField();
		cardno_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int j = Integer.parseInt(cardno_field.getText());
					valid_label3.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					valid_label3.setText("Please enter a valid number");
					
				}
			}
		});
		cardno_field.setColumns(10);
		cardno_field.setBounds(471, 153, 130, 26);
		contentPane.add(cardno_field);
		
		uid_field = new JTextField();
		uid_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int j = Integer.parseInt(uid_field.getText());
					valid_label4.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					valid_label4.setText("Please enter a valid number");
					
				}
			}
		});
		uid_field.setColumns(10);
		uid_field.setBounds(471, 211, 130, 26);
		contentPane.add(uid_field);
		
	}
}
