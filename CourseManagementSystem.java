package coursemanagementsystem;

// importing necessary libraries to run the program
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.table.*;

/*
 ************************************************************************************************************************************************************************
 * To run this program successfully you need to do a couple of things before running it:                                                                                *
 * 1. create a connection in mysql with username and password both set to "root"                                                                                        *
 * 2. create a database in mysql(in this case call it Courses_db)                                                                                                       *
 * 3. create 4 tables in mysql(in this case call them(table1, course1, course2, course3)                                                                                *
 * 4. for table1: create 4 columns: (num)(NN) INT, (Course) VARCHAR(45), (Day) VARCHAR(45), (Time) VARCHAR(45) and leave it empty                                       *
 * 5. for course1 & course2 & course3: create 7 columns:                                                                                                                *
 * (student)(PK)(NN) VARCHAR(45), (absence) INT, (test1) FLOAT, (test2) FLOAT, (assignment) FLOAT, (final) FLOAT, (notifications) VARCHAR(255) and leave them empty     *
 * 6. create 3 text files for each course in the professor portal containing student names separated by a comma,                                                        *
 * but they all must have (Abdullah El-Haddad) inside them because the student portal is designed to be for this student                                                *
 * **********************************************************************************************************************************************************************
 */

// main class
public class CourseManagementSystem extends JFrame implements ActionListener{
	private DatabaseManager dbmanager; // to use the class that connects to mysql using JDBC, and use its methods
	private JButton loginButton;
	private JButton exitButton;
	private JButton ploginButton;

	public CourseManagementSystem() {
		dbmanager = new DatabaseManager();
		
		// inserting course name, day, and time to the table in mysql
		dbmanager.insertData("Courses_db", "table1", 1, "Operation Management", "Monday", "8 - 9:30");
		dbmanager.insertData("Courses_db", "table1", 2, "Statistics", "Tuesday", "11 - 12:30");
		dbmanager.insertData("Courses_db", "table1", 3, "Java Programming", "Wednesday", "2 - 3:30");
		dbmanager.insertData("Courses_db", "table1", 4, "Software Engineering", "Thursday", "5 - 6:30");
		dbmanager.insertData("Courses_db", "table1", 5, "Object Oriented Programming", "Friday", "11 - 12:30");
		dbmanager.insertData("Courses_db", "table1", 6, "Bahasa Melayu Komunikasi 3", "Monday", "9:30 - 12:30");
		dbmanager.insertData("Courses_db", "table1", 7, "Circuit Theory", "Tuesday", "11 - 12:30");
		dbmanager.insertData("Courses_db", "table1", 8, "Electromagetic Theory", "Wednesday", "8 - 9:30");
		dbmanager.insertData("Courses_db", "table1", 9, "Analogue Electronics", "Thursday", "5 - 6:30");
		dbmanager.insertData("Courses_db", "table1", 10, "Digital Electronics", "Friday", "4 - 5:30");
		dbmanager.insertData("Courses_db", "table1", 11, "Material Engineering", "Monday", "2 - 3:30");
		dbmanager.insertData("Courses_db", "table1", 12, "Control Systems", "Tuesday", "8 - 9:30");

		
		
	    setTitle("Welcome to UCSI Student Portal");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(300, 150);
	    setLocationRelativeTo(null);

	    // student portal button
	    loginButton = new JButton("Student Portal");
	    loginButton.addActionListener(this);
	    
	    // professor portal login button
	    ploginButton = new JButton("Professor Portal");
	    ploginButton.addActionListener(this);
	    
	    // exit button
	    exitButton = new JButton("Exit");
	    exitButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	        }
	    });

	    JPanel panel = new JPanel(new GridLayout(3, 1));
	    panel.add(loginButton);
	    panel.add(ploginButton);
	    panel.add(exitButton);

	    add(panel, BorderLayout.CENTER);
	    setVisible(true);
	}

	// using one function to detect which button was clicked and open the portal of the clicked button
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			LoginPortal loginportal = new LoginPortal();
		} else if (e.getSource() == ploginButton) {
			ProfessorPortal professorportal = new ProfessorPortal();
		}
		dispose();
	}
	
// the professor portal class
public class ProfessorPortal extends JFrame implements ActionListener{
	private JLabel proflabel;
	private JLabel passlabel;
	private JTextField proftextfield;
	private JPasswordField passtextfield;
	private JButton loginbutton;
    private DatabaseManager dbmanager1;
	
