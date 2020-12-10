package com.example.laakintapaivakirja;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reminder implements Serializable {
    private String name; //Luokan variablet
    private String time;

    public Reminder(String name, String time) {  //Päivittää itsensä muistutusten listalle luonnin yhteydessä
        //ArrayList reminders = new ArrayList<Reminder>();
        //reminders.add(this);
        //Log.i("class", "" + reminders);
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
