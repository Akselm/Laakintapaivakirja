package com.example.laakintapaivakirja;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvShowNameAndAge;
    private TextView textView;
    private final String shredPreferencesName = "MessageStore";
    private final String messageKey = "LastValue";
    public static final String EXTRA = "com.example.laakintapaivakirja";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Profiiliaktiviteetissa tallennettu nimi tulee näkyviin.
        Intent intent = getIntent();
        String message = intent.getStringExtra(Profile.EXTRA_MESSAGE);
        textView = findViewById(R.id.textView);
        textView.setText(message);


        SharedPreferences prefGet = getSharedPreferences(shredPreferencesName, Context.MODE_PRIVATE);
        textView.setText(prefGet.getString(messageKey,""));
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