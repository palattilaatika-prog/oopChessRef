/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sakklepesek;

/**
 *
 * @author Denes
 */
public enum Irany {
    E(0, 1),
    D(0, -1),
    K(-1, 0),
    NY(1, 0),
    E_NY(1, 1),
    E_K(-1, 1),
    D_NY(1, -1),
    D_K(-1, -1);

    private final int dOszlop;
    private final int dSor;

    Irany(int dOszlop, int dSor) {
        this.dOszlop = dOszlop;
        this.dSor = dSor;
    }

    public int getDOszlop() { return dOszlop; }
    public int getDSor() { return dSor; }

    public static Irany[] ORTOGONALIS = { E, D, K, NY };
    public static Irany[] ATLOS = { E_NY, E_K, D_K, D_NY };
    public static Irany[] MINDEN = values();
}
