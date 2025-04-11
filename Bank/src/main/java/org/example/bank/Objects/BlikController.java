package org.example.bank.Objects;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.*;

@RestController
@RequestMapping("/api")
public class BlikController {

    private static final Logger log = LoggerFactory.getLogger(BlikController.class);

    @PostMapping("/blik")
    public String dodajKod(@RequestBody Blik blik, UriComponentsBuilder uriComponentsBuilder) throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/bank";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);


        System.out.println(blik.getKodBlik());
        System.out.println(blik.getIdkonta());
        String sql = "INSERT INTO blik (kod_blik,id_konta) VALUES ('"+blik.getKodBlik()+"','"+blik.getIdkonta()+"')";
        conn.createStatement().executeUpdate(sql);
        return "Dodano kod";
    }
}