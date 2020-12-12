package com.example.laakintapaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Luokka, joka vastaa Reminder Activity Layoutin toiminnasta.
 * @author Aksel Manns
 * @version 11/12/2020
 */
public class ReminderActivity extends AppCompatActivity {
    //Muuttujien määrittely.
    private Button button2;
    RadioGroup mg;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Aktiviteetin luonnin aikana tapahtuvaa androidin perussäätöä.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        RadioGroup mg = (RadioGroup) findViewById(R.id.medicine_button_group); //Etsitään ui napit ja tehdään niistä muuttujia.
        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() { //Klikkauksella nappi toteuttaa uuden muistutuksen luomisen.
            /**
             * Napin painamisen funktio, joka tallentaa käyttäjän antamat arvot ja siirtää ne uuteen muistutukseen.
             * @param view Tämä luokka edustaa perustaa, jolle ui-komponentit luodaan.
             */
            public void onClick(View view) {
                TextView mn = findViewById(R.id.medicine_name); //Etsitään ui-tekstikentät ja tehdään niistä variableja vasta klikkauksen jälkeen, jolloin ne saavat viimeisimmät arvot.
                TextView ta = findViewById(R.id.time_amount);
                TextView da = findViewById(R.id.date_amount);

                String medicinename = mn.getText().toString(); //Muutetaan tekstikentän ja napin muuttujien sisältö vielä tekstiksi toisen muuttujan muotoon.
                String time = ta.getText().toString();
                String date = da.getText().toString();
                String type = ((RadioButton)findViewById(mg.getCheckedRadioButtonId())).getText().toString();

                ReminderList.getInstance().addReminder(medicinename, time, type, date); //Kutsuu ReminderListiä luomaan uuden muistutuksen listalle käyttäjältä saaduilla arvoilla.

                Intent intent = new Intent(view.getContext(), MainActivity.class); //Luo uuden intentin, joka palauttaa käyttäjän Main Activityyn ja sulkee RemainderActivityn.
                startActivity(intent);
                finish();
            }
        });
    }
}


