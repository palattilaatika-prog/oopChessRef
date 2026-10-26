/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sakklepesek;
import com.mycompany.sakklepesek.Figurak.*;
//AI GENERALT


/**
 *
 * @author Denes
 */
public class Tabla {
    private final Figura[][] racs = new Figura[8][8];
    private Lepes utolsoLepes = null;

    public Tabla() {}

    //copy
    public Tabla(Tabla masik) {
        for (int o = 0; o < 8; o++) {
            for (int s = 0; s < 8; s++) {
                this.racs[o][s] = masik.racs[o][s];
            }
        }
        this.utolsoLepes = masik.utolsoLepes;
    }

    public void lerak(Mezo m, Figura f) {
        racs[m.oszlop()][m.sor()] = f;
    }

    public Figura figura(Mezo m) {
        return racs[m.oszlop()][m.sor()];
    }

    public boolean uresE(Mezo m) {
        return figura(m) == null;
    }

    public Lepes getUtolsoLepes() {
        return utolsoLepes;
    }

    public void alaphelyzet() {
        
        for (int i = 0; i < 8; i++) {
            lerak(new Mezo(i, 1), new Gyalog(true));
            lerak(new Mezo(i, 6), new Gyalog(false));
        }
        lerak(new Mezo(0, 0), new Bastya(true));
        lerak(new Mezo(1, 0), new Huszar(true));
        lerak(new Mezo(2, 0), new Futo(true));
        lerak(new Mezo(3, 0), new Vezer(true));
        lerak(new Mezo(4, 0), new Kiraly(true));
        lerak(new Mezo(5, 0), new Futo(true));
        lerak(new Mezo(6, 0), new Huszar(true));
        lerak(new Mezo(7, 0), new Bastya(true));

        lerak(new Mezo(0, 7), new Bastya(false));
        lerak(new Mezo(1, 7), new Huszar(false));
        lerak(new Mezo(2, 7), new Futo(false));
        lerak(new Mezo(3, 7), new Vezer(false));
        lerak(new Mezo(4, 7), new Kiraly(false));
        lerak(new Mezo(5, 7), new Futo(false));
        lerak(new Mezo(6, 7), new Huszar(false));
        lerak(new Mezo(7, 7), new Bastya(false));
    }

    /**
     * Fő ellenőrző metódus a bíró számára.
     */
    public boolean szabalyosLepesE(Mezo honnan, Mezo hova) {
        Figura f = figura(honnan);
        if (f == null) return false;

        // 1. A figura saját mozgási szabályai szerint léphet-e?
        if (!f.lephet(honnan, hova, this)) {
            return false;
        }

        // 2. A lépés után a saját király sakkban maradna/kerülne-e?
        Tabla masolat = new Tabla(this);
        masolat.vegrehajt(honnan, hova);
        if (masolat.sakkbanVanE(f.isFeher())) {
            return false;
        }

        return true;
    }

    public void vegrehajt(Mezo honnan, Mezo hova) {
        Figura f = figura(honnan);
        if (f == null) return;

        // En passant
        if (f instanceof Gyalog && enPassantLepesE(honnan, hova)) {
            racs[hova.oszlop()][honnan.sor()] = null;
        }

        // Castle
        if (f instanceof Kiraly && Math.abs(hova.oszlop() - honnan.oszlop()) == 2) {
            int sor = honnan.sor();
            if (hova.oszlop() == 6) { // Kis sánc
                racs[5][sor] = racs[7][sor];
                racs[7][sor] = null;
            } else if (hova.oszlop() == 2) { // Nagy sánc
                racs[3][sor] = racs[0][sor];
                racs[0][sor] = null;
            }
        }

        // Gyalog->valami
        if (f instanceof Gyalog && (hova.sor() == 7 || hova.sor() == 0)) {
            f = new Vezer(f.isFeher());
        }

        racs[hova.oszlop()][hova.sor()] = f;
        racs[honnan.oszlop()][honnan.sor()] = null;

        this.utolsoLepes = new Lepes(honnan, hova, f);
    }

