package bookspackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Random;

import java.sql.*;

public class Books {

	private JFrame frame;
	private JTextField id;
	private JTextField title;
	private JTextField pages;
	private JTable table;
	private DefaultTableModel model;
	private Connection conn;

	/**
	 * Launch the application.
	 */
//	 public static void main(String[] args) {
//	 	Books window = new Books();
//	 	window.frame.setVisible(true);
//	 }

	/**
	 * Create the application.
	 */
	public Books(Connection conn) {
		this.conn = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize(){
		
		
		frame = new JFrame();
		frame.setTitle("Manage/View Book Records");
		frame.setResizable(false);
		frame.setBounds(100, 100, 651, 710);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 637, 673);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add / Remove Book");
		lblNewLabel.setBounds(81, 21, 471, 51);
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book ID");
		lblNewLabel_1.setBounds(81, 82, 150, 51);
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Title");
		lblNewLabel_2.setBounds(81, 143, 150, 51);
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("No. of Pages");
		lblNewLabel_3.setBounds(81, 204, 150, 51);
		lblNewLabel_3.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
		panel.add(lblNewLabel_3);
		
		id = new JTextField();
		id.setBounds(241, 82, 150, 51);
		id.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		panel.add(id);
		id.setColumns(10);
		
		title = new JTextField();
		title.setBounds(241, 143, 351, 51);
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		panel.add(title);
		title.setColumns(10);
		
		pages = new JTextField();
		pages.setBounds(241, 204, 150, 51);
		pages.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		panel.add(pages);
		pages.setColumns(10);
		
		JButton generate = new JButton("Generate an ID");
		generate.setBounds(413, 82, 179, 51);
		generate.setBackground(Color.CYAN);
		generate.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		panel.add(generate);
				
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				String r_string = GRS();
				id.setText(r_string);
			}
		});
		
		
		JButton remove = new JButton("Remove");
		remove.setBounds(81, 265, 196, 40);
		remove.setForeground(Color.WHITE);
		remove.setBackground(Color.RED);
		remove.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		panel.add(remove);
		
		JButton add = new JButton("Add Book");
		add.setBounds(356, 265, 196, 40);
		add.setForeground(Color.WHITE);
		add.setBackground(Color.GREEN);
		add.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBook();
			}
		});
		
		panel.add(add);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(45, 348, 546, 303);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(new Color(255, 222, 173));
		Object[] columns = {"ID","Title","No. of Pages"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		
		
		table.setModel(model);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		
		fetchAndShow();
		
		frame.setVisible(true);
		
		
	}
	public void fetchAndShow() {
		
		Object[] row = new Object[3];
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from books");
			
			while(rs.next()) {
				row[0] = rs.getString("id");
				row[1] = rs.getString("title");
				row[2] = rs.getInt("pages");
				model.addRow(row);
			}
		}
		catch(Exception e) {
			System.out.println("Cant retrieve data :"+e.getMessage());
		}
	}
	public void addBook() {
		try {
			String bid = id.getText();
			String btitle = title.getText();
			int bpages = Integer.parseInt(pages.getText());
			
			if(!bid.equals("") && !btitle.equals("")) {
				String query = "insert into books values(?,?,?)";
				
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1,bid);
				stmt.setString(2,btitle);
				stmt.setInt(3,bpages);
				int i = stmt.executeUpdate();
				//System.out.println(i+ " Record inserted Successfully");
				
				//Refresh the table
				int rc = model.getRowCount();
				for(i = rc-1;i>=0;i--) {
					model.removeRow(i);
				}
				fetchAndShow();
				id.setText("");
				title.setText("");
				pages.setText("");
				JOptionPane.showMessageDialog(null,"Book Successfully added.","Success !",JOptionPane.PLAIN_MESSAGE);
			}
			
		}catch(Exception e) {
			//System.out.println("Cant enter data");
			JOptionPane.showMessageDialog(null,"Can't Enter Data\nPlease re-verify the Details.\nEnsure: Book id must be unique.","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void delete() {
		try {
			int r = table.getSelectedRow();
			String bid = model.getValueAt(r,0).toString();
			
			String query ="Delete from books where id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,bid);
			stmt.executeUpdate();
			
			//Refresh the table;
			int rc = model.getRowCount();
			for(int i = rc-1;i>=0;i--) {
				model.removeRow(i);
			}
			fetchAndShow();
			
		}catch(Exception e) {
			//System.out.println("Cant be deleted: "+e.getMessage());
			JOptionPane.showMessageDialog(null,"Can't be removed!\nBook is currently issued by someone.","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	public String GRS() {
		String upper = "QWERTYUIOPASDFGHJKLZXCVBNM";
		String lower = upper.toLowerCase();
		String nums = "1234567890";
		int[] r_index = new int[6];
		Random rand= new Random();
		r_index[0] = rand.nextInt(upper.length());
		r_index[1] = rand.nextInt(upper.length());
		r_index[2] = rand.nextInt(upper.length());
		r_index[3] = rand.nextInt(upper.length());
		r_index[4] = rand.nextInt(nums.length());
		r_index[5] = rand.nextInt(nums.length());
		char[] r_char = new char[6];
		r_char[0] = upper.charAt(r_index[0]);
		r_char[1] = upper.charAt(r_index[1]);
		r_char[2] = lower.charAt(r_index[2]);
		r_char[3] = lower.charAt(r_index[3]);
		r_char[4] = nums.charAt(r_index[4]);
		r_char[5] = nums.charAt(r_index[5]);
		return new String(r_char);
	}
}
