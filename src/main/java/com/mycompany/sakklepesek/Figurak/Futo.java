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
public class Futo extends CsuszoFigura {
    public Futo(boolean feher) {
        super(feher, Irany.ATLOS);
    }

    @Override
    public char jel() {
        return isFeher() ? '♗' : '♝';
    }
}
