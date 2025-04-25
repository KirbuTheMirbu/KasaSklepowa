package org.example.bank.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class PlatnoscBlik {
    private static final Logger log = LoggerFactory.getLogger(BlikController.class);

    @PostMapping("/blikPl")
    public String dodajKod(@RequestBody Blik blik/*, UriComponentsBuilder uriComponentsBuilder*/) throws SQLException, InterruptedException {
        String url = "jdbc:mariadb://localhost:3306/bank";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);


        System.out.println(blik.getKod_blik());
        System.out.println(blik.getId_konta());
        String sql = "INSERT INTO blik (kod_blik,id_konta) VALUES ('"+blik.getKod_blik()+"','"+blik.getId_konta()+"')";
        conn.createStatement().executeUpdate(sql);

        //Thread.sleep(5000);

        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("gfgfg");
                try {
                    String sql2 = "DELETE FROM blik WHERE kod_blik='"+blik.getKod_blik()+"'";
                    conn.createStatement().executeUpdate(sql2);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(task, 10000);
        return "Wykonano kod";
    }
}
