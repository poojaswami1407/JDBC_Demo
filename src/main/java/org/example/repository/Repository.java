package org.example.repository;

import com.mysql.cj.protocol.Resultset;
import org.example.model.Student;

import java.sql.*;

public class Repository {
    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "pooja@2007";
    public void createdUser(Student student) {
        String sql = """
                            INSERT INTO students (name, email, age)
                            VALUES (?, ?, ?)""";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ){

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, student.getAge());
            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0) {
                System.out.println("User created successfully");
            } else {
                System.out.println("Error creating user");
            }
        } catch (SQLException e) {
            System.out.println("Error establishing connection ");
            e.printStackTrace();
        }
    }
    public void updateUser() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sql = "Update students set email='rahul.updated@example.com' where name='rahul'";
            int result = statement.executeUpdate(sql);
            if(result == 1) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("Error updating user");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error establishing connection ");
            e.printStackTrace();
        }
    }
    public void deleteUser() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sql = "Delete from students where name='rahul'";
            int result = statement.executeUpdate(sql);
            if(result == 1) {
                System.out.println("User deleted successfully");
            } else {
                System.out.println("Error deleting user");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error establishing connection ");
            e.printStackTrace();
        }
    }
    public void getUserById() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "select id, name,email,age from students where id = 2";

            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            Student student = mapRow(resultSet);

            System.out.println(student);

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error establishing connection ");
            e.printStackTrace();
        }
    }

    public void completeCRUD() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "select id, name,email,age from students where id = 2";
            boolean result = statement.execute(sql);
            if(result){
                Resultset resultset = (Resultset) statement.getResultSet();

            }else{
                int rowsAffected = statement.getUpdateCount();
            }


            connection.close();
        } catch (SQLException e) {
            System.out.println("Error establishing connection ");
            e.printStackTrace();
        }
    }


    private Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }

}
