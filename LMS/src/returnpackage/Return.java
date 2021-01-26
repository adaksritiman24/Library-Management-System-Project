package returnpackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.*;
import java.sql.*;



public class Return {

	private JFrame frame;
	private JTextField id;
	private JTextField title;
	private Connection conn;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Return window = new Return();
//		window.frame.setVisible(true);
//	}

	/**
	 * Create the application.
	 */
	public Return(Connection conn) {
		this.conn = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Return Book");
		frame.setResizable(false);
		frame.setBounds(100, 100, 541, 360);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Return Book");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 10, 517, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book ID");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(64, 78, 114, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		id = new JTextField();
		id.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		id.setBounds(188, 76, 131, 44);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		title = new JTextField();
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		title.setBounds(188, 130, 272, 48);
		frame.getContentPane().add(title);
		title.setColumns(10);
		title.setEditable(false);
		
		JButton search = new JButton("Search");
		search.setBackground(Color.ORANGE);
		search.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		search.setBounds(329, 76, 131, 44);
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		frame.getContentPane().add(search);
		
		
		JLabel lblNewLabel_2 = new JLabel("Title");
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(64, 133, 114, 45);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton rb = new JButton("Return Book");
		rb.setForeground(Color.YELLOW);
		rb.setBackground(Color.BLACK);
		rb.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		rb.setBounds(64, 234, 214, 48);
		rb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				returnBook();
			}
		});
		
		frame.getContentPane().add(rb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(64, 206, 396, 2);
		frame.getContentPane().add(separator);
		
		frame.setVisible(true);
	}
	public void search() {
		try {
			String bid = id.getText();
			String query = "select title from books where id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,bid);
			ResultSet rs = stmt.executeQuery(); 
			rs.next();
			title.setText(rs.getString("title"));
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Book Not Found!","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void returnBook() {
		try {
			if(!id.getText().isEmpty() && !title.getText().isEmpty()) {
				String b_id = id.getText();
				String query = "delete from book_student where b_id=?";
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1,b_id);
				int bs = stmt.executeUpdate();
				System.out.println(bs);
				JOptionPane.showMessageDialog(null,"Book Successfully Returned!","Done",JOptionPane.PLAIN_MESSAGE);
				
				id.setText("");
				title.setText("");
			}
			else {
				JOptionPane.showMessageDialog(null,"Please provide the Book Details.","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e) {
			System.out.println("Can't execute query: "+e.getMessage());
		}
	}
}