	public ProfessorPortal() {
		setTitle("Login Portal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        proflabel = new JLabel("Username:");
        passlabel = new JLabel("Password:");
        
        proftextfield = new JTextField(10);
        passtextfield = new JPasswordField(10);
        
        loginbutton = new JButton("Login");
        loginbutton.addActionListener(this);
        
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(proflabel);
        panel.add(proftextfield);
        panel.add(passlabel);
        panel.add(passtextfield);
        panel.add(new JLabel(""));
        panel.add(loginbutton);
        
        add(panel, BorderLayout.CENTER);
        setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		String username = proftextfield.getText();
        String password = new String(passtextfield.getPassword());

        // checks wether the username and password are correct
        if (username.equals("Dr. Mohammed Arif") && password.equals("UCSI1234")) {
        	dbmanager1 = new DatabaseManager();

        	ArrayList<String> courselist1 = new ArrayList<String>();
            courselist1 = dbmanager1.getCourseName(); // retrieving the courses from mysql
            
            ArrayList<String> courses = new ArrayList<String>();
            
            // adding just three courses to the professor to teach
            for (int i = 2; i < 5; i++) {
            	courses.add(courselist1.get(i));
            }
        	String coursesTeaching = String.join("\n", courses); // storing the courses he teaches in a string
        	
        	// retrieving the course schedule from mysql, and storing it in two arraylists(day & time)
        	ArrayList<String> coursedaylist = new ArrayList<String>();
            coursedaylist = dbmanager.getCourseDay();
            
            ArrayList<String> coursetimelist = new ArrayList<String>();
            coursetimelist = dbmanager.getCourseTime();
            
            // declaring an empty arraylist to add the schedule to it
            ArrayList<String> currentCoursetimelist = new ArrayList<String>();
            
            // using a for loop to add the day and time of each course,
            for (int i = 2; i < 5; i++) {
            	currentCoursetimelist.add(coursedaylist.get(i));
            	currentCoursetimelist.add(coursetimelist.get(i));
            }
        	
            // converting the schedule arraylist to a string, because it will be easier to add to text areas
            String currentCoursetime = String.join("\n", currentCoursetimelist);

            
        	JFrame mainframe = new JFrame("Course Management System(Professor)");
        	mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainframe.setSize(550, 350);
            mainframe.setLocationRelativeTo(null);
            mainframe.setResizable(false);

        	JPanel mainpanel = new JPanel(new FlowLayout());
        	
        	JLabel courseschedule = new JLabel("Schedule:");
        	JTextArea scheduletextarea = new JTextArea(10, 20);
        	scheduletextarea.append(currentCoursetime); // appending the schedule text area with the string that we created earlier
        	
        	scheduletextarea.setEditable(false);
        	
        	JLabel coursesLabel = new JLabel("Courses:");
        	JTextArea coursestextarea = new JTextArea(10, 20);
        	coursestextarea.append(coursesTeaching); // appending the courses text area with the string that we created earlier
        	
        	// A button for the first course
        	JButton course1 = new JButton(courselist1.get(2));
        	course1.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			JFrame mainframe = new JFrame("Course Options");
        	        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	        mainframe.setSize(800, 400);
        	        mainframe.setLocationRelativeTo(null);
        	        mainframe.setResizable(false);
        	        mainframe.setVisible(true);
        	        JPanel mainpanel = new JPanel(new GridLayout());
        	        DatabaseManager dbmanager2 = new DatabaseManager();

        	        // creating a table to add the students to it
        	        DefaultTableModel model = new DefaultTableModel();
        	        JTable table = new JTable(model);
        	        // adding columns for the user(professor) to edit and give marks
        	        model.addColumn("Student Name");
        	        model.addColumn("Absences");
        	        model.addColumn("Remaining Absences");
        	        model.addColumn("Test 1");
        	        model.addColumn("Test 2");
        	        model.addColumn("Assignment");
        	        model.addColumn("Final");
        	        model.addColumn("Notifications");

        	        // to determine how the data in a cell is displayed
        	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        	        // using a text file with 10 student names to fill the table
        	        try {
        	            File file = new File("Students.txt");
        	            Scanner scan = new Scanner(file);
        	            while (scan.hasNext()) {
        	                String line = scan.nextLine();
        	                String[] names = line.split(","); // because the names are seperated by a comma
        	                for (String name : names) { // loops through all the names
        	                    int absences = 0;
        	                    int remainingAbsences = 7;
        	                    float test1 = 0;
        	                    float test2 = 0;
        	                    float assignment = 0;
        	                    float finalExam = 0;
        	                    String notification = "";
        	                // adding different columns for each student
        	                model.addRow(new Object[]{name, absences, remainingAbsences, test1, test2, assignment, finalExam, notification});
        	                // adding the data to mysql
        	                dbmanager2.insertStudentData("Courses_db", "course1", name, absences, test1, test2, assignment, finalExam, notification);
        	            }
        	            }
        	        } catch (FileNotFoundException e2) { // catching file exception
        	            System.out.println("File not found: " + e2.getMessage());
        	        }

        	        // setting table size
        	        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        	        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        	        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        	        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        	        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()));
        	        // adding a listener to listen to the changes made in the (absence) column
        	        table.getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener() {
        	            @Override
        	            public void editingStopped(ChangeEvent e) {
        	                int row = table.getSelectedRow();
        	                int column = table.getSelectedColumn();
        	                String value = table.getValueAt(row, column).toString();
        	                try {
        	                    if (column == 1) {
        	                        // Handle changes to the absences column
        	                        int absences = Integer.parseInt(value);
        	                        int remainingAbsences = 7 - absences;
        	                        table.setValueAt(remainingAbsences, row, 2);
        	                    } else {
        	                        // Handle changes to the other columns
        	                        float score = Float.parseFloat(value);
        	                        if (score < 0 || score > 100) {
        	                            throw new NumberFormatException();
        	                        }
        	                    }

        	                    // Update the MySQL table with the new values once the user finishes changing the value of the first column(absence)
        	                    System.out.println("Updating student: " + table.getValueAt(row, 0) + " with values: " + table.getValueAt(row, 1) + ", " + table.getValueAt(row, 3) + ", " + table.getValueAt(row, 4) + ", " + table.getValueAt(row, 5) + ", " + table.getValueAt(row, 6) + ", " + table.getValueAt(row,  7)); // to ensure the table is updated with the right values
        	                    dbmanager2.insertStudentData("Courses_db", "course1", table.getValueAt(row, 0).toString(), Integer.parseInt(table.getValueAt(row, 1).toString()), Float.parseFloat(table.getValueAt(row, 3).toString()), Float.parseFloat(table.getValueAt(row, 4).toString()), Float.parseFloat(table.getValueAt(row, 5).toString()), Float.parseFloat(table.getValueAt(row, 6).toString()), table.getValueAt(row, 7).toString());
        	                } catch (NumberFormatException ex) {
        	                    System.out.println("Invalid input: " + value);
        	                }
        	            }

        	            @Override
        	            public void editingCanceled(ChangeEvent e) {
        	                // do nothing
        	            }
        	        });

