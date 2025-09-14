package studentmanagementsystem;

import java.awt.EventQueue;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JToolBar;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.print.PrintException;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import oracle.jdbc.OracleResultSet;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class admindashboard {

	JFrame adminframe;
	private JLabel lblday;
	private JLabel lblclock;
	protected AbstractButton testpanel;
	protected String[][] data;
	private JTextField tf9;
	private JTextField tf10;
	private JTextField tf8;
	private JTextField tf7;
	private JTextField tfsf2;
	private JTextField tf6;
	private JTextField tf5;
	private JTextField tf4;
	private JTextField tf3;
	private JTextField tf2;
	private JTextField tf1;
	private JTextField tfsf1;
	private JTable adminstutable;
	private JTable adminfacultytable;
	private JTextField tf11;
	private JTextField tf14;
	private JTextField tf12;
	private JTextField tf13;
	private JTextField tfsf3;
	private JTable admincoursetable;
	private JTextField sid2;
	private JTextField stsf4;
	private JComboBox fc1;
	private JComboBox fcc2;
	private JComboBox fcc3;
	private JComboBox fcc4;
	private JComboBox fcc5;
	private JComboBox c1;
	private JComboBox c2;
	private JComboBox c3;
	private JComboBox c4;
	private JComboBox c5;
	private JTable adminsubjecttable;
	private JTextField stupass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admindashboard window = new admindashboard();
					window.adminframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for(;;) {
					Calendar cal = new GregorianCalendar();
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					lblclock.setText("Time:- "+hour+":"+minute+":"+second);
					sleep(1000);}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		clock.start();
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		lblday.setText("Date:- "+day+"/"+month+"/"+year);
	}
	
	public void clearcb() {
		int itemCount = fc1.getItemCount();

		for(int i = 1; i < itemCount; i++){
		  fc1.removeItemAt(1);
		  fcc2.removeItemAt(1);
		  fcc3.removeItemAt(1);
		  fcc4.removeItemAt(1);
		  fcc5.removeItemAt(1);
		}		
	}
	
	public void clearcb2() {
		int itemCount = c1.getItemCount();

		for(int i = 1; i < itemCount; i++){
		  c1.removeItemAt(1);
		  c2.removeItemAt(1);
		  c3.removeItemAt(1);
		  c4.removeItemAt(1);
		  c5.removeItemAt(1);
		}		
	}
	
	public void refresh() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT sid,sname,gender,age,email,phone,address,spass FROM student order by sid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) adminstutable.getModel();
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
	
			while(rs.next()) {
				String sid=rs.getString(1);
				String sname=rs.getString(2);
				String gender=rs.getString(3);
				String age=rs.getString(4);
				String email=rs.getString(5);
				String phone=rs.getString(6);
				String address=rs.getString(7);
				String spass=rs.getString(8);
				String[] row= {sid,sname,gender,age,email,phone,address,spass};
				model.addRow(row);
			}
			conn.close();
			st.close();
		}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	}
	
	
	public void refresh2() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT sid,cid1,fid1,cid2,fid2,cid3,fid3,cid4,fid4,cid5,fid5 FROM student order by sid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) adminsubjecttable.getModel();
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
	
			while(rs.next()) {
				String sid=rs.getString(1);
				String cid1=rs.getString(2);
				String fid1=rs.getString(3);
				String cid2=rs.getString(4);
				String fid2=rs.getString(5);
				String cid3=rs.getString(6);
				String fid3=rs.getString(7);
				String cid4=rs.getString(8);
				String fid4=rs.getString(9);
				String cid5=rs.getString(10);
				String fid5=rs.getString(11);
				String[] row= {sid,cid1,fid1,cid2,fid2,cid3,fid3,cid4,fid4,cid5,fid5};
				model.addRow(row);
			}
			conn.close();
			st.close();
		}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	}
	
	public void getfacultyid() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT fid FROM faculty order by fid";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
	            String facultyid = rs.getString("fid");
	            fc1.addItem(facultyid);	           
	            fcc2.addItem(facultyid);
	            fcc3.addItem(facultyid);
	            fcc4.addItem(facultyid);
	            fcc5.addItem(facultyid);
	        }
			conn.close();
			st.close();
		}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	}
	
	public void getcourseid() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT cid FROM course order by cid";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
	            String courseid = rs.getString("cid");
	            c1.addItem(courseid);
	            c2.addItem(courseid);
	            c3.addItem(courseid);
	            c4.addItem(courseid);
	            c5.addItem(courseid);
	        }
			conn.close();
			st.close();
		}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	}
	
	public void refresh3() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT fid,fname,faddress,fphone,fpass from faculty order by fid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) adminfacultytable.getModel();
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
	
			while(rs.next()) {
				String fid=rs.getString(1);
				String fname=rs.getString(2);
				String faddress=rs.getString(3);
				String fphone=rs.getString(4);
				String fpass=rs.getString(5);
				String[] row= {fid,fname,faddress,fphone,fpass};
				model.addRow(row);
			}
			conn.close();
			st.close();
		}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	}
	
	
	public boolean empty() {	
		if(tf1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student ID is missing");
			return false;
		}
		if(tf2.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Name is missing");
			return false;
		}
		if(tf3.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Age is missing");
			return false;
		}
		if(!tf4.getText().matches("^.+@.+\\..+$")) {
			JOptionPane.showMessageDialog(null, "Invalid Email Address");
			return false;
		}
		if(tf5.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Phone Number is missing");
			return false;
		}
		if(tf5.getText().length()>=15) {
			JOptionPane.showMessageDialog(null, "Phone Number is too long");
			return false;
		}
		if(tf6.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Address ID is missing");
			return false;
		}
		if(stupass.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Password ID is missing");
			return false;
		}
		return true;
	}
	
	public boolean empty3() {
		if(tf7.getText().isEmpty() || tf7.getText().matches("SET ID")) {
			JOptionPane.showMessageDialog(null, "Faculty ID is missing");
			return false;
		}
		if(tf8.getText().isEmpty() || tf8.getText().matches("SET NAME")) {
			JOptionPane.showMessageDialog(null, "Faculty Name is missing");
			return false;
		}
		if(tf9.getText().isEmpty() || tf9.getText().matches("SET ADDRESS")) {
			JOptionPane.showMessageDialog(null, "Faculty Address is missing");
			return false;
		}
		if(tf10.getText().isEmpty() || tf10.getText().matches("SET PHONE")) {
			JOptionPane.showMessageDialog(null, "Faculty Phone Number is missing");
			return false;
		}
		if(tf11.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faculty Password is missing");
			return false;
		}
		return true;
	}
	
	public boolean empty2() {
		if(c1.getSelectedItem().toString()==c2.getSelectedItem().toString() || c1.getSelectedItem().toString()==c3.getSelectedItem().toString() || c1.getSelectedItem().toString()==c4.getSelectedItem().toString() || c1.getSelectedItem().toString()==c5.getSelectedItem().toString()) {
			JOptionPane.showMessageDialog(null, "SUBJECT ALREADY ENROLLED");
			return false;
		}
		if(c2.getSelectedItem().toString()==c1.getSelectedItem().toString() || c2.getSelectedItem().toString()==c3.getSelectedItem().toString() || c2.getSelectedItem().toString()==c4.getSelectedItem().toString() || c2.getSelectedItem().toString()==c5.getSelectedItem().toString()) {
			JOptionPane.showMessageDialog(null, "SUBJECT ALREADY ENROLLED");
			return false;
		}
		if(c3.getSelectedItem().toString()==c2.getSelectedItem().toString() || c3.getSelectedItem().toString()==c1.getSelectedItem().toString() || c3.getSelectedItem().toString()==c4.getSelectedItem().toString() || c3.getSelectedItem().toString()==c5.getSelectedItem().toString()) {
			JOptionPane.showMessageDialog(null, "SUBJECT ALREADY ENROLLED");
			return false;
		}
		if(c4.getSelectedItem().toString()==c2.getSelectedItem().toString() || c4.getSelectedItem().toString()==c3.getSelectedItem().toString() || c4.getSelectedItem().toString()==c1.getSelectedItem().toString() || c4.getSelectedItem().toString()==c5.getSelectedItem().toString()) {
			JOptionPane.showMessageDialog(null, "SUBJECT ALREADY ENROLLED");
			return false;
		}
		if(c5.getSelectedItem().toString()==c2.getSelectedItem().toString() || c5.getSelectedItem().toString()==c3.getSelectedItem().toString() || c5.getSelectedItem().toString()==c4.getSelectedItem().toString() || c5.getSelectedItem().toString()==c1.getSelectedItem().toString()) {
			JOptionPane.showMessageDialog(null, "SUBJECT ALREADY ENROLLED");
			return false;
		}
		if(c1.getSelectedItem().toString()=="SET CID1") {
			JOptionPane.showMessageDialog(null, "Please Select COURSE ID 1");
			return false;
		}
		if(c2.getSelectedItem().toString()=="SET CID2") {
			JOptionPane.showMessageDialog(null, "Please Select COURSE ID 2");
			return false;
		}
		if(c3.getSelectedItem().toString()=="SET CID3") {
			JOptionPane.showMessageDialog(null, "Please Select COURSE ID 3");
			return false;
		}
		if(c4.getSelectedItem().toString()=="SSET CID4") {
			JOptionPane.showMessageDialog(null, "Please Select COURSE ID 4");
			return false;
		}
		if(c5.getSelectedItem().toString()=="SET CID5") {
			JOptionPane.showMessageDialog(null, "Please Select COURSE ID 5");
			return false;
		}
		if(fc1.getSelectedItem().toString()=="SET") {
			JOptionPane.showMessageDialog(null, "MUST ADD Faculty ID TO COURSE 1");
			return false;
		}
		if(fcc2.getSelectedItem().toString()=="SET") {
			JOptionPane.showMessageDialog(null, "MUST ADD Faculty ID TO COURSE 2");
			return false;
		}
		if(fcc3.getSelectedItem().toString()=="SET") {
			JOptionPane.showMessageDialog(null, "MUST ADD Faculty ID TO COURSE 3");
			return false;
		}
		if(fcc4.getSelectedItem().toString()=="SET") {
			JOptionPane.showMessageDialog(null, "MUST ADD Faculty ID TO COURSE 4");
			return false;
		}
		if(fcc5.getSelectedItem().toString()=="SET") {
			JOptionPane.showMessageDialog(null, "MUST ADD Faculty ID TO COURSE 5");
			return false;
		}
		return true;
	}
	
	public boolean empty5() {
		if(tf13.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "INSERT COURSE ID");
			return false;
		}
		if(tf14.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "INSERT COURSE NAME");
			return false;
		}
		return true;
	}
	
	public void refresh5() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT * from course order by cid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) admincoursetable.getModel();
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
	
			while(rs.next()) {
				String ciid=rs.getString(1);
				String ciidname=rs.getString(2);
				String[] row= {ciid,ciidname};
				model.addRow(row);
			}
			conn.close();
			st.close();
		}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	}

	/**
	 * Create the application.
	 */
	public admindashboard() {
		initialize();
		clock();
		refresh();
		refresh2();
		refresh3();
		refresh5();
		getfacultyid();
		getcourseid();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		adminframe = new JFrame();
		adminframe.setTitle("admindashboard - StudentManagementSystem");
		adminframe.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\S A Z\\Desktop\\java icons\\icons\\software icon.png"));
		adminframe.setResizable(false);
		adminframe.getContentPane().setBackground(new Color(90, 190, 250));
		adminframe.setBounds(100, 100, 1248, 766);
		adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBackground(new Color(0, 217, 217));
		
		lblday = new JLabel("Date:- 13/11/2023");
		lblday.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		
		lblclock = new JLabel("New label");
		lblclock.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 178, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addComponent(lblday, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblclock, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 53, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblclock)
					.addGap(3)
					.addComponent(lblday)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminframe.dispose();
				main main1 = new main();
				main1.selectlogin.setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JButton btnLogout_1 = new JButton("EXIT");
		btnLogout_1.setSelectedIcon(new ImageIcon(""));
		btnLogout_1.setForeground(new Color(0, 0, 0));
		btnLogout_1.setBackground(new Color(240, 240, 240));
		btnLogout_1.setIcon(new ImageIcon(""));
		btnLogout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminframe.dispose();			}
		});
		btnLogout_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JLabel lblAdminDashboard = new JLabel("WELCOME - ADMIN DASHBOARD");
		lblAdminDashboard.setFont(new Font("Tahoma", Font.BOLD, 43));
		GroupLayout groupLayout = new GroupLayout(adminframe.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(lblAdminDashboard, GroupLayout.PREFERRED_SIZE, 724, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnLogout_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1212, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLogout_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
							.addGap(4))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAdminDashboard, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		Image images = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image imagesss = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image img1 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image imagess = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image img3 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		
		JPanel panel_4_1_1_1_2 = new JPanel();
		panel_4_1_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1_1_1_2.setBackground(new Color(0, 217, 217));
		tabbedPane.addTab("Student", null, panel_4_1_1_1_2, null);
		
		JPanel panel_1_2_1_1_1_2 = new JPanel();
		panel_1_2_1_1_1_2.setForeground(new Color(70, 155, 249));
		panel_1_2_1_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1_1_2.setBackground(new Color(0, 217, 217));
		
		JLabel lblAge_1_1_1_1_2 = new JLabel("Age :- ");
		lblAge_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblEmail_1_1_1_1_2 = new JLabel("Email :- ");
		lblEmail_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblPhone_1_1_1_1_2 = new JLabel("Phone :- ");
		lblPhone_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblAddress_1_1_1_1_2 = new JLabel("Address :- ");
		lblAddress_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tf6 = new JTextField();
		tf6.setColumns(10);
		
		tf5 = new JTextField();
		tf5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tf5.setColumns(10);
		
		tf4 = new JTextField();
		tf4.setColumns(10);
		
		JButton btnClear_1_1_1_1_2 = new JButton("Clear");
		btnClear_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf1.setText(null);
				tf2.setText(null);
				tf3.setText(null);
				tf4.setText(null);
				tf5.setText(null);
				tf6.setText(null);
				stupass.setText(null);
			}
		});
		btnClear_1_1_1_1_2.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		tf3 = new JTextField();
		tf3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tf3.setColumns(10);
		
		JLabel lblGender_1_1_1_1_2 = new JLabel("Gender :- ");
		lblGender_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Student Name :- ");
		lblNewLabel_2_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2 = new JLabel("Student ID :- ");
		lblStudentId_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		
		tf1 = new JTextField();
		tf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tf1.setColumns(10);
		
		JComboBox sgender = new JComboBox();
		sgender.setModel(new DefaultComboBoxModel(new String[] {"MALE", "FEMALE"}));
		sgender.setMaximumRowCount(3);
		
		stupass = new JTextField();
		stupass.setColumns(10);
		
		JLabel lblAddress_1_1_1_1_2_1 = new JLabel("Password :-");
		lblAddress_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GroupLayout gl_panel_1_2_1_1_1_2 = new GroupLayout(panel_1_2_1_1_1_2);
		gl_panel_1_2_1_1_1_2.setHorizontalGroup(
			gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_2_1_1_1_2.createSequentialGroup()
							.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGender_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_1_1_1_2)
								.addComponent(lblStudentId_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
							.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tf2, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
								.addComponent(tf1)
								.addComponent(sgender, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
							.addGap(4))
						.addGroup(gl_panel_1_2_1_1_1_2.createSequentialGroup()
							.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAge_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhone_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAddress_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAddress_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(stupass, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
										.addComponent(tf6, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
										.addComponent(tf5, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
										.addComponent(tf4, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnClear_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
									.addComponent(tf3, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_panel_1_2_1_1_1_2.setVerticalGroup(
			gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_1_1_2))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender_1_1_1_1_2)
						.addComponent(sgender, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAge_1_1_1_1_2)
						.addComponent(tf3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail_1_1_1_1_2)
						.addComponent(tf4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone_1_1_1_1_2)
						.addComponent(tf5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress_1_1_1_1_2)
						.addComponent(tf6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(stupass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddress_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClear_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1_2_1_1_1_2.setLayout(gl_panel_1_2_1_1_1_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel = new JLabel("");
		Image img1111 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(images));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_1.setBackground(new Color(0, 217, 217));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_2_2_1 = new JPanel();
		panel_2_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_2_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Search By SID :-");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		tfsf1 = new JTextField();
		tfsf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tfsf1.setColumns(10);
		
		JButton btnNewButton_2_1 = new JButton("Search");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminstutable.setModel(new DefaultTableModel (null,new String[] {"sid","sname","gender","age","email","phone","address"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT sid,sname,gender,age,email,phone,address,spass FROM student WHERE (sid = '"+tfsf1.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) adminstutable.getModel();
					
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
			
					while(rs.next()) {
						String sid=rs.getString(1);
						String sname=rs.getString(2);
						String gender=rs.getString(3);
						String age=rs.getString(4);
						String email=rs.getString(5);
						String phone=rs.getString(6);
						String address=rs.getString(7);
						String spass=rs.getString(8);
						String[] row= {sid,sname,gender,age,email,phone,address,spass};
						model.addRow(row);
					}
					conn.close();
					st.close();
				}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
			}
		});
		btnNewButton_2_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_3_1 = new JButton("Refresh");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminstutable.setModel(new DefaultTableModel (null,new String[] {"sid","sname","gender","age","email","phone","address"}));
				refresh();
			}
		});
		btnNewButton_1_3_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_2_1 = new GroupLayout(panel_2_2_1);
		gl_panel_2_2_1.setHorizontalGroup(
			gl_panel_2_2_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 765, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfsf1, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2_2_1.setVerticalGroup(
			gl_panel_2_2_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGap(0, 52, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_2_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2_2_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_1)
							.addComponent(tfsf1, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1.setLayout(gl_panel_2_2_1);
		
		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_1_1_1.setBackground(new Color(0, 217, 217));
		
		JButton btnNewButton_1_2_1_1 = new JButton("Add Student");
		btnNewButton_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empty()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
						Statement stmt = conn.createStatement();
						String query = "insert into student (sid,sname,gender,age,email,phone,address,cid1,cid2,cid3,cid4,cid5,mark1,mark2,mark3,mark4,mark5,fid1,fid2,fid3,fid4,fid5,spass) values ('"+tf1.getText()+"','"+tf2.getText()+"','"+sgender.getSelectedItem().toString()+"','"+tf3.getText()+"','"+tf4.getText()+"','"+tf5.getText()+"','"+tf6.getText()+"','SET CID1','SET CID2','SET CID3','SET CID4','SET CID5','SET','SET','SET','SET','SET','SET','SET','SET','SET','SET','"+stupass.getText()+"')";                                  
						ResultSet rs=stmt.executeQuery(query);
						if(rs.next()==true) {
							adminstutable.setModel(new DefaultTableModel (null,new String[] {"sid","sname","gender","age","email","phone","address","spass"}));	
							refresh();
							adminsubjecttable.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));
							refresh2();
						}
						else {
							JOptionPane.showMessageDialog(null,"ERROR");
							adminstutable.setModel(new DefaultTableModel (null,new String[] {"sid","sname","gender","age","email","phone","address","spass"}));	
							refresh();
							adminsubjecttable.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));
							refresh2();
						}
						conn.close();
					}catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnNewButton_1_2_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_2_1 = new JButton("Update");
		btnNewButton_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empty()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
						Statement stmt = conn.createStatement();
						String query = "update student set sname='"+tf2.getText()+"',gender='"+sgender.getSelectedItem().toString()+"',age='"+tf3.getText()+"',email='"+tf4.getText()+"',phone='"+tf5.getText()+"',address='"+tf6.getText()+"',spass='"+stupass.getText()+"' where sid='"+tf1.getText()+"'";                            
						ResultSet rs=stmt.executeQuery(query);
						conn.close();
						adminstutable.setModel(new DefaultTableModel (null,new String[] {"sid","sname","gender","age","email","phone","address","spass"}));				
						refresh();
					}
					catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnNewButton_1_1_2_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_1_1_1 = new JButton("Delete Student");
		btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement stmt = conn.createStatement();
					String query = "delete from student where sid='"+tf1.getText()+"'";                                  
					ResultSet rs=stmt.executeQuery(query);
					conn.close();
					adminstutable.setModel(new DefaultTableModel (null,new String[] {"sid","sname","gender","age","email","phone","address"}));				
					refresh();
				}
				catch(Exception e1) {System.out.println(e);}
			}
		});
		btnNewButton_1_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_1_1_1_1 = new JButton("Print");
		btnNewButton_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessageFormat header = new MessageFormat("Students Information");
					MessageFormat footer = new MessageFormat("Page{0,number,integer}");
					adminstutable.print(JTable.PrintMode.FIT_WIDTH,header,footer);
				}
				catch(Exception e1) {}
			}
		});
		btnNewButton_1_1_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_1_1_1 = new GroupLayout(panel_2_1_1_1);
		gl_panel_2_1_1_1.setHorizontalGroup(
			gl_panel_2_1_1_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 765, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1.createSequentialGroup()
					.addGap(56)
					.addComponent(btnNewButton_1_2_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(btnNewButton_1_1_2_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addComponent(btnNewButton_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
		);
		gl_panel_2_1_1_1.setVerticalGroup(
			gl_panel_2_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 59, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_2_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_2_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2_1_1_1.setLayout(gl_panel_2_1_1_1);
		GroupLayout gl_panel_1_1_1 = new GroupLayout(panel_1_1_1);
		gl_panel_1_1_1.setHorizontalGroup(
			gl_panel_1_1_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 789, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
						.addComponent(panel_2_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
						.addComponent(panel_2_1_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1_1_1.setVerticalGroup(
			gl_panel_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 578, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2_1_1_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		adminstutable = new JTable();
		adminstutable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = adminstutable.getSelectedRow();
				TableModel model = adminstutable.getModel();
				tf1.setText(model.getValueAt(i, 0).toString());
				tf2.setText(model.getValueAt(i, 1).toString());
				String sgender1 = model.getValueAt(i, 2).toString();
				switch(sgender1) {
				case "MALE":
					sgender.setSelectedIndex(0);
					break;
					
				case "FEMALE":
					sgender.setSelectedIndex(1);
					break;
				}
				tf3.setText(model.getValueAt(i, 3).toString());
				tf4.setText(model.getValueAt(i, 4).toString());
				tf5.setText(model.getValueAt(i, 5).toString());
				tf6.setText(model.getValueAt(i, 6).toString());
				stupass.setText(model.getValueAt(i, 7).toString());
			}
		});
		scrollPane.setViewportView(adminstutable);
		panel_1_1_1.setLayout(gl_panel_1_1_1);
		GroupLayout gl_panel_4_1_1_1_2 = new GroupLayout(panel_4_1_1_1_2);
		gl_panel_4_1_1_1_2.setHorizontalGroup(
			gl_panel_4_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1_1_2.createSequentialGroup()
					.addGroup(gl_panel_4_1_1_1_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_4_1_1_1_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_panel_4_1_1_1_2.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(panel_1_1_1, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_panel_4_1_1_1_2.setVerticalGroup(
			gl_panel_4_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1_1_1, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel_4_1_1_1_2.createSequentialGroup()
							.addComponent(panel_1_2_1_1_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(356, Short.MAX_VALUE))
		);
		panel_4_1_1_1_2.setLayout(gl_panel_4_1_1_1_2);
		Image img111 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		
		JPanel panel_4_1_1_1_2_1_1 = new JPanel();
		panel_4_1_1_1_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1_1_1_2_1_1.setBackground(new Color(0, 217, 217));
		tabbedPane.addTab("Faculty", null, panel_4_1_1_1_2_1_1, null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(null);
		panel_1_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(images));
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 368, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 242, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_1_1.setLayout(gl_panel_1_1);
		
		JPanel panel_1_2_1_1_1_2_1_1 = new JPanel();
		panel_1_2_1_1_1_2_1_1.setForeground(new Color(70, 155, 249));
		panel_1_2_1_1_1_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1_1_2_1_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_2 = new JLabel("Address :- ");
		lblStudentId_1_1_1_1_2_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tf9 = new JTextField();
		tf9.setColumns(10);
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_3 = new JLabel("Phone :- ");
		lblStudentId_1_1_1_1_2_1_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tf10 = new JTextField();
		tf10.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tf10.setColumns(10);
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2 = new JLabel("Faculty ID :- ");
		lblStudentId_1_1_1_1_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_1 = new JLabel("Name :- ");
		lblStudentId_1_1_1_1_2_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tf8 = new JTextField();
		tf8.setColumns(10);
		
		tf7 = new JTextField();
		tf7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tf7.setColumns(10);
		
		JButton btnClear_1_1_1_1_2_1_1 = new JButton("Clear");
		btnClear_1_1_1_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf7.setText(null);
				tf8.setText(null);
				tf9.setText(null);
				tf10.setText(null);
				tf11.setText(null);
			}
		});
		btnClear_1_1_1_1_2_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_3_1 = new JLabel("Password :- ");
		lblStudentId_1_1_1_1_2_1_1_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tf11 = new JTextField();
		tf11.setColumns(10);
		GroupLayout gl_panel_1_2_1_1_1_2_1_1 = new GroupLayout(panel_1_2_1_1_1_2_1_1);
		gl_panel_1_2_1_1_1_2_1_1.setHorizontalGroup(
			gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
									.addComponent(lblStudentId_1_1_1_1_2_1_1_2_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tf9, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
									.addComponent(lblStudentId_1_1_1_1_2_1_1_2_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(tf10, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_2_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
								.addComponent(tf8, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf7, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
							.addGap(136)
							.addComponent(btnClear_1_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStudentId_1_1_1_1_2_1_1_2_3_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tf11, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel_1_2_1_1_1_2_1_1.setVerticalGroup(
			gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_3_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addComponent(btnClear_1_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		panel_1_2_1_1_1_2_1_1.setLayout(gl_panel_1_2_1_1_1_2_1_1);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_2.setBackground(new Color(0, 217, 217));
		
		JPanel panel_2_2_1_1_1 = new JPanel();
		panel_2_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_2_1_1_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Search By FID :-");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		tfsf2 = new JTextField();
		tfsf2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tfsf2.setColumns(10);
		
		JButton btnNewButton_2_1_1_1 = new JButton("Search");
		btnNewButton_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminfacultytable.setModel(new DefaultTableModel (null,new String[] {"cid","fid","fname","faddress","fphone"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT fid,fname,faddress,fphone,fpass FROM faculty WHERE (fid = '"+tfsf2.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) adminfacultytable.getModel();
					
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
			
					while(rs.next()) {
						String fid=rs.getString(1);
						String fname=rs.getString(2);
						String faddress=rs.getString(3);
						String fphone=rs.getString(4);
						String fpass=rs.getString(5);
						String[] row= {fid,fname,faddress,fphone,fpass};
						model.addRow(row);
					}
					conn.close();
					st.close();
				}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
			}
		});
		btnNewButton_2_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_3_1_1_1 = new JButton("Refresh");
		btnNewButton_1_3_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminfacultytable.setModel(new DefaultTableModel (null,new String[] {"cid","fid","fname","faddress","fphone"}));
				refresh3();
			}
		});
		btnNewButton_1_3_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_2_1_1_1 = new GroupLayout(panel_2_2_1_1_1);
		gl_panel_2_2_1_1_1.setHorizontalGroup(
			gl_panel_2_2_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 765, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1_1_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfsf2, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2_1_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1_3_1_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2_2_1_1_1.setVerticalGroup(
			gl_panel_2_2_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGap(0, 52, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_2_1_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2_2_1_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_1_1_1)
							.addComponent(tfsf2, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1_1_1.setLayout(gl_panel_2_2_1_1_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		
		JPanel panel_2_1_1_1_2 = new JPanel();
		panel_2_1_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_1_1_1_2.setBackground(new Color(0, 217, 217));
		
		JButton btnNewButton_1_2_1_1_1 = new JButton("Add Faculty");
		btnNewButton_1_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empty3()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
						Statement stmt = conn.createStatement();
						String query = "insert into faculty (fid,fname,faddress,fphone,fpass) values ('"+tf7.getText()+"','"+tf8.getText()+"','"+tf9.getText()+"','"+tf10.getText()+"','"+tf11.getText()+"')";                                  
						ResultSet rs=stmt.executeQuery(query);
						if(rs.next()==true) {
							adminfacultytable.setModel(new DefaultTableModel (null,new String[] {"fid","fname","faddress","fphone","fpass"}));	
							refresh3();
							clearcb();
							clearcb2();
							getfacultyid();
							getcourseid();
						}
						else {
							JOptionPane.showMessageDialog(null,"ERROR");
							adminfacultytable.setModel(new DefaultTableModel (null,new String[] {"fid","fname","faddress","fphone","fpass"}));	
							refresh3();
							clearcb();
							clearcb2();
							getfacultyid();
							getcourseid();
						}
						conn.close();
					}catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnNewButton_1_2_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_2_1_2 = new JButton("Update");
		btnNewButton_1_1_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empty3()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
						Statement stmt = conn.createStatement();
						String query = "update faculty set fname='"+tf8.getText()+"',faddress='"+tf9.getText()+"',fphone='"+tf10.getText()+"',fpass='"+tf11.getText()+"' where fid='"+tf7.getText()+"'";                            
						ResultSet rs=stmt.executeQuery(query);
						conn.close();
						adminfacultytable.setModel(new DefaultTableModel (null,new String[] {"fid","fname","faddress","fphone","fpass"}));				
						refresh3();
						clearcb();
						clearcb2();
						getfacultyid();
						getcourseid();
					}
					catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnNewButton_1_1_2_1_2.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_1_1_1_2 = new JButton("Delete Faculty");
		btnNewButton_1_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement stmt = conn.createStatement();
					String query = "delete from faculty where fid='"+tf7.getText()+"'";                                  
					ResultSet rs=stmt.executeQuery(query);
					conn.close();
					adminfacultytable.setModel(new DefaultTableModel (null,new String[] {"fid","fname","faddress","fphone"}));				
					refresh3();
					clearcb();
					clearcb2();
					getfacultyid();
					getcourseid();
				}
				catch(Exception e1) {System.out.println(e);}
			}
		});
		btnNewButton_1_1_1_1_1_2.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_1_1_1_1_2 = new JButton("Print");
		btnNewButton_1_1_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessageFormat header = new MessageFormat("Faculty Information");
					MessageFormat footer = new MessageFormat("Page{0,number,integer}");
					adminfacultytable.print(JTable.PrintMode.FIT_WIDTH,header,footer);
				}
				catch(Exception e1) {}
			}
		});
		btnNewButton_1_1_1_1_1_1_2.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_1_1_1_2 = new GroupLayout(panel_2_1_1_1_2);
		gl_panel_2_1_1_1_2.setHorizontalGroup(
			gl_panel_2_1_1_1_2.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 765, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1_2.createSequentialGroup()
					.addGap(56)
					.addComponent(btnNewButton_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(btnNewButton_1_1_2_1_2, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addComponent(btnNewButton_1_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_1_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
		);
		gl_panel_2_1_1_1_2.setVerticalGroup(
			gl_panel_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 59, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_1_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_2_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2_1_1_1_2.setLayout(gl_panel_2_1_1_1_2);
		GroupLayout gl_panel_1_1_2 = new GroupLayout(panel_1_1_2);
		gl_panel_1_1_2.setHorizontalGroup(
			gl_panel_1_1_2.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 789, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1_1, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1_1_2.setVerticalGroup(
			gl_panel_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 578, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1_1, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		adminfacultytable = new JTable();
		adminfacultytable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = adminfacultytable.getSelectedRow();
				TableModel model = adminfacultytable.getModel();
				tf7.setText(model.getValueAt(i, 0).toString());
				tf8.setText(model.getValueAt(i, 1).toString());
				tf9.setText(model.getValueAt(i, 2).toString());
				tf10.setText(model.getValueAt(i, 3).toString());
				tf11.setText(model.getValueAt(i, 4).toString());
			}
		});
		scrollPane_1_1.setViewportView(adminfacultytable);
		panel_1_1_2.setLayout(gl_panel_1_1_2);
		GroupLayout gl_panel_4_1_1_1_2_1_1 = new GroupLayout(panel_4_1_1_1_2_1_1);
		gl_panel_4_1_1_1_2_1_1.setHorizontalGroup(
			gl_panel_4_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1_1_2_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1_2_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1_1_2, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_panel_4_1_1_1_2_1_1.setVerticalGroup(
			gl_panel_4_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1_1_2_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1_1_2, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_4_1_1_1_2_1_1.createSequentialGroup()
							.addComponent(panel_1_2_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(356, Short.MAX_VALUE))
		);
		panel_4_1_1_1_2_1_1.setLayout(gl_panel_4_1_1_1_2_1_1);
		
		JPanel panel_4_1_1_1_2_1 = new JPanel();
		panel_4_1_1_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1_1_1_2_1.setBackground(new Color(0, 217, 217));
		tabbedPane.addTab("Subject", null, panel_4_1_1_1_2_1, null);
		
		JPanel panel_1_2_1_1_1_2_1 = new JPanel();
		panel_1_2_1_1_1_2_1.setForeground(new Color(70, 155, 249));
		panel_1_2_1_1_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1_1_2_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblAge_1_1_1_1_2_1_1 = new JLabel("Course ID 3 :- ");
		lblAge_1_1_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblAge_1_1_1_1_2_1 = new JLabel("Course ID 2 :- ");
		lblAge_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblGender_1_1_1_1_2_1 = new JLabel("Course ID 1 :- ");
		lblGender_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1 = new JLabel("Student ID :- ");
		lblStudentId_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblAge_1_1_1_1_2_1_2 = new JLabel("Course ID 4 :- ");
		lblAge_1_1_1_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblAge_1_1_1_1_2_1_3 = new JLabel("Course ID 5 :- ");
		lblAge_1_1_1_1_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		c5 = new JComboBox();
		c5.setModel(new DefaultComboBoxModel(new String[] {"SET CID5"}));
		c5.setMaximumRowCount(10);
		
		c4 = new JComboBox();
		c4.setModel(new DefaultComboBoxModel(new String[] {"SET CID4"}));
		c4.setMaximumRowCount(10);
		
		c3 = new JComboBox();
		c3.setModel(new DefaultComboBoxModel(new String[] {"SET CID3"}));
		c3.setMaximumRowCount(10);
		
		c2 = new JComboBox();
		c2.setModel(new DefaultComboBoxModel(new String[] {"SET CID2"}));
		c2.setMaximumRowCount(10);
		
		c1 = new JComboBox();
		c1.setModel(new DefaultComboBoxModel(new String[] {"SET CID1"}));
		c1.setMaximumRowCount(10);
		
		sid2 = new JTextField();
		sid2.setEditable(false);
		sid2.setColumns(10);
		sid2.setBackground(Color.LIGHT_GRAY);
		
		fcc2 = new JComboBox();
		fcc2.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
		fcc3 = new JComboBox();
		fcc3.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
		fcc4 = new JComboBox();
		fcc4.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
		fcc5 = new JComboBox();
		fcc5.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
		JButton btnClear_1_1_1_1_2_1 = new JButton("Clear");
		btnClear_1_1_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid2.setText("");
				c1.setSelectedIndex(0);
				c2.setSelectedIndex(0);
				c3.setSelectedIndex(0);
				c4.setSelectedIndex(0);
				c5.setSelectedIndex(0);
				fc1.setSelectedIndex(0);
				fcc2.setSelectedIndex(0);
				fcc3.setSelectedIndex(0);
				fcc4.setSelectedIndex(0);
				fcc5.setSelectedIndex(0);
			}
		});
		btnClear_1_1_1_1_2_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		fc1 = new JComboBox();
		fc1.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		GroupLayout gl_panel_1_2_1_1_1_2_1 = new GroupLayout(panel_1_2_1_1_1_2_1);
		gl_panel_1_2_1_1_1_2_1.setHorizontalGroup(
			gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1.createSequentialGroup()
					.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_2_1_1_1_2_1.createSequentialGroup()
							.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1_2_1_1_1_2_1.createSequentialGroup()
									.addGap(9)
									.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1_2_1_1_1_2_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblAge_1_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblAge_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblGender_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
										.addComponent(lblStudentId_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_1_2_1_1_1_2_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblAge_1_1_1_1_2_1_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1_2_1_1_1_2_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblAge_1_1_1_1_2_1_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
								.addComponent(c5, 0, 0, Short.MAX_VALUE)
								.addComponent(c4, 0, 0, Short.MAX_VALUE)
								.addComponent(c3, 0, 0, Short.MAX_VALUE)
								.addComponent(c2, 0, 0, Short.MAX_VALUE)
								.addComponent(c1, 0, 0, Short.MAX_VALUE)
								.addComponent(sid2, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
								.addComponent(fc1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(fcc2, 0, 0, Short.MAX_VALUE)
								.addComponent(fcc3, Alignment.TRAILING, 0, 71, Short.MAX_VALUE)
								.addComponent(fcc4, Alignment.TRAILING, 0, 71, Short.MAX_VALUE)
								.addComponent(fcc5, 0, 71, Short.MAX_VALUE)))
						.addGroup(gl_panel_1_2_1_1_1_2_1.createSequentialGroup()
							.addGap(137)
							.addComponent(btnClear_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1_2_1_1_1_2_1.setVerticalGroup(
			gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(sid2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGender_1_1_1_1_2_1)
						.addComponent(fc1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAge_1_1_1_1_2_1)
						.addComponent(c2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(fcc2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(fcc3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAge_1_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(fcc4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAge_1_1_1_1_2_1_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(fcc5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAge_1_1_1_1_2_1_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnClear_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		panel_1_2_1_1_1_2_1.setLayout(gl_panel_1_2_1_1_1_2_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image img11111 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(images));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 368, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 242, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_1_1.setBackground(new Color(0, 217, 217));
		
		JPanel panel_2_2_1_1 = new JPanel();
		panel_2_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_2_1_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Search By SID :-");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		stsf4 = new JTextField();
		stsf4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		stsf4.setColumns(10);
		
		JButton btnNewButton_2_1_1 = new JButton("Search");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminsubjecttable.setModel(new DefaultTableModel (null,new String[] {"SID","COURSE1","COURSE2","COURSE3","COURSE4","COURSE5"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT sid,cid1,fid1,cid2,fid2,cid3,fid3,cid4,fid4,cid5,fid5 FROM student WHERE (sid = '"+stsf4.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) adminsubjecttable.getModel();
					
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
			
					while(rs.next()) {
						String sid=rs.getString(1);
						String cid1=rs.getString(2);
						String fid1=rs.getString(3);
						String cid2=rs.getString(4);
						String fid2=rs.getString(5);
						String cid3=rs.getString(6);
						String fid3=rs.getString(7);
						String cid4=rs.getString(8);
						String fid4=rs.getString(9);
						String cid5=rs.getString(10);
						String fid5=rs.getString(11);
						String[] row= {sid,cid1,fid1,cid2,fid2,cid3,fid3,cid4,fid4,cid5,fid5};
						model.addRow(row);
					}
					conn.close();
					st.close();
				}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_3_1_1 = new JButton("Refresh");
		btnNewButton_1_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminsubjecttable.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));
				refresh2();
				clearcb();
				getfacultyid();
				clearcb2();
				getcourseid();
			}
		});
		btnNewButton_1_3_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_2_1_1 = new GroupLayout(panel_2_2_1_1);
		gl_panel_2_2_1_1.setHorizontalGroup(
			gl_panel_2_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 765, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(stsf4, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1_3_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2_2_1_1.setVerticalGroup(
			gl_panel_2_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGap(0, 52, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_2_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2_2_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_1_1)
							.addComponent(stsf4, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1_1.setLayout(gl_panel_2_2_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JPanel panel_2_1_1_1_1 = new JPanel();
		panel_2_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_1_1_1_1.setBackground(new Color(0, 217, 217));
		
		JButton btnNewButton_1_1_2_1_1 = new JButton("Update");
		btnNewButton_1_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empty2()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
						Statement stmt = conn.createStatement();
						String query = "update student set cid1='"+c1.getSelectedItem().toString()+"',cid2='"+c2.getSelectedItem().toString()+"',cid3='"+c3.getSelectedItem().toString()+"',cid4='"+c4.getSelectedItem().toString()+"',cid5='"+c5.getSelectedItem().toString()+"',fid1='"+fc1.getSelectedItem().toString()+"',fid2='"+fcc2.getSelectedItem().toString()+"',fid3='"+fcc3.getSelectedItem().toString()+"',fid4='"+fcc4.getSelectedItem().toString()+"',fid5='"+fcc5.getSelectedItem().toString()+"' where sid='"+sid2.getText()+"'";                            
						ResultSet rs=stmt.executeQuery(query);
						conn.close();
						adminsubjecttable.setModel(new DefaultTableModel (null,new String[] {"sid","cid1","fid1","cid2","fid2","cid3","fid3","cid4","fid4","cid5","fid5"}));	
						refresh2();
					}
					
					catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnNewButton_1_1_2_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_1_1_1_1_1 = new JButton("Print");
		btnNewButton_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessageFormat header = new MessageFormat("Subjects Info");
					MessageFormat footer = new MessageFormat("Page{0,number,integer}");
					adminsubjecttable.print(JTable.PrintMode.FIT_WIDTH,header,footer);
				}
				catch(Exception e1) {}
			}
		});
		btnNewButton_1_1_1_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_1_1_1_1 = new GroupLayout(panel_2_1_1_1_1);
		gl_panel_2_1_1_1_1.setHorizontalGroup(
			gl_panel_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 490, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1_1.createSequentialGroup()
					.addGap(64)
					.addComponent(btnNewButton_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(106)
					.addComponent(btnNewButton_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_panel_2_1_1_1_1.setVerticalGroup(
			gl_panel_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 59, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_1_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2_1_1_1_1.setLayout(gl_panel_2_1_1_1_1);
		GroupLayout gl_panel_1_1_1_1 = new GroupLayout(panel_1_1_1_1);
		gl_panel_1_1_1_1.setHorizontalGroup(
			gl_panel_1_1_1_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 789, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_1_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1_1_1_1.createSequentialGroup()
							.addGroup(gl_panel_1_1_1_1.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_2_2_1_1, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_panel_1_1_1_1.createSequentialGroup()
							.addComponent(panel_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
							.addGap(138))))
		);
		gl_panel_1_1_1_1.setVerticalGroup(
			gl_panel_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 578, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		adminsubjecttable = new JTable();
		adminsubjecttable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = adminsubjecttable.getSelectedRow();
				TableModel model = adminsubjecttable.getModel();
				sid2.setText(model.getValueAt(i, 0).toString());
				
				String a = adminsubjecttable.getValueAt(i, 1).toString();
				c1.setSelectedItem(a);
																
				String fid1 = adminsubjecttable.getValueAt(i, 2).toString();
				fc1.setSelectedItem(fid1);
				
				String b = adminsubjecttable.getValueAt(i, 3).toString();
				c2.setSelectedItem(b);
				
				String fid2 = adminsubjecttable.getValueAt(i, 4).toString();
				fcc2.setSelectedItem(fid2);
				
				String c = adminsubjecttable.getValueAt(i, 5).toString();
				c3.setSelectedItem(c);
				
				String fid3 = adminsubjecttable.getValueAt(i, 6).toString();
				fcc3.setSelectedItem(fid3);
				
				String d = adminsubjecttable.getValueAt(i, 7).toString();
				c4.setSelectedItem(d);
				
				String fid4 = adminsubjecttable.getValueAt(i, 8).toString();
				fcc4.setSelectedItem(fid4);
				
				String ee = adminsubjecttable.getValueAt(i, 9).toString();
				c5.setSelectedItem(ee);
				
				String fid5 = adminsubjecttable.getValueAt(i, 10).toString();
				fcc5.setSelectedItem(fid5);
			}
		});
		scrollPane_1.setViewportView(adminsubjecttable);
		panel_1_1_1_1.setLayout(gl_panel_1_1_1_1);
		GroupLayout gl_panel_4_1_1_1_2_1 = new GroupLayout(panel_4_1_1_1_2_1);
		gl_panel_4_1_1_1_2_1.setHorizontalGroup(
			gl_panel_4_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1207, Short.MAX_VALUE)
				.addGroup(gl_panel_4_1_1_1_2_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1_2_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_panel_4_1_1_1_2_1.setVerticalGroup(
			gl_panel_4_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 945, Short.MAX_VALUE)
				.addGroup(gl_panel_4_1_1_1_2_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_4_1_1_1_2_1.createSequentialGroup()
							.addComponent(panel_1_2_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(352, Short.MAX_VALUE))
		);
		panel_4_1_1_1_2_1.setLayout(gl_panel_4_1_1_1_2_1);
		
		JPanel panel_4_1_1_1_2_1_1_2 = new JPanel();
		panel_4_1_1_1_2_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1_1_1_2_1_1_2.setBackground(new Color(0, 217, 217));
		tabbedPane.addTab("Course", null, panel_4_1_1_1_2_1_1_2, null);
		
		JPanel panel_1_1_5 = new JPanel();
		panel_1_1_5.setBorder(null);
		panel_1_1_5.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_4 = new JLabel("");
		lblNewLabel_1_1_4.setIcon(new ImageIcon(images));
		GroupLayout gl_panel_1_1_5 = new GroupLayout(panel_1_1_5);
		gl_panel_1_1_5.setHorizontalGroup(
			gl_panel_1_1_5.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 368, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_5.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1_1_4, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel_1_1_5.setVerticalGroup(
			gl_panel_1_1_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 242, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_4, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_1_1_5.setLayout(gl_panel_1_1_5);
		
		JPanel panel_1_2_1_1_1_2_1_1_2 = new JPanel();
		panel_1_2_1_1_1_2_1_1_2.setForeground(new Color(70, 155, 249));
		panel_1_2_1_1_1_2_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1_1_2_1_1_2.setBackground(new Color(0, 217, 217));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_1_1 = new JLabel("Course Name :- ");
		lblStudentId_1_1_1_1_2_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tf14 = new JTextField();
		tf14.setColumns(10);
		
		JButton btnClear_1_1_1_1_2_1_1_2 = new JButton("Clear");
		btnClear_1_1_1_1_2_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf12.setText(null);
				tf13.setText(null);
				tf14.setText(null);
			}
		});
		btnClear_1_1_1_1_2_1_1_2.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_4_1 = new JLabel("Course ID :- ");
		lblStudentId_1_1_1_1_2_1_1_2_4_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_4 = new JLabel("Selected CID :- ");
		lblStudentId_1_1_1_1_2_1_1_2_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tf12 = new JTextField();
		tf12.setEditable(false);
		tf12.setColumns(10);
		tf12.setBackground(Color.LIGHT_GRAY);
		
		tf13 = new JTextField();
		tf13.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tf13.setColumns(10);
		GroupLayout gl_panel_1_2_1_1_1_2_1_1_2 = new GroupLayout(panel_1_2_1_1_1_2_1_1_2);
		gl_panel_1_2_1_1_1_2_1_1_2.setHorizontalGroup(
			gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_2_4_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_2_4))
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
							.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(tf12, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf13, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStudentId_1_1_1_1_2_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
							.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(btnClear_1_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf14, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_1_2_1_1_1_2_1_1_2.setVerticalGroup(
			gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_4_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnClear_1_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		panel_1_2_1_1_1_2_1_1_2.setLayout(gl_panel_1_2_1_1_1_2_1_1_2);
		
		JPanel panel_1_1_2_2 = new JPanel();
		panel_1_1_2_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_2_2.setBackground(new Color(0, 217, 217));
		
		JPanel panel_2_2_1_1_1_2 = new JPanel();
		panel_2_2_1_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_2_1_1_1_2.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Search By CID :-");
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		tfsf3 = new JTextField();
		tfsf3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		tfsf3.setColumns(10);
		
		JButton btnNewButton_2_1_1_1_1 = new JButton("Search");
		btnNewButton_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admincoursetable.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT sid,sname,gender,age,email,phone,address FROM student WHERE (sid = '"+tfsf3.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) adminfacultytable.getModel();
					
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
			
					while(rs.next()) {
						String sid=rs.getString(1);
						String sname=rs.getString(2);
						String gender=rs.getString(3);
						String age=rs.getString(4);
						String email=rs.getString(5);
						String phone=rs.getString(6);
						String address=rs.getString(7);
						String[] row= {sid,sname,gender,age,email,phone,address};
						model.addRow(row);
					}
					conn.close();
					st.close();
				}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
			}
		});
		btnNewButton_2_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_3_1_1_1_2 = new JButton("Refresh");
		btnNewButton_1_3_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admincoursetable.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));
				refresh5();
			}
		});
		btnNewButton_1_3_1_1_1_2.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_2_1_1_1_2 = new GroupLayout(panel_2_2_1_1_1_2);
		gl_panel_2_2_1_1_1_2.setHorizontalGroup(
			gl_panel_2_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 765, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1_1_1_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfsf3, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1_3_1_1_1_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2_2_1_1_1_2.setVerticalGroup(
			gl_panel_2_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGap(0, 52, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_1_1_1_2)
							.addComponent(tfsf3, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1_1_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1_1_1_2.setLayout(gl_panel_2_2_1_1_1_2);
		
		JScrollPane scrollPane_1_1_2 = new JScrollPane();
		
		JPanel panel_2_1_1_1_2_1 = new JPanel();
		panel_2_1_1_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_1_1_1_2_1.setBackground(new Color(0, 217, 217));
		
		JButton btnNewButton_1_2_1_1_1_1 = new JButton("Add Course");
		btnNewButton_1_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empty5()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
						Statement stmt = conn.createStatement();
						String query = "insert into course (CID,CNAME) values ('"+tf13.getText()+"','"+tf14.getText()+"')";                                  
						ResultSet rs=stmt.executeQuery(query);
						if(rs.next()==true) {
							admincoursetable.setModel(new DefaultTableModel (null,new String[] {"cidd","ciidname"}));	
							refresh5();
							clearcb();
							clearcb2();
							getfacultyid();
							getcourseid();
						}
						else {
							JOptionPane.showMessageDialog(null,"ERROR");
							admincoursetable.setModel(new DefaultTableModel (null,new String[] {"ciid","ciidname"}));	
							refresh5();
							clearcb();
							clearcb2();
							getfacultyid();
							getcourseid();
						}
						conn.close();
					}catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnNewButton_1_2_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_2_1_2_1 = new JButton("Update");
		btnNewButton_1_1_2_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empty5()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
						Statement stmt = conn.createStatement();
						String query = "update course  set cid='"+tf13.getText()+"',cname='"+tf14.getText()+"' where cid='"+tf12.getText()+"'";                            
						ResultSet rs=stmt.executeQuery(query);
						conn.close();
						admincoursetable.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));				
						refresh5();
						clearcb();
						clearcb2();
						getfacultyid();
						getcourseid();
					}
					catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnNewButton_1_1_2_1_2_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_1_1_1_2_1 = new JButton("Delete Course");
		btnNewButton_1_1_1_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement stmt = conn.createStatement();
					String query = "delete from course where cid='"+tf12.getText()+"'";                                  
					ResultSet rs=stmt.executeQuery(query);
					conn.close();
					admincoursetable.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));				
					refresh5();
					clearcb();
					clearcb2();
					getfacultyid();
					getcourseid();
				}
				catch(Exception e1) {System.out.println(e);}
			}
		});
		btnNewButton_1_1_1_1_1_2_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_1_1_1_1_2_1 = new JButton("Print");
		btnNewButton_1_1_1_1_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessageFormat header = new MessageFormat("Course Information");
					MessageFormat footer = new MessageFormat("Page{0,number,integer}");
					admincoursetable.print(JTable.PrintMode.FIT_WIDTH,header,footer);
				}
				catch(Exception e1) {}
			}
		});
		btnNewButton_1_1_1_1_1_1_2_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_1_1_1_2_1 = new GroupLayout(panel_2_1_1_1_2_1);
		gl_panel_2_1_1_1_2_1.setHorizontalGroup(
			gl_panel_2_1_1_1_2_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 765, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1_2_1.createSequentialGroup()
					.addGap(56)
					.addComponent(btnNewButton_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(btnNewButton_1_1_2_1_2_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addComponent(btnNewButton_1_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_1_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
		);
		gl_panel_2_1_1_1_2_1.setVerticalGroup(
			gl_panel_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 59, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1_2_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_1_1_1_2_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_1_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_2_1_2_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2_1_1_1_2_1.setLayout(gl_panel_2_1_1_1_2_1);
		GroupLayout gl_panel_1_1_2_2 = new GroupLayout(panel_1_1_2_2);
		gl_panel_1_1_2_2.setHorizontalGroup(
			gl_panel_1_1_2_2.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 789, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_2_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_2_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1_1_2, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1_1_2_2.setVerticalGroup(
			gl_panel_1_1_2_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 578, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_2_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1_1_2, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		admincoursetable = new JTable();
		admincoursetable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i =admincoursetable.getSelectedRow();
				TableModel model = admincoursetable.getModel();
				tf12.setText(model.getValueAt(i, 0).toString());
				tf13.setText(model.getValueAt(i, 0).toString());
				tf14.setText(model.getValueAt(i, 1).toString());
			}
		});
		scrollPane_1_1_2.setViewportView(admincoursetable);
		panel_1_1_2_2.setLayout(gl_panel_1_1_2_2);
		GroupLayout gl_panel_4_1_1_1_2_1_1_2 = new GroupLayout(panel_4_1_1_1_2_1_1_2);
		gl_panel_4_1_1_1_2_1_1_2.setHorizontalGroup(
			gl_panel_4_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1207, Short.MAX_VALUE)
				.addGroup(gl_panel_4_1_1_1_2_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1_1_5, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1_2_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1_1_2_2, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_panel_4_1_1_1_2_1_1_2.setVerticalGroup(
			gl_panel_4_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 945, Short.MAX_VALUE)
				.addGroup(gl_panel_4_1_1_1_2_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1_1_2.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1_1_2_2, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_4_1_1_1_2_1_1_2.createSequentialGroup()
							.addComponent(panel_1_2_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1_1_5, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(352, Short.MAX_VALUE))
		);
		panel_4_1_1_1_2_1_1_2.setLayout(gl_panel_4_1_1_1_2_1_1_2);
		Image img111111 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image img1111112 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image img4 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image img5 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image img6 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		adminframe.getContentPane().setLayout(groupLayout);
	}
	private void initComponents() {
		// TODO Auto-generated method stub
		
	}
}
