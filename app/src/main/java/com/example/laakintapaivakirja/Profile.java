package com.example.laakintapaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Luokka sisältää käyttäjäprofiilin tarvitsemia tietoja.
 * @author Suvi Laakkonen
 * @version 11/12/2020
 */

public class Profile extends AppCompatActivity {

    private TextView tvShowNameAndAge;
    private final String shredPreferencesName = "MessageStore";
    private final String messageKey = "LastValue";
    public static final String EXTRA_MESSAGE = "com.example.laakintapaivakirja.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent profile = getIntent();
        String str = profile.getStringExtra(MainActivity.EXTRA);

        tvShowNameAndAge = findViewById(R.id.tvShowNameAndAge);

        //Tallennaetaan käyttäjän asettama nimi muistiin, jos ei anneta sisältöä, ei tulosteta mitään näkyviin.
        SharedPreferences prefGet = getSharedPreferences(shredPreferencesName, Context.MODE_PRIVATE);
        tvShowNameAndAge.setText(prefGet.getString(messageKey,""));
    }

    /**
     * Nappia painamalla asetettu nimi ja ikä tallentuvat laitteelle ja palataan main aktiviteettiin.
     * @param view määrittelee mikä id toteutetaan.
     */
    public void buttonPressed(View view) {
        if(view == findViewById(R.id.buttonOK)) {

            //Muunnetaan molempien editText kenttien sisällöt merkkijonoiksi.
            EditText etName = (EditText) findViewById(R.id.editTextName);
            EditText etAge = (EditText) findViewById(R.id.editTextAge);

            etName.setInputType(InputType.TYPE_CLASS_TEXT);
            etAge.setInputType(InputType.TYPE_CLASS_TEXT);

            String strName = etName.getText().toString();
            String strAge = etAge.getText().toString();

            //Asetetaan käyttäjän nimi näkyville ok -nappiaa painamalla ja viedään annettu nimi main aktiviteettiin.
            tvShowNameAndAge.setText("Hei " + strName + "!");

            Intent intentMain = new Intent(this, MainActivity.class);
            intentMain.putExtra(EXTRA_MESSAGE, strName);
            startActivity(intentMain);
        }
    }

    /**
     * Ohjelma tallentaa käyttäjän asettamat tiedot vaikka sovellus suljetaan.
     */
    protected void onPause() {
        super.onPause();

        SharedPreferences prefPut = getSharedPreferences(shredPreferencesName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();

        prefEditor.putString(messageKey, tvShowNameAndAge.getText().toString());
        prefEditor.commit();
    }
}