package coursemanagementsystem;

// importing necessary libraries to run the program
import java.sql.*;
import java.util.*;
import javax.swing.*;

// class to connect to mysql using JDBC
public class DatabaseManager {
  
    private Connection conn; // declaring connection
    private Statement stmt; // declaring statement
    
    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/courses_db", "root", "root"); // connecting to the schema we already built, with username and password: root
            stmt = conn.createStatement(); // creates a statement object to throw to mysql
        } catch (Exception e) { // catch any exceptions
            e.printStackTrace();
        }
    }
        
    // method to insert data to the table we already built in mysql workbench
    public void insertData(String dbName, String tableName, int num, String Course, String Day, String Time) {
        try {
            stmt.executeUpdate("USE " + dbName);
            stmt.executeUpdate("INSERT INTO " + tableName + " (num, Course, Day, Time) VALUES (" + num + ", '" + Course + "', " + "'" + Day + "', " + "'" + Time + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // method to get "course" from the table in mysql, as an arraylist 
    public ArrayList<String> getCourseName() {
        ArrayList<String> coursenames = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT Course FROM table1");
            while (rs.next()) {
                String course = rs.getString("Course");
                coursenames.add(course);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursenames;
    }
    
    // method to get "day" from the table in mysql, as an arraylist
    public ArrayList<String> getCourseDay() {
        ArrayList<String> courseday = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT Day FROM Table1");
            while (rs.next()) {
                String day = rs.getString("Day");
                courseday.add(day);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseday;
    }
    
    // method to get "time" from the table in my mysql, as an arraylist
    public ArrayList<String> getCourseTime() {
        ArrayList<String> coursetime = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT Time FROM Table1");
            while (rs.next()) {
                String time = rs.getString("Time");
                coursetime.add(time);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursetime;
    }
    
    // method to insert data from the user in the gui to the table we already created in mysql
    public void insertStudentData(String dbName, String tableName, String student, int absence, float test1, float test2, float assignment, float finalScore, String notification) {
        try {
            stmt.executeUpdate("USE " + dbName);

            // Check if the student data already exists in the table
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE student = '" + student + "'");
            boolean dataExists = rs.next();
            rs.close();

            if (dataExists) {
                // If the student data already exists, update the corresponding row in the table
                stmt.executeUpdate("UPDATE " + tableName + " SET absence = " + absence + ", test1 = " + test1 + ", test2 = " + test2 + ", assignment = " + assignment + ", final = " + finalScore + ", notifications = '" + notification + "' WHERE student = '" + student + "'");
            } else {
                // If the student data does not exist, insert it
                stmt.executeUpdate("INSERT INTO " + tableName + " (student, absence, test1, test2, assignment, final, notifications) VALUES ('" + student + "', " + absence + ", " + test1 + ", " + test2 + ", " + assignment + ", " + finalScore + ", '" + notification + "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // method to get all the information of a specific student in the table and store it in an arraylist
    public ArrayList<String[]> getAllStudentsData() {
        ArrayList<String[]> studentsData = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int num = rs.getInt("num");
                String student = rs.getString("student");
                int absence = rs.getInt("absence");
                int test1 = rs.getInt("test1");
                int test2 = rs.getInt("test2");
                int assignment = rs.getInt("assignment");
                int finalScore = rs.getInt("final");
                String[] studentData = {Integer.toString(num), student, Integer.toString(absence), Integer.toString(test1), Integer.toString(test2), Integer.toString(assignment), Integer.toString(finalScore)};
                studentsData.add(studentData);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsData;
    }
    
    // method to add the student data from the professor portal to the panel in the student portal
    public Object[] addStudentDataToPanel(String dbName, String tableName, String student) {
        Object[] data = new Object[6];
        try {
            stmt.executeUpdate("USE " + dbName);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE student = '" + student + "'");
            while (rs.next()) {
                data[0] = rs.getInt("absence");
                data[1] = rs.getFloat("test1");
                data[2] = rs.getFloat("test2");
                data[3] = rs.getFloat("assignment");
                data[4] = rs.getFloat("final");
                data[5] = rs.getString("notifications");

                int absence = rs.getInt("absence");
                float test1 = rs.getFloat("test1");
                float test2 = rs.getFloat("test2");
                float assignment = rs.getFloat("assignment");
                float finalScore = rs.getFloat("final");
                String motifications = rs.getString("notifications");
          
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    // closing the mysql connection
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
