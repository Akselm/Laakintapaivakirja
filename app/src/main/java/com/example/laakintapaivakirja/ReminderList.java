package com.example.laakintapaivakirja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Luokka, joka kerää muistutuksia listaan sitä mukaan, kun niitä luodaan.
 * @author Aksel Manns
 * @version 11/12/2020
 */
public class ReminderList { //Luo instanssin tästä luokasta, jotta muulla koodilla olisi helppo pääsy tämän luokan listaukseen.
    private ArrayList<Reminder> reminders;
    private static final ReminderList ourInstance = new ReminderList();
    public static ReminderList getInstance() {
        return ourInstance;
    }

    /**
     * Määrittää listamuuttujan ArrayListin.
     */
    public ReminderList() {
        reminders = new ArrayList<Reminder>();
    }

    /**
     * Luo uuden muistutuksen listalle arvoineen kutsuttaessa.
     * @param name Käyttäjän määrittämä nimi lääkkeelle.
     * @param time Käyttäjän määrittämä ottamisajankohta lääkkeelle.
     * @param type Käyttäjän määrittämä tyyppi lääkkeelle.
     */
    public void addReminder(String name, String time, String type, String date, int typeindex) {
        reminders.add(new Reminder(name, time, type, date, typeindex));
    }

    /**
     * Palauttaa listview adapteriin muistutusten listauksen.
     * @return Paluuarvo tämän luokan listasta.
     */
    public ArrayList<Reminder> getReminders() {

        return reminders;
    }

    /**
     * Poistaa listviewistä klikatun solun.
     * @param i solun paikka, indexi.
     */
    public void removeReminder(int i) {
        reminders.remove(i);
    }

    /**
     * Päivittää listan muistiin tallennetuilla tiedoilla.
     * @param savedrem Muistista haettu tallennettu lista.
     */
    public void updateReminders(ArrayList savedrem) {
        this.reminders = savedrem;
    }
}