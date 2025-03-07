package org.example.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Koszyk {
    int rozm = 6;
    double koszt = 0;
    //Product[] produkty = new Product[rozm];
    ArrayList<Product> produkty = new ArrayList<>();
    public Koszyk() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/sklep";
        String user = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "select * from produkty";
        ResultSet rs = connection.createStatement().executeQuery(sql);
        while (rs.next()) {
            String barcode = rs.getString(2);
            String name = rs.getString(3);
            double price = rs.getDouble(4);
            produkty.add(new Product(barcode, name, price));
        }

        /*produkty[0] = new Product("5414635120807", "Puszka", 20.99);
        produkty[1] = new Product("9788328374157", "inf04", 39.99);
        produkty[2] = new Product("5702017415932", "lego", 999.99);
        produkty[3] = new Product("5449000011527", "Fanta", 9.99);
        produkty[4] = new Product("5449000234636", "Sprite", 6.11);
        produkty[5] = new Product("5449000000996", "Cola", 21.40);*/
    }
    ArrayList<int[]> kosz = new ArrayList<>();

    public void dodaj(String barcode){
        if(czyjest(barcode) != -1){
            kosz.get(czyjest(barcode))[1]++;
            koszt += ((double) Math.round((produkty.get(wyszukajProdukt(barcode)).price * 100)) / 100);
        }
        else{
            if(wyszukajProdukt(barcode) != -1){
                kosz.add(new int[]{wyszukajProdukt(barcode), 1});
                koszt += ((double) Math.round((produkty.get(wyszukajProdukt(barcode)).price * 100)) / 100);
            }
        }
    }

    int czyjest(String barcode){
        for (int i = 0; i < kosz.size(); i++){
            if(produkty.get(kosz.get(i)[0]).barcode.equals(barcode)){
                return i;
            }
        }
        return -1;
    }

    int wyszukajProdukt(String barcode){
        for (int i = 0; i < rozm; i++){
            if(produkty.get(i).barcode.equals(barcode)){
                return i;
            }
        }
        return -1;
    }

    public void wypiszKoszyk(){
        for(int[] i : kosz){
            System.out.println(produkty.get(i[0]).name + " " + i[1] + " * " + produkty.get(i[0]).price + " = " + i[1] * produkty.get(i[0]).price);
        }
        System.out.println("===========================");
        System.out.println("Koszt: " + String.format("%.2f",koszt));
    }

    public void resetujKoszyk(){
        kosz.clear();
        koszt = 0;
        System.out.println("Zresetowano Koszyk");
    }

    public void platnoscGot(double pien){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for(int[] i : kosz){
            System.out.println(produkty.get(i[0]).name + " " + i[1] + " * " + produkty.get(i[0]).price + " = " + i[1] * produkty.get(i[0]).price);
        }
        System.out.println("===========================");
        System.out.println("Koszt: " + String.format("%.2f",koszt) + "zł");
        System.out.println("Płatność Gotówką: " + String.format("%.2f",pien) + "zł");
        System.out.println("Reszta: " + String.format("%.2f",(pien - koszt)) + "zł");
    }

    public void platnoscKar(String NKar){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for(int[] i : kosz){
            System.out.println(produkty.get(i[0]).name + " " + i[1] + " * " + produkty.get(i[0]).price + " = " + i[1] * produkty.get(i[0]).price);
        }
        System.out.println("===========================");
        System.out.println("Koszt: " + String.format("%.2f",koszt) + "zł");
        System.out.print("Płatność kartą ");
        for (int i = 0; i < 16; i++){
            if(i <= 3 || i >= 12){
                System.out.print(NKar.charAt(i));
            }
            else {
                System.out.print("*");
            }

        }
        System.out.println(" ");
    }

    public double getKoszt(){
        return koszt;
    }

}
