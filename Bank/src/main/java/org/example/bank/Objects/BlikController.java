package org.example.bank.Objects;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.*;

@RestController
@RequestMapping("/api")
public class BlikController {

    @PostMapping("/blik")
    public String dodajKod(@RequestBody Blik blik, UriComponentsBuilder uriComponentsBuilder) throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/bank";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);


        String sql = "INSERT INTO blik (kod_blik) VALUES ("+blik.getKodBlik()+")";
        conn.createStatement().executeUpdate(sql);

        return "Dodano kod";
    }
}