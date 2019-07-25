package Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 621, 470);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGeethansLibrary = new JLabel("GEETHAN'S LIBRARY");
		lblGeethansLibrary.setForeground(new Color(255, 255, 255));
		lblGeethansLibrary.setBackground(new Color(255, 255, 255));
		lblGeethansLibrary.setBounds(253, 25, 143, 16);
		contentPane.add(lblGeethansLibrary);
		
		JButton branches_button = new JButton("Branches");
		branches_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Library_Branches lb = new Library_Branches();
				lb.setVisible(true);
			}
		});
		branches_button.setBounds(41, 89, 117, 29);
		contentPane.add(branches_button);
		
		JButton publisher_button = new JButton("Publishers");
		publisher_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Publisher p = new Publisher();
			p.setVisible(true);
			}
		});
		publisher_button.setBounds(41, 315, 117, 29);
		contentPane.add(publisher_button);
		
		JButton borrowers_button = new JButton("Borrowers");
		borrowers_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrowers b = new Borrowers();
				b.setVisible(true);
			}
		});
		borrowers_button.setBounds(450, 89, 117, 29);
		contentPane.add(borrowers_button);
		
		JButton loan_button = new JButton("Return Books");
		loan_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnBooks rb = new ReturnBooks();
				rb.setVisible(true);
			}
		});
		loan_button.setBounds(450, 315, 117, 29);
		contentPane.add(loan_button);
		
		JButton copies_button = new JButton("Check Out");
		copies_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckOut co = new CheckOut();
				co.setVisible(true);
			}
		});
		copies_button.setBounds(253, 141, 117, 29);
		contentPane.add(copies_button);
		
		JButton books_button = new JButton("Books");
		books_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DataModel dm = new DataModel();
				dm.setVisible(true);
			}
		});
		books_button.setBounds(253, 265, 117, 29);
		contentPane.add(books_button);
	}

}
