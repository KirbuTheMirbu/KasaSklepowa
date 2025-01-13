package org.example.Objects;

import java.util.ArrayList;

public class Koszyk {
    int rozm = 6;
    double koszt = 0;
    Product[] produkty = new Product[rozm];
    public Koszyk(){
        produkty[0] = new Product("5414635120807", "Puszka", 20.99);
        produkty[1] = new Product("9788328374157", "inf04", 39.99);
        produkty[2] = new Product("5702017415932", "lego", 999.99);
        produkty[3] = new Product("5449000011527", "Fanta", 9.99);
        produkty[4] = new Product("5449000234636", "Sprite", 6.11);
        produkty[5] = new Product("5449000000996", "Cola", 21.40);
    }
    ArrayList<int[]> kosz = new ArrayList<>();

    public void dodaj(String barcode){
        if(czyjest(barcode) != -1){
            kosz.get(czyjest(barcode))[1]++;
            koszt += ((double) Math.round((produkty[wyszukajProdukt(barcode)].price * 100)) / 100);
        }
        else{
            if(wyszukajProdukt(barcode) != -1){
                kosz.add(new int[]{wyszukajProdukt(barcode), 1});
                koszt += ((double) Math.round((produkty[wyszukajProdukt(barcode)].price * 100)) / 100);
            }
        }
    }

    int czyjest(String barcode){
        for (int i = 0; i < kosz.size(); i++){
            if(produkty[kosz.get(i)[0]].barcode.equals(barcode)){
                return i;
            }
        }
        return -1;
    }

    int wyszukajProdukt(String barcode){
        for (int i = 0; i < rozm; i++){
            if(produkty[i].barcode.equals(barcode)){
                return i;
            }
        }
        return -1;
    }

    public void wypiszKoszyk(){
        for(int[] i : kosz){
            System.out.println(produkty[i[0]].name + " " + i[1] + " * " + produkty[i[0]].price + " = " + i[1] * produkty[i[0]].price);
        }
        System.out.println("===========================");
        System.out.println("Koszt: " + String.format("%.2f",koszt));
    }

}
