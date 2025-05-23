package org.example.bank.Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class BlikController {

    private static final Logger log = LoggerFactory.getLogger(BlikController.class);

    @PostMapping("/blik")
    public String dodajKod(@RequestBody Blik blik/*, UriComponentsBuilder uriComponentsBuilder*/) throws SQLException, InterruptedException {
        String url = "jdbc:mariadb://localhost:3306/bank";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);

        int tempBlik = (int) Math.floor((Math.random() * 900000) + 100000);
        blik.setKod_blik(Integer.toString(tempBlik));
        System.out.println(blik.getKod_blik());
        System.out.println(blik.getId_konta());
        String sql = "INSERT INTO blik (kod_blik,id_konta) VALUES ('"+blik.getKod_blik()+"','"+blik.getId_konta()+"')";
        conn.createStatement().executeUpdate(sql);


        return blik.getKod_blik();
    }
}
