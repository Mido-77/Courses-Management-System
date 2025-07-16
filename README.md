# Courses-Management-System
A Course Management System (CMS) streamlines student-professor interactions, enabling course tracking, grade review, and assignment management. Built in Java using OOP, ADTs, and exception handling, this GUI-based tool enhances efficiency and prevents errors in academic administration. 

# 📚 Course Management System GUI

A Java-based Course Management System developed as part of the BEE2053 Java Programming course (Jan–April 2023) at UCSI University. The system provides a simple and effective graphical interface for **students** and **professors** to manage academic activities, backed by a MySQL database for real-time data operations.

---

## 🚀 Features

### 👨‍🎓 Student Portal
- 🔐 Login (username & password)
- 📅 View course schedules
- 📊 Track academic performance and attendance
- 📂 View grades and announcements
- 📬 Notification panel for assignments and deadlines
- ➕ Add/drop courses
- 🚪 Logout

### 👨‍🏫 Professor Portal
- 🔐 Login (username & password)
- 📅 View teaching schedule
- 📝 Add/edit assignments, grades, announcements
- 👥 Manage student attendance and course content
- 📬 Notification panel for reminders and events
- 🚪 Logout

---

## 🧱 Tech Stack

- **Language**: Java  
- **GUI Framework**: Swing (`JFrame`, `JPanel`, `JTextField`, `JList`, `JLabel`, `JButton`)  
- **Database**: MySQL (via JDBC)  
- **Database Tool**: MySQL Workbench  
- **IDE**: NetBeans / IntelliJ / Eclipse

---

## 🛠️ Project Structure

- `CourseManagementSystem`: Main class and entry point with login and navigation controls
- `LoginPortal`: Handles student login and dashboard views
- `ProfessorPortal`: Handles professor login and interaction with students and courses
- `DatabaseManager`: Handles MySQL database operations like fetching, inserting, and updating data

---

## 🧪 How It Works

1. On launch, users choose between Student or Professor login.
2. The credentials are validated through MySQL.
3. On success:
   - Students access a dashboard with grades, schedules, and notifications.
   - Professors access a dashboard to update marks, manage attendance, and communicate with students.
4. All data is synchronized via the `DatabaseManager` class.

---

## 🛠️ Future Improvements

- 💬 In-app chat between students and lecturers
- 🧾 Fee payment integration
- 🎨 Custom themes and UI improvements
- 📈 Academic history & performance reports
- ✅ Validation checks (e.g. for grade entry limits)

---

## 🧑‍💻 Authors

- Mohammed Ahmed Hussien (1002163078)  
- Mohamed Tarek Essam (1002163249)  
- Abdullah Ahmed Elhaddad (1002060687)  
- Amtul Shaafi Hanaa (1002162952)

---

## 📝 License

This project was developed for academic purposes. Feel free to explore, modify, and build upon it with proper attribution.

