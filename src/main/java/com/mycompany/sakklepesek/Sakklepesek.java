/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sakklepesek;

import java.util.Scanner;

/**
 *
 * @author Denes
 */
public class Sakklepesek {
    public static void main(String[] args) {
        Tabla tabla = new Tabla();
        tabla.alaphelyzet();
        Scanner sc = new Scanner(System.in);
        boolean feherJon = true;

        System.out.println("=== SAKKBÍRÓ ALKALMAZÁS ===");
        System.out.println("Adja meg a lépéseket 'e2 e4' formátumban! (Kilépés: 'exit')");

        while (true) {
            tabla.kirajzol();
            System.out.print((feherJon ? "Világos" : "Sötét") + " lépése: ");
            String sor = sc.nextLine().trim();

            if ("exit".equalsIgnoreCase(sor)) break;

            String[] reszek = sor.split("\\s+");
            if (reszek.length != 2) {
                System.out.println("Hiba: Használja a 'honnan hova' formátumot (pl. e2 e4)!");
                continue;
            }

            try {
                Mezo honnan = Mezo.of(reszek[0]);
                Mezo hova = Mezo.of(reszek[1]);

                if (tabla.uresE(honnan)) {
                    System.out.println("Hiba: A forrásmezőn nincs figura!");
                    continue;
                }

                if (tabla.figura(honnan).isFeher() != feherJon) {
                    System.out.println("Hiba: Nem az Ön figurája következik!");
                    continue;
                }

                if (tabla.szabalyosLepesE(honnan, hova)) {
                    tabla.vegrehajt(honnan, hova);
                    System.out.println("-> Szabályos lépés!");
                    feherJon = !feherJon;
                } else {
                    System.out.println("-> SZABÁLYTALAN LÉPÉS!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Hiba: " + e.getMessage());
            }
        }
        sc.close();
    }
}
