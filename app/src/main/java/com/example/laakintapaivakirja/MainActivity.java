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

public class MainActivity extends AppCompatActivity {

    private TextView tvShowNameAndAge;
    private TextView textView;
    private final String shredPreferencesName = "MessageStore";
    private final String messageKey = "LastValue";
    public static final String EXTRA = "com.example.laakintapaivakirja";
    private Button button; //Button variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Profiiliaktiviteetissa tallennettu nimi tulee näkyviin.
        Intent intent = getIntent();
        String message = intent.getStringExtra(Profile.EXTRA_MESSAGE);
        textView = findViewById(R.id.textView);
        textView.setText(message);

        ListView mListView = (ListView) findViewById(R.id.reminder_listview);

        button = (Button) findViewById(R.id.button); //Etsitään ui tekstikentät/napit ja tehdään niistä variableja
        button.setOnClickListener(new View.OnClickListener() { //Klikkauksella nappi kutsuu uuden aktiviteetin avaamisen
            public void onClick(View view) {
                OpenRemainderActivity();
            }
        });

        Reminder reminder1 = new Reminder("he", "jgh");
        ArrayList<Reminder> reminders = new ArrayList<>();
        //reminders.add(reminder1);

        Reminder reminder = (Reminder) getIntent().getSerializableExtra("NAME");
        int condition = getIntent().getIntExtra("method", 0);

        while(condition==1)
        {
            reminders.add(reminder);
            condition = 0;
            Log.i("abcd", "" + condition);
        }

        ReminderListAdapter adapter = new ReminderListAdapter(this, R.layout.adapter_view_layout, reminders);
        mListView.setAdapter(adapter);

        SharedPreferences prefGet = getSharedPreferences(shredPreferencesName, Context.MODE_PRIVATE);
        textView.setText(prefGet.getString(messageKey,""));
    }

    public void OpenRemainderActivity(){ //Uusi aktiviteetti avautuu, kun kutsu käy
        Intent intent2 = new Intent(this, RemainderActivity.class);
        startActivity(intent2);

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

