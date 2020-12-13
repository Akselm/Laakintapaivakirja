package com.example.laakintapaivakirja;

import java.io.Serializable;

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
    private int typeindex;

    /**
     * Luokan syntyessä sen muuttujien arvot liitetään käyttäjän syöttämän tiedon pohjalta.
     * @param name Lääkkeen nimi.
     * @param time Lääkkeen ottamisajankohta.
     * @param type Lääkkeen tyyppi.
     */
    public Reminder(String name, String time, String type, String date, int typeindex) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.date = date;
        this.typeindex = typeindex;
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

    /**
     * Palauttaa lääkkeen tyyppi-indexin tästä muistutuksesta.
     * @return Palautettu arvo.
     */
    public int getTypeIndex() {
        return typeindex;
    }
}