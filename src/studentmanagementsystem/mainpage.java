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

public class mainpage {

	JFrame mainpageframe;
	private JLabel lblday;
	private JLabel lblclock;
	private JTextField sname;
	private JTextField sid;
	private JTextField saddress;
	private JTextField sphone;
	private JTextField semail;
	private JTextField sage;
	private JTextField searchfield;
	private JComboBox<String> sgender;
	protected AbstractButton testpanel;
	protected String[][] data;
	private JTable maintable;
	private JTextField searchfield2;
	private JTextField sid2;
	private JTable maintable2;
	private JComboBox c1;
	private JComboBox c2;
	private JComboBox c3;
	private JComboBox c4;
	private JComboBox c5;
	private JTextField fid;
	private JTextField fname;
	private JTextField searchfield3;
	private JTable maintable3;
	private JTextField c11;
	private JTextField searchfield5;
	private JTextField c22;
	private JTextField m1;
	private JTextField m2;
	private JTextField c33;
	private JTextField c44;
	private JTextField c55;
	private JTextField m3;
	private JTextField m4;
	private JTextField m5;
	private JTable maintable4;
	private JTextField sid3;
	private JComboBox fc1;
	private JComboBox fcc2;
	private JComboBox fcc3;
	private JComboBox fcc4;
	private JComboBox fcc5;
	private JTextField ciidname;
	private JTextField ciid;
	private JTextField searchfield6;
	private JTable maintable5;
	private JTextField slccid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage window = new mainpage();
					window.mainpageframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	
	
