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
public abstract class Figura {
    private final boolean feher;

    protected Figura(boolean feher) {
        this.feher = feher;
    }

    public boolean isFeher() {
        return feher;
    }

    
    public abstract boolean lephet(Mezo honnan, Mezo hova, Tabla tabla);

    public abstract char jel();

    
    public boolean tamadja(Mezo honnan, Mezo hova, Tabla tabla) {
        return lephet(honnan, hova, tabla);
    }
}