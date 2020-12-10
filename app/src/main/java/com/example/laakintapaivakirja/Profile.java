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

public class Profile extends AppCompatActivity {

    private TextView tvShowNameAndAge;
    private final String shredPreferencesName = "MessageStore";
    private final String messageKey = "LastValue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent profile = getIntent();
        String str = profile.getStringExtra(MainActivity.EXTRA);

        tvShowNameAndAge = findViewById(R.id.tvShowNameAndAge);

        SharedPreferences prefGet = getSharedPreferences(shredPreferencesName, Context.MODE_PRIVATE);
        tvShowNameAndAge.setText(prefGet.getString(messageKey,""));
    }

    public void buttonPressed(View view) {
        if(view == findViewById(R.id.buttonOK)) {

            EditText etName = (EditText) findViewById(R.id.editTextName);
            EditText etAge = (EditText) findViewById(R.id.editTextAge);

            etName.setInputType(InputType.TYPE_CLASS_TEXT);
            etAge.setInputType(InputType.TYPE_CLASS_TEXT);

            String strName = etName.getText().toString();
            String strAge = etAge.getText().toString();

            tvShowNameAndAge.setText(strName + " , " + strAge);
        }
    }

    protected void onPause() {
        super.onPause();

        SharedPreferences prefPut = getSharedPreferences(shredPreferencesName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();

        prefEditor.putString(messageKey, tvShowNameAndAge.getText().toString());
        prefEditor.commit();
    }
}