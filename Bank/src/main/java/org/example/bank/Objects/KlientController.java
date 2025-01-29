package org.example.bank.Objects;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/klienci")
public class KlientController {

    @PostMapping("/dodaj")
    public String dodajKlienta(@RequestBody Klient klient) {
        // Obsługa danych otrzymanych w body
        return "Dodano klienta: " + klient.getNumer_karty() + " " + klient.getTermin_waznosci() + ", email: " + klient.getCvv();
    }
}
