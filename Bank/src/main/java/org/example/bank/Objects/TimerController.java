package org.example.bank.Objects;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;



@RestController
@RequestMapping("/api")
public class TimerController {
    @PostMapping("/timer")
    public String setTimer(@RequestBody Blik blik/*, UriComponentsBuilder uriComponentsBuilder*/) throws SQLException, InterruptedException {
        String url = "jdbc:mariadb://localhost:3306/bank";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);
        System.out.println(blik.getKod_blik());


        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("gfgfg");
                try {
                    String sql2 = "DELETE FROM blik WHERE kod_blik='" + blik.getKod_blik() + "'";
                    conn.createStatement().executeUpdate(sql2);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(task, 120000);
        return "koniec";
    }
}