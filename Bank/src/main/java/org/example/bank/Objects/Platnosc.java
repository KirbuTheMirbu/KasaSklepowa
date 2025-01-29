package org.example.bank.Objects;

public class Platnosc {
    private String numer_karty;
    private String termin_waznosci;
    private int cvv;
    private double kwota;

    // Gettery i settery

    public String getNumer_karty() {
        return numer_karty;
    }

    public void setNumer_karty(String numer_karty) {
        this.numer_karty = numer_karty;
    }

    public String getTermin_waznosci() {
        return termin_waznosci;
    }

    public void setTermin_waznosci(String termin_waznosci) {
        this.termin_waznosci = termin_waznosci;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }
}