	public void refresh() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT sid,sname,gender,age,email,phone,address FROM student order by sid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) maintable.getModel();
			
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
	
	public void refresh2() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT sid,cid1,fid1,cid2,fid2,cid3,fid3,cid4,fid4,cid5,fid5 FROM student order by sid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) maintable2.getModel();
			
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
		
	public void refresh3() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT fid,fname from faculty order by fid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) maintable3.getModel();
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
	
			while(rs.next()) {
				String fid=rs.getString(1);
				String fname=rs.getString(2);
				String[] row= {fid,fname};
				model.addRow(row);
			}
			conn.close();
			st.close();
		}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	}
	
	public void refresh4() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT sid,cid1,mark1,cid2,mark2,cid3,mark3,cid4,mark4,cid5,mark5 FROM student order by sid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) maintable4.getModel();
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
	
			while(rs.next()) {
				String sid=rs.getString(1);
				String cid1=rs.getString(2);
				String mark1=rs.getString(3);
				String cid2=rs.getString(4);
				String mark2=rs.getString(5);
				String cid3=rs.getString(6);
				String mark3=rs.getString(7);
				String cid4=rs.getString(8);
				String mark4=rs.getString(9);
				String cid5=rs.getString(10);
				String mark5=rs.getString(11);
				String[] row= {sid,cid1,mark1,cid2,mark2,cid3,mark3,cid4,mark4,cid5,mark5};
				model.addRow(row);
			}
			conn.close();
			st.close();
		}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	}
	
	public void refresh5() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
			Statement st=conn.createStatement();
			String query = "SELECT * from course order by cid";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) maintable5.getModel();
			
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
	
	
	
	public boolean empty5() {
		if(ciid.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "INSERT COURSE ID");
			return false;
		}
		if(ciidname.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "INSERT COURSE NAME");
			return false;
		}
		return true;
	}
	
	
	public boolean empty4() {
		if(m1.getText().isEmpty() || m1.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "ADD MARKS FOR COURSE1");
			return false;
		}
		if(m2.getText().isEmpty() || m2.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "ADD MARKS FOR COURSE2");
			return false;
		}
		if(m3.getText().isEmpty() || m3.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "ADD MARKS FOR COURSE3");
			return false;
		}
		if(m4.getText().isEmpty() || m4.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "ADD MARKS FOR COURSE4");
			return false;
		}
		if(m5.getText().isEmpty() || m5.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "ADD MARKS FOR COURSE5");
			return false;
		}
		return true;
	}
	
	
	public boolean forpercentage() {
		if(m1.getText().isEmpty() || m1.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "SET MARKS FOR COURSE 1 FIRST");
			return false;
		}
		if(m2.getText().isEmpty() || m2.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "SET MARKS FOR COURSE 2 FIRST");
			return false;
		}
		if(m3.getText().isEmpty() || m3.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "SET MARKS FOR COURSE 3 FIRST");
			return false;
		}
		if(m4.getText().isEmpty() || m4.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "SET MARKS FOR COURSE 4 FIRST");
			return false;
		}
		if(m5.getText().isEmpty() || m5.getText().matches("SET")) {
			JOptionPane.showMessageDialog(null, "SET MARKS FOR COURSE 5 FIRST");
			return false;
		}
		return true;
	}
	
	
	public boolean empty3() {
		if(fid.getText().isEmpty() || fid.getText().matches("SET ID")) {
			JOptionPane.showMessageDialog(null, "Faculty ID is missing");
			return false;
		}
		if(fname.getText().isEmpty() || fname.getText().matches("SET NAME")) {
			JOptionPane.showMessageDialog(null, "Faculty Name is missing");
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
	
	
	public boolean empty() {	
		if(sid.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student ID is missing");
			return false;
		}
		if(sname.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Name is missing");
			return false;
		}
		if(sage.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Age is missing");
			return false;
		}
		if(!semail.getText().matches("^.+@.+\\..+$")) {
			JOptionPane.showMessageDialog(null, "Invalid Email Address");
			return false;
		}
		if(sphone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Phone Number is missing");
			return false;
		}
		if(sphone.getText().length()>=15) {
			JOptionPane.showMessageDialog(null, "Phone Number is too long");
			return false;
		}
		if(saddress.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Student Address ID is missing");
			return false;
		}
		return true;
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

	/**
	 * Create the application.
	 */
	public mainpage() {
		initialize();
		clock();
		refresh();
		refresh2();
		refresh3();
		refresh4();
		refresh5();
		getfacultyid();
		getcourseid();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainpageframe = new JFrame();
		mainpageframe.setTitle("mainpage - StudentManagementSystem");
		mainpageframe.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\S A Z\\Desktop\\java icons\\icons\\software icon.png"));
		mainpageframe.setResizable(false);
		mainpageframe.getContentPane().setBackground(new Color(90, 190, 250));
		mainpageframe.setBounds(100, 100, 1248, 766);
		mainpageframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		
		JLabel lblStudentManagementSystem = new JLabel("WELCOME - FACULTY DASHBOARD");
		lblStudentManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 43));
		
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
				mainpageframe.dispose();
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
				mainpageframe.dispose();			}
		});
		btnLogout_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(mainpageframe.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblStudentManagementSystem, GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
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
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblStudentManagementSystem, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnLogout_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
							.addGap(4)))
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JPanel panel_4_1_1_1_2 = new JPanel();
		panel_4_1_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1_1_1_2.setBackground(new Color(0, 217, 217));
		tabbedPane.addTab("Student", null, panel_4_1_1_1_2, null);
		
		JPanel panel_1_2_1_1_1_2 = new JPanel();
		panel_1_2_1_1_1_2.setForeground(new Color(70, 155, 249));
		panel_1_2_1_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1_1_2.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Student Name :- ");
		lblNewLabel_2_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		sname = new JTextField();
		sname.setColumns(10);
		
		JLabel lblStudentId_1_1_1_1_2 = new JLabel("Student ID :- ");
		lblStudentId_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		sid = new JTextField();
		sid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		sid.setColumns(10);
		
		JLabel lblGender_1_1_1_1_2 = new JLabel("Gender :- ");
		lblGender_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		sgender = new JComboBox();
		sgender.setModel(new DefaultComboBoxModel(new String[] {"MALE", "FEMALE"}));
		sgender.setMaximumRowCount(3);
		
		JLabel lblAge_1_1_1_1_2 = new JLabel("Age :- ");
		lblAge_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblEmail_1_1_1_1_2 = new JLabel("Email :- ");
		lblEmail_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblPhone_1_1_1_1_2 = new JLabel("Phone :- ");
		lblPhone_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblAddress_1_1_1_1_2 = new JLabel("Address :- ");
		lblAddress_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		saddress = new JTextField();
		saddress.setColumns(10);
		
		sphone = new JTextField();
		sphone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		sphone.setColumns(10);
		
		semail = new JTextField();
		semail.setColumns(10);
		
		sage = new JTextField();
		sage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		sage.setColumns(10);
		
		JButton btnClear_1_1_1_1_2 = new JButton("Clear");
		btnClear_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid.setText("");
				sname.setText("");
				sage.setText("");
				semail.setText("");
				sphone.setText("");
				saddress.setText("");
			}
		});
		btnClear_1_1_1_1_2.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_1_2_1_1_1_2 = new GroupLayout(panel_1_2_1_1_1_2);
		gl_panel_1_2_1_1_1_2.setHorizontalGroup(
			gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2.createSequentialGroup()
					.addContainerGap(21, Short.MAX_VALUE)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1_2_1_1_1_2.createSequentialGroup()
							.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAge_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhone_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAddress_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
									.addComponent(saddress, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
									.addComponent(sphone, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
									.addComponent(semail, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnClear_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
								.addComponent(sage, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1_2_1_1_1_2.createSequentialGroup()
							.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGender_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_1_1_1_2)
								.addComponent(lblStudentId_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
							.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(sname, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
								.addComponent(sid)
								.addComponent(sgender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(4)))
					.addContainerGap())
		);
		gl_panel_1_2_1_1_1_2.setVerticalGroup(
			gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(sid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(sname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_1_1_2))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender_1_1_1_1_2)
						.addComponent(sgender, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAge_1_1_1_1_2)
						.addComponent(sage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail_1_1_1_1_2)
						.addComponent(semail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone_1_1_1_1_2)
						.addComponent(sphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress_1_1_1_1_2)
						.addComponent(saddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnClear_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1_2_1_1_1_2.setLayout(gl_panel_1_2_1_1_1_2);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_1.setBackground(new Color(0, 217, 217));
		
		JPanel panel_2_2_1 = new JPanel();
		panel_2_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_2_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Search By SID :-");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		searchfield = new JTextField();
		searchfield.addKeyListener(new KeyAdapter() {
		 @Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		searchfield.setColumns(10);
		
		JButton btnNewButton_2_1 = new JButton("Search");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintable.setModel(new DefaultTableModel (null,new String[] {"sid","sname","gender","age","email","phone","address"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT sid,sname,gender,age,email,phone,address FROM student WHERE (sid = '"+searchfield.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) maintable.getModel();
					
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
		btnNewButton_2_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_3_1 = new JButton("Refresh");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				maintable.setModel(new DefaultTableModel (null,new String[] {"sid","sname","gender","age","email","phone","address"}));
				refresh();
			}
		});
		btnNewButton_1_3_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_2_1 = new GroupLayout(panel_2_2_1);
		gl_panel_2_2_1.setHorizontalGroup(
			gl_panel_2_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_2_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(searchfield, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2_2_1.setVerticalGroup(
			gl_panel_2_2_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 52, Short.MAX_VALUE)
				.addGap(0, 52, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_2_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2_2_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_1)
							.addComponent(searchfield, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1.setLayout(gl_panel_2_2_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		maintable = new JTable();
		maintable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = maintable.getSelectedRow();
				TableModel model = maintable.getModel();
				sid.setText(model.getValueAt(i, 0).toString());
				sname.setText(model.getValueAt(i, 1).toString());
				String sgender1 = model.getValueAt(i, 2).toString();
				switch(sgender1) {
				case "MALE":
					sgender.setSelectedIndex(0);
					break;
					
				case "FEMALE":
					sgender.setSelectedIndex(1);
					break;
				}
				sage.setText(model.getValueAt(i, 3).toString());
				semail.setText(model.getValueAt(i, 4).toString());
				sphone.setText(model.getValueAt(i, 5).toString());
				saddress.setText(model.getValueAt(i, 6).toString());
				
			}
		});
		scrollPane.setViewportView(maintable);
		maintable.setModel(new DefaultTableModel());
		maintable.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GroupLayout gl_panel_1_1_1 = new GroupLayout(panel_1_1_1);
		gl_panel_1_1_1.setHorizontalGroup(
			gl_panel_1_1_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
						.addComponent(panel_2_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1_1_1.setVerticalGroup(
			gl_panel_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1_1_1.setLayout(gl_panel_1_1_1);
		Image images = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		Image imagesss = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 217, 217));
		panel.setBorder(null);
		GroupLayout gl_panel_4_1_1_1_2 = new GroupLayout(panel_4_1_1_1_2);
		gl_panel_4_1_1_1_2.setHorizontalGroup(
			gl_panel_4_1_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1_1_2.createSequentialGroup()
					.addGroup(gl_panel_4_1_1_1_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4_1_1_1_2.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel_4_1_1_1_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
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
						.addGroup(gl_panel_4_1_1_1_2.createSequentialGroup()
							.addComponent(panel_1_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(344, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(images));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(41, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		panel_4_1_1_1_2.setLayout(gl_panel_4_1_1_1_2);
		
		JPanel panel_4_1_1_1_2_1 = new JPanel();
		panel_4_1_1_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1_1_1_2_1.setBackground(new Color(0, 217, 217));
		tabbedPane.addTab("Subject", null, panel_4_1_1_1_2_1, null);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_1_1.setBackground(new Color(0, 217, 217));
		
		JPanel panel_2_2_1_1 = new JPanel();
		panel_2_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_2_1_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Search By SID :-");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		searchfield2 = new JTextField();
		searchfield2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		searchfield2.setColumns(10);
		
		JButton btnNewButton_2_1_1 = new JButton("Search");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintable2.setModel(new DefaultTableModel (null,new String[] {"SID","COURSE1","COURSE2","COURSE3","COURSE4","COURSE5"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT sid,cid1,fid1,cid2,fid2,cid3,fid3,cid4,fid4,cid5,fid5 FROM student WHERE (sid = '"+searchfield2.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) maintable2.getModel();
					
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
				maintable2.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));
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
					.addComponent(searchfield2, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
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
							.addComponent(searchfield2, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1_1.setLayout(gl_panel_2_2_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_1_1_1_1 = new GroupLayout(panel_1_1_1_1);
		gl_panel_1_1_1_1.setHorizontalGroup(
			gl_panel_1_1_1_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_1_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
						.addComponent(panel_2_2_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1_1_1_1.setVerticalGroup(
			gl_panel_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		maintable2 = new JTable() {
		};
		maintable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = maintable2.getSelectedRow();
				TableModel model = maintable2.getModel();
				sid2.setText(model.getValueAt(i, 0).toString());
				
				String a = maintable2.getValueAt(i, 1).toString();
				c1.setSelectedItem(a);
																
				String fid1 = maintable2.getValueAt(i, 2).toString();
				fc1.setSelectedItem(fid1);
				
				String b = maintable2.getValueAt(i, 3).toString();
				c2.setSelectedItem(b);
				
				String fid2 = maintable2.getValueAt(i, 4).toString();
				fcc2.setSelectedItem(fid2);
				
				String c = maintable2.getValueAt(i, 5).toString();
				c3.setSelectedItem(c);
				
				String fid3 = maintable2.getValueAt(i, 6).toString();
				fcc3.setSelectedItem(fid3);
				
				String d = maintable2.getValueAt(i, 7).toString();
				c4.setSelectedItem(d);
				
				String fid4 = maintable2.getValueAt(i, 8).toString();
				fcc4.setSelectedItem(fid4);
				
				String ee = maintable2.getValueAt(i, 9).toString();
				c5.setSelectedItem(ee);
				
				String fid5 = maintable2.getValueAt(i, 10).toString();
				fcc5.setSelectedItem(fid5);
				
			}
		});
		maintable2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane_1.setViewportView(maintable2);
		panel_1_1_1_1.setLayout(gl_panel_1_1_1_1);
		Image imagess = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		
		JPanel panel_1_2_1_1_1_2_1 = new JPanel();
		panel_1_2_1_1_1_2_1.setForeground(new Color(70, 155, 249));
		panel_1_2_1_1_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1_1_2_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblAge_1_1_1_1_2_1 = new JLabel("Course ID 2 :- ");
		lblAge_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
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
		
		JLabel lblGender_1_1_1_1_2_1 = new JLabel("Course ID 1 :- ");
		lblGender_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1 = new JLabel("Student ID :- ");
		lblStudentId_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
        c1 = new JComboBox();
		c1.setModel(new DefaultComboBoxModel(new String[] {"SET CID1"}));
		c1.setMaximumRowCount(10);
		
		sid2 = new JTextField();
		sid2.setBackground(new Color(192, 192, 192));
		sid2.setEditable(false);
		sid2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		sid2.setColumns(10);
		
		c2 = new JComboBox();
		c2.setModel(new DefaultComboBoxModel(new String[] {"SET CID2"}));
		c2.setMaximumRowCount(10);
		
		c3 = new JComboBox();
		c3.setModel(new DefaultComboBoxModel(new String[] {"SET CID3"}));
		c3.setMaximumRowCount(10);
		
		c4 = new JComboBox();
		c4.setModel(new DefaultComboBoxModel(new String[] {"SET CID4"}));
		c4.setMaximumRowCount(10);
		
		c5 = new JComboBox();
		c5.setModel(new DefaultComboBoxModel(new String[] {"SET CID5"}));
		c5.setMaximumRowCount(10);
		
		fc1 = new JComboBox();
		fc1.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
		fcc3 = new JComboBox();
		fcc3.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
		fcc4 = new JComboBox();
		fcc4.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
		fcc5 = new JComboBox();
		fcc5.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
	fcc2 = new JComboBox();
	fcc2.setModel(new DefaultComboBoxModel(new String[] {"SET"}));
		
		JLabel lblAge_1_1_1_1_2_1_1 = new JLabel("Course ID 3 :- ");
		lblAge_1_1_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblAge_1_1_1_1_2_1_2 = new JLabel("Course ID 4 :- ");
		lblAge_1_1_1_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblAge_1_1_1_1_2_1_3 = new JLabel("Course ID 5 :- ");
		lblAge_1_1_1_1_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
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
								.addComponent(fcc2, 0, 0, Short.MAX_VALUE)
								.addComponent(fc1, 0, 71, Short.MAX_VALUE)
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
						.addComponent(fc1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGender_1_1_1_1_2_1))
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
		Image img2 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(images));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 368, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(41, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 242, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel_4_1_1_1_2_1 = new GroupLayout(panel_4_1_1_1_2_1);
		gl_panel_4_1_1_1_2_1.setHorizontalGroup(
			gl_panel_4_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
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
				.addGroup(gl_panel_4_1_1_1_2_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_4_1_1_1_2_1.createSequentialGroup()
							.addComponent(panel_1_2_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(344, Short.MAX_VALUE))
		);
		panel_4_1_1_1_2_1.setLayout(gl_panel_4_1_1_1_2_1);
		Image img3 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		
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
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_4 = new JLabel("Selected CID :- ");
		lblStudentId_1_1_1_1_2_1_1_2_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_1_1 = new JLabel("Course Name :- ");
		lblStudentId_1_1_1_1_2_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		ciidname = new JTextField();
		ciidname.setColumns(10);
		
		ciid = new JTextField();
		ciid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		ciid.setColumns(10);
		
		JButton btnClear_1_1_1_1_2_1_1_2 = new JButton("Clear");
		btnClear_1_1_1_1_2_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ciid.setText("");
				ciidname.setText("");
				slccid.setText("");
			}
		});
		btnClear_1_1_1_1_2_1_1_2.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_4_1 = new JLabel("Course ID :- ");
		lblStudentId_1_1_1_1_2_1_1_2_4_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		slccid = new JTextField();
		slccid.setEditable(false);
		slccid.setBackground(new Color(192, 192, 192));
		slccid.setColumns(10);
		GroupLayout gl_panel_1_2_1_1_1_2_1_1_2 = new GroupLayout(panel_1_2_1_1_1_2_1_1_2);
		gl_panel_1_2_1_1_1_2_1_1_2.setHorizontalGroup(
			gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStudentId_1_1_1_1_2_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
							.addComponent(ciidname, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
							.addGap(131)
							.addComponent(btnClear_1_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_2_4_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_2_4))
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
							.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
								.addComponent(slccid, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(ciid, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_1_2_1_1_1_2_1_1_2.setVerticalGroup(
			gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(slccid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_4_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(ciid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(ciidname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnClear_1_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
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
		
		searchfield6 = new JTextField();
		searchfield6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		searchfield6.setColumns(10);
		
		JButton btnNewButton_2_1_1_1_1 = new JButton("Search");
		btnNewButton_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintable5.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT cid,cname FROM course WHERE (cid = '"+searchfield6.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) maintable5.getModel();
					
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
			
					while(rs.next()) {
						String cid=rs.getString(1);
						String cname=rs.getString(2);
						String[] row= {cid,cname};
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
				maintable5.setModel(new DefaultTableModel (null,new String[] {"cid","cname"}));
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
					.addComponent(searchfield6, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
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
							.addComponent(searchfield6, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1_1_1_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1_1_1_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1_1_1_2.setLayout(gl_panel_2_2_1_1_1_2);
		
		JScrollPane scrollPane_1_1_2 = new JScrollPane();
		GroupLayout gl_panel_1_1_2_2 = new GroupLayout(panel_1_1_2_2);
		gl_panel_1_1_2_2.setHorizontalGroup(
			gl_panel_1_1_2_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1_1_2_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_2_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1_1_2, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1_1_2_2.setVerticalGroup(
			gl_panel_1_1_2_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_2_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1_1_2, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		maintable5 = new JTable();
		maintable5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = maintable5.getSelectedRow();
				TableModel model = maintable5.getModel();
				ciid.setText(model.getValueAt(i, 0).toString());
				slccid.setText(model.getValueAt(i, 0).toString());
				ciidname.setText(model.getValueAt(i, 1).toString());
			}
		});
		scrollPane_1_1_2.setViewportView(maintable5);
		panel_1_1_2_2.setLayout(gl_panel_1_1_2_2);
		GroupLayout gl_panel_4_1_1_1_2_1_1_2 = new GroupLayout(panel_4_1_1_1_2_1_1_2);
		gl_panel_4_1_1_1_2_1_1_2.setHorizontalGroup(
			gl_panel_4_1_1_1_2_1_1_2.createParallelGroup(Alignment.LEADING)
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
		
		JPanel panel_4_1_1_1_2_1_1 = new JPanel();
		panel_4_1_1_1_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1_1_1_2_1_1.setBackground(new Color(0, 217, 217));
		tabbedPane.addTab("Faculty", null, panel_4_1_1_1_2_1_1, null);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_2.setBackground(new Color(0, 217, 217));
		
		JPanel panel_2_2_1_1_1 = new JPanel();
		panel_2_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_2_1_1_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Search By FID :-");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		searchfield3 = new JTextField();
		searchfield3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		searchfield3.setColumns(10);
		
		JButton btnNewButton_2_1_1_1 = new JButton("Search");
		btnNewButton_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintable3.setModel(new DefaultTableModel (null,new String[] {"FID","FNAME","FADDRESS","FPHONE"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT fid,fname FROM faculty WHERE (fid = '"+searchfield3.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) maintable3.getModel();
					
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
			
					while(rs.next()) {
						String fid=rs.getString(1);
						String fname=rs.getString(2);
						String[] row= {fid,fname};
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
				maintable3.setModel(new DefaultTableModel (null,new String[] {"cid","fid","fname","faddress","fphone"}));
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
					.addComponent(searchfield3, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
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
							.addComponent(searchfield3, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1_1_1.setLayout(gl_panel_2_2_1_1_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		GroupLayout gl_panel_1_1_2 = new GroupLayout(panel_1_1_2);
		gl_panel_1_1_2.setHorizontalGroup(
			gl_panel_1_1_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1_1, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1_1_2.setVerticalGroup(
			gl_panel_1_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1_1, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		maintable3 = new JTable();
		maintable3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		maintable3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = maintable3.getSelectedRow();
				TableModel model = maintable3.getModel();
				fid.setText(model.getValueAt(i, 0).toString());
				fname.setText(model.getValueAt(i, 1).toString());
			}
		});
		scrollPane_1_1.setViewportView(maintable3);
		panel_1_1_2.setLayout(gl_panel_1_1_2);
		
		JPanel panel_1_2_1_1_1_2_1_1 = new JPanel();
		panel_1_2_1_1_1_2_1_1.setForeground(new Color(70, 155, 249));
		panel_1_2_1_1_1_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1_1_2_1_1.setBackground(new Color(0, 217, 217));
		
		fid = new JTextField();
		fid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		fid.setColumns(10);
		
		fname = new JTextField();
		fname.setColumns(10);
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2_1 = new JLabel("Name :- ");
		lblStudentId_1_1_1_1_2_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_2 = new JLabel("Faculty ID :- ");
		lblStudentId_1_1_1_1_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnClear_1_1_1_1_2_1_1 = new JButton("Clear");
		btnClear_1_1_1_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fid.setText("");
				fname.setText("");
			}
		});
		btnClear_1_1_1_1_2_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_1_2_1_1_1_2_1_1 = new GroupLayout(panel_1_2_1_1_1_2_1_1);
		gl_panel_1_2_1_1_1_2_1_1.setHorizontalGroup(
			gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_2_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
								.addComponent(fname, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(fid, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
							.addGap(134)
							.addComponent(btnClear_1_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel_1_2_1_1_1_2_1_1.setVerticalGroup(
			gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(fid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(fname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_2_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnClear_1_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		panel_1_2_1_1_1_2_1_1.setLayout(gl_panel_1_2_1_1_1_2_1_1);
		
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
							.addComponent(panel_1_2_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(352, Short.MAX_VALUE))
		);
		panel_4_1_1_1_2_1_1.setLayout(gl_panel_4_1_1_1_2_1_1);
		
		JPanel panel_4_1_1_1_2_1_1_1 = new JPanel();
		panel_4_1_1_1_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4_1_1_1_2_1_1_1.setBackground(new Color(0, 217, 217));
		tabbedPane.addTab("Result", null, panel_4_1_1_1_2_1_1_1, null);
		
		JPanel panel_1_1_3 = new JPanel();
		panel_1_1_3.setBorder(null);
		panel_1_1_3.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_3 = new JLabel("");
		Image img4 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		lblNewLabel_1_1_3.setIcon(new ImageIcon(images));
		GroupLayout gl_panel_1_1_3 = new GroupLayout(panel_1_1_3);
		gl_panel_1_1_3.setHorizontalGroup(
			gl_panel_1_1_3.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 368, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_3.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1_1_3, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel_1_1_3.setVerticalGroup(
			gl_panel_1_1_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 242, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_3, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_1_1_3.setLayout(gl_panel_1_1_3);
		
		JPanel panel_1_2_1_1_1_2_1_1_1 = new JPanel();
		panel_1_2_1_1_1_2_1_1_1.setForeground(new Color(70, 155, 249));
		panel_1_2_1_1_1_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1_1_2_1_1_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_1 = new JLabel("COURSE 1 :- ");
		lblStudentId_1_1_1_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		c11 = new JTextField();
		c11.setEditable(false);
		c11.setBackground(new Color(192, 192, 192));
		c11.setColumns(10);
		
		c22 = new JTextField();
		c22.setEditable(false);
		c22.setBackground(new Color(192, 192, 192));
		c22.setColumns(10);
		
		m1 = new JTextField();
		m1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		m1.setColumns(10);
		
		m2 = new JTextField();
		m2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		m2.setColumns(10);
		
		c33 = new JTextField();
		c33.setBackground(new Color(192, 192, 192));
		c33.setEditable(false);
		c33.setColumns(10);
		
		c44 = new JTextField();
		c44.setBackground(new Color(192, 192, 192));
		c44.setEditable(false);
		c44.setColumns(10);
		
		c55 = new JTextField();
		c55.setBackground(new Color(192, 192, 192));
		c55.setEditable(false);
		c55.setColumns(10);
		
		m3 = new JTextField();
		m3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		m3.setColumns(10);
		
		m4 = new JTextField();
		m4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		m4.setColumns(10);
		
		m5 = new JTextField();
		m5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		m5.setColumns(10);
		
		JLabel lblStudentId_1_1_1_1_2_1_1_1_1 = new JLabel("COURSE 2 :- ");
		lblStudentId_1_1_1_1_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_1_2 = new JLabel("COURSE 3 :- ");
		lblStudentId_1_1_1_1_2_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_1_3 = new JLabel("COURSE 4 :- ");
		lblStudentId_1_1_1_1_2_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_1_4 = new JLabel("COURSE 5 :- ");
		lblStudentId_1_1_1_1_2_1_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblStudentId_1_1_1_1_2_1_1_1_5 = new JLabel("Student ID :-");
		lblStudentId_1_1_1_1_2_1_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		sid3 = new JTextField();
		sid3.setEditable(false);
		sid3.setColumns(10);
		sid3.setBackground(Color.LIGHT_GRAY);
		
		JButton btnClear_1_1_1_1_2_1_1_1_1 = new JButton("Get Percentage");
		btnClear_1_1_1_1_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(forpercentage()) {
					try {
						float percentage = (float) ((Integer.valueOf(m1.getText())+Integer.valueOf(m2.getText())+Integer.valueOf(m3.getText())+Integer.valueOf(m4.getText())+Integer.valueOf(m5.getText()))/5.0);
						JOptionPane.showMessageDialog(null, "Percentage for SID ("+sid3.getText()+") is :- "+percentage);
					}catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnClear_1_1_1_1_2_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnClear_1_1_1_1_2_1_1_1_1_1 = new JButton("Clear");
		btnClear_1_1_1_1_2_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c11.setText("");
				c22.setText("");
				c33.setText("");
				c44.setText("");
			    c55.setText("");
			    sid3.setText("");
				m1.setText("");
				m2.setText("");
				m3.setText("");
				m4.setText("");
				m5.setText("");
			}
		});
		btnClear_1_1_1_1_2_1_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_1_2_1_1_1_2_1_1_1 = new GroupLayout(panel_1_2_1_1_1_2_1_1_1);
		gl_panel_1_2_1_1_1_2_1_1_1.setHorizontalGroup(
			gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_1_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_1_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId_1_1_1_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
									.addComponent(c11, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(m1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
									.addComponent(c22, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(m2, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
									.addComponent(c33, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(m3, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
									.addComponent(c44, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(m4, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
									.addComponent(c55, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(m5, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStudentId_1_1_1_1_2_1_1_1_5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sid3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
							.addGap(48)
							.addComponent(btnClear_1_1_1_1_2_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnClear_1_1_1_1_2_1_1_1_1)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_panel_1_2_1_1_1_2_1_1_1.setVerticalGroup(
			gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_1_5, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sid3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_1)
						.addComponent(m1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c22, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(m2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c33, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_1_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(m3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c44, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_1_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(m4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(c55, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId_1_1_1_1_2_1_1_1_4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(m5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1_2_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnClear_1_1_1_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClear_1_1_1_1_2_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(14))
		);
		panel_1_2_1_1_1_2_1_1_1.setLayout(gl_panel_1_2_1_1_1_2_1_1_1);
		
		JPanel panel_1_1_2_1 = new JPanel();
		panel_1_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_2_1.setBackground(new Color(0, 217, 217));
		
		JPanel panel_2_2_1_1_1_1 = new JPanel();
		panel_2_2_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_2_1_1_1_1.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Search By SID :-");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		searchfield5 = new JTextField();
		searchfield5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		searchfield5.setColumns(10);
		
		JButton btnNewButton_1_3_1_1_1_1 = new JButton("Refresh");
		btnNewButton_1_3_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintable4.setModel(new DefaultTableModel (null,new String[] {"SID","CID1","MARK1","CID2","MARK2","CID3","MARK3","CID4","MARK4","CID5","MARK5"}));
				refresh4();
			}
		});
		btnNewButton_1_3_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_3_1_1_1_1_1 = new JButton("Search");
		btnNewButton_1_3_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintable4.setModel(new DefaultTableModel (null,new String[] {"SID","COURSE1","MARK1","COURSE2","MARK2","COURSE3","MARK3","COURSE4","MARK4","COURSE5","MARK5"}));
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
					Statement st=conn.createStatement();
					String query = "SELECT sid,cid1,mark1,cid2,mark2,cid3,mark3,cid4,mark4,cid5,mark5 FROM student WHERE (sid='"+searchfield5.getText()+"')";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) maintable4.getModel();
					
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
			
					while(rs.next()) {
						String sid=rs.getString(1);
						String cid1=rs.getString(2);
						String mark1=rs.getString(3);
						String cid2=rs.getString(4);
						String mark2=rs.getString(5);
						String cid3=rs.getString(6);
						String mark3=rs.getString(7);
						String cid4=rs.getString(8);
						String mark4=rs.getString(9);
						String cid5=rs.getString(10);
						String mark5=rs.getString(11);
						String[] row= {sid,cid1,mark1,cid2,mark2,cid3,mark3,cid4,mark4,cid5,mark5};
						model.addRow(row);
					}
					conn.close();
					st.close();
				}catch(ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
			}
		});
		btnNewButton_1_3_1_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_2_1_1_1_1 = new GroupLayout(panel_2_2_1_1_1_1);
		gl_panel_2_2_1_1_1_1.setHorizontalGroup(
			gl_panel_2_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_2_1_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1_1_1_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(searchfield5, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1_3_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnNewButton_1_3_1_1_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2_2_1_1_1_1.setVerticalGroup(
			gl_panel_2_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 52, Short.MAX_VALUE)
				.addGroup(gl_panel_2_2_1_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2_2_1_1_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_1_1_1_1)
							.addComponent(searchfield5, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGroup(gl_panel_2_2_1_1_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_3_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_3_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2_2_1_1_1_1.setLayout(gl_panel_2_2_1_1_1_1);
		
		JScrollPane scrollPane_1_1_1 = new JScrollPane();
		
		JPanel panel_2_1_1_1_1_1_1 = new JPanel();
		panel_2_1_1_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_1_1_1_1_1_1.setBackground(new Color(0, 217, 217));
		
		JButton btnNewButton_1_1_2_1_1_1_1 = new JButton("Update");
		btnNewButton_1_1_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empty4()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Uit54321");
						Statement stmt = conn.createStatement();
						String query = "update student set mark1='"+m1.getText()+"', mark2='"+m2.getText()+"', mark3='"+m3.getText()+"', mark4='"+m4.getText()+"', mark5='"+m5.getText()+"' where sid='"+sid3.getText()+"'";                            
						ResultSet rs=stmt.executeQuery(query);
						conn.close();
						maintable4.setModel(new DefaultTableModel (null,new String[] {"SID","CID1","MARK1","CID2","MARK2","CID3","MARK3","CID4","MARK4","CID5","MARK5"}));			
						refresh4();
					}
					catch(Exception e1) {System.out.println(e);}
				}
			}
		});
		btnNewButton_1_1_2_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JButton btnNewButton_1_1_1_1_1_1_1_1_1 = new JButton("Print");
		btnNewButton_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessageFormat header = new MessageFormat("MARKSHEET");
					MessageFormat footer = new MessageFormat("Page{0,number,integer}");
					maintable4.print(JTable.PrintMode.FIT_WIDTH,header,footer);
				}
				catch(Exception e1) {}
			}
		});
		btnNewButton_1_1_1_1_1_1_1_1_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		GroupLayout gl_panel_2_1_1_1_1_1_1 = new GroupLayout(panel_2_1_1_1_1_1_1);
		gl_panel_2_1_1_1_1_1_1.setHorizontalGroup(
			gl_panel_2_1_1_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 490, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1_1_1_1.createSequentialGroup()
					.addGap(64)
					.addComponent(btnNewButton_1_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(106)
					.addComponent(btnNewButton_1_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_panel_2_1_1_1_1_1_1.setVerticalGroup(
			gl_panel_2_1_1_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 59, Short.MAX_VALUE)
				.addGroup(gl_panel_2_1_1_1_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_1_1_1_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2_1_1_1_1_1_1.setLayout(gl_panel_2_1_1_1_1_1_1);
		GroupLayout gl_panel_1_1_2_1 = new GroupLayout(panel_1_1_2_1);
		gl_panel_1_1_2_1.setHorizontalGroup(
			gl_panel_1_1_2_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 789, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_2_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_2_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1_1_1, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel_1_1_2_1.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addComponent(panel_2_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
					.addGap(142))
		);
		gl_panel_1_1_2_1.setVerticalGroup(
			gl_panel_1_1_2_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 578, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_2_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1_1_1, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		maintable4 = new JTable();
		maintable4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = maintable4.getSelectedRow();
				TableModel model = maintable4.getModel();
				sid3.setText(model.getValueAt(i, 0).toString());
				c11.setText(model.getValueAt(i, 1).toString());
				m1.setText(model.getValueAt(i, 2).toString());
				c22.setText(model.getValueAt(i, 3).toString());
				m2.setText(model.getValueAt(i, 4).toString());
				c33.setText(model.getValueAt(i, 5).toString());
				m3.setText(model.getValueAt(i, 6).toString());
				c44.setText(model.getValueAt(i, 7).toString());
				m4.setText(model.getValueAt(i, 8).toString());
				c55.setText(model.getValueAt(i, 9).toString());
				m5.setText(model.getValueAt(i, 10).toString());
			}
		});
		scrollPane_1_1_1.setViewportView(maintable4);
		panel_1_1_2_1.setLayout(gl_panel_1_1_2_1);
		
		JPanel panel_1_1_4 = new JPanel();
		panel_1_1_4.setBorder(null);
		panel_1_1_4.setBackground(new Color(0, 217, 217));
		
		JLabel lblNewLabel_1_1_2 = new JLabel("");
		Image img5 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		lblNewLabel_1_1_2.setIcon(new ImageIcon(images));
		GroupLayout gl_panel_1_1_4 = new GroupLayout(panel_1_1_4);
		gl_panel_1_1_4.setHorizontalGroup(
			gl_panel_1_1_4.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 368, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_4.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel_1_1_4.setVerticalGroup(
			gl_panel_1_1_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 242, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_1_1_4.setLayout(gl_panel_1_1_4);
		GroupLayout gl_panel_4_1_1_1_2_1_1_1 = new GroupLayout(panel_4_1_1_1_2_1_1_1);
		gl_panel_4_1_1_1_2_1_1_1.setHorizontalGroup(
			gl_panel_4_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1_1_2_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4_1_1_1_2_1_1_1.createSequentialGroup()
							.addGroup(gl_panel_4_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1_1_4, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_1_2_1_1_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1_1_2_1, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1_1_3, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_panel_4_1_1_1_2_1_1_1.setVerticalGroup(
			gl_panel_4_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1_1_1_2_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4_1_1_1_2_1_1_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1_1_2_1, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel_4_1_1_1_2_1_1_1.createSequentialGroup()
							.addComponent(panel_1_2_1_1_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1_1_4, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
					.addGap(95)
					.addComponent(panel_1_1_3, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_4_1_1_1_2_1_1_1.setLayout(gl_panel_4_1_1_1_2_1_1_1);
		Image img6 = new ImageIcon(this.getClass().getResource("/stu.png")).getImage();
		mainpageframe.getContentPane().setLayout(groupLayout);
	}
	private void initComponents() {
		// TODO Auto-generated method stub
		
	}
}
