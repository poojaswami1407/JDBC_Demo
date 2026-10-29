package org.example;

import org.example.model.Student;
import org.example.repository.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        repository.createdUser(new Student("Arohi", "arohi@gmail.com", 25));
        //repository.updateUser();
        //repository.deleteUser();
        //repository.getUserById();
    }
}