    public boolean sakkbanVanE(boolean feher) {
        Mezo kiralyMezo = keresKiraly(feher);
        if (kiralyMezo == null) return false;

        for (int o = 0; o < 8; o++) {
            for (int s = 0; s < 8; s++) {
                Mezo m = new Mezo(o, s);
                Figura f = figura(m);
                if (f != null && f.isFeher() != feher) {
                    if (f.tamadja(m, kiralyMezo, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Mezo keresKiraly(boolean feher) {
        for (int o = 0; o < 8; o++) {
            for (int s = 0; s < 8; s++) {
                Mezo m = new Mezo(o, s);
                Figura f = figura(m);
                if (f instanceof Kiraly && f.isFeher() == feher) {
                    return m;
                }
            }
        }
        return null;
    }

    public boolean enPassantLepesE(Mezo honnan, Mezo hova) {
        Figura f = figura(honnan);
        if (!(f instanceof Gyalog)) return false;
        if (utolsoLepes == null) return false;

        Figura utolsoFigura = figura(utolsoLepes.hova());
        if (!(utolsoFigura instanceof Gyalog)) return false;

        int dOszlop = Math.abs(utolsoLepes.honnan().oszlop() - utolsoLepes.hova().oszlop());
        int dSor = Math.abs(utolsoLepes.honnan().sor() - utolsoLepes.hova().sor());

        // Az utolsó lépés egy dupla gyaloglépés volt a szomszédos oszlopban
        boolean duplaLepesVolt = (dOszlop == 0 && dSor == 2);
        boolean szomszedosOszlop = Math.abs(utolsoLepes.hova().oszlop() - honnan.oszlop()) == 1;
        boolean azonosSor = utolsoLepes.hova().sor() == honnan.sor();

        int irany = f.isFeher() ? 1 : -1;
        boolean joCélMező = (hova.oszlop() == utolsoLepes.hova().oszlop()) && (hova.sor() == honnan.sor() + irany);

        return duplaLepesVolt && szomszedosOszlop && azonosSor && joCélMező;
    }

    public boolean sancolhatE(Mezo honnan, Mezo hova) {
        Figura f = figura(honnan);
        if (!(f instanceof Kiraly)) return false;

        int sor = f.isFeher() ? 0 : 7;
        if (honnan.sor() != sor || honnan.oszlop() != 4) return false;

        // Kis sánc (e1-g1 vagy e8-g8)
        if (hova.sor() == sor && hova.oszlop() == 6) {
            Figura b = figura(new Mezo(7, sor));
            if (!(b instanceof Bastya) || b.isFeher() != f.isFeher()) return false;
            return uresE(new Mezo(5, sor)) && uresE(new Mezo(6, sor)) &&
                   !sakkbanVanE(f.isFeher()) &&
                   !mezotTamadjakE(new Mezo(5, sor), !f.isFeher());
        }

        // Nagy sánc (e1-c1 vagy e8-c8)
        if (hova.sor() == sor && hova.oszlop() == 2) {
            Figura b = figura(new Mezo(0, sor));
            if (!(b instanceof Bastya) || b.isFeher() != f.isFeher()) return false;
            return uresE(new Mezo(1, sor)) && uresE(new Mezo(2, sor)) && uresE(new Mezo(3, sor)) &&
                   !sakkbanVanE(f.isFeher()) &&
                   !mezotTamadjakE(new Mezo(3, sor), !f.isFeher());
        }

        return false;
    }

    private boolean mezotTamadjakE(Mezo m, boolean tamadoSzin) {
        for (int o = 0; o < 8; o++) {
            for (int s = 0; s < 8; s++) {
                Mezo forras = new Mezo(o, s);
                Figura f = figura(forras);
                if (f != null && f.isFeher() == tamadoSzin) {
                    if (f.tamadja(forras, m, this)) return true;
                }
            }
        }
        return false;
    }

    public void kirajzol() {
        System.out.println("\n  a b c d e f g h");
        for (int s = 7; s >= 0; s--) {
            System.out.print((s + 1) + " ");
            for (int o = 0; o < 8; o++) {
                Figura f = racs[o][s];
                System.out.print((f == null ? "." : f.jel()) + " ");
            }
            System.out.println(s + 1);
        }
        System.out.println("  a b c d e f g h\n");
    }
}
