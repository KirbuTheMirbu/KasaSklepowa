package org.example.bank.Objects;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/api")
public class PlatnoscController {

    @PostMapping("/platnosc")
    public String zaplac(@RequestBody Platnosc platnosc) throws SQLException {
            String url = "jdbc:mariadb://localhost:3306/bank";
            String user = "root";
            String pass = "";

            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql = "select * from karty";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String karta = rs.getString(3);
                if(karta.equals(platnosc.getNumer_karty())){
                    String termin = rs.getString(4);
                    int cvv = rs.getInt(5);
                    if(termin.equals(platnosc.getTermin_waznosci()) && cvv == platnosc.getCvv()){
                        int limit = rs.getInt(6);
                        if(platnosc.getKwota() < limit){
                            String konto = "select * from klienci join konta on klienci.id_klienta = konta.id_klienta where id_konta='" + rs.getInt(2) + "'";
                            ResultSet res = conn.createStatement().executeQuery(konto);
                            System.out.println(res.getInt(11));
                            return String.valueOf(res.getInt(12));
                            //String kradziez = "update konta set saldo="+(res.getInt(12) - platnosc.getKwota())+" where id_klienta='" + res.getInt(1) + "'";
                            //ResultSet res2 = conn.createStatement().executeQuery(kradziez);
                            //return "Transakcja udana";
                        }
                        else{
                            return "Przekroczono limit";
                        }
                    }
                    else{
                        return "Niepoprawne dane karty";
                    }
                }
            }


        return "Dodano klienta: " + platnosc.getNumer_karty() + " " + platnosc.getTermin_waznosci() + ", email: " + platnosc.getCvv()
                + " " + platnosc.getKwota();
    }
}
