package com.csit228.csit228_prefinalstabada;

import com.csit228.csit228_prefinalstabada.data.User;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseConnection {
    private String URL = "jdbc:mysql://localhost:3306/csit228prefinalf3";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";


    public boolean validateUser(String username, String password){
        String query = "SELECT username,password FROM users";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String checkName = resultSet.getString("username");
                String checkPass = resultSet.getString("password");

                if(checkName.equals(username) && checkPass.equals(password))
                    return true;
            }
            return false;
        }catch (SQLException e){
            System.out.println("From validate user" +e.getMessage());
            return false;
        }
    }

    public User isTeacher(String username, String password){
        String query = "SELECT type,first_name,last_name FROM users WHERE username = '"+username+"' AND password = '"+password+"'";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int type = resultSet.getInt("type");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");

                return new User(username,password,type,fname,lname);
            }
        }catch (SQLException e){
            System.out.println("From isTeacher " +e.getMessage());
        }
        return null;
    }

    public List<String> getStudentQuizData(){
        String query = "SELECT title FROM quizzes";
        List<String> ret = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String quizName = resultSet.getString("title");
                ret.add(quizName);
            }
        }catch (SQLException e){
            System.out.println("From isTeacher " +e.getMessage());
        }
        return ret;
    }

    private List<Integer> getQuizId(){
        String query = "SELECT id FROM quizzes";
        List<Integer> ret = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                ret.add(id);
            }
        }catch (SQLException e){
            System.out.println("From isTeacher " +e.getMessage());
        }
        return ret;
    }

    public List<String> getQuestions(){
        String query = "SELECT title FROM quizzes";
        List<String> ret = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String quizName = resultSet.getString("title");
                ret.add(quizName);
            }
        }catch (SQLException e){
            System.out.println("From isTeacher " +e.getMessage());
        }
        return ret;
    }

    /*public boolean insert_tblStudents(String studentName, int courseID){
        boolean isCommit = false;
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(
                    "insert into students (name, course) values(?,?)"
            ))
        {
            connection.setAutoCommit(false);

            pstmt.setString(1,studentName);
            pstmt.setInt(2,courseID);

            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0) {
                connection.commit();
                isCommit = true;
            }
            else connection.rollback();
        }catch (SQLException e){
            System.out.println("From insert students" + e.getMessage());
        }
        return isCommit;
    }

    public boolean insert_tblCourses(String courseCode, String courseName){
        boolean isCommit = false;
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(
                    "insert ignore into courses (code, course) values(?,?)"
            ))
        {
            connection.setAutoCommit(false);

            pstmt.setString(1,courseCode);
            pstmt.setString(2,courseName);

            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0) {
                connection.commit();
                isCommit = true;
            }
            else connection.rollback();
        }catch (SQLException e){
            System.out.println("From insert courses" + e.getMessage());
        }
        return isCommit;
    }

    public void delete_tblStudents(int id){
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM students WHERE id = ?"
            ))
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("From delete students" +e.getMessage());
        }
    }

    public void delete_tblCourses(int id){
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM courses WHERE id = ?"
            ))
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("From delete courses" +e.getMessage());
        }
    }

    public void retrieve_tblStudents(List<String> students){
        String queryStudents = "SELECT * FROM students";
        String queryCode = "SELECT code FROM courses WHERE id = ?";
        String queryProgram = "SELECT course FROM courses WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(queryStudents)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int courseID = resultSet.getInt("course");

                try (PreparedStatement pstmtCode = connection.prepareStatement(queryCode);
                     PreparedStatement pstmtProgram = connection.prepareStatement(queryProgram)) {

                    pstmtCode.setInt(1, courseID);
                    ResultSet rsCode = pstmtCode.executeQuery();
                    String code = rsCode.next() ? rsCode.getString("code") : "";

                    pstmtProgram.setInt(1, courseID);
                    ResultSet rsProgram = pstmtProgram.executeQuery();
                    String course = rsProgram.next() ? rsProgram.getString("course") : "";

                    students.add("[" + id + "] " + name + " | " + code + " | " + course);
        }
            }
        }catch (SQLException e){
            System.out.println("From retrieve students" +e.getMessage());
        }
    }

    public void retrieve_tblCourses(List<String> courses){
        String query = "SELECT * FROM courses";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("course");
                String code = resultSet.getString("code");

                courses.add("[" + id + "] " + code + " | " + name);
            }
        }catch (SQLException e){
            System.out.println("From retrieve courses" +e.getMessage());
        }
    }

    public boolean update_tblStudents(int id, String studentName, int courseID){
        boolean isCommit = false;
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(
                    "update students set name=?, course=? where id=?"
            ))
        {
            connection.setAutoCommit(false);

            pstmt.setString(1,studentName);
            pstmt.setInt(2,courseID);
            pstmt.setInt(3,id);

            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0) {
                connection.commit();
                isCommit = true;
            }
            else connection.rollback();
        }catch (SQLException e){
            System.out.println("From update students" +e.getMessage());
        }
        return isCommit;
    }

    public boolean update_tblCourses(int id, String courseName, String courseCode){
        boolean isCommit = false;
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(
                    "update courses set course=?, code=? where id=?"
            ))
        {
            connection.setAutoCommit(false);

            pstmt.setString(1,courseName);
            pstmt.setString(2,courseCode);
            pstmt.setInt(3,id);

            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0) {
                connection.commit();
                isCommit = true;
            }
            else connection.rollback();
        }catch (SQLException e){
            System.out.println("From update courses" + e.getMessage());
        }
        return isCommit;
    }*/
}
