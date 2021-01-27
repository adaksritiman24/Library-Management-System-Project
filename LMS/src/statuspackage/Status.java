package statuspackage;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.*;

public class Status {

	private JFrame frame;
	private JTable all;
	private JTable ov;
	private DefaultTableModel model_all;
	private DefaultTableModel model_ov;
	private Object[] columns = {"Book ID","Book Title","Student ID","Student Name","Student email","Issue Date","Return Date"};
	private Connection conn;
	private JTextField id;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Status window = new Status();
//		window.frame.setVisible(true);
//	}

	/**
	 * Create the application.
	 */
	public Status(Connection conn) {
		this.conn = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Status of Issued Books");
		frame.setResizable(false);
		frame.setBounds(100, 100, 945, 637);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Issued Books");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(42, 10, 847, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("All Issued Books :");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(40, 76, 180, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(42, 124, 847, 181);
		frame.getContentPane().add(scrollPane);
		
		
		all = new JTable();
		all.setBackground(new Color(200, 255, 200));
		model_all = new DefaultTableModel();
		model_all.setColumnIdentifiers(columns);
		all.setModel(model_all);
		//model_all.addRow(new Object[]{1,2,3,4,5,6,7});
		scrollPane.setViewportView(all);
		
		JLabel lblNewLabel_2 = new JLabel("Defaulters :");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(42, 326, 178, 38);
		frame.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(42, 374, 847, 181);
		frame.getContentPane().add(scrollPane_1);
		
		ov = new JTable();
		ov.setBackground(new Color(255, 150, 150));
		model_ov = new DefaultTableModel();
		model_ov.setColumnIdentifiers(columns);
		ov.setModel(model_ov);
		//model_ov.addRow(new Object[]{1,2,3,4,5,6,7});
		scrollPane_1.setViewportView(ov);
		
		id = new JTextField();
		id.setBackground(new Color(255, 255, 204));
		id.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		id.setBounds(436, 76, 158, 32);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Student ID:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblNewLabel_3.setBounds(324, 76, 102, 32);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton search = new JButton("Search");
		search.setBackground(Color.LIGHT_GRAY);
		search.setFont(new Font("Tahoma", Font.BOLD, 15));
		search.setBounds(604, 76, 85, 32);
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchByID();
			}
		});
		
		frame.getContentPane().add(search);
		
		JButton refresh = new JButton("Refresh");
		refresh.setBackground(Color.LIGHT_GRAY);
		refresh.setFont(new Font("Tahoma", Font.BOLD, 15));
		refresh.setBounds(699, 76, 102, 32);
		
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rc = model_all.getRowCount();
				for(int i = rc-1;i>=0;i--) {
					model_all.removeRow(i);
				}
				showAll();
			}
		});
		
		frame.getContentPane().add(refresh);
		
		showAll();
		showDefaulters();
		
		frame.setVisible(true);
	}
	public void showAll() {
		
		Object row[] = new Object[7];
		try {
			Statement stmt = conn.createStatement();
			String query = "select b.id,b.title,s.id,s.name,s.email,bs.i_date,bs.r_date from book_student bs inner join student s on s.id = bs.s_id inner join books b on b.id = bs.b_id order by b.id";
			ResultSet rs =stmt.executeQuery(query);
			while (rs.next()) {
				row[0] = rs.getString("b.id");
				row[1] = rs.getString("b.title");
				row[2] = rs.getString("s.id");
				row[3] = rs.getString("s.name");
				row[4] = rs.getString("s.email");
				row[5] = rs.getString("bs.i_date");
				row[6] = rs.getString("bs.r_date");
				model_all.addRow(row);
			}
		}
		catch(Exception e) {
			System.out.println("Bullshit errors: "+e.getMessage());
		}
	}
	public void showDefaulters() {
		
		Object row[] = new Object[7];
		try {
			Statement stmt = conn.createStatement();
			String query = "select b.id,b.title,s.id,s.name,s.email,bs.i_date,bs.r_date from book_student bs inner join student s on s.id = bs.s_id inner join books b on b.id = bs.b_id where bs.r_date < curdate() order by bs.r_date";
			ResultSet rs =stmt.executeQuery(query);
			while (rs.next()) {
				row[0] = rs.getString("b.id");
				row[1] = rs.getString("b.title");
				row[2] = rs.getString("s.id");
				row[3] = rs.getString("s.name");
				row[4] = rs.getString("s.email");
				row[5] = rs.getString("bs.i_date");
				row[6] = rs.getString("bs.r_date");
				model_ov.addRow(row);
			}
		}
		catch(Exception e) {
			System.out.println("Bullshit errors: "+e.getMessage());
		}
	}
	public void fetchByID() {
		Object row[] = new Object[7];
		try {
			
			String query = "select b.id,b.title,s.id,s.name,s.email,bs.i_date,bs.r_date from book_student bs inner join student s on s.id = bs.s_id inner join books b on b.id = bs.b_id where s.id='"+id.getText()+"' order by bs.r_date";
			PreparedStatement stmt = conn.prepareStatement(query);
			//stmt.setString(1,"10");
			ResultSet rs =stmt.executeQuery(query);
			
			int rc = model_all.getRowCount();
			for(int i = rc-1;i>=0;i--) {
				model_all.removeRow(i);
			}
			
			while (rs.next()) {
				row[0] = rs.getString("b.id");
				row[1] = rs.getString("b.title");
				row[2] = rs.getString("s.id");
				row[3] = rs.getString("s.name");
				row[4] = rs.getString("s.email");
				row[5] = rs.getString("bs.i_date");
				row[6] = rs.getString("bs.r_date");
				model_all.addRow(row);
			}
		}
		catch(Exception e) {
			System.out.println("Bullshit errors: "+e.getMessage());
		}
	}
}
