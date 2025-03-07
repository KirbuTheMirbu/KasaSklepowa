package org.example;

import org.example.Objects.Koszyk;
import org.example.Objects.Product;
import org.example.Objects.testDatabase;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
                HttpClient client = HttpClient.newHttpClient();
                System.out.print("Numer Karty: ");
                String cardNum = scanner.nextLine();
                System.out.print("Numer Daty: ");
                String expirationDate = scanner.nextLine();
                System.out.print("Numer CVV: ");
                int cvv = Integer.parseInt(scanner.nextLine());
                // numer_karty,termin_waznosci, cvv
                String jsonInput = "{\"numer_karty\":\""+cardNum+"\",\"termin_waznosci\":\""+expirationDate+"\"," +
                        "\"cvv\":\""+cvv+"\",\"kwota\":\""+koszyk.getKoszt()+"\"}";
                System.out.println(jsonInput);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://192.168.0.117:7070/api/platnosc"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response Code: " + response.statusCode());
                System.out.println("Response Body: " + response.body());
                if(response.body().equals("Transakcja udana")) {
                    koszyk.platnoscKar(cardNum);
                }
                else{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(response.body());
                }
                contin = false;
            }
        }
    }
}