package org.example.bank.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;


@RestController
public class PlatnoscBlik {
    private static final Logger log = LoggerFactory.getLogger(BlikController.class);

    @PostMapping("/blikPl")
    public String dodajKod(@RequestBody String blik, Double kwota/*, UriComponentsBuilder uriComponentsBuilder*/) throws SQLException, InterruptedException {
        String url = "jdbc:mariadb://localhost:3306/bank";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);

        String sql = "SELECT * FROM blik";
        System.out.println("działa "+kwota);
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()) {

            if(rs.getString(2).equals(blik)){
                System.out.println("ZNALEZIONO BLIK!!!");
                String sql2 = "SELECT * FROM klienci WHERE id_konta = "+(rs.getString(1))+"";
                ResultSet rs2 = conn.createStatement().executeQuery(sql2);
            }
            else {
                System.out.print("Nie ma bliku :( ");
                System.out.println(rs.getString(2));
                System.out.print(" != ");
                System.out.println(blik);
            }
        }

        return "Wykonano kod";
    }
}
