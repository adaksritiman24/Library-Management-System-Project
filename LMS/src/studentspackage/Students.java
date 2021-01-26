package studentspackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.sql.*;

public class Students {

	private JFrame frame;
	private JTextField id;
	private JTextField name;
	private JComboBox dept;
	private JTextField email;
	private JTextField phone;
	private JTable table;
	private DefaultTableModel model;
	private Connection conn;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Students window = new Students();
//		window.frame.setVisible(true);
//	}

	/**
	 * Create the application.
	 */
	public Students(Connection conn) {
		this.conn = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Manage/View Student Records");
		frame.setResizable(false);
		frame.setBounds(100, 100, 636, 766);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add/Remove Student");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 27));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(41, 25, 538, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(41, 94, 172, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		id = new JTextField();
		id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		id.setBounds(223, 94, 159, 42);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(41, 146, 172, 42);
		frame.getContentPane().add(lblNewLabel_2);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name.setBackground(new Color(255, 255, 255));
		name.setBounds(223, 146, 342, 42);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Department");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(41, 198, 172, 42);
		frame.getContentPane().add(lblNewLabel_3);
		
		dept = new JComboBox();
		dept.setFont(new Font("Tahoma", Font.BOLD, 16));
		dept.setModel(new DefaultComboBoxModel(new String[] {"EE", "ECE", "IT", "CSE", "ME", "CE", "EIE"}));
		dept.setBounds(223, 198, 159, 42);
		frame.getContentPane().add(dept);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(41, 250, 172, 42);
		frame.getContentPane().add(lblNewLabel_4);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 16));
		email.setBounds(223, 250, 342, 42);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Phone No.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(41, 302, 172, 42);
		frame.getContentPane().add(lblNewLabel_5);
		
		phone = new JTextField();
		phone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phone.setBounds(223, 302, 342, 42);
		frame.getContentPane().add(phone);
		phone.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(41, 420, 538, 284);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(new Color(255, 222, 173));
		Object[] columns = {"ID","Name","Department","Email","Phone No."};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		
		table.setModel(model);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		
		JButton remove = new JButton("Remove");
		remove.setBackground(Color.YELLOW);
		remove.setFont(new Font("Tahoma", Font.PLAIN, 18));
		remove.setBounds(41, 354, 231, 42);
		
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		frame.getContentPane().add(remove);
		
		JButton add = new JButton("Add");
		add.setBackground(Color.GREEN);
		add.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add.setBounds(348, 354, 231, 42);
		frame.getContentPane().add(add);
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent();
			}
		});
		
		fetchAndShow();
		
		frame.setVisible(true);
	}
	public void fetchAndShow() {
		
		Object[] row = new Object[5];
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			
			while(rs.next()) {
				row[0] = rs.getString("id");
				row[1] = rs.getString("name");
				row[2] = rs.getString("dept");
				row[3] = rs.getString("email");
				row[4] = rs.getString("phone");
				model.addRow(row);
			}
		}
		catch(Exception e) {
			System.out.println("Cant retrieve data :"+e.getMessage());
		}
	}
	public void addStudent() {
		try {
			String sid = id.getText();
			String sname = name.getText();
			String sdept = dept.getSelectedItem().toString();
			String semail = email.getText();
			String sphone = phone.getText();
			
			if(!sid.equals("") && !sname.equals("") && !semail.equals("") && !sphone.equals("")) {
				String query = "insert into student values(?,?,?,?,?)";
				
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1,sid);
				stmt.setString(2,sname);
				stmt.setString(3,sdept);
				stmt.setString(4,semail);
				stmt.setString(5,sphone);
				
				int i = stmt.executeUpdate();
				//System.out.println(i+ " Record inserted Successfully");
				
				//Refresh the table
				int rc = model.getRowCount();
				for(i = rc-1;i>=0;i--) {
					model.removeRow(i);
				}
				fetchAndShow();
				id.setText("");
				name.setText("");
				email.setText("");
				phone.setText("");
				JOptionPane.showMessageDialog(null,"Successfully added.","Success !",JOptionPane.PLAIN_MESSAGE);
			}
			
		}catch(Exception e) {
			//System.out.println("Cant enter data");
			JOptionPane.showMessageDialog(null,"Can't Enter Data\nPlease re-verify the Details","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void delete() {
		try {
			int r = table.getSelectedRow();
			String sid = model.getValueAt(r,0).toString();
			
			String query ="Delete from student where id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,sid);
			stmt.executeUpdate();
			
			//Refresh the table;
			int rc = model.getRowCount();
			for(int i = rc-1;i>=0;i--) {
				model.removeRow(i);
			}
			fetchAndShow();
			
		}catch(Exception e) {
			//System.out.println("Cant be deleted: "+e.getMessage());
			JOptionPane.showMessageDialog(null,"Can't Delete!\nThe Student has not returned all books.","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
}
