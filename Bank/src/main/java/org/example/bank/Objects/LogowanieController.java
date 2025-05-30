package org.example.bank.Objects;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class LogowanieController {

    @PostMapping("/login")
    public String dodajKod(@RequestBody Logowanie logowanie/*, UriComponentsBuilder uriComponentsBuilder*/) throws SQLException, InterruptedException {
        String url = "jdbc:mariadb://localhost:3306/bank";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);

        String sql = "SELECT * FROM konta";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println(logowanie.getLogin());
            if(rs.getString(6).equals(logowanie.getLogin())){
                System.out.println("ZNALEZIONO KONTO!!!");
                if(rs.getString(7).equals(logowanie.getHaslo())){
                    return rs.getString(1);
                }
                else{
                    return "-2";
                }
            }
            else {
                System.out.print("Nie ma kodu :( ");
                System.out.println(logowanie.getLogin());
                System.out.println(" :( ");
                System.out.println(logowanie.getHaslo());
            }
        }
        return "-1";
    }

}
