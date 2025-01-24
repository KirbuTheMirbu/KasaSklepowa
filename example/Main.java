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
            if(input.equals("reset") || input.equals("Reset")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                koszyk.wypiszKoszyk();
                koszyk.resetujKoszyk();
            }
            else if(input.length() != 13) {//if (Barcode.isBarcode(input))
                contin = false;
            }
            else{
                koszyk.dodaj(input);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                koszyk.wypiszKoszyk();
            }
        }

        System.out.println("Wybiesz metodę płatności(1 - Gotówka, 2 - Karta)");
        contin = true;
        while(contin) {
            String input = scanner.nextLine();
            if(input.equals("1")) {
                System.out.print("Gotówka: ");
                int money = Integer.parseInt(scanner.nextLine());
                koszyk.platnoscGot(money);
                contin = false;
            }
            else if(input.equals("2")) {//if (Barcode.isBarcode(input))
                contin = false;
            }
        }
    }
}