        	        mainframe.add(new JScrollPane(table));

        	    }
        	});

        	// same as the previous button exactly
        	JButton course2 = new JButton(courselist1.get(3));
        	course2.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			JFrame mainframe = new JFrame("Course Options");
        	        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	        mainframe.setSize(800, 400);
        	        mainframe.setLocationRelativeTo(null);
        	        mainframe.setResizable(false);
        	        mainframe.setVisible(true);
        	        JPanel mainpanel = new JPanel(new GridLayout());
        	        DatabaseManager dbmanager2 = new DatabaseManager();

        	        DefaultTableModel model = new DefaultTableModel();
        	        JTable table = new JTable(model);
        	        model.addColumn("Student Name");
        	        model.addColumn("Absences");
        	        model.addColumn("Remaining Absences");
        	        model.addColumn("Test 1");
        	        model.addColumn("Test 2");
        	        model.addColumn("Assignment");
        	        model.addColumn("Final");
        	        model.addColumn("Notifications");

        	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        	        try {
        	            File file = new File("Students2.txt");
        	            Scanner scan = new Scanner(file);
        	            while (scan.hasNext()) {
        	                String line = scan.nextLine();
        	                String[] names = line.split(",");
        	                for (String name : names) {
        	                    int absences = 0;
        	                    int remainingAbsences = 7;
        	                    float test1 = 0;
        	                    float test2 = 0;
        	                    float assignment = 0;
        	                    float finalExam = 0;
        	                    String notification = "";
        	                model.addRow(new Object[]{name, absences, remainingAbsences, test1, test2, assignment, finalExam});
        	                dbmanager2.insertStudentData("Courses_db", "course2", name, absences, test1, test2, assignment, finalExam, notification);
        	            }
        	            }
        	        } catch (FileNotFoundException e2) {
        	            System.out.println("File not found: " + e2.getMessage());
        	        }

        	        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        	        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        	        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        	        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        	        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()));
        	        table.getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener() {
        	            @Override
        	            public void editingStopped(ChangeEvent e) {
        	                int row = table.getSelectedRow();
        	                int column = table.getSelectedColumn();
        	                String value = table.getValueAt(row, column).toString();
        	                try {
        	                    if (column == 1) {
        	                        int absences = Integer.parseInt(value);
        	                        int remainingAbsences = 7 - absences;
        	                        table.setValueAt(remainingAbsences, row, 2);
        	                    } else {
        	                        float score = Float.parseFloat(value);
        	                        if (score < 0 || score > 100) {
        	                            throw new NumberFormatException();
        	                        }
        	                    }

        	                    System.out.println("Updating student: " + table.getValueAt(row, 0) + " with values: " + table.getValueAt(row, 1) + ", " + table.getValueAt(row, 3) + ", " + table.getValueAt(row, 4) + ", " + table.getValueAt(row, 5) + ", " + table.getValueAt(row, 6) + ", " + table.getValueAt(row,  7));
        	                    dbmanager2.insertStudentData("Courses_db", "course2", table.getValueAt(row, 0).toString(), Integer.parseInt(table.getValueAt(row, 1).toString()), Float.parseFloat(table.getValueAt(row, 3).toString()), Float.parseFloat(table.getValueAt(row, 4).toString()), Float.parseFloat(table.getValueAt(row, 5).toString()), Float.parseFloat(table.getValueAt(row, 6).toString()), table.getValueAt(row, 7).toString());
        	                } catch (NumberFormatException ex) {
        	                    System.out.println("Invalid input: " + value);
        	                }
        	            }

        	            @Override
        	            public void editingCanceled(ChangeEvent e) {
        	                // do nothing
        	            }
        	        });

        	        mainframe.add(new JScrollPane(table));

        	    }
        	});

        	// same exactly as the previous buttons as well
        	JButton course3 = new JButton(courselist1.get(4));
        	course3.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			JFrame mainframe = new JFrame("Course Options");
        	        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	        mainframe.setSize(800, 400);
        	        mainframe.setLocationRelativeTo(null);
        	        mainframe.setResizable(false);
        	        mainframe.setVisible(true);
        	        JPanel mainpanel = new JPanel(new GridLayout());
        	        DatabaseManager dbmanager2 = new DatabaseManager();

        	        DefaultTableModel model = new DefaultTableModel();
        	        JTable table = new JTable(model);
        	        model.addColumn("Student Name");
        	        model.addColumn("Absences");
        	        model.addColumn("Remaining Absences");
        	        model.addColumn("Test 1");
        	        model.addColumn("Test 2");
        	        model.addColumn("Assignment");
        	        model.addColumn("Final");
        	        model.addColumn("Notifications");

        	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        	        try {
        	            File file = new File("Students3.txt");
        	            Scanner scan = new Scanner(file);
        	            while (scan.hasNext()) {
        	                String line = scan.nextLine();
        	                String[] names = line.split(",");
        	                for (String name : names) {
        	                    int absences = 0;
        	                    int remainingAbsences = 7;
        	                    float test1 = 0;
        	                    float test2 = 0;
        	                    float assignment = 0;
        	                    float finalExam = 0;
        	                    String notification = "";
        	                model.addRow(new Object[]{name, absences, remainingAbsences, test1, test2, assignment, finalExam});
        	                dbmanager2.insertStudentData("Courses_db", "course3", name, absences, test1, test2, assignment, finalExam, notification);
        	            }
        	            }
        	        } catch (FileNotFoundException e2) {
        	            System.out.println("File not found: " + e2.getMessage());
        	        }

        	        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        	        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        	        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        	        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        	        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        	        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()));
        	        table.getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener() {
        	            @Override
        	            public void editingStopped(ChangeEvent e) {
        	                int row = table.getSelectedRow();
        	                int column = table.getSelectedColumn();
        	                String value = table.getValueAt(row, column).toString();
        	                try {
        	                    if (column == 1) {
        	                        int absences = Integer.parseInt(value);
        	                        int remainingAbsences = 7 - absences;
        	                        table.setValueAt(remainingAbsences, row, 2);
        	                    } else {
        	                        float score = Float.parseFloat(value);
        	                        if (score < 0 || score > 100) {
        	                            throw new NumberFormatException();
        	                        }
        	                    }

        	                    System.out.println("Updating student: " + table.getValueAt(row, 0) + " with values: " + table.getValueAt(row, 1) + ", " + table.getValueAt(row, 3) + ", " + table.getValueAt(row, 4) + ", " + table.getValueAt(row, 5) + ", " + table.getValueAt(row, 6) + ", " + table.getValueAt(row,  7));
        	                    dbmanager2.insertStudentData("Courses_db", "course3", table.getValueAt(row, 0).toString(), Integer.parseInt(table.getValueAt(row, 1).toString()), Float.parseFloat(table.getValueAt(row, 3).toString()), Float.parseFloat(table.getValueAt(row, 4).toString()), Float.parseFloat(table.getValueAt(row, 5).toString()), Float.parseFloat(table.getValueAt(row, 6).toString()), table.getValueAt(row, 7).toString());
        	                } catch (NumberFormatException ex) {
        	                    System.out.println("Invalid input: " + value);
        	                }
        	            }

        	            @Override
        	            public void editingCanceled(ChangeEvent e) {
        	                // do nothing
        	            }
        	        });

        	        mainframe.add(new JScrollPane(table));

        	    }
        	});

        	mainpanel.add(courseschedule);
        	mainpanel.add(scheduletextarea);
        	mainpanel.add(coursesLabel);
        	mainpanel.add(coursestextarea);
        	mainpanel.add(course1);
        	mainpanel.add(course2);
        	mainpanel.add(course3);
        	
        	mainframe.add(mainpanel, BorderLayout.CENTER);
        	mainframe.setVisible(true);
        	dispose();
        }  else { // displaying a pop-up window containing a message if the username or password are incorrect
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
	}
}
	
