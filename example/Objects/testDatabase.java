package org.example.Objects;
import java.io.*;
import java.sql.*;


public class testDatabase {
    public testDatabase() throws Exception {
        String url = "jdbc:mysql://localhost:3306/produkty";
        String username = "rootgfg"; // MySQL credentials
        String password = "gfg123";
        String query
                = "select * from produkty";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                url, username, password);
        System.out.println(
                "Connection Established successfully");
    }
}
