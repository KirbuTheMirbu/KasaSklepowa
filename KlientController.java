package org.example.bank;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/klienci")
public class KlientController {

    @PostMapping("/dodaj")
    public String dodajKlienta(@RequestBody Klient klient) {
        // Obsługa danych otrzymanych w body
        return "Dodano klienta: " + klient.getImie() + " " + klient.getNazwisko() + ", email: " + klient.getEmail();
    }
}
