package com.example.laakintapaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Pääluokka, joka vastaa Main Activity Layoutin toiminnasta.
 * @author Suvi Laakkonen, Aksel Manns
 * @version 11/12/2020
 */
public class MainActivity extends AppCompatActivity {
    //Muuttujien määrittely.
    private TextView textView;
    private final String shredPreferencesName = "MessageStore";
    private final String messageKey = "LastValue";
    private final String reminderKey = "ReminderValue";
    public static final String EXTRA = "com.example.laakintapaivakirja";
    private Button button; //Button variable

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Aktiviteetin luonnin aikana tapahtuvaa androidin perussäätöä.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Profiiliaktiviteetissa tallennettu nimi ja ikä tulee näkyviin.
        Intent intent = getIntent();
        String message = intent.getStringExtra(Profile.EXTRA_MESSAGE);
        textView = findViewById(R.id.textView);
        textView.setText(message);

        ListView reminderview = (ListView) findViewById(R.id.reminder_listview); //Etsitään listview ja tehdään siitä muuttuja.
        button = (Button) findViewById(R.id.button); //Etsitään nappi ja tehdään siitä muuttuja.

        button.setOnClickListener(new View.OnClickListener() { //Klikkauksella nappi kutsuu uuden aktiviteetin avaamisen.
            /**
             * Napin painamisen funktio, joka avaa RemainderActivityn.
              * @param view Tämä luokka edustaa perustaa, jolle ui-komponentit luodaan.
             */
            public void onClick(View view) {
                OpenReminderActivity();
            }
        });

        //Profiiliaktiviteetissa määritelty nimi ja ikä tallennetaan, jos ei anneta mitään, ei tulosteta tietoja.
        SharedPreferences prefGet = getSharedPreferences(shredPreferencesName, Context.MODE_PRIVATE);
        textView.setText(prefGet.getString(messageKey,""));

        //Hakee tallennetut muistutukset Gsonilla ja pyytää listaa päivittämään ne näkymään.
        String jsonString = prefGet.getString(reminderKey, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Reminder>>() {}.getType();
        ArrayList<Reminder> reminders = gson.fromJson(jsonString, type);
        ReminderList.getInstance().updateReminders(reminders);

        //Luo listviewta varten kustomoidun adapterin, joka liittää dataa ReminderList -luokan listalta näkymään.
        ReminderListAdapter adapter = new ReminderListAdapter(this, R.layout.adapter_view_layout, ReminderList.getInstance().getReminders());
        reminderview.setAdapter(adapter);


        reminderview.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Lähettää ReminderListille solun poistopyynnön klikattaessa.
            @Override
            /**
             * Listan solua painamalla adapteri hakee painetun solun indexin, jota tarvitaan solun poistopyynnössä.
             * @param i on ainoa tarvittava parametri, joka kertoo, mitä soluista painetaan.
             */
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReminderList.getInstance().removeReminder(i);

                SharedPreferences prefPut = getSharedPreferences(shredPreferencesName, Activity.MODE_PRIVATE); //Tallentaa listan tilan Gsonilla.
                SharedPreferences.Editor prefEditor = prefPut.edit();
                Gson gson = new Gson();
                String jsonString = gson.toJson(ReminderList.getInstance().getReminders());
                prefEditor.putString(reminderKey, jsonString);
                prefEditor.commit();

                Intent intent = new Intent(view.getContext(), MainActivity.class); //Päivittää MainActivityn.
                startActivity(intent);
            }
        });
    }

    /**
     * RemainderActivity avautuu, jossa käyttäjä voi luoda muistutuksia.
     */
    public void OpenReminderActivity(){

        Intent intent = new Intent(this, ReminderActivity.class);
        startActivity(intent);

    }

    /**
     * Nappia painamalla aukeaa uusi aktiviteetti.
     * @param view määrittelee mikä id toteutetaan.
     */
    public void buttonPressed(View view) {
        if (view == findViewById(R.id.buttonProfile)) {
            Intent profile = new Intent(this, Profile.class);
            startActivity(profile);
        }
    }

    /**
     * Ohjelma tallentaa käyttäjän asettamat tiedot vaikka sovellus suljetaan.
     */
    protected void onPause() {
        super.onPause();

        SharedPreferences prefPut = getSharedPreferences(shredPreferencesName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();

        prefEditor.putString(messageKey, textView.getText().toString());
    }
}