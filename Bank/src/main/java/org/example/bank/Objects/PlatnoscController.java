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
                            res.next();
                            if(res.getInt(12) < platnosc.getKwota()){
                                return "Niewystarczająco środków na koncie";
                            }
                            String kradziez = "update konta set saldo="+(res.getInt(12) - platnosc.getKwota())+" where id_klienta='" + res.getInt(1) + "'";
                            conn.createStatement().executeUpdate(kradziez);
                            String dodaj = "INSERT INTO transakcje(id_karty, data, kwota, typ_transakcji) " +
                                    "VALUES ('"+rs.getInt(1)+"', '"+java.time.LocalDateTime.now()+"', '"
                                    +platnosc.getKwota()+"', 'Zakupy w sklepie')";
                            conn.createStatement().executeUpdate(dodaj);

                            return "Transakcja udana";
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
            return "Nie znaleziono karty";


        //return "Dodano klienta: " + platnosc.getNumer_karty() + " " + platnosc.getTermin_waznosci() + ", email: " + platnosc.getCvv()
                //+ " " + platnosc.getKwota();
    }
}
