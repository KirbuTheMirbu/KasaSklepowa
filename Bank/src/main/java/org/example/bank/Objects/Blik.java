package org.example.bank.Objects;

public class Blik {
    private String kod_blik;

    public int getId_konta() {
        return id_konta;
    }

    public void setId_konta(int id_konta) {
        this.id_konta = id_konta;
    }

    public String getKod_blik() {
        return kod_blik;
    }

    public void setKod_blik(String kod_blik) {
        this.kod_blik = kod_blik;
    }

    private int id_konta;

    private double koszt;

    public double getKoszt() {
        return koszt;
    }

    public void setKoszt(double koszt) {
        this.koszt = koszt;
    }
}
