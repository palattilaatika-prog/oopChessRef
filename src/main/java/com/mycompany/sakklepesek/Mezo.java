/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sakklepesek;

/**
 *
 * @author Denes
 */
public record Mezo(int oszlop, int sor) {

    public Mezo {
        if (oszlop < 0 || oszlop > 7 || sor < 0 || sor > 7) {
            throw new IllegalArgumentException("The coordinates must be between 0 and 7 " + oszlop + ", " + sor + ")");
        }
    }
    public static Mezo of(String algebrai) {
        if (algebrai == null || algebrai.length() != 2) {
            throw new IllegalArgumentException("Not a possible algebric representation: " + algebrai);
        }
        char oszlopChar = Character.toLowerCase(algebrai.charAt(0));
        char sorChar = algebrai.charAt(1);

        int oszlop = oszlopChar - 'a';
        int sor = sorChar - '1';

        return new Mezo(oszlop, sor);
    }

    @Override
    public String toString() {
        return "" + (char) ('a' + oszlop) + (sor + 1);
    }
}
