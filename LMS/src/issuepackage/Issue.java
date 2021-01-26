package issuepackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.*;

public class Issue {

	private JFrame frame;
	private JTextField s_id;
	private JTextField name;
	private JTextField b_id;
	private JTextField title;
	private JDateChooser issue_date;
	private JDateChooser return_date;
	private Connection conn;
	/**
	 * @wbp.nonvisual location=138,504
	 */

	/**
	 * Launch the application.
	 */
//	 public static void main(String[] args) {
//	 		Issue window = new Issue();
//	 		window.frame.setVisible(true);
//	 }

	/**
	 * Create the application.
	 */
	public Issue(Connection conn) {
		this.conn = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Issue book");
		frame.setResizable(false);
		frame.setBounds(100, 100, 682, 625);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Issue Book");
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(65, 10, 535, 72);
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 648, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Student");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		lblNewLabel_1.setBounds(20, 91, 119, 41);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("id");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(165, 142, 71, 32);
		frame.getContentPane().add(lblNewLabel_2);
		
		s_id = new JTextField();
		s_id.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		s_id.setBounds(246, 143, 119, 32);
		frame.getContentPane().add(s_id);
		s_id.setColumns(10);
		
		JButton g_student = new JButton("Get Details");
		g_student.setFont(new Font("Tahoma", Font.BOLD, 17));
		g_student.setForeground(Color.WHITE);
		g_student.setBackground(Color.DARK_GRAY);
		g_student.setBounds(390, 142, 179, 32);
		
		g_student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchStudent();
			}
		});
		
		frame.getContentPane().add(g_student);
		
		name = new JTextField();
		name.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		name.setBounds(246, 185, 263, 32);
		frame.getContentPane().add(name);
		name.setColumns(10);
		name.setEditable(false);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(165, 184, 71, 32);
		frame.getContentPane().add(lblNewLabel_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 239, 648, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblNewLabel_4 = new JLabel("Book");
		lblNewLabel_4.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		lblNewLabel_4.setBounds(20, 251, 119, 41);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("id");
		lblNewLabel_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(165, 308, 71, 32);
		frame.getContentPane().add(lblNewLabel_5);
		
		b_id = new JTextField();
		b_id.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		b_id.setBounds(246, 308, 119, 31);
		frame.getContentPane().add(b_id);
		b_id.setColumns(10);
		
		JButton g_book = new JButton("Get Details");
		g_book.setFont(new Font("Tahoma", Font.BOLD, 17));
		g_book.setForeground(Color.WHITE);
		g_book.setBackground(Color.DARK_GRAY);
		g_book.setBounds(390, 308, 179, 32);
		
		g_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchBook();
			}
		});
		
		frame.getContentPane().add(g_book);
		
		JLabel lblNewLabel_6 = new JLabel("Title");
		lblNewLabel_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(165, 350, 71, 32);
		frame.getContentPane().add(lblNewLabel_6);
		
		title = new JTextField();
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		title.setBounds(246, 349, 263, 33);
		frame.getContentPane().add(title);
		title.setColumns(10);
		title.setEditable(false);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 402, 648, 2);
		frame.getContentPane().add(separator_2);
		
		JLabel lblNewLabel_7 = new JLabel("Issue Date");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_7.setBounds(20, 414, 119, 32);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Return Date");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_8.setBounds(20, 456, 119, 32);
		frame.getContentPane().add(lblNewLabel_8);
		
		issue_date = new JDateChooser();
		issue_date.setBounds(164, 414, 179, 32);
		issue_date.setDateFormatString("yyyy-MM-dd");
		frame.getContentPane().add(issue_date);
		
		return_date = new JDateChooser();
		return_date.setBounds(164, 456, 179, 32);
		return_date.setDateFormatString("yyyy-MM-dd");
		frame.getContentPane().add(return_date);
		
		JButton ib = new JButton("Get Book Issued");
		ib.setBackground(Color.GREEN);
		ib.setFont(new Font("Verdana", Font.BOLD, 20));
		
		ib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				issueBook();
			}
		});
		ib.setBounds(165, 521, 344, 41);
		frame.getContentPane().add(ib);
		
		frame.setVisible(true);
	}
	public void fetchStudent() {
		try {
			String sid = s_id.getText();
			String query = "select name from student where id=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,sid);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			name.setText(rs.getString("name"));
		}
		catch(Exception e) {
			//System.out.println("No results found!");
			name.setText("");
			JOptionPane.showMessageDialog(null,"Student Not found!","ERROR",JOptionPane.ERROR_MESSAGE);
			
		}
 	}
	public void fetchBook() {
		try {
			String bid = b_id.getText();
			String query = "select title from books where id=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,bid);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			title.setText(rs.getString("title"));
		}
		catch(Exception e) {
			//System.out.println("No results found!");
			title.setText("");
			JOptionPane.showMessageDialog(null,"Book Not found!","ERROR",JOptionPane.ERROR_MESSAGE);
			
		}
 	}
	public void issueBook() {
		try {
			String sid = s_id.getText();
			String bid = b_id.getText();
			String g_name = name.getText();
			String g_title = title.getText();
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String i_d = df.format(issue_date.getDate());
			String r_d = df.format(return_date.getDate());
			
			if(!sid.isEmpty() && !bid.isEmpty() && !g_name.isEmpty() && !g_title.isEmpty()) {
				String query="insert into book_student(b_id,s_id,i_date,r_date) values (?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1,bid);
				ps.setString(2,sid);
				ps.setString(3,i_d);
				ps.setString(4,r_d);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Book Issued Successfully!","Success",JOptionPane.PLAIN_MESSAGE);
				
				s_id.setText("");
				b_id.setText("");
				name.setText("");
				title.setText("");
			}
			else {
//				System.out.println("");
				JOptionPane.showMessageDialog(null,"Please fill details properly","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Book Currently not Availale!","ERROR",JOptionPane.ERROR_MESSAGE);
			//System.out.println("Sql exception: "+e.getMessage());
		}
		catch(Exception e) {
//			System.out.println("Date not filled properly: "+e.getMessage());
			JOptionPane.showMessageDialog(null,"Please fill details properly","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
}
