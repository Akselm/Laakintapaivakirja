package com.example.laakintapaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Pääluokka, joka vastaa Main Activity Layoutin toiminnasta.
 * @author Suvi Laakkonen, Aksel Manns
 * @version 11/12/2020
 */
public class MainActivity extends AppCompatActivity {
    //Muuttujien määrittely.
    private TextView tvShowNameAndAge;
    private TextView textView;
    private final String shredPreferencesName = "MessageStore";
    private final String messageKey = "LastValue";
    public static final String EXTRA = "com.example.laakintapaivakirja";
    private Button button; //Button variable

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Aktiviteetin luonnin aikana tapahtuvaa androidin perussäätöä.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Profiiliaktiviteetissa tallennettu nimi tulee näkyviin.
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

        //Luo listviewta varten kustomoidun adapterin, joka liittää dataa ReminderList -luokan listalta näkymään.
        ReminderListAdapter adapter = new ReminderListAdapter(this, R.layout.adapter_view_layout, ReminderList.getInstance().getReminders());
        reminderview.setAdapter(adapter);

        //KOMENNTTIA TARVITAAN!
        SharedPreferences prefGet = getSharedPreferences(shredPreferencesName, Context.MODE_PRIVATE);
        textView.setText(prefGet.getString(messageKey,""));
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
        prefEditor.commit();
    }
}

