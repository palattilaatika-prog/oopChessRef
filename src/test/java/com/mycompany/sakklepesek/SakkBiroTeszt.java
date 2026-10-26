/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sakklepesek;
import com.mycompany.sakklepesek.Figurak.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Denes
 */
public class SakkBiroTeszt {
    private Tabla tabla;

    @BeforeEach
    void setUp() {
        tabla = new Tabla();
    }

    // --- GYALOG TESZTEK ---
    @Test
    void tesztGyalogSzabalyosDuplaLepes() {
        tabla.lerak(Mezo.of("e2"), new Gyalog(true));
        assertTrue(tabla.szabalyosLepesE(Mezo.of("e2"), Mezo.of("e4")));
    }

    @Test
    void tesztGyalogSzabalytalanHatra() {
        tabla.lerak(Mezo.of("e4"), new Gyalog(true));
        assertFalse(tabla.szabalyosLepesE(Mezo.of("e4"), Mezo.of("e3")));
    }

    // --- HUSZÁR TESZTEK ---
    @ParameterizedTest
    @CsvSource({"c3", "f3", "b1"})
    void tesztHuszarSzabalyosLepesek(String cel) {
        tabla.lerak(Mezo.of("d1"), new Huszar(true));
        assertTrue(tabla.szabalyosLepesE(Mezo.of("d1"), Mezo.of(cel)));
    }

    @Test
    void tesztHuszarSzabalytalanEgyenesen() {
        tabla.lerak(Mezo.of("d1"), new Huszar(true));
        assertFalse(tabla.szabalyosLepesE(Mezo.of("d1"), Mezo.of("d3")));
    }

    // --- FUTÓ TESZTEK ---
    @Test
    void tesztFutoSzabalyosAtlo() {
        tabla.lerak(Mezo.of("c1"), new Futo(true));
        assertTrue(tabla.szabalyosLepesE(Mezo.of("c1"), Mezo.of("h6")));
    }

    @Test
    void tesztFutoSzabalytalanEgyenes() {
        tabla.lerak(Mezo.of("c1"), new Futo(true));
        assertFalse(tabla.szabalyosLepesE(Mezo.of("c1"), Mezo.of("c4")));
    }

    // --- BÁSTYA TESZTEK ---
    @Test
    void tesztBastyaSzabalyosOszlop() {
        tabla.lerak(Mezo.of("a1"), new Bastya(true));
        assertTrue(tabla.szabalyosLepesE(Mezo.of("a1"), Mezo.of("a7")));
    }

    @Test
    void tesztBastyaSzabalytalanAtlo() {
        tabla.lerak(Mezo.of("a1"), new Bastya(true));
        assertFalse(tabla.szabalyosLepesE(Mezo.of("a1"), Mezo.of("b2")));
    }

    // --- VEZÉR TESZTEK ---
    @Test
    void tesztVezerSzabalyosAtlo() {
        tabla.lerak(Mezo.of("d1"), new Vezer(true));
        assertTrue(tabla.szabalyosLepesE(Mezo.of("d1"), Mezo.of("h5")));
    }

    @Test
    void tesztVezerSzabalytalanLóUgras() {
        tabla.lerak(Mezo.of("d1"), new Vezer(true));
        assertFalse(tabla.szabalyosLepesE(Mezo.of("d1"), Mezo.of("e3")));
    }

    // --- KIRÁLY TESZTEK ---
    @Test
    void tesztKiralySzabalyosEgyMezo() {
        tabla.lerak(Mezo.of("e1"), new Kiraly(true));
        assertTrue(tabla.szabalyosLepesE(Mezo.of("e1"), Mezo.of("e2")));
    }

    @Test
    void tesztKiralySzabalytalanKetMezo() {
        tabla.lerak(Mezo.of("e1"), new Kiraly(true));
        assertFalse(tabla.szabalyosLepesE(Mezo.of("e1"), Mezo.of("e3")));
    }

    // --- SPECIÁLIS SZABÁLYOK TESZTJEI ---
    @Test
    void tesztSakkbaLepesTilalma() {
        // Kiraly e1-en, Vezer d1-en, Ellenseges Bastya h1-en -> a vezér elmozdulása felfedi a sakkot
        tabla.lerak(Mezo.of("e1"), new Kiraly(true));
        tabla.lerak(Mezo.of("d1"), new Vezer(true));
        tabla.lerak(Mezo.of("h1"), new Bastya(false));

        // A vezér d2-re lépne, de akkor a király ütésben marad h1-ről
        assertFalse(tabla.szabalyosLepesE(Mezo.of("d1"), Mezo.of("d2")));
    }

    @Test
    void tesztEnPassantSpeciálisLepes() {
        tabla.lerak(Mezo.of("e2"), new Gyalog(false)); // Tegyük fel, sötét gyalog e7-ről e5-re lépett
        tabla.vegrehajt(Mezo.of("e7"), Mezo.of("e5")); // Szimuláljuk az utolsó lépést
        tabla.lerak(Mezo.of("d5"), new Gyalog(true));  // Világos gyalog d5-ön

        assertTrue(tabla.szabalyosLepesE(Mezo.of("d5"), Mezo.of("e6")));
    }
}
