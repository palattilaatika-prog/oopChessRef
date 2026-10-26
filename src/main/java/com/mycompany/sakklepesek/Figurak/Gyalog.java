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
// if-elsek maradtak a Pawn/gyalog furcsa lehetosegei vegett
public class Gyalog extends Figura {
    public Gyalog(boolean feher) { super(feher); }

    @Override
    public boolean lephet(Mezo honnan, Mezo hova, Tabla tabla) {
        int irany = isFeher() ? 1 : -1;
        int alapSor = isFeher() ? 1 : 6;

        int dOszlop = hova.oszlop() - honnan.oszlop();
        int dSor = hova.sor() - honnan.sor();

        if (dOszlop == 0 && dSor == irany && tabla.uresE(hova)) {
            return true;
        }

        if (dOszlop == 0 && dSor == 2 * irany && honnan.sor() == alapSor) {
            Mezo koztes = new Mezo(honnan.oszlop(), honnan.sor() + irany);
            if (tabla.uresE(koztes) && tabla.uresE(hova)) {
                return true;
            }
        }

        if (Math.abs(dOszlop) == 1 && dSor == irany) {
            Figura cel = tabla.figura(hova);
            if (cel != null && cel.isFeher() != isFeher()) {
                return true;
            }
            if (tabla.enPassantLepesE(honnan, hova)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean tamadja(Mezo honnan, Mezo hova, Tabla tabla) {
        int irany = isFeher() ? 1 : -1;
        int dOszlop = Math.abs(hova.oszlop() - honnan.oszlop());
        int dSor = hova.sor() - honnan.sor();
        return dOszlop == 1 && dSor == irany;
    }

    @Override
    public char jel() {
        return isFeher() ? '♙' : '♟';
    }
}