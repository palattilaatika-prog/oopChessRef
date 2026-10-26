/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sakklepesek.Figurak;

import com.mycompany.sakklepesek.Irany;
import com.mycompany.sakklepesek.Mezo;
import com.mycompany.sakklepesek.Tabla;


/**
 *
 * @author Denes
 */
public abstract class CsuszoFigura extends Figura {
    private final Irany[] iranyok;

    protected CsuszoFigura(boolean feher, Irany[] iranyok) {
        super(feher);
        this.iranyok = iranyok;
    }

    @Override
    public boolean lephet(Mezo honnan, Mezo hova, Tabla tabla) {
        if (honnan.equals(hova)) return false;

        Figura celFigura = tabla.figura(hova);
        if (celFigura != null && celFigura.isFeher() == this.isFeher()) {
            return false; // Sajat babura nem lephetunk
        }

        for (Irany irany : iranyok) {
            Mezo akt = honnan.eltol(irany.getDOszlop(), irany.getDSor());
            while (akt != null) {
                if (akt.equals(hova)) {
                    return true;
                }
                if (!tabla.uresE(akt)) {
                    break;
                }
                akt = akt.eltol(irany.getDOszlop(), irany.getDSor());
            }
        }
        return false;
    }
}
