import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.awt.event.*;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import java.sql.*;

import bookspackage.Books;
import issuepackage.Issue;
import studentspackage.Students;
import returnpackage.Return;
import statuspackage.Status;

public class App {
	private JButton books;
	private JFrame frame;
	private JButton student;
	private JButton issue;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		App window = new App();
		window.frame.setVisible(true);
	}
	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//make database connections;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/lms";
			String username = "root";
			String pwd = "AdakSriti69";
			conn = DriverManager.getConnection(url,username, pwd);
		}
		catch(Exception e) {
			//System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Unable to Connect to database !\nPlease start the Mysql Server and Retry.","ERROR",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			
		}
		
		frame = new JFrame();
		frame.setTitle("Library Management System by Sritiman Adak");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1024, 696);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(0, 0, 1010, 659);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Library Management System");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Trebuchet MS", Font.BOLD, 32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(40, 35, 926, 86);
		panel.add(title);
		
		books = new JButton("Books");
		books.setBackground(Color.LIGHT_GRAY);
		books.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		books.setMargin(new Insets(0, 0, 0, 0));
		Image img = new ImageIcon(this.getClass().getResource("img/book.png")).getImage();
		books.setIcon(new ImageIcon(img));
		books.setBounds(40, 163, 247, 197);

		books.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Books(conn);
			}
		});
		
		panel.add(books);
		
		JButton return_books = new JButton("Return");
		return_books.setBackground(Color.LIGHT_GRAY);
		return_books.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		return_books.setMargin(new Insets(0, 0, 0, 0));
		Image img4 = new ImageIcon(this.getClass().getResource("img/return.png")).getImage();
		return_books.setIcon(new ImageIcon(img4));
		return_books.setBounds(40, 405, 247, 197);
		return_books.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Return(conn);
			}
		});
		panel.add(return_books);
		
		student = new JButton("Students");
		student.setBackground(Color.LIGHT_GRAY);
		student.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		student.setMargin(new Insets(0, 0, 0, 0));
		Image img2 = new ImageIcon(this.getClass().getResource("img/student.png")).getImage();
		student.setIcon(new ImageIcon(img2));
		student.setBounds(382, 163, 247, 197);
		
		student.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Students(conn);
			}
		});
		
		panel.add(student);
		
		issue = new JButton("Issue");
		issue.setBackground(Color.LIGHT_GRAY);
		issue.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		issue.setMargin(new Insets(0, 0, 0, 0));
		Image img3 = new ImageIcon(this.getClass().getResource("img/issue.png")).getImage();
		issue.setIcon(new ImageIcon(img3));
		issue.setBounds(726, 163, 240, 197);
		

		issue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Issue(conn);
			}
		});
		panel.add(issue);
		
		JButton status = new JButton("Status");
		status.setBackground(Color.LIGHT_GRAY);
		status.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		status.setMargin(new Insets(0, 0, 0, 0));
		Image img5 = new ImageIcon(this.getClass().getResource("img/stats.png")).getImage();
		status.setIcon(new ImageIcon(img5));
		status.setBounds(382, 405, 247, 197);
		
		status.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Status(conn);
			}
		});
		
		panel.add(status);
		
		JButton exit = new JButton("Exit");
		exit.setBackground(Color.LIGHT_GRAY);
		exit.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		exit.setMargin(new Insets(0, 0, 0, 0));
		Image img6 = new ImageIcon(this.getClass().getResource("img/exit.png")).getImage();
		exit.setIcon(new ImageIcon(img6));
		exit.setBounds(726, 405, 240, 197);
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		panel.add(exit);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(40, 131, 927, 8);
		panel.add(separator);
	}
}
