package org.example;

import org.example.Objects.Koszyk;
import org.example.Objects.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Koszyk koszyk = new Koszyk();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Co chcesz kupic? (Wpisz 'N' aby przerwać)");
        boolean contin = true;
        while(contin) {
            String input = scanner.nextLine();
            if(input.equals("N")) {
                contin = false;
            }
            else{
                koszyk.dodaj(input);
            }
        }
        koszyk.wypiszKoszyk();
    }
}