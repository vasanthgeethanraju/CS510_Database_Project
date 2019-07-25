 package Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DataModel extends JFrame {

	private JPanel contentPane;
	private JTextField bookid_field;
	private JTextField authorname_field;
	private JTextField title_field;
	private JTextField bookcopy_field;
	private JTextField uid_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataModel frame = new DataModel();
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
	public DataModel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 621, 470);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Validation_label = new JLabel("");
		Validation_label.setForeground(Color.RED);
		Validation_label.setEnabled(false);
		Validation_label.setBounds(389, 116, 165, 16);
		
		contentPane.add(Validation_label);
		
		JLabel validation_label1 = new JLabel("");
		validation_label1.setForeground(Color.RED);
		validation_label1.setEnabled(false);
		validation_label1.setBounds(148, 66, 165, 16);
		contentPane.add(validation_label1);
		
		bookid_field = new JTextField();
		bookid_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int i = Integer.parseInt(bookid_field.getText());
					validation_label1.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					validation_label1.setText("Please enter a valid number");
					
				}
			}
		});
		bookid_field.setBounds(154, 38, 130, 26);
		
		
		contentPane.add(bookid_field);
		bookid_field.setColumns(10);
		
		authorname_field = new JTextField();
		authorname_field.setBounds(154, 94, 130, 26);
		contentPane.add(authorname_field);
		authorname_field.setColumns(10);
		
		title_field = new JTextField();
		title_field.setBounds(154, 147, 130, 26);
		contentPane.add(title_field);
		title_field.setColumns(10);
		
		JComboBox Publishername_combobox = new JComboBox();
		Publishername_combobox.addItem("Bantam Books");
		Publishername_combobox.addItem("George Newnes");
		Publishername_combobox.addItem("Rupa Publications");
		Publishername_combobox.setBounds(154, 206, 130, 27);
		contentPane.add(Publishername_combobox);
		
		bookcopy_field = new JTextField();
		bookcopy_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int j = Integer.parseInt(bookcopy_field.getText());
					Validation_label.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					Validation_label.setText("Please enter a valid number");
					
				}
			}
		});
		bookcopy_field.setBounds(417, 88, 130, 26);
		contentPane.add(bookcopy_field);
		bookcopy_field.setColumns(10);
		
		JComboBox branchname_combobox = new JComboBox();
		branchname_combobox.addItem("100");
		branchname_combobox.addItem("101");
		branchname_combobox.addItem("102");
		branchname_combobox.addItem("103");
		branchname_combobox.setBounds(417, 172, 130, 27);
		contentPane.add(branchname_combobox);
		
		JButton btnUpdate = new JButton("Insert");
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Connection connect = null;
				PreparedStatement ps = null;
				PreparedStatement ps1 = null;
				try {
					connect = DriverManager
					        .getConnection("jdbc:mysql://localhost:3307/SP?"
					        		+ " verifyServerCertificate=false&useSSL=true&"
					                + "user=msandbox&password=cs510sum19&"
					        		+ "serverTimezone=UTC");
					
					String query = "insert into Books (BookID, AuthorName, Title, Name, UID)"
							+ "values (?, ?, ?, ?, ?)";
					
					ps = connect.prepareStatement(query);
					ps.setString(1, bookid_field.getText());
					ps.setString(2, authorname_field.getText());
					ps.setString(3, title_field.getText());
					ps.setObject(4, Publishername_combobox.getSelectedItem());
					ps.setString(5, uid_field.getText());
					ps.execute();
					
					String query1 = "INSERT INTO Book_Copies (NoOfCopies, BookID, BranchID, UID)"
						    + " values (?,?,?,?)";
					
					ps1 = connect.prepareStatement(query1);

					ps1.setInt(1, Integer.parseInt(bookcopy_field.getText()));
					ps1.setInt(2, Integer.parseInt(bookid_field.getText()));
					ps1.setObject(3, branchname_combobox.getSelectedItem());
					ps1.setString(4, uid_field.getText());
					ps1.execute();
					
					JOptionPane.showMessageDialog(null, "Book and Copies Inserted Successfully :) ");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		
		btnUpdate.setBounds(306, 320, 117, 29);
		contentPane.add(btnUpdate);
		
		JLabel lblInsertBooks = new JLabel("INSERT BOOKS");
		lblInsertBooks.setForeground(Color.WHITE);
		lblInsertBooks.setVerticalAlignment(SwingConstants.BOTTOM);
		lblInsertBooks.setBounds(287, 6, 107, 16);
		contentPane.add(lblInsertBooks);
		
		JLabel lblBookid = new JLabel("Book ID");
		lblBookid.setForeground(Color.CYAN);
		lblBookid.setBounds(28, 43, 61, 16);
		contentPane.add(lblBookid);
		
		
		
		JLabel lblAuthorName = new JLabel("Author Name");
		lblAuthorName.setForeground(new Color(0, 255, 255));
		lblAuthorName.setBounds(28, 99, 94, 16);
		contentPane.add(lblAuthorName);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(Color.CYAN);
		lblTitle.setBounds(28, 152, 61, 16);
		contentPane.add(lblTitle);
		
		JLabel lblPublisherName = new JLabel("Publisher Name");
		lblPublisherName.setForeground(Color.CYAN);
		lblPublisherName.setBounds(28, 210, 114, 16);
		
		contentPane.add(lblPublisherName);
		
		JButton home_button = new JButton("Home");
		home_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
			}
		});
		home_button.setBounds(28, 394, 171, 26);
		contentPane.add(home_button);
		
		
		
		JLabel lblHowManyCopies = new JLabel("How many copies of books?  ");
		lblHowManyCopies.setForeground(Color.CYAN);
		lblHowManyCopies.setBounds(391, 60, 184, 16);
		contentPane.add(lblHowManyCopies);
		
		JLabel lblToWhichBranch = new JLabel("To which branch?");
		lblToWhichBranch.setForeground(Color.CYAN);
		lblToWhichBranch.setBounds(427, 135, 117, 16);
		contentPane.add(lblToWhichBranch);
		
		JButton btnNewButton = new JButton("Display Books");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayBooks db = new DisplayBooks();
				db.setVisible(true);
			}
		});
		btnNewButton.setBounds(341, 393, 234, 29);
		contentPane.add(btnNewButton);
		
		JLabel valid_label = new JLabel("");
		valid_label.setForeground(Color.RED);
		valid_label.setEnabled(false);
		valid_label.setBounds(137, 297, 165, 16);
		contentPane.add(valid_label);
		
		
		JLabel uid_label = new JLabel("User ID (UID)");
		uid_label.setForeground(Color.CYAN);
		uid_label.setBounds(28, 276, 114, 16);
		contentPane.add(uid_label);
		
		uid_field = new JTextField();
		uid_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int i = Integer.parseInt(uid_field.getText());
					valid_label.setText("");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					valid_label.setText("Please enter a valid number");
					
				}
			}
		});
		uid_field.setColumns(10);
		uid_field.setBounds(154, 271, 130, 26);
		contentPane.add(uid_field);
		
		
		
		
		
		
		
	}
}
