/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sakklepesek.Figurak;

import com.mycompany.sakklepesek.Irany;

/**
 *
 * @author Denes
 */
public class Bastya extends CsuszoFigura {
    public Bastya(boolean feher) {
        super(feher, Irany.ORTOGONALIS);
    }

    @Override
    public char jel() {
        return isFeher() ? '♖' : '♜';
    }
}
