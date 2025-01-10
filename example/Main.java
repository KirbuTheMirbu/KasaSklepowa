package org.example;

import org.example.Objects.Koszyk;
import org.example.Objects.Product;
import org.example.Objects.testDatabase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Koszyk koszyk = new Koszyk();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Co chcesz kupic? (Wpisz 'N' aby przerwać)");
        boolean contin = true;
        while(contin) {
            String input = scanner.nextLine();
            if(input.length() != 13) {
                contin = false;
            }
            else{
                koszyk.dodaj(input);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                koszyk.wypiszKoszyk();
            }
        }
    }
}