// the student portal class
public class LoginPortal extends JFrame implements ActionListener {

    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private DatabaseManager dbmanager;

    public LoginPortal() {
        setTitle("Login Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        userLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        userTextField = new JTextField(10);
        passwordTextField = new JPasswordField(10);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        String password = new String(passwordTextField.getPassword());

        // checks wether the right username and password where entered
        if (username.equals("Abdullah El-Haddad") && password.equals("Haddad-2002")) {
        	
        	dbmanager = new DatabaseManager();
        	
        	ArrayList<String> courselist = new ArrayList<String>();
            courselist = dbmanager.getCourseName();
            
            ArrayList<String> coursesTakenlist = new ArrayList<String>(courselist.subList(6, 12)); // storing 6 courses in the already finished courses
            ArrayList<String> currentCourseslist = new ArrayList<String>(courselist.subList(0, 6)); // and 6 courses in the currently taking courses
            
            // converting to string to append the text areas
            String coursesTaken = String.join("\n", coursesTakenlist);
            String currentCourses = String.join("\n", currentCourseslist);
            
            ArrayList<String> coursedaylist = new ArrayList<String>();
            coursedaylist = dbmanager.getCourseDay();
            
            ArrayList<String> coursetimelist = new ArrayList<String>();
            coursetimelist = dbmanager.getCourseTime();
            
            JFrame mainFrame = new JFrame("Course Management System(Student)");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(1100, 650);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setResizable(false);

            JLabel coursesTakenLabel = new JLabel("Courses Taken:");
            JTextArea coursesTakenTextArea = new JTextArea(5, 20);
               
            coursesTakenTextArea.setText(coursesTaken);
            coursesTakenTextArea.setEditable(false);
                
            JLabel scheduleLabel = new JLabel("Schedule:");
            JTextArea scheduleTextArea = new JTextArea(10, 20);
            
            // creates an empty string
            String schedule = "";
            // looping through the day and time from the arraylist that got the data from mysql
            for(int i = 0; i < 6; i++) {
            	// appending the empty string with the day + time, resulting in a schedule format string
            	schedule += currentCourseslist.get(i) + ":\n" + coursedaylist.get(i) + "\n" + coursetimelist.get(i) + "\n\n";
            }
            scheduleTextArea.setText(schedule);
            scheduleTextArea.setEditable(false);
            JScrollPane scheduleScrollPane = new JScrollPane(scheduleTextArea); // adding a scroll panel because there is too much data
            
            JLabel notificationLabel = new JLabel("Notifications:");
            JTextArea notificationTextArea = new JTextArea(5, 20);                 
            notificationTextArea.setText("");
            notificationTextArea.setEditable(false);
            // to start a new line if the text area width is small
            notificationTextArea.setLineWrap(true); 
            notificationTextArea.setWrapStyleWord(true);

                        
            // Add components for current courses taken
            // by creating a table containing all the data that the professor set for this student(data will be added later)
            JLabel currentCoursesLabel = new JLabel("Current Courses:");
            JPanel currentCoursesPanel = new JPanel(new GridLayout(currentCourseslist.size() + 1, 7));
            currentCoursesPanel.add(new JLabel(""));
            currentCoursesPanel.add(new JLabel("Absence"));
            currentCoursesPanel.add(new JLabel("Test 1"));
            currentCoursesPanel.add(new JLabel("Test 2"));
            currentCoursesPanel.add(new JLabel("Assignment"));
            currentCoursesPanel.add(new JLabel("Final"));
            currentCoursesPanel.add(new JLabel("Total"));
            for (String course : currentCourseslist) {
                JLabel courseLabel = new JLabel(course);
                JTextField absenceTextField = new JTextField("0", 5);
                absenceTextField.setEditable(false);
                JTextField test1TextField = new JTextField("0", 5);
                test1TextField.setEditable(false);
                JTextField test2TextField = new JTextField("0", 5);
                test2TextField.setEditable(false);
                JTextField assignmentTextField = new JTextField("0", 5);
                assignmentTextField.setEditable(false);
                JTextField finalTextField = new JTextField("0", 5);
                finalTextField.setEditable(false);
                JTextField totalTextField = new JTextField("0", 5);
                totalTextField.setEditable(false);
                currentCoursesPanel.add(courseLabel);
                currentCoursesPanel.add(absenceTextField);
                currentCoursesPanel.add(test1TextField);
                currentCoursesPanel.add(test2TextField);
                currentCoursesPanel.add(assignmentTextField);
                currentCoursesPanel.add(finalTextField);
                currentCoursesPanel.add(totalTextField);
            }


            // Call the addStudentDataToPanel method to fill up the values in the currentCoursesPanel
            String student = "Abdullah El-Haddad"; // Replace with the name of the student you want to retrieve data for
            Object[] studentData1 = dbmanager.addStudentDataToPanel("Courses_db", "course1", student);
            
            int absence = (int) studentData1[0];
            float test1 = (float) studentData1[1];
            float test2 = (float) studentData1[2];
            float assignment = (float) studentData1[3];
            float finalScore = (float) studentData1[4];
            String notification = (String) studentData1[5];
             
            
            float total = test1 + test2 + assignment + finalScore;
            // the professor is teaching this student 3 courses which are number 3,4,5 in the current courses 
            // so we need to append the data for these specific courses
            // the table first row is all labels, the rest of the rows contain:(course label, absence,test1,test2,assignment,final,total) 
            // so we need to ignore components 0-6, then course 1: 7-13, course 2: 14-20
            // hence for course 3 we have to get components: 22-27(we don't need 21-course name)
            JTextField absenceTextField1 = (JTextField) currentCoursesPanel.getComponent(22); 
            absenceTextField1.setText(Integer.toString(absence));
            JTextField test1TextField1 = (JTextField) currentCoursesPanel.getComponent(23);
            test1TextField1.setText(Float.toString(test1));
            JTextField test2TextField1 = (JTextField) currentCoursesPanel.getComponent(24);  
            test2TextField1.setText(Float.toString(test2));
            JTextField assignmentTextField1 = (JTextField) currentCoursesPanel.getComponent(25);
            assignmentTextField1.setText(Float.toString(assignment));
            JTextField finalTextField1 = (JTextField) currentCoursesPanel.getComponent(26);
            finalTextField1.setText(Float.toString(finalScore));
            JTextField totalTextField1 = (JTextField) currentCoursesPanel.getComponent(27);
            totalTextField1.setText(Float.toString(total));
            
            notificationTextArea.setText(notification);
           
            // calling the method to add retrieved data from professor portal to the panel
            Object[] studentData2 = dbmanager.addStudentDataToPanel("Courses_db", "course2", student);
            
            int absence2 = (int) studentData2[0];
            float test12 = (float) studentData2[1];
            float test22 = (float) studentData2[2];
            float assignment2 = (float) studentData2[3];
            float finalScore2 = (float) studentData2[4];
            String notification2 = (String) studentData2[5];
             
            // and for course 4 we need components: 29-34
            float total2 = test12 + test22 + assignment2 + finalScore2;
            JTextField absenceTextField2 = (JTextField) currentCoursesPanel.getComponent(29);
            absenceTextField2.setText(Integer.toString(absence2));
            JTextField test1TextField2 = (JTextField) currentCoursesPanel.getComponent(30);
            test1TextField2.setText(Float.toString(test12));
            JTextField test2TextField2 = (JTextField) currentCoursesPanel.getComponent(31);  
            test2TextField2.setText(Float.toString(test22));
            JTextField assignmentTextField2 = (JTextField) currentCoursesPanel.getComponent(32);
            assignmentTextField2.setText(Float.toString(assignment2));
            JTextField finalTextField2 = (JTextField) currentCoursesPanel.getComponent(33);
            finalTextField2.setText(Float.toString(finalScore2));
            JTextField totalTextField2 = (JTextField) currentCoursesPanel.getComponent(34);
            totalTextField2.setText(Float.toString(total2));
            
            // getting the text from the notification text area and adding to it the data from professor portal for this course 
            // then appending the text area again
            String notificationtext = notificationTextArea.getText();
            String updatedtext = notificationtext + "\n" + notification2;
            notificationTextArea.setText(updatedtext);
            
            // calling the method to add retrieved data from professor portal to the panel
            Object[] studentData3 = dbmanager.addStudentDataToPanel("Courses_db", "course3", student);
            
            int absence3 = (int) studentData3[0];
            float test13 = (float) studentData3[1];
            float test23 = (float) studentData3[2];
            float assignment3 = (float) studentData3[3];
            float finalScore3 = (float) studentData3[4];
            String notification3 = (String) studentData3[5];
             
            // and for course 5 we need components: 36-41
            float total3 = test13 + test23 + assignment3 + finalScore3;
            JTextField absenceTextField3 = (JTextField) currentCoursesPanel.getComponent(36);
            absenceTextField3.setText(Integer.toString(absence3));
            JTextField test1TextField3 = (JTextField) currentCoursesPanel.getComponent(37);
            test1TextField3.setText(Float.toString(test13));
            JTextField test2TextField3 = (JTextField) currentCoursesPanel.getComponent(38);  
            test2TextField3.setText(Float.toString(test23));
            JTextField assignmentTextField3 = (JTextField) currentCoursesPanel.getComponent(39);
            assignmentTextField3.setText(Float.toString(assignment3));
            JTextField finalTextField3 = (JTextField) currentCoursesPanel.getComponent(40);
            finalTextField3.setText(Float.toString(finalScore3));
            JTextField totalTextField3 = (JTextField) currentCoursesPanel.getComponent(41);
            totalTextField3.setText(Float.toString(total3));
            
            // getting the text from the notification text area and adding to it the data from professor portal for this course 
            // then appending the text area again
            String notificationtext2 = notificationTextArea.getText();
            String updatedtext2 = notificationtext2 + "\n" + notification3;
            notificationTextArea.setText(updatedtext2);

            
            // Close the database connection
            dbmanager.closeConnection();

            
            // Add components to main frame
            JPanel mainPanel = new JPanel(new GridLayout(4, 2));
            mainPanel.add(coursesTakenLabel);
            mainPanel.add(coursesTakenTextArea);
            mainPanel.add(scheduleLabel);
            mainPanel.add(scheduleScrollPane);
            mainPanel.add(currentCoursesLabel);
            mainPanel.add(currentCoursesPanel);
            mainPanel.add(notificationLabel);
            mainPanel.add(notificationTextArea);
            
            
            mainFrame.add(mainPanel);
            mainFrame.setVisible(true);
            dispose(); // Close the login portal window
        }
            else { // displaying a pop-up window containing a message if the username or password are incorrect
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    }

    public static void main(String[] args) {
        new CourseManagementSystem(); // To run the program
    }
}
}