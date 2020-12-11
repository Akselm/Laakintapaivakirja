package com.example.laakintapaivakirja;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Yksittäisestä muistutuksesta ja sen tiedoista vastaava luokka.
 * @author Aksel Manns
 * @version 11/12/2020
 */
public class Reminder implements Serializable {
    private String name; //Luokan muuttujat määritetään.
    private String time;
    private String type;
    private String date;

    /**
     * Luokan syntyessä sen muuttujien arvot liitetään käyttäjän syöttämän tiedon pohjalta.
     * @param name Lääkkeen nimi.
     * @param time Lääkkeen ottamisajankohta.
     * @param type Lääkkeen tyyppi.
     */
    public Reminder(String name, String time, String type, String date) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.date = date;
    }

    /**
     * Palauttaa lääkkeen nimen tästä muistutuksesta.
     * @return Palautettu arvo.
     */
    public String getName() {
        return name;
    }

    /**
     * Palauttaa lääkkeen ottamisajankohdan tästä muistutuksesta.
     * @return Palautettu arvo.
     */
    public String getTime() {
        return time;
    }

    /**
     * Palauttaa lääkkeen tyypin tästä muistutuksesta.
     * @return Palautettu arvo.
     */
    public String getType() {
        return type;
    }
    /**
     * Palauttaa lääkkeen ottamispäivämäärän tästä muistutuksesta.
     * @return Palautettu arvo.
     */
    public String getDate() {
        return date;
    }
}
