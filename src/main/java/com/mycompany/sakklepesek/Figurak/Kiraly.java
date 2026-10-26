/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sakklepesek.Figurak;

import com.mycompany.sakklepesek.Mezo;
import com.mycompany.sakklepesek.Tabla;

/**
 *
 * @author Denes
 */
public class Kiraly extends Figura {
    public Kiraly(boolean feher) { super(feher); }

    @Override
    public boolean lephet(Mezo honnan, Mezo hova, Tabla tabla) {
        if (honnan.equals(hova)) return false;
        Figura cel = tabla.figura(hova);
        if (cel != null && cel.isFeher() == isFeher()) return false;

        int dOszlop = Math.abs(honnan.oszlop() - hova.oszlop());
        int dSor = Math.abs(honnan.sor() - hova.sor());

        if (dOszlop <= 1 && dSor <= 1) {
            return true;
        }

        //Castles?
        return tabla.sancolhatE(honnan, hova);
    }

    @Override
    public char jel() {
        return isFeher() ? '♔' : '♚';
    }